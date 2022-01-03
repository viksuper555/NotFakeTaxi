package com.example.notfaketaxi.filters;

import com.example.notfaketaxi.repositories.ClientRepository;
import com.example.notfaketaxi.repositories.OAuthRepository;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;


@Component
public class AuthFilter implements Filter {
    private final OAuthRepository oauthRepo;

    public AuthFilter(OAuthRepository oauthRepo) {
        this.oauthRepo = oauthRepo;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();

        if (path.startsWith("/api/client/register") || path.startsWith("/api/authorization"))
        {
            chain.doFilter(request, response);
        }
        else if(req.getHeader("Authorization") != null)
        {
            UUID token;
            try{
                token = UUID.fromString(req.getHeader("Authorization"));

            } catch (IllegalArgumentException exception){

                HttpServletResponse res = (HttpServletResponse) response;
                res.setStatus(401);
                return;
            }


            var oauth = oauthRepo.findOAuthByAccessTokenAndExpireDateAfter(token, new Date());
            if(oauth.isEmpty())
            {
                HttpServletResponse res = (HttpServletResponse) response;
                res.setStatus(401);
                return;
            }

            var client = oauth.get().getClient();
            request.setAttribute("client", client);
            chain.doFilter(request, response);
        }
        else {
            HttpServletResponse res = (HttpServletResponse) response;
            res.setStatus(401);
        }
    }
}
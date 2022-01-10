package com.notfaketaxi.repositories;

import com.notfaketaxi.entities.OAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface OAuthRepository extends JpaRepository<OAuth,Long> {
   Optional<OAuth> findOAuthByAuthorizationCode(UUID code);
   Optional<OAuth> findOAuthByAccessTokenAndExpireDateAfter(UUID token, Date today);
}

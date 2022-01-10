package com.notfaketaxi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notfaketaxi.models.responses.GoogleApiResponse;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.List;
import java.util.concurrent.Future;

import org.springframework.web.util.UriComponentsBuilder;

@Component
public class HttpRouteService {

    @SneakyThrows
    public static List<GoogleApiResponse.Route> GetDirections(String origin, String destination) {
        CloseableHttpClient client = HttpClientBuilder.create().build();

        var url = "https://maps.googleapis.com/maps/api/directions/json";
        var apiKey = System.getenv().get("APIKey");

        var Uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("maps.googleapis.com")
                .path("/maps/api/directions/json")
                .queryParam("key", apiKey)
                .queryParam("mode", "driving")
                .queryParam("destination", "Bulgaria, Sofia," + destination)
                .queryParam("origin", "Bulgaria, Sofia, " + origin)
                .build()
                .toUri();

        var request = new HttpGet(Uri);

        CloseableHttpResponse response = client.execute(request);
        var parsed = new ObjectMapper().readValue(response.getEntity().getContent(), GoogleApiResponse.class);

        if(!parsed.status.equals("OK"))
            return null;

        return parsed.routes;
    }
}

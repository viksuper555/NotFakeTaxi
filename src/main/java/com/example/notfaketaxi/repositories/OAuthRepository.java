package com.example.notfaketaxi.repositories;

import com.example.notfaketaxi.entities.Client;
import com.example.notfaketaxi.entities.OAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuthRepository extends JpaRepository<OAuth,Long> {
//   Optional<OAuth> findOAuthByClient(Client client);
}

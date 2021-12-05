package com.example.notfaketaxi.repositories;

import com.example.notfaketaxi.entities.OAuth;
import com.example.notfaketaxi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuthRepository extends JpaRepository<OAuth,Long> {

   OAuth findOAuthByUser(String userName, String password);
}

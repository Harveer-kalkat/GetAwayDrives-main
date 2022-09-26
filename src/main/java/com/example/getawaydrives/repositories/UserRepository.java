package com.example.getawaydrives.repositories;

import com.example.getawaydrives.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
//    List<User> findByNameContaining (String kw);
    List<User> findUserByEmail(String email);
}

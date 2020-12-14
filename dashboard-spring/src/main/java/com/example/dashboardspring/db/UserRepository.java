package com.example.dashboardspring.db;

import java.util.Optional;

import com.example.dashboardspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findUserByEmail(String email);
}
package com.revature.repo;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);
}

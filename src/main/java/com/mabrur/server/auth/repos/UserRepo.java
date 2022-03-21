package com.mabrur.server.auth.repos;

import java.util.Optional;

import com.mabrur.server.auth.entities.User;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends PagingAndSortingRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}

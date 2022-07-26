package com.devnus.belloga.user.user.repository;

import com.devnus.belloga.user.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository<T extends User> extends JpaRepository<T, String> {
    Optional<User> findByAccountId(String accountId);
    boolean existsByAccountId(String accountId);
}

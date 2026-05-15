package com.wanted.springasync.repository.user;

import java.util.Optional;

import com.wanted.springasync.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}

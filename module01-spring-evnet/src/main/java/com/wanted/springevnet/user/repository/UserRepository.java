package com.wanted.springevnet.user.repository;

import com.wanted.springevnet.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

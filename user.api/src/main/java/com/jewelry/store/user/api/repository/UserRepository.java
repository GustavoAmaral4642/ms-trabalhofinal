package com.jewelry.store.user.api.repository;

import com.jewelry.store.user.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

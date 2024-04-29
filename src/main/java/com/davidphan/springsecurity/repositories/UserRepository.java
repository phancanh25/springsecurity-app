package com.davidphan.springsecurity.repositories;

import com.davidphan.springsecurity.entities.Role;
import com.davidphan.springsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    User findByRole(Role role);
}

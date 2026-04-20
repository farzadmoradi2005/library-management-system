package org.example.library_management_system.repository;


import org.example.library_management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}

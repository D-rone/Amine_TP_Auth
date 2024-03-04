package com.esisba.tp_auth.repositories;

import com.esisba.tp_auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {
    User findUserByAddress(String a);
}

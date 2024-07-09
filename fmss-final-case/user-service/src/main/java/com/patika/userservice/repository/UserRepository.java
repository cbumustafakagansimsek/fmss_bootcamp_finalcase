package com.patika.userservice.repository;

import com.patika.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsUserByMail(String mail);

}

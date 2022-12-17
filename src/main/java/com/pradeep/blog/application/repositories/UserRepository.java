package com.pradeep.blog.application.repositories;

import com.pradeep.blog.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    List<User> findByGender(String gender);


}

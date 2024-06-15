package com.tpe.library_management_system.repository.user;

import com.tpe.library_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername (String email);
}

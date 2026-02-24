package com.lcwd.user.service.UserService.repositories;

import com.lcwd.user.service.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

public  interface UserRepository extends JpaRepository<User, String> {

}

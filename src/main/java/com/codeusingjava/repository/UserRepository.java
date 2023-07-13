package com.codeusingjava.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.codeusingjava.model.UserDao;
public interface UserRepository extends JpaRepository<UserDao, Integer> {
    UserDao findByUsername(String username);
}
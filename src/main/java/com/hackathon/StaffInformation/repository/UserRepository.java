package com.hackathon.StaffInformation.repository;

import com.hackathon.StaffInformation.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    @Modifying
    @Query(value = "INSERT INTO users_roles VALUES (?1, ?2)", nativeQuery = true)
    @Transactional
    void setRoleOnSignup(Long role, String username);

    @Modifying
    @Query(value = "UPDATE users_roles set role_id = ?1 where username like ?2", nativeQuery = true)
    @Transactional
    void setRoleForUser(Long role, String username);

    @Modifying
    @Query(value = "INSERT INTO roles_actions VALUES (?1, ?2)", nativeQuery = true)
    @Transactional
    void pushRolesActions(Long role_id, Long action_id);

    @Modifying
    @Query(value = "INSERT INTO ACTIONS VALUES (?1, ?2)", nativeQuery = true)
    @Transactional
    void pushActions(Long action_id, String action_name);

    @Modifying
    @Query(value = "TRUNCATE TABLE users_roles", nativeQuery = true)
    @Transactional
    void deleteRolesActions();

    @Modifying
    @Query(value = "TRUNCATE TABLE roles_actions", nativeQuery = true)
    @Transactional
    void deleteUsersRoles();
}
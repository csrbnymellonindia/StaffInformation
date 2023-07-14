package com.hackathon.StaffInformation.model;

import jakarta.persistence.*;
import java.util.*;

@Table(name = "ROLES")
@Entity
public class Role {
    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_actions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "action_id")
    )
    private Set<Actions> actions;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Actions> getActions() {
        return actions;
    }

    public void setActions(Set<Actions> actions) {
        this.actions = actions;
    }

    public Role(){

    }

    public Role(Long roleId, String roleName, Set<User> users, Set<Actions> actions) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.users = users;
        this.actions = actions;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass()!=o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) && Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode(){ return Objects.hash(roleId, roleName);}
}
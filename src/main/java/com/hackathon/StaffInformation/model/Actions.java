package com.hackathon.StaffInformation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.*;

@Table(name = "ACTIONS")
@Entity
public class Actions{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "action_id")
    private Long actionId;
    @Column(name = "action_name")
    private String actionName;

    @ManyToMany(mappedBy = "actions")
    private Set<Role> roles;

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @JsonManagedReference
    @JsonIgnore
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Actions(){

    }

    public Actions(Long actionId, String actionName, Set<Role> roles) {
        this.actionId = actionId;
        this.actionName = actionName;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass()!=o.getClass()) return false;
        Actions actions = (Actions) o;
        return Objects.equals(actionId, actions.actionId) && Objects.equals(actionName, actions.actionName);
    }

    @Override
    public int hashCode(){ return Objects.hash(actionId, actionName); }
}
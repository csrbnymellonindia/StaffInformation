package com.hackathon.StaffInformation.model;

import jakarta.persistence.*;
import java.util.*;

@Table(name = "USERS")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "primary_contact_number")
    private Long primaryContactNumber;

    @Column(name = "secondary_contact_number")
    private Long secondaryContactNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "whatsapp_number")
    private Long whatsappNumber;

    @Column(name = "password")
    private String password;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPrimaryContactNumber() {
        return primaryContactNumber;
    }

    public void setPrimaryContactNumber(Long primaryContactNumber) {
        this.primaryContactNumber = primaryContactNumber;
    }

    public Long getSecondaryContactNumber() {
        return secondaryContactNumber;
    }

    public void setSecondaryContactNumber(Long secondaryContactNumber) {
        this.secondaryContactNumber = secondaryContactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(Long whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(){
    }

    public User(Long id, String username, String firstName, String middleName, String lastName, Long primaryContactNumber, Long secondaryContactNumber, String address, String email, Long whatsappNumber, String password, Role role) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.primaryContactNumber = primaryContactNumber;
        this.secondaryContactNumber = secondaryContactNumber;
        this.address = address;
        this.email = email;
        this.whatsappNumber = whatsappNumber;
        this.password = password;
        this.role = role;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass()!=o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) &&
                Objects.equals(middleName, user.middleName) && Objects.equals(lastName, user.lastName) &&
                Objects.equals(primaryContactNumber, user.primaryContactNumber) &&
                Objects.equals(secondaryContactNumber, user.secondaryContactNumber) &&
                Objects.equals(whatsappNumber, user.whatsappNumber) &&
                Objects.equals(address, user.address) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode(){return Objects.hash(id, username, firstName, middleName, lastName, primaryContactNumber, secondaryContactNumber, address, email, whatsappNumber, password);}
}
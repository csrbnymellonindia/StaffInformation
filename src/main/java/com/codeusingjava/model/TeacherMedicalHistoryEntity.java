package com.codeusingjava.model;

import javax.persistence.*;

@Entity
@Table(name = "teacher_medical_history")
public class TeacherMedicalHistoryEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
    @Column(name="staff_id")
    private Long staffId;

    @Column(name="blood_group")
    private String bloodGroup;

    @Column(name="height")
    private int height;

    @Column(name="weight")
    private int weight;

    @Column(name="differently_abled")
    private boolean differentlyAbled;

    @Column(name="disability")
    private String disability;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isDifferentlyAbled() {
        return differentlyAbled;
    }

    public void setDifferentlyAbled(boolean differentlyAbled) {
        this.differentlyAbled = differentlyAbled;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public TeacherMedicalHistoryEntity() {
    }
}

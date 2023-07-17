package com.codeusingjava.model;

import javax.persistence.*;

@Entity
@Table(name = "CLASS")
public class ClassModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLASS_IDENTIFIER")
    private int classIdentifier;

    @Column(name = "GRADE_TEXT")
    private String gradeText;

    @Column(name = "DIVISION_TEXT")
    private String divisionText;

    @Column(name = "STAFF_IDENTIFIER")
    private Integer staffIdentifier;

    public int getClassIdentifier() {
        return classIdentifier;
    }

    public void setClassIdentifier(int classIdentifier) {
        this.classIdentifier = classIdentifier;
    }

    public String getGradeText() {
        return gradeText;
    }

    public void setGradeText(String gradeText) {
        this.gradeText = gradeText;
    }

    public String getDivisionText() {
        return divisionText;
    }

    public void setDivisionText(String divisionText) {
        this.divisionText = divisionText;
    }

    public Integer getStaffIdentifier() {
        return staffIdentifier;
    }

    public void setStaffIdentifier(Integer staffIdentifier) {
        this.staffIdentifier = staffIdentifier;
    }

    public ClassModel() {
    }
}

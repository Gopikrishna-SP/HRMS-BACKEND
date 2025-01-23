package com.kryptos.hrms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeeStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Assuming a unique ID for this entity, you can generate it or use a custom one

    private int totalEmployees;
    private int departments;
    private int newHires;
    private double staffTurnover;

    // Constructors
    public EmployeeStatistics() {}

    public EmployeeStatistics(Long id, int totalEmployees, int departments, int newHires, double staffTurnover) {
        this.id = id;
        this.totalEmployees = totalEmployees;
        this.departments = departments;
        this.newHires = newHires;
        this.staffTurnover = staffTurnover;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public int getDepartments() {
        return departments;
    }

    public void setDepartments(int departments) {
        this.departments = departments;
    }

    public int getNewHires() {
        return newHires;
    }

    public void setNewHires(int newHires) {
        this.newHires = newHires;
    }

    public double getStaffTurnover() {
        return staffTurnover;
    }

    public void setStaffTurnover(double staffTurnover) {
        this.staffTurnover = staffTurnover;
    }
}

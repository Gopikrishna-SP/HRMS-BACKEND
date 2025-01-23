package com.kryptos.hrms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

import com.kryptos.hrms.model.enums.ERole;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name; 
    
//    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
//    private List<Employee> employees; // List of employees assigned to this role

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
//
//    public List<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }

    // Default constructor
    public Role() {
    }

    // Constructor with parameters
    public Role(Long roleId, ERole name) {
        this.roleId = roleId;
        this.name = name;
    }
}

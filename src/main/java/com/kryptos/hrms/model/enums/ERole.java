package com.kryptos.hrms.model.enums;

public enum ERole {
    
	ROLE_EMPLOYEE("Employee"), 
    ROLE_HUMANRESOURCE("Hr"), 
    ROLE_MANAGER("Manager"), 
    ROLE_ADMIN("Admin");
    
    private final String roleName;  

    ERole(String roleName) {
        this.roleName = roleName;
    }
    
    public String getRoleName() {
        return roleName;
    }


}

package com.kryptos.hrms.model.enums;

public enum ELeaveType {
	SICK("sick"), CASUAL("casual"), PUBLIC_HOLIDAY("public_holiday"); 
    private final String leaveType;  

    ELeaveType(String leaveType) {
        this.leaveType = leaveType;
    }
    
    public String getGender() {
        return leaveType;
    }

}

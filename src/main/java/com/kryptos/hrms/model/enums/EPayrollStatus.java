package com.kryptos.hrms.model.enums;

public enum EPayrollStatus {

	PENDING("pending"),
	PAID("paid");
	
    private final String status;  

    EPayrollStatus(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }
}

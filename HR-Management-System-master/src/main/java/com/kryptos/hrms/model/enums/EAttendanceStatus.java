package com.kryptos.hrms.model.enums;

public enum EAttendanceStatus {
	
	ABSENT("absent"),HALF_DAY("half day"),PRESENT("present"),LEAVE("leave");
	
    private final String status;  

    private EAttendanceStatus(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }
	

}

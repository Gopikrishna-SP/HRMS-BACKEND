package com.kryptos.hrms.model.enums;

public enum EGender {
	
	MALE("male"), FEMALE("female"), RATHER_NOT_SAY("rather not say");
	
    private final String gender;  

    EGender(String gender) {
        this.gender = gender;
    }
    
    public String getGender() {
        return gender;
    }

}

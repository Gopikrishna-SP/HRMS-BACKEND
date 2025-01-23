package com.kryptos.hrms.model.enums;


public enum EMonth {
	JANUARY(1), FEBUARY(2), MARCH(3), APRIL(4), MAY(5), JUNE(6), JULY(7), AUGUST(8), SEPTEMBER(9), OCTOBER(10),
	NOVEMBER(11), DECEMBER(12);

	private final int monthValue;

	EMonth(int monthValue) {
        this.monthValue = monthValue;
    }

	public int getMonthValue() {
		return monthValue;
	}

	// Get PayrollMonth by its integer value
	public static EMonth fromValue(int value) {
		for (EMonth month : EMonth.values()) {
			if (month.getMonthValue() == value) {
				return month;
			}
		}
		throw new IllegalArgumentException("Invalid month value: " + value);
	}
}

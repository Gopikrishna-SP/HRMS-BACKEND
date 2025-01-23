package com.kryptos.hrms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MasterLeave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(insertable = false, updatable = false)
	private int sick;

	@Column(insertable = false, updatable = false)
	private int casual;

	@Column(insertable = false, updatable = false)
	private int paidLeave;

	@Column(insertable = false, updatable = false)
	private int unPaidLeave;

	@Column(insertable = false, updatable = false)
	private int maternityLeave;

	@Column(insertable = false, updatable = false)
	private int paternityLeave;

	@Column(insertable = false, updatable = false)
	private int publicHoliday;

	public MasterLeave() {
		// Default constructor
	}

	public MasterLeave(Long id, int sick, int casual, int paidLeave, int unPaidLeave, int maternityLeave,
			int paternityLeave, int publicHoliday) {
		super();
		this.id = id;
		this.sick = sick;
		this.casual = casual;
		this.paidLeave = paidLeave;
		this.unPaidLeave = unPaidLeave;
		this.maternityLeave = maternityLeave;
		this.paternityLeave = paternityLeave;
		this.publicHoliday = publicHoliday;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Getters and setters
	public int getSick() {
		return sick;
	}

	public void setSick(int sick) {
		this.sick = sick;
	}

	public int getCasual() {
		return casual;
	}

	public void setCasual(int casual) {
		this.casual = casual;
	}

	public int getPaidLeave() {
		return paidLeave;
	}

	public void setPaidLeave(int paidLeave) {
		this.paidLeave = paidLeave;
	}

	public int getUnPaidLeave() {
		return unPaidLeave;
	}

	public void setUnPaidLeave(int unPaidLeave) {
		this.unPaidLeave = unPaidLeave;
	}

	public int getMaternityLeave() {
		return maternityLeave;
	}

	public void setMaternityLeave(int maternityLeave) {
		this.maternityLeave = maternityLeave;
	}

	public int getPaternityLeave() {
		return paternityLeave;
	}

	public void setPaternityLeave(int paternityLeave) {
		this.paternityLeave = paternityLeave;
	}

	public int getPublicHoliday() {
		return publicHoliday;
	}

	public void setPublicHoliday(int publicHoliday) {
		this.publicHoliday = publicHoliday;
	}
}

package com.kryptos.hrms.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class BankAccountDetails {


	private String accountHolderName;
	private String bankName;
	private String branchName;
	private String accountNumber;
	private String accountType;
	private String ifscCode;
	private LocalDate accountOpeningDate;
	@Column(nullable = true)
	private Boolean isActive;
	
	public BankAccountDetails() {
		// TODO Auto-generated constructor stub
	}

	public BankAccountDetails(String accountHolderName, String bankName, String branchName, String accountNumber,
			String accountType, String ifscCode, LocalDate accountOpeningDate,
			 boolean isActive) {
		super();
		this.accountHolderName = accountHolderName;
		this.bankName = bankName;
		this.branchName = branchName;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.ifscCode = ifscCode;
		this.accountOpeningDate = accountOpeningDate;
		this.isActive = isActive;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}



	public LocalDate getAccountOpeningDate() {
		return accountOpeningDate;
	}

	public void setAccountOpeningDate(LocalDate accountOpeningDate) {
		this.accountOpeningDate = accountOpeningDate;
	}



	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
}

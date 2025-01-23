package com.kryptos.hrms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class UserLeave {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userLeaveId;


    @OneToOne
    private User user;

    @OneToOne
    private MasterLeave leaveDefault;

    private int sickTaken;
    private int casualTaken;
    private int paidLeaveTaken;
    private int unPaidLeaveTaken;
    private int maternityLeaveTaken;
    private int paternityLeaveTaken;

    public UserLeave() {
    }

    public UserLeave(Long userLeaveId, User user, MasterLeave leaveDefault, int sickTaken, int casualTaken,
            int paidLeaveTaken, int unPaidLeaveTaken, int maternityLeaveTaken, int paternityLeaveTaken) {
        this.userLeaveId = userLeaveId;
        this.user = user;
        this.leaveDefault = leaveDefault;
        this.sickTaken = sickTaken;
        this.casualTaken = casualTaken;
        this.paidLeaveTaken = paidLeaveTaken;
        this.unPaidLeaveTaken = unPaidLeaveTaken;
        this.maternityLeaveTaken = maternityLeaveTaken;
        this.paternityLeaveTaken = paternityLeaveTaken;
    }

    public Long getUserLeaveId() {
        return userLeaveId;
    }

    public void setUserLeaveId(Long userLeaveId) {
        this.userLeaveId = userLeaveId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MasterLeave getLeaveDefault() {
        return leaveDefault;
    }

    public void setLeaveDefault(MasterLeave leaveDefault) {
        this.leaveDefault = leaveDefault;
    }

    public int getSickTaken() {
        return sickTaken;
    }

    public void setSickTaken(int sickTaken) {
        this.sickTaken = sickTaken;
    }

    public int getCasualTaken() {
        return casualTaken;
    }

    public void setCasualTaken(int casualTaken) {
        this.casualTaken = casualTaken;
    }

    public int getPaidLeaveTaken() {
        return paidLeaveTaken;
    }

    public void setPaidLeaveTaken(int paidLeaveTaken) {
        this.paidLeaveTaken = paidLeaveTaken;
    }

    public int getUnPaidLeaveTaken() {
        return unPaidLeaveTaken;
    }

    public void setUnPaidLeaveTaken(int unPaidLeaveTaken) {
        this.unPaidLeaveTaken = unPaidLeaveTaken;
    }

    public int getMaternityLeaveTaken() {
        return maternityLeaveTaken;
    }

    public void setMaternityLeaveTaken(int maternityLeaveTaken) {
        this.maternityLeaveTaken = maternityLeaveTaken;
    }

    public int getPaternityLeaveTaken() {
        return paternityLeaveTaken;
    }

    public void setPaternityLeaveTaken(int paternityLeaveTaken) {
        this.paternityLeaveTaken = paternityLeaveTaken;
    }
}

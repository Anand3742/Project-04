package com.rays.pro4.Bean;

import java.util.Date;

/**
 * Student JavaBean encapsulates Doctor attributes.
 * 
 * @author Prabhakar Mandloi
 *
 */
/**
 * 
 */
public class DoctorBean extends BaseBean {

	private String name;
	private Date dob;
	private String mobileNo;
	private String expertise;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	@Override
	public String getkey() {
		return expertise;
	}

	public String getValue() {
		return expertise;
	}

}

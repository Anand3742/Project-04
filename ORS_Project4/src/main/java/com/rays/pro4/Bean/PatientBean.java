package com.rays.pro4.Bean;

import java.util.Date;

public class PatientBean extends BaseBean {
	
	
	private String name;	
	private Date dateofvisit;
	private String mobileno;
	private String decease;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Date getDateofvisit() {
		return dateofvisit;
	}

	public void setDateofvisit(Date dateofvisit) {
		this.dateofvisit = dateofvisit;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getDecease() {
		return decease;
	}

	public void setDecease(String decease) {
		this.decease = decease;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	

}

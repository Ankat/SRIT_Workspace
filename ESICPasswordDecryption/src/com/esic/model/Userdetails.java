package com.esic.model;

public class Userdetails {

	private String username;
	private String password;
	private String createdDate;
	private String updatedDate;
	private String PASSWORD_PREVIOUS_1;
	private String PASSWORD_PREVIOUS_2;
	private String PASSWORD_PREVIOUS_3;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getPASSWORD_PREVIOUS_1() {
		return PASSWORD_PREVIOUS_1;
	}

	public void setPASSWORD_PREVIOUS_1(String pASSWORD_PREVIOUS_1) {
		PASSWORD_PREVIOUS_1 = pASSWORD_PREVIOUS_1;
	}

	public String getPASSWORD_PREVIOUS_2() {
		return PASSWORD_PREVIOUS_2;
	}

	public void setPASSWORD_PREVIOUS_2(String pASSWORD_PREVIOUS_2) {
		PASSWORD_PREVIOUS_2 = pASSWORD_PREVIOUS_2;
	}

	public String getPASSWORD_PREVIOUS_3() {
		return PASSWORD_PREVIOUS_3;
	}

	public void setPASSWORD_PREVIOUS_3(String pASSWORD_PREVIOUS_3) {
		PASSWORD_PREVIOUS_3 = pASSWORD_PREVIOUS_3;
	}

	@Override
	public String toString() {
		return new com.google.gson.Gson().toJson(this);
	}

}

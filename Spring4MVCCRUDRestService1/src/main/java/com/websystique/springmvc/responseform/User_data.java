package com.websystique.springmvc.responseform;

public class User_data {
	private String u_id;
	private String u_name;
	private String phone_number;
	private String price;
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "User_data [u_id=" + u_id + ", u_name=" + u_name + ", phone_number=" + phone_number + ", price=" + price
				+ "]";
	}
	
}

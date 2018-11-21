package com.websystique.springmvc.responseform;

public class res_registor {
	private int status;
	private String error;
	private String privilege;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	@Override
	public String toString() {
		return "res_registor [status=" + status + ", error=" + error + ", privilege=" + privilege + "]";
	}
	
}

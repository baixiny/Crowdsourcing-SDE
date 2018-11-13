package com.websystique.springmvc.responseform;

public class res_task {
	private int flag;
	private msg msg;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public msg getMsg() {
		return msg;
	}
	public void setMsg(msg msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "res_task [flag=" + flag + ", msg=" + msg + "]";
	}
	
}

package com.websystique.springmvc.request;

public class res {
	private Request_task data;

	public Request_task getData() {
		return data;
	}

	public void setData(Request_task data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "res [data=" + data + "]";
	}
	
}

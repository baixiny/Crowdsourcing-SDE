package com.websystique.springmvc.responseform;

public class res_tid {
	private String tname;
	private int tid;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	@Override
	public String toString() {
		return "res_tid [tname=" + tname + ", tid=" + tid + "]";
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}
	
}

package com.websystique.springmvc.responseform;

public class msg {
	private double longitude;
	private double altitude;
	private String location;
	public double getLongitude() {
		return longitude;
	}
	public void setLongititude(double longitude) {
		this.longitude = longitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "msg [longitude=" + longitude + ", altitude=" + altitude + ", location=" + location + "]";
	}
	
	

}

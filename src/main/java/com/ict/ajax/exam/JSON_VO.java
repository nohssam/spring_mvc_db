package com.ict.ajax.exam;

public class JSON_VO {
	private String city; 
	private long totalcount, firstcount,  secondcount ;
	private double firstpersent, secondpersent ;
	public JSON_VO(String city, long totalcount, long firstcount, long secondcount, double firstpersent,
			double secondpersent) {
		super();
		this.city = city;
		this.totalcount = totalcount;
		this.firstcount = firstcount;
		this.secondcount = secondcount;
		this.firstpersent = firstpersent;
		this.secondpersent = secondpersent;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(long totalcount) {
		this.totalcount = totalcount;
	}
	public long getFirstcount() {
		return firstcount;
	}
	public void setFirstcount(long firstcount) {
		this.firstcount = firstcount;
	}
	public long getSecondcount() {
		return secondcount;
	}
	public void setSecondcount(long secondcount) {
		this.secondcount = secondcount;
	}
	public double getFirstpersent() {
		return firstpersent;
	}
	public void setFirstpersent(double firstpersent) {
		this.firstpersent = firstpersent;
	}
	public double getSecondpersent() {
		return secondpersent;
	}
	public void setSecondpersent(double secondpersent) {
		this.secondpersent = secondpersent;
	}
	
	
}

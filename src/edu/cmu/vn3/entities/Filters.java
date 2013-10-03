package edu.cmu.vn3.entities;

public class Filters {
	private long date;
	private String cat; //Need to be changed for Category
	private int radius;
	private int time;
	
	public Filters(long date, String cat, int radius, int time) {
		super();
		this.date = date;
		this.cat = cat;
		this.radius = radius;
		this.time = time;
	}
	public long getDate() {
		return date;
	}
	public String getCat() {
		return cat;
	}
	public int getRadius() {
		return radius;
	}
	public int getTime() {
		return time;
	}
	
	
}

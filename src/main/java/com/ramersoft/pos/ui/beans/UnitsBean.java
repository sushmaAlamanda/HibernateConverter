package com.ramersoft.pos.ui.beans;

public class UnitsBean {
   
	private String uuid;
	private String item_uuid;
	private String portion_uuid;
	private String outlet_uuid;
	private double price;
	private String portion_name;
	private String unit_name;
	private double size;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getItem_uuid() {
		return item_uuid;
	}
	public void setItem_uuid(String item_uuid) {
		this.item_uuid = item_uuid;
	}
	public String getPortion_uuid() {
		return portion_uuid;
	}
	public void setPortion_uuid(String portion_uuid) {
		this.portion_uuid = portion_uuid;
	}
	public String getOutlet_uuid() {
		return outlet_uuid;
	}
	public void setOutlet_uuid(String outlet_uuid) {
		this.outlet_uuid = outlet_uuid;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPortion_name() {
		return portion_name;
	}
	public void setPortion_name(String portion_name) {
		this.portion_name = portion_name;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	

}

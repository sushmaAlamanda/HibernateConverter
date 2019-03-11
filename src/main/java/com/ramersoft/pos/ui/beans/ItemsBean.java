package com.ramersoft.pos.ui.beans;

import org.springframework.stereotype.Component;

@Component
public class ItemsBean {
       private String uuid;
       private String unit_uuid;
       private String item_name;
       private String hsn_uuid;
       private int tax;
       private double discount;
       
       /*for generaterecipt*/
       private String portion_name;
       private String portion_alias;
       private String code;
       private double ItemID;
       private long PortionID;
       private String category_name;
	
       public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public long getPortionID() {
   		return PortionID;
   	}
   	public void setPortionID(long portionID) {
   		PortionID = portionID;
   	}
	public double getItemID() {
		return ItemID;
	}
	public void setItemID(double itemID) {
		ItemID = itemID;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getPortion_name() {
		return portion_name;
	}
	public void setPortion_name(String portion_name) {
		this.portion_name = portion_name;
	}
	public String getPortion_alias() {
		return portion_alias;
	}
	public void setPortion_alias(String portion_alias) {
		this.portion_alias = portion_alias;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUnit_uuid() {
		return unit_uuid;
	}
	public void setUnit_uuid(String unit_uuid) {
		this.unit_uuid = unit_uuid;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getHsn_uuid() {
		return hsn_uuid;
	}
	public void setHsn_uuid(String hsn_uuid) {
		this.hsn_uuid = hsn_uuid;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
       
       
}

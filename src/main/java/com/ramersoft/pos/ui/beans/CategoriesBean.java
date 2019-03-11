package com.ramersoft.pos.ui.beans;
import org.springframework.stereotype.Component;

@Component
public class CategoriesBean {
     
	private String uuid;
	private String category_name;
	private int max_category;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getMax_category() {
		return max_category;
	}
	public void setMax_category(int max_category) {
		this.max_category = max_category;
	}
	
	
}

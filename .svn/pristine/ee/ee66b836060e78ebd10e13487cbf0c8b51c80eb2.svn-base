package com.ramersoft.pos.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ramersoft.pos.enums.ERP_Outlets_Status;
import com.ramersoft.pos.enums.Pos_Items_Status;
import com.ramersoft.pos.enums.pos_items_is_for_outlet_sale;
import com.ramersoft.pos.enums.pos_items_is_price_editable;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity(name = "pos_items")
@Table(name = "pos_items")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class Pos_Items implements Serializable {
    
	@GeneratedValue
	@Column(name = "\"ItemID\"")
	private long ItemID;

	@Column(name = "item_name")
	private String item_name;

	@Column(name = "item_short_name")
	private String item_short_name;

	@Column(name = "item_alias")
	private String item_alias;

	@Column(name = "item_code")
	private String item_code;

	@Column(name = "item_description")
	private String item_description;

	@Column(name = "item_img_name")
	private String item_img_name;

	/*
	 * private pos_items_is_for_outlet_sale is_for_outlet_sale; private
	 * pos_items_is_price_editable is_price_editable;
	 */

	@Column(name = "mrp_price")
	private double mrp_price;

	@Column(name = "selling_price")
	private double selling_price;

	@Column(name = "item_discount")
	private double item_discount;

	@Column(name = "piece_price")
	private double piece_price;

	@Column(name = "total_quantiy")
	private int total_quantiy;
    
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date created_date;

	@Column(name = "created_by")
	private String created_by;
    
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updated_date;

	@Column(name = "updated_by")
	private String updated_by;
	
	@Enumerated(EnumType.STRING)
	@Type( type = "pgsql_enum" )
	@Column(name="status",columnDefinition="pos_items_status")
	private Pos_Items_Status status;
	
	@Id
	@Column(name = "uuid")
	private String uuid;

	@Column(name = "brand_uuid")
	private String brand_uuid;

	@Column(name = "department_uuid")
	private String department_uuid;

	@Column(name = "category_uuid")
	private String category_uuid;

	@Column(name = "hsn_uuid")
	private String hsn_uuid;

	@Column(name = "unit_uuid")
	private String unit_uuid;

	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Pos_Item_Prices.class)
	@JoinColumn(name="item_uuid")
	private Set<Pos_Item_Prices> pos_item_prices = new HashSet(0);
	
	/*@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Pos_Item_Price_Partitions.class)
	@JoinColumn(name="item_uuid")
	private Set<Pos_Item_Price_Partitions> item_pricePartions = new HashSet(0);
		
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Pos_Outlet_Item_Portion_Prices.class)
	@JoinColumn(name="item_uuid")
	private Set<Pos_Outlet_Item_Portion_Prices> itemPortionPrices = new HashSet(0);
		
    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Pos_HsnCodes hsnCodes;*/
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private Pos_Units units;	
	
	
	public long getItemID() {
		return ItemID;
	}

	public void setItemID(long itemID) {
		ItemID = itemID;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_short_name() {
		return item_short_name;
	}

	public void setItem_short_name(String item_short_name) {
		this.item_short_name = item_short_name;
	}

	public String getItem_alias() {
		return item_alias;
	}

	public void setItem_alias(String item_alias) {
		this.item_alias = item_alias;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public String getItem_img_name() {
		return item_img_name;
	}

	public void setItem_img_name(String item_img_name) {
		this.item_img_name = item_img_name;
	}

	public double getMrp_price() {
		return mrp_price;
	}

	public void setMrp_price(double mrp_price) {
		this.mrp_price = mrp_price;
	}

	public double getSelling_price() {
		return selling_price;
	}

	public void setSelling_price(double selling_price) {
		this.selling_price = selling_price;
	}

	public double getItem_discount() {
		return item_discount;
	}

	public void setItem_discount(double item_discount) {
		this.item_discount = item_discount;
	}

	public double getPiece_price() {
		return piece_price;
	}

	public void setPiece_price(double piece_price) {
		this.piece_price = piece_price;
	}

	public int getTotal_quantiy() {
		return total_quantiy;
	}

	public void setTotal_quantiy(int total_quantiy) {
		this.total_quantiy = total_quantiy;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getBrand_uuid() {
		return brand_uuid;
	}

	public void setBrand_uuid(String brand_uuid) {
		this.brand_uuid = brand_uuid;
	}

	public String getDepartment_uuid() {
		return department_uuid;
	}

	public void setDepartment_uuid(String department_uuid) {
		this.department_uuid = department_uuid;
	}

	public String getCategory_uuid() {
		return category_uuid;
	}

	public void setCategory_uuid(String category_uuid) {
		this.category_uuid = category_uuid;
	}

	public String getHsn_uuid() {
		return hsn_uuid;
	}

	public void setHsn_uuid(String hsn_uuid) {
		this.hsn_uuid = hsn_uuid;
	}

	public String getUnit_uuid() {
		return unit_uuid;
	}

	public void setUnit_uuid(String unit_uuid) {
		this.unit_uuid = unit_uuid;
	}

	public Set<Pos_Item_Prices> getPos_item_prices() {
		return pos_item_prices;
	}

	public void setPos_item_prices(Set<Pos_Item_Prices> pos_item_prices) {
		this.pos_item_prices = pos_item_prices;
	}
 
	public Pos_Items_Status getStatus() {
		return status;
	}

	public void setStatus(Pos_Items_Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Pos_Items [ItemID=" + ItemID + ", item_name=" + item_name + ", item_short_name=" + item_short_name
				+ ", item_alias=" + item_alias + ", item_code=" + item_code + ", item_description=" + item_description
				+ ", item_img_name=" + item_img_name + ", mrp_price=" + mrp_price + ", selling_price=" + selling_price
				+ ", item_discount=" + item_discount + ", piece_price=" + piece_price + ", total_quantiy="
				+ total_quantiy + ", created_date=" + created_date + ", created_by=" + created_by + ", updated_date="
				+ updated_date + ", updated_by=" + updated_by + ", uuid=" + uuid + ", brand_uuid=" + brand_uuid
				+ ", department_uuid=" + department_uuid + ", category_uuid=" + category_uuid + ", hsn_uuid=" + hsn_uuid
				+ ", unit_uuid=" + unit_uuid + ", pos_item_prices=" + pos_item_prices + "]";
	}
    
	/*
	public Set<Pos_Item_Price_Partitions> getItem_pricePartions() {
		return item_pricePartions;
	}

	public void setItem_pricePartions(Set<Pos_Item_Price_Partitions> item_pricePartions) {
		this.item_pricePartions = item_pricePartions;
	}

	public Set<Pos_Outlet_Item_Portion_Prices> getItemPortionPrices() {
		return itemPortionPrices;
	}

	public void setItemPortionPrices(Set<Pos_Outlet_Item_Portion_Prices> itemPortionPrices) {
		this.itemPortionPrices = itemPortionPrices;
	}

	public Pos_HsnCodes getHsnCodes() {
		return hsnCodes;
	}

	public void setHsnCodes(Pos_HsnCodes hsnCodes) {
		this.hsnCodes = hsnCodes;
	}
	*/
	
	

}

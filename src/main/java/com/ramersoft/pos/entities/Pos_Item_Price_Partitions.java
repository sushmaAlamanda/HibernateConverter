package com.ramersoft.pos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ramersoft.pos.enums.Pos_Item_Price_Partitions_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity(name = "pos_item_price_partitions")
@Table(name = "pos_item_price_partitions")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Pos_Item_Price_Partitions implements Serializable {

	@GeneratedValue
	@Column(name = "\"ItemPricePartitionID\"")
	private int ItemPricePartitionID;
	
	@Column(name="price")
	private double price;
	
	@Column(name="discount")
	private double discount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="created_by")
	private String created_by;
	
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="updated_by")
	private String updated_by;
	
	@Column(name = "status")
	@Type(type = "pgsql_enum")
	private Pos_Item_Price_Partitions_Status status;
	
	@Column(name="size")
	private double size;
	
	@Id
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="item_uuid")
	private String item_uuid;
	
	@Column(name="itemPrice_uuid")
	private String itemPrice_uuid;
	
	@Column(name="portion_uuid")
	private String portion_uuid;
    
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private Pos_Items items;
	
	
	public int getItemPricePartitionID() {
		return ItemPricePartitionID;
	}

	public void setItemPricePartitionID(int itemPricePartitionID) {
		ItemPricePartitionID = itemPricePartitionID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
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
  
	public Pos_Item_Price_Partitions_Status getStatus() {
		return status;
	}

	public void setStatus(Pos_Item_Price_Partitions_Status status) {
		this.status = status;
	}

	public Pos_Items getItems() {
		return items;
	}

	public void setItems(Pos_Items items) {
		this.items = items;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

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

	public String getItemPrice_uuid() {
		return itemPrice_uuid;
	}

	public void setItemPrice_uuid(String itemPrice_uuid) {
		this.itemPrice_uuid = itemPrice_uuid;
	}

	public String getPortion_uuid() {
		return portion_uuid;
	}

	public void setPortion_uuid(String portion_uuid) {
		this.portion_uuid = portion_uuid;
	}


}

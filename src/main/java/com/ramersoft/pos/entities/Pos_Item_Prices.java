package com.ramersoft.pos.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ramersoft.pos.enums.Pos_Item_Prices_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity(name = "pos_item_prices")
@Table(name = "pos_item_prices")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Pos_Item_Prices {

	@Column(name = "\"ItemPriceID\"")
	private Long ItemPriceID;

	@Column(name = "range_name")
	private String range_name;

	@Column(name = "price")
	private double price;

	/*@Column(name = "pieces_cost")
	private double pieces_cost;*/

	@Column(name = "from_date")
	private Date from_date;

	@Column(name = "to_date")
	private Date to_date;

	@Column(name = "discount")
	private double discount;

	@Column(name = "created_date")
	private Date created_date;

	@Column(name = "created_by")
	private String created_by;

	@Column(name = "updated_date")
	private Date updated_date;

	@Column(name = "updated_by")
	private String updated_by;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@Type(type = "pgsql_enum")
	private Pos_Item_Prices_Status status;

	@Column(name = "size")
	private double size;
    
	@Id
	@Column(name = "uuid")
	private String uuid;

	@Column(name = "item_uuid")
	private String item_uuid;

	@Column(name = "hsn_uuid")
	private String hsn_uuid;
    
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private Pos_Items items;
	
	
	public Long getItemPriceID() {
		return ItemPriceID;
	}

	public void setItemPriceID(Long itemPriceID) {
		ItemPriceID = itemPriceID;
	}

	public String getRange_name() {
		return range_name;
	}

	public void setRange_name(String range_name) {
		this.range_name = range_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/*public double getPieces_cost() {
		return pieces_cost;
	}

	public void setPieces_cost(double pieces_cost) {
		this.pieces_cost = pieces_cost;
	}*/

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public Date getTo_date() {
		return to_date;
	}

	public void setTo_date(Date to_date) {
		this.to_date = to_date;
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

	public Pos_Item_Prices_Status getStatus() {
		return status;
	}

	public void setStatus(Pos_Item_Prices_Status status) {
		this.status = status;
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

	public String getHsn_uuid() {
		return hsn_uuid;
	}

	public void setHsn_uuid(String hsn_uuid) {
		this.hsn_uuid = hsn_uuid;
	}

	public Pos_Items getItems() {
		return items;
	}

	public void setItems(Pos_Items items) {
		this.items = items;
	}

	
}

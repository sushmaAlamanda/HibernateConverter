package com.ramersoft.pos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ramersoft.pos.enums.Pos_Outlet_Item_Portion_Prices_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity(name="pos_outlet_item_portion_prices")
@Table(name="pos_outlet_item_portion_prices")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
  )
public class Pos_Outlet_Item_Portion_Prices implements Serializable {
    
    @GeneratedValue
    @Column(name = "\"OutletItemPortionPriceID\"")
    private int OutletItemPortionPriceID;
   
    @Column(name = "price")
    private double price;
    
    @Column(name = "discount")
    private double discount;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date cretated_date;
        
    @Column(name = "created_by")
    private String created_by;
   
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")   
    private Date updated_date;
 
    @Column(name = "updated_by")
    private String updated_by;
   
   
    @Enumerated(EnumType.STRING)
    @Type( type = "pgsql_enum" )
    @Column(name="status")
    private Pos_Outlet_Item_Portion_Prices_Status status;
    
    
    @Column(name = "upcean")
    private String upcean;
    
    @Id
    @Column(name = "uuid")
    private String uuid;
    
    @Column(name = "outlet_uuid")
    private String outlet_uuid;
    
    @Column(name = "item_uuid")
    private String item_uuid;
    
    @Column(name = "itemPricePartition_uuid")
    private String itemPricePartition_uuid;
    
    @Column(name = "portion_uuid")
    private String portion_uuid;
    
    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private Pos_Items items;

	public int getOutletItemPortionPriceID() {
		return OutletItemPortionPriceID;
	}

	public void setOutletItemPortionPriceID(int outletItemPortionPriceID) {
		OutletItemPortionPriceID = outletItemPortionPriceID;
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

	public Date getCretated_date() {
		return cretated_date;
	}

	public void setCretated_date(Date cretated_date) {
		this.cretated_date = cretated_date;
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

	public String getUpcean() {
		return upcean;
	}

	public void setUpcean(String upcean) {
		this.upcean = upcean;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOutlet_uuid() {
		return outlet_uuid;
	}

	public void setOutlet_uuid(String outlet_uuid) {
		this.outlet_uuid = outlet_uuid;
	}

	public String getItem_uuid() {
		return item_uuid;
	}

	public void setItem_uuid(String item_uuid) {
		this.item_uuid = item_uuid;
	}

	public String getItemPricePartition_uuid() {
		return itemPricePartition_uuid;
	}

	public void setItemPricePartition_uuid(String itemPricePartition_uuid) {
		this.itemPricePartition_uuid = itemPricePartition_uuid;
	}

	public String getPortion_uuid() {
		return portion_uuid;
	}

	public void setPortion_uuid(String portion_uuid) {
		this.portion_uuid = portion_uuid;
	}

	public Pos_Items getItems() {
		return items;
	}

	public void setItems(Pos_Items items) {
		this.items = items;
	}

	public Pos_Outlet_Item_Portion_Prices_Status getStatus() {
		return status;
	}

	public void setStatus(Pos_Outlet_Item_Portion_Prices_Status status) {
		this.status = status;
	}
	
	
          
}
    
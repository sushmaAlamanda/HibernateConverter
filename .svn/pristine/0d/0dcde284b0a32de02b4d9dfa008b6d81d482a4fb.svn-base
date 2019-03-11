package com.ramersoft.pos.entities;

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

import com.ramersoft.pos.enums.Pos_Unit_Portions_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity(name="pos_unit_portions")
@Table(name="pos_unit_portions")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class Pos_Unit_Portions {
     
	 @GeneratedValue
	 @Column(name="\"PortionID\"")
	 private long PortionID;
	 
	 @Column(name="portion_name")
	 private String portion_name;
	 
	 @Column(name="portion_alias")
	 private String portion_alias;
	 
	 @Column(name="portion_weight")
	 private double portion_weight;
	 
	 @Column(name="portion_order")
	 private int portion_order;
	 
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
	 
	 @Enumerated(EnumType.STRING)
	 @Type( type = "pgsql_enum" )
	 @Column(name="status")
	 private Pos_Unit_Portions_Status status;
	 
	 @Id
	 @Column(name="uuid")
	 private String uuid;
	 
	 @Column(name="unit_uuid")
	 private String unit_uuid;
    
	 
	 @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	 private Pos_Units units;	
	 
	 @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Pos_Outlet_Item_Portion_Prices.class)
	 @JoinColumn(name="portion_uuid")
	 private Set<Pos_Outlet_Item_Portion_Prices> itemPortionPrices = new HashSet(0);
	 
	 
	public long getPortionID() {
		return PortionID;
	}

	public void setPortionID(long portionID) {
		PortionID = portionID;
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

	public double getPortion_weight() {
		return portion_weight;
	}

	public void setPortion_weight(double portion_weight) {
		this.portion_weight = portion_weight;
	}

	public int getPortion_order() {
		return portion_order;
	}

	public void setPortion_order(int portion_order) {
		this.portion_order = portion_order;
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

	public Pos_Unit_Portions_Status getStatus() {
		return status;
	}

	public void setStatus(Pos_Unit_Portions_Status status) {
		this.status = status;
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

	public Pos_Units getUnits() {
		return units;
	}

	public void setUnits(Pos_Units units) {
		this.units = units;
	}

	public Set<Pos_Outlet_Item_Portion_Prices> getItemPortionPrices() {
		return itemPortionPrices;
	}

	public void setItemPortionPrices(Set<Pos_Outlet_Item_Portion_Prices> itemPortionPrices) {
		this.itemPortionPrices = itemPortionPrices;
	}
	 	 
}

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ramersoft.pos.enums.Pos_Units_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;


@Entity(name="pos_units")
@Table(name="pos_units")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class Pos_Units {
    
	@GeneratedValue
	@Column(name="\"UnitID\"")
	private long UnitID;
	
	@Column(name="unit_name")
    private String unit_name;
	
	@Column(name="unit_alias")
    private String unit_alias;
	
	@Column(name="unit_description")
    private String unit_description;
	
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
	@Type(type = "pgsql_enum")
	@Column(name="status")
    private Pos_Units_Status status;
	
	@Id
	@Column(name="uuid")
    private String uuid;
    
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Pos_Items.class)
	@JoinColumn(name="unit_uuid")
	private Set<Pos_Items> items = new HashSet(0);
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Pos_Unit_Portions.class)
	@JoinColumn(name="unit_uuid")
	private Set<Pos_Unit_Portions> unitPortions = new HashSet(0);
	
	
	public long getUnitID() {
		return UnitID;
	}

	public void setUnitID(long unitID) {
		UnitID = unitID;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getUnit_alias() {
		return unit_alias;
	}

	public void setUnit_alias(String unit_alias) {
		this.unit_alias = unit_alias;
	}

	public String getUnit_description() {
		return unit_description;
	}

	public void setUnit_description(String unit_description) {
		this.unit_description = unit_description;
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

	public Pos_Units_Status getStatus() {
		return status;
	}

	public void setStatus(Pos_Units_Status status) {
		this.status = status;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Set<Pos_Items> getItems() {
		return items;
	}

	public void setItems(Set<Pos_Items> items) {
		this.items = items;
	}

	public Set<Pos_Unit_Portions> getUnitPortions() {
		return unitPortions;
	}

	public void setUnitPortions(Set<Pos_Unit_Portions> unitPortions) {
		this.unitPortions = unitPortions;
	}
		
	
}

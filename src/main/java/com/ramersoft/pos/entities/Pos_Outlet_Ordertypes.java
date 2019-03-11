package com.ramersoft.pos.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ramersoft.pos.enums.Bom_Amounts_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity(name="pos_outlet_ordertypes")
@Table(name="pos_outlet_ordertypes")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class Pos_Outlet_Ordertypes {
	@GeneratedValue
	@Column(name="ordertype_autoid",insertable=false)
	private long ordertype_autoid;
	
	@Column(name="ordertype_name")
	private String ordertype_name;
	
	@Column(name="bck_color")
	private String bck_color;
	
	@Column(name="added_perc")
	private long added_perc;
	
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
	private Bom_Amounts_Status status;
	
	@Id
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="outlet_uuid")
	private String outlet_uuid;
	
	
	public long getOrdertype_autoid() {
		return ordertype_autoid;
	}

	public void setOrdertype_autoid(long ordertype_autoid) {
		this.ordertype_autoid = ordertype_autoid;
	}

	public String getOrdertype_name() {
		return ordertype_name;
	}

	public void setOrdertype_name(String ordertype_name) {
		this.ordertype_name = ordertype_name;
	}

	public String getBck_color() {
		return bck_color;
	}

	public void setBck_color(String bck_color) {
		this.bck_color = bck_color;
	}

	public long getAdded_perc() {
		return added_perc;
	}

	public void setAdded_perc(long added_perc) {
		this.added_perc = added_perc;
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

	public Bom_Amounts_Status getStatus() {
		return status;
	}

	public void setStatus(Bom_Amounts_Status status) {
		this.status = status;
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
}

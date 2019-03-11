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

import com.ramersoft.pos.enums.Pos_Hsncodes_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;


@Entity(name="pos_hsncodes")
@Table(name="pos_hsncodes")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class Pos_HsnCodes {
    
	@GeneratedValue
	@Column(name="\"HSNID\"")
	private Long HSNID;
	
	@Column(name="name")
    private String name;
	
	@Column(name="code")
    private String code;
	
	@Column(name="tax")
    private int tax;
	
	@Column(name="description")
    private String description;
	
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
    
	/*@Enumerated(EnumType.STRING)
	@Type( type = "pgsql_enum" )
	@Column(name="status")
	private Pos_Hsncodes_Status status;*/
	
	@Id
	@Column(name="uuid")
    private String uuid;
    
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Pos_Items.class)
	@JoinColumn(name="hsn_uuid")
	private Set<Pos_Items> posItems = new HashSet(0);
	
	
	public Long getHSNID() {
		return HSNID;
	}

	public void setHSNID(Long hSNID) {
		HSNID = hSNID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	/*public Pos_Hsncodes_Status getStatus() {
		return status;
	}

	public void setStatus(Pos_Hsncodes_Status status) {
		this.status = status;
	}*/

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	
	@Override
	public String toString() {
		return "Pos_HsnCodes [HSNID=" + HSNID + ", name=" + name + ", code=" + code + ", tax=" + tax + ", description="
				+ description + ", created_date=" + created_date + ", created_by=" + created_by + ", updated_date="
				+ updated_date + ", updated_by=" + updated_by + ", uuid=" + uuid + "]";
	}
    
	
    	
	
}

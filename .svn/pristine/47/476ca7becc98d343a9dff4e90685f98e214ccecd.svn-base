package com.ramersoft.pos.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ramersoft.pos.enums.ERP_Companies_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity(name="erp_companies")
@Table(name="erp_companies")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class ERP_Companies {
	@Id
	@Column(name="\"CompanyID\"")
	private int CompanyID; 
	
	@Column(name="\"Company_name\"")
	private String Company_name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_date;	
	
	@Column(name="created_by")
	private String created_by;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_date;
	
	@Column(name="updated_by")
	private String updated_by;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", columnDefinition="erp_companies_status")
	@Type( type = "pgsql_enum" )
	private ERP_Companies_Status status;		

	@Column(name="uuid")
	private String uuid;

	public int getCompanyID() {
		return CompanyID;
	}

	public void setCompanyID(int companyID) {
		CompanyID = companyID;
	}

	public String getCompany_name() {
		return Company_name;
	}

	public void setCompany_name(String company_name) {
		Company_name = company_name;
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
	
	public String getStatus() {
		return status.toString();
	}

	public void setStatus(ERP_Companies_Status status) {
		this.status = status;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}

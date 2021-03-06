package com.ramersoft.pos.entities;

/**
 * Entity Class for DataBase Table =========>  epr_outlets
 */

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

import com.ramersoft.pos.enums.ERP_Outlets_Status;
/**
 * com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType 
 * This dependency added to map the postgresql status columns with the hibernate 
 * This dependency will provide pgsql_enum with PostgreSQLEnumType.class other wise manually need to add the code for PostgreSQLEnumType.class
 */
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;


@Entity(name="erp_outlets")
@Table(name="erp_outlets")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class ERP_Outlets {
	
    @Id		
	@Column(name="\"OutletID\"")
	private int OutletID; 
	
	@Column(name="b_unit_name")
	private String b_unit_name;
	
	@Column(name="b_unit_short_name")
	private String b_unit_short_name;
	
	@Column(name="address")
	private String address;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_date;	
	
	@Column(name="created_by")
	private String created_by;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_date;
	
	@Column(name="updated_by")
	private String updated_by;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", columnDefinition="erp_outlets_status")
	@Type( type = "pgsql_enum" )
	private ERP_Outlets_Status status;		
	
	public String getStatus() {
		return status.toString();
	}

	public void setStatus(ERP_Outlets_Status status) {
		this.status = status;
	}
	
	
	@Column(name="\"GSTtinnumber\"")
    private String GSTtinnumber;
	
	@Column(name="\"ContactNo\"")
	private String ContactNo;	
	
	@Column(name="uuid")
	private String uuid;
	
	
	@Column(name="company_uuid")
	private String company_uuid;
	
	@Column(name="location_uuid")
	private String location_uuid;
	//End of Db Fields Matching
	
    /*==================Starting of Getter and Setter Methods================*/	
	public int getOutletID() {
		return OutletID;
	}
	public void setOutletID(int outletID) {
		OutletID = outletID;
	}
	public String getB_unit_name() {
		return b_unit_name;
	}
	public void setB_unit_name(String b_unit_name) {
		this.b_unit_name = b_unit_name;
	}
	public String getB_unit_short_name() {
		return b_unit_short_name;
	}
	public void setB_unit_short_name(String b_unit_short_name) {
		this.b_unit_short_name = b_unit_short_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
 
	
	
	public String getGSTtinnumber() {
		return GSTtinnumber;
	}
	public void setGSTtinnumber(String gSTtinnumber) {
		GSTtinnumber = gSTtinnumber;
	}
	public String getContactNo() {
		return ContactNo;
	}
	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCompany_uuid() {
		return company_uuid;
	}
	public void setCompany_uuid(String company_uuid) {
		this.company_uuid = company_uuid;
	}
	public String getLocation_uuid() {
		return location_uuid;
	}
	public void setLocation_uuid(String location_uuid) {
		this.location_uuid = location_uuid;
	}
	
	
	/*=================End of Getter and Setter Methods=================*/
	
	
	
	//To String Method
	@Override
	public String toString() {
		return "ERP_Outlets [OutletID=" + OutletID + ", b_unit_name=" + b_unit_name + ", b_unit_short_name="
				+ b_unit_short_name + ", address=" + address + ", created_date=" + created_date + ", created_by="
				+ created_by + ", updated_date=" + updated_date + ", updated_by=" + updated_by
				+ ", GSTtinnumber=" + GSTtinnumber + ", ContactNo=" + ContactNo + ", uuid=" + uuid + ", company_uuid="
				+ company_uuid + ", location_uuid=" + location_uuid + "]";
	} 
	//end of toString
	
	
}

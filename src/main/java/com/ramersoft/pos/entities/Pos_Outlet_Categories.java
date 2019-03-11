package com.ramersoft.pos.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ramersoft.pos.enums.Pos_Outlet_Categories_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity(name="pos_outlet_categories")
@Table(name="pos_outlet_categories")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )

public class Pos_Outlet_Categories{
	
	@GeneratedValue
	@Column(name="\"OutletCatAutoID\"")
	private Long OutletCatAutoID;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="created_by")
    private String created_by;
	
	@Column(name="updated_date")
    private Date updated_date;
	
	@Column(name="updated_by")
    private String updated_by;
	
	@Enumerated(EnumType.STRING)
	@Type( type = "pgsql_enum" )
	@Column(name="status")
    private Pos_Outlet_Categories_Status status;
   
    @Id
    @Column(name="uuid")
    private String uuid;
    
    @Column(name="outlet_uuid")
    private String outlet_uuid;
        
    @Column(name="category_uuid")
    private String category_uuid;
    
    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Pos_Categories posCategories;
       
	public Long getOutletCatAutoID() {
		return OutletCatAutoID;
	}
	public void setOutletCatAutoID(Long outletCatAutoID) {
		OutletCatAutoID = outletCatAutoID;
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
	public Pos_Outlet_Categories_Status getStatus() {
		return status;
	}
	public void setStatus(Pos_Outlet_Categories_Status status) {
		this.status = status;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@Column(name="outlet_uuid")
	public String getOutlet_uuid() {
		return outlet_uuid;
	}
	public void setOutlet_uuid(String outlet_uuid) {
		this.outlet_uuid = outlet_uuid;
	}
	public String getCategory_uuid() {
		return category_uuid;
	}
	public void setCategory_uuid(String category_uuid) {
		this.category_uuid = category_uuid;
	}	

    public Pos_Categories getPosCategories() {
		return posCategories;
	}
    
    
	public void setPosCategories(Pos_Categories posCategories) {
		this.posCategories = posCategories;
	}
	
	
	@Override
	public String toString() {
		return "Pos_Outlet_Categories [OutletCatAutoID=" + OutletCatAutoID + ", created_date=" + created_date
				+ ", created_by=" + created_by + ", updated_date=" + updated_date + ", updated_by=" + updated_by
				+ ", status=" + status + ", uuid=" + uuid + ", outlet_uuid=" + outlet_uuid + ", category_uuid="
				+ category_uuid + "]";
	}
    

}

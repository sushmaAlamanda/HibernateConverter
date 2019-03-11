package com.ramersoft.pos.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ramersoft.pos.enums.Pos_Categories_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;



@Entity(name="pos_categories")
@Table(name="pos_categories")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class Pos_Categories implements java.io.Serializable{
    
	@GeneratedValue
	@Column(name="\"CategoryID\"")
	private Long CategoryID;
	
	@Column(name="category_name")
	private String category_name;
	
	@Column(name="category_alias")
	private String category_alias;
	
	@Column(name="category_code")
	private String category_code;
	
	@Column(name="\"Parent_CategoryID\"")
	private String Parent_CategoryID;
	
	@Column(name="category_description")
	private String category_description;
	
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
	private Pos_Categories_Status status;
		
	@Id
	@Column(name="uuid", unique = true, nullable = true)
	private String uuid;
    
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Pos_Outlet_Categories.class)
	@JoinColumn(name="category_uuid")
	private Set<Pos_Outlet_Categories> pos_outlet_categories = new HashSet(0);
	
	
	
	/*//----------------------------------mapping with pos_items-----------------------------------------//
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
		name="pos_items",
		joinColumns = @JoinColumn(name="category_uuid")
				//inverseJoinColumns = @JoinColumn(name="ROLE_ID")
	)
    private Set<Pos_Items> posItems;
    //--------------------------------------------------------------------------------------------------//
    */
	
  /*  
	//----------------------------------mapping with pos_outlet_categories-----------------------------------------//
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(
		name="pos_outlet_categories",
		joinColumns = @JoinColumn(name="category_uuid")
		)
	private Set<Pos_Outlet_Categories> posOutlet_Categories;
	//--------------------------------------------------------------------------------------------------//
	*/
	
	
	public Long getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(Long categoryID) {
		CategoryID = categoryID;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_alias() {
		return category_alias;
	}

	public void setCategory_alias(String category_alias) {
		this.category_alias = category_alias;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public String getParent_CategoryID() {
		return Parent_CategoryID;
	}

	public void setParent_CategoryID(String parent_CategoryID) {
		Parent_CategoryID = parent_CategoryID;
	}

	public String getCategory_description() {
		return category_description;
	}

	public void setCategory_description(String category_description) {
		this.category_description = category_description;
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

/*	public Pos_Categories_Status getStatus() {
		return status;
	}

	public void setStatus(Pos_Categories_Status status) {
		this.status = status;
	}
*/
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
   
	
	

	public Set<Pos_Outlet_Categories> getPos_outlet_categories() {
		return pos_outlet_categories;
	}

	public void setPos_outlet_categories(Set<Pos_Outlet_Categories> pos_outlet_categories) {
		this.pos_outlet_categories = pos_outlet_categories;
	}

	@Override
	public String toString() {
		return "Pos_Categories [CategoryID=" + CategoryID + ", category_name=" + category_name + ", category_alias="
				+ category_alias + ", category_code=" + category_code + ", Parent_CategoryID=" + Parent_CategoryID
				+ ", category_description=" + category_description + ", created_date=" + created_date + ", created_by="
				+ created_by + ", updated_date=" + updated_date + ", updated_by=" + updated_by + ", uuid=" + uuid
				+ ", pos_outlet_categories=" + pos_outlet_categories + "]";
	}

	
	
	
    
    
	


	
	
}

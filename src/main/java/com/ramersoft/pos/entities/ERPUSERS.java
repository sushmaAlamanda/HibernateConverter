package com.ramersoft.pos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ramersoft.pos.enums.Erp_Users_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

/*import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;*/
 
@Entity(name="erp_users")
@Table(name="erp_users")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class ERPUSERS implements Serializable{
     
   
    @GeneratedValue
    @Column(name="\"UserID\"")
    private Integer userid;     
	@Column(name ="firstname")
    private String firstname;
	@Column(name ="lastname")
	private String lastname;
	@Column(name ="email")
    private String email;
	@Column(name ="password")
    private String password;
	@Column(name ="address")
    private String address;
	@Column(name ="created_date")
    private Date created_date;
	@Column(name ="created_by")
    private String created_by;
	@Column(name ="updated_date")
    private Date updated_date;
	@Column(name ="updated_by")
    private String updated_by;
   /*
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "erp_users_status")
    @Type( type = "pgsql_enum" )
    private Erp_Users status;
    */
	
  /*  @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "erp_users_status")
    @Type( type = "pgsql_enum" )
    private Erp_Users_Status status;*/
   
    @Id
    private String uuid;
   
     
    public ERPUSERS() {}
    
    
    
public ERPUSERS(Integer userID, String firstname, String lastname, String email, String password, String address,
			String uuid) {
		super();
		userid=userID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.uuid = uuid;
	}



    

	
	public Integer getUserid() {
		return userid;
	}
	
	
	
	public void setUserid(Integer userid) {
		this.userid = userid;
	}



	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		if(null!= lastname)
			this.lastname = lastname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

    
	/*public Date getCreated_date() {
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
*/

	/*public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}


	public String getUpdated_by() {
		return updated_by;
	}*/

	/*public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}*/

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


/*
	public Erp_Users_Status getStatus() {
		return status;
	}



	public void setStatus(Erp_Users_Status status) {
		this.status = status;
	}
*/


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "ERPUSERS [userid=" + userid + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", address=" + address + ", created_date=" + created_date + ", created_by="
				+ created_by + ", updated_date=" + updated_date + ", updated_by=" + updated_by + ", uuid=" + uuid + "]";
	}
   
 	
}



package com.ramersoft.pos.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;


@Entity(name="pos_outlet_creditnotedetails")
@Table(name="pos_outlet_creditnotedetails")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
  )
public class Pos_CreditNoteDetails {
	 @SequenceGenerator(name="identifier", sequenceName="\"pos_outlet_creditnotedetails_creditnoteid_seq\"")
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		@Type(type="java.lang.Long")
		@Column(name="\"creditnoteid\"",insertable=false)
	     private Long creditnoteid;
    
     @Column(name = "\"Creditamount\"")
     private double Creditamount;
    
     @Column(name = "generatedbill")
     private Long generatedbill;
     //private status;
    
     @Column(name = "random_credit")
     private Long random_credit;
    
     @Column(name = "usedbill")
     private Long usedbill;
    
     @Column(name = "created_date")
     private Date created_date;
    
     @Column(name = "created_by")
     private String created_by;
    
     @Column(name = "updated_date")
     private Date updated_date;
    
     @Column(name = "updated_by")
     private String updated_by;
    
     @Id
     @Column(name = "uuid")
     private String uuid;
    
     @Column(name = "outlet_uuid")
     private String outlet_uuid;
    
     @Column(name = "bills_uuid")
     private String bills_uuid;

	public Long getCreditnoteid() {
		return creditnoteid;
	}

	public void setCreditnoteid(Long creditnoteid) {
		this.creditnoteid = creditnoteid;
	}

	public double getCreditamount() {
		return Creditamount;
	}

	public void setCreditamount(double creditamount) {
		Creditamount = creditamount;
	}

	public Long getGeneratedbill() {
		return generatedbill;
	}

	public void setGeneratedbill(Long generatedbill) {
		this.generatedbill = generatedbill;
	}

	public Long getRandom_credit() {
		return random_credit;
	}

	public void setRandom_credit(Long random_credit) {
		this.random_credit = random_credit;
	}

	public Long getUsedbill() {
		return usedbill;
	}

	public void setUsedbill(Long usedbill) {
		this.usedbill = usedbill;
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

	public String getBills_uuid() {
		return bills_uuid;
	}

	public void setBills_uuid(String bills_uuid) {
		this.bills_uuid = bills_uuid;
	}
     
     
     
     
}

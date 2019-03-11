package com.ramersoft.pos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ramersoft.pos.enums.Pos_Voucher_Discounts_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;


@Entity(name = "pos_voucher_discounts")
@Table(name = "pos_voucher_discounts")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Pos_Voucher_Discounts implements Serializable{
	  @Id
	  @SequenceGenerator(name="identifier", sequenceName="\"pos_voucher_discounts_voucher_dis_auto_id_seq\"")
	  @GeneratedValue(strategy = GenerationType.SEQUENCE)
	  @Column(name="voucher_dis_auto_id",insertable=false)
	  private long voucher_dis_auto_id;
	  
	  
	  @Column(name = "min_range")
	  private int min_range;
	  
	  @Column(name = "amount")
	  private int amount;
	  

	  @Temporal(TemporalType.TIMESTAMP)
	  @Column(name = "created_date")
	  private Date created_date;
		
	  @Column(name = "created_by")
	  private String created_by;
		
		
	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name = "updated_date")
	  private Date updated_date;
		
	  @Column(name = "updated_by")
	  private String updated_by;
	  
	  @Column(name = "uuid")
	  private String uuid;
	  
	  @Column(name = "voucher_uuid")
	  private String voucher_uuid;

	  @Enumerated(EnumType.STRING)
	  @Type(type = "pgsql_enum")
	  @Column(name="status", columnDefinition = "pos_voucher_discounts_status")
	  private Pos_Voucher_Discounts_Status status;
	  
	
		
		public String getStatus() {
		return status.toString();
	}


	public void setStatus(Pos_Voucher_Discounts_Status status) {
		this.status = status;
	}


		@Column(name="perc_or_amt")
		private String perc_or_amt;





		public String getPerc_or_amt() {
			return perc_or_amt;
		}


		public void setPerc_or_amt(String perc_or_amt) {
			this.perc_or_amt = perc_or_amt;
		}


		public long getVoucher_dis_auto_id() {
			return voucher_dis_auto_id;
		}


		public void setVoucher_dis_auto_id(long voucher_dis_auto_id) {
			this.voucher_dis_auto_id = voucher_dis_auto_id;
		}


		public int getMin_range() {
			return min_range;
		}


		public void setMin_range(int min_range) {
			this.min_range = min_range;
		}


		public int getAmount() {
			return amount;
		}


		public void setAmount(int amount) {
			this.amount = amount;
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


		public String getVoucher_uuid() {
			return voucher_uuid;
		}


		public void setVoucher_uuid(String voucher_uuid) {
			this.voucher_uuid = voucher_uuid;
		}
		
}

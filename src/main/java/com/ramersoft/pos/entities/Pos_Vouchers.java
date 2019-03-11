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

import com.ramersoft.pos.enums.Pos_vouchers_status;

import com.sun.istack.internal.NotNull;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity(name = "pos_vouchers")
@Table(name = "pos_vouchers")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Pos_Vouchers implements Serializable {
	
	  @Id
	  @SequenceGenerator(name="identifier", sequenceName="\"pos_vouchers_voucher_id_seq\"")
	  @GeneratedValue(strategy = GenerationType.SEQUENCE)
	  @Column(name="voucher_id",insertable=false)
	  private long voucher_id;
	  
	  @Column(name = "voucher_name")
	  private String voucher_name;
	  
	  @Column(name = "voucher_code")
	  private String voucher_code;
		
	  @Column(name = "valid_from", columnDefinition = "timestamp with time zone not null")
	  @NotNull
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date valid_from;
	  
	  @Column(name = "valid_to", columnDefinition = "timestamp with time zone not null")
	  @NotNull
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date valid_to;
	  
	  @Column(name = "noof_vouchers")
	  private int noof_vouchers;
	  
	  @Column(name = "parent_voucher_id")
	  private String parent_voucher_id;
	  
	  @Column(name = "item_ids")
	  private String item_ids;
	  
	  @Column(name = "random_number")
	  private int random_number;
	  

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
	  
	  @Column(name = "outlet_ids")
	  private String outlet_ids;

	  @Enumerated(EnumType.STRING)
	  @Type(type = "pgsql_enum")
	  @Column(name="status", columnDefinition = "pos_vouchers_status")
	  private Pos_vouchers_status status;
	  
	  
	  
		public String getStatus() {
			return status.toString();
		}
	
		public void setStatus(Pos_vouchers_status status) {
			this.status = status;
		}
		
		/*@Enumerated(EnumType.STRING)
		@Type(type = "pgsql_enum")
		@Column(name="voucher_type", columnDefinition = "pos_vouchers_voucher_type")
		private Pos_Vouchers_Type voucher_type;
		
		
		public String getVoucher_type() {
				return voucher_type.toString();
		}
	
		public void setVoucher_type(Pos_Vouchers_Type voucher_type) {
				this.voucher_type = voucher_type;
		}
		
		@Enumerated(EnumType.STRING)
		@Type(type = "pgsql_enum")
		@Column(name="used_status", columnDefinition = "pos_vouchers_used_status")
		private Pos_vouchersUsed_status used_status;
		
		
		public String getUsed_status() {
			return used_status.toString();
		}

		public void setUsed_status(Pos_vouchersUsed_status used_status) {
			this.used_status = used_status;
		}
		@Enumerated(EnumType.STRING)
		@Type(type = "pgsql_enum")
		@Column(name="discount_type", columnDefinition = "pos_vouchers_discount_type")
		private Pos_vouchers_discountType discount_type;

	   public String getDiscount_type() {
			return discount_type.toString();
		}

		public void setDiscount_type(Pos_vouchers_discountType discount_type) {
			this.discount_type = discount_type;
		}
*/
		
		@Column(name="voucher_type")
		private String voucher_type;
		@Column(name="used_status")
		private String used_status;
		@Column(name="discount_type")
		private String discount_type;

    	public String getVoucher_type() {
			return voucher_type;
		}

		public void setVoucher_type(String voucher_type) {
			this.voucher_type = voucher_type;
		}

		public String getUsed_status() {
			return used_status;
		}

		public void setUsed_status(String used_status) {
			this.used_status = used_status;
		}

		public String getDiscount_type() {
			return discount_type;
		}

		public void setDiscount_type(String discount_type) {
			this.discount_type = discount_type;
		}

	public long getVoucher_id() {
		return voucher_id;
	}

	public void setVoucher_id(long voucher_id) {
		this.voucher_id = voucher_id;
	}

	public String getVoucher_name() {
		return voucher_name;
	}

	public void setVoucher_name(String voucher_name) {
		this.voucher_name = voucher_name;
	}

	public String getVoucher_code() {
		return voucher_code;
	}

	public void setVoucher_code(String voucher_code) {
		this.voucher_code = voucher_code;
	}

	public Date getValid_from() {
		return valid_from;
	}

	public void setValid_from(Date valid_from) {
		this.valid_from = valid_from;
	}

	public Date getValid_to() {
		return valid_to;
	}

	public void setValid_to(Date valid_to) {
		this.valid_to = valid_to;
	}

	public int getNoof_vouchers() {
		return noof_vouchers;
	}

	public void setNoof_vouchers(int noof_vouchers) {
		this.noof_vouchers = noof_vouchers;
	}

	public String getParent_voucher_id() {
		return parent_voucher_id;
	}

	public void setParent_voucher_id(String parent_voucher_id) {
		this.parent_voucher_id = parent_voucher_id;
	}

	public String getItem_ids() {
		return item_ids;
	}

	public void setItem_ids(String item_ids) {
		this.item_ids = item_ids;
	}

	public int getRandom_number() {
		return random_number;
	}

	public void setRandom_number(int random_number) {
		this.random_number = random_number;
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

	public String getOutlet_ids() {
		return outlet_ids;
	}

	public void setOutlet_ids(String outlet_ids) {
		this.outlet_ids = outlet_ids;
	}
	
	  
	  
	  
}

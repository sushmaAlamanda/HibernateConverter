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
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.ramersoft.pos.enums.Day_InOut_Transaction_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity(name="pos_outlet_day_in_out_transaction")
@Table(name="pos_outlet_day_in_out_transaction")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )

public class Pos_Outlet_Day_In_Out_Transaction{
    
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	
	//@Id
	@SequenceGenerator(name="identifier", sequenceName="\"pos_outlet_day_in_out_transaction_Day_In_Out_AutoID_seq\"")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Type(type="java.lang.Long")
	@Column(name="\"Day_In_Out_AutoID\"",insertable=false)
	private Long Day_In_Out_AutoID;
	
	@Column(name="counter_number")	
	private int counter_number;
    
	@Column(name="opening_balance")	
	private double opening_balance;
	
	@DateTimeFormat(pattern="HH:mm:ss" )
	@Temporal(TemporalType.TIME)
	@Column(name="dayend_time")	
	private Date dayend_time;

	@Column(name="cash_amount")
	private double cash_amount;
	
	@Column(name="card_amount")
	private double card_amount;
	
	@Column(name="gift_voucher_amount")
	private double gift_voucher_amount;
	
	@Column(name="coupon_amount")
	private double coupon_amount;
	
	@Column(name="cash_in")
	private double cash_in;
	
	@Column(name="cash_out")
	private double cash_out;
	
	@Column(name="cash_sales_return")
	private double cash_sales_return;
	
	@Column(name="coupon_sales_return")
	private double coupon_sales_return;
	
	@Column(name="credit_sales_amount")
	private double credit_sales_amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="created_by")
	private String created_by;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	@UpdateTimestamp
	private Date updated_date;
	
	@Column(name="updated_by")
	private String updated_by;
	
	
	/*@Type( type = "pgsql_enum" )
	@Column(name="status",columnDefinition = "pos_outlet_day_in_out_transaction_status")
	@Enumerated(EnumType.STRING)
	private Day_InOut_Transaction_Status status;*/
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dayin_datetime")
	private Date dayin_datetime;
	
	@Column(name="ewallet_amount")
	private double ewallet_amount;
	
	@Id
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="outlet_uuid")
	private String outlet_uuid;
	
	@Column(name="user_uuid")
	private String user_uuid;
    
	
	
	
	
	
	
	
	
	public Long getDay_In_Out_AutoID() {
		return Day_In_Out_AutoID;
	}

	public void setDay_In_Out_AutoID(Long Day_In_Out_AutoID) {
		//if(null!= Day_In_Out_AutoID)
			this.Day_In_Out_AutoID = Day_In_Out_AutoID;
	}

	public int getCounter_number() {
		return counter_number;
	}

	public void setCounter_number(int counter_number) {
		this.counter_number = counter_number;
	}

	public double getOpening_balance() {
		return opening_balance;
	}

	public void setOpening_balance(double opening_balance) {
		this.opening_balance = opening_balance;
	}

	public Date getDayend_time() {
		return dayend_time;
	}

	public void setDayend_time(Date dayend_time) {
		this.dayend_time = dayend_time;
	}

	public double getCash_amount() {
		return cash_amount;
	}

	public void setCash_amount(double cash_amount) {
		this.cash_amount = cash_amount;
	}

	public double getCard_amount() {
		return card_amount;
	}

	public void setCard_amount(double card_amount) {
		this.card_amount = card_amount;
	}

	public double getGift_voucher_amount() {
		return gift_voucher_amount;
	}

	public void setGift_voucher_amount(double gift_voucher_amount) {
		this.gift_voucher_amount = gift_voucher_amount;
	}

	public double getCoupon_amount() {
		return coupon_amount;
	}

	public void setCoupon_amount(double coupon_amount) {
		this.coupon_amount = coupon_amount;
	}

	public double getCash_in() {
		return cash_in;
	}

	public void setCash_in(double cash_in) {
		this.cash_in = cash_in;
	}

	public double getCash_out() {
		return cash_out;
	}

	public void setCash_out(double cash_out) {
		this.cash_out = cash_out;
	}

	public double getCash_sales_return() {
		return cash_sales_return;
	}

	public void setCash_sales_return(double cash_sales_return) {
		this.cash_sales_return = cash_sales_return;
	}

	public double getCoupon_sales_return() {
		return coupon_sales_return;
	}

	public void setCoupon_sales_return(double coupon_sales_return) {
		this.coupon_sales_return = coupon_sales_return;
	}

	public double getCredit_sales_amount() {
		return credit_sales_amount;
	}

	public void setCredit_sales_amount(double credit_sales_amount) {
		this.credit_sales_amount = credit_sales_amount;
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
	
	/*public Day_InOut_Transaction_Status getStatus() {
		return status;
	}

	public void setStatus(Day_InOut_Transaction_Status status) {
		this.status = status;
	}
   */
	public double getEwallet_amount() {
		return ewallet_amount;
	}

	public void setEwallet_amount(double ewallet_amount) {
		this.ewallet_amount = ewallet_amount;
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

	public String getUser_uuid() {
		return user_uuid;
	}

	public void setUser_uuid(String user_uuid) {
		this.user_uuid = user_uuid;
	}
    
	public Date getDayin_datetime() {
		return dayin_datetime;
	}

	public void setDayin_datetime(Date dayin_datetime) {
		this.dayin_datetime = dayin_datetime;
	}
    
		
}

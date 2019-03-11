package com.ramersoft.pos.entities;

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

import com.ramersoft.pos.enums.Bom_Amounts_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity(name="pos_outlet_bom_amounts")
@Table(name="pos_outlet_bom_amounts")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class Pos_Outlet_Bom_Amounts {
    
	@SequenceGenerator(name="identifier", sequenceName="\"pos_outlet_bom_amounts_Bill_AmountAutoID_seq\"")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="\"Bill_AmountAutoID\"",insertable=false)
	private long Bill_AmountAutoID;
	
	@Column(name="\"Billno\"")
	private long Billno;
	
	@Column(name="\"BillAmountID\"")
	private long BillAmountID;
	
	@Column(name="total_items")
	private int total_items;
	
	@Column(name="total_price_bd")
	private double total_price_bd;
	
	@Column(name="items_discount_amt")
	private double items_discount_amt;
	
	@Column(name="bill_discount_perc")
	private double bill_discount_perc;
	
	@Column(name="bill_discount_amt")
	private double bill_discount_amt;
	
	@Column(name="total_discount_amt")
	private double total_discount_amt;
	
	@Column(name="total_price_bg")
	private double total_price_bg;
	
	@Column(name="total_gst_amt")
	private double total_gst_amt;
	
	@Column(name="total_cgst_amt")
	private double total_cgst_amt;
	
	@Column(name="total_sgst_amt")
	private double total_sgst_amt;
	
	@Column(name="total_igst_amt")
	private double total_igst_amt;
	
	@Column(name="total_price")
	private double total_price;
	
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
	@Column(name="status", columnDefinition="pos_outlet_bom_amounts_status")
	private Bom_Amounts_Status status;
	
	
	@Id
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="outlet_uuid")
	private String outlet_uuid;
	
	@Column(name="bills_uuid")
	private String bills_uuid;

	
	public long getBill_AmountAutoID() {
		return Bill_AmountAutoID;
	}

	public void setBill_AmountAutoID(long bill_AmountAutoID) {
		Bill_AmountAutoID = bill_AmountAutoID;
	}

	public long getBillno() {
		return Billno;
	}

	public void setBillno(long billno) {
		Billno = billno;
	}

	public long getBillAmountID() {
		return BillAmountID;
	}

	public void setBillAmountID(long billAmountID) {
		BillAmountID = billAmountID;
	}

	public int getTotal_items() {
		return total_items;
	}

	public void setTotal_items(int total_items) {
		this.total_items = total_items;
	}

	public double getTotal_price_bd() {
		return total_price_bd;
	}

	public void setTotal_price_bd(double total_price_bd) {
		this.total_price_bd = total_price_bd;
	}

	public double getItems_discount_amt() {
		return items_discount_amt;
	}

	public void setItems_discount_amt(double items_discount_amt) {
		this.items_discount_amt = items_discount_amt;
	}

	public double getBill_discount_perc() {
		return bill_discount_perc;
	}

	public void setBill_discount_perc(double bill_discount_perc) {
		this.bill_discount_perc = bill_discount_perc;
	}

	public double getBill_discount_amt() {
		return bill_discount_amt;
	}

	public void setBill_discount_amt(double bill_discount_amt) {
		this.bill_discount_amt = bill_discount_amt;
	}

	public double getTotal_discount_amt() {
		return total_discount_amt;
	}

	public void setTotal_discount_amt(double total_discount_amt) {
		this.total_discount_amt = total_discount_amt;
	}

	public double getTotal_price_bg() {
		return total_price_bg;
	}

	public void setTotal_price_bg(double total_price_bg) {
		this.total_price_bg = total_price_bg;
	}

	public double getTotal_gst_amt() {
		return total_gst_amt;
	}

	public void setTotal_gst_amt(double total_gst_amt) {
		this.total_gst_amt = total_gst_amt;
	}

	public double getTotal_cgst_amt() {
		return total_cgst_amt;
	}

	public void setTotal_cgst_amt(double total_cgst_amt) {
		this.total_cgst_amt = total_cgst_amt;
	}

	public double getTotal_sgst_amt() {
		return total_sgst_amt;
	}

	public void setTotal_sgst_amt(double total_sgst_amt) {
		this.total_sgst_amt = total_sgst_amt;
	}

	public double getTotal_igst_amt() {
		return total_igst_amt;
	}

	public void setTotal_igst_amt(double total_igst_amt) {
		this.total_igst_amt = total_igst_amt;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
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

	public Bom_Amounts_Status getStatus() {
		return status;
	}

	public void setStatus(Bom_Amounts_Status status) {
		this.status = status;
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

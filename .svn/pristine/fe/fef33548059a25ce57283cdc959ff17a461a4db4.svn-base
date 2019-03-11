package com.ramersoft.pos.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name="pos_outlet_pricechanges")
@Table(name="pos_outlet_pricechanges")
public class Pos_Outlet_PriceChange {
	
	@SequenceGenerator(name="identifier", sequenceName="\"pos_outlet_pricechanges_Price_AutoID_seq\"")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="\"Price_AutoID\"",insertable=false)
	private Long Price_AutoID;
	
	@Column(name="\"Old_priceval\"")
	private Double Old_priceval;
	
	@Column(name="\"New_priceval\"")
	private Double New_priceval;
	
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
	//private status;
	
	@Column(name="\"Billno\"")
	private Long Billno;
	
	@Column(name="\"Adt_perc_discval\"")
	private Double Adt_perc_discval;
	
	@Column(name="\"Item_perc_discval\"")
	private Double Item_perc_discval;
	
	@Column(name="\"Item_amt_discval\"")
	private Double Item_amt_discval;
	
	@Column(name="\"Adt_amt_discval\"")
	private Double Adt_amt_discval;
	
	@Id
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="outlet_uuid")
	private String outlet_uuid;
	
	@Column(name="portion_uuid")
	private String portion_uuid;
	
	@Column(name="item_uuid")
	private String item_uuid;
	
	@Column(name="user_uuid")
	private String user_uuid;
	
	@Column(name="bills_uuid")
	private String bills_uuid;

	public Long getPrice_AutoID() {
		return Price_AutoID;
	}

	public void setPrice_AutoID(Long price_AutoID) {
		Price_AutoID = price_AutoID;
	}

	public Double getOld_priceval() {
		return Old_priceval;
	}

	public void setOld_priceval(Double old_priceval) {
		Old_priceval = old_priceval;
	}

	public Double getNew_priceval() {
		return New_priceval;
	}

	public void setNew_priceval(Double new_priceval) {
		New_priceval = new_priceval;
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

	public Long getBillno() {
		return Billno;
	}

	public void setBillno(Long billno) {
		Billno = billno;
	}

	public Double getAdt_perc_discval() {
		return Adt_perc_discval;
	}

	public void setAdt_perc_discval(Double adt_perc_discval) {
		Adt_perc_discval = adt_perc_discval;
	}

	public Double getItem_perc_discval() {
		return Item_perc_discval;
	}

	public void setItem_perc_discval(Double item_perc_discval) {
		Item_perc_discval = item_perc_discval;
	}

	public Double getItem_amt_discval() {
		return Item_amt_discval;
	}

	public void setItem_amt_discval(Double item_amt_discval) {
		Item_amt_discval = item_amt_discval;
	}

	public Double getAdt_amt_discval() {
		return Adt_amt_discval;
	}

	public void setAdt_amt_discval(Double adt_amt_discval) {
		Adt_amt_discval = adt_amt_discval;
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

	public String getPortion_uuid() {
		return portion_uuid;
	}

	public void setPortion_uuid(String portion_uuid) {
		this.portion_uuid = portion_uuid;
	}

	public String getItem_uuid() {
		return item_uuid;
	}

	public void setItem_uuid(String item_uuid) {
		this.item_uuid = item_uuid;
	}

	public String getUser_uuid() {
		return user_uuid;
	}

	public void setUser_uuid(String user_uuid) {
		this.user_uuid = user_uuid;
	}

	public String getBills_uuid() {
		return bills_uuid;
	}

	public void setBills_uuid(String bills_uuid) {
		this.bills_uuid = bills_uuid;
	}

	@Override
	public String toString() {
		return "Pos_Outlet_PriceChange [Price_AutoID=" + Price_AutoID + ", Old_priceval=" + Old_priceval
				+ ", New_priceval=" + New_priceval + ", created_date=" + created_date + ", created_by=" + created_by
				+ ", updated_date=" + updated_date + ", updated_by=" + updated_by + ", Billno=" + Billno
				+ ", Adt_perc_discval=" + Adt_perc_discval + ", Item_perc_discval=" + Item_perc_discval
				+ ", Item_amt_discval=" + Item_amt_discval + ", Adt_amt_discval=" + Adt_amt_discval + ", uuid=" + uuid
				+ ", outlet_uuid=" + outlet_uuid + ", portion_uuid=" + portion_uuid + ", item_uuid=" + item_uuid
				+ ", user_uuid=" + user_uuid + ", bills_uuid=" + bills_uuid + "]";
	}
	
	
	
}

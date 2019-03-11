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

import com.ramersoft.pos.enums.Bom_Items_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;


@Entity(name="pos_outlet_bom_items")
@Table(name="pos_outlet_bom_items")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class Pos_Outlet_Bom_Items {
    
	@SequenceGenerator(name="identifier", sequenceName="\"pos_outlet_bom_items_Bill_ItemsAutoID_seq\"")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Type(type="java.lang.Long")
	@Column(name="\"Bill_ItemsAutoID\"",insertable=false)
	private long Bill_ItemsAutoID;
	
	@Column(name="\"Billno\"")
	private long Billno;
	
	@Column(name="\"BOM_ID\"")
	private long BOM_ID;
	
	@Column(name="quantity")
	private double quantity;
	
	@Column(name="original_price")
	private double original_price;
	
	@Column(name="price_bd")
	private double price_bd;
	
	@Column(name="original_discount_perc")
	private double original_discount_perc;
	
	@Column(name="original_discount_amt")
	private double original_discount_amt;
	
	@Column(name="discount_perc")
	private double discount_perc;
	
	@Column(name="discount_amt")
	private double discount_amt;
	
	@Column(name="adt_discount_perc")
	private double adt_discount_perc;
	
	@Column(name="adt_discount_amt")
	private double adt_discount_amt;
	
	@Column(name="price_bg")
	private double price_bg;
	
	@Column(name="\"GST_perc\"")
	private double GST_perc;
	
	@Column(name="\"GST_amt\"")
	private double GST_amt;
	
	@Column(name="\"SGST_perc\"")
	private double SGST_perc;
	
	@Column(name="\"SGST_amt\"")
	private double SGST_amt;
	
	@Column(name="\"CGST_perc\"")
	private double CGST_perc;
	
	@Column(name="\"CGST_amt\"")
	private double CGST_amt;
	
	@Column(name="\"IGST_perc\"")
	private double IGST_perc;
	
	@Column(name="\"IGST_amt\"")
	private double IGST_amt;
	
	@Column(name="price")
	private double price;
	
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
	@Column(name="status", columnDefinition="pos_outlet_bom_items_status")
	private Bom_Items_Status status;
	
	@Column(name="size")
	private double size;
	
	
	@Id
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="outlet_uuid")
	private String outlet_uuid;
	
	@Column(name="item_uuid")
	private String item_uuid;
	
	@Column(name="portion_uuid")
	private String portion_uuid;
	
	@Column(name="hsn_uuid")
	private String hsn_uuid;
	
	@Column(name="bills_uuid")
	private String bills_uuid;
	
	
	
	public long getBill_ItemsAutoID() {
		return Bill_ItemsAutoID;
	}
	public void setBill_ItemsAutoID(long bill_ItemsAutoID) {
		Bill_ItemsAutoID = bill_ItemsAutoID;
	}
	public long getBillno() {
		return Billno;
	}
	public void setBillno(long billno) {
		Billno = billno;
	}
	public long getBOM_ID() {
		return BOM_ID;
	}
	public void setBOM_ID(long bOM_ID) {
		BOM_ID = bOM_ID;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(double original_price) {
		this.original_price = original_price;
	}
	public double getPrice_bd() {
		return price_bd;
	}
	public void setPrice_bd(double price_bd) {
		this.price_bd = price_bd;
	}
	public double getOriginal_discount_perc() {
		return original_discount_perc;
	}
	public void setOriginal_discount_perc(double original_discount_perc) {
		this.original_discount_perc = original_discount_perc;
	}
	public double getOriginal_discount_amt() {
		return original_discount_amt;
	}
	public void setOriginal_discount_amt(double original_discount_amt) {
		this.original_discount_amt = original_discount_amt;
	}
	public double getDiscount_perc() {
		return discount_perc;
	}
	public void setDiscount_perc(double discount_perc) {
		this.discount_perc = discount_perc;
	}
	public double getDiscount_amt() {
		return discount_amt;
	}
	public void setDiscount_amt(double discount_amt) {
		this.discount_amt = discount_amt;
	}
	public double getAdt_discount_perc() {
		return adt_discount_perc;
	}
	public void setAdt_discount_perc(double adt_discount_perc) {
		this.adt_discount_perc = adt_discount_perc;
	}
	public double getAdt_discount_amt() {
		return adt_discount_amt;
	}
	public void setAdt_discount_amt(double adt_discount_amt) {
		this.adt_discount_amt = adt_discount_amt;
	}
	public double getPrice_bg() {
		return price_bg;
	}
	public void setPrice_bg(double price_bg) {
		this.price_bg = price_bg;
	}
	public double getGST_perc() {
		return GST_perc;
	}
	public void setGST_perc(double gST_perc) {
		GST_perc = gST_perc;
	}
	public double getGST_amt() {
		return GST_amt;
	}
	public void setGST_amt(double gST_amt) {
		GST_amt = gST_amt;
	}
	public double getSGST_perc() {
		return SGST_perc;
	}
	public void setSGST_perc(double sGST_perc) {
		SGST_perc = sGST_perc;
	}
	public double getSGST_amt() {
		return SGST_amt;
	}
	public void setSGST_amt(double sGST_amt) {
		SGST_amt = sGST_amt;
	}
	public double getCGST_perc() {
		return CGST_perc;
	}
	public void setCGST_perc(double cGST_perc) {
		CGST_perc = cGST_perc;
	}
	public double getCGST_amt() {
		return CGST_amt;
	}
	public void setCGST_amt(double cGST_amt) {
		CGST_amt = cGST_amt;
	}
	public double getIGST_perc() {
		return IGST_perc;
	}
	public void setIGST_perc(double iGST_perc) {
		IGST_perc = iGST_perc;
	}
	public double getIGST_amt() {
		return IGST_amt;
	}
	public void setIGST_amt(double iGST_amt) {
		IGST_amt = iGST_amt;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public Bom_Items_Status getStatus() {
		return status;
	}
	public void setStatus(Bom_Items_Status status) {
		this.status = status;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
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
	public String getItem_uuid() {
		return item_uuid;
	}
	public void setItem_uuid(String item_uuid) {
		this.item_uuid = item_uuid;
	}
	public String getPortion_uuid() {
		return portion_uuid;
	}
	public void setPortion_uuid(String portion_uuid) {
		this.portion_uuid = portion_uuid;
	}
	public String getHsn_uuid() {
		return hsn_uuid;
	}
	public void setHsn_uuid(String hsn_uuid) {
		this.hsn_uuid = hsn_uuid;
	}
	public String getBills_uuid() {
		return bills_uuid;
	}
	public void setBills_uuid(String bills_uuid) {
		this.bills_uuid = bills_uuid;
	}
	
	@Override
	public String toString() {
		return "Pos_Outlet_Bom_Invoice [Bill_itemsAutoID=" + Bill_ItemsAutoID + ", Billno=" + Billno
				+ ", BOM_ID=" + BOM_ID + ", quantity=" + quantity + ", original_price=" + original_price
				+ ", price_bd=" + price_bd + ", original_discount_perc=" + original_discount_perc + ", original_discount_amt=" + original_discount_amt
				+ ", discount_perc=" + discount_perc + ", discount_amt=" + discount_amt + ", adt_discount_perc=" + adt_discount_perc
				+ ", adt_discount_amt=" + adt_discount_amt + ", price_bg=" + price_bg + ", GST_perc=" + GST_perc + ", GST_amt="
				+ GST_amt + ", SGST_perc=" + SGST_perc + ", SGST_amt=" + SGST_amt + ", CGST_perc=" + CGST_perc + ", CGST_amt="
						+ CGST_amt + ", IGST_perc=" + IGST_perc +", IGST_amt=" + IGST_amt +"]";
	}
		
}

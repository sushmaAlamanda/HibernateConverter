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

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ramersoft.pos.enums.PreOrders_OrderStatus;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity(name="pos_outlet_preorders")
@Table(name="pos_outlet_preorders")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class Pos_Outlet_PreOrders {
	
	
	@SequenceGenerator(name="identifier", sequenceName="\"pos_outlet_preorders_Pre_OrderAutoId_seq\"")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="\"Pre_OrderAutoId\"",insertable=false)
	private long Pre_OrderAutoId;
	
	@Column(name="created_by")
	private String created_by;
	
	@Column(name="transaction_type")
	private String transaction_type;
	
	@Column(name="\"BillNo\"")
	private long BillNo;
	
	@Column(name="\"TransactionID\"")
	private long TransactionID;
	
	@Column(name="\"Customer_name\"")
	private String Customer_name;
	
	@Column(name="\"Phonenumber\"")
	private String Phonenumber;
	
	@Column(name="created_datetime")
	private Date created_datetime;
	
	@Column(name="\"Delivery_datetime\"")
	private Date Delivery_datetime;
	
	@Column(name="\"Total_Amount\"")
	private double Total_Amount;
	
	@Column(name="\"Advance\"")
	private double Advance;
	
	@Column(name="\"Updated_date\"")
	private Date Updated_date;
	
	@Column(name="\"Updated_by\"")
	private String Updated_by;
	//private status;
	
	@Enumerated(EnumType.STRING)
	@Type( type = "pgsql_enum" )
	@Column(name="\"Orderstatus\"")
	private PreOrders_OrderStatus Orderstatus;
	
	public PreOrders_OrderStatus getOrderstatus() {
		return Orderstatus;
	}
	public void setOrderstatus(PreOrders_OrderStatus orderstatus) {
		Orderstatus = orderstatus;
	}
	
	@Column(name="cust_address")
	private String cust_address;
	
	@Column(name="\"Addons\"")
	private int Addons;
	
   //private Inventorystatus;
	
	@Column(name="\"CreditorsFlag\"")
	private int CreditorsFlag;
	
	@Id
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="outlet_uuid")
	private String outlet_uuid;
	
	@Column(name="bills_uuid")
	private String bills_uuid;
	
	
	public long getPre_OrderAutoId() {
		return Pre_OrderAutoId;
	}
	public void setPre_OrderAutoId(long pre_OrderAutoId) {
		Pre_OrderAutoId = pre_OrderAutoId;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public long getBillNo() {
		return BillNo;
	}
	public void setBillNo(long billNo) {
		BillNo = billNo;
	}
	public long getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(long transactionID) {
		TransactionID = transactionID;
	}
	public String getCustomer_name() {
		return Customer_name;
	}
	public void setCustomer_name(String customer_name) {
		Customer_name = customer_name;
	}
	public String getPhonenumber() {
		return Phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		Phonenumber = phonenumber;
	}
	public Date getCreated_datetime() {
		return created_datetime;
	}
	public void setCreated_datetime(Date created_datetime) {
		this.created_datetime = created_datetime;
	}
	public Date getDelivery_datetime() {
		return Delivery_datetime;
	}
	public void setDelivery_datetime(Date delivery_datetime) {
		Delivery_datetime = delivery_datetime;
	}
	public double getTotal_Amount() {
		return Total_Amount;
	}
	public void setTotal_Amount(double total_Amount) {
		Total_Amount = total_Amount;
	}
	public double getAdvance() {
		return Advance;
	}
	public void setAdvance(double advance) {
		Advance = advance;
	}
	public Date getUpdated_date() {
		return Updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		Updated_date = updated_date;
	}
	public String getUpdated_by() {
		return Updated_by;
	}
	public void setUpdated_by(String updated_by) {
		Updated_by = updated_by;
	}
	public String getCust_address() {
		return cust_address;
	}
	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}
	public int getAddons() {
		return Addons;
	}
	public void setAddons(int addons) {
		Addons = addons;
	}
	public int getCreditorsFlag() {
		return CreditorsFlag;
	}
	public void setCreditorsFlag(int creditorsFlag) {
		CreditorsFlag = creditorsFlag;
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

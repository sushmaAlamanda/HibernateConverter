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

import com.ramersoft.pos.enums.BTOBOrders_status;
import com.ramersoft.pos.enums.BTOB_status;
import com.ramersoft.pos.enums.Bom_Invoice_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;


@Entity(name="pos_outlet_btoborders")
@Table(name="pos_outlet_btoborders")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class Pos_Outlet_BTOBOrders {
	
	/*@GeneratedValue
	@Column(name="btob_orderautoid")
	private long btob_orderautoid;*/
	
	@SequenceGenerator(name="identifier", sequenceName="pos_outlet_btoborders_btob_orderautoid_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="btob_orderautoid",insertable=false)
	private long btob_orderautoid;
	
	@Column(name="customer_name")
	private String customer_name;
	
	@Column(name="phonenumber")
	private String phonenumber;
	
	@Column(name="email_id")
	private String email_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="delivery_datetime")
	private Date delivery_datetime;
	
	@Column(name="total_amount")	
	private double total_amount;
	
	@Column(name="advance")
	private double advance;
	
	@Column(name="particulars")
	private String particulars;
	
	/*@Enumerated(EnumType.STRING)
	@Type( type = "pgsql_enum" )
	@Column(name="status",columnDefinition="pos_outlet_btob_status")
	private BTOB_status status;
	
	
	public String getStatus() {
		return status.toString();
	}

	public void setStatus(BTOB_status status) {
		this.status = status;
	}
	*/
	@Column(name="transaction_type")
	private String transaction_type;
	
	@Column(name="\"Billno\"")
	private long Billno;
	
	@Enumerated(EnumType.STRING)
	@Type( type = "pgsql_enum" )
	@Column(name="orderstatus")
	private BTOBOrders_status orderstatus;
	
	
		
	public String getOrderstatus() {
		return orderstatus.toString();
	}

	public void setOrderstatus(BTOBOrders_status orderstatus) {
		this.orderstatus = orderstatus;
	}
	
	@Column(name="\"TransactionID\"")
	private long TransactionID;
	
	@Column(name="gsttinnumber")
	private String gsttinnumber;
	
	@Column(name="created_by")
	private String created_by;
    
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_datetime")
	private Date created_datetime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updated_date;
	
	@Column(name="updated_by")
	private String updated_by;
	
	@Column(name="cust_address")
	private String cust_address;
	
	@Column(name="\"CreditorsFlag\"")
	private int CreditorsFlag;
	
	@Id
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="outlet_uuid")
	private String outlet_uuid;
	
	@Column(name="bills_uuid")
	private String bills_uuid;
    	
	public long getBtob_orderautoid() {
		return btob_orderautoid;
	}

	public void setBtob_orderautoid(long btob_orderautoid) {
		this.btob_orderautoid = btob_orderautoid;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public Date getDelivery_datetime() {
		return delivery_datetime;
	}

	public void setDelivery_datetime(Date delivery_datetime) {
		this.delivery_datetime = delivery_datetime;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public double getAdvance() {
		return advance;
	}

	public void setAdvance(double advance) {
		this.advance = advance;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public long getBillno() {
		return Billno;
	}

	public void setBillno(long billno) {
		Billno = billno;
	}


	public long getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(long transactionID) {
		TransactionID = transactionID;
	}

	public String getGsttinnumber() {
		return gsttinnumber;
	}

	public void setGsttinnumber(String gsttinnumber) {
		this.gsttinnumber = gsttinnumber;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_datetime() {
		return created_datetime;
	}

	public void setCreated_datetime(Date created_datetime) {
		this.created_datetime = created_datetime;
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

	public String getCust_address() {
		return cust_address;
	}

	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
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

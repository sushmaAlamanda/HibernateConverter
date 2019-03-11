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

import com.ramersoft.pos.enums.Bom_Invoice_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;



@Entity(name="pos_outlet_bom_invoice")
@Table(name="pos_outlet_bom_invoice")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class Pos_Outlet_Bom_Invoice {
    
	@SequenceGenerator(name="identifier", sequenceName="\"pos_outlet_bom_invoice_Bill_InvoiceAutoID_seq\"")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="\"Bill_InvoiceAutoID\"",insertable=false)
	private long Bill_InvoiceAutoID;
	
	@Column(name="\"Billno\"")
	private long Billno;
	
	@Column(name="\"InvoiceID\"")
	private long InvoiceID;
	
	@Column(name="\"TransactionID\"")
	private long TransactionID;
	
	@Column(name="bill_amt")
	private double bill_amt;
	
	@Column(name="paid_amt")
	private double paid_amt;
	
	@Column(name="balance_amt")
	private double balance_amt;
	
	@Column(name="ip_address")
	private String ip_address;
	
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

	
/*  @Enumerated(EnumType.STRING)
	@Type( type = "pgsql_enum" )
	@Column(name="status",columnDefinition="pos_outlet_bom_invoice_status")
	private Bom_Invoice_Status status;
	
     
	public String getStatus() {
		return status.toString();
	}

	
	public void setStatus(Bom_Invoice_Status status) {
		this.status = status;
	}
	*/
	
	
	
	@Column(name="oldbillnumber",nullable = true)
	private Long oldbillnumber;
	
	@Id
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="outlet_uuid")
	private String outlet_uuid;
	
	@Column(name="bills_uuid")
	private String bills_uuid;

	public long getBill_InvoiceAutoID() {
		return Bill_InvoiceAutoID;
	}

	public void setBill_InvoiceAutoID(long bill_InvoiceAutoID) {
		System.out.println("billInvoice id:"+bill_InvoiceAutoID);
		Bill_InvoiceAutoID = bill_InvoiceAutoID;
	}

	public long getBillno() {
		return Billno;
	}

	public void setBillno(long billno) {
		System.out.println("billno id:"+billno);
		Billno = billno;
	}

	public long getInvoiceID() {
		return InvoiceID;
	}

	public void setInvoiceID(long invoiceID) {
		System.out.println("invoiceID id:"+invoiceID);
		InvoiceID = invoiceID;
	}

	public long getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(long transactionID) {
		System.out.println("transactionID id:"+transactionID);
		TransactionID = transactionID;
	}

	public double getBill_amt() {
		return bill_amt;
	}

	public void setBill_amt(double bill_amt) {
		System.out.println("bill_amt id:"+bill_amt);
		this.bill_amt = bill_amt;
	}

	public double getPaid_amt() {
		return paid_amt;
	}

	public void setPaid_amt(double paid_amt) {
		System.out.println("paid_amt id:"+paid_amt);
		this.paid_amt = paid_amt;
	}

	public double getBalance_amt() {
		return balance_amt;
	}

	public void setBalance_amt(double balance_amt) {
		System.out.println("balance_amt id:"+balance_amt);
		this.balance_amt = balance_amt;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		System.out.println("ip_address id:"+ip_address);
		this.ip_address = ip_address;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		System.out.println("created_date id:"+created_date);
		this.created_date = created_date;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		System.out.println("created_by id:"+created_by);
		this.created_by = created_by;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		System.out.println("updated_date id:"+updated_date);
		this.updated_date = updated_date;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		System.out.println("updated_by id:"+updated_by);
		this.updated_by = updated_by;
	}
    

	
	public long getOldbillnumber() {
		return oldbillnumber;
	}
    
    	
    public void setOldbillnumber(Long oldbillnumber) {
    	if(oldbillnumber!= null) {
    		this.oldbillnumber = oldbillnumber;
    	}
		/*if(null != Long.valueOf(oldbillnumber)){
			this.oldbillnumber = oldbillnumber;
        }else{
        	this.oldbillnumber = 0L;
        }*/
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		System.out.println("uuid id:"+uuid);
		if(null!= uuid)
		this.uuid = uuid;
	}

	public String getOutlet_uuid() {
		return outlet_uuid;
	}
   
	public void setOutlet_uuid(String outlet_uuid) {
		System.out.println("outlet_uuid id:"+outlet_uuid);
		this.outlet_uuid = outlet_uuid;
	}

	public String getBills_uuid() {
		return bills_uuid;
	}

	public void setBills_uuid(String bills_uuid) {
		System.out.println("bills_uuid id:"+bills_uuid);
		this.bills_uuid = bills_uuid;
	}

	
	@Override
	public String toString() {
		return "Pos_Outlet_Bom_Invoice [Bill_InvoiceAutoID=" + Bill_InvoiceAutoID + ", Billno=" + Billno
				+ ", InvoiceID=" + InvoiceID + ", TransactionID=" + TransactionID + ", bill_amt=" + bill_amt
				+ ", paid_amt=" + paid_amt + ", balance_amt=" + balance_amt + ", ip_address=" + ip_address
				+ ", created_date=" + created_date + ", created_by=" + created_by + ", updated_date=" + updated_date
				+ ", updated_by=" + updated_by + ", oldbillnumber=" + oldbillnumber + ", uuid="
				+ uuid + ", outlet_uuid=" + outlet_uuid + ", bills_uuid=" + bills_uuid + "]";
	}

	
	
}

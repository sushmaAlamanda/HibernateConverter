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

import com.ramersoft.pos.enums.ProformaBills_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity(name="pos_outlet_proformabills")
@Table(name="pos_outlet_proformabills")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class Pos_Outlet_ProformaBills {
    
	 
	@SequenceGenerator(name="identifier", sequenceName="\"pos_outlet_proformabills_Proforma_AutoID_seq\"")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="\"Proforma_AutoID\"",insertable=false)
	private long Proforma_AutoID;
	
	@Column(name="\"TransactionID\"")
	private long TransactionID;
	
	@Column(name="\"Billno\"")
	private long Billno;
	
	@Column(name="\"InvoiceID\"")
	private long InvoiceID;
	
	@Column(name="bill_amt")
	private double bill_amt;
	
	@Column(name="paid_amt")
	private double paid_amt;
	
	@Column(name="balance_amt")
	private double balance_amt;
	
	@Column(name="\"Maintax_Invoice\"")
	private long Maintax_Invoice;
	
	@Column(name="ip_address")
	private String ip_address;
	
	@Column(name="created_date")
	private Date created_date;
	
	@Column(name="created_by")
	private String created_by;
	
	@Column(name="updated_date")
    private Date updated_date;
    
	@Column(name="updated_by")
	private String updated_by;
	
	@Enumerated(EnumType.STRING)
	@Type( type = "pgsql_enum" )
	@Column(name="status")
    private ProformaBills_Status status;
    
	@Id
    @Column(name="uuid")
    private String uuid;
    
    @Column(name="outlet_uuid")
    private String outlet_uuid;
    
    @Column(name="bills_uuid")
    private String bills_uuid;

    
	public long getProforma_AutoID() {
		return Proforma_AutoID;
	}

	public void setProforma_AutoID(long proforma_AutoID) {
		Proforma_AutoID = proforma_AutoID;
	}

	public long getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(long transactionID) {
		TransactionID = transactionID;
	}

	public long getBillno() {
		return Billno;
	}

	public void setBillno(long billno) {
		Billno = billno;
	}

	public long getInvoiceID() {
		return InvoiceID;
	}

	public void setInvoiceID(long invoiceID) {
		InvoiceID = invoiceID;
	}

	public double getBill_amt() {
		return bill_amt;
	}

	public void setBill_amt(double bill_amt) {
		this.bill_amt = bill_amt;
	}

	public double getPaid_amt() {
		return paid_amt;
	}

	public void setPaid_amt(double paid_amt) {
		this.paid_amt = paid_amt;
	}

	public double getBalance_amt() {
		return balance_amt;
	}

	public void setBalance_amt(double balance_amt) {
		this.balance_amt = balance_amt;
	}

	public long getMaintax_Invoice() {
		return Maintax_Invoice;
	}

	public void setMaintax_Invoice(long maintax_Invoice) {
		Maintax_Invoice = maintax_Invoice;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
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

	public String getStatus() {
		return status.toString();
	}

	public void setStatus(ProformaBills_Status status) {
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

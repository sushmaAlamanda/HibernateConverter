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

import com.ramersoft.pos.enums.Pos_Outlet_Bills_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;


@Entity(name="pos_outlet_bills")
@Table(name="pos_outlet_bills")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class Pos_Outlet_Bills {
	 
	  @SequenceGenerator(name="identifier", sequenceName="\"pos_outlet_bills_BillnoAutoID_seq\"")
	  @GeneratedValue(strategy = GenerationType.SEQUENCE)
	  @Column(name="\"BillnoAutoID\"",insertable=false)
	  private long BillnoAutoID;
	  
	  @Column(name="\"Billno\"")
	  private long Billno;
	  
	  @Column(name="\"BOM_ID\"")
	  private long BOM_ID;
	  
	  @Column(name="\"BillAmountID\"")
	  private long BillAmountID;
	  
	  @Column(name="\"TransactionID\"")
	  private Long TransactionID;
	  
	  @Column(name="\"InvoiceID\"")
	  private Long InvoiceID;
	  
	  @Column(name="bill_type")
	  private String bill_type;
	  
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
	  
	  /*@Enumerated(EnumType.STRING)
	  @Type( type = "pgsql_enum" )
	  @Column(name="status", columnDefinition="pos_outlet_bills_status")
	  private Pos_Outlet_Bills_Status status;
	  
	  public Pos_Outlet_Bills_Status getStatus() {
			return status;
		}

		public void setStatus(Pos_Outlet_Bills_Status status) {
			this.status = status;
		}*/
	  
	  @Column(name="uuid")
	  private String uuid;
	  	  
	  @Id
	  @Column(name="outlet_uuid")
	  private String outlet_uuid;

	
	  
	public long getBillnoAutoID() {
		return BillnoAutoID;
	}

	public void setBillnoAutoID(long billnoAutoID) {
		BillnoAutoID = billnoAutoID;
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

	public long getBillAmountID() {
		return BillAmountID;
	}

	public void setBillAmountID(long billAmountID) {
		BillAmountID = billAmountID;
	}

	public Long getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(Long transactionID) {
		TransactionID = transactionID;
	}

	public Long getInvoiceID() {
		return InvoiceID;
	}

	public void setInvoiceID(Long invoiceID) {
		InvoiceID = invoiceID;
	}

	public String getBill_type() {
		return bill_type;
	}

	public void setBill_type(String bill_type) {
		this.bill_type = bill_type;
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

	@Override
	public String toString() {
		return "Pos_Outlet_Bills [BillnoAutoID=" + BillnoAutoID + ", Billno=" + Billno + ", BOM_ID=" + BOM_ID
				+ ", BillAmountID=" + BillAmountID + ", TransactionID=" + TransactionID + ", InvoiceID=" + InvoiceID
				+ ", bill_type=" + bill_type + ", ip_address=" + ip_address + ", created_date=" + created_date
				+ ", created_by=" + created_by + ", updated_date=" + updated_date + ", updated_by=" + updated_by
				+ ", uuid=" + uuid + ", outlet_uuid=" + outlet_uuid + "]";
	}  
	  	  
}

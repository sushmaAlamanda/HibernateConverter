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
import com.ramersoft.pos.enums.Bom_Trans_Status;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity(name="pos_outlet_bom_transaction")
@Table(name="pos_outlet_bom_transaction")
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
  )
public class Pos_Outlet_Bom_Transaction {
    
	 
	@SequenceGenerator(name="identifier", sequenceName="\"pos_outlet_bom_transaction_Bill_TransAutoID_seq\"")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="\"Bill_TransAutoID\"",insertable=false)
	private long Bill_TransAutoID;
	
	@Column(name="\"Billno\"")
	private long Billno;
	
	@Column(name="\"BillAmountID\"")
	private long BillAmountID;
	
	@Column(name="\"TransactionID\"")
	private long TransactionID;
	
	@Column(name="transaction_type")
	private String transaction_type;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="card_type")
	private String card_type;
	
	@Column(name="\"VoucherID\"")
	private String VoucherID;
	
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
	@Type(type = "pgsql_enum" )
	@Column(name="status",columnDefinition="pos_outlet_bom_transaction_status")
	private Bom_Trans_Status statusTrans;
	
	public String getStatusTrans() {
		return statusTrans.toString();
	}

	public void setStatusTrans(Bom_Trans_Status statusTrans) {
		this.statusTrans = statusTrans;
	}*/
	
	
	
	@Id
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="outlet_uuid")
	private String outlet_uuid;
	
	@Column(name="order_type")
	private String order_type;
	
	@Column(name="bills_uuid")
	private String bills_uuid;
     
	
	
	
	public long getBill_TransAutoID() {
		return Bill_TransAutoID;
	}

	public void setBill_TransAutoID(long bill_TransAutoID) {
		Bill_TransAutoID = bill_TransAutoID;
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

	public long getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(long transactionID) {
		TransactionID = transactionID;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getVoucherID() {
		return VoucherID;
	}

	public void setVoucherID(String voucherID) {
		VoucherID = voucherID;
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

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public String getBills_uuid() {
		return bills_uuid;
	}

	public void setBills_uuid(String bills_uuid) {
		this.bills_uuid = bills_uuid;
	}
	
	

	@Override
	public String toString() {
		return "Pos_Outlet_Bom_Transaction [Bill_TransAutoID=" + Bill_TransAutoID + ", Billno=" + Billno
				+ ", BillAmountID=" + BillAmountID + ", TransactionID=" + TransactionID + ", transaction_type="
				+ transaction_type + ", amount=" + amount + ", card_type=" + card_type + ", VoucherID=" + VoucherID
				+ ", created_date=" + created_date + ", created_by=" + created_by + ", updated_date=" + updated_date
				+ ", updated_by=" + updated_by + ", uuid=" + uuid + ", outlet_uuid="
				+ outlet_uuid + ", order_type=" + order_type + ", bills_uuid=" + bills_uuid + "]";
	}
    		
	
	
	
} 

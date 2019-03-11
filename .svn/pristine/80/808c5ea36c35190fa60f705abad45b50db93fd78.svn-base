package com.ramersoft.pos.ui.dao;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ramersoft.pos.entities.Pos_Outlet_BTOBOrders;
import com.ramersoft.pos.entities.Pos_Outlet_Billpark_Items;
import com.ramersoft.pos.entities.Pos_Outlet_Bills;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Amounts;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Invoice;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Transaction;
import com.ramersoft.pos.entities.Pos_Outlet_PreOrders;
import com.ramersoft.pos.entities.Pos_Outlet_ProformaBills;
import com.ramersoft.pos.ui.beans.Billpark_Details_Bean;
import com.ramersoft.pos.ui.beans.DeliveryOrdersBean;
import com.ramersoft.pos.ui.beans.OrdersDataBean;
import com.ramersoft.pos.ui.beans.PartialViewItemsBean;
import com.ramersoft.pos.ui.beans.ReturnViewBean;

public interface OrdersDao {
	
	List<Billpark_Details_Bean> getBillParkData(String outlet_uuid,String user_uuid,Date dayStartTime,Date dayEndTime);
	
	void deleteBillPark(String billParkNumber, String outlet_uuid, Date startTime, Date endTime,String user_uuid);
	
	void generatePreOrderBill();

	List<DeliveryOrdersBean> getHoursDeliveryData(String hours,String outlet_uuid);
	
	void insertPreOrderDetails(Pos_Outlet_Bom_Amounts tableBillsObj);
	
    Boolean getReturndate(String searchBillNo,Date prst_DateTime,Date twoDaysPrev_DateTime);
	
	String  generateReciptReturnView(HttpServletRequest request, HttpSession session);
	
	List<Pos_Outlet_Bom_Invoice> getBillnoInvoiceDetails(String Billno,String outlet_uuid);
	
	List<Pos_Outlet_Bills> getBillsData(String Billno,String outlet_uuid);
	
	List<Pos_Outlet_Bom_Transaction> getTransactionsData(String Billno,String outlet_uuid);
	
	List<Pos_Outlet_PreOrders> getPreOrdersData(String Billno,String outlet_uuid);
	
	List<ReturnViewBean> getCompleteBillDetails(String Billno,String outlet_uuid);
	
	Double getReturnOrderItemsAmount(String returnIDs,String outlet_uuid);

	List<Pos_Outlet_Billpark_Items> getBillParkItems(String outlet_uuid, String user_uuid, String billParkNumber, Date startTime,Date endTime);

	List<OrdersDataBean> orderDataList(HttpServletRequest request,String outlet_uuid,Date startTime,Date endTime);

	List<Pos_Outlet_ProformaBills> getProFormaBillsData(String billno, String outlet_uuid);
	
	List<Pos_Outlet_Bom_Invoice> get_view_search(HttpServletRequest request, HttpSession session,Date startTime,Date endTime);

	double getTotalTransactionSum(Date startTime,Date endTime);

	List<Pos_Outlet_BTOBOrders> getbtobOrdersData(String billno, String outlet_uuid);

	Double getReturnOrderItemsPrice(long oldbillnumber, String outlet_uuids);

	double getPtoformaBillsBalanceamount(long billno);

	List<PartialViewItemsBean> getOrderItemsModel(Long billno, String outlet_uuid);

	Double getBalanceAmount(Long billno, String outlet_uuid);
	String getCreditNoteAmount(HttpServletRequest request,String returnIDs, String creditnoteid_value,HttpSession httpSession);

	double getPreviousPriceForThisItemPortion(String outlet_uuids, String item_uuid, String key);
}

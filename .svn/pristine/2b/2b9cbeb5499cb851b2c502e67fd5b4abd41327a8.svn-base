package com.ramersoft.pos.ui.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ramersoft.pos.entities.Pos_Outlet_Billpark_Items;
import com.ramersoft.pos.ui.beans.Billpark_Details_Bean;
import com.ramersoft.pos.ui.beans.OrdersDataBean;
import com.ramersoft.pos.ui.beans.PartialViewItemsBean;
import com.ramersoft.pos.ui.beans.Pos_Vouchers_Bean;

public interface OrdersService {  
	
	 List<Billpark_Details_Bean> getBillParkData(HttpServletRequest request, HttpSession session);
	
	 void deleteBillPark(HttpServletRequest request, HttpSession session);
	
	String generate_overall_discount(HttpServletRequest request, HttpSession session);
    
	String generatePreOrderBill(HttpServletRequest request, HttpSession session);
	
	String addBillParkDetails(HttpServletRequest request, HttpSession session);
	
	String getHoursDeliveryData(HttpServletRequest request, HttpSession session);
	
	List<OrdersDataBean> orderDataList(HttpServletRequest request, HttpSession session);
	
	String getReturndate(HttpServletRequest request, HttpSession session);
	
	String generateReciptReturnView(HttpServletRequest request, HttpSession session);
	
	String getReturnOrderItemsAmount(HttpServletRequest request, HttpSession session);
	
	void get_view_search(HttpServletRequest request, HttpSession session);

	List<Pos_Outlet_Billpark_Items> getBillParkItems(HttpServletRequest request, HttpSession session);
	
	String generateReciptView(HttpServletRequest request, HttpSession session);
    
	double getTotalTransactionSum();

	Pos_Vouchers_Bean getVoucherDetails(HttpServletRequest request, HttpSession session);

	String generateestimateBill(HttpServletRequest request, HttpSession session);

	String generateDuplicateRecipt(HttpServletRequest request, HttpSession session);

	List<PartialViewItemsBean> getOrderItemsModel(HttpServletRequest request, HttpSession session);

	Double getBalanceAmountOfPreorder(HttpServletRequest request, HttpSession session);

	String generatePreOrderTotalBill(HttpServletRequest request, HttpSession session);
	
	String generateCreditNote(HttpServletRequest request, HttpSession session) ;

}

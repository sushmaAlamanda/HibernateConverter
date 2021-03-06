package com.ramersoft.pos.ui.controllers;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ramersoft.pos.entities.Pos_Outlet_Billpark_Items;
import com.ramersoft.pos.ui.beans.Billpark_Details_Bean;
import com.ramersoft.pos.ui.beans.PartialViewItemsBean;
import com.ramersoft.pos.ui.beans.Pos_Vouchers_Bean;
import com.ramersoft.pos.ui.service.OrdersService;
import com.ramersoft.pos.ui.libs.ERPFunctions;


@Controller
@RequestMapping("/Orders")
public class OrdersController {
	
	
	@Autowired
	OrdersService ordersService;
	
	GsonBuilder gsonBuilder = new GsonBuilder();
	Gson gson = gsonBuilder.create();
	
	
	
	@RequestMapping("/getBillParkData")
	@ResponseBody
	public String getBillParkData(HttpServletRequest request, HttpSession session) {
		System.out.println("going to get the billpark");
		List<Billpark_Details_Bean> billParkItemsDetails = ordersService.getBillParkData(request, session);
		Gson gsonBuilder = new GsonBuilder().create();
		String jsonFromItemsMap = gsonBuilder.toJson(billParkItemsDetails);
		return jsonFromItemsMap;
	}
	
	@RequestMapping("/getBillParkItems")
	@ResponseBody
	public String getBillParkContents(HttpServletRequest request, HttpSession session) {
		System.out.println("going to get the data");
		List<Pos_Outlet_Billpark_Items> billParkDetails = ordersService.getBillParkItems(request, session);
		Gson gsonBuilder = new GsonBuilder().create();
		String jsonFromItemsMap = gsonBuilder.toJson(billParkDetails);
		return jsonFromItemsMap;
	}
	
	@RequestMapping("/deleteBillPark")
	@ResponseBody
	public String deleteBillPark(HttpServletRequest request, HttpSession session) {
		System.out.println("I am in deleteBillPark Function Bro");
		//request.getParameter(name)
		ordersService.deleteBillPark(request, session);
		return null;
	}
	
	@RequestMapping("/addBillPark")
	@ResponseBody
	public String addBillPark(HttpServletRequest request,HttpSession session) {
		String billParkMessage = ordersService.addBillParkDetails(request, session);
		return billParkMessage;	
	}
	
	
	@RequestMapping("/generate_overall_discount")
	@ResponseBody
	public String generate_overall_discount(HttpServletRequest request, HttpSession session) {
	    String discountResult =  ordersService.generate_overall_discount(request, session); 
		return discountResult;
	}
	
    
	
	@RequestMapping("/generatePreOrderBill")
	@ResponseBody
	public String generatePreOrderBill(HttpServletRequest request, HttpSession session) {
	//	System.out.println((String)request.getParameter("data_array"));
		String billsData = ordersService.generatePreOrderBill(request, session); 
	    return billsData;
	}
	
	@RequestMapping("/generateBtoBOrderBill")
	@ResponseBody
	public String  generateBtoBOrderBill(HttpServletRequest request, HttpSession session) {
		String billsData = ordersService.generatePreOrderBill(request, session); 
	    return billsData;
	}
 
	
	@RequestMapping("/gethoursdeliverydata")
	@ResponseBody
	public String  getHoursDeliveryData(HttpServletRequest request, HttpSession session) {
		String details_grid = ordersService.getHoursDeliveryData(request, session); 
	    return details_grid;
	}
	
	@RequestMapping("/getReturndate")
	@ResponseBody
	public String  getReturndate(HttpServletRequest request, HttpSession session) {
		String resultBoolNum = ordersService.getReturndate(request, session);
		return resultBoolNum;
	}
	
	@RequestMapping("/generateReciptReturnView")
	@ResponseBody
	public String  generateReciptReturnView(HttpServletRequest request, HttpSession session) {
		System.out.println("in generateReciptReturnView");
		String returnOrdResultStr = ordersService.generateReciptReturnView(request, session);
		return returnOrdResultStr;
	}
	
	@RequestMapping("/getreturnorderitemsamount")
	@ResponseBody
	public String  getreturnorderitemsamount(HttpServletRequest request, HttpSession session) {
		System.out.println("in getreturnorderitemsamount");				
		String returnAmountStr = ordersService.getReturnOrderItemsAmount(request, session);
		return returnAmountStr;
	}
	
	
	/**
	 * Not useful for present Context
	 */
	@RequestMapping("/get_view_search")
	@ResponseBody
	public String  get_view_search(HttpServletRequest request, HttpSession session) {
		System.out.println("============get_view_search=========");				
		ordersService.get_view_search(request, session);
		return "asdfghjklmnbvcxz";
	}
	
	
	@RequestMapping("/generateReciptView")
	@ResponseBody
	public String  generateReciptView(HttpServletRequest request, HttpSession session) {
		System.out.println("in generateReciptView");				
		//String returnAmountStr = ordersService.getReturnOrderItemsAmount(request, session);
		String receiptData = ordersService.generateReciptView(request, session);
		return receiptData;
	}
	
	@RequestMapping("/generateReciptViewSearch")
	@ResponseBody
	public String  generateReciptViewSearch(HttpServletRequest request, HttpSession session) {
		System.out.println("in generateReciptViewSearch");				
		//String returnAmountStr = ordersService.getReturnOrderItemsAmount(request, session);
		String receiptData = ordersService.generateReciptView(request, session);
		return receiptData;
	}
	
	@RequestMapping("/generateDeliveryReciptView")
	@ResponseBody
	public String generateDeliveryReciptView(HttpServletRequest request, HttpSession session) {
		System.out.println("in generateDeliveryReciptView");				
		//String returnAmountStr = ordersService.getReturnOrderItemsAmount(request, session);
		request.setAttribute("isThisDeliveryOrderEdit", "yes");
		String receiptData = ordersService.generateReciptView(request, session);
		return receiptData;
	}
	@RequestMapping("/getVoucherDetails")
	@ResponseBody
	public String getVoucherDetails(HttpServletRequest request, HttpSession session) {
		System.out.println("in getVoucherDetails controller");	
		Pos_Vouchers_Bean voucherDetails = ordersService.getVoucherDetails(request, session);
		System.out.println("controller list is " + voucherDetails);
		Gson gsonBuilder = new GsonBuilder().create();
		String jsonFromItemsMap = gsonBuilder.toJson(voucherDetails);
		return jsonFromItemsMap;
	}
	
	
	@RequestMapping("/generateestimateBill")
	@ResponseBody
	public String generateestimateBill(HttpServletRequest request, HttpSession session) {
		System.out.println("in generateestimateBill");
		String billsData = ordersService.generateestimateBill(request, session);
		System.out.println("bills data is*****"+billsData);
		return billsData;
	}
	
	@RequestMapping("/generateDuplicateRecipt")
	@ResponseBody
	public String generateDuplicateRecipt(HttpServletRequest request, HttpSession session) {
		String billsData = ordersService.generateDuplicateRecipt(request, session); 
	    return billsData;
	}
	
	@RequestMapping("/getPartialOrderItems")
	@ResponseBody
	public List<PartialViewItemsBean> getPartialOrderItems(HttpServletRequest request, HttpSession session) {
		List<PartialViewItemsBean> billsData = ordersService.getOrderItemsModel(request, session); 
	    return billsData;
	}
	
	
	@RequestMapping("/getBalanceAmountOfPreorder")
	@ResponseBody
	public Double getBalanceAmountOfPreorder(HttpServletRequest request, HttpSession session) {
		/*List<PartialViewItemsBean> billsData = ordersService.getOrderItemsModel(request, session); 
	    return billsData;*/
		System.out.println("In getBalanceAmountOfPreorder.................");
		Double balanceAmount = 0d;
		try {
			balanceAmount = ordersService.getBalanceAmountOfPreorder(request, session);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return balanceAmount;
	}   
	
	
	@RequestMapping("/generatePreOrderTotalBill")
	@ResponseBody
	public String generatePreOrderTotalBill(HttpServletRequest request, HttpSession session) {
		System.out.println("In generatePreOrderTotalBill.................");

		String data = "";
		try {
			data  = ordersService.generatePreOrderTotalBill(request, session);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	} 
	
	@RequestMapping("/getCreditNote")
	@ResponseBody
	public String getCreditNote(HttpServletRequest request, HttpSession session) {
		
		System.out.println("aaaaaaaaaammmmmmmmmmmmmmmmmmmmmmaaaaaaaaaa"); 
		System.out.println("helloooooooooooooooooooooo");
		String returnID_data = request.getParameter("retunID_value");
		System.out.println("original _data is ::::"+returnID_data);
		
		String creditnoteid_value = request.getParameter("creditnoteid_value");
		System.out.println("creditnoteid_value is:"+creditnoteid_value);
		
		//System.out.println("getCreditNote Controller"+request.getParameter("data_array"));
		String creditData = ordersService.generateCreditNote(request, session); 
	    //return billsData;
		return creditData;
	}
	
	
}

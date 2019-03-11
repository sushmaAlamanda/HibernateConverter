package com.ramersoft.pos.ui.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ramersoft.pos.ui.dao.BillsDao;
import com.ramersoft.pos.ui.service.BillsService;

@RestController
@RequestMapping("/Bills")
public class BillsController {
    
	@Autowired
	BillsService billsService;
	
	@ResponseBody
	@RequestMapping("/generateBill")
	public String generateBill(HttpServletRequest request, HttpSession session) {
		System.out.println("***********************generatebill controller************************");
		String outlet_uuid = (String) session.getAttribute("Outlet_uuid");
	    String user_uuid = (String) session.getAttribute("User_uuid");
	    System.out.println("outlet uuid is : " +outlet_uuid);
	    System.out.println("user uuid is :"+user_uuid);
	    
	    String original_data = request.getParameter("data_array");
	    System.out.println("paramobj values...." +original_data);
	    
		String billsData = billsService.generateBillsData(request, session);
    	System.out.println("data os bill  in controller is"+billsData);
    	
		return billsData;
       			
	}
}

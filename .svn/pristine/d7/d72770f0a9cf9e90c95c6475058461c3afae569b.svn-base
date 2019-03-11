package com.ramersoft.pos.ui.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ramersoft.pos.entities.ERP_Outlets;
import com.ramersoft.pos.entities.Pos_Outlet_Day_In_Out_Transaction;
import com.ramersoft.pos.entities.Pos_Outlet_Ordertypes;
import com.ramersoft.pos.ui.beans.CategoriesBean;
import com.ramersoft.pos.ui.beans.OrdersDataBean;
import com.ramersoft.pos.ui.beans.OrderstypesBean;
import com.ramersoft.pos.ui.libs.Constants;
import com.ramersoft.pos.ui.libs.ERPFunctions;
import com.ramersoft.pos.ui.service.OrdersService;
import com.ramersoft.pos.ui.service.PosService;



@Controller
@RequestMapping("/Pos")
public class PosController {
    
	@Autowired
	PosService posService;
	
	@Autowired
	OrdersService ordersService;
	
	@Autowired
	ERPFunctions  erpFunObj;
	
	@RequestMapping("/Index")
	public ModelAndView showHomePage(HttpServletRequest request,HttpSession httpSession) {		
		ModelAndView mv = new ModelAndView("pos/index");
	    Pos_Outlet_Day_In_Out_Transaction day_in_details = posService.getDayInDataForToday(httpSession);
	    System.out.println("day in data:"+day_in_details);
		
	    List<CategoriesBean> categories =	posService.get_categoryData(request,httpSession);
	    System.out.println(" in pos controller displaying categories");
	    categories.forEach(System.out::println);

	    /* to get the general orders data***/
	    String general_uuid = posService.get_generalorderData();
	    
	    /* to get the general orders data***/
	    List<OrderstypesBean> ordertypes = new ArrayList<OrderstypesBean>();
	    try {
	    	ordertypes = posService.get_ordersData();
	    }catch(Exception e){
	    	
	    }
	    System.out.println("*****controller order types is**"+ordertypes);
	    Gson gsonBuilder = new GsonBuilder().create();
		String jsonFromItemsMap = gsonBuilder.toJson(ordertypes);
		System.out.println("*****controller order types are**"+jsonFromItemsMap);
	    
	    List<OrdersDataBean> OrdersDataList = new ArrayList<OrdersDataBean>();
	    try {
	    	OrdersDataList = ordersService.orderDataList(request, httpSession);
	    }catch(Exception e){
	    	
	    }
	    System.out.println(" in pos controller displaying orderslist");
        System.out.print("\n*********\n"+OrdersDataList);
	    
	    /*
	    int max_pages = 0;
	    if(categories.get(0).getMax_category()!=0) {
	    	double pages = (double)categories.get(0).getMax_category();
	    	max_pages = (int)Math.ceil(pages/4);
	    }	    
	    System.out.println("max_pages"+max_pages);
	    mv.addObject("day_in_data",day_in_details);	    
	    mv.addObject("categories_max_cnt",max_pages);
	    */
        
        //Categories Navigation Max Pages
        int max_pages = 0;
        int noOfMaxCatgry = 0;
        int maxMainCatgryLimit = Constants.MAX_MAIN_CATEGORY_LIMIT; 
        
        try {
        	noOfMaxCatgry = posService.get_totalCategoryDataNo(httpSession);
        	System.out.println("=======noOfMaxCatgry is=======::"+noOfMaxCatgry);
        	max_pages = (int)Math.ceil((double)noOfMaxCatgry/maxMainCatgryLimit);
        }catch(Exception e) {
        	System.out.println("problem to set the max_pages in posController");
        }
        
        System.out.println("in pos_controller max_pages is:"+max_pages);
        

        
        double transactionSumData = 0d;
        try {
        	transactionSumData = ordersService.getTotalTransactionSum();
        }catch(Exception e) {
            System.out.println("problem to get the total transaction Data for today");
        	e.printStackTrace();
        }
        System.out.println("In posController transactionSumData is:"+transactionSumData);
        
        
        String year = Constants.year;
        try {
            year = erpFunObj.getLocalDataInfo("current_year");
        }catch(Exception e) {
            System.out.println("problem to get the current_year from database");
            e.printStackTrace();
        }
        
        
        /***********for location name of outlet*************/
        
        ERP_Outlets outletName = null;
        try {
        	outletName = posService.getOutletName(request, httpSession);
        }catch(Exception e) {
            System.out.println("problem to get the outlet name");
        	e.printStackTrace();
        }
        System.out.println("In posController outletdata is:"+outletName);
        
        
        
        
	    mv.addObject("day_in_data",day_in_details);
	    mv.addObject("MainCategories",categories);
	    mv.addObject("general_uuid", general_uuid);
	    mv.addObject("orders_data", OrdersDataList);
	    mv.addObject("ordertypes", ordertypes);
	    mv.addObject("transactionSumData",transactionSumData);
	    mv.addObject("year",year);
	    mv.addObject("outletName",outletName);
	    mv.addObject("categories_max_cnt",max_pages);
	    
		return mv;
	}
	
	
	@RequestMapping("/setDayInData")
	public ModelAndView setDayInData(HttpServletRequest request,HttpSession httpSession) {
		Long InsertionID = posService.set_DayInData(request, httpSession);		
		return null;
		
	}
	
	@RequestMapping("/getdialogs")
	public ModelAndView setDailog(HttpServletRequest request,HttpSession httpSession) {
		System.out.println("************"+request.getParameter("dialog")+"in controller*****");
		ModelAndView mv = new ModelAndView("pos/card_payment");
		mv.addObject(request);
		return mv;
		
	}
	
	
	    @RequestMapping("/getMainCategoryData")
	    @ResponseBody
	    public String getMainCategoryData(HttpServletRequest request,HttpSession httpSession){
	        List<CategoriesBean> maincategoryresult=posService.get_categoryData(request, httpSession);
	        String categories_table = erpFunObj.getMainCategoriesData(maincategoryresult,request, httpSession);
	        LinkedHashMap<String,String> returnMainCategories = new  LinkedHashMap<String,String>();
	        returnMainCategories.put("tbl_data",categories_table);
	        //returnMainCategories.put("max_pages",Integer.toString(max_pages));
	        
	         System.out.println("in getMainCategories hashMap was set");
	         Gson gsonBuilder = new GsonBuilder().create();
	         // Convert Java Map into JSON
	      
	          String jsonFromJavaMap = gsonBuilder.toJson(returnMainCategories);
	          
	          return jsonFromJavaMap;
	    }
	
	
	
	
	
	@RequestMapping("/getSubCategoryData")
	@ResponseBody	
	public String get_SubCategoryData(HttpServletRequest request,HttpSession httpSession) {
		String subCategoriesResult = posService.get_SubCategoryData(request, httpSession);		
	    return subCategoriesResult;		
	}//get_SubCategoryData
	
	
	@RequestMapping("/getItemsData")
	@ResponseBody
	public String getItemsData(HttpServletRequest request,HttpSession httpSession) {
		String itemsResult = posService.getItemsData(request, httpSession);
	    return itemsResult;		
	}//getItemsData
	
	
	@RequestMapping("/getUnitsData")
	@ResponseBody
	public String getUnitsData(HttpServletRequest request,HttpSession httpSession) {
		String unitsResult = posService.get_AllUnitsData(request, httpSession);
		
	    return unitsResult;		
	}//getItemsData
	
	
}  //class

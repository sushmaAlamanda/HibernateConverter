package com.ramersoft.pos.ui.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ramersoft.pos.entities.ERP_Outlets;
import com.ramersoft.pos.entities.Pos_Outlet_Day_In_Out_Transaction;
import com.ramersoft.pos.entities.Pos_Outlet_Ordertypes;
import com.ramersoft.pos.ui.beans.CategoriesBean;
import com.ramersoft.pos.ui.beans.OrderstypesBean;


public interface PosService {
     
	Pos_Outlet_Day_In_Out_Transaction getDayInDataForToday(HttpSession httpSession);
	Long set_DayInData(HttpServletRequest request,HttpSession httpSession);
	List<CategoriesBean> get_categoryData(HttpServletRequest request,HttpSession httpSession);
	String get_SubCategoryData(HttpServletRequest request,HttpSession httpSession);
	String getItemsData(HttpServletRequest request,HttpSession httpSession);
	String get_AllUnitsData(HttpServletRequest request,HttpSession httpSession);
	String get_generalorderData();
	List<OrderstypesBean> get_ordersData();
	ERP_Outlets getOutletName(HttpServletRequest request,HttpSession httpSession);
	
	int get_totalCategoryDataNo(HttpSession httpSession);
}

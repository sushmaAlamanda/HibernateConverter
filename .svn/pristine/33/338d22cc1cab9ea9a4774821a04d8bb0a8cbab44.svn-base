package com.ramersoft.pos.ui.dao;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ramersoft.pos.entities.ERP_Outlets;
import com.ramersoft.pos.entities.Pos_Outlet_Day_In_Out_Transaction;
import com.ramersoft.pos.entities.Pos_Outlet_Ordertypes;
import com.ramersoft.pos.ui.beans.CategoriesBean;
import com.ramersoft.pos.ui.beans.ItemsBean;
import com.ramersoft.pos.ui.beans.OrderstypesBean;
import com.ramersoft.pos.ui.beans.UnitsBean;

public interface PosDao {
	Pos_Outlet_Day_In_Out_Transaction getDayInDataForToday(String outlet_uuid,Date startTime,Date endTime);
	
	Long saveDayInDataForToday(Pos_Outlet_Day_In_Out_Transaction  dayInDataObj);
	
	List<CategoriesBean> get_categoryData(String outlet_uuid,int page_length,int page_start);
	
	List<CategoriesBean> get_SubCategoryData(String outlet_uuid,String category_uuid,int page_start,int page_length);
	
	List<ItemsBean> get_AllItemsData(String outlet_uuid,String sub_category_id,String search_value1);
	
	List<UnitsBean> get_AllUnitsData(String outlet_uuid,String item_uuid,String unit_uuid);
	
	String get_generalorderData();

	List<OrderstypesBean> get_ordersData();

	ERP_Outlets getOutletName(HttpServletRequest request,HttpSession httpSession);
	
	int get_totalCategoryDataNo(String outlet_uuid);

	int get_totalSubCategoryDataNo(String outlet_uuid, String category_uuid);

}

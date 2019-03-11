package com.ramersoft.pos.ui.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ramersoft.pos.entities.Pos_Outlet_BTOBOrders;
import com.ramersoft.pos.entities.Pos_Outlet_Billpark_Items;
import com.ramersoft.pos.entities.Pos_Outlet_Bills;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Amounts;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Invoice;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Items;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Transaction;
import com.ramersoft.pos.entities.Pos_Outlet_PreOrders;
import com.ramersoft.pos.entities.Pos_Outlet_PriceChange;
import com.ramersoft.pos.entities.Pos_Outlet_ProformaBills;
import com.ramersoft.pos.entities.Pos_Voucher_Discounts;
import com.ramersoft.pos.entities.Pos_Vouchers;
import com.ramersoft.pos.enums.BTOBOrders_status;
import com.ramersoft.pos.enums.BTOB_status;
import com.ramersoft.pos.enums.Pos_Voucher_Discounts_Status;
import com.ramersoft.pos.enums.Pos_outlet_billpark_items_status;
import com.ramersoft.pos.enums.Pos_vouchers_status;
import com.ramersoft.pos.enums.PreOrders_OrderStatus;
/*import com.ramersoft.pos.ui.beans.BillPark;*/
import com.ramersoft.pos.ui.beans.Billpark_Details_Bean;
import com.ramersoft.pos.ui.beans.DeliveryOrdersBean;
import com.ramersoft.pos.ui.beans.GstAndCgstBean;
import com.ramersoft.pos.ui.beans.ItemsBean;
import com.ramersoft.pos.ui.beans.OrdersDataBean;
import com.ramersoft.pos.ui.beans.PartialViewItemsBean;
import com.ramersoft.pos.ui.beans.Pos_Vouchers_Bean;
import com.ramersoft.pos.ui.beans.ReturnViewBean;
import com.ramersoft.pos.ui.dao.OrdersDao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ramersoft.pos.ui.libs.BillsRandomGenerations;
import com.ramersoft.pos.ui.libs.Constants;
import com.ramersoft.pos.ui.libs.ERPFunctions;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	OrdersDao ordersDao;

	@Autowired
	ERPFunctions erpFunObj;
	
	@Autowired
	BillsRandomGenerations billsRandFuncts;
	
	@Autowired
    BillsCommonFnsService commonMethodsObj;
	
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	Transaction tx;

	GsonBuilder gsonBuilder = new GsonBuilder();
	Gson gson = gsonBuilder.create();
	
	@Override
	public List<Billpark_Details_Bean> getBillParkData(HttpServletRequest request, HttpSession session) {
		
		String outlet_uuid = (String) session.getAttribute("Outlet_uuid");
		String user_uuid = (String) session.getAttribute("User_uuid");
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String todayDate = now.format(formatter);
		String dayStartTime = todayDate + " 00:00:00";
		String dayEndTime = todayDate + " 23:59:59";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startTime = null;
		Date endTime = null;
		try {			
		  //startTime = (Date)format.parse("2018-12-22 00:00:00");
			startTime = (Date)format.parse(dayStartTime);
			endTime =  (Date)format.parse(dayEndTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Billpark_Details_Bean> billParkList = new ArrayList<Billpark_Details_Bean>();
				
		try {
		 billParkList= ordersDao.getBillParkData(outlet_uuid, user_uuid,startTime,endTime);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return billParkList;
	}
	
	
	/* to get bill park items of billparked bill*/
	@Override
	public List<Pos_Outlet_Billpark_Items> getBillParkItems(HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated method stub
		String outlet_uuid = (String) session.getAttribute("Outlet_uuid");
		String user_uuid = (String) session.getAttribute("User_uuid");
		String billParkNumber = (String) request.getParameter("billno");
		String original_data = request.getParameter("data_array");
		
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(original_data);
		JsonObject jsonObject = jsonTree.getAsJsonObject();
		System.out.println("This is the given number:"+jsonObject.get("billno"));
		
		billParkNumber = jsonObject.get("billno").getAsString();
		
		System.out.println("*****"+billParkNumber+"*****");
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String todayDate = now.format(formatter);
		String dayStartTime = todayDate + " 00:00:00";
		String dayEndTime = todayDate + " 23:59:59";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startTime = null;
		Date endTime = null;
		try {			
		  //startTime = (Date)format.parse("2018-12-22 00:00:00");
			startTime = (Date)format.parse(dayStartTime);
			endTime =  (Date)format.parse(dayEndTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Pos_Outlet_Billpark_Items> billParkList = new ArrayList<Pos_Outlet_Billpark_Items>();
		
		
		try {
		 billParkList= ordersDao.getBillParkItems(outlet_uuid, user_uuid, billParkNumber, startTime, endTime);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return billParkList;
	}

	
	
	@Override
	public void deleteBillPark(HttpServletRequest request, HttpSession session) {
		String outlet_uuid = (String) session.getAttribute("Outlet_uuid");
		String user_uuid = (String) session.getAttribute("User_uuid");
		String billParkNumber = (String) request.getParameter("billno");
		LocalDateTime now = LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String todayDate = now.format(formatter);
		String dayStartTime = todayDate + " 00:00:00";
		String dayEndTime = todayDate + " 23:59:59";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startTime = null;
		Date endTime = null;
		try {			
		  //startTime = (Date)format.parse("2018-12-22 00:00:00");
			startTime = (Date)format.parse(dayStartTime);
			endTime =  (Date)format.parse(dayEndTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		int billParkresult=0;
		
		try {
		
		 ordersDao.deleteBillPark(billParkNumber,outlet_uuid,startTime,endTime, user_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
	@Override
	public String addBillParkDetails(HttpServletRequest request, HttpSession httpsession) {
		System.out.println("I am in addBillParkDetails model");		
	    
		String outlet_uuid = (String) httpsession.getAttribute("Outlet_uuid");
		String user_uuid = (String) httpsession.getAttribute("User_uuid");
		String billParkNumber = (String) request.getParameter("billno");
		
	    
		String original_data = request.getParameter("data_array");
		
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(original_data);
		JsonObject jsonObject = jsonTree.getAsJsonObject();
		System.out.println("This is the given number:"+jsonObject.get("phone_number"));
		
		JsonElement selectedItemArray = jsonObject.get("selectedItemArray");
		JsonObject selectedItemObj = selectedItemArray.getAsJsonObject();
		
		int noOfKeys = selectedItemObj.keySet().size();
		System.out.println("This is the key set :"+noOfKeys);
		
		billParkNumber = jsonObject.get("phone_number").getAsString();
		
		System.out.println("*****"+billParkNumber+"*****");
		
		Session session = null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		long countNo=0L;
		Criteria criteria = session.createCriteria(Pos_Outlet_Billpark_Items.class);
		criteria.add(Restrictions.eq("status", Pos_outlet_billpark_items_status.Active));
		criteria.add(Restrictions.eq("phone_number", billParkNumber));
		criteria.setProjection(Projections.rowCount());
		countNo = (long)criteria.uniqueResult();
		System.out.println("*****Result set issssssssssssssss"+countNo+"*****");
		session.close();
		
		List<Pos_Outlet_Billpark_Items> billParkList = new ArrayList<Pos_Outlet_Billpark_Items>();
		if (countNo>0) {
			    System.out.println("Number had already existed");
				return "Duplicate Phone Number";
		}else {
				int sumOfInsertions = 0;
				System.out.println("I am in else case");
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String presentDateTime = now.format(formatter);
				
	
				Set<?> s =  selectedItemObj.keySet();
	
			    Iterator<?> i = s.iterator();
			    
			    float item_dis = 0f;
			    float item_adt_dis = 0f;
			    float total_dis = 0f;
			  	
			    do{
			        String key = i.next().toString();
			        JsonObject itemsRowData = selectedItemObj.get(key).getAsJsonObject();
			        System.out.println(key);
			        System.out.println("This is itemdata:"+itemsRowData);
			        System.out.println(itemsRowData.get("unit").getAsString());
			        System.out.println(itemsRowData.get("name").getAsString());
			        System.out.println(itemsRowData.get("original_discount").getAsJsonObject().get("percentage").getAsFloat());
			        System.out.println(itemsRowData.get("gst").getAsJsonObject().get("all").getAsJsonObject().get("amount").getAsFloat());
			        System.out.println(itemsRowData.get("gst").getAsJsonObject().get("all").getAsJsonObject().get("percentage").getAsFloat());
			        System.out.println("********"+itemsRowData.get("total").getAsFloat());
			        System.out.print("\n\n");
			       
			        float i_adt_dis_perc = 0f;
			        float i_adt_dis_amt = 0f;
			        
			        try {
			        	i_adt_dis_perc = itemsRowData.get("additional_discount").getAsJsonObject().get("percentage").getAsFloat();
			        	i_adt_dis_amt =  itemsRowData.get("additional_discount").getAsJsonObject().get("amount").getAsFloat();
			        	System.out.println("dis perc"+i_adt_dis_perc); //******output 0.0
			        	System.out.println("amt perc"+i_adt_dis_amt); //******output 0.0
			        }catch(Exception e) {
			        	e.printStackTrace();
			        }
			        item_dis += itemsRowData.get("discount").getAsJsonObject().get("amount").getAsFloat();
			        item_adt_dis += i_adt_dis_amt;
			        float discount_amt = itemsRowData.get("discount").getAsJsonObject().get("amount").getAsFloat();
			        total_dis = discount_amt + i_adt_dis_amt;
			        float price_bg = (itemsRowData.get("price").getAsFloat() * itemsRowData.get("qty").getAsFloat())-
			        				 (itemsRowData.get("discount").getAsJsonObject().get("amount").getAsFloat()+i_adt_dis_amt);
			        
			        
			        Pos_Outlet_Billpark_Items billParkObj = new Pos_Outlet_Billpark_Items();
			        
			        System.out.print("*****unit is*****"+itemsRowData.get("unit").getAsString()+"**********");
			        
			        billParkObj.setPhone_number(jsonObject.get("phone_number").getAsString());
			        billParkObj.setUnit(itemsRowData.get("unit").getAsString());
			        billParkObj.setOriginal_price(itemsRowData.get("original_price").getAsFloat());
			        
			        billParkObj.setPrice(itemsRowData.get("price").getAsFloat());
			        billParkObj.setName(itemsRowData.get("name").getAsString());
			        billParkObj.setSize((int) itemsRowData.get("size").getAsFloat());
			        
			        billParkObj.setQuantity(itemsRowData.get("qty").getAsInt());
			        billParkObj.setItem_uuid(itemsRowData.get("item_id").getAsString());
			        billParkObj.setOriginal_discount_perc(itemsRowData.get("original_discount").getAsJsonObject().get("percentage").getAsFloat());
			        
			        billParkObj.setOriginal_discount_amt(itemsRowData.get("original_discount").getAsJsonObject().get("amount").getAsFloat());
			        billParkObj.setDiscount_perc(itemsRowData.get("discount").getAsJsonObject().get("percentage").getAsFloat());
			        billParkObj.setDiscount_amt(itemsRowData.get("discount").getAsJsonObject().get("amount").getAsFloat());
			        billParkObj.setAdt_discount_perc(i_adt_dis_perc);
			        billParkObj.setAdt_discount_amt(i_adt_dis_amt);
			        billParkObj.setGST_perc(itemsRowData.get("gst").getAsJsonObject().get("all").getAsJsonObject().get("percentage").getAsFloat());
			        
			        billParkObj.setGST_amt(itemsRowData.get("gst").getAsJsonObject().get("all").getAsJsonObject().get("amount").getAsFloat()); 
			        billParkObj.setCGST_perc(itemsRowData.get("gst").getAsJsonObject().get("cgst").getAsJsonObject().get("percentage").getAsFloat());
			        billParkObj.setCGST_amt(itemsRowData.get("gst").getAsJsonObject().get("cgst").getAsJsonObject().get("amount").getAsFloat()); 
			        
			        billParkObj.setSGST_perc(itemsRowData.get("gst").getAsJsonObject().get("sgst").getAsJsonObject().get("percentage").getAsFloat());
			        billParkObj.setSGST_amt(itemsRowData.get("gst").getAsJsonObject().get("sgst").getAsJsonObject().get("amount").getAsFloat());
			        billParkObj.setTotal(itemsRowData.get("total").getAsFloat());
			        
			        billParkObj.setUuid(erpFunObj.uniqueRandAndTimeStamp());
			        billParkObj.setOutlet_uuid(outlet_uuid);
			        billParkObj.setPortion_uuid(key.toString().trim());
			        
			        billParkObj.setCreated_date(new Date());
			        billParkObj.setCreated_by(user_uuid);
			        billParkObj.setPrice_bd(itemsRowData.get("price").getAsFloat());
			        
			        billParkObj.setPrice_bg(price_bg);
			        billParkObj.setStatus(Pos_outlet_billpark_items_status.Active);
			        billParkObj.setIGST_amt(0.0f);
			        billParkObj.setIGST_perc(0.0f);
			        
			        billParkList.add(billParkObj);
			        
			        System.out.println("***************************");
		    		System.out.println(billParkObj.toString());
		    		System.out.println("***************************");
			        
			     }while(i.hasNext());
			        
		     }
			   session=null;
			   String successful = "Failed"; 
		       try {
		        	session = sessionFactory.openSession();
		        	session.beginTransaction();
		        	for (Pos_Outlet_Billpark_Items billparkObjs : billParkList){
					      session.save(billparkObjs);
					}
			        session.getTransaction().commit();
			        System.out.println("\n.......Records Saved Successfully to the Database.......\n");
			        successful = "Success";
			        
		        	
		       }catch(Exception sqlException) {
					if(null != session.getTransaction()) {
						System.out.println("\n.......Transaction Is Being Rolled Backed");
						session.getTransaction().rollback();
						
						}
					sqlException.printStackTrace();
				
		       }finally {
						session.close();
			    }
		       
		       return successful;
		      
	}

	
	
	
	@Override
	public String generate_overall_discount(HttpServletRequest request, HttpSession session) {

		// ordersService
		String outlet_uuid = (String) session.getAttribute("Outlet_uuid");
		String user_uuid = (String) session.getAttribute("User_uuid");

		String original_data = request.getParameter("data_array");
	    System.out.println("paramobj values...." +original_data);

		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(original_data);
		JsonObject jsonObject = jsonTree.getAsJsonObject();
		float cur_amount = jsonObject.get("cur_amount").getAsFloat();
		JsonElement selectedItemArray = jsonObject.get("selectedItemArray");
		JsonObject selectedItemObj = selectedItemArray.getAsJsonObject();

		JsonElement selectedItemsGstArray = jsonObject.get("selectedItemsGstArray");
		JsonObject selectedItemsGstObj = selectedItemsGstArray.getAsJsonObject();

		JsonElement selectedItemstot = jsonObject.get("selectedItemstot");
		JsonObject selectedItemstotObj = selectedItemstot.getAsJsonObject();

		float oall_dis_amt = 0, oall_dis_perc = 0;
		if (jsonObject.get("dis_perc") != null) {
			try {
				if (selectedItemstotObj.get("balance_amount").getAsString() != null) {
					oall_dis_perc = Math.round(cur_amount);
					oall_dis_amt = Math.round((selectedItemstotObj.get("balance_amount").getAsFloat() * cur_amount) / 100);
				}
			} catch (Exception e) {
				oall_dis_perc = Math.round(cur_amount);
				oall_dis_amt = Math.round((selectedItemstotObj.get("tot_before_gst").getAsFloat() * cur_amount) / 100);
			}
		} else {
			oall_dis_amt = Math.round(cur_amount);
			oall_dis_perc = Math.round((cur_amount * 100) / selectedItemstotObj.get("tot_before_gst").getAsFloat());
		}

		double tot_dis_amt = Math.round(oall_dis_amt);
		int i = 0;
		double dis_amt = 0, dis_per = 0;

		Set<?> s = selectedItemObj.keySet();
		Iterator<?> itr = s.iterator();

		int noOfElements = 0;
		do {
			String key = itr.next().toString();
			JsonObject itemsRowData = selectedItemsGstObj.get(key).getAsJsonObject();

			noOfElements++;
			float tprice = itemsRowData.get("tprice").getAsFloat();
			float totalbillprice = itemsRowData.get("totalbillprice").getAsFloat();
			if (tot_dis_amt > 0) {
				if (tot_dis_amt < tprice) {
					dis_amt = ((tprice / totalbillprice) * tot_dis_amt);
										
					/*String formate = df.format(dis_amt); 
				    try {
						dis_amt = (double)df.parse(formate) ;
					} catch (ParseException e) {
						e.printStackTrace();
					}
					*/
					dis_per = ((dis_amt / tprice) * 100);
					
					
				} else if (tot_dis_amt >= tprice) {
					dis_amt = ((tprice / totalbillprice) * tot_dis_amt);
					dis_per = ((dis_amt / tprice) * 100);
				}
                
				dis_amt = Math.round(dis_amt*100)/100.00;
				dis_per = Math.round(dis_per*100)/100.00;
				
				System.out.println("discount amount and percentage vaues:"+dis_amt+"-----"+dis_per);
				
				// selectedItemObj.get(key).getAsJsonObject().get("additional_discount").getAsJsonObject().

				selectedItemObj.get(key).getAsJsonObject().get("additional_discount").getAsJsonObject()
						.addProperty("percentage", Math.round(dis_per));
				selectedItemObj.get(key).getAsJsonObject().get("additional_discount").getAsJsonObject()
						.addProperty("amount", Math.round(dis_amt));
			} else {
				selectedItemObj.get(key).getAsJsonObject().get("additional_discount").getAsJsonObject()
						.addProperty("percentage", 0);
				selectedItemObj.get(key).getAsJsonObject().get("additional_discount").getAsJsonObject()
						.addProperty("amount", 0);
			}
		} while (itr.hasNext());

		if (noOfElements == 0) {
			oall_dis_perc = 0;
			oall_dis_amt = 0;
		}

		LinkedHashMap<String, String> overAllDiscountMap = new LinkedHashMap<String, String>();
		
		LinkedHashMap<String, String> overallInnerMap = new LinkedHashMap<String, String>();
		overallInnerMap.put("percentage", Float.toString(oall_dis_perc));
		overallInnerMap.put("amount", Float.toString(oall_dis_amt));		
		overallInnerMap.put("cur_amount", Float.toString(cur_amount));
		String overallInnerMapString = gson.toJson(overallInnerMap);
		try {
			if(jsonObject.get("dis_perc") != null) 
		         overallInnerMap.put("dis_perc", jsonObject.get("dis_perc").getAsString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		// String selectedItemArrayString = selectedItemObj.toString();

		String selectedItemArrayString = new Gson().toJson(selectedItemObj).toString();

		overAllDiscountMap.put("selectedItemArray", selectedItemArrayString);
		overAllDiscountMap.put("over_dis", overallInnerMapString);

		return gson.toJson(overAllDiscountMap);
	}

	
	@Override
	public String generatePreOrderBill(HttpServletRequest request, HttpSession httpSession) {
		
		String params_data = request.getParameter("data_array"); 
		System.out.println("json data in generatePreOrderBill:");
		System.out.println(params_data);
		String outlet_uuids = (String)httpSession.getAttribute("Outlet_uuid");
		String outlet_uuid = String.valueOf(Integer.valueOf(outlet_uuids)+10);
		String user_uuid = (String)httpSession.getAttribute("User_uuid");
		
		Long BillNo = 0L;
		try {
	        BillNo = billsRandFuncts.getbill_NextProformaNumber(httpSession);	
		}catch(Exception e) {
			System.out.println("problem with setting BillNo");
			e.printStackTrace();
		}
		
		System.out.println("in service so next bill no is:"+BillNo);
		
		
		
		Long BOM_ID=0L;
		try {
		    BOM_ID = billsRandFuncts.generateUniqueToken(httpSession,"BOM_ID",Constants.BOM_ID_LENGTH);
		}catch(Exception e) {
			System.out.println("problem while setting BOM_ID...........");
			e.printStackTrace();
		}
		
		
		Long BillAmountID=0L;
		try {
			BillAmountID = billsRandFuncts.generateUniqueToken(httpSession,"BillAmountID",Constants.BillAmountID_LENGTH);
		}catch(Exception e) {
			System.out.println("problem while setting BillAmountID...........");
			e.printStackTrace();
		}
		
		Long TransactionID = 0L;
		try {
			Long TransactionID_dup = billsRandFuncts.generateUniqueToken(httpSession,"TransactionID",Constants.TransactionID_LENGTH);
			String local_TransactionID = String.valueOf(TransactionID_dup)+ erpFunObj.getTimeStamp();
			TransactionID = Long.valueOf(local_TransactionID);
		}catch(Exception e) {
			System.out.println("problem while setting TransactionID...........");
			e.printStackTrace();
		}
		
		Long InvoiceID = 0L;
		try {
			InvoiceID = billsRandFuncts.generateUniqueToken(httpSession,"InvoiceID",Constants.TransactionID_LENGTH);
		}catch(Exception e) {
			System.out.println("problem while setting TransactionID...........");
			e.printStackTrace();
		}
		
		System.out.println("I am going to print all the generated ID");
		System.out.println("BillNo is:"+BillNo+"----BOM_ID:"+BOM_ID+"--BillAmountID:"+BillAmountID+"--TransactionID:"+TransactionID+"--InvoiceID:"+InvoiceID);
		
		
		/*=====================================*/
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(params_data);
		JsonObject jsonObject = jsonTree.getAsJsonObject();
		
		System.out.println(jsonObject.get("gsttinnumber"));
		JsonElement selectedItemArray = jsonObject.get("selectedItemArray");
		JsonObject selectedItemArrayObj = selectedItemArray.getAsJsonObject();
        System.out.println("This is the selectedItemArray:");
        System.out.println(selectedItemArrayObj.toString());
		
		/*=====================================*/
		
		 
		String uuid_bills = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
		Pos_Outlet_Bills  tableBillsObj = new Pos_Outlet_Bills();
		tableBillsObj.setUuid(uuid_bills);
		tableBillsObj.setOutlet_uuid(outlet_uuids);
		tableBillsObj.setBillno(BillNo);
		tableBillsObj.setBOM_ID(BOM_ID);
		tableBillsObj.setBillAmountID(BillAmountID);
		tableBillsObj.setTransactionID(TransactionID);
		tableBillsObj.setInvoiceID(InvoiceID);
		tableBillsObj.setCreated_date(new Date());
		tableBillsObj.setCreated_by(user_uuid);
	  //tableBillsObj.setStatus(Pos_Outlet_Bills_Status.Active);
		
		Session session=null;
		
	  	/**
	  	 * Here even float variable also take an double because in entity we mentioned as double so....to avoid the casting
	  	 */
	  	
		
		/**
		 * For pos_outlet_bom_items & price changes(need to implement)
		 */
	  	Set<?> s =  selectedItemArrayObj.keySet();
	  	Iterator<?> i = s.iterator();
	    
	  	List<Pos_Outlet_Bom_Items> bomItemsObjList = new ArrayList<Pos_Outlet_Bom_Items>();	
	  	List<Pos_Outlet_PriceChange> priceChangesObjList = new ArrayList<Pos_Outlet_PriceChange>();	
	  	
	  	double item_dis = 0d;
	  	double item_adt_dis = 0d;
	  	double total_dis = 0d;
	  	do{
	        String key = i.next().toString();
	        System.out.println("This is the key:"+key);
	        JsonObject itemsRowData = selectedItemArrayObj.get(key).getAsJsonObject();
	        
	        double i_adt_dis_perc = 0d;
	        double i_adt_dis_amt = 0d;
	        double price_fordiscount = 0d;
	        try {
	        	i_adt_dis_perc = itemsRowData.get("additional_discount").getAsJsonObject().get("percentage").getAsDouble();
	        	i_adt_dis_amt =  itemsRowData.get("additional_discount").getAsJsonObject().get("amount").getAsDouble();
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	        item_adt_dis += i_adt_dis_amt;
	        double discount_amt = itemsRowData.get("discount").getAsJsonObject().get("amount").getAsDouble();
	        total_dis = discount_amt + i_adt_dis_amt;
	        double price_bd = 0d;
	        double original_price = 0d;
	        double price_bg = 0d;
	        double original_discount_perc = 0d;
	        double discount_perc = 0d;
	        double adt_discount_perc =0d;
	        double quantity = 0d;
	        double adt_discount_amt = 0d;
	        String item_uuid = "";
	        try {
	        	item_uuid = itemsRowData.get("item_id").getAsString();	
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        // double gst_perc = 0d;
	        double gst_perc = itemsRowData.get("gst").getAsJsonObject().get("all").getAsJsonObject().get("percentage").getAsDouble();
	        try {	   
	        	quantity = itemsRowData.get("qty").getAsDouble();
	        	original_price = (double)((itemsRowData.get("price").getAsDouble()/(gst_perc+100d))*100);
	        	price_bd = (double)(original_price * itemsRowData.get("qty").getAsDouble() * original_price); 
	        	price_fordiscount = (double)itemsRowData.get("price").getAsDouble() * original_price * itemsRowData.get("qty").getAsDouble();
	        	price_bg = (price_fordiscount)-(discount_amt + i_adt_dis_amt);
	        	original_discount_perc = itemsRowData.get("original_discount").getAsJsonObject().get("percentage").getAsDouble();
	        	discount_perc = itemsRowData.get("discount").getAsJsonObject().get("percentage").getAsDouble();
	        	adt_discount_perc = itemsRowData.get("additional_discount").getAsJsonObject().get("percentage").getAsDouble();
	        	adt_discount_amt =  itemsRowData.get("additional_discount").getAsJsonObject().get("amount").getAsDouble();
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	        //setting gst,cgst,sgst values	        
	        double gst_amt = 0d;
	        double cgst_amt = 0d;
	        double sgst_amt = 0d;
	        double gst_exclude = 0d;
	        double price_lastvalue = 0d;
	        double price_beforevalue = 0d;
	        double original_discount = 0d;
	        double priceForPortion = 0d;
	        try {
			priceForPortion = (double)(itemsRowData.get("price").getAsDouble());
	        }catch(Exception e) {
	        	System.out.println("portion price is missing");
	        }
	        try {
		        if(original_discount_perc > 0 || discount_perc>0 || adt_discount_perc>0) {
		        	gst_exclude = (price_bg/(gst_perc+100d))*100;
		        	gst_amt = (double)(gst_exclude*(gst_perc/100));
		        	cgst_amt = (double)(gst_amt/2);
		        	sgst_amt = (double)(gst_amt/2);     //both cgst_amt & sgst_amt carries half in the gst amount
		        	price_lastvalue = gst_exclude + gst_amt;
		        }else {
		        	gst_amt = itemsRowData.get("gst").getAsJsonObject().get("all").getAsJsonObject().get("amount").getAsDouble();
		            cgst_amt = itemsRowData.get("gst").getAsJsonObject().get("cgst").getAsJsonObject().get("amount").getAsDouble();
		            sgst_amt  = itemsRowData.get("gst").getAsJsonObject().get("sgst").getAsJsonObject().get("amount").getAsDouble();
		            price_beforevalue = original_price * itemsRowData.get("qty").getAsDouble();
		            price_lastvalue   = price_beforevalue +  gst_amt;
		            original_discount = itemsRowData.get("original_discount").getAsJsonObject().get("amount").getAsDouble();
		        } 
	        }catch(Exception e) {
	        	
	        }
	        
	        double cgst_perc = 0d;
	        double sgst_perc = 0d;
	        cgst_perc = itemsRowData.get("gst").getAsJsonObject().get("cgst").getAsJsonObject().get("percentage").getAsDouble();
	        sgst_perc = itemsRowData.get("gst").getAsJsonObject().get("sgst").getAsJsonObject().get("percentage").getAsDouble();
	        
	        
	        String bom_items_uuid = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
	        Pos_Outlet_Bom_Items bomItemsObj = new Pos_Outlet_Bom_Items();
	        bomItemsObj.setUuid(bom_items_uuid);
	        bomItemsObj.setOutlet_uuid(outlet_uuids);
	        bomItemsObj.setBillno(BillNo);
	        bomItemsObj.setBOM_ID(BOM_ID);
	        bomItemsObj.setItem_uuid(item_uuid);
	        bomItemsObj.setPortion_uuid(key);
	        bomItemsObj.setQuantity(quantity);
	        bomItemsObj.setOriginal_price(original_price);
	        bomItemsObj.setPrice_bd(price_bd);
	        bomItemsObj.setOriginal_discount_perc(original_discount_perc);
	        bomItemsObj.setOriginal_discount_amt(original_discount);
	        bomItemsObj.setDiscount_perc(discount_perc);
	        bomItemsObj.setDiscount_amt(discount_amt);
	        bomItemsObj.setAdt_discount_perc(adt_discount_perc);
	        bomItemsObj.setAdt_discount_amt(adt_discount_amt);
	        bomItemsObj.setPrice_bg(price_bg);
	        bomItemsObj.setGST_perc(gst_perc);
	        bomItemsObj.setGST_amt(gst_amt);
	        bomItemsObj.setCGST_perc(cgst_perc);
	        bomItemsObj.setCGST_amt(cgst_amt);
	        bomItemsObj.setSGST_perc(sgst_perc);
	        bomItemsObj.setSGST_amt(sgst_amt);
	        bomItemsObj.setIGST_perc(0d);
	        bomItemsObj.setIGST_amt(0d);
	        bomItemsObj.setPrice(price_lastvalue);
	        bomItemsObj.setCreated_date(new Date());
	        bomItemsObj.setCreated_by(user_uuid);
	        bomItemsObj.setBills_uuid(uuid_bills);
	   
	        bomItemsObjList.add(bomItemsObj);
	        
	        
	        
	        
	        
	        //adding price changes conditions
       double previous_price = 0d;
        try {
        	previous_price = ordersDao.getPreviousPriceForThisItemPortion(outlet_uuids,item_uuid,key);
        }catch(Exception e) {
        	System.out.println("price change history not found");
        }
        
        System.out.println("previous_price for this portion is:"+Math.round(previous_price)+" and "+"price for portion is:::"+Math.round(priceForPortion));
       /*    
        Pos_Outlet_PriceChange priceChangesObjs = new Pos_Outlet_PriceChange();
		System.out.println("Object created for Pos_Outlet_PriceChange");*/
		
        try {
        	if(previous_price > 0d) {
        		if((Double.compare(Math.round(previous_price), Math.round(priceForPortion)) != 0)|| ((i_adt_dis_perc !=0d)&&(i_adt_dis_perc>20d))) {
        			
        			System.out.println("=============@@@@@@@@@In if conditions@@@@@@@@@@===============");
        			String uuid_pricechanges = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
        			Pos_Outlet_PriceChange priceChangesObj = new Pos_Outlet_PriceChange();
        			System.out.println("Object created for Pos_Outlet_PriceChange in if conditions");
        			priceChangesObj.setUuid(uuid_pricechanges);
        			priceChangesObj.setOutlet_uuid(outlet_uuids);
        			priceChangesObj.setUser_uuid(user_uuid);
        			priceChangesObj.setItem_uuid(item_uuid);
        			priceChangesObj.setPortion_uuid(key);
        			priceChangesObj.setOld_priceval(previous_price);
        			priceChangesObj.setNew_priceval(priceForPortion);
        			priceChangesObj.setCreated_date(new Date());
        			priceChangesObj.setBillno(BillNo);
        			priceChangesObj.setAdt_perc_discval(i_adt_dis_perc);
        			priceChangesObj.setAdt_amt_discval(i_adt_dis_amt);
        			priceChangesObj.setItem_perc_discval(discount_perc);
        			priceChangesObj.setItem_amt_discval(discount_amt);
        			priceChangesObj.setCreated_by(user_uuid);
        			//priceChangesObj.setStatus();
        			priceChangesObj.setUpdated_date(new Date());
        			priceChangesObj.setUpdated_by(user_uuid);
        			priceChangesObj.setBills_uuid(uuid_bills);
        			
        			
        			priceChangesObjList.add(priceChangesObj);
        		}
        	}
        }catch(Exception e) {
        	
        }

	        	        
	        
	    }while(i.hasNext());
		//end of do-while
	  	
	  	
	    System.out.println("================priceChangesObjList Bean is =====================");
	    priceChangesObjList.forEach(System.out::println);
	    System.out.println("=====================================================");
	  	
	  	
	  	
	  	/**
	  	 * For pos_outlet_bom_amount
	  	 */
	  	
	  	JsonElement selectedItemstot = jsonObject.get("selectedItemstot");
		JsonObject selectedItemstotObj = selectedItemstot.getAsJsonObject();
		
		JsonElement overall_bill_disc = jsonObject.get("overall_bill_disc");
	    JsonObject overall_bill_discObj = overall_bill_disc.getAsJsonObject();
		
		double tot_price_bd = 0d;
		double ovrl_bill_dis_perc = 0d;
		double ovrl_bill_dis_amt = 0d;
		
		try {
			if(overall_bill_discObj.isJsonNull() == false) {
				ovrl_bill_dis_perc = overall_bill_discObj.get("percentage").getAsDouble();
				ovrl_bill_dis_amt  = overall_bill_discObj.get("amount").getAsDouble();
			}
		}catch(NullPointerException n) {
			System.out.println("Null pointer exception occured with overall bill discounts");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		double bill_amt = 0d;
	     try {
	    	 bill_amt = selectedItemstotObj.get("tot_price").getAsDouble();
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
		
		
		String uuid_bom_amounts = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
		
		Pos_Outlet_Bom_Amounts bomAmountsObj = new Pos_Outlet_Bom_Amounts();
		bomAmountsObj.setUuid(uuid_bom_amounts);
		bomAmountsObj.setOutlet_uuid(outlet_uuids);
		bomAmountsObj.setBillno(BillNo);
		bomAmountsObj.setBillAmountID(BOM_ID);
		bomAmountsObj.setTotal_items(selectedItemstotObj.get("qty_val").getAsInt());
		bomAmountsObj.setTotal_price_bd(tot_price_bd);
		bomAmountsObj.setItems_discount_amt(item_dis);
		bomAmountsObj.setBill_discount_perc(ovrl_bill_dis_perc);
		bomAmountsObj.setBill_discount_amt(ovrl_bill_dis_amt);
		bomAmountsObj.setTotal_discount_amt(total_dis);
	  	bomAmountsObj.setTotal_price_bg(selectedItemstotObj.get("tot_before_gst").getAsDouble());
	  	bomAmountsObj.setTotal_gst_amt(selectedItemstotObj.get("tot_gst_amout").getAsDouble());
	  	bomAmountsObj.setTotal_cgst_amt(selectedItemstotObj.get("tot_cgst_amout").getAsDouble());
	  	bomAmountsObj.setTotal_sgst_amt(selectedItemstotObj.get("tot_sgst_amout").getAsDouble());
	  	bomAmountsObj.setTotal_igst_amt(0d);
	  	bomAmountsObj.setTotal_price(bill_amt);
	  	bomAmountsObj.setCreated_date(new Date());
	  	bomAmountsObj.setCreated_by(user_uuid);
	  	bomAmountsObj.setUpdated_date(new Date());
	  	//bomAmountsObj.setStatus(status);
		bomAmountsObj.setBills_uuid(uuid_bills);
		
	
	    String payment_mode_val = "cash";
	    try {
	    	payment_mode_val = jsonObject.get("payment_mode_val").getAsString();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
		System.out.println("payment value is:"+payment_mode_val);
		
		String customer_name ="";
		String customer_address="";
		int addOns = 0;
		try {
			customer_name = jsonObject.get("customer_name").getAsString();
			customer_address = jsonObject.get("cust_address").getAsString();
			addOns = Integer.parseInt(jsonObject.get("addons").getAsString());
		}catch(Exception e) {
			System.out.println("error to set the customer name or address ");
			e.printStackTrace();
		}
		
		double selectedItemsAdvance = 0d;
		try {
			selectedItemsAdvance = jsonObject.get("selectedItemsadvance").getAsDouble();
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		/**
		 * when transaction type is cash then we will insert transaction type as cash
		 * in_preOrders & bom_transaction table
		 */
		
		String transactionType = "Cash";
		String card_type = null;
		try {
			if(payment_mode_val.equals("cash")) {
				transactionType = "Cash";
			}
			if(payment_mode_val.equals("card")) {
				transactionType = "Card";
				card_type = jsonObject.get("cardtype_mode").getAsString();
				
			}
			if(payment_mode_val.equals("ewallet")) {
				transactionType = "Ewallet";
				card_type = jsonObject.get("cardtype_mode").getAsString();
			}
		}catch(NumberFormatException nfe) {
			nfe.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String order_type = "";
		try {
			order_type = jsonObject.get("trans_type").getAsString();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Pos_Outlet_PreOrders preOrdersObj = new Pos_Outlet_PreOrders();
		
		Pos_Outlet_BTOBOrders btobOrdersObj = new Pos_Outlet_BTOBOrders();
		
		//  don't forget to convert string into the date format
		//  preOrdersObj.setDelivery_datetime(delivery_datetime);
		String deliveryDateTimeStr = null;
		Date deliveryDateTime = null;
		try {
				deliveryDateTimeStr = jsonObject.get("deliverydatetime").getAsString();
				deliveryDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(deliveryDateTimeStr);
		        System.out.println(deliveryDateTime);
		}catch(Exception e) {
				e.printStackTrace();
		}
			
		System.out.println("******************************************************");
		System.out.println(deliveryDateTime);
		System.out.println("******************************************************");
		

		String gsttin = null;
		try {
			gsttin = jsonObject.get("gsttinnumber").getAsString();
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("gst is"+gsttin);
		
		if(gsttin!=null) {
			System.out.println("if caseeeeeeeeeeeeeee");
			String btoborders_uuid = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
			btobOrdersObj.setUuid(btoborders_uuid);
			btobOrdersObj.setOutlet_uuid(outlet_uuids);
			btobOrdersObj.setCreated_datetime(new Date());
			btobOrdersObj.setCreated_by(user_uuid);
			btobOrdersObj.setCustomer_name(customer_name);
			btobOrdersObj.setPhonenumber(jsonObject.get("phone_num").getAsString());
			btobOrdersObj.setDelivery_datetime(deliveryDateTime);
			btobOrdersObj.setTotal_amount(bill_amt);
			btobOrdersObj.setBillno(BillNo);
			btobOrdersObj.setAdvance(selectedItemsAdvance);
			btobOrdersObj.setTransaction_type(transactionType);
			btobOrdersObj.setTransactionID(TransactionID);
			btobOrdersObj.setGsttinnumber(gsttin);
			//btobOrdersObj.setOrderstatus(BTOBOrders_status.pending);
			//btobOrdersObj.setStatus(BTOB_status.Active);
			btobOrdersObj.setCust_address(customer_address);
			btobOrdersObj.setCreditorsFlag(0);
			btobOrdersObj.setBills_uuid(uuid_bills);
			
			System.out.println("***************************");
			System.out.println(btobOrdersObj.toString());
			System.out.println("***************************");
			
		}
		else {
			System.out.println("else caseeeeeeeeeee");
			String preOrders_uuid = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
			preOrdersObj.setUuid(preOrders_uuid);
			preOrdersObj.setCustomer_name(customer_name);
			preOrdersObj.setPhonenumber(jsonObject.get("phone_num").getAsString());
			preOrdersObj.setTotal_Amount(bill_amt);
			preOrdersObj.setOutlet_uuid(outlet_uuids);
			preOrdersObj.setBillNo(BillNo);
			preOrdersObj.setTransactionID(TransactionID);
			preOrdersObj.setTransaction_type(transactionType);
			preOrdersObj.setAdvance(selectedItemsAdvance);
			preOrdersObj.setCreated_datetime(new Date());
			preOrdersObj.setCreated_by(user_uuid);
			preOrdersObj.setDelivery_datetime(deliveryDateTime);
			//preOrdersObj.setOrderstatus(PreOrders_OrderStatus.Pending);
			//preOrdersObj.setStatus()
			preOrdersObj.setCust_address(customer_address);
			preOrdersObj.setAddons(addOns);
			preOrdersObj.setCreditorsFlag(0);
			preOrdersObj.setBills_uuid(uuid_bills);
			
			System.out.println("***************************");
			System.out.println(preOrdersObj.toString());
			System.out.println("***************************");
		}
		
	    /**
		 * for table: pos_outlet_bom transaction
		 */		 		
		List<Pos_Outlet_Bom_Transaction> bomtransObjList = new ArrayList<Pos_Outlet_Bom_Transaction>();
		Pos_Outlet_Bom_Transaction bomTransObj = new Pos_Outlet_Bom_Transaction();
		
		String bomTrans_uuid = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
		bomTransObj.setUuid(bomTrans_uuid);
		bomTransObj.setOutlet_uuid(outlet_uuids);
		bomTransObj.setBillno(BillNo);
		bomTransObj.setBillAmountID(BillAmountID);
		bomTransObj.setTransactionID(TransactionID);
		bomTransObj.setTransaction_type(transactionType);
		bomTransObj.setAmount(selectedItemsAdvance);
		bomTransObj.setCreated_date(new Date());
		bomTransObj.setCreated_by(user_uuid);
		bomTransObj.setCard_type(card_type);
		//bomTransObj.setStatus(status);
		bomTransObj.setUpdated_date(new Date());
		bomTransObj.setUpdated_by(user_uuid);
	 	bomTransObj.setOrder_type(order_type);
		bomTransObj.setBills_uuid(uuid_bills);
		
		System.out.println("***************************");
		System.out.println(bomTransObj.toString());
		System.out.println("***************************");
	
		bomtransObjList.add(bomTransObj);
		
		/**
		 * For table: pos_outlet_proformabills
		 */
				
		double balance_amt = 0d;
		balance_amt =  (double)(bill_amt - selectedItemsAdvance);
		
		Pos_Outlet_ProformaBills proformaBillsObj = new Pos_Outlet_ProformaBills();
		String proforma_uuid = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
		proformaBillsObj.setUuid(proforma_uuid);
		proformaBillsObj.setOutlet_uuid(outlet_uuids);
		proformaBillsObj.setBillno(BillNo);
		proformaBillsObj.setTransactionID(TransactionID);
		proformaBillsObj.setInvoiceID(InvoiceID);
		proformaBillsObj.setBill_amt(bill_amt);
		proformaBillsObj.setPaid_amt(selectedItemsAdvance);
		proformaBillsObj.setBalance_amt(balance_amt);
		//proformaBillsObj.setIp_address("");
		proformaBillsObj.setCreated_date(new Date());
		proformaBillsObj.setCreated_by(user_uuid);
		//proformaBillsObj.setStatus()
		proformaBillsObj.setUpdated_date(new Date());
		proformaBillsObj.setUpdated_by(user_uuid);
		proformaBillsObj.setBills_uuid(uuid_bills);
		
	    //Transaction transaction = null;
        session=null;
        String items_grid = "";
        String linux_grid = "";
		try {
			session=sessionFactory.openSession();
			//transaction = (Transaction) session.beginTransaction();
		    session.beginTransaction();
		    session.save(tableBillsObj);
			for (Pos_Outlet_Bom_Items bomItemObj : bomItemsObjList){
			      session.save(bomItemObj);
			}
			
			
			for (Pos_Outlet_PriceChange priceChangeObj : priceChangesObjList){
			      session.save(priceChangeObj);
			}
			
		    session.save(bomAmountsObj);
		    
		    if(gsttin!=null) {
		    	System.out.println("if case save");
		    	 session.save(btobOrdersObj);
			}else {
				System.out.println("else case save");
		        session.save(preOrdersObj);
			}
		    
		    session.save(bomTransObj);
		    session.save(proformaBillsObj);
	        session.getTransaction().commit();
	        System.out.println("**********************Success fully Commited**********************");

	        System.out.println("*************Designing the bill********************");
		    String billno = String.valueOf(BillNo);
	        String year=Constants.year;
			String commonInBill = "88"+year+outlet_uuid;
			long presentBillNo = Long.valueOf(billno.replace(commonInBill,""));
			
			/*****designing the company details********/
			String getCompanyDetails = commonMethodsObj.companyDetails(outlet_uuids);
			String[] compSplit = getCompanyDetails.split("@");
			

			if(gsttin!=null) {
				items_grid = compSplit[0];	
			    linux_grid = compSplit[1];	
				
				/**********for windows***************/
				items_grid += "<input type=\"hidden\" name=\"gsttin_number\" value="+gsttin+" id=\"gsttin_number\">\n" + 
					      "<input type=\"hidden\" name=\"billnumber_forreceipt\" value="+"B-"+presentBillNo+" id=\"billnumber_forreceipt\">";
		    
			    items_grid += "<span class=\"billtitle\">ADVANCE RECEIPT VOUCHER INVOICE</span> </td>\n"+
			    			  "</tr>\n"+
			    			  "</table><span>----------------------------------------------------------------------------</span>\n"+
			    			  "<table  border=\"0\" width=\"100%\">\n"+
			    			  "<tr>\n"+
			    			  "<td style=\"font-size:17px;\"><strong>"+new Date()+"</strong></td>\n<td  style=\"width: 20%;\">&nbsp;</td>\n"+
			    			  "<td align=\"center\"  style=\"font-weight:bold;font-size:17px !important;\">INVOICE NO</td>\n"+
			    			  "</tr><tr>\n"+
			    			  "<td style=\"font-size:17px !important;\"><strong>Cashier:</strong>"+httpSession.getAttribute("UserName")+"</td>\n"+
			    			  "<td  style=\"width: 23%;\">&nbsp;</td><td align=\"center\" style=\" font-size:20px;\">B-"+presentBillNo+"</td>\n"+
			    			  "</tr>\n"+
			    			  "<tr><td  style=\"width: 23%;text-align:left;\" colspan=\"3\"><strong>Delivery Date&Time:</strong>"+deliveryDateTime+
			    			  "</td></tr>\n"+
			    			  "</table>\n";
			    
			    items_grid += "<span>----------------------------------------------------------------------------</span>\n"
  		  		   		+"<table  border=\"0\" width=\"100%\"><tr><td style=\"font-size:17px;\"><strong>Buyer GSTIN Number :"+gsttin+"</strong></td>\n"
  		  		   		+ "<td  style=\"width: 20%;\">&nbsp;</td>"
  		  		   		+ "<td align=\"center\"  style=\"font-weight:bold;font-size:17px !important;\">Buyer Address</td><td align=\"center\" style=\" font-size:20px;\">"+customer_address+"</td></tr>\n"
  		  		   		+ "<tr><td style=\" font-size:17px !important;\"><strong>Place Of Supply:</strong>AndhraPradesh, Vizag</td><td  style=\"width: 23%;\">&nbsp;</td>\n"
  		  		   		+ "</tr>\n"
  		  		   		+ "</table>\n";
			    
			    /******************************Customer Details For Linux***********************************/
			    
			    linux_grid += Constants.center+"\u001b!\u0008"+"ADVANCE RECEIPT VOUCHER INVOICE"+"\u001b!\u0000"+"\n";  // Bill name
			    linux_grid += "------------------------------------------\n";
			    linux_grid += "\u001b!\u0008"+new Date()+"\u001b!\u0000"+"\t";
			    linux_grid += "\u001b!\u0008"+"INVOICE NO"+"\u001b!\u0000"+"\n";
			    linux_grid += Constants.left+"\u001b!\u0008"+"Cashier:"+"\u001b!\u0000";
			    linux_grid += httpSession.getAttribute("UserName")+"\t\t";
			    linux_grid += "\u001b!\u0008"+presentBillNo+"\u001b!\u0000"+"\n";
			    linux_grid += "\u001b!\u0008"+"Delivery Date&Time:"+"\u001b!\u0000"+deliveryDateTime+"\n";
			    
			    linux_grid += "------------------------------------------\n";
			    linux_grid += Constants.left+"\u001b!\u0008"+"Buyer GSTIN Number : "+"\u001b!\u0000"+"\t";
			    linux_grid += gsttin+"\n";
			    linux_grid += Constants.left+"\u001b!\u0008"+"Buyer Address : "+"\u001b!\u0000";
			    linux_grid += customer_address+"\n";
			    linux_grid += Constants.left+"\u001b!\u0008"+"Place Of Supply : "+"\u001b!\u0000"+"\t";
                linux_grid += "AndhraPradesh, Vizag"+"\n";
			    
			    
			}
			
			else {
				items_grid = compSplit[0];	
			    linux_grid = compSplit[1];	
				/**********for windows***************/
			    items_grid += "<span class=\"billtitle\">PROFORMA INVOICE</span> </td>\n"+
			    			  "</tr>\n"+
			    			  "</table><span>----------------------------------------------------------------------------</span>\n"+
			    			  "<table  border=\"0\" width=\"100%\">\n"+
			    			  "<tr>\n"+
			    			  "<td style=\"font-size:17px;\"><strong>"+new Date()+"</strong></td>\n<td  style=\"width: 20%;\">&nbsp;</td>\n"+
			    			  "<td align=\"center\"  style=\"font-weight:bold;font-size:17px !important;\">INVOICE NO</td>\n"+
			    			  "</tr><tr>\n"+
			    			  "<td style=\"font-size:17px !important;\"><strong>Cashier:</strong>"+httpSession.getAttribute("UserName")+"</td>\n"+
			    			  "<td  style=\"width: 23%;\">&nbsp;</td><td align=\"center\" style=\" font-size:20px;\">P-"+presentBillNo+"</td>\n"+
			    			  "</tr>\n"+
			    			  "<tr><td  style=\"width: 23%;text-align:left;\" colspan=\"3\"><strong>Delivery Date&Time:</strong>"+deliveryDateTime+
			    			  "</td></tr>\n"+
			    			  "</table>\n";
			    
			    
			    /**********for Linux***************/
			    linux_grid += Constants.center+"\u001b!\u0008"+"PROFORMA INVOICE"+"\u001b!\u0000"+"\n";  // Bill name
			    linux_grid += "------------------------------------------\n";
			    linux_grid += Constants.left+"\u001b!\u0008"+new Date()+"\u001b!\u0000"+"\t";
			    linux_grid += "\u001b!\u0008"+"INVOICE NO"+"\u001b!\u0000"+"\n";
			    linux_grid += Constants.left+"\u001b!\u0008"+"Cashier:"+"\u001b!\u0000";
			    linux_grid += httpSession.getAttribute("UserName")+"\t\t ";
			    linux_grid += "\u001b!\u0008"+presentBillNo+"\u001b!\u0000"+"\n";
			    linux_grid += "\u001b!\u0008"+"Delivery Date&Time:"+"\u001b!\u0000";
			    linux_grid += deliveryDateTime+"\n";
		    
			}
		    /*********designing items and token*******/
		    int token_flag = 0;
	    	String token_grid = null;
	    	String linux_token_grid = null;
	    	Double returnAmount = 0d;
		    try {
		    	token_flag = jsonObject.get("token_flag").getAsInt();
		    	String items_data = commonMethodsObj.ItemsDetails(bomItemsObjList, BillNo, outlet_uuids, returnAmount, token_flag);
		    	String[] parts = items_data.split("@", 2);
		    	
		    	/*******windows part*********/
		    	String part1 = parts[0];
		    	String[] w_parts = part1.split(",",2);
		    	items_grid += w_parts[0];
		    	token_grid = w_parts[1];
		    	
		    	/*********linux part**********/
		    	String part2 = parts[1];
		    	String[] l_parts = part2.split("@@",2);
		    	linux_grid += l_parts[0];
		    	linux_token_grid = l_parts[1];
		    	
		    }catch(Exception e) {
		    	token_flag = 0;
		    	String items_details = commonMethodsObj.ItemsDetails(bomItemsObjList, BillNo, outlet_uuids, returnAmount, token_flag);
		    	String[] itemsSplit = items_details.split("@");
		    	items_grid += itemsSplit[0];
		    	linux_grid += itemsSplit[1];
		    }
		    
		    
		    /*****designing transaction details********/
		    double balance_amtcheck = proformaBillsObj.getBalance_amt();
		    String preOrderAmountDetails = commonMethodsObj.getpreOrderAmountDetails(balance_amtcheck, bomtransObjList);
		    String [] preOrderAmountParts = preOrderAmountDetails.split("@",2);

		    /************for windows************/
		    items_grid += preOrderAmountParts[0];
		    
		    /************for linux************/
		    linux_grid += preOrderAmountParts[1];
		    
		    
		    String gst_details = "";
		    
		    /*********************b to border gst details********/
		    if(gsttin!=null) {
		    	int btobordresCount = 1;
		    	gst_details = commonMethodsObj.gstDetails(bomItemsObjList, btobordresCount);
		    }
		    
		    /*********************pre border gst details********/
		    else {
		    	int btobordresCount = 0;
		    	gst_details = commonMethodsObj.gstDetails(bomItemsObjList, btobordresCount);
		    }
		    
            String[] gst_parts = gst_details.split("@",2);
		    items_grid += gst_parts[0];
		    linux_grid += gst_parts[1];
		    linux_grid += Constants.cutpaper; 
		    
		    String ipAddress = request.getRemoteAddr();
	        System.out.println("**Requsted system IP Address : ***" + 
	                             ipAddress+"*************"); 

		    
		    
		    String os_type = erpFunObj.findOs(request);
		      
		      if(token_flag == 1) {
		    	  items_grid += token_grid;
		    	  linux_grid += linux_token_grid;
		    	  
		    	  if(os_type == "Linux" && gsttin==null) {
		    		  
		    		  /****** server to server*************/
		    		  if(ipAddress.equals("0:0:0:0:0:0:0:1")) {
		    			  String server_print_status = erpFunObj.sendingPrinter(linux_grid);
		    			  return "Linux"+"@"+server_print_status;
		    		  }
		    		  /****** server to node*************/
		    		  else {
		    			  String node_print_status = erpFunObj.nodePrinter(ipAddress, linux_grid);
		    			  return "Linux"+"@"+node_print_status;
		    		  }
		    	  }
		    	  
		    	  return items_grid;
		      }else {
		    	  
		    	  if(os_type == "Linux" && gsttin==null) {
		    		  
		    		  /****** server to server*************/
		    		  if(ipAddress.equals("0:0:0:0:0:0:0:1")) {
		    			  String server_print_status = erpFunObj.sendingPrinter(linux_grid);
		    			  return "Linux"+"@"+server_print_status;
		    		  }
		    		  /****** server to node*************/
		    		  else {
		    			  String node_print_status = erpFunObj.nodePrinter(ipAddress, linux_grid);
		    			  return "Linux"+"@"+node_print_status;
		    		  }
		    		  
		    	  }
		    	  return items_grid;
		      }
	      /***************end of the print code*****************************/
		} catch(Exception sqlException) {
			if(null != session.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Backed");
				session.getTransaction().rollback();
				items_grid = "Failed";
				return items_grid;
			}
			sqlException.printStackTrace();
		}finally {
			session.close();
		}
	 return items_grid;				     
}
	

	/**
	 * Getting delivery orders details
	 */	
	
	public String getHoursDeliveryData(HttpServletRequest request, HttpSession session) {
		
		System.out.println("I am here to get the delivery data");
		String hours = "";
		String outlet_uuid ="";
		try {
		   hours = (String)request.getParameter("hoursvalue");
		   outlet_uuid = (String)session.getAttribute("Outlet_uuid");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<DeliveryOrdersBean> deliveryOrdersList = new ArrayList<DeliveryOrdersBean>();
		
		try {
			if(hours.equals("all")) {
				deliveryOrdersList =  ordersDao.getHoursDeliveryData(hours,outlet_uuid);
			}else {
				deliveryOrdersList =  ordersDao.getHoursDeliveryData(hours,outlet_uuid);
			}
		}catch(Exception e) {
			System.out.println("Problem with getHoursDeliveryData dao calling");
			e.printStackTrace();
		}	
		
		 String items_grid = null;
		 
		
         items_grid = "<style>table.scroll { width: 100%;border-spacing: 0;}table.scroll tbody,table.scroll thead { display: block; }table.scroll thead tr th {height: 30px;line-height: 15px;}table.scroll tbody {height: 290px;overflow-y: auto;overflow-x: hidden;}table.scroll tbody td, table.scroll thead th {width: 2%;}table.scroll tbody td:last-child, table.scroll thead th:last-child {border-right: none;}.containerstyle{width:100%;}#deliveryorderslist_table th{text-align:center;}#deliveryliststyle{font-weight:bold;text-decoration:underline;}</style><script>$(document).ready( function () {$(\"#deliveryorderslist_table\").DataTable({\"pageLength\": 2,\"bLengthChange\": false,\"bInfo\": true,searching: true});});</script><div class=\"containerstyle\">";
         
         if (hours.equals("all")) {
	            items_grid += "<lable id=\"deliveryliststyle\">All Delivery List</lable><br>";
	        } else {
	            items_grid += "<lable id=\"deliveryliststyle\">Delivery List With In "+hours+" Hours</lable><br>";
	        }
        
         
         items_grid += "<br>";
         
         if(deliveryOrdersList.size()==0) {
        	 items_grid += "<lable>No Deliveries To This Time</lable>";
         }else {
        	 items_grid += "<table border=\"1\" id=\"deliveryorderslist_table\" width=\"100%\" class=\"\"><thead><tr><th>sno</th><th>Name</th><th>Contact</th><th>Delivery Time</th><th>Action</th></tr></thead>";
        int count  = 1;
         
         for(DeliveryOrdersBean deliveryBean : deliveryOrdersList) {
        	 items_grid += "<tr><td align=\"center\">"+ count + "</td><td>"+ deliveryBean.getCustomer_name() +"</td><td>"+ deliveryBean.getPhonenumber()+"</td><td>"+ deliveryBean.getDelivery_datetime()+"</td>";        	 
         
              if(deliveryBean.getBillNo()!=0L) {
            	//items_grid += "<td><img title=\"View Order\" src=\"images/preview.png\" id=\"btn_deliveryvieworder\" width=\"40px\" height=\"40px\"  data-id=\""+ deliveryBean.getBillNo() +"\"><img title=\"Print Order\" src=\"images/print.ico\" id=\"btn_deliveryprintorder\" width=\"33px\" height=\"40px\" data-id=\"" + deliveryBean.getBillNo() +"\"></td></tr>";
            	  items_grid += "<td><button id=\"btn_deliveryvieworder\" width=\"40px\" height=\"40px\"  data-id=\""+ deliveryBean.getBillNo() +"\">Edit</button><button value=\"print\" id=\"btn_deliveryprintorder\" width=\"33px\" height=\"40px\" data-id=\"" + deliveryBean.getBillNo() +"\">print</button></td></tr>";
              }
              
              count++;
         }
         items_grid += "</table><button type=\"button\" class=\"btn btn-primary close_delivery\">Close</button></div>";
         }
		return items_grid;
	}
	
	
	public String getReturndate(HttpServletRequest request, HttpSession session) {
		System.out.println("in order serviceimpl get return data");
		String billno = (String)request.getParameter("Billno");
	    System.out.println("request bil no is:"+billno);
	    System.out.println("adding current year for billno::"+billno);
	    
	    String current_year=Constants.year;
	    try {
	    	current_year = erpFunObj.getLocalDataInfo("current_year");
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
	    System.out.println("current year is:"+current_year);
	    String cmplBillNo = current_year+billno;
	    //String cmplBillNo = billno;
	    System.out.println("complete Billno after current year is :"+cmplBillNo);
	    
	    Date present_Date = new Date();
	    final long hoursInMillis = 60L * 60L * 1000L;
        Date twoDaysPrevious_Date = new Date(present_Date.getTime() + (-48L * hoursInMillis)); // substracts 48 hours
	    Boolean validForReturn = false;
	    try {
	    validForReturn  = ordersDao.getReturndate(cmplBillNo,present_Date,twoDaysPrevious_Date);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
	    if(validForReturn==true) {
	    	return "1";
	    }else {
	    	return "0";
	    }
	    
	}
	
	public String generateReciptReturnView(HttpServletRequest request, HttpSession session) {
		System.out.println("in service of generateReciptReturnView");
		String returnBillNo = (String)request.getParameter("orderIDreturn");
		System.out.println("bill without outlet:"+returnBillNo);
		String billno = (String)request.getParameter("Billno");
	    System.out.println("request bil no with outlet is:"+billno); 
	   
	    String outlet_uuid ="";
	    String user_name ="";
		try {
		   outlet_uuid = (String)session.getAttribute("Outlet_uuid");
		   user_name = (String)session.getAttribute("UserName");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String current_year=Constants.year;
		    try {
		    	current_year = erpFunObj.getLocalDataInfo("current_year");
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
		
		billno = current_year+billno;
		
		//getting invoice result
		List<Pos_Outlet_Bom_Invoice> bomInvoiceResult = new ArrayList<Pos_Outlet_Bom_Invoice>();
		try {
		   bomInvoiceResult = ordersDao.getBillnoInvoiceDetails(billno, outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		//getting bills result
		List<Pos_Outlet_Bills> billsResult = new ArrayList<Pos_Outlet_Bills>();
		try {
			//billsResult = 
			billsResult = ordersDao.getBillsData(billno, outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Pos_Outlet_Bom_Transaction> bomTransactionList = new ArrayList<Pos_Outlet_Bom_Transaction>();
		try {
			bomTransactionList = ordersDao.getTransactionsData(billno, outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Pos_Outlet_PreOrders> preOrdersList = new ArrayList<Pos_Outlet_PreOrders>();
		try {
			preOrdersList=ordersDao.getPreOrdersData(billno, outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<ReturnViewBean> returnViewList = new ArrayList<ReturnViewBean>();
		try { 
			returnViewList = ordersDao.getCompleteBillDetails(billno, outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//need to add price and status in the bean of return bean
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		String items_grid = "<style>\n" + 
				".container{font-family: sans-serif;height:100%;background: #FFF;font-size: 9.5pt;}\n" + 
				".divTableRow1 {display: table-row;}\n" + 
				".divTableHeading1 {background-color: #EEE;display: table-header-group;}\n" + 
				".divTableCell1 {font-size: 14px;display: table-cell;padding: 3px 10px;}\n" + 
				".divTableCell2{display: table-cell;padding-left: 40px;}\n" + 
				".divTableCell3{display: table-cell;padding-left: 65px;}\n" + 
				".divTableCell4{display: table-cell;padding-left: 70px;}\n" + 
				".divTableHead1 {font-size: 11px;display: table-cell;padding: 1px 11px;}	\n" + 
				".divTable{col:40;}\n" + 
				".divTableRow {display: table-row;}\n" + 
				".divTableHeading {background-color: #EEE;display: table-header-group;}\n" + 
				".divTableCell {font-size: 12px;display: table-cell;}\n" + 
				".itemCell {font-size: 12px;width: 120px;font-weight:bold;padding: 3px 2px;}\n" + 
				".divTableHead {font-size: 20px;display: table-cell;padding: 3px 3px;}\n" + 
				".divTableHeading {background-color: #EEE;display: table-header-group;font-weight: bold;}\n" + 
				".divTableFoot {background-color: #EEE;display: table-footer-group;font-weight: bold;}\n" + 
				".divTableBody {display: table-row-group;}\n" + 
				".lastright { padding-left: 72px;}\n" + 
				".lastcolumn {padding-left: 10px;}	\n" + 
				".price {padding-left: 0px;}\n" + 
				".hsncode{font-size: 10px;}\n" + 
				".header{col:80;text-align:center;}\n" + 
				".header h2{text-decoration:underline;}\n" + 
				".textright{padding-left: 106px;}\n" + 
				".gstright{margin-left: 147px;}\n" + 
				".netright{margin-left: 106px;}\n" + 
				".gstright1{margin-left: 68px;}\n" + 
				".subtotal{padding-left: 77px;}\n" + 
				".billtitle{font-size: 20px;}\n" + 
				".cashright{margin-left: 159px;}\n" + 
				".fottertitle{padding-left: 70px;}\n" + 
				".divTableCellmain{font-size: 14px;display: table-cell;text-align:center;padding-left:75px;}\n" + 
				".Bighr{color:#000 !important;width: 40% !important;}\n" + 
				".divTableHeadnew{font-size:15px;display: table-cell;padding: 3px 3px;}\n" + 
				"#orderdetailstable,#gstdetailstable{border-color:#eee !important;}\n" + 
				"#gstdetailstable_previous,#gstdetailstable_next{display:none;}\n" + 
				"</style>\n" + 
				"<script>\n" + 
				"$(document).ready( function () {\n" + 
				"    $(\"#orderdetailstable\").DataTable({\n" + 
				"		\"pageLength\": 2,\n" + 
				"		\"bLengthChange\": false,\n" + 
				"		\"bInfo\": false,\n" + 
				"		searching: false\n" + 
				"	});\n" + 
				"	$(\"#gstdetailstable\").DataTable({\n" + 
				"		\"pageLength\": 2,\n" + 
				"		\"bLengthChange\": false,\n" + 
				"		\"bInfo\": false,\n" + 
				"		searching: false\n" + 
				"	});\n" + 
				"} );\n" + 
				"</script>\n" + 
				"<div class=\"divTable\">\n" + 
				"<div class=\"divTableBody\">\n"+ 
				"<div class=\"divTableRow\">";
		
		//need to build condition with preorder case
		
		items_grid +="<div class=\"\"><strong>Date:</strong>"+bomTransactionList.get(0).getCreated_date()+" <strong>Cashier:</strong>"+user_name+"</div>"; 
		
		items_grid+="<div class=\"\"><span><strong>Inovice No:</strong>"+returnBillNo+"</span></div>\n" + 
				"</div><input type=\"hidden\" value=\"" + billno +"\" name=\"orderprint_value\" id=\"orderprint_value\">\n" + 
				"</div></div><div id=\"inner_tablestyle\">\n" + 
				"<table border=\"1\" id=\"orderdetailstable\">\n" + 
				"<thead>\n" + 
				"<th>Product</th><th>Qty</th><th>Unit</th><th>Pc.</th><th>Amt.</th><th>Action</th>\n" + 
				"</thead>";
		
		double subtotal_without_tax = 0d;
        double net_total = 0d;
        double subtotal = 0d;
        for(ReturnViewBean returnBean : returnViewList) {
        	
          double inclusiveprice = 0d;
          double pricewithquant = 0d;
          double cgst_perc = (double)returnBean.getGST_perc() / 2;
          try {
        	  inclusiveprice =  (double)(returnBean.getOriginal_price()*((returnBean.getGST_perc()+100)/100d));
        	  pricewithquant = inclusiveprice * returnBean.getQuantity();
        	  
          }catch(Exception e) {
        	  System.out.println("error for setting inclusingprice or pricewithquant");
        	  e.printStackTrace();
          }
          
    	  
     	 if(returnBean.getUuid().equals("10000"))
     			returnBean.setItem_name("OPEN ORDER-1");
         	else if(returnBean.getUuid().equals("20000"))
         		returnBean.setItem_name("OPEN ORDER-2");
         	else if(returnBean.getUuid().equals("30000"))
         		returnBean.setItem_name("OPEN ORDER-3");
     	
         	if(returnBean.getPortion_uuid().equals("11111"))
         		returnBean.setPortion_name("KG");
         	else if(returnBean.getPortion_uuid().equals("22222"))
         		returnBean.setPortion_name("PC");
         	else if(returnBean.getPortion_uuid().equals("33333"))
         		returnBean.setPortion_name("Unit");
                
         /*	
          *	  	
         	if(returnBean.getItemID() >= 10000) {
         		if(returnBean.getGST_perc() == 0)
         			returnBean.setCode(Integer.toString(1905));
         		if(bomitem.getGST_perc() == 5 || bomitem.getGST_perc() == 18)
         			pos_itemsBean.setCode(Integer.toString(2106));
         		if(bomitem.getGST_perc() == 12)
         			pos_itemsBean.setCode(Integer.toString(2008));
         		if(bomitem.getGST_perc() == 28)
         			pos_itemsBean.setCode(Integer.toString(1806));
         	}
           */
     	   
          
          
          
          
          items_grid+="<tr><td  align=\"center\">"+returnBean.getItem_name()+"<br /><span class=\"hsncode\">[GST " + df.format((double)returnBean.getGST_perc())  + "% HSN:" + returnBean.getHsncode() + "]</span></td><td  align=\"center\" >"+    df.format((double)returnBean.getQuantity())  + "</td><td>"+returnBean.getPortion_name()+"<br /><span class=\"hsncode\">SGST "+ df.format((double)cgst_perc) +"%&nbsp;&nbsp;&nbsp; "+df.format((double)returnBean.getCGST_amt())  +"</span></td><td>"+df.format((double)inclusiveprice) +"<br /><span class=\"hsncode\">CGST "+ df.format((double)cgst_perc) + "%&nbsp;&nbsp;&nbsp;"+df.format((double)returnBean.getCGST_amt()) +"</span></td><td align=\"center\"><span class=\"price\">"+df.format((double)pricewithquant) +"/-</td><td align=\"center\"><input type=\"checkbox\" value=\""+ returnBean.getUuid() +"\" id=\"View_returnitemslist\" name=\"View_returnitemslist\"/></td></tr>";
          subtotal += pricewithquant;
          net_total += returnBean.getPrice();
        }
		
       items_grid+="</table></div></div></div>";
   
       items_grid+="<div class=\"divTable\">\n" + 
        		"<div class=\"divTableBody\">\n" + 
        		"<div class=\"divTableRow\">\n" + 
        		"<div class=\"divTableCell\">TOTAL: </div>\n" + 
        		"<div class=\"divTableCell\"></div>\n" + 
        		"<div class=\"divTableCell\"></div>\n" + 
        		"<div class=\"divTableCell\"> <span class=\"gstright\"><strong>Rs " + df.format((double)subtotal) + "/-</strong></span></div>\n" + 
        		"</div>\n" + 
        		"</div></div>";
         
        items_grid+="<div class=\"divTable\">\n" + 
        		"<div class=\"divTableBody\">\n" + 
        		"	<div class=\"divTableRow\">\n" + 
        		"		<div class=\"divTableCell\">DISCOUNT AMOUNT: </div>\n" + 
        		"		<div class=\"divTableCell\"></div>\n" + 
        		"		<div class=\"divTableCell\"></div>\n" + 
        		"		<div class=\"divTableCell\"> <span class=\"gstright1\"><strong>Rs "+df.format((double)(subtotal - net_total))+"/-</strong></span></div>\n" + 
        		"	</div>\n" + 
        		"</div></div>";
       
        items_grid+="<div class=\"divTable\">\n" + 
        		"<div class=\"divTableBody\">\n" + 
        		"<div class=\"divTableRow\">\n" + 
        		"<div class=\"divTableCell\">NET AMOUNT:</div>\n" + 
        		"<div class=\"divTableCell\"></div>\n" + 
        		"<div class=\"divTableCell\"></div>		\n" + 
        		"<div class=\"divTableCell\"> <span class=\"netright\"><strong>Rs "+df.format((double)net_total)+"/-</strong></span></div>\n" + 
        		"</div>\n" + 
        		"</div></div>";
         
        items_grid+= "<div class=\"divTable\">\n" + 
        		"<div class=\"divTableBody\">\n" + 
        		"<div class=\"divTableRow\">\n" + 
        		"<div class=\"divTableCell\" style=\"text-decoration:underline;text-align:center;\"><strong>PAYMENT DETAILS</strong> </div>\n" + 
        		"</div>\n" + 
        		"</div></div>";
       
            double totalamt = 0d;
        for(Pos_Outlet_Bom_Transaction bomTransObj :bomTransactionList) {
        	
        	items_grid+="<div class=\"divTable\">\n" + 
        			"<div class=\"divTableBody\">\n" + 
        			"<div class=\"divTableRow\">\n" + 
        			"<div class=\"divTableCell\">"+ bomTransObj.getTransaction_type() +"</div>\n" + 
        			"<div class=\"divTableCell\"></div>\n" + 
        			"<div class=\"divTableCell\"></div>		\n" + 
        			"<div class=\"divTableCell\"><span class=\"cashright\"><strong>Rs "+df.format((double)bomTransObj.getAmount())  + "/-</strong></span></div>\n" + 
        			"</div>\n" + 
        			"</div></div>";
        	
        	totalamt+=bomTransObj.getAmount();
        	
        }
        
        /*================Need to add balance amount code ==============*/
           
        /*==============================================================*/
        
        items_grid+="<div class=\"divTable\">\n" + 
        		"<div class=\"divTableBody\">\n" + 
        		"<div class=\"divTableRow\">\n" + 
        		"<div class=\"divTableCell\" style=\"text-decoration:underline;text-align:center;\"><strong>GST SUMMARY</strong> </div>\n" + 
        		"</div>\n" + 
        		"</div></div>";
        
        
        
        //Gst - Summary Details
               
        List<GstAndCgstBean> gstList = new ArrayList<GstAndCgstBean>();
        List<GstAndCgstBean> cgstList = new ArrayList<GstAndCgstBean>();
               
        for(ReturnViewBean returnBean : returnViewList) {
        	double gst_perc = returnBean.getGST_perc();
        	double perc_gst_amt =0d;
        	double perc_cgst_amt = 0d;
        	try {
        		double GST_perc = returnBean.getGST_perc();
        		double price = returnBean.getPrice();
        		double cgst_amt = returnBean.getCGST_amt();
        		System.out.println("gst_perc & price & cgst_amt"+GST_perc + price +  cgst_amt);
        		perc_gst_amt = (double )(price- (2*cgst_amt));
        		perc_cgst_amt = (double)cgst_amt;
        		
        		GstAndCgstBean gstbean = new GstAndCgstBean();
        		gstbean.setGstOrCgstPerc(GST_perc);
        		gstbean.setGstOrCgstAmount(perc_gst_amt);
        		
        		GstAndCgstBean cgstbean = new GstAndCgstBean();
        		cgstbean.setGstOrCgstPerc(GST_perc);
        		cgstbean.setGstOrCgstAmount(perc_cgst_amt);
        		
        		gstList.add(gstbean);
        		cgstList.add(cgstbean);
        		
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        
        System.out.println("********printing cgst and gst maps**********");
         System.out.println("gstList is:"+gstList);
         System.out.println("cgstList is:"+cgstList);
        System.out.println("********printing cgst and gst maps**********");
        
        
        
        Set<Double> keysInGstList = new HashSet<Double>();
        for(GstAndCgstBean element : gstList) {
        	double gstPerc = element.getGstOrCgstPerc();
        	keysInGstList.add(gstPerc);        	
        }
        
        
        System.out.println("***************Keys in Gst List is*********");
        keysInGstList.forEach(System.out::println);
        System.out.println("*******************************************");
        
        
        Map<Double, Double> gstAndAmountSum = new HashMap<Double,Double>();
        Map<Double, Double> cgstAndAmountSum = new HashMap<Double,Double>();
      
        // Set<Double> keysInGstMap = gstMap.keySet();       
        for(Double gstPerc : keysInGstList) {
        	
        	System.out.println("Gst perc is:"+gstPerc);
        	
        	Double amountForGst = 0d;
        	Double amountForCgst = 0d;
        	
        	
        	 for(GstAndCgstBean element : gstList) {
             	 if(gstPerc.equals(element.getGstOrCgstPerc())) {
             		amountForGst+=element.getGstOrCgstAmount();
             	 }
             }
        	
        	 
        	 for(GstAndCgstBean element : cgstList) {
             	 if(gstPerc.equals(element.getGstOrCgstPerc())) {
             		amountForCgst+=element.getGstOrCgstAmount();
             	 }
             }
        	        	 
        	gstAndAmountSum.put(gstPerc, amountForGst);
        	cgstAndAmountSum.put(gstPerc, amountForCgst);
        	
        }
        
        System.out.println("gstAndAmountSum :"+gstAndAmountSum);
        System.out.println("cgstAndAmountSum :"+cgstAndAmountSum);
        
        
        //adding gst details
        Set<Double> keysInGstAmountSum = gstAndAmountSum.keySet(); 
        
        
        
        
        items_grid += "<div id=\"inner_tablestyle\">\n" + 
        		"<table border=\"1\" id=\"gstdetailstable\">\n" + 
        		"<thead>\n" + 
        		"<th>TAX %</th><th>TAXABLE VALUE</th><th>CGST</th><th>SGST</th>\n" + 
        		"</thead>";
        
               
        for(Double gstKey : keysInGstAmountSum) {
        	items_grid +="<tr><td align=\"center\">[<strong>GST " +df.format((double)gstKey)   +"</strong>]</td>\n" + 
        			"<td  align=\"center\">"+ df.format((double)gstAndAmountSum.get(gstKey)) +"</td>\n" + 
        			"<td align=\"center\">" + df.format((double)cgstAndAmountSum.get(gstKey))  + "</td>\n" + 
        			"<td  align=\"center\">" +df.format((double)cgstAndAmountSum.get(gstKey))  + "</td></tr>";
        }
            
        /*==============================================================*/
        
        items_grid+="</table><span><strong>Select Print Type:</strong> <select id=\"printstyle\" name=\"printstyle\"><option value=\"40\">40 Column Print</option><option value=\"a4\">A4 Size Print</option></select></span><br></div></div></div><span><br><button type=\"button\" class=\"btn btn-primary printimg_submitBtn\" id=\"printimg_submitBtn\" data-id=\""+returnViewList.get(0).getBillno()+"\">REPRINT</button>&nbsp;&nbsp;<button type=\"button\" class=\"btn btn-primary View_returnitems\"  id=\"View_returnitems\">RETURN</button>&nbsp;&nbsp;<button type=\"button\" class=\"btn btn-primary print_closeBtn\" id=\"print\">CLOSE</button>&nbsp;&nbsp;<button type=\"button\" class=\"btn btn-primary print_creditnoteBtn\" id=\"creditnote\">CREDIT NOTE</button></span></div>";
        
		return items_grid;
	}


	@Override
	public String getReturnOrderItemsAmount(HttpServletRequest request, HttpSession session) {
		System.out.println("In getReturnOrderItemsAmount");
		System.out.println("*********************");
		
		String outlet_uuid ="";
			try {
			   outlet_uuid = (String)session.getAttribute("Outlet_uuid");
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		String returnIDs = (String)request.getParameter("returnIDs");
		
		 String uuidInCond="";
		 try
		 {
			 uuidInCond = returnIDs.substring(1, returnIDs.length()-1);
			//replacing " with '
			 uuidInCond = uuidInCond.replaceAll("\"", "'");   
			 System.out.println(uuidInCond);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		List<String> returnUUIDsList = new ArrayList<String>(); 
		//String returnOrdResultStr = ordersService.generateReciptReturnView(request, session);
		String returnIDsArray[] = erpFunObj.splitGivenString(returnIDs,",");
		for(String id:returnIDsArray) {
			System.out.println("id is:"+id);
			returnUUIDsList.add(id);
		}
		
		Double price = 0d;
		String priceInStr = "";
		try {
			price = ordersDao.getReturnOrderItemsAmount(uuidInCond,outlet_uuid);
			priceInStr = String.valueOf(price);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		/*---------
		Map<String,String> returnMap = new HashMap<String,String>();
		-----------*/
		String returnString = priceInStr+"||||"+uuidInCond;
		
	    return returnString;
			
	}
	
	@Override
	public void get_view_search(HttpServletRequest request, HttpSession session){		
		System.out.println("=========in get_view_search service impl================");
		
		
		 String outlet_uuid ="";
			try {
			   outlet_uuid = (String)session.getAttribute("Outlet_uuid");
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String todayDate = now.format(formatter);
		String dayStartTime = todayDate + " 00:00:00";
		String dayEndTime = todayDate + " 23:59:59";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startTime = null;
		Date endTime = null;
		try {			
		  //startTime = (Date)format.parse("2018-12-22 00:00:00");
			startTime = (Date)format.parse(dayStartTime);
			endTime =  (Date)format.parse(dayEndTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Pos_Outlet_Bom_Invoice> invoiceDetails = ordersDao.get_view_search(request, session,startTime,endTime);
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 DateFormat dateFormat1 = new SimpleDateFormat("HH-mm-ss");
         Date date = new Date();
        
        System.out.println(date);
        System.out.println(dateFormat.format(date));
        System.out.println(dateFormat1.format(date));
        
        String result = "";
        
       // $result = '<table border="0px" id="search_details" style="font-size:15px;"><tr>';
        result = "<table border=\"0px\" id=\"search_details\" style=\"font-size:15px;\"><tr>";
	
	}
	
	
	
	
	
	@Override
	public List<OrdersDataBean> orderDataList(HttpServletRequest request, HttpSession session){
		
		 String outlet_uuid ="";
			try {
			   outlet_uuid = (String)session.getAttribute("Outlet_uuid");
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String todayDate = now.format(formatter);
		String dayStartTime = todayDate + " 00:00:00";
		String dayEndTime = todayDate + " 23:59:59";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startTime = null;
		Date endTime = null;
		try {			
		  //startTime = (Date)format.parse("2018-12-22 00:00:00");
			startTime = (Date)format.parse(dayStartTime);
			endTime =  (Date)format.parse(dayEndTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
		List<OrdersDataBean> ordersDataList = new ArrayList<OrdersDataBean>();
		try {
			ordersDataList = 	ordersDao.orderDataList(request,outlet_uuid,startTime,endTime);
		}catch(Exception e) {
			
		}
		
		return ordersDataList;
	}
	
	@Override
	public String generateReciptView(HttpServletRequest request, HttpSession session) {
	    System.out.println("in generateReciptView orders_service_impl");
	    
	    
	    //****************************************//
	    //to distinguish search order order view and other receipt views
	    String isSearchOrderView = "no";
	    try {
	    	if(request.getParameter("isSearchOrderView")!=null)
	    		isSearchOrderView = (String)request.getParameter("isSearchOrderView");
	    }catch(Exception e) {
	    	System.out.println("This is not a search order view ");
	    	e.printStackTrace();
	    }
	    
	    System.out.println("is this search view:::::::"+isSearchOrderView);
	    
	    //****************************************//
	    
	  //to distinguish deliver order order view and other receipt views
	    String isThisDeliveryOrderEdit = "no";
	    try {
	    	if(request.getAttribute("isThisDeliveryOrderEdit")!=null)
	    		isThisDeliveryOrderEdit = (String)request.getAttribute("isThisDeliveryOrderEdit");
	    }catch(Exception e) {
	    	System.out.println("This is not a delivery order order view ");
	    	e.printStackTrace();
	    }
	    
	    System.out.println("is this delivery view:::::::"+isThisDeliveryOrderEdit);
	    
	    
	    String Billno = (String)request.getParameter("params");
	    System.out.println(Billno);
	    
	    String year = Constants.year;
	    try {
	    	year = erpFunObj.getLocalDataInfo("current_year");
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	
	    
	    String outlet_uuids ="";
	    String outlet_uuid = "";
	    String user_name ="";
		try {
		   outlet_uuid = (String)session.getAttribute("Outlet_uuid");
		   System.out.println("outlet_uuid is:"+outlet_uuid);
		   outlet_uuids = String.valueOf(Integer.valueOf(outlet_uuid)+10);
		   System.out.println("outlet_uuids:"+outlet_uuids);
		   user_name = (String)session.getAttribute("UserName");
		}catch(Exception e) {
			e.printStackTrace();
		}
	    
		
		String billno = Billno;
	    try {
	    
	       if(isSearchOrderView.equals("yes")) {
	    	   billno = Billno;
	       }else if(isThisDeliveryOrderEdit.equals("yes")) {
	    	   billno = Billno;
	       }else {
	    	   billno = year+outlet_uuids+Billno;
	       }
	       
	      
	       
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }	    
	    
	    System.out.println("final bill no is:"+billno);
	    
	    
	    List<ReturnViewBean> billsCompleteData = new ArrayList<ReturnViewBean>();
		try { 
			billsCompleteData = ordersDao.getCompleteBillDetails(billno, outlet_uuid);
		}catch(Exception e) {
			 System.out.println("Something wrong with setting ReturnView Data");
			e.printStackTrace();
		}
		
		System.out.println("======================in service impl generateReciptView===================================");
		System.out.println(billsCompleteData);
		System.out.println("======================generateReciptView===================================================");
		
		//getting bills result
		List<Pos_Outlet_Bills> billsResult = new ArrayList<Pos_Outlet_Bills>();
		 try {
				//billsResult = 
			billsResult = ordersDao.getBillsData(billno, outlet_uuid);
		 }catch(Exception e) {
			 System.out.println("Something wrong with setting Outlet_Bills Data");
			e.printStackTrace();
		}
		
		System.out.println("======================in service impl generateReciptView===================================");
		System.out.println(billsResult);
		System.out.println("======================generateReciptView===================================================");
			
		List<Pos_Outlet_Bom_Transaction> bomTransactionList = new ArrayList<Pos_Outlet_Bom_Transaction>();
			try {
				bomTransactionList = ordersDao.getTransactionsData(billno, outlet_uuid);
			}catch(Exception e) {
				e.printStackTrace();
			} 
		
		System.out.println("======================in service impl generateReciptView===================================");
		System.out.println(bomTransactionList);
		System.out.println("======================generateReciptView===================================================");
		
	
		List<Pos_Outlet_ProformaBills> proFormaBillsList = new ArrayList<Pos_Outlet_ProformaBills>();
		try {
			proFormaBillsList = ordersDao.getProFormaBillsData(billno, outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		} 
	
		System.out.println("======================in service impl generateReciptView===================================");
		System.out.println(proFormaBillsList);
		System.out.println(proFormaBillsList.size());
		System.out.println("======================generateReciptView===================================================");
		
		
		List<Pos_Outlet_PreOrders> preOrdersList = new ArrayList<Pos_Outlet_PreOrders>();
		try {
			preOrdersList=ordersDao.getPreOrdersData(billno, outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("======================in service impl generateReciptView===================================");
		System.out.println("preorders data for generatereceiptview:"+preOrdersList);
		System.out.println("size of preorders data is:"+preOrdersList.size());
		System.out.println("======================generateReciptView===================================================");
		
		//getting invoice result
		List<Pos_Outlet_Bom_Invoice> bomInvoiceResultList = new ArrayList<Pos_Outlet_Bom_Invoice>();
		try {
		   bomInvoiceResultList = ordersDao.getBillnoInvoiceDetails(billno, outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		System.out.println("======================in service impl generateReciptView===================================");
		System.out.println("preorders data for generatereceiptview:"+bomInvoiceResultList);
		System.out.println("size of preorders data is:"+bomInvoiceResultList.size());
		System.out.println("======================generateReciptView===================================================");

		DecimalFormat df = new DecimalFormat("#.00");
		
		String items_grid = "<style>\n" + 
				".container{font-family: sans-serif;height:100%;background: #FFF;font-size: 9.5pt;}\n" + 
				".divTableRow1 {display: table-row;}\n" + 
				".divTableHeading1 {background-color: #EEE;display: table-header-group;}\n" + 
				".divTableCell1 {font-size: 14px;display: table-cell;padding: 3px 10px;}\n" + 
				".divTableCell2{display: table-cell;padding-left: 40px;}\n" + 
				".divTableCell3{display: table-cell;padding-left: 65px;}\n" + 
				".divTableCell4{display: table-cell;padding-left: 70px;}\n" + 
				".divTableHead1 {font-size: 11px;display: table-cell;padding: 1px 11px;}	\n" + 
				".divTable{col:40;}\n" + 
				".divTableRow {display: table-row;}\n" + 
				".divTableHeading {background-color: #EEE;display: table-header-group;}\n" + 
				".divTableCell {font-size: 12px;display: table-cell;}\n" + 
				".itemCell {font-size: 12px;width: 120px;font-weight:bold;padding: 3px 2px;}\n" + 
				".divTableHead {font-size: 20px;display: table-cell;padding: 3px 3px;}\n" + 
				".divTableHeading {background-color: #EEE;display: table-header-group;font-weight: bold;}\n" + 
				".divTableFoot {background-color: #EEE;display: table-footer-group;font-weight: bold;}\n" + 
				".divTableBody {display: table-row-group;}\n" + 
				".lastright { padding-left: 72px;}\n" + 
				".lastcolumn {padding-left: 10px;}	\n" + 
				".price {padding-left: 0px;}\n" + 
				".hsncode{font-size: 10px;}\n" + 
				".header{col:80;text-align:center;}\n" + 
				".header h2{text-decoration:underline;}\n" + 
				".textright{padding-left: 106px;}\n" + 
				".gstright{margin-left: 147px;}\n" + 
				".netright{margin-left: 106px;}\n" + 
				".gstright1{margin-left: 68px;}\n" + 
				".subtotal{padding-left: 77px;}\n" + 
				".billtitle{font-size: 20px;}\n" + 
				".cashright{margin-left: 159px;}\n" + 
				".fottertitle{padding-left: 70px;}\n" + 
				".divTableCellmain{font-size: 14px;display: table-cell;text-align:center;padding-left:75px;}\n" + 
				".Bighr{color:#000 !important;width: 40% !important;}\n" + 
				".divTableHeadnew{font-size:15px;display: table-cell;padding: 3px 3px;}\n" + 
				"#orderdetailstable,#gstdetailstable{border-color:#eee !important;}\n" + 
				"#gstdetailstable_previous,#gstdetailstable_next{display:none;}\n" + 
				"</style>\n" + 
				"<script>\n" + 
				"$(document).ready( function () {\n" + 
				"    $(\"#orderdetailstable\").DataTable({\n" + 
				"		\"pageLength\": 2,\n" + 
				"		\"bLengthChange\": false,\n" + 
				"		\"bInfo\": false,\n" + 
				"		searching: false\n" + 
				"	});\n" + 
				"	$(\"#gstdetailstable\").DataTable({\n" + 
				"		\"pageLength\": 2,\n" + 
				"		\"bLengthChange\": false,\n" + 
				"		\"bInfo\": false,\n" + 
				"		searching: false\n" + 
				"	});\n" + 
				"} );\n" + 
				"</script>\n" + 
				"<div class=\"divTable\">\n" + 
				"<div class=\"divTableBody\">"; 	
		
	    items_grid += "<div class=\"divTableRow\">";
	     try {
		if(preOrdersList.isEmpty() == false) {
			items_grid += "<div class=\"\"><strong>Date:</strong>"+preOrdersList.get(0).getCreated_datetime()+"nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Delivery Date:</strong>"+preOrdersList.get(0).getDelivery_datetime()+" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Cashier:</strong>"+user_name+"</div>";
		}else {
			items_grid += "<div class=\"\"><strong>Date:</strong>"+bomTransactionList.get(0).getCreated_date()+"<strong>Cashier:</strong>"+user_name+"</div>";
		}
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
       items_grid += "<div class=\"\"><span><strong>Inovice No:</strong>"+billno+"</span>&nbsp;";
	   
       items_grid += "</div>\n" + 
       		"</div><input type=\"hidden\" value=\""+billsCompleteData.get(0).getBillno()+"\" name=\"orderprint_value\" id=\"orderprint_value\">\n" + 
       		"</div></div><div id=\"inner_tablestyle\">\n" + 
       		"<table border=\"1\" id=\"orderdetailstable\">\n" + 
       		"<thead>\n" + 
       		"<th>Product</th><th>Qty</th><th>Unit</th><th>Pc.</th><th>Amt.</th>\n" + 
       		"</thead>";
       
       double subtotal_without_tax = 0d;
       double net_total = 0d;
       double subtotal = 0d;
       for(ReturnViewBean returnBean : billsCompleteData) {
       	  
    	 if(returnBean.getUuid().equals("10000"))
    			returnBean.setItem_name("OPEN ORDER-1");
        	else if(returnBean.getUuid().equals("20000"))
        		returnBean.setItem_name("OPEN ORDER-2");
        	else if(returnBean.getUuid().equals("30000"))
        		returnBean.setItem_name("OPEN ORDER-3");
    	
        	if(returnBean.getPortion_uuid().equals("11111"))
        		returnBean.setPortion_name("KG");
        	else if(returnBean.getPortion_uuid().equals("22222"))
        		returnBean.setPortion_name("PC");
        	else if(returnBean.getPortion_uuid().equals("33333"))
        		returnBean.setPortion_name("Unit");
               
        /*	
         *	  	
        	if(returnBean.getItemID() >= 10000) {
        		if(returnBean.getGST_perc() == 0)
        			returnBean.setCode(Integer.toString(1905));
        		if(bomitem.getGST_perc() == 5 || bomitem.getGST_perc() == 18)
        			pos_itemsBean.setCode(Integer.toString(2106));
        		if(bomitem.getGST_perc() == 12)
        			pos_itemsBean.setCode(Integer.toString(2008));
        		if(bomitem.getGST_perc() == 28)
        			pos_itemsBean.setCode(Integer.toString(1806));
        	}
          */
    	   
        	
           double inclusiveprice = 0d;
           double pricewithquant = 0d;
           double cgst_perc = (double)returnBean.getGST_perc() / 2;
           try {
         	  inclusiveprice =  (double)(returnBean.getOriginal_price()*((returnBean.getGST_perc()+100)/100d));
         	  pricewithquant = inclusiveprice * returnBean.getQuantity();
         	  
           }catch(Exception e) {
         	  System.out.println("error for setting inclusingprice or pricewithquant");
         	  e.printStackTrace();
           }
           
           items_grid +="<tr> <td  align=\"center\">"+returnBean.getItem_name()+"<br /><span class=\"hsncode\">[GST " + returnBean.getGST_perc() + "% HSN:" + returnBean.getHsncode() + "]</span></td> <td  align=\"center\">"+df.format((double)returnBean.getQuantity())  + "</td><td>"+returnBean.getPortion_name()+"<br /><span class=\"hsncode\">SGST "+ df.format((double)cgst_perc)+"%&nbsp;&nbsp;&nbsp;"+df.format((double)returnBean.getCGST_amt())+"</span></td>   <td>"+df.format((double)inclusiveprice)+"<br /><span class=\"hsncode\">CGST "+df.format((double)cgst_perc) + "%&nbsp;&nbsp;&nbsp;"+df.format((double)returnBean.getCGST_amt())+"</span></td>   <td align=\"center\"><span class=\"price\">"+df.format(pricewithquant)+"/-</td>      </tr>";
           subtotal += pricewithquant;
           net_total += returnBean.getPrice();
       }
   
       items_grid +="</table></div></div></div></div>";
       
       items_grid +="<div class=\"divTable\">\n" + 
        		"<div class=\"divTableBody\">\n" + 
        		"<div class=\"divTableRow\">\n" + 
        		"<div class=\"divTableCell\">TOTAL: </div>\n" + 
        		"<div class=\"divTableCell\"></div>\n" + 
        		"<div class=\"divTableCell\"></div>\n" + 
        		"<div class=\"divTableCell\"> <span class=\"gstright\"><strong>Rs " +df.format((double)subtotal)  + "/-</strong></span></div>\n" + 
        		"</div>\n" + 
        		"</div></div>";
         
        items_grid +="<div class=\"divTable\">\n" + 
        		"<div class=\"divTableBody\">\n" + 
        		"	<div class=\"divTableRow\">\n" + 
        		"		<div class=\"divTableCell\">DISCOUNT AMOUNT: </div>\n" + 
        		"		<div class=\"divTableCell\"></div>\n" + 
        		"		<div class=\"divTableCell\"></div>\n" + 
        		"		<div class=\"divTableCell\"> <span class=\"gstright1\"><strong>Rs "+df.format((double)(subtotal - net_total))+"/-</strong></span></div>\n" + 
        		"	</div>\n" + 
        		"</div></div>";    
        
       // --- should add credit Amount condition ----
        
        items_grid +="<div class=\"divTable\">\n" + 
        		"<div class=\"divTableBody\">\n" + 
        		"<div class=\"divTableRow\">\n" + 
        		"<div class=\"divTableCell\">NET AMOUNT:</div>\n" + 
        		"<div class=\"divTableCell\"></div>\n" + 
        		"<div class=\"divTableCell\"></div>		\n" + 
        		"<div class=\"divTableCell\"> <span class=\"netright\"><strong>Rs"+df.format((double)net_total)+"/-</strong></span></div>\n" + 
        		"</div>\n" + 
        		"</div></div>";
        
        items_grid += "<div class=\"divTable\">\n" + 
        		"<div class=\"divTableBody\">\n" + 
        		"<div class=\"divTableRow\">\n" +  
        		"<div class=\"divTableCell\" style=\"text-decoration:underline;text-align:center;\"><strong>PAYMENT DETAILS</strong> </div>\n" + 
        		"</div>\n" + 
        		"</div></div>";
         
        items_grid += "<table style=\"width: 100%;\">";
        
        //need to add proformabill conditions
        
        //adding transaction details
        int check = 0;
        int check_else = 0;
        double tamt = 0d;
        double balance_amtcheck = 0d;
        
        try {
        	balance_amtcheck = bomInvoiceResultList.get(0).getBalance_amt();
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        
        //including transaction details
        try {
        	if(!bomTransactionList.isEmpty()) {
        		for(Pos_Outlet_Bom_Transaction bomTransObj : bomTransactionList){
        			tamt += (double)bomTransObj.getAmount();
        			items_grid += "<tr><td style=\"font-weight:bold;text-align: left;font-size: 15px;width: 50%;\"><strong>";
        			if(balance_amtcheck != 0) {
        				if(check ==0) {
        					if(bomTransObj.getTransaction_type().equals("Cash")) {
        						items_grid +="Advance Amount("+bomTransObj.getTransaction_type()+")";
        					}else {
        						items_grid +="Advance Amount("+bomTransObj.getTransaction_type()+")"; //need to add card_type	
        					}	
        				}else {
        					check_else = 1;
        					items_grid += "Paid Amount(" + bomTransObj.getTransaction_type()+ ")</strong> ";
        				}
        			}else {	
        				try {
        				if(bomTransObj.getCard_type() != null) {
        				    items_grid +=  bomTransObj.getTransaction_type()+"("+bomTransObj.getCard_type()+")";	
        				}else {
        					items_grid += bomTransObj.getTransaction_type();
        				}
        				//items_grid += bomTransObj.getTransaction_type();
        				}catch(Exception e) {
        					e.printStackTrace();
        				}
        			}
        			
        			items_grid += "</td><td><label style=\"font-weight:normal;font-size: 15px;\">Rs <strong>"+ (double)Math.round(bomTransObj.getAmount()*100.0)/100.0+"/-</label></strong></td></tr>";
        			check++;
        		}
        		items_grid += "<tr>";
        	}
        	
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        
        
        
        //including balance amount condition
        
        double bal_amt = 0d;
        try {
        	bal_amt = (double)(net_total - tamt);
        }catch(Exception e) {
        	System.out.println("problem with setting bal_amt");
        	e.printStackTrace();
        }
                
        try {
        	if(!(Double.compare(bal_amt, 0)==0)) {
        		bal_amt = balance_amtcheck;
        		items_grid += "<td style=\"font-weight:bold;width:82%;\"><strong><strong>Balance Amount:</strong>";       
        		items_grid += "</strong></td><td><label style=\"font-weight:normal;\">Rs</label> <strong>"+df.format((double)balance_amtcheck)+"'/-</strong>&nbsp;&nbsp;&nbsp;"; 
        		
        		items_grid += "<button type=\"button\" class=\"btn btn-primary View_partialitems\" data-billno='"+billno+"' id=\"View_partialitems\">View Items</button>";
        		
        		items_grid += "</td>";
        	}else{       	
        	items_grid += "<td style=\"font-weight:bold;text-align: left;font-size: 15px;\"><strong><strong>Balance Amount:</strong>";
        if (Double.compare(balance_amtcheck, 0)==0 && balance_amtcheck > 0) {
        	bal_amt = balance_amtcheck;
        	       	
        	items_grid += "</strong></td>\n" + 
        			"		\n" + 
        			"		<td><label style=\"font-weight:normal;font-size: 15px;\">Rs <strong>" + (double)Math.round(balance_amtcheck*100.0)/100 + "/-</strong></label></td><td colspan=\"2\" style=\"text-align: right;\"><button type=\"button\" class=\"btn btn-primary View_partialitems\" data-billno=\"' . $Billno .'\" id=\"View_partialitems\">View Items</button></td></tr>";
        }else if(Double.compare(balance_amtcheck, 0)==0 && balance_amtcheck < 0) {
        	
        	try {
        	items_grid += "</strong></td>\n" + 
        			"		\n" + 
        			"		<td><label style=\"font-weight:normal;font-size: 15px;\">Rs <strong> 0/-</label></strong></td></tr>" ;
        	//add preorder condition
        	}catch(Exception e) {
        		e.printStackTrace();
        	}      
        	
        }else {
        	try {
        			items_grid += "</strong></td>\n" + 
        					"		\n" + 
        					"		<td><label style=\"font-weight:normal;font-size: 15px;\">Rs <strong>"+(double)Math.round(balance_amtcheck*100.0)/100 + "/-</label></strong></td></tr>";
        			//need to add preorders condition
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        	
        	items_grid +="</tr>";
        }
        
        	}
        
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
       
        if(check_else == 1) {
        	items_grid += "<strong>Balance Amount:</strong>";
        	items_grid += "</strong></td><td><label style=\"font-weight:normal;font-size: 15px;\">Rs <strong>0 /-</label></strong></td></tr>";
        }
        
        
        //Gst - Summary Details
               
        List<GstAndCgstBean> gstList = new ArrayList<GstAndCgstBean>();
        List<GstAndCgstBean> cgstList = new ArrayList<GstAndCgstBean>();
               
        for(ReturnViewBean returnBean : billsCompleteData) {
        	double gst_perc = returnBean.getGST_perc();
        	double perc_gst_amt =0d;
        	double perc_cgst_amt = 0d;
        	try {
        		double GST_perc = returnBean.getGST_perc();
        		double price = returnBean.getPrice();
        		double cgst_amt = returnBean.getCGST_amt();
        		System.out.println("gst_perc & price & cgst_amt"+GST_perc + price +  cgst_amt);
        		perc_gst_amt = (double )(price- (2*cgst_amt));
        		perc_cgst_amt = (double)cgst_amt;
        		
        		GstAndCgstBean gstbean = new GstAndCgstBean();
        		gstbean.setGstOrCgstPerc(GST_perc);
        		gstbean.setGstOrCgstAmount(perc_gst_amt);
        		
        		GstAndCgstBean cgstbean = new GstAndCgstBean();
        		cgstbean.setGstOrCgstPerc(GST_perc);
        		cgstbean.setGstOrCgstAmount(perc_cgst_amt);
        		
        		gstList.add(gstbean);
        		cgstList.add(cgstbean);
        		
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        
        System.out.println("********printing cgst and gst maps**********");
         System.out.println("gstList is:"+gstList);
         System.out.println("cgstList is:"+cgstList);
        System.out.println("********printing cgst and gst maps**********");
        
        
        
        Set<Double> keysInGstList = new HashSet<Double>();
        for(GstAndCgstBean element : gstList) {
        	double gstPerc = element.getGstOrCgstPerc();
        	keysInGstList.add(gstPerc);        	
        }
        
        
        System.out.println("***************Keys in Gst List is*********");
          keysInGstList.forEach(System.out::println);
        System.out.println("*******************************************");
        
        
        Map<Double, Double> gstAndAmountSum = new HashMap<Double,Double>();
        Map<Double, Double> cgstAndAmountSum = new HashMap<Double,Double>();
      
        // Set<Double> keysInGstMap = gstMap.keySet();       
        for(Double gstPerc : keysInGstList) {
        	
        	System.out.println("Gst perc is:"+gstPerc);
        	
        	Double amountForGst = 0d;
        	Double amountForCgst = 0d;
        	
        	
        	 for(GstAndCgstBean element : gstList) {
             	 if(gstPerc.equals(element.getGstOrCgstPerc())) {
             		amountForGst+=element.getGstOrCgstAmount();
             	 }
             }
        	
        	 
        	 for(GstAndCgstBean element : cgstList) {
             	 if(gstPerc.equals(element.getGstOrCgstPerc())) {
             		amountForCgst+=element.getGstOrCgstAmount();
             	 }
             }
        	        	 
        	gstAndAmountSum.put(gstPerc, amountForGst);
        	cgstAndAmountSum.put(gstPerc, amountForCgst);
        	
        }
        
        System.out.println("gstAndAmountSum :"+gstAndAmountSum);
        System.out.println("cgstAndAmountSum :"+cgstAndAmountSum);
        
      
        Set<Double> keysInGstAmountSum = gstAndAmountSum.keySet(); 
      
        items_grid += "</table><div class=\"divTable\">\n" + 
        		"<div class=\"divTableBody\">\n" + 
        		"<div class=\"divTableRow\">\n" + 
        		"<div class=\"divTableCell\" style=\"text-decoration:underline;text-align:center;\"><strong>GST SUMMARY</strong> </div>\n" + 
        		"</div>\n" + 
        		"</div></div><br>";
        
        items_grid += "<div id=\"inner_tablestyle\">\n" + 
        		"<table border=\"1\" id=\"gstdetailstable\">\n" + 
        		"<thead>\n" + 
        		"<th>TAX %</th><th>TAXABLE VALUE</th><th>CGST</th><th>SGST</th>\n" + 
        		"</thead>";
        
               
        for(Double gstKey : keysInGstAmountSum) {
        	items_grid +="<tr><td align=\"center\">[<strong>GST " +df.format(gstKey)  +"</strong>]</td>\n" + 
        			"<td  align=\"center\">"+df.format(gstAndAmountSum.get(gstKey)) +"</td>\n" + 
        			"<td align=\"center\">" +df.format(cgstAndAmountSum.get(gstKey))   + "</td>\n" + 
        			"<td  align=\"center\">" +df.format(cgstAndAmountSum.get(gstKey))   + "</td></tr>";
        }
        
        String bill = String.valueOf(billsCompleteData.get(0).getBillno());
        System.out.println("*******bill no is:***************************"+bill);
        outlet_uuids = String.valueOf(Integer.valueOf(outlet_uuid)+10);
        String commonInBill = year+outlet_uuids;
        System.out.println("\n*******commonInBill no is:***************************"+commonInBill+"sefsefs"+outlet_uuids);
        long presentBillNo = Long.valueOf(bill.replace(commonInBill,""));
        System.out.println("*****************************************************");
        System.out.println("********present bill no is:***************************"+presentBillNo);
        
        items_grid +="</table></div></div></div><input type=\"hidden\" value=\""+presentBillNo+"\" id=\"orderid_return\">"
        		+ "<span><button type=\"button\" class=\"btn btn-primary printimg_submitBtn\" id=\"printimg_submitBtn\" data-id=\""+billsCompleteData.get(0).getBillno()+"\">REPRINT</button>&nbsp;&nbsp;"
        				+ "<button type=\"button\" class=\"btn btn-primary print_closeBtn\" id=\"print\">CLOSE</button>&nbsp;&nbsp;"
        				+ "<button type=\"button\" class=\"btn btn-primary returnorder_submitBtn\" id=\"returnorder_submitBtn\" data-id=\"" + presentBillNo + "\">RETURN</button></span></div>";
        
        return items_grid;
	}


	@Override
	public double getTotalTransactionSum() {
		
		System.out.println("***********In getTotalTransactionSum service impl");
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String todayDate = now.format(formatter);
		String dayStartTime = todayDate + " 00:00:00";
		String dayEndTime = todayDate + " 23:59:59";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startTime = null;
		Date endTime = null;
		try {			
		  //startTime = (Date)format.parse("2018-12-22 00:00:00");
			startTime = (Date)format.parse(dayStartTime);
			endTime =  (Date)format.parse(dayEndTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
       
		
		double totalTransSum = 0d;
		try {
			totalTransSum = ordersDao.getTotalTransactionSum(startTime,endTime);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return totalTransSum;
	
	}


	@Override
	public Pos_Vouchers_Bean getVoucherDetails(HttpServletRequest request, HttpSession httpsession) {
		System.out.println("**********ordersimpl getVoucherDetails*******");
		String params_data = request.getParameter("data_array"); 
		System.out.println("json data in getVoucherDetails:");
		System.out.println(params_data);
		/*=====================================*/
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(params_data);
		JsonObject jsonObject = jsonTree.getAsJsonObject();
		
		JsonElement selectedItemstot = jsonObject.get("selectedItemstot");
		JsonObject selectedItemstotObj = selectedItemstot.getAsJsonObject();
		
	    String voucher_code = jsonObject.get("voucher_code").getAsString();
	    System.out.println("voucher code is"+voucher_code+"\n");
	    Date date= new Date();
	    long time = date.getTime();
	    Timestamp ts = new Timestamp(time);
	    System.out.println("***Ts is"+ts);
	    System.out.println("***********************");
	     System.out.println("timestamp for getVoucherDetails is:"+ts);
	     System.out.println("voucher code is:"+voucher_code);
	    System.out.println("***********************");
	    Session session = null;
	    session = sessionFactory.openSession();
	    session.beginTransaction(); 	
		
	    Criteria criteria = session.createCriteria(Pos_Vouchers.class);
	    		 criteria.add(Restrictions.eq("voucher_code", voucher_code));
	    		 criteria.add(Restrictions.eq("status", Pos_vouchers_status.Active));
	    		 criteria.add(Restrictions.eq("voucher_code", voucher_code));
	    		 criteria.add(Restrictions.le("valid_from", ts));
	    		 criteria.add(Restrictions.ge("valid_to", ts));
	    		 criteria.setProjection(Projections.projectionList()
	    				 .add(Projections.property("voucher_type"))
	    				 .add(Projections.property("used_status"))
	    				 .add(Projections.property("discount_type"))
	    				 .add(Projections.property("outlet_ids"))
	    				 .add(Projections.property("uuid"))
	    				 .add(Projections.property("parent_voucher_id"))
	    				 .add(Projections.property("item_ids")));
	    System.out.println("***********Query list is*********"+criteria.list());
	    
	    List<Object[]> results = (List<Object[]>)criteria.list();
		System.out.println(results);
		
		List<Pos_Vouchers_Bean> pos_vouchers_bean_list = new ArrayList<>();
		Pos_Vouchers_Bean pos_vouchersBean = new Pos_Vouchers_Bean();
		int totalRecords = 0;
         try {
               totalRecords = results.size();
         }catch(Exception e) {
            	e.printStackTrace();
         }
         String result_vouchertype = null;
		 String result_usedstatus = null;
		 String result_discounttype = null;
		 String result_outlet_ids = null;
		 String result_voucher_id = null;
		 String result_parent_id = null;
		 String result_item_ids = null;
		 String voucher_id = null;
		 
	    if(totalRecords == 1){
	    	System.out.println("\n********If loop**********");
	    	 for(Object[] vouchersBeanResult: results){
	    		 System.out.println("\n********for loop**********");
	    		 result_vouchertype = (String)vouchersBeanResult[0];
	    		 result_usedstatus = (String)vouchersBeanResult[1];
	    		 result_discounttype = (String)vouchersBeanResult[2];
	    		 result_outlet_ids = (String)vouchersBeanResult[3];
	    		 result_voucher_id = (String)vouchersBeanResult[4];
	    		 result_parent_id = (String)vouchersBeanResult[5];
	    		 result_item_ids = (String)vouchersBeanResult[6];
	           }
	    	 System.out.println("************"+pos_vouchersBean+"****\n");
	    	 String outletId = (String) httpsession.getAttribute("Outlet_uuid");
	    	 boolean outletcheck_flag = checkOutletIds(result_outlet_ids, outletId);
	    	 if (result_outlet_ids.equals("0") || outletcheck_flag == true) {
	    		 if (result_vouchertype.equals("one_time")) {
	    			 System.out.println("\n********if flag and bean loop**********");
	                    if (result_usedstatus == "used") {
	                    	pos_vouchersBean.setError("Already Used"); 
	                    } else {
	                    	result_parent_id = result_voucher_id;
	                        voucher_id = result_parent_id;
	                    }
	                } else {
	                	voucher_id = result_voucher_id;
	                }
	    		 
	    		//********min range**************//
		    	 System.out.println("\n********result_discounttype**********"+result_discounttype+"******\n");
		    	 int amount = 0;
		    	 String perc_or_amt = null;
	    		 if(result_discounttype.equals("min_range")) {
	    			 System.out.println("\n********If min range loop**********");
	    			 Criteria criteria1 = session.createCriteria(Pos_Voucher_Discounts.class);
	    			    	  criteria1.add(Restrictions.eq("voucher_uuid", voucher_id));
	    			    	  criteria1.add(Restrictions.eq("status", Pos_Voucher_Discounts_Status.Active));
	    			    	  criteria1.addOrder(Order.desc("min_range"));
	    			    	  criteria1.setProjection(Projections.projectionList()
	    			    			   .add(Projections.property("min_range"))
	    			    			   .add(Projections.property("amount"))
	    			    			   .add(Projections.property("perc_or_amt")));
	    			 System.out.println("****min range*******"+criteria1.list());
	    			 
	    			 double billamt = 0d;
	    		     try {
	    		    	 billamt = selectedItemstotObj.get("tot_price").getAsDouble();
	    		     }catch(Exception e) {
	    		    	 e.printStackTrace();
	    		     }
	    		     int totaldiscountval = 0;
	    		     List<Object[]> results1 = (List<Object[]>)criteria1.list();
	    			 System.out.println(results1);
	    			 for(Object[] result: results1){	            	
	    				 int min_range = (int) result[0];
	    				 if (billamt > min_range) {
	    					 amount = (int) result[1];
	    					 perc_or_amt = (String) result[2]; 
	    					 if (perc_or_amt.equals("perc"))
	                             totaldiscountval = (int)(amount) / 100;
	                         else{
	                             totaldiscountval = (int)(amount);
	                         }
	                         break;
	    				 }
	    			 }
	    		 }else {
	    			 System.out.println("\n********else min range loop**********");
	    			 Criteria criteria1 = session.createCriteria(Pos_Voucher_Discounts.class);
	    			    	  criteria1.add(Restrictions.eq("voucher_uuid", voucher_id));
	    			    	  criteria1.add(Restrictions.eq("status", Pos_Voucher_Discounts_Status.Active));
	    			    	  criteria1.setProjection(Projections.projectionList()
	    			    			   .add(Projections.property("min_range"))
	    			    			   .add(Projections.property("amount"))
	    			    			   .add(Projections.property("perc_or_amt")));
	    			 System.out.println("****min range else case*******"+criteria1.list());
	    			 int totaldiscountval = 0;
	    			 List<Object[]> results1 = (List<Object[]>)criteria1.list();
	    			 System.out.println(results1);
	    			 for(Object[] result: results1){	            	
	    				 amount = (int) result[1];
	    				 perc_or_amt = (String) result[2]; 
	    				 if (perc_or_amt.equals("perc"))
                             totaldiscountval = (int)(amount) / 100;
                         else{
                             totaldiscountval = (int)(amount);
                         }
	    			 }
	    		 }
	    		 pos_vouchersBean.setVoucher_type(result_vouchertype);
	    		 pos_vouchersBean.setUuid(result_voucher_id);
	    		 pos_vouchersBean.setItem_ids(result_item_ids);
	    		 pos_vouchersBean.setAmount(amount);
	    		 pos_vouchersBean.setPerc_or_amt(perc_or_amt);
	    	 
	    	 }else {
	    		 pos_vouchersBean.setError("Voucher is not valid in this outlet"); 
	    	 }

	    }else {
	    	pos_vouchersBean.setError("Voucher Got Expired"); 
        }
	    pos_vouchers_bean_list.add(pos_vouchersBean);
		return pos_vouchersBean;
	}
	
	public boolean checkOutletIds(String result_outlet_ids, String outletId){
		String[] resultoutletList = result_outlet_ids.split(",");
		for(String resultoutletid : resultoutletList) {
			if(outletId == resultoutletid) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String generateestimateBill(HttpServletRequest request, HttpSession httpSession) {
		String params_data = request.getParameter("data_array");
		System.out.println(params_data);
		String user_uuid = (String) httpSession.getAttribute("User_uuid");
		System.out.println(user_uuid);
		long BillNo = 0L;
		try {
			BillNo = billsRandFuncts.getestimatebill_nextnumber(httpSession);

		} catch (Exception e) {
			System.out.println("problem with setting BillNo");
			e.printStackTrace();
		}

		System.out.println("in OrdersServiceImpl,So next bill no is:" + BillNo);
		Long BOM_ID = 0L;
		try {
			BOM_ID = billsRandFuncts.generateUniqueToken(httpSession, "BOM_ID", Constants.BOM_ID_LENGTH);
		} catch (Exception e) {
			System.out.println("problem while setting BOM_ID...........");
			e.printStackTrace();
		}

		Long BillAmountID = 0L;
		try {
			BillAmountID = billsRandFuncts.generateUniqueToken(httpSession, "BillAmountID",
					Constants.BillAmountID_LENGTH);
		} catch (Exception e) {
			System.out.println("problem while setting BillAmountID...........");
			e.printStackTrace();
		}

		System.out.println(" printing Billno,BOM_ID AND BillAmountID'''''''''''''''''''''''''''''");
		System.out.println("BillNo is:" + BillNo + "----BOM_ID:" + BOM_ID + "--BillAmountID:" + BillAmountID);

		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(params_data);
		String prettyJsonString = gson.toJson(jsonTree);
		System.out.println(prettyJsonString);
		JsonObject jsonObject = jsonTree.getAsJsonObject();

		JsonElement selectedItemArray = jsonObject.get("selectedItemArray");
		JsonObject selectedItemArrayObj = selectedItemArray.getAsJsonObject();
		System.out.println("This is the selectedItemArray:");
		System.out.println(selectedItemArrayObj.toString());
		// -------------------------------------------------------
		JsonElement selectedItemstot = jsonObject.get("selectedItemstot");
		JsonObject selectedItemstotObj = selectedItemstot.getAsJsonObject();
		System.out.println("this is seleteditemsssssssssss");
		System.out.println(selectedItemstotObj.toString());

		JsonElement overall_bill_disc = jsonObject.get("overall_bill_disc");
		JsonObject overall_bill_discObj = overall_bill_disc.getAsJsonObject();
		System.out.println(overall_bill_discObj.toString());

		// ===================================================

		String outlet_uuids = (String) httpSession.getAttribute("Outlet_uuid");
		String var = outlet_uuids + 10;
		String uniqid = outlet_uuids + erpFunObj.uniqueRandAndTimeStamp();
		String server_data = this.erpFunObj.gettingDate();
		System.out.println(server_data);
		String uuid_bills = var + erpFunObj.uniqueRandAndTimeStamp();

		// ------------------------------------------------
		Pos_Outlet_Bills Obj = new Pos_Outlet_Bills();
		Obj.setUuid(uuid_bills);
		Obj.setOutlet_uuid(outlet_uuids);
		Obj.setBillno(BillNo);
		Obj.setBOM_ID(BOM_ID);
		Obj.setBillAmountID(BillAmountID);
		Obj.setCreated_date(new Date());
		Obj.setBill_type("Estimate Bill");
		Obj.setCreated_by(user_uuid);
		Obj.setUpdated_date(new Date());
		Obj.setUpdated_by(user_uuid);

		Session session = null;
		Set<?> s = selectedItemArrayObj.keySet();
		Iterator<?> i = s.iterator();

		List<Pos_Outlet_Bom_Items> bomItemsObjList1 = new ArrayList<Pos_Outlet_Bom_Items>();
		double item_dis = 0d;
		double item_adt_dis = 0d;
		double total_dis = 0d;
		do {
			String key = i.next().toString();
			System.out.println("This is the key:" + key);
			JsonObject itemsRowData = selectedItemArrayObj.get(key).getAsJsonObject();

			double i_adt_dis_perc = 0d;
			double i_adt_dis_amt = 0d;
			double price_fordiscount = 0d;
			try {
				i_adt_dis_perc = itemsRowData.get("additional_discount").getAsJsonObject().get("percentage")
						.getAsDouble();
				i_adt_dis_amt = itemsRowData.get("additional_discount").getAsJsonObject().get("amount").getAsDouble();
				System.out.println("dis perc" + i_adt_dis_perc); // ******output 0.0*******
				System.out.println("amt perc" + i_adt_dis_amt); // ******output 0.0********
			} catch (Exception e) {
				e.printStackTrace();
			}

			item_adt_dis += i_adt_dis_amt;
			double discount_amt = itemsRowData.get("discount").getAsJsonObject().get("amount").getAsDouble();
			total_dis = discount_amt + i_adt_dis_amt;
			double price_bd = 0d;
			double original_price = 0d;
			double price_bg = 0d;
			double original_discount_perc = 0d;
			double discount_perc = 0d;
			double adt_discount_perc = 0d;
			double quantity = 0d;
			double adt_discount_amt = 0d;
			String item_uuid = "";
			try {
				item_uuid = itemsRowData.get("item_id").getAsString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// double gst_perc = 0d;
			double gst_perc = itemsRowData.get("gst").getAsJsonObject().get("all").getAsJsonObject().get("percentage")
					.getAsDouble();
			try {
				quantity = itemsRowData.get("qty").getAsDouble();
				original_price = (double) ((itemsRowData.get("price").getAsDouble() / (gst_perc + 100d)) * 100);
				price_bd = (double) (original_price * itemsRowData.get("qty").getAsDouble());
				price_fordiscount = (double) itemsRowData.get("price").getAsDouble()
						* itemsRowData.get("qty").getAsDouble();

				price_bg = (price_fordiscount) - (discount_amt + i_adt_dis_amt);
				original_discount_perc = itemsRowData.get("original_discount").getAsJsonObject().get("percentage")
						.getAsDouble();
				discount_perc = itemsRowData.get("discount").getAsJsonObject().get("percentage").getAsDouble();
				adt_discount_perc = itemsRowData.get("additional_discount").getAsJsonObject().get("percentage")
						.getAsDouble();
				adt_discount_amt = itemsRowData.get("additional_discount").getAsJsonObject().get("amount")
						.getAsDouble();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// setting gst,cgst,sgst values
			double gst_amt = 0d;
			double cgst_amt = 0d;
			double sgst_amt = 0d;
			double gst_exclude = 0d;
			double price_lastvalue = 0d;
			double price_beforevalue = 0d;
			double original_discount = 0d;
			try {
				if (original_discount_perc > 0 || discount_perc > 0 || adt_discount_perc > 0) {
					gst_exclude = (price_bg / (gst_perc + 100d)) * 100;
					gst_amt = (double) (gst_exclude * (gst_perc / 100));
					cgst_amt = (double) (gst_amt / 2);
					sgst_amt = (double) (gst_amt / 2); // both cgst_amt & sgst_amt carries half in the gst amount
					price_lastvalue = gst_exclude + gst_amt;
				} else {
					gst_amt = itemsRowData.get("gst").getAsJsonObject().get("all").getAsJsonObject().get("amount")
							.getAsDouble();
					cgst_amt = itemsRowData.get("gst").getAsJsonObject().get("cgst").getAsJsonObject().get("amount")
							.getAsDouble();
					sgst_amt = itemsRowData.get("gst").getAsJsonObject().get("sgst").getAsJsonObject().get("amount")
							.getAsDouble();
					price_beforevalue = original_price * itemsRowData.get("qty").getAsDouble();
					price_lastvalue = price_beforevalue + gst_amt;
					original_discount = itemsRowData.get("original_discount").getAsJsonObject().get("amount")
							.getAsDouble();
				}
			} catch (Exception e) {

			}

			double cgst_perc = 0d;
			double sgst_perc = 0d;
			cgst_perc = itemsRowData.get("gst").getAsJsonObject().get("cgst").getAsJsonObject().get("percentage")
					.getAsDouble();
			sgst_perc = itemsRowData.get("gst").getAsJsonObject().get("sgst").getAsJsonObject().get("percentage")
					.getAsDouble();

			// setting values
			String uuid_items = var + this.erpFunObj.uniqueRandAndTimeStamp();
			Pos_Outlet_Bom_Items bomItemsObj1 = new Pos_Outlet_Bom_Items();
			bomItemsObj1.setUuid(uuid_items);
			bomItemsObj1.setOutlet_uuid(outlet_uuids);
			bomItemsObj1.setBillno(BillNo);
			bomItemsObj1.setBOM_ID(BOM_ID);
			bomItemsObj1.setItem_uuid(item_uuid);
			bomItemsObj1.setPortion_uuid(key);
			bomItemsObj1.setQuantity(quantity);
			bomItemsObj1.setOriginal_price(original_price);
			bomItemsObj1.setPrice_bd(price_bd);
			bomItemsObj1.setOriginal_discount_perc(original_discount_perc);
			bomItemsObj1.setOriginal_discount_amt(original_discount);
			bomItemsObj1.setDiscount_perc(discount_perc);
			bomItemsObj1.setDiscount_amt(discount_amt);
			bomItemsObj1.setAdt_discount_perc(adt_discount_perc);
			bomItemsObj1.setAdt_discount_amt(adt_discount_amt);
			bomItemsObj1.setPrice_bg(price_bg);
			bomItemsObj1.setGST_perc(gst_perc);
			bomItemsObj1.setGST_amt(gst_amt);
			bomItemsObj1.setCGST_perc(cgst_perc);
			bomItemsObj1.setCGST_amt(cgst_amt);
			bomItemsObj1.setSGST_perc(sgst_perc);
			bomItemsObj1.setSGST_amt(sgst_amt);
			bomItemsObj1.setIGST_perc(0d);
			bomItemsObj1.setIGST_amt(0d);
			bomItemsObj1.setHsn_uuid("1501cc8af3209cee");
			bomItemsObj1.setPrice(price_lastvalue);
			bomItemsObj1.setCreated_date(new Date());
			bomItemsObj1.setCreated_by(user_uuid);
			bomItemsObj1.setBills_uuid(uuid_bills);
			bomItemsObj1.setUpdated_date(new Date());
			bomItemsObj1.setUpdated_by(user_uuid);
			bomItemsObjList1.add(bomItemsObj1);

		} while (i.hasNext());

		double tot_price_bd = 0d;
		double ovrl_bill_dis_perc = 0d;
		double ovrl_bill_dis_amt = 0d;

		try {
			if (overall_bill_discObj.isJsonNull() == false) {
				ovrl_bill_dis_perc = overall_bill_discObj.get("percentage").getAsDouble();
				ovrl_bill_dis_amt = overall_bill_discObj.get("amount").getAsDouble();
				System.out.println("printing discount overall amt" + ovrl_bill_dis_amt);
				System.out.println("printing over all discount percent" + ovrl_bill_dis_perc);
			}
		} catch (NullPointerException n) {
			System.out.println("Null pointer exception occured with overall bill discounts");
		} catch (Exception e) {
			e.printStackTrace();
		}
		double bill_amt = 0d;
		try {
			bill_amt = selectedItemstotObj.get("tot_price").getAsDouble();
			System.out.println("bill amount printing" + bill_amt);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// -----------------------------------------------------------------

		session = null;
		String items_grid = "";
		String linux_grid = "";
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(Obj);
			System.out.println("POS OUTLET BILLS OBJECT PRINTINGGGGGGGGGGGGGGGGG");
			System.out.println(Obj);
			for (Pos_Outlet_Bom_Items bomItemObj1 : bomItemsObjList1) {
				session.save(bomItemObj1);
				System.out.println("BOM ITEMS OBJECT PRINTINGGGGGGGGGGGGGGGG");
				System.out.println(bomItemObj1);
			}
			session.getTransaction().commit();
			System.out.println("********Tables successfully commited*************** ");
			
			/***** designing the company details ********/
			String getCompanyDetails = commonMethodsObj.companyDetails(outlet_uuids);
			String[] compSplit = getCompanyDetails.split("@");
			
			
			/**********for windows***************/
			items_grid = compSplit[0];	
			items_grid += "<span class=\"billtitle\">ESTIMATE ORDER </span> </td>\n" + 
						 "</tr>\n" + 
						 "</table><span>----------------------------------------------------------------------------</span>\n"+
						 "<table  border=\"0\" width=\"100%\">\n"+
		    			  "<tr>\n"+
		    			  "<td style=\"font-size:17px;\"><strong>"+new Date()+"</strong></td>\n<td  style=\"width: 20%;\">&nbsp;</td>\n"+
		    			  "</tr><tr>\n"+
		    			  "<td style=\"font-size:17px !important;\"><strong>Cashier:</strong>"+httpSession.getAttribute("UserName")+"</td>\n"+
		    			  "</tr>\n"+
		    			  "</table>\n";
			
			/**********for Linux***************/
		    linux_grid = compSplit[1];	
		    linux_grid += Constants.center+"\u001b!\u0008"+"ESTIMATE ORDER"+"\u001b!\u0000"+"\n";  // Bill name
		    linux_grid += "------------------------------------------\n";
		    linux_grid += Constants.left+"\u001b!\u0008"+new Date()+"\u001b!\u0000"+"\n";
		    linux_grid += Constants.left+"\u001b!\u0008"+"Cashier:"+"\u001b!\u0000";
		    linux_grid += httpSession.getAttribute("UserName")+"\n";
		    
			/*********designing items and token*******/
		    double returnAmount = 0d;
		    int token_flag = 0;
	    	String token_grid = null;
	    	String linux_token_grid = null;
		    try {
		    	token_flag = jsonObject.get("token_flag").getAsInt();
		    	String items_data = commonMethodsObj.ItemsDetails(bomItemsObjList1, BillNo, outlet_uuids, returnAmount, token_flag);
		    	String[] parts = items_data.split("@", 2);
		    	
		    	/*******windows part*********/
		    	String part1 = parts[0];
		    	String[] w_parts = part1.split(",",2);
		    	items_grid += w_parts[0];
		    	token_grid = w_parts[1];
		    	
		    	/*********linux part**********/
		    	String part2 = parts[1];
		    	String[] l_parts = part2.split("@@",2);
		    	linux_grid += l_parts[0];
		    	linux_token_grid = l_parts[1];
		    	
		    }catch(Exception e) {
		    	token_flag = 0;
		    	String items_details = commonMethodsObj.ItemsDetails(bomItemsObjList1, BillNo, outlet_uuids, returnAmount, token_flag);
		    	String[] itemsSplit = items_details.split("@");
		    	items_grid += itemsSplit[0];
		    	linux_grid += itemsSplit[1];
		    }
			
		    /*********for gst values***************/
		    linux_grid += "\u001b!\u0008"+"GST SUMMARY"+"\u001b!\u0000"+"\n";
		    int btobordresCount = 0; 
			 String gst_details = commonMethodsObj.gstDetails(bomItemsObjList1, btobordresCount);
             String[] gst_parts = gst_details.split("@",2);
		      items_grid += gst_parts[0];
		      linux_grid += gst_parts[1];
		      linux_grid += Constants.cutpaper; 
		      
	    	  String os_type = erpFunObj.findOs(request);
	    	  String ipAddress = request.getRemoteAddr();
	          System.out.println("**Requsted system IP Address : ***" + 
	                             ipAddress+"*************"); 
		      
		      if(token_flag == 1) {
		    	  items_grid += token_grid;
		    	  linux_grid += linux_token_grid;
		    	  
		    	  if(os_type == "Linux") {
		    		  
		    		  /****** server to server*************/
		    		  if(ipAddress.equals("0:0:0:0:0:0:0:1")) {
		    			  String server_print_status = erpFunObj.sendingPrinter(linux_grid);
		    			  return "Linux"+"@"+server_print_status;
		    		  }
		    		  /****** server to node*************/
		    		  else {
		    			  String node_print_status = erpFunObj.nodePrinter(ipAddress, linux_grid);
		    			  return "Linux"+"@"+node_print_status;
		    		  }
		    	  }
		    	  
		    	  return items_grid;
		      }else {
		    	  
		    	  if(os_type == "Linux") {
		    		  
		    		  /****** server to server*************/
		    		  if(ipAddress.equals("0:0:0:0:0:0:0:1")) {
		    			  String server_print_status = erpFunObj.sendingPrinter(linux_grid);
		    			  return "Linux"+"@"+server_print_status;
		    		  }
		    		  /****** server to node*************/
		    		  else {
		    			  String node_print_status = erpFunObj.nodePrinter(ipAddress, linux_grid);
		    			  return "Linux"+"@"+node_print_status;
		    		  }
		    		  
		    	  }
		    	  return items_grid;
		      }
			/***************end of the print code*****************************/
			
			
		} catch (Exception sqlException) {
			if (null != session.getTransaction()) {
				System.out.println("Transaction Is Being Rolled Backed");
				session.getTransaction().rollback();
				items_grid = "Failed";
				return items_grid;
			}
			sqlException.printStackTrace();
		} finally {
			session.close();
		}
		return items_grid;

	}


	@Override
	public String generateDuplicateRecipt(HttpServletRequest request, HttpSession httpSession) {
		String billno = (String)request.getParameter("Billno");
	    System.out.println("request bil no with outlet is:"+billno); 
		
	    String outlet_uuid ="";
	    String user_name ="";
		try {
		   outlet_uuid = (String)httpSession.getAttribute("Outlet_uuid");
		   user_name = (String)httpSession.getAttribute("UserName");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String current_year=Constants.year;
		try {
		    	current_year = erpFunObj.getLocalDataInfo("current_year");
		}catch(Exception e) {
		    	e.printStackTrace();
		}
		
		//getting invoice result
		List<Pos_Outlet_Bom_Invoice> bomInvoiceResult = new ArrayList<Pos_Outlet_Bom_Invoice>();
		try {
		   bomInvoiceResult = ordersDao.getBillnoInvoiceDetails(billno, outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		//getting bills result
		List<Pos_Outlet_Bills> billsResult = new ArrayList<Pos_Outlet_Bills>();
		try {
			//billsResult = 
			billsResult = ordersDao.getBillsData(billno, outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Pos_Outlet_Bom_Transaction> bomTransactionList = new ArrayList<Pos_Outlet_Bom_Transaction>();
		try {
			bomTransactionList = ordersDao.getTransactionsData(billno, outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Pos_Outlet_PreOrders> preOrdersList = new ArrayList<Pos_Outlet_PreOrders>();
		try {
			preOrdersList=ordersDao.getPreOrdersData(billno, outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//*****Listing the b to b orders*****
		List<Pos_Outlet_BTOBOrders> btobOrdersList = new ArrayList<Pos_Outlet_BTOBOrders>();
		try {
			btobOrdersList=ordersDao.getbtobOrdersData(billno, outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		List<ReturnViewBean> returnViewList = new ArrayList<ReturnViewBean>();
		try { 
			returnViewList = ordersDao.getCompleteBillDetails(billno, outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String items_grid = "";
		String linux_grid = "";
		String getCompanyDetails = commonMethodsObj.companyDetails(outlet_uuid);
        String[] compSplit = getCompanyDetails.split("@");
        
		
		//System.out.println("****company details******\n"+items_grid);
		
		String outlet_uuids = String.valueOf(Integer.valueOf(outlet_uuid)+10);
		String year = Constants.year;
		String commonInBill = year+outlet_uuids;
		long presentBillNo = Long.valueOf(billno.replace(commonInBill,""));
		
		System.out.println("jabheffff"+presentBillNo);
		
		Date last_createddate = new Date();
        for(Pos_Outlet_Bills bills : billsResult) {
        	last_createddate = bills.getCreated_date();	 
        }

        int totalPreOrdersCount = 0;
	    int totalbtobOrdersCount = 0;
	    try {
       	 totalbtobOrdersCount = btobOrdersList.size();
       	 if(totalbtobOrdersCount != 0) {
       		commonInBill = "88"+year+outlet_uuids;
    		presentBillNo = Long.valueOf(billno.replace(commonInBill,""));
       	 }
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
        
	    /*************for windows****************/
	    items_grid = compSplit[0];
		String bill_header = "<span class=\"billtitle\">DUPLICATE</span> </td>\n"+
  			  		  		 "</tr>\n"+
  			  		  		 "</table><span>----------------------------------------------------------------------------</span>\n"+
  			  		  		 "<table  border=\"0\" width=\"100%\">\n"+
  			  		  		 "<tr>\n"+
  			  		  		 "<td style=\"font-size:17px;\"><strong>"+new Date()+"</strong></td>\n<td  style=\"width: 20%;\">&nbsp;</td>\n"+
  			  		  		 "<td align=\"center\"  style=\"font-weight:bold;font-size:17px !important;\">INVOICE NO</td>\n"+
  			  		  		 "</tr><tr>\n"+
  			  		  		 "<td style=\"font-size:17px !important;\"><strong>Cashier:</strong>"+httpSession.getAttribute("UserName")+"</td>\n"+
  			  		  		 "<td  style=\"width: 23%;\">&nbsp;</td><td align=\"center\" style=\" font-size:20px;\">"+presentBillNo+"</td>\n"+
  			  		  		 "</tr>\n"+
  			  		  		 "<tr><td align=\"center\" ><strong>Purchased date:</strong>"+last_createddate+"</td></tr>"+
  			  		  		 "</table>\n";
		
		/**************************************For Linux***********************************/
        linux_grid = compSplit[1];
        String linux_grid_header="";
        linux_grid_header += Constants.center+"\u001b!\u0008"+"DUPLICATE"+"\u001b!\u0000"+"\n";  // Bill name
        linux_grid_header += "------------------------------------------\n";
        linux_grid_header += "\u001b!\u0008"+new Date()+"\u001b!\u0000"+"\t";
        linux_grid_header += "\u001b!\u0008"+"INVOICE NO"+"\u001b!\u0000"+"\n";
        linux_grid_header += Constants.left+"\u001b!\u0008"+"Cashier:"+"\u001b!\u0000";
        linux_grid_header += httpSession.getAttribute("UserName")+"\t\t";
        linux_grid_header += "\u001b!\u0008"+presentBillNo+"\u001b!\u0000"+"\n";
        linux_grid_header += "\u001b!\u0008"+"Purchased date:"+"\u001b!\u0000"+last_createddate+"\n";
        
	    System.out.println("preorders lenght is ::::"+preOrdersList.size());
	    System.out.println("\n\nbtoborders lenght is ::::"+btobOrdersList.size());
	    String advance_grid = "";
	    
   	    totalPreOrdersCount = preOrdersList.size();
        try {
        	 if(totalPreOrdersCount !=0) {
        		 System.out.println("******pre orders*******");
        		 String first_name = "";
        	     for(Pos_Outlet_PreOrders preorderobj : preOrdersList) {
        	    	 first_name = preorderobj.getCustomer_name();
        	     }
     			 commonInBill = "88"+year+outlet_uuids;
     			 presentBillNo = Long.valueOf(billno.replace(commonInBill,""));
     			 
     			/***********************For Windows PreOrder Print************************/
        		 bill_header = "";
        		 bill_header = "<span class=\"billtitle\">DUPLICATE</span> </td>\n"+
			  		  		   "</tr>\n"+
			  		  		   "</table><span>----------------------------------------------------------------------------</span>\n"+
			  		  		   "<table  border=\"0\" width=\"100%\">\n"+
			  		  		   "<tr>\n"+
			  		  		   "<td style=\"font-size:17px;\"><strong>"+new Date()+"</strong></td>\n<td  style=\"width: 20%;\">&nbsp;</td>\n"+
			  		  		   "<td align=\"center\"  style=\"font-weight:bold;font-size:17px !important;\">INVOICE NO</td>\n"+
			  		  		   "</tr><tr>\n"+
			  		  		   "<td style=\"font-size:17px !important;\"><strong>Cashier:</strong>"+httpSession.getAttribute("UserName")+"</td>\n"+
			  		  		   "<td  style=\"width: 23%;\">&nbsp;</td><td align=\"center\" style=\" font-size:20px;\">P-"+presentBillNo+"</td>\n"+
			  		  		   "</tr>\n"+
			  		  		   "<tr><td colspan=\"2\" style=\"font-weight:bold;text-align:left;\">Ordered Date & Time:"+last_createddate+"</td>"+
			  		  		   "<td  style=\"width: 23%;\"> Order Taken By:"+first_name+"</td></tr>"+
			  		  		   "</table>\n";
        		 
        		
        		 /*********************************For Linux PreOrder Print**************************************/
        		 linux_grid_header = "";
                 linux_grid_header = Constants.center+"\u001b!\u0008"+"DUPLICATE"+"\u001b!\u0000"+"\n";  // Bill name
                 linux_grid_header += "------------------------------------------\n";
                 linux_grid_header += "\u001b!\u0008"+new Date()+"\u001b!\u0000"+"\t";
                 linux_grid_header += "\u001b!\u0008"+"INVOICE NO"+"\u001b!\u0000"+"\n";
                 linux_grid_header += Constants.left+"\u001b!\u0008"+"Cashier:"+"\u001b!\u0000";
                 linux_grid_header += httpSession.getAttribute("UserName")+"\t\t";
                 linux_grid_header += "\u001b!\u0008"+presentBillNo+"\u001b!\u0000"+"\n";
                 linux_grid_header += Constants.left+"\u001b!\u0008"+"Ordered Date&Time:"+"\u001b!\u0000"+last_createddate+"\n";
                 linux_grid_header += Constants.left+"\u001b!\u0008"+"Order Taken By:"+"\u001b!\u0000"+first_name+"\n";
        	 }
        }catch(Exception e) {
             e.printStackTrace();
        }
        totalbtobOrdersCount = btobOrdersList.size();
        try {
        	 if(totalbtobOrdersCount !=0) {
        		 System.out.println("******btob orders*******");
        		 String cust_gst_no = "";
        		 String cust_address = "";
        	     for(Pos_Outlet_BTOBOrders btoborderobj : btobOrdersList) {
        	    	 cust_gst_no = btoborderobj.getGsttinnumber();
        	    	 cust_address = btoborderobj.getCust_address();
        	     }
        		 bill_header += "<span>----------------------------------------------------------------------------</span>\n"
		  		  		   		+"<table  border=\"0\" width=\"100%\"><tr><td style=\"font-size:17px;\"><strong>Buyer GSTIN Number :"+cust_gst_no+"</strong></td>\n"
		  		  		   		+ "<td  style=\"width: 20%;\">&nbsp;</td><td align=\"center\"  style=\"font-weight:bold;font-size:17px !important;\">Buyer Address</td></tr>\n"
		  		  		   		+ "<tr><td style=\" font-size:17px !important;\"><strong>Place Of Supply:</strong>AndhraPradesh, Vizag</td><td  style=\"width: 23%;\">&nbsp;</td>\n"
		  		  		   		+ "<td align=\"center\" style=\" font-size:20px;\">"+cust_address+"</td></tr>\n"
		  		  		   		+ "</table>\n";
        		 
        		 /******************************Customer Details For Linux***********************************/
        		 linux_grid_header += "------------------------------------------\n";
        		 linux_grid_header += Constants.left+"\u001b!\u0008"+"Buyer GSTIN Number : "+"\u001b!\u0000"+"\t";
        		 linux_grid_header += cust_gst_no+"\n";
                 linux_grid_header += Constants.left+"\u001b!\u0008"+"Buyer Address : "+"\u001b!\u0000";
                 linux_grid_header += cust_address+"\n";
                 linux_grid_header += Constants.left+"\u001b!\u0008"+"Place Of Supply : "+"\u001b!\u0000"+"\t";
                 linux_grid_header += "AndhraPradesh, Vizag"+"\n";
        	 }
        	 
        }catch(Exception e) {
             e.printStackTrace();
        }
        
        int token_flag = 0;
        items_grid += bill_header;
        linux_grid += linux_grid_header;
        
        System.out.println("***********headerrrr_grid******* is\n"+items_grid);
        
        long Billno = Long.parseLong(billno);
        List <Pos_Outlet_Bom_Items> bomItemsObjList = new ArrayList<Pos_Outlet_Bom_Items>();
        
        for(ReturnViewBean returnViewBean : returnViewList) {
        	System.out.println(returnViewBean.toString());
        	
        	Pos_Outlet_Bom_Items bomitems = new Pos_Outlet_Bom_Items();
        	double cgst_perc = (double)returnViewBean.getGST_perc() / 2;
        	bomitems.setGST_perc(returnViewBean.getGST_perc());
        	bomitems.setCGST_perc(cgst_perc);
        	bomitems.setQuantity(returnViewBean.getQuantity());
        	bomitems.setCGST_amt(returnViewBean.getCGST_amt());
        	//bomitems.setCGST_amt(returnViewBean.getCGST_amt());
        	bomitems.setPrice(returnViewBean.getPrice());
        	bomitems.setAdt_discount_amt(returnViewBean.getAdt_discount_amt());
        	bomitems.setDiscount_amt(returnViewBean.getDiscount_amt());
        	bomitems.setOriginal_price(returnViewBean.getOriginal_price());
        	bomItemsObjList.add(bomitems);
        }
        
        double returnAmount = 0d;
        
        for(Pos_Outlet_Bom_Invoice bominvoice: bomInvoiceResult) {
        	try {
	        	System.out.println("(((old bill no is)))))::::::"+bominvoice.getOldbillnumber());
	        	if(bominvoice.getOldbillnumber()!=0) {
	        		returnAmount = ordersDao.getReturnOrderItemsPrice(bominvoice.getOldbillnumber(),outlet_uuid);
	        	}
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
  
        	/*************designing the items grid*************/
	    	String items_details = commonMethodsObj.ItemsDetails(bomItemsObjList, Billno, outlet_uuid, returnAmount, token_flag);
	    	
	    	System.out.println("items_details are****************"+items_details+"*****************************************");
	    	
	    	String[] itemsSplit = items_details.split("@",2);
	    	items_grid += itemsSplit[0];
	    	linux_grid += itemsSplit[1];
	    	
	
	    	/*try {
		    	if(totalPreOrdersCount!=0) {
			    	if(billsResult.size()>0) {
			    		int totalTransactionsCount = 0;
			    		String trans_type = "";
			    		Double trans_amt = 0d;
			    		try {
				        	 totalTransactionsCount = bomTransactionList.size();
				       }catch(Exception e) {
				             e.printStackTrace();
				       }
					   for(int i2=0;i2<totalTransactionsCount;i2++) {
					       Pos_Outlet_Bom_Transaction bomtrans = (Pos_Outlet_Bom_Transaction) bomTransactionList.get(i2);
					       trans_type = bomtrans.getTransaction_type();
					       trans_amt = bomtrans.getAmount();
				        }
					   items_grid += "<tr><td style=\"font-weight:bold;text-align: left;font-size: 15px;width: 50%;\"><strong>";
					   items_grid += "Advance Amount("+ trans_type+")";
					   items_grid += "</td><td><label style=\"font-weight:normal;font-size: 15px;\">Rs <strong>"+ trans_amt+"/-</label></strong></td>\n" + 
					   		         "</tr>";
					   
					   /**********for linux **************/
					  /*linux_grid += "\u001b!\u0008"+"Advance Amount("+trans_type+")"+"\u001b!\u0000"+"\t\t";
					   linux_grid += "\u001b!\u0008"+"Rs "+trans_amt+"\u001b!\u0000"+"/- \n";
		    		}
		    	}
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}*/
    		 
        
        /*****designing transaction details********/
        double balance_amtcheck = 0d;
        try {
	     balance_amtcheck = ordersDao.getPtoformaBillsBalanceamount(Billno);
	     
        }catch(Exception e) {
        	e.printStackTrace();
        	balance_amtcheck = 0d;
        }
        System.out.println("***********bal check is *********\n"+ balance_amtcheck);
        
        String preOrderAmountDetails = commonMethodsObj.getpreOrderAmountDetails(balance_amtcheck, bomTransactionList);
	    String [] preOrderAmountParts = preOrderAmountDetails.split("@",2);

	    /************for windows************/
	    items_grid += preOrderAmountParts[0];
	    
	    /************for linux************/
	    linux_grid += preOrderAmountParts[1];
        
	    
	    /***************gst details***************/
	    String gst_details = "";
	    
	    /*************b to b orders gst details********/
	    if(totalbtobOrdersCount!=0) {
	    	int btobordresCount = 1;
	    	gst_details = commonMethodsObj.gstDetails(bomItemsObjList, btobordresCount);
	    }
	    
	    /*************pre orders gst details********/
	    else {
	    	int btobordresCount = 0;
	    	gst_details = commonMethodsObj.gstDetails(bomItemsObjList, btobordresCount);
	    }
	   
        String[] gst_parts = gst_details.split("@",2);
	      items_grid += gst_parts[0];
	      linux_grid += gst_parts[1];
	      linux_grid += Constants.cutpaper;  
	      
	      String ipAddress = request.getRemoteAddr();
          System.out.println("**Requsted system IP Address : ***" + 
                             ipAddress+"*************"); 

	     
	      String os_type = erpFunObj.findOs(request);
	      
	      if(os_type == "Linux" ) {
	    	  
		    	  /****** server to server*************/
	    		  if(ipAddress.equals("0:0:0:0:0:0:0:1")) {
	    			  String server_print_status = erpFunObj.sendingPrinter(linux_grid);
	    			  return "Linux"+"@"+server_print_status;
	    		  }
	    		  /****** server to node*************/
	    		  else {
	    			  String node_print_status = erpFunObj.nodePrinter(ipAddress, linux_grid);
	    			  return "Linux"+"@"+node_print_status;
	    		  }
    	  }
        
		return items_grid;
	}
	
	
	@Override
	public List<PartialViewItemsBean> getOrderItemsModel(HttpServletRequest request, HttpSession session) {
		String outlet_uuid="";
		String Billno = "";
		Long billno = 0l;
		try {
			outlet_uuid = (String)session.getAttribute("Outlet_uuid");
			
			if(request.getParameter("Billno") != null)
				 Billno = (String)request.getParameter("Billno");
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(Billno!="") {
		 billno = Long.parseLong(Billno);
		}
		
		System.out.println("bill no in getOrderItemsModelis::::::"+billno);
		List<PartialViewItemsBean> returnPartialItemsList = new ArrayList<PartialViewItemsBean>();
		try {
			returnPartialItemsList = ordersDao.getOrderItemsModel(billno,outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return returnPartialItemsList;
	}


	@Override
	public Double getBalanceAmountOfPreorder(HttpServletRequest request, HttpSession session) {
		
		String Billno = "";
		Long billno = 0L;
		String outlet_uuid="";
		try {
			if(request.getParameter("billno")!= null)
				Billno = (String)request.getParameter("billno");
			
			outlet_uuid = (String)session.getAttribute("Outlet_uuid");
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(Billno!="") {
			billno = Long.valueOf(Billno);
		}
		System.out.println("Billno in getBalanceAmountOfPreorder::::"+billno);
		
		Double balanceAmount = 0d;
		try {
			balanceAmount = ordersDao.getBalanceAmount(billno,outlet_uuid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("bbalance Amount in getBalanceAmount is:"+balanceAmount);
		return balanceAmount;
	}
     
	/**
	 * generatePreOrderTotalBill===========> preorder full payment 
	 * siva kumar 
	 * 01/03/19 
	 */
	@Override
	public String generatePreOrderTotalBill(HttpServletRequest request, HttpSession httpSession) {
		System.out.println("==========================================================");
		System.out.println("in generatePreOrderTotalBill");
		System.out.println("==========================================================");
	
	
		String outlet_uuids = (String)httpSession.getAttribute("Outlet_uuid");
		String outlet_uuid = String.valueOf(Integer.valueOf(outlet_uuids)+10);
		String user_uuid = (String)httpSession.getAttribute("User_uuid");
		
		Long BillNo = 0L;
		try {
	        BillNo = billsRandFuncts.getbill_NextNumber(httpSession);	
		}catch(Exception e) {
			System.out.println("problem with setting BillNo");
			e.printStackTrace();
		}
		
		System.out.println("in service so next bill no is:"+BillNo);
		
		
		Map<String,Long> uniqueBillIDsMap = new LinkedHashMap<String,Long>();
		try {
			uniqueBillIDsMap = this.getUniqueBillIDNumber(httpSession);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		Long BOM_ID=0L;
		Long BillAmountID=0L;
		Long TransactionID = 0L;
		try {
			if((int) uniqueBillIDsMap.size() > 0) {
				BOM_ID = uniqueBillIDsMap.get("BOM_ID");
				BillAmountID = uniqueBillIDsMap.get("BillAmountID");
				TransactionID = uniqueBillIDsMap.get("TransactionID");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		System.out.println("I am going to print all the generated ID");
		System.out.println("BillNo is:"+BillNo+"----BOM_ID:"+BOM_ID+"--BillAmountID:"+BillAmountID+"--TransactionID:"+TransactionID);
		
		
		System.out.println("json data in generatePreOrderTotalBill:");
		String params_data = request.getParameter("data_array"); 
		System.out.println("params data is::::"+params_data);
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(params_data);
		JsonObject jsonObject = jsonTree.getAsJsonObject();
        
		JsonElement selectedItemArray = jsonObject.get("selectedItemArray");
		JsonObject selectedItemArrayObj = selectedItemArray.getAsJsonObject();
        System.out.println("This is the selectedItemArray:");
        System.out.println(selectedItemArrayObj.toString());
		
        
        
        
        String trans_type = "";
        String billtype = "";
        try {
        	trans_type = jsonObject.get("trans_type").getAsString();
        	 System.out.println("trans____type  is"+trans_type);
            billtype = jsonObject.get("bill_type").getAsString();
             System.out.println("billtype  is"+billtype);
        }catch(Exception e) {
        	
        }
        
       
        
        
        
        
        //previously generated Billno for preorder
        String prevBillnoStr = "";
        Long prevBillno = 0L;
        
        
        //getting billno--all rows contains same same Billno
        Set<?> keysSalectedItemObj =  selectedItemArrayObj.keySet();
	  	Iterator<?> selectItemsObjkeys = keysSalectedItemObj.iterator();
	  	
	  	//uuid for pos_outlet_bills table
	  	String uuid_bills = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
	  	
	  	
	  	/*====================Bom Items & PriceChanges ================*/
	  	List<Pos_Outlet_Bom_Items> bomItemsObjList = new ArrayList<Pos_Outlet_Bom_Items>();	
	  	List<Pos_Outlet_PriceChange> priceChangesObjList = new ArrayList<Pos_Outlet_PriceChange>();	
	  	double item_dis = 0d;
	  	double item_adt_dis = 0d;
	  	double total_dis = 0d;

	  	do{
	        String key = selectItemsObjkeys.next().toString();
	        System.out.println("This is the key:"+key);
	        JsonObject itemsRowData = selectedItemArrayObj.get(key).getAsJsonObject();
	        
	        //this billno will add only in preorder selectedItemArray
	        try {
	        	prevBillnoStr = itemsRowData.get("billno").getAsString();
	        	prevBillno = Long.valueOf(prevBillnoStr);
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	        double i_adt_dis_perc = 0d;
	        double i_adt_dis_amt = 0d;
	        double price_fordiscount = 0d;
	        try {
	        	i_adt_dis_perc = itemsRowData.get("additional_discount").getAsJsonObject().get("percentage").getAsDouble();
	        	i_adt_dis_amt =  itemsRowData.get("additional_discount").getAsJsonObject().get("amount").getAsDouble();
	        }catch(Exception e) {
	        	System.out.println("NullPointer exception with addition_discounts");
	        	//e.printStackTrace();
	        }
	        
	        item_adt_dis += i_adt_dis_amt;
	        double discount_amt = 0d;
	        try {
	        	discount_amt = itemsRowData.get("discount").getAsJsonObject().get("amount").getAsDouble();
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        total_dis = discount_amt + i_adt_dis_amt;
	        double price_bd = 0d;
	        double original_price = 0d;
	        double price_bg = 0d;
	        double original_discount_perc = 0d;
	        double discount_perc = 0d;
	        double adt_discount_perc =0d;
	        double quantity = 0d;
	        double adt_discount_amt = 0d;
	        double priceForPortion = 0d;
	      //  double discount_amt = 0d;
	        String item_uuid = "";
	        try {
	        	item_uuid = itemsRowData.get("item_id").getAsString();	
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        // double gst_perc = 0d;
	        double gst_perc = itemsRowData.get("gst").getAsJsonObject().get("all").getAsJsonObject().get("percentage").getAsDouble();
	        try {	   
	        	quantity = itemsRowData.get("qty").getAsDouble();
	        	System.out.println("quantity is:"+quantity);
	        	priceForPortion = (double)(itemsRowData.get("price").getAsDouble());
	        	original_price = (double)((itemsRowData.get("price").getAsDouble()/(gst_perc+100d))*100);
	        	System.out.println("original price is:"+original_price);
	        	price_bd = (double)(original_price * itemsRowData.get("qty").getAsDouble()); 
	        	System.out.println("price_bd is:"+price_bd);
	        	price_fordiscount = (double)itemsRowData.get("price").getAsDouble() * quantity;
	        	price_bg = (price_fordiscount)-(discount_amt + i_adt_dis_amt);
	        	System.out.println("before calculating price_bg:::::::::");
	        	System.out.println("price_fordiscount::"+price_fordiscount+"::::discount_amt is::"+discount_amt+"::::::i_adt_dis_amt::"+i_adt_dis_amt+"::::::final price_bg is::"+price_bg);
	        	original_discount_perc = itemsRowData.get("original_discount").getAsJsonObject().get("percentage").getAsDouble();
	        	discount_perc = itemsRowData.get("discount").getAsJsonObject().get("percentage").getAsDouble();
	        	try {
	        	adt_discount_perc = itemsRowData.get("additional_discount").getAsJsonObject().get("percentage").getAsDouble();
	        	adt_discount_amt =  itemsRowData.get("additional_discount").getAsJsonObject().get("amount").getAsDouble();
	        	}catch(Exception e) {
	        		
	        	}
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	        //setting gst,cgst,sgst values	        
	        double gst_amt = 0d;
	        double cgst_amt = 0d;
	        double sgst_amt = 0d;
	        double gst_exclude = 0d;
	        double price_lastvalue = 0d;
	        double price_beforevalue = 0d;
	        double original_discount = 0d;
	        try {
		        if(original_discount_perc > 0 || discount_perc>0 || adt_discount_perc>0) {
		        	gst_exclude = (price_bg/(gst_perc+100d))*100;
		        	gst_amt = (double)(gst_exclude*(gst_perc/100));
		        	cgst_amt = (double)(gst_amt/2);
		        	sgst_amt = (double)(gst_amt/2);     //both cgst_amt & sgst_amt carries half in the gst amount
		        	price_lastvalue = gst_exclude + gst_amt;
		        }else {
		        	gst_amt = itemsRowData.get("gst").getAsJsonObject().get("all").getAsJsonObject().get("amount").getAsDouble();
		            cgst_amt = itemsRowData.get("gst").getAsJsonObject().get("cgst").getAsJsonObject().get("amount").getAsDouble();
		            sgst_amt  = itemsRowData.get("gst").getAsJsonObject().get("sgst").getAsJsonObject().get("amount").getAsDouble();
		            price_beforevalue = original_price * itemsRowData.get("qty").getAsDouble();
		            price_lastvalue   = price_beforevalue +  gst_amt;
		            original_discount = itemsRowData.get("original_discount").getAsJsonObject().get("amount").getAsDouble();
		        } 
	        }catch(Exception e) {
	        	
	        }
	        
	        double cgst_perc = 0d;
	        double sgst_perc = 0d;
	        cgst_perc = itemsRowData.get("gst").getAsJsonObject().get("cgst").getAsJsonObject().get("percentage").getAsDouble();
	        sgst_perc = itemsRowData.get("gst").getAsJsonObject().get("sgst").getAsJsonObject().get("percentage").getAsDouble();
	        
	        
	        String bom_items_uuid = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
	        Pos_Outlet_Bom_Items bomItemsObj = new Pos_Outlet_Bom_Items();
	        bomItemsObj.setUuid(bom_items_uuid);
	        bomItemsObj.setOutlet_uuid(outlet_uuids);
	        bomItemsObj.setBillno(BillNo);
	        bomItemsObj.setBOM_ID(BOM_ID);
	        bomItemsObj.setItem_uuid(item_uuid);
	        bomItemsObj.setPortion_uuid(key);
	        bomItemsObj.setQuantity(quantity);
	        bomItemsObj.setOriginal_price(original_price);
	        bomItemsObj.setPrice_bd(price_bd);
	        bomItemsObj.setOriginal_discount_perc(original_discount_perc);
	        bomItemsObj.setOriginal_discount_amt(original_discount);
	        bomItemsObj.setDiscount_perc(discount_perc);
	        bomItemsObj.setDiscount_amt(discount_amt);
	        bomItemsObj.setAdt_discount_perc(adt_discount_perc);
	        bomItemsObj.setAdt_discount_amt(adt_discount_amt);
	        bomItemsObj.setPrice_bg(price_bg);
	        bomItemsObj.setGST_perc(gst_perc);
	        bomItemsObj.setGST_amt(gst_amt);
	        bomItemsObj.setCGST_perc(cgst_perc);
	        bomItemsObj.setCGST_amt(cgst_amt);
	        bomItemsObj.setSGST_perc(sgst_perc);
	        bomItemsObj.setSGST_amt(sgst_amt);
	        bomItemsObj.setIGST_perc(0d);
	        bomItemsObj.setIGST_amt(0d);
	        bomItemsObj.setPrice(price_lastvalue);
	        bomItemsObj.setCreated_date(new Date());
	        bomItemsObj.setCreated_by(user_uuid);
	        bomItemsObj.setBills_uuid(uuid_bills);
	   
	        bomItemsObjList.add(bomItemsObj);
	        
	        
	        //adding price changes conditions
	       double previous_price = 0d;
	        try {
	        	previous_price = ordersDao.getPreviousPriceForThisItemPortion(outlet_uuids,item_uuid,key);
	        }catch(Exception e) {
	        	System.out.println("price change history not found");
	        }
	        
	        System.out.println("previous_price for this portion is:"+Math.round(previous_price)+" and "+"price for portion is:::"+Math.round(priceForPortion));
	           
	        Pos_Outlet_PriceChange priceChangesObjs = new Pos_Outlet_PriceChange();
			System.out.println("Object created for Pos_Outlet_PriceChange");
			
	        try {
	        	if(previous_price > 0d) {
	        		if((Double.compare(Math.round(previous_price), Math.round(priceForPortion)) != 0)|| ((i_adt_dis_perc !=0d)&&(i_adt_dis_perc>20d))) {
	        			
	        			System.out.println("=============@@@@@@@@@In if conditions@@@@@@@@@@===============");
	        			String uuid_pricechanges = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
	        			Pos_Outlet_PriceChange priceChangesObj = new Pos_Outlet_PriceChange();
	        			System.out.println("Object created for Pos_Outlet_PriceChange in if conditions");
	        			priceChangesObj.setUuid(uuid_pricechanges);
	        			priceChangesObj.setOutlet_uuid(outlet_uuids);
	        			priceChangesObj.setUser_uuid(user_uuid);
	        			priceChangesObj.setItem_uuid(item_uuid);
	        			priceChangesObj.setPortion_uuid(key);
	        			priceChangesObj.setOld_priceval(previous_price);
	        			priceChangesObj.setNew_priceval(priceForPortion);
	        			priceChangesObj.setCreated_date(new Date());
	        			priceChangesObj.setBillno(BillNo);
	        			priceChangesObj.setAdt_perc_discval(i_adt_dis_perc);
	        			priceChangesObj.setAdt_amt_discval(i_adt_dis_amt);
	        			priceChangesObj.setItem_perc_discval(discount_perc);
	        			priceChangesObj.setItem_amt_discval(discount_amt);
	        			priceChangesObj.setCreated_by(user_uuid);
	        			//priceChangesObj.setStatus();
	        			priceChangesObj.setUpdated_date(new Date());
	        			priceChangesObj.setUpdated_by(user_uuid);
	        			priceChangesObj.setBills_uuid(uuid_bills);
	        			
	        			
	        			priceChangesObjList.add(priceChangesObj);
	        		}
	        	}
	        }catch(Exception e) {
	        	
	        }
	        
	        
	    }while(selectItemsObjkeys.hasNext());
		//end of do-while
	  	
	  	/*====================Bom Items & PriceChanges ================*/
	  	
	  	System.out.println("================Items Bean is =====================");
	  	bomItemsObjList.forEach(System.out::println);
	    System.out.println("=====================================================");
	    
	    
	    System.out.println("================priceChangesObjList Bean is =====================");
	    priceChangesObjList.forEach(System.out::println);
	    System.out.println("=====================================================");


        String billSubStr = "";
        Long invoiceID = 0L;
        Double paidAmtValue = 0D;
	  	if(prevBillnoStr!="") {
	  		billSubStr = prevBillnoStr.substring(0,2);
	  		if(billSubStr.equals("88")) {
	  			List<Pos_Outlet_ProformaBills> proformaBillData= new ArrayList<Pos_Outlet_ProformaBills>();
	  			try {
	  				proformaBillData = ordersDao.getProFormaBillsData(prevBillnoStr, outlet_uuids);
	  				if(proformaBillData.size() > 0) {
	  					invoiceID = proformaBillData.get(0).getInvoiceID();
	  					paidAmtValue = proformaBillData.get(0).getPaid_amt();
	  				}
	  			}catch(Exception e) {
	  				System.out.println("proforma data not found with bill no:"+prevBillnoStr);
	  				e.printStackTrace();
	  			}
	  		}else {
	  			List<Pos_Outlet_Bom_Invoice> bomInvoiceResult = new ArrayList<Pos_Outlet_Bom_Invoice>();
	  			try {
	  				bomInvoiceResult = ordersDao.getBillnoInvoiceDetails(prevBillnoStr, outlet_uuids);
	  				if(bomInvoiceResult.size() > 0) {
	  					invoiceID = bomInvoiceResult.get(0).getInvoiceID();
	  					paidAmtValue = bomInvoiceResult.get(0).getPaid_amt();
	  				}
	  			}catch(Exception e) {
	  				System.out.println("invoice data not found with bill no:"+prevBillnoStr);
	  				e.printStackTrace();
	  			}
	  		}
	  	   	
	  	}
	  	
	  	System.out.println("invoice id is:"+invoiceID);
	  	System.out.println("paidAmtValue  is:"+paidAmtValue);
	  	
	  	
	  	/*===============To check preorder or btob order======================*/
	  	   String isPreOrderOrBtoB = "preOrder";
	  	   int resultPreOrderCount = 0;
	  	   int resultBtoBOrderCount = 0;
	  	   try {
	  		 List<Pos_Outlet_PreOrders> preOrdersList = new ArrayList<Pos_Outlet_PreOrders>();
	  		 preOrdersList = ordersDao.getPreOrdersData(prevBillnoStr, outlet_uuids);
	  		 resultPreOrderCount = preOrdersList.size();
		  		  if(resultPreOrderCount>0) {
		  			isPreOrderOrBtoB = "preOrder";
		  		    System.out.println("this is preorder full payment");
		  		  }
	  	   }catch(Exception e) {
	  		   
	  	   }
	  	   
	  	   try {
	  		 List<Pos_Outlet_BTOBOrders> btobOrdersList = new ArrayList<Pos_Outlet_BTOBOrders>();
	  		 btobOrdersList = ordersDao.getbtobOrdersData(prevBillnoStr, outlet_uuids);
	  		 resultBtoBOrderCount = btobOrdersList.size();
	  		      if(resultPreOrderCount>0) {
		  			isPreOrderOrBtoB = "btobOrder";
		  		    System.out.println("this is btobOrder full payment");
		  		  }
	  	   }catch(Exception e) {
	  		   
	  	   }
	  	   
	  	   
	  	/*========================================================*/
	  	
	  	
	  	
	  	
	  	/**
	  	 * need to add flag check condition --> preorder or btob order 
	  	 */
	  	/*if(invoiceID > 0) {
	  		
	  	}*/
	  	uniqueBillIDsMap.put("InvoiceID", invoiceID);
	  	uniqueBillIDsMap.put("BillNo", BillNo);
	  	//getting object to insert into pos_outlet_bills 
	  	
	    /*String trans_type = jsonObject.get("trans_type").getAsString();
	     System.out.println("trans____type  is"+trans_type);
	     String billtype = jsonObject.get("bill_type").getAsString();
	     System.out.println("billtype  is"+billtype);*/
	  	
	  	List<Pos_Outlet_Bills> billsObjsList = new ArrayList<Pos_Outlet_Bills>();
	  	try {
	  		billsObjsList = this.setDataInBills(uuid_bills, outlet_uuids, user_uuid, uniqueBillIDsMap,billtype);
	  	}catch(Exception e) {
	  		e.printStackTrace();
	  	}
	  	System.out.println("================billsObjsList Bean is =====================");
	  	billsObjsList.forEach(System.out::println);
	    System.out.println("=====================================================");
	  	
	  	
	  	/**
	  	 * For pos_outlet_bom_amount
	  	 */
	  	//List to store the bom_amounts Objects
	  	List<Pos_Outlet_Bom_Amounts> AmountsObjsList = new ArrayList<Pos_Outlet_Bom_Amounts>();
	  	
	  	JsonObject selectedItemstotObj  = new JsonObject();
	  	try {
	  	   JsonElement selectedItemstot = jsonObject.get("selectedItemstot");
	       selectedItemstotObj = selectedItemstot.getAsJsonObject();
	  	}catch(Exception e) {
	  	   System.out.println("selectedItemstot was not set");
	  	}
		
	  	JsonObject overall_bill_discObj = new JsonObject();
	  	try {
		JsonElement overall_bill_disc = jsonObject.get("overall_bill_disc");
	    overall_bill_discObj = overall_bill_disc.getAsJsonObject();
	  	}catch(Exception e) {
	  		System.out.println("problem with setting over_bill_discObj");
	  	}
		
		double tot_price_bd = 0d;
		double ovrl_bill_dis_perc = 0d;
		double ovrl_bill_dis_amt = 0d;
		
		try {
			tot_price_bd = selectedItemstotObj.get("tot_before_gst").getAsDouble() - total_dis;
			
		}catch(Exception e) {
			System.out.println("exception occured with overall bill discounts");
			 e.printStackTrace();
		}
		
	   try {
			if(overall_bill_discObj.isJsonNull() == false) {
					ovrl_bill_dis_perc = overall_bill_discObj.get("percentage").getAsDouble();
				}
			}catch(Exception e) {
				System.out.println("exception occured with overall bill discounts");
				 e.printStackTrace();
	    }
	  try {
			if(overall_bill_discObj.isJsonNull() == false) {
				ovrl_bill_dis_amt = overall_bill_discObj.get("amount").getAsDouble();
			}
		}catch(Exception e) {
				System.out.println("exception occured with overall bill discounts");
				e.printStackTrace();
	   }
	  
		double bill_amt = 0d;
	     try {
	    	 bill_amt = selectedItemstotObj.get("tot_price").getAsDouble();
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
		
	     Map<String,Double> detailsToSetInAmountsMap = new LinkedHashMap<String,Double>();
	     detailsToSetInAmountsMap.put("tot_price_bd", tot_price_bd);
	     detailsToSetInAmountsMap.put("item_dis", item_dis);
	     detailsToSetInAmountsMap.put("ovrl_bill_dis_perc", ovrl_bill_dis_perc);
	     detailsToSetInAmountsMap.put("ovrl_bill_dis_amt", ovrl_bill_dis_amt);
	     detailsToSetInAmountsMap.put("total_dis", total_dis);
	     detailsToSetInAmountsMap.put("item_dis", item_dis);
	     detailsToSetInAmountsMap.put("bill_amt", bill_amt);
	     detailsToSetInAmountsMap.put("ovrl_bill_dis_amt", ovrl_bill_dis_amt);
	     	     
	     try {
	    	 AmountsObjsList = setDataInAmounts(uuid_bills, outlet_uuids, user_uuid, uniqueBillIDsMap, detailsToSetInAmountsMap, selectedItemstotObj);
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
	     System.out.println("================AmountsObjsList Bean is =====================");
	     AmountsObjsList.forEach(System.out::println);
	     System.out.println("=====================================================");
	     
	     
	     /**
	      * Setting in Transaction 
	      */
         //Double paidAmt = 0d;
	     Double remainingamount = 0d;
	     
	     //to store Transaction Objects
	     List<Pos_Outlet_Bom_Transaction> bomtransObjList = new ArrayList<Pos_Outlet_Bom_Transaction>();	
	     
	     
	     JsonElement paymentModeArray = jsonObject.get("payment_mode_ary");
		 JsonObject paymentModeArrayObj = paymentModeArray.getAsJsonObject();
	     System.out.println("This is the payment_mode_ary:");
	     System.out.println(paymentModeArrayObj.toString());
	     
	  
	      
	     
	     Set<?> set =  paymentModeArrayObj.keySet();
	        Iterator<?> iterator = set.iterator();
	        do{
	            String pays_key = iterator.next().toString();
	            System.out.println("key is"+pays_key);
	            Double amount = 0d;
	            try {
	            	
	            JsonObject paymentRowData = paymentModeArrayObj.get(pays_key).getAsJsonObject();
	            System.out.println("value is "+paymentRowData.get("amount"));
	            amount = paymentRowData.get("amount").getAsDouble();
	            }catch(Exception e) {
	            	e.printStackTrace();
	            }
	            
	            System.out.println("amount issssssssssssssssssssssss:::::"+amount);
	            
	            remainingamount += amount;
	            System.out.println("remaining amount iss:::::"+remainingamount);
	        }while(iterator.hasNext());
	     
	        remainingamount +=  paidAmtValue;
	        System.out.println("finalllll remaining amount iss:::::"+remainingamount);
	        
	        
	     try {
	    	 bomtransObjList = setDataInTransaction(uuid_bills, outlet_uuids, user_uuid, uniqueBillIDsMap, detailsToSetInAmountsMap, paymentModeArrayObj, trans_type);
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
	     System.out.println("================bomtransObjList Bean is =====================");
	     bomtransObjList.forEach(System.out::println);
	     System.out.println("=====================================================");
	     
	     /**
	      * Setting in Invoice 
	      */
	     //To Store the invoiceDetails
	     List<Pos_Outlet_Bom_Invoice> bomInvoiceObjList = new ArrayList<Pos_Outlet_Bom_Invoice>();
	     try {
	    	 bomInvoiceObjList = setDataInInvoice(uuid_bills, outlet_uuids, user_uuid, uniqueBillIDsMap, detailsToSetInAmountsMap, selectedItemstotObj);
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
	     System.out.println("================Invoice Bean is =====================");
	     bomInvoiceObjList.forEach(System.out::println);
	     System.out.println("=====================================================");
	     
	     /*
			double selectedItemsAdvance = 0d;
			try {
				selectedItemsAdvance = jsonObject.get("selectedItemsadvance").getAsDouble();
			}catch(Exception e) {
				e.printStackTrace();
			}*/

	     
		/*	double balance_amt = 0d;
			balance_amt =  (double)(bill_amt - selectedItemsAdvance);*/
			
			Pos_Outlet_ProformaBills proformaBillsObj = new Pos_Outlet_ProformaBills();
			String proforma_uuid = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
			proformaBillsObj.setUuid(proforma_uuid);
			proformaBillsObj.setMaintax_Invoice(BillNo);
			proformaBillsObj.setTransactionID(TransactionID);
			proformaBillsObj.setBill_amt(bill_amt);
			proformaBillsObj.setPaid_amt(remainingamount);
			proformaBillsObj.setBalance_amt(0d);
			//proformaBillsObj.setIp_address("");
			//proformaBillsObj.setCreated_date(new Date());
			//proformaBillsObj.setCreated_by(user_uuid);
			proformaBillsObj.setUpdated_date(new Date());
			proformaBillsObj.setUpdated_by(user_uuid);
			//proformaBillsObj.setStatus()
			proformaBillsObj.setUpdated_date(new Date());
			proformaBillsObj.setUpdated_by(user_uuid);
			proformaBillsObj.setBills_uuid(uuid_bills);
			
		
	     System.out.println("================proformaBillsObj Bean is =====================");
	     System.out.println(proformaBillsObj.toString());
	     System.out.println("=====================================================");
	     
	     
	     System.out.println("=================previous bill no is:===============:"+prevBillno);
	     
	        Session session=null;
	        String items_grid = "";
	        String linux_grid = "";
			try {
				session=sessionFactory.openSession();
				//transaction = (Transaction) session.beginTransaction();
			    session.beginTransaction();
			    
			    for (Pos_Outlet_Bills billsObj : billsObjsList){
				      session.save(billsObj);
				}
			    
			    for (Pos_Outlet_Bom_Items bomItemObj : bomItemsObjList){
				      session.save(bomItemObj);
				}
			    
			    for (Pos_Outlet_PriceChange priceChangeObj : priceChangesObjList){
				      session.save(priceChangeObj);
				}
			    
			    for (Pos_Outlet_Bom_Amounts amountsObj : AmountsObjsList){
				      session.save(amountsObj);
				}
			    
			    for (Pos_Outlet_Bom_Transaction transactionObj : bomtransObjList){
				      session.save(transactionObj);
				}
			    
			    for (Pos_Outlet_Bom_Invoice invoiceObj : bomInvoiceObjList){
				      session.save(invoiceObj);
				}
			    
			   // session.saveOrUpdate(proformaBillsObj);
			    /**
			     * a.Maintax_Invoice=:Maintax_Invoice,a.TransactionID=:TransactionID,a.bill_amt=:bill_amt,
			     */
				 System.out.println("before inserting the proformaData");
				  int result = 0;
				    Query query = session.createQuery("UPDATE pos_outlet_proformabills a set a.uuid=:uuid, a.Maintax_Invoice=:Maintax_Invoice,a.TransactionID=:TransactionID,a.bill_amt=:bill_amt,a.paid_amt=:paid_amt,a.balance_amt=:balance_amt, a.updated_date=:updated_date,a.updated_by=:updated_by,a.bills_uuid=:bills_uuid where a.Billno =:BillNo");
				    query.setParameter("uuid", proformaBillsObj.getUuid());
				    query.setParameter("Maintax_Invoice", proformaBillsObj.getMaintax_Invoice());
				    System.out.println(" before commit MainTaxInvoice or new Bill no is:::"+proformaBillsObj.getMaintax_Invoice());
				    query.setParameter("TransactionID", proformaBillsObj.getTransactionID());
				    query.setParameter("bill_amt", proformaBillsObj.getBill_amt());
				    query.setParameter("paid_amt",proformaBillsObj.getPaid_amt());
				    query.setParameter("balance_amt",proformaBillsObj.getBalance_amt());
				    query.setParameter("updated_date", proformaBillsObj.getUpdated_date());
				    query.setParameter("updated_by", proformaBillsObj.getUpdated_by());
				    query.setParameter("bills_uuid", proformaBillsObj.getBills_uuid());
				    query.setParameter("BillNo", prevBillno);
				    result = query.executeUpdate();

				//int returnResult = insertProformaInDB(proformaBillsObj,prevBillno);
				    
				    int result2 = 0;
				    String orderUpdateQry="";
				    String gsttin = "";
				    String customer_address = "";
				    if(isPreOrderOrBtoB.equals("preOrder")) {
				    	orderUpdateQry = "UPDATE pos_outlet_preorders a set a.Orderstatus='Paid' where a.BillNo =:BillNo and outlet_uuid=:outlet_uuid and status='Active'";
				    }
				    if(isPreOrderOrBtoB.equals("btobOrder")) {
				    	orderUpdateQry = "UPDATE pos_outlet_btoborders a set a.orderstatus='paid' where a.Billno =:BillNo and outlet_uuid=:outlet_uuid and status='Active'";
				    	Query selectQry = session.createQuery("select gsttinnumber, cust_address from pos_outlet_btoborders where Billno =:BillNo");
				    	selectQry.setParameter("BillNo", prevBillno);
				        System.out.println("qry result is"+selectQry.list());
				        
				        List<Object[]> results = (List<Object[]>) selectQry.list();
				        
				        for(Object[] result1: results){	 
				        	gsttin = (String) result1[0];
				        	customer_address = (String) result1[1];
				        }

				    }
				    Query queryTableUpdate = session.createQuery(orderUpdateQry);
				    queryTableUpdate.setParameter("BillNo", prevBillno);
				    queryTableUpdate.setParameter("outlet_uuid", outlet_uuids);
				    result2 = queryTableUpdate.executeUpdate();
				    
				 session.getTransaction().commit();
				 if(result>0 && result2>0)
				      System.out.println("===============Transaction successfully committed for preorder full payment============");
			      
			      System.out.println("*************Designing the bill********************");
				    String billno = String.valueOf(BillNo);
			        String year=Constants.year;
					String commonInBill = year+outlet_uuid;
					long presentBillNo = Long.valueOf(billno.replace(commonInBill,""));
					
				   /*****designing the company details********/
					String getCompanyDetails = commonMethodsObj.companyDetails(outlet_uuids);
					String[] compSplit = getCompanyDetails.split("@");
					
					System.out.println("compSplit compSplit"+compSplit[0]);
					System.out.println("\n\n\n**********"+compSplit[1]);
					
					if(isPreOrderOrBtoB.equals("preOrder")) {
					
						items_grid = compSplit[0];	
						linux_grid = compSplit[1];	
						
						/**********for windows***************/
						
					    items_grid += "<span class=\"billtitle\">TAX INVOICE </span> </td>\n"+
					    			  "</tr>\n"+
					    			  "</table><span>----------------------------------------------------------------------------</span>\n"+
					    			  "<table  border=\"0\" width=\"100%\">\n"+
					    			  "<tr>\n"+
					    			  "<td style=\"font-size:17px;\"><strong>"+new Date()+"</strong></td>\n<td  style=\"width: 20%;\">&nbsp;</td>\n"+
					    			  "<td align=\"center\"  style=\"font-weight:bold;font-size:17px !important;\">INVOICE NO</td>\n"+
					    			  "</tr><tr>\n"+
					    			  "<td style=\"font-size:17px !important;\"><strong>Cashier:</strong>"+httpSession.getAttribute("UserName")+"</td>\n"+
					    			  "<td  style=\"width: 23%;\">&nbsp;</td><td align=\"center\" style=\" font-size:20px;\">"+presentBillNo+"</td>\n"+
					    			  "</tr>\n"+
					    			  "</table>\n";
					    
					    
					    /**********for Linux***************/
					    
					    
					    linux_grid += Constants.center+"\u001b!\u0008"+"TAX INVOICE"+"\u001b!\u0000"+"\n";  // Bill name
					    linux_grid += "------------------------------------------\n";
					    linux_grid += Constants.left+"\u001b!\u0008"+new Date()+"\u001b!\u0000"+"\t";
					    linux_grid += "\u001b!\u0008"+"INVOICE NO"+"\u001b!\u0000"+"\n";
					    linux_grid += Constants.left+"\u001b!\u0008"+"Cashier:"+"\u001b!\u0000";
					    linux_grid += httpSession.getAttribute("UserName")+"\t\t ";
					    linux_grid += "\u001b!\u0008"+presentBillNo+"\u001b!\u0000"+"\n";
					    
					}else {
						
						items_grid = compSplit[0];	
						linux_grid = compSplit[1];	
						
						/**********for windows***************/
						items_grid += "<input type=\"hidden\" name=\"gsttin_number\" value="+gsttin+" id=\"gsttin_number\">\n" + 
								      "<input type=\"hidden\" name=\"billnumber_forreceipt\" value="+"B-"+presentBillNo+" id=\"billnumber_forreceipt\">";
					    items_grid += "<span class=\"billtitle\">ORIGINAL TAX INVOICE</span> </td>\n"+
					    			  "</tr>\n"+
					    			  "</table><span>----------------------------------------------------------------------------</span>\n"+
					    			  "<table  border=\"0\" width=\"100%\">\n"+
					    			  "<tr>\n"+
					    			  "<td style=\"font-size:17px;\"><strong>"+new Date()+"</strong></td>\n<td  style=\"width: 20%;\">&nbsp;</td>\n"+
					    			  "<td align=\"center\"  style=\"font-weight:bold;font-size:17px !important;\">INVOICE NO</td>\n"+
					    			  "</tr><tr>\n"+
					    			  "<td style=\"font-size:17px !important;\"><strong>Cashier:</strong>"+httpSession.getAttribute("UserName")+"</td>\n"+
					    			  "<td  style=\"width: 23%;\">&nbsp;</td><td align=\"center\" style=\" font-size:20px;\">B-"+presentBillNo+"</td>\n"+
					    			  "</tr>\n"+
					    			  "</table>\n";
					    
					    items_grid += "<span>----------------------------------------------------------------------------</span>\n"
		  		  		   		+"<table  border=\"0\" width=\"100%\"><tr><td style=\"font-size:17px;\"><strong>Buyer GSTIN Number :"+gsttin+"</strong></td>\n"
		  		  		   		+ "<td  style=\"width: 20%;\">&nbsp;</td>"
		  		  		   		+ "<td align=\"center\"  style=\"font-weight:bold;font-size:17px !important;\">Buyer Address</td><td align=\"center\" style=\" font-size:20px;\">"+customer_address+"</td></tr>\n"
		  		  		   		+ "<tr><td style=\" font-size:17px !important;\"><strong>Place Of Supply:</strong>AndhraPradesh, Vizag</td><td  style=\"width: 23%;\">&nbsp;</td>\n"
		  		  		   		+ "</tr>\n"
		  		  		   		+ "</table>\n";
					    
					    /******************************Customer Details For Linux***********************************/
					    
					    linux_grid += Constants.center+"\u001b!\u0008"+"ORIGINAL TAX INVOICE"+"\u001b!\u0000"+"\n";  // Bill name
					    linux_grid += "------------------------------------------\n";
					    linux_grid += "\u001b!\u0008"+new Date()+"\u001b!\u0000"+"\t";
					    linux_grid += "\u001b!\u0008"+"INVOICE NO"+"\u001b!\u0000"+"\n";
					    linux_grid += Constants.left+"\u001b!\u0008"+"Cashier:"+"\u001b!\u0000";
					    linux_grid += httpSession.getAttribute("UserName")+"\t\t";
					    linux_grid += "\u001b!\u0008"+presentBillNo+"\u001b!\u0000"+"\n";
					    
					    linux_grid += "------------------------------------------\n";
					    linux_grid += Constants.left+"\u001b!\u0008"+"Buyer GSTIN Number : "+"\u001b!\u0000"+"\t";
					    linux_grid += gsttin+"\n";
					    linux_grid += Constants.left+"\u001b!\u0008"+"Buyer Address : "+"\u001b!\u0000";
					    linux_grid += customer_address+"\n";
					    linux_grid += Constants.left+"\u001b!\u0008"+"Place Of Supply : "+"\u001b!\u0000"+"\t";
		                linux_grid += "AndhraPradesh, Vizag"+"\n";
					    
					}
				    
				    
				    double returnAmount = 0d;
					int token_flag = 0;
					
					/*************designing the items grid*************/
			    	String items_details = commonMethodsObj.ItemsDetails(bomItemsObjList, BillNo, outlet_uuids, returnAmount , token_flag );
			    	
			    	System.out.println("items_details are****************"+items_details+"*****************************************");
			    	
			    	String[] itemsSplit = items_details.split("@",2);
			    	items_grid += itemsSplit[0];
			    	linux_grid += itemsSplit[1];
			    	
			    	
			    	/*****designing transaction details********/
			    	DecimalFormat df = new DecimalFormat("#.00");
			    	items_grid += "<tr><td style=\"text-align:left;\"><strong>Advance Amount";
			    	items_grid += "<td style=\"padding-left:3px;\"><label style=\"font-weight:normal;\">Rs</label> <strong>"+ df.format(paidAmtValue) 									+"/-</strong></td>\n" + 
			    			      "</tr>";
			    	
			    	linux_grid += Constants.left+"\u001b!\u0008"+"Advance Amount"+"\u001b!\u0000"+"\t\t ";
				    linux_grid += df.format(paidAmtValue)+"/-"+"\n";
				    
				    double balance_amtcheck = 0.0;
				    int check = 0;
			        int check_else = 0;
				    
			        int totalTransactionsCount = 0;
				       try {
				        	 totalTransactionsCount = bomtransObjList.size();
				       }catch(Exception e) {
				             e.printStackTrace();
				       }
				       
				     for(int i2=0;i2<totalTransactionsCount;i2++) {
				       Pos_Outlet_Bom_Transaction bomtrans = (Pos_Outlet_Bom_Transaction) bomtransObjList.get(i2);
			            //$tamt += $value['amount'];
			            items_grid += "<tr><td style=\"text-align:left;\"><strong>";
			            if (balance_amtcheck == 0.0) {
			                    check_else = 1;
			                    items_grid += "Paid Amount(" +bomtrans.getTransaction_type() +")</strong> </td>";
			                    linux_grid += Constants.left+"\u001b!\u0008"+"Paid Amount("+bomtrans.getTransaction_type() +")"+"\u001b!\u0000"+"\t  ";
							    
			               
			            } else {
			                items_grid += bomtrans.getTransaction_type();
			                
			            }
			            items_grid += "<td style=\"padding-left:3px;\"><label style=\"font-weight:normal;\">Rs</label> <strong>"+ df.format(bomtrans.getAmount())+"/-</strong></td>\n" + 
			            		"	</tr>";
			            
			            linux_grid += df.format(bomtrans.getAmount())+"/-"+"\n";
			            
			            check++;
				    }
				    
				     items_grid += "<tr><td style=\"text-align:left;\"><strong>";
				        if (balance_amtcheck != 0.0) {
				            double bal_amt = balance_amtcheck;
				            items_grid += "<strong>Balance Amount:</strong>";
				            items_grid += "</strong></td><td style=\"padding-left:3px;\"><label>Rs</label> <strong>"+bal_amt+"/-</strong></td></tr>";
				            
				            linux_grid += Constants.left+"\u001b!\u0008"+"Balance Amount:"+"\u001b!\u0000"+"\t\t ";
				            linux_grid += "Rs"+bal_amt+"/-"+"\n";
				         
				        }
				        else {
				            int bal_amt = 0;
				            items_grid += "<strong>Balance Amount:</strong>";
				            items_grid += "</strong></td><td style=\"padding-left:3px;\"><label style=\"font-weight:normal;\">Rs</label> 											<strong>"+bal_amt+"/-</strong></td></tr>";
				            
				            linux_grid += Constants.left+"\u001b!\u0008"+"Balance Amount:"+"\u001b!\u0000"+"\t\t ";
				            linux_grid += "Rs"+bal_amt+"/-"+"\n";
				            
				        }
				        
				        items_grid += "</table><span>----------------------------------------------------------------------------</span><div class=\"divTable\"><div class=\"divTableBody\"><div class=\"divTableRow\"><div class=\"divTableCell\"><strong>GST SUMMARY</strong></div><div class=\"divTableCell\"></div><div class=\"divTableCell\"></div><div class=\"divTableCell\"></div></div></div></div>";
				    
				        linux_grid += "------------------------------------------\n";
				        linux_grid += "\u001b!\u0008"+"GST SUMMARY"+"\u001b!\u0000"+"\n";
				    
				    /*double balance_amtcheck = 0.0;
				    
				    String preOrderAmountDetails = commonMethodsObj.getpreOrderAmountDetails(balance_amtcheck, bomtransObjList);
				    String [] preOrderAmountParts = preOrderAmountDetails.split("@",2);
*/
				    /************for windows************/
				   // items_grid += preOrderAmountParts[0];
				    
				    /************for linux************/
				   // linux_grid += preOrderAmountParts[1];
				    
				    
				    String gst_details = "";
				    
				    
				    if(isPreOrderOrBtoB.equals("preOrder")) {
					    int btobordresCount = 0;
					   	gst_details = commonMethodsObj.gstDetails(bomItemsObjList, btobordresCount);
				    }else {
				    	int btobordresCount = 1;
					   	gst_details = commonMethodsObj.gstDetails(bomItemsObjList, btobordresCount);
				    }
				    
				    
		            String[] gst_parts = gst_details.split("@",2);
				    items_grid += gst_parts[0];
				    linux_grid += gst_parts[1];
				    linux_grid += Constants.cutpaper; 
				    
				    String ipAddress = request.getRemoteAddr();
			        System.out.println("**Requsted system IP Address : ***" + 
			                             ipAddress+"*************"); 

				    
				    
				    String os_type = erpFunObj.findOs(request);
				    
				    System.out.println("the windows are ******"+items_grid+"\n\n\n\n");
				    
				    System.out.println("the windows are ******"+linux_grid+"\n\n\n\n");
				      
				    if(os_type == "Linux" && isPreOrderOrBtoB.equals("preOrder")) {
				    	  
				    	  /****** server to server*************/
			    		  if(ipAddress.equals("0:0:0:0:0:0:0:1")) {
			    			  String server_print_status = erpFunObj.sendingPrinter(linux_grid);
			    			  return "Linux"+"@"+server_print_status;
			    		  }
			    		  /****** server to node*************/
			    		  else {
			    			  String node_print_status = erpFunObj.nodePrinter(ipAddress, linux_grid);
			    			  return "Linux"+"@"+node_print_status;
			    		  }
		    	  }
				    //**************For bto b order***********************/
				  if(isPreOrderOrBtoB.equals("btobOrder")) {
					  return items_grid+"@"+"btobOrder";
				  }
		        
				return items_grid;
			      
			}catch (Exception sqlException) {
				if (null != session.getTransaction()) {
					System.out.println("Transaction Is Being Rolled Backed");
					session.getTransaction().rollback();
					
				}
				sqlException.printStackTrace();
			} finally {
				session.close();
			}

	     
			return items_grid;
	}
	
	/**
	 * siva
	 * returns a Map ==> BOM_ID,BillAmountID,TransactionID,InvoiceID
	 */
    public Map<String,Long> getUniqueBillIDNumber(HttpSession httpSession){
    	 
    	Map<String,Long> uniqueBillIDsMap = new LinkedHashMap<String,Long>();
      
		Long BOM_ID=0L;
		try {
		    BOM_ID = billsRandFuncts.generateUniqueToken(httpSession,"BOM_ID",Constants.BOM_ID_LENGTH);
		}catch(Exception e) {
			System.out.println("problem while setting BOM_ID...........");
			e.printStackTrace();
		}
		
		
		Long BillAmountID=0L;
		try {
			BillAmountID = billsRandFuncts.generateUniqueToken(httpSession,"BillAmountID",Constants.BillAmountID_LENGTH);
		}catch(Exception e) {
			System.out.println("problem while setting BillAmountID...........");
			e.printStackTrace();
		}
		
		Long TransactionID = 0L;
		try {
			Long TransactionID_dup = billsRandFuncts.generateUniqueToken(httpSession,"TransactionID",Constants.TransactionID_LENGTH);
			String local_TransactionID = String.valueOf(TransactionID_dup)+ erpFunObj.getTimeStamp();
			TransactionID = Long.valueOf(local_TransactionID);
		}catch(Exception e) {
			System.out.println("problem while setting TransactionID...........");
			e.printStackTrace();
		}
		
		Long InvoiceID = 0L;
		try {
			InvoiceID = billsRandFuncts.generateUniqueToken(httpSession,"InvoiceID",Constants.TransactionID_LENGTH);
		}catch(Exception e) {
			System.out.println("problem while setting TransactionID...........");
			e.printStackTrace();
		}
	    
		uniqueBillIDsMap.put("BOM_ID", BOM_ID);
		uniqueBillIDsMap.put("BillAmountID", BillAmountID);
		uniqueBillIDsMap.put("TransactionID", TransactionID);
		uniqueBillIDsMap.put("InvoiceID", InvoiceID);
		
    	
    	return uniqueBillIDsMap;
    }
	
   
    /**
     * 
     * @param uuid_bills
     * @param outlet_uuids
     * @param user_uuid
     * @param uniqueBillIDsMap
     * @return An pos_outlet_bill entity object
     */
    public List<Pos_Outlet_Bills> setDataInBills(String uuid_bills,String outlet_uuids,String user_uuid,Map<String,Long> uniqueBillIDsMap,String billtype) {
    	
    	List<Pos_Outlet_Bills> billsObjsList = new ArrayList<Pos_Outlet_Bills>();
    	try {
    		Long BOM_ID=0L;
    		Long BillAmountID=0L;
    		Long TransactionID = 0L;
    		Long InvoiceID = 0L;
    		Long BillNo = 0L;
    		if(uniqueBillIDsMap.size() > 0) {
    			BOM_ID = uniqueBillIDsMap.get("BOM_ID");
    			BillAmountID = uniqueBillIDsMap.get("BillAmountID");
    			TransactionID = uniqueBillIDsMap.get("TransactionID");
    			InvoiceID = uniqueBillIDsMap.get("InvoiceID");
    			BillNo = uniqueBillIDsMap.get("BillNo");
    			
    			Pos_Outlet_Bills  tableBillsObj = new Pos_Outlet_Bills();
    			tableBillsObj.setUuid(uuid_bills);
    			tableBillsObj.setOutlet_uuid(outlet_uuids);
    			tableBillsObj.setBillno(BillNo);
    			tableBillsObj.setBOM_ID(BOM_ID);
    			tableBillsObj.setBillAmountID(BillAmountID);
    			tableBillsObj.setTransactionID(TransactionID);
    			tableBillsObj.setBill_type(billtype);
    			tableBillsObj.setInvoiceID(InvoiceID);
    			tableBillsObj.setCreated_date(new Date());
    			tableBillsObj.setCreated_by(user_uuid);
    		  //tableBillsObj.setStatus(Pos_Outlet_Bills_Status.Active);
    			
    			billsObjsList.add(tableBillsObj);
    		}
    	}catch(Exception e) {
    		System.out.println("Problem to set the pos_outlet_bills data in setDataInBills()");
    		e.printStackTrace();
    	}
    	
    	return billsObjsList;
    }
    
    public List<Pos_Outlet_Bom_Amounts> setDataInAmounts(String uuid_bills,String outlet_uuids,String user_uuid,Map<String,Long> uniqueBillIDsMap,Map<String,Double> detailsToSetInAmountsMap,JsonObject selectedItemstotObj) {
    	
    	List<Pos_Outlet_Bom_Amounts> AmountsObjsList = new ArrayList<Pos_Outlet_Bom_Amounts>();
    	
    	try {
    		String outlet_uuid = String.valueOf(Integer.valueOf(outlet_uuids)+10);
			String uuid_bom_amounts = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
			
			Pos_Outlet_Bom_Amounts bomAmountsObj = new Pos_Outlet_Bom_Amounts();
			
			Long BOM_ID = uniqueBillIDsMap.get("BOM_ID");
			Long BillAmountID = uniqueBillIDsMap.get("BillAmountID");
			Long TransactionID = uniqueBillIDsMap.get("TransactionID");
			Long InvoiceID = uniqueBillIDsMap.get("InvoiceID");
			Long BillNo = uniqueBillIDsMap.get("BillNo");
			
			Double tot_price_bd = detailsToSetInAmountsMap.get("tot_price_bd");
			Double item_dis = detailsToSetInAmountsMap.get("item_dis");
			Double ovrl_bill_dis_perc = detailsToSetInAmountsMap.get("ovrl_bill_dis_perc");
			Double ovrl_bill_dis_amt = detailsToSetInAmountsMap.get("ovrl_bill_dis_amt");
			Double total_dis = detailsToSetInAmountsMap.get("total_dis");
			Double bill_amt = detailsToSetInAmountsMap.get("bill_amt");
			
			bomAmountsObj.setUuid(uuid_bom_amounts);
			bomAmountsObj.setOutlet_uuid(outlet_uuids);
			bomAmountsObj.setBillno(BillNo);
			bomAmountsObj.setBillAmountID(BOM_ID);
			bomAmountsObj.setTotal_items(selectedItemstotObj.get("qty_val").getAsInt());
			bomAmountsObj.setTotal_price_bd(tot_price_bd);
			bomAmountsObj.setItems_discount_amt(item_dis);
			bomAmountsObj.setBill_discount_perc(ovrl_bill_dis_perc);
			bomAmountsObj.setBill_discount_amt(ovrl_bill_dis_amt);
			bomAmountsObj.setTotal_discount_amt(total_dis);
		  	bomAmountsObj.setTotal_price_bg(selectedItemstotObj.get("tot_before_gst").getAsDouble());
		  	bomAmountsObj.setTotal_gst_amt(selectedItemstotObj.get("tot_gst_amout").getAsDouble());
		  	bomAmountsObj.setTotal_cgst_amt(selectedItemstotObj.get("tot_cgst_amout").getAsDouble());
		  	bomAmountsObj.setTotal_sgst_amt(selectedItemstotObj.get("tot_sgst_amout").getAsDouble());
		  	bomAmountsObj.setTotal_igst_amt(0d);
		  	bomAmountsObj.setTotal_price(bill_amt);
		  	bomAmountsObj.setCreated_date(new Date());
		  	bomAmountsObj.setCreated_by(user_uuid);
		  	bomAmountsObj.setUpdated_date(new Date());
		  	//bomAmountsObj.setStatus(status);
			bomAmountsObj.setBills_uuid(uuid_bills);
			
			AmountsObjsList.add(bomAmountsObj);
			
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return AmountsObjsList;
    } //end of setDataInAmounts
    
    
    public List<Pos_Outlet_Bom_Transaction> setDataInTransaction(String uuid_bills,String outlet_uuids,String user_uuid,Map<String,Long> uniqueBillIDsMap,Map<String,Double> detailsToSetInAmountsMap,JsonObject paymentModeArrayObj,String trans_type) {
        
        List<Pos_Outlet_Bom_Transaction> bomtransObjList = new ArrayList<Pos_Outlet_Bom_Transaction>();	
        
        Long BOM_ID = uniqueBillIDsMap.get("BOM_ID");
		Long BillAmountID = uniqueBillIDsMap.get("BillAmountID");
		Long TransactionID = uniqueBillIDsMap.get("TransactionID");
		Long InvoiceID = uniqueBillIDsMap.get("InvoiceID");
		Long BillNo = uniqueBillIDsMap.get("BillNo");
        
		
		Double tot_price_bd = detailsToSetInAmountsMap.get("tot_price_bd");
		Double item_dis = detailsToSetInAmountsMap.get("item_dis");
		Double ovrl_bill_dis_perc = detailsToSetInAmountsMap.get("ovrl_bill_dis_perc");
		Double ovrl_bill_dis_amt = detailsToSetInAmountsMap.get("ovrl_bill_dis_amt");
		Double total_dis = detailsToSetInAmountsMap.get("total_dis");
		Double bill_amt = detailsToSetInAmountsMap.get("bill_amt");
		
		
        Set<?> set =  paymentModeArrayObj.keySet();
        Iterator<?> iterator = set.iterator();
        do{
            String pays_key = iterator.next().toString();
            System.out.println("key is"+pays_key);
            JsonObject paymentRowData = paymentModeArrayObj.get(pays_key).getAsJsonObject();
            System.out.println("value is "+paymentRowData.get("amount"));
            
            double payment_amt = 0d;
            try {
            payment_amt = paymentRowData.get("amount").getAsDouble();
            }catch(Exception e) {
            	e.printStackTrace();
            }
            
            String transactionType =null;
            String card_type = null;
            		
    		Pos_Outlet_Bom_Transaction bomTransObj = new Pos_Outlet_Bom_Transaction();
    		
    		String bomTrans_uuid = outlet_uuids+erpFunObj.uniqueRandAndTimeStamp();
    		bomTransObj.setUuid(bomTrans_uuid);
    		bomTransObj.setOutlet_uuid(outlet_uuids);
    		bomTransObj.setBillno(BillNo);
    		bomTransObj.setBillAmountID(BillAmountID);
    		bomTransObj.setTransactionID(TransactionID);
    		//bomTransObj.setTransaction_type(transactionType);
    		//bomTransObj.setAmount(selectedItemsAmount);
    		bomTransObj.setCreated_date(new Date());
    		bomTransObj.setCreated_by(user_uuid);
    		//bomTransObj.setStatusTrans(Bom_Trans_Status.Active);
    		bomTransObj.setUpdated_date(new Date());
    		bomTransObj.setUpdated_by(user_uuid);
    	 	//bomTransObj.setOrder_type(order_type);
    		bomTransObj.setBills_uuid(uuid_bills);
    		
    		
       switch(pays_key) {
     	 case "PRICE":
     	 case "DISCOUNT":
         case "QUANTITY":
         case "Cash":
         	if (payment_amt > bill_amt)
         		bomTransObj.setAmount(bill_amt);
             else
             	bomTransObj.setAmount(payment_amt);
             transactionType = "Cash";
             bomTransObj.setTransaction_type(transactionType);
             bomTransObj.setOrder_type(trans_type);
     		break;
         case "Card":
         	bomTransObj.setAmount(payment_amt);
         	transactionType = "Card";
             bomTransObj.setTransaction_type(transactionType);
             bomTransObj.setOrder_type(trans_type);
             card_type = paymentRowData.get("card_type").getAsString();
         	bomTransObj.setCard_type(card_type);
     		break;
         case "Voucher":	
         	bomTransObj.setAmount(payment_amt);
         	transactionType = "Voucher";
             bomTransObj.setTransaction_type(transactionType);
             bomTransObj.setOrder_type(trans_type);
             break;
         case "EWallet":	
         	bomTransObj.setAmount(payment_amt);
         	transactionType = "EWallet";
             bomTransObj.setTransaction_type(transactionType);
             bomTransObj.setOrder_type(trans_type);
             card_type = paymentRowData.get("card_type").getAsString();
         	bomTransObj.setCard_type(card_type);
             break;
        }
           bomtransObjList.add(bomTransObj);
    	
    		System.out.println("***************************");
    		System.out.println(bomTransObj.toString());
    		System.out.println("***************************");

        }while(iterator.hasNext());

        
    	return bomtransObjList;    
    }
    
    
    public List<Pos_Outlet_Bom_Invoice> setDataInInvoice(String uuid_bills,String outlet_uuids,String user_uuid,Map<String,Long> uniqueBillIDsMap,Map<String,Double> detailsToSetInAmountsMap,JsonObject selectedItemstotObj){
    	
    	List<Pos_Outlet_Bom_Invoice> bomInvoiceObjList = new ArrayList<Pos_Outlet_Bom_Invoice>();
    	
        Long BOM_ID = uniqueBillIDsMap.get("BOM_ID");
		Long BillAmountID = uniqueBillIDsMap.get("BillAmountID");
		Long TransactionID = uniqueBillIDsMap.get("TransactionID");
		Long InvoiceID = uniqueBillIDsMap.get("InvoiceID");
		Long BillNo = uniqueBillIDsMap.get("BillNo");
        
		Double bill_amt = detailsToSetInAmountsMap.get("bill_amt");

    	
    	    double balance_amt = 0d;
    	    try {
	        balance_amt = selectedItemstotObj.get("remaining_amount").getAsDouble();
    	    }catch(Exception e) {
    	    	e.printStackTrace();
    	    }
	        
	        double paid_amt = 0d;
	        try {
	        paid_amt = bill_amt - balance_amt;
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	        String uuid_invoice = outlet_uuids+erpFunObj.uniqueRandAndTimeStamp();
			Pos_Outlet_Bom_Invoice  tableInvoiceObj = new Pos_Outlet_Bom_Invoice();
			
			/*if(returnAmount!=0) {
				tableInvoiceObj.setOldbillnumber(old_bill_number);
			} */ 
				
			tableInvoiceObj.setUuid(uuid_invoice);
			tableInvoiceObj.setOutlet_uuid(outlet_uuids);
			tableInvoiceObj.setBillno(BillNo);
			tableInvoiceObj.setTransactionID(TransactionID);
			tableInvoiceObj.setInvoiceID(InvoiceID);
			tableInvoiceObj.setCreated_date(new Date());
			tableInvoiceObj.setCreated_by(user_uuid);
			tableInvoiceObj.setBill_amt(bill_amt);
			tableInvoiceObj.setPaid_amt(paid_amt);
			tableInvoiceObj.setBalance_amt(balance_amt);
			tableInvoiceObj.setBills_uuid(uuid_bills);
			tableInvoiceObj.setUpdated_date(new Date());
			tableInvoiceObj.setUpdated_by(user_uuid);
				
			bomInvoiceObjList.add(tableInvoiceObj);
				
			return bomInvoiceObjList;
    
    } //end of setDataInInvoice
    
    @Override
	public String generateCreditNote(HttpServletRequest request, HttpSession httpSession) {
		System.out.println("in service implimentation");
		String returnID_data = request.getParameter("retunID_value");
		String creditnoteid_value = request.getParameter("creditnoteid_value");
		String outlet_uuid = (String) httpSession.getAttribute("outlet_uuid");
		 System.out.println("checking outlet uuid"+outlet_uuid);
		 String creditreturn = "";
		 String uuidInCond="";
			 uuidInCond = returnID_data.substring(1, returnID_data.length()-1);
			//replacing " with '
			 uuidInCond = uuidInCond.replaceAll("\"", "'");   
			 System.out.println(uuidInCond);
			 try {
					 creditreturn = ordersDao.getCreditNoteAmount(request,uuidInCond,creditnoteid_value,httpSession);
				}catch(Exception e) {
					e.printStackTrace();
				}
		
		return creditreturn;
	}
	

}

package com.ramersoft.pos.ui.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.text.DecimalFormat;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ramersoft.pos.entities.Pos_Outlet_Bills;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Amounts;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Invoice;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Items;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Transaction;
import com.ramersoft.pos.entities.Pos_Outlet_PriceChange;
import com.ramersoft.pos.enums.Bom_Amounts_Status;
import com.ramersoft.pos.enums.Bom_Invoice_Status;
import com.ramersoft.pos.enums.Bom_Items_Status;
import com.ramersoft.pos.enums.Bom_Trans_Status;
import com.ramersoft.pos.enums.Pos_Outlet_Bills_Status;
import com.ramersoft.pos.enums.Pos_outlet_billpark_items_status;
import com.ramersoft.pos.ui.beans.Companies_Outlet_Bean;
import com.ramersoft.pos.ui.beans.ItemsBean;
import com.ramersoft.pos.ui.dao.OrdersDaoImpl;
import com.ramersoft.pos.ui.libs.BillsRandomGenerations;
import com.ramersoft.pos.ui.libs.Constants;
import com.ramersoft.pos.ui.libs.ERPFunctions;

@Service
public class BillsServiceImpl implements BillsService{
	
	    @Autowired
        BillsRandomGenerations billsRandFuncts;

	    @Autowired
		ERPFunctions erpFunObj;
	    
	    @Autowired
	    OrdersDaoImpl orderDaoObj;
	    
	    @Autowired
	    BillsCommonFnsService commonMethodsObj;

	    @Resource(name="sessionFactory")
		private SessionFactory sessionFactory;
		
		Transaction tx;

		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
	    
	   /********  Cahbill  *********/
	    @Override
		public String generateBillsData(HttpServletRequest request, HttpSession httpSession) {
			String params_data = request.getParameter("data_array"); 
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
			
			JsonParser parser = new JsonParser();
			JsonElement jsonTree = parser.parse(params_data);
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			
			String uuid_bills = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
			
			/**********vouchers code********************/
			String voucherid = null;
			String vouchertype = null;
			Pos_Outlet_Bom_Transaction bomVoucherObj = new Pos_Outlet_Bom_Transaction();
			try {
				voucherid = jsonObject.get("voucherid").getAsString();
				if(voucherid!=null) {
					int voucher_discount = jsonObject.get("voucher_discount").getAsInt();
					String uuid_vouchers = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
					
					bomVoucherObj.setUuid(uuid_vouchers);
					bomVoucherObj.setOutlet_uuid(outlet_uuids);
					bomVoucherObj.setBillno(BillNo);
					bomVoucherObj.setBillAmountID(BillAmountID);
					bomVoucherObj.setTransactionID(TransactionID);
					bomVoucherObj.setCreated_date(new Date());
					bomVoucherObj.setCreated_by(user_uuid);
					bomVoucherObj.setUpdated_date(new Date());
					bomVoucherObj.setUpdated_by(user_uuid);
					//bomVoucherObj.setStatusTrans(Bom_Trans_Status.Active);
					bomVoucherObj.setVoucherID(voucherid);
					bomVoucherObj.setAmount(voucher_discount);
					bomVoucherObj.setTransaction_type("Voucher");
					bomVoucherObj.setBills_uuid(uuid_bills);
					
					System.out.println("\n************************");
					System.out.println(bomVoucherObj.toString()+"\n");
					System.out.println("************************\n");
					
					vouchertype = jsonObject.get("vouchertype").getAsString();
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			/*******************************************/

			JsonElement selectedItemArray = jsonObject.get("selectedItemArray");
			JsonObject selectedItemArrayObj = selectedItemArray.getAsJsonObject();
	        System.out.println("This is the selectedItemArray:");
	        System.out.println(selectedItemArrayObj.toString());
			
			/*=====================================*/
	        
	        String trans_type = jsonObject.get("trans_type").getAsString();
	        System.out.println("trans____type  is"+trans_type);
	        String billtype = jsonObject.get("bill_type").getAsString();
	        System.out.println("billtype  is"+billtype);
	        
	        /*******************return amount code**********************/
	        double returnAmount = 0d;
	        long old_bill_number = 0l;
            try {
                
                //to update the return items status as returned
                try {            
                   
                    try {
                        if(jsonObject.has("ReturnAmount"))
                              returnAmount = jsonObject.get("ReturnAmount").getAsDouble();    
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                    
                    String ReturnAmount_autoids = "";
                    try {
                        if(jsonObject.has("ReturnAmount_autoids"))
                            ReturnAmount_autoids = jsonObject.get("ReturnAmount_autoids").getAsString();    
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                    
                    if(!ReturnAmount_autoids.equals("null")) {
                        Session session = null;
                         try {
                                session = sessionFactory.openSession();
                                String hql = "update pos_outlet_bom_items set status =:status,outlet_uuid =:outlet_uuid,updated_date =:up_date,updated_by=:up_by    where uuid in("+ReturnAmount_autoids+")";                            
                                Query query = session.createQuery(hql);
                                query.setParameter("outlet_uuid", outlet_uuids);
                                query.setParameter("status", Bom_Items_Status.Returned);
                                query.setParameter("up_date", new Date());
                                query.setParameter("up_by",user_uuid);
                                int result = query.executeUpdate();
                                System.out.println("**********return item records updated successfully in pos_outlet_bom_tems***********");
                                
                                try {
                                	old_bill_number = orderDaoObj.getReturnOrderItemsBillNo( ReturnAmount_autoids,  outlet_uuids);
                                	System.out.println("********old_bill_number**************:"+old_bill_number+"*********");
                                	
                                }catch(Exception e) {
                                	System.out.println("There is a problem to get billno from pos_outlet_bom_items");
                                	 e.printStackTrace();
                                }
                                
                            } catch (Exception e) {
                                System.out.println("There is a problem to update the status of return items in pos_outlet_bom_items");
                                e.printStackTrace();
                            } finally {
                                if (session != null) {
                                    session.close();
                                }
                            }
                   }                
                }catch(Exception e) {
                    e.printStackTrace();
                }
                
                
                //to update the credit_note details......if any
                try {                    
                    
                    long creditautoid = 0L;
                    try {
                        if(jsonObject.has("random_credit"))
                            creditautoid = jsonObject.get("random_credit").getAsLong();    
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    
                    if(creditautoid > 0) {                        
                        Session session = null;
                         try {
                                session = sessionFactory.openSession();
                                String hql = "update pos_outlet_creditnotedetails set status =:status,usedbill =:billno,updated_date =:up_date,updated_by=:up_by where ";
                                Query query = session.createQuery(hql);
                                query.setParameter("billno", BillNo);
                                query.setParameter("status", "InActive");
                                query.setParameter("up_date", new Date());
                                query.setParameter("up_by",user_uuid);
                                int result = query.executeUpdate();
                                System.out.println("**********records updated successfully in pos_outlet_creditnotedetails***********");
                            } catch (Exception e) {
                                System.out.println("Exception occured with credit note details status updation(There might be no records)");
                                //e.printStackTrace();
                            } finally {
                                if (session != null) {
                                    session.close();
                                }
                            }                        
                    }
                    
                
                }catch(Exception e){
                    e.printStackTrace();
                }

            }catch(Exception e) {
                e.printStackTrace();
            }
   
            /******************************************************/
	        
			Pos_Outlet_Bills  tableBillsObj = new Pos_Outlet_Bills();
			tableBillsObj.setUuid(uuid_bills);
			tableBillsObj.setOutlet_uuid(outlet_uuids);
			tableBillsObj.setBill_type(billtype);
			tableBillsObj.setBillno(BillNo);
			tableBillsObj.setBOM_ID(BOM_ID);
			tableBillsObj.setBillAmountID(BillAmountID);
			tableBillsObj.setTransactionID(TransactionID);
			tableBillsObj.setInvoiceID(InvoiceID);
			tableBillsObj.setCreated_date(new Date());
			tableBillsObj.setCreated_by(user_uuid);
			//tableBillsObj.setStatus(Pos_Outlet_Bills_Status.Active);
			
			System.out.println("the obj is"+tableBillsObj.toString());
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
		  	
			//to store the price changes
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
		        	System.out.println("dis perc"+i_adt_dis_perc); //******output 0.0*******
		        	System.out.println("amt perc"+i_adt_dis_amt); //******output 0.0********
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
		        	price_bd = (double)(original_price * itemsRowData.get("qty").getAsDouble()); 
		        	price_fordiscount = (double)itemsRowData.get("price").getAsDouble() * itemsRowData.get("qty").getAsDouble();
		        	
		        	price_bg = (price_fordiscount)-(discount_amt + i_adt_dis_amt);
		        	original_discount_perc = itemsRowData.get("original_discount").getAsJsonObject().get("percentage").getAsDouble();
		        	discount_perc = itemsRowData.get("discount").getAsJsonObject().get("percentage").getAsDouble();
		        	adt_discount_perc = itemsRowData.get("additional_discount").getAsJsonObject().get("percentage").getAsDouble();
		        	adt_discount_amt =  itemsRowData.get("additional_discount").getAsJsonObject().get("amount").getAsDouble();
		        }catch(Exception e) {
		        	e.printStackTrace();
		        }
		        
		        double priceForPortion = 0d;
		        try {
		        	priceForPortion = (double)(itemsRowData.get("price").getAsDouble());
		        }catch(Exception e) {
		        	
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
		        
		        System.out.println("*********the bills uuid is ********"+uuid_bills+"----------------");
		        
		        
		        
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
		        bomItemsObj.setStatus(Bom_Items_Status.Active);
		        
		        bomItemsObjList.add(bomItemsObj);//***adding the items object to the items list
		        
		        System.out.println("***************************");
	    		System.out.println(bomItemsObj.toString());
	    		System.out.println("***************************");
	    		
	    		
	    		
	    		 /*---------------------------------------------*/
			       //adding price changes conditions
		 	       double previous_price = 0d;
		 	        try {
		 	        	previous_price = orderDaoObj.getPreviousPriceForThisItemPortion(outlet_uuids,item_uuid,key);
		 	        }catch(Exception e) {
		 	        	System.out.println("price change history not found");
		 	        }
		 	        
		 	        System.out.println("previous_price for this portion is:"+Math.round(previous_price)+" and "+"price for portion is:::"+Math.round(priceForPortion));
		 	           
		 	      /*Pos_Outlet_PriceChange priceChangesObjs = new Pos_Outlet_PriceChange();
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
		    		/*---------------------------------------------*/
		    	
		        
		    }while(i.hasNext());
			//end of do-while
		  	
		  	
		  //priceChanges
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
		  	bomAmountsObj.setStatus(Bom_Amounts_Status.Active);
			bomAmountsObj.setBills_uuid(uuid_bills);
			System.out.println("***************************");
    		System.out.println(bomAmountsObj.toString());
    		System.out.println("***************************");
			
			/*****For bom transaction****
			 * 
			 */
			JsonElement paymentModeArray = jsonObject.get("payment_mode_ary");
			JsonObject paymentModeArrayObj = paymentModeArray.getAsJsonObject();
	        System.out.println("This is the payment_mode_ary:");
	        System.out.println(paymentModeArrayObj.toString());
	        
	        List<Pos_Outlet_Bom_Transaction> bomtransObjList = new ArrayList<Pos_Outlet_Bom_Transaction>();	
	        Set<?> set =  paymentModeArrayObj.keySet();

	        Iterator<?> iterator = set.iterator();
	        do{
	            String pays_key = iterator.next().toString();
	            System.out.println("key is"+pays_key);
	            JsonObject paymentRowData = paymentModeArrayObj.get(pays_key).getAsJsonObject();
	            System.out.println("value is "+paymentRowData.get("amount"));
	            
	            double payment_amt = 0d;
	            payment_amt = paymentRowData.get("amount").getAsDouble();
	            String transactionType =null;
	            String card_type = null;
	            		
	    		Pos_Outlet_Bom_Transaction bomTransObj = new Pos_Outlet_Bom_Transaction();
	    		
	    		String bomTrans_uuid = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
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
	        
	        /**** For bom invoice*
	         * 
	         */
	       
	        double balance_amt = 0d;
	        balance_amt = selectedItemstotObj.get("remaining_amount").getAsDouble();
	        
	        double paid_amt = 0d;
	        paid_amt = bill_amt - balance_amt;
	        
	        
	        String uuid_invoice = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
			Pos_Outlet_Bom_Invoice  tableInvoiceObj = new Pos_Outlet_Bom_Invoice();
			
			if(returnAmount!=0) {
				tableInvoiceObj.setOldbillnumber(old_bill_number);
			}
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
				//tableInvoiceObj.setStatus(Bom_Invoice_Status.Active);
				
			System.out.println("the obj is"+tableInvoiceObj);
			System.out.println("*****************************************");
			
			session=null;
			String items_grid = "";
			String linux_grid = "";
			try {
				session=sessionFactory.openSession();
			    session.beginTransaction();
			    
			    session.save(tableBillsObj);
				
			    for (Pos_Outlet_Bom_Items bomItemObj : bomItemsObjList){
				      session.save(bomItemObj);
				}
			    
			  //priceChanges
			    for (Pos_Outlet_PriceChange priceChangeObj : priceChangesObjList){
				      session.save(priceChangeObj);
				}
			    
			    session.save(bomAmountsObj);
			    
			    for (Pos_Outlet_Bom_Transaction bomObj : bomtransObjList){
				      session.save(bomObj);
				}
			    
			    session.save(tableInvoiceObj);
			    
			    if(voucherid!=null) {
			    	session.save(bomVoucherObj);
			    	if(vouchertype.equals("one_time")) {
						String Query = "UPDATE pos_vouchers set used_status =:used_status, updated_date =:updated_date, updated_by =:updated_by where uuid =:voucherid";
						Query query = session.createQuery(Query);
						query.setParameter("used_status", "used");
						query.setTimestamp("updated_date", new Date());
						query.setParameter("updated_by", user_uuid);
						query.setParameter("voucherid", voucherid);
						
						int result = query.executeUpdate();
						System.out.println("result is "+result);
					}
			    }
			    
		        session.getTransaction().commit();
		        System.out.println("**********************Success fully Commited**********************");
		        
		        
			    System.out.println("*************Designing the bill********************");
			    String billno = String.valueOf(BillNo);
		        String year=Constants.year;
				String commonInBill = year+outlet_uuid;
				long presentBillNo = Long.valueOf(billno.replace(commonInBill,""));
				
			   /*****designing the company details********/
				String getCompanyDetails = commonMethodsObj.companyDetails(outlet_uuids);
				String[] compSplit = getCompanyDetails.split("@");
				
				
				/**********for windows***************/
				items_grid = compSplit[0];	
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
			    
			    linux_grid = compSplit[1];	
			    linux_grid += Constants.center+"\u001b!\u0008"+"TAX INVOICE"+"\u001b!\u0000"+"\n";  // Bill name
			    linux_grid += "------------------------------------------\n";
			    linux_grid += Constants.left+"\u001b!\u0008"+new Date()+"\u001b!\u0000"+"\t";
			    linux_grid += "\u001b!\u0008"+"INVOICE NO"+"\u001b!\u0000"+"\n";
			    linux_grid += Constants.left+"\u001b!\u0008"+"Cashier:"+"\u001b!\u0000";
			    linux_grid += httpSession.getAttribute("UserName")+"\t\t ";
			    linux_grid += "\u001b!\u0008"+presentBillNo+"\u001b!\u0000"+"\n";
			    
			    
			    /*********designing items and token*******/
			    int token_flag = 0;
		    	String token_grid = null;
		    	String linux_token_grid = null;
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
		       
		       int totalTransactionsCount = 0;
		       try {
		        	 totalTransactionsCount = bomtransObjList.size();
		       }catch(Exception e) {
		             e.printStackTrace();
		       }
		       
		     for(int i2=0;i2<totalTransactionsCount;i2++) {
		       Pos_Outlet_Bom_Transaction bomtrans = (Pos_Outlet_Bom_Transaction) bomtransObjList.get(i2);
		    	   if (bomtrans.getCard_type()!=null){
		                items_grid += "<tr><td style=\"text-align:left;\"><strong>"+bomtrans.getTransaction_type()+"("+bomtrans.getCard_type()+")</strong> </td><td><label>Rs</label> <strong>"+bomtrans.getAmount()+"/-</strong></td></tr>\n";
		                String payment_type=bomtrans.getTransaction_type()+"("+bomtrans.getCard_type()+")";
		                
		                /******for linux*********/
		                String transactiontype = bomtrans.getTransaction_type()+bomtrans.getCard_type();
		                if(transactiontype.length()>=15) {
		                	linux_grid += "\u001b!\u0008"+bomtrans.getTransaction_type()+"("+bomtrans.getCard_type()+")"+"\u001b!\u0000"+"\t\t";
			                linux_grid += "\u001b!\u0008"+"Rs "+bomtrans.getAmount()+"\u001b!\u0000"+"/-\n";
		                }else {
		                	linux_grid += "\u001b!\u0008"+bomtrans.getTransaction_type()+"("+bomtrans.getCard_type()+")"+"\u001b!\u0000"+"\t\t\t";
			                linux_grid += "\u001b!\u0008"+"Rs "+bomtrans.getAmount()+"\u001b!\u0000"+"/-\n";
		                }
		                
		                
		            }
		            else{
		                items_grid += "<tr><td style=\"text-align:left;\"><strong>"+bomtrans.getTransaction_type()+"</strong> </td><td><label>Rs</label> <strong>"+bomtrans.getAmount()+"/-</strong></td></tr>\n";
		                
		                /******for linux*********/
		                linux_grid += "\u001b!\u0008"+bomtrans.getTransaction_type()+"\u001b!\u0000"+"\t\t\t\t";
		                linux_grid += Constants.right+"\u001b!\u0008"+"Rs "+bomtrans.getAmount()+"\u001b!\u0000"+"/-\n";
		            
		            }
		       }
		       
		       items_grid += "<tr><td  style=\"text-align:left;\">\n";
		       double balance_amtcheck = tableInvoiceObj.getBalance_amt();
		       double bal_amt = balance_amtcheck;
		       String table_viewstatus = "";
		       String linux_table_viewstatus = "";
		       if (Math.round(balance_amtcheck) != 0 && Math.round(balance_amtcheck) > 0) {
		            bal_amt = balance_amtcheck;
		            items_grid += "<strong>Balance Amount:</strong>";
		            items_grid += "</td><td><label style=\"font-weight:normal;\">Rs</label> <strong>"+Math.abs(bal_amt)+"/-</strong></td></tr>\n";
		            table_viewstatus = "<table  border=\"1\" width=\"100%\"><tr><td align=\"center\"  style=\"font-weight:bold;\">Status</td>\n"
		            				 + "<td align=\"center\" style=\"font-weight:bold;\">Not Delivered</td></tr></table>\n";
		            
		            
		            /**********for linux******/
	                linux_grid += "\u001b!\u0008"+"Balance Amount:"+"\u001b!\u0000"+"\t\t  ";
	                linux_grid += "\u001b!\u0008"+"Rs "+Math.abs(bal_amt)+"\u001b!\u0000"+"/- \n";
	                
	                linux_table_viewstatus += "\u001b!\u0008"+"Status"+"\u001b!\u0000"+"\t\t";
	                linux_table_viewstatus += "\u001b!\u0008"+"Not Delivered"+"\u001b!\u0000"+"\n";
	                
		        } 
		       else if(Math.round(balance_amtcheck) != 0 && Math.round(balance_amtcheck) < 0){
					items_grid += "<tr><td  style=\"text-align:left;\"><strong>Balance Amount:</strong></td>\n";
		            items_grid += "<td><label style=\"font-weight:normal;\">Rs</label> <strong> 0/-</strong></td></tr>\n";
			        items_grid +="<tr><td  style=\"text-align:left;\"><strong>Return Change Amount:</strong></td>\n";
		            items_grid += "<td><label style=\"font-weight:normal;\">Rs</label> <strong>"+Math.abs(bal_amt)+"/-</strong></td></tr>\n"; 
		            
		            /**********for linux******/
	                linux_grid += "\u001b!\u0008"+"Balance Amount:"+"\u001b!\u0000"+"\t\t  ";
	                linux_grid += "\u001b!\u0008"+"Rs 0"+"\u001b!\u0000"+"/- \n";
	                linux_grid += "\u001b!\u0008"+"Return Change Amount:"+"\u001b!\u0000"+"\t\t";
	                linux_grid += "\u001b!\u0008"+"Rs "+Math.abs(bal_amt)+"\u001b!\u0000"+"/- \n";
	                
		        }
		       else {
		            items_grid += "<tr><td  style=\"text-align:left;\"><strong>Balance Amount:</strong></td>\n";
		            items_grid += "<td><label style=\"font-weight:normal;\">Rs</label> <strong>"+Math.abs(bal_amt)+"/-</strong></td></tr>\n";
		            table_viewstatus = "<table  border=\"1\" width=\"100%\"><tr><td align=\"center\" style=\"font-weight:bold;\">Status</td>\n"
		            		         + "<td align=\"center\" style=\"font-weight:bold;\">Delivered</td></tr>\n";
		            
		            /**********for linux******/
	                linux_grid += "\u001b!\u0008"+"Balance Amount:"+"\u001b!\u0000"+"\t\t  ";
	                linux_grid += "\u001b!\u0008"+"Rs "+Math.abs(bal_amt)+"\u001b!\u0000"+"/- \n";
	                
	                linux_table_viewstatus += "\u001b!\u0008"+"Status"+"\u001b!\u0000"+"\t\t";
	                linux_table_viewstatus += "\u001b!\u0008"+"Delivered"+"\u001b!\u0000"+"\n";
		       }
		       
		      items_grid += "</table><span>----------------------------------------------------------------------------</span>\n"
		      			 + "<div>"+table_viewstatus+"</div>\n"
		      			 + "<div class=\"divTable\">\n"
		      			 + "<div class=\"divTableBody\">\n"
		      			 + "<div class=\"divTableRow\">\n"
		      			 + "<div class=\"\"><strong>GST SUMMARY</strong></div>\n"
		      			 + "<div class=\"divTableCell\"></div>\n"
		      			 + "<div class=\"divTableCell\"></div>\n"
		      			 + "<div class=\"divTableCell\"></div>\n"
		      			 + "</div></div></div>\n";
		      
		      /********for linux***********/
		      linux_grid += "------------------------------------------\n";
              linux_grid += linux_table_viewstatus+"\n";
              linux_grid += "------------------------------------------\n";
              linux_grid += "\u001b!\u0008"+"GST SUMMARY"+"\u001b!\u0000"+"\n";
		      
              int btobordresCount = 0;
              
              String gst_details = commonMethodsObj.gstDetails(bomItemsObjList, btobordresCount);
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
}

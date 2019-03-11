package com.ramersoft.pos.ui.service;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.io.PrintStream;
import java.math.BigInteger;
import java.text.AttributedString;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramersoft.pos.entities.Pos_Outlet_Bom_Items;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Transaction;
import com.ramersoft.pos.ui.beans.Companies_Outlet_Bean;
import com.ramersoft.pos.ui.beans.GstAndCgstBean;
import com.ramersoft.pos.ui.beans.ItemsBean;
import com.ramersoft.pos.ui.libs.Constants;
import com.ramersoft.pos.ui.libs.ERPFunctions;

@Service
public class BillsCommonFnsServiceImpl implements BillsCommonFnsService{
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	ERPFunctions erpFunObj;

	@Override
	public String companyDetails(String outlet_uuids) {
		
		String company_details = "";
		Session session=null;
		String details = "";
		
		try {
			session=sessionFactory.openSession();
			session.beginTransaction();
			
			/***query to get outlet data*/
			Query qry = session.createQuery("select ec.Company_name,eo.b_unit_name,eo.address,eo.ContactNo,eo.GSTtinnumber from erp_companies ec ,erp_outlets eo where eo.company_uuid=ec.uuid and eo.uuid =:OutletID");
	        qry.setParameter("OutletID", outlet_uuids);
	        System.out.println("qry result is"+qry.list());
	        List<Object[]> results = (List<Object[]>)qry.list();
	        Companies_Outlet_Bean company_outlet = new Companies_Outlet_Bean();
	        int totalRecords = 0;
	         try {
	            	totalRecords = results.size();
	         }catch(Exception e) {
	            	e.printStackTrace();
	         }
	     	String Company_name="";
	        for(Object[] result: results){	            	
	       	   Company_name = (String)result[0];	                  
	           String b_unit_name = (String)result[1];
	           String address = (String)result[2];
	           String ContactNo = (String)result[3];
	           String GSTtinnumber = (String)result[4];
	           System.out.println(Company_name+" ----- "+b_unit_name+" ------- "+address+" ------ "+ContactNo+" ----- "+GSTtinnumber);
	           company_outlet.setCompany_name(Company_name);
	           company_outlet.setB_unit_name(b_unit_name);
	           company_outlet.setAddress(address);
	           company_outlet.setContactNo(ContactNo);
	           company_outlet.setGSTtinnumber(GSTtinnumber);
	        }
	        	String[] company_name_split = Company_name.split("\\(",2);
	        	
	        
			   company_details = "<style>\n"+
						".container{font-family: sans-serif;height:100%;background: #FFF;font-size: 9.5pt;}\n"+
						".divTableRow1 {display: table-row;}\n"+
						".divTableHeading1 {background-color: #EEE;display: table-header-group;}\n"+
						".divTableCell1 {font-size: 14px;display: table-cell;padding: 3px 10px;}\n"+
						".divTableCell2{display: table-cell;}\n"+
						".divTableCell3{display: table-cell;}\n"+
						".divTableCell4{display: table-cell;}\n"+
						".divTableHead1 {font-size: 16px;display: table-cell;padding: 1px 11px;}\n"+	
						".divTable{col:40;}\n"+
						".divTableRow {display: table-row;}\n"+
						".divTableHeading {background-color: #EEE;display: table-header-group;}\n"+
						".divTableCell {font-size: 14px;display: table-cell;padding: 3px 10px;}\n"+
						".itemCell {font-size: 12px;width: 120px;font-weight:bold;padding: 3px 2px;}\n"+
						".divTableHead {font-size: 20px;display: table-cell;padding: 3px 3px;}\n"+
						".divTableHeading {background-color: #EEE;display: table-header-group;font-weight: bold;text-align:center;}\n"+
						".divTableFoot {background-color: #EEE;display: table-footer-group;font-weight: bold;}\n"+
						".divTableBody {display: table-row-group;}\n"+
						".lastright {padding-left: 72px;}\n"+
						".lastcolumn {padding-left: 10px;}\n"+	
						".price {padding-left: 0px;}\n"+
						".hsncode{font-size: 12px;}\n"+
						".header{col:80;text-align:center;}\n"+
						".header h2{text-decoration:underline;}\n"+
						".textright{padding-left: 106px;}\n"+
						".gstright{margin-left:156px;font-weight:bold;}\n"+
						".netright{margin-left: 156px;font-weight:bold;}\n"+
						".gstright1{margin-left: 103px;font-weight:bold;}\n"+
						".subtotal{padding-left: 77px;}\n"+
						".billtitle{font-size: 20px;FONT-WEIGHT:BOLD;}\n"+
						".cashright{padding-left: 156px;}\n"+
						".fottertitle{padding-left: 70px;}\n"+
						".divTableCellmain{font-size: 14px;text-align:center;}\n"+
						".Bighr{color:#000 !important;width: 40% !important;}\n"+
						"</style>\n"+
						"<div class=\"container\" style=\"font-size:16px;\">\n"+
						"<table border=\"0\" width=\"100%\">\n"+
						"<tr>\n"+
						"<td align=\"center\" valign=\"middle\"><lable  style=\"font-weight:bold;\">"+company_name_split[0]+"</lable><br /> "+company_outlet.getAddress()+"<br />Contact No : "+company_outlet.getContactNo()+" <br /> GSTIN : "+company_outlet.getGSTtinnumber()+" <br />";
		
			   
			 //************for printing the bill heading in centos**************//   
			    String print_str = "";
		        
		        print_str += Constants.center+"\u001b!\u0008" + company_name_split[0] + "\u001b!\u0000"+"\n";  // Company
		        print_str += Constants.center+ company_outlet.getAddress()+"\n";        //address
		        print_str += Constants.center+"Contact No:"+company_outlet.getContactNo()+"\n";    // phone_no
		        print_str += Constants.center+"GSTIN:"+company_outlet.getGSTtinnumber()+"\n";    // GSt no
		        
		       		        
		        details = company_details+"@"+print_str;
		        
		        
		
		}catch(Exception sqlException) {
				System.out.println("****company details are not coming**********");
				sqlException.printStackTrace();
			}finally {
				session.close();
			}
		   return details;
	}

	

	@Override
	public String ItemsDetails(List<Pos_Outlet_Bom_Items> bomItemsObjList, Long BillNo, String outlet_uuids, double returnAmount, int token_flag) {
		
		Session session=null;
		String items_details = null;
		String print_str = "";
		String print_token_str = "";
        String print_token_grid = "";

		
		try {
			session=sessionFactory.openSession();
			session.beginTransaction();
			
			/******for windows***********/
		    items_details = "<span>----------------------------------------------------------------------------</span>\n"+
								  "<table border=\"0\" width=\"100%\">\n"+
								  "<tr>\n"+
								  "<td style=\"font-size:19px;font-weight:bold;\">No&nbsp;</td>\n"+
								  "<td style=\"font-size:19px;font-weight:bold;\">Product</td>\n"+
								  "<td align=\"left\" style=\"font-size:19px;font-weight:bold;\">Qty</td>\n"+
								  "<td style=\"font-size:19px;font-weight:bold;\">Unit</td>\n"+
								  "<td style=\"font-size:19px;font-weight:bold;\">Pc.</td>\n"+
								  "<td style=\"font-size:19px;font-weight:bold;\">Amt.</td>\n"+
								  "</tr>\n"+
								  "<tr><td colspan=\"6\">----------------------------------------------------------------------------</td></tr>\n";
		    
		    /*********for linux********/
		    print_str +="------------------------------------------\n";
	        print_str +="\u001b!\u0008"+"No"+"\u001b!\u0000"+"   ";
	        print_str +="\u001b!\u0008"+"Product"+"\u001b!\u0000"+"\t";
	        print_str +="\u001b!\u0008"+"Qty"+"\u001b!\u0000"+"   ";
	        print_str +="\u001b!\u0008"+"Unit"+"\u001b!\u0000"+"  ";
	        print_str +="\u001b!\u0008"+"Pc."+"\u001b!\u0000"+"    ";
	        print_str +="\u001b!\u0008"+"Amt"+"\u001b!\u0000"+"\n";
	        print_str +="------------------------------------------\n";
		    
	        print_token_grid = "------------------------------------------\n";
	        print_token_grid += "\u001b!\u0008"+"No"+"\u001b!\u0000"+"   "+"\u001b!\u0008"+"Product"+"\u001b!\u0000"+"\t  "
	        				 +" \u001b!\u0008"+"Qty"+"\u001b!\u0000   "+"\u001b!\u0008"+"Unit"+"\u001b!\u0000\n";
	        print_token_grid += "------------------------------------------\n";
	        
 
			/***query to get items data*/
	        SQLQuery qry1 = session.createSQLQuery("select b.item_name as item_name,b.hsn_uuid as HSNID,c.code as hsncode,tbl.portion_name,tbl.portion_alias, b.\"ItemID\" as itemid, cat.category_name as category_name from "
	        		+ "pos_outlet_bom_items a left join pos_items b on a.item_uuid = b.uuid left join pos_hsncodes c on b.hsn_uuid = c.uuid left join ( select a.uuid as OutletItemPortionPriceID,a.portion_uuid as PortionID,b.portion_name,b.portion_alias from "
	        		+ "pos_outlet_item_portion_prices a left join pos_unit_portions b on a.portion_uuid = b.uuid where true ) as tbl on a.portion_uuid = tbl.OutletItemPortionPriceID left join pos_categories cat on cat.uuid=b.category_uuid where a.outlet_uuid ='"+outlet_uuids+"' and a.\"Billno\" ="+BillNo);
	        List<Object[]> results1 = (List<Object[]>)qry1.list();
			System.out.println(results1);
			List<ItemsBean> pos_itemsBeanlist = new ArrayList<ItemsBean>();	
	        int totalRecords1 = 0;
	         try {
	            	totalRecords1 = results1.size();
	         }catch(Exception e) {
	            	e.printStackTrace();
	         }
	        
	        for(Object[] itemsBeanResult: results1){	   
	        	ItemsBean pos_itemsBean = new ItemsBean();
	        	pos_itemsBean.setItem_name((String)itemsBeanResult[0]);
	        	pos_itemsBean.setHsn_uuid((String)itemsBeanResult[1]);
	        	pos_itemsBean.setCode((String)itemsBeanResult[2]);
	        	pos_itemsBean.setPortion_name((String)itemsBeanResult[3]);
	        	pos_itemsBean.setPortion_alias((String)itemsBeanResult[4]);
	        	BigInteger b1;
	        	b1 = (BigInteger)itemsBeanResult[5];
	        	pos_itemsBean.setItemID(b1.longValue());
	        	pos_itemsBeanlist.add(pos_itemsBean);
	        	pos_itemsBean.setCategory_name((String)itemsBeanResult[6]);
	       }
	        
	        /*****designing the items details********/
		    int totalitemsCount = 0;
		    int totalitemsBeansCount = 0;
	         try {
	        	 totalitemsCount = bomItemsObjList.size();
	         }catch(Exception e) {
	             e.printStackTrace();
	         }
	         try {
	        	 totalitemsBeansCount = pos_itemsBeanlist.size();
	         }catch(Exception e) {
	             e.printStackTrace();
	         }
	         System.out.println("the totalitemsBeansCount issssss"+totalitemsBeansCount+"--------- the totalitemsCount issssss"+totalitemsCount);
	         
	         int subtotal_without_tax = 0;
	         int net_total = 0;
	         int sno = 1;
	         double discount_val = 0;
	         int subtotal = 0;
	         String items_grid_inventory = "";
	         String categoryname = "";
	         
	         for(int i1=0;i1<totalitemsCount;i1++) {
	        	System.out.println("for loop");
	        	Pos_Outlet_Bom_Items bomitem= bomItemsObjList.get(i1);
	        	ItemsBean pos_itemsBean = pos_itemsBeanlist.get(i1);
	        	if(pos_itemsBean.getItemID() == 10000)
	        		pos_itemsBean.setItem_name("OPEN ORDER-1");
	        	else if(pos_itemsBean.getItemID() == 20000)
	        		pos_itemsBean.setItem_name("OPEN ORDER-2");
	        	else if(pos_itemsBean.getItemID() == 30000)
	        		pos_itemsBean.setItem_name("OPEN ORDER-3");
	        	
	        	if(pos_itemsBean.getPortionID() == 11111)
	        		pos_itemsBean.setPortion_name("KG");
	        	else if(pos_itemsBean.getPortionID() == 22222)
	        		pos_itemsBean.setPortion_name("PC");
	        	else if(pos_itemsBean.getPortionID() == 33333)
	        		pos_itemsBean.setPortion_name("Unit");
	        	
	        	if(pos_itemsBean.getItemID() >= 10000) {
	        		if(bomitem.getGST_perc() == 0)
	        			pos_itemsBean.setCode(Integer.toString(1905));
	        		if(bomitem.getGST_perc() == 5 || bomitem.getGST_perc() == 18)
	        			pos_itemsBean.setCode(Integer.toString(2106));
	        		if(bomitem.getGST_perc() == 12)
	        			pos_itemsBean.setCode(Integer.toString(2008));
	        		if(bomitem.getGST_perc() == 28)
	        			pos_itemsBean.setCode(Integer.toString(1806));
	        	}
	           int quantityvalnow;
	           if((int)bomitem.getQuantity() == 1) {
	        	   quantityvalnow = (int) bomitem.getQuantity();
	           }else {
	        	   quantityvalnow = (int) bomitem.getQuantity();
	           }
	           double inclusiveprice = Math.round(bomitem.getOriginal_price() * ((bomitem.getGST_perc() + 100) / 100));
	           int pricewithquant = (int) (inclusiveprice * quantityvalnow);
	           
	           categoryname= pos_itemsBean.getCategory_name();
	           
	           DecimalFormat df = new DecimalFormat("0.00");
	           items_grid_inventory += "<tr>\n<td>" +sno + "<br />&nbsp;</td>\n"
							           		+ "<td  align=\"center\" style=\"font-weight:600;font-size:14px !important;\">"+pos_itemsBean.getItem_name()+"</td>\n"
							           		+ "<td  align=\"left\" style=\"font-weight:600;\">"+quantityvalnow+"</td>\n"
							           		+ "<td  style=\"font-weight:600;\">"+pos_itemsBean.getPortion_alias()+"</td></tr>\n";
	           
	           items_details += "<tr>\n<td>" + sno + "<br />&nbsp;</td>\n"
		           		     + "<td width=\"33%\" style=\"font-weight:600;font-size:14px !important;\">"+pos_itemsBean.getItem_name()+"<br /><span class=\"hsncode\">[GST "+ bomitem.getGST_perc()+"% HSN:"+pos_itemsBean.getCode()+"]</span></td>\n"
		           		     + "<td  align=\"left\"  width=\"17%\" style=\"font-weight:600;\">"+quantityvalnow+"<br /><span class=\"hsncode\">SGST "+bomitem.getCGST_perc()+"%</span></td>\n"
		           		     + "<td  width=\"13%\" style=\"font-weight:600;\">"+pos_itemsBean.getPortion_alias()+"<br /><span class=\"hsncode\"> ("+df.format(bomitem.getCGST_amt())+")</span></td>\n"
		           		     + "<td  width=\"17%\" style=\"font-weight:600;\">"+ Math.round(inclusiveprice)+".00<br /><span class=\"hsncode\">CGST "+bomitem.getCGST_perc()+"%</span></td>\n"
		           		     + "<td  width=\"15%\" style=\"font-weight:600;\"><span class=\"price\">"+pricewithquant+"/-<br /><span class=\"hsncode\">("+bomitem.getCGST_amt()+")</td>\n</tr>\n";
		            
	           
	           /******for linux*********************/
	           
	           int fixed_length=12;
	           int item_len=pos_itemsBean.getItem_name().length();
	           
	           if(item_len<fixed_length){
	        	   print_str +=sno+")"+" ";
	               int spaces=fixed_length-item_len;
	               print_str += pos_itemsBean.getItem_name();
	               
	               for(int k=0;k<spaces;k++){
	            	   print_str +=" ";
	               }
	               
	               print_str +="  "+quantityvalnow+" ";   
	               print_str += "   "+pos_itemsBean.getPortion_alias()+" "; 
	               print_str += "  "+Math.round(inclusiveprice)+".00"+" ";    
	               print_str += " "+pricewithquant+"\n"; 
	           }else if (item_len>fixed_length) {
	        	   print_str +=sno+")"+" ";
		           for(int n=0;n<fixed_length;n++){
		               print_str +=pos_itemsBean.getItem_name().charAt(n);
		               if(n==11){
		                     print_str +="-";
		                	 print_str +="  "+quantityvalnow+" ";   
		                	 print_str += "   "+pos_itemsBean.getPortion_alias()+" "; 
		                	 print_str += "  "+Math.round(inclusiveprice)+".00"+" ";    
		                	 print_str += " "+pricewithquant+"\n"; 
		                	 print_str +="   ";
			                 for(int o=n+1;o<=item_len;o++){
			                	 try {
			                		 print_str +=pos_itemsBean.getItem_name().charAt(o);
			                	 }catch(Exception e) {
			                		 e.printStackTrace();
			                	 }
			                 }
			                 print_str +="\t";   
			                 print_str += "\t";
			                 print_str += "\t";   
			                 print_str += "\n"; 
		                 }
		            }     
	            }else{
	            	print_str +=sno+")"+" ";
	            	print_str +=pos_itemsBean.getItem_name();
	            	print_str +="  "+quantityvalnow+" ";   
	            	print_str += "   "+pos_itemsBean.getPortion_alias()+" "; 
	            	print_str += "  "+Math.round(inclusiveprice)+".00"+" ";    
	            	print_str += " "+pricewithquant+"\n"; 
	           }
	           String gst_values = (char)27+"M"+(char)1+"[GST:"+bomitem.getGST_perc()+"%";
	           gst_values += " HSN:"+pos_itemsBean.getCode()+"]";
	           gst_values += "(SGST "+bomitem.getCGST_perc()+"%-";
	           gst_values += df.format(bomitem.getCGST_amt())+")";
	           gst_values += "(CGST "+bomitem.getCGST_perc()+"%-";
	           gst_values += bomitem.getCGST_amt()+")"+"\n";
	           gst_values += (char)27+"M"+(char)0;
	            
	           print_str +=gst_values;

	           
	           /*******for items token grid linux********/
	          
	           if(item_len<fixed_length){
	        	   print_token_grid +=sno+")"+" ";
	               int spaces=fixed_length-item_len;
	               print_token_grid += pos_itemsBean.getItem_name();
	               for(int k=0;k<spaces;k++){
	            	   print_token_grid +=" ";
	               }
	               print_token_grid +="  "+quantityvalnow+"   ";   
	               print_token_grid += pos_itemsBean.getPortion_alias()+"\n";
	           }else if (item_len>fixed_length) {
	        	   print_token_grid +=sno+")"+" ";
		            for(int n=0;n<fixed_length;n++){
		            	print_token_grid +=pos_itemsBean.getItem_name().charAt(n);
		                 if(n==11){
		                   print_token_grid +="-";
		                   print_token_grid +="  "+quantityvalnow+"   ";   
		                   print_token_grid += pos_itemsBean.getPortion_alias()+"\n";
		                   print_token_grid +="\t";
		                   for(int o=n+1;o<=item_len;o++){
		                    	try {
		                    		print_token_grid +=pos_itemsBean.getItem_name().charAt(o);
			                	 }catch(Exception e) {
			                		 e.printStackTrace();
			                	 }
		                    }
		                   print_token_grid +="\t";   
		                   print_token_grid += "\t";
		                   print_token_grid += "\t";   
		                   print_token_grid += "\n"; 
		                 }
		            }    
	           }else{
	        	   print_token_grid +=sno+")"+" ";
	        	   print_token_grid +=pos_itemsBean.getItem_name();
	        	   print_token_grid +="  "+quantityvalnow+"   ";   
	        	   print_token_grid += pos_itemsBean.getPortion_alias()+"\n";
	              
	           } 
	          /****end of linux code*******/
	           
	           
	           double discount_item = bomitem.getDiscount_amt();
	           double discount_bill = bomitem.getAdt_discount_amt();
	           discount_val+= discount_item + discount_bill;
	           subtotal += Math.round(pricewithquant);
	           net_total += Math.round(bomitem.getPrice());
	           sno++;
	
	       }
	       /*******end of the for loop************/
	         System.out.print("subtotal is"+Math.round(subtotal));
		       System.out.print("\ndis val is"+Math.round(discount_val));
		       System.out.print("\nnet_total val is"+Math.round(net_total));
		       System.out.println("\n in words net amount is==="+erpFunObj.convertDigitToString(Math.round(net_total)));
		       
		       /*****designing the total amount details********/
		       String netamt_words = erpFunObj.convertDigitToString(Math.round(net_total));
		       String discfinalvalue = "<label>Rs</label> " +Math.round(discount_val);
		       items_details += "</table>\n";
		       items_details  += "<span>----------------------------------------------------------------------------</span>\n"
			       			  + "<table border=\"0\" width=\"100%\">\n"
			       			  + "<tr><td style=\"text-align:left;\"><strong>TOTAL:</strong></td>\n"
			       			  + "<td style=\"padding-left:2px;\"><label>Rs</label>"+Math.round(subtotal)+"/-</td>\n"
			       			  + "</tr>\n";
		       
		       
		        print_str += "------------------------------------------\n";
		        print_str += Constants.left+"\u001b!\u0008"+"TOTAL AMOUNT:"+"\u001b!\u0000"+"\t\t\t";
		        print_str += "\u001b!\u0008"+"Rs"+"\u001b!\u0000"+ Math.round(subtotal)+"/-\n";
		        
		        
			       
		       if(returnAmount!=0) {
		    	   net_total = (int) (returnAmount - subtotal);
		    	   netamt_words = erpFunObj.convertDigitToString(Math.round(net_total));
		    	   items_details += "<tr><td style=\"text-align:left;\"><strong>"
		    	   		+ "CREDIT AMOUNT: </strong></td><td style=\"\"><label>Rs</label>" +returnAmount+"/-</td></tr>";
		    	   items_details += "<tr><td style=\"text-align:left;\"><strong>DISCOUNT AMOUNT:</strong></td>\n"
			       			  + "<td style=\"padding-left:2px;\">"+ discfinalvalue +"</td>\n"
			       			  + "</tr>\n";
			       items_details += "<tr><td style=\"text-align:left;\"><strong>NET AMOUNT:</strong> </td>\n"
			       			  + "<td style=\"padding-left:2px;font-size:22px;\"><label>Rs</label> <strong>"+Math.abs(net_total)+"/-</strong></td>\n"
			       			  + "</tr>\n";
			       items_details += "<tr><td colspan=\"4\" style=\"text-align:left;font-weight:bold;\">("+netamt_words+" rupees)</td>\n"
			       			  + "</tr><tr><td COLSPAN=\"4\">----------------------------------------------------------------------------</td>\n</tr>\n"
			       			  + "<tr><td style=\"text-align:left;\"><strong>PAYMENT DETAILS</strong> </div></tr>\n"
			       			  + "<tr><td COLSPAN=\"4\">----------------------------------------------------------------------------</td>\n"
			       			  + "</tr>\n";
			       
			       
			       print_str += "\u001b!\u0008"+"CREDIT AMOUNT:"+"\u001b!\u0000"+"\t\t\t";
			       print_str += "\u001b!\u0008"+"Rs"+"\u001b!\u0000"+ returnAmount+"/-\n";
			       print_str += "\u001b!\u0008"+"DISCOUNT AMOUNT:"+"\u001b!\u0000"+"\t\t  ";
			       print_str += "Rs "+ Math.round(discount_val)+"\n";
			       print_str += "\u001b!\u0008"+"NET AMOUNT:"+"\u001b!\u0000"+"\t\t\t";
			       print_str += "\u001b!\u0008"+"Rs "+Math.abs(net_total)+"\u001b!\u0000"+"/-\n";
			       print_str += "\u001b!\u0008"+"("+netamt_words+" rupees)"+"\u001b!\u0000"+"\n";
			       print_str += "------------------------------------------\n";
			       print_str += "\u001b!\u0008"+"PAYMENT DETAILS"+"\u001b!\u0000"+"\n";
			       print_str += "------------------------------------------\n";
			        
			        
		       }else {
		    	   items_details += "<tr><td style=\"text-align:left;\"><strong>DISCOUNT AMOUNT:</strong></td>\n"
			       			  + "<td style=\"padding-left:2px;\">"+ discfinalvalue +"</td>\n"
			       			  + "</tr>\n";
			       items_details += "<tr><td style=\"text-align:left;\"><strong>NET AMOUNT:</strong> </td>\n"
			       			  + "<td style=\"padding-left:2px;font-size:22px;\"><label>Rs</label> <strong>"+Math.round(net_total)+"/-</strong></td>\n"
			       			  + "</tr>\n";
			       items_details += "<tr><td colspan=\"4\" style=\"text-align:left;font-weight:bold;\">("+netamt_words+" rupees)</td>\n"
			       			  + "</tr><tr><td COLSPAN=\"4\">----------------------------------------------------------------------------</td>\n</tr>\n"
			       			  + "<tr><td style=\"text-align:left;\"><strong>PAYMENT DETAILS</strong> </div></tr>\n"
			       			  + "<tr><td COLSPAN=\"4\">----------------------------------------------------------------------------</td>\n"
			       			  + "</tr>\n";
			       
			       print_str += "\u001b!\u0008"+"DISCOUNT AMOUNT:"+"\u001b!\u0000"+"\t\t  ";
			       print_str += "Rs "+ Math.round(discount_val)+"\n";
			       print_str += "\u001b!\u0008"+"NET AMOUNT:"+"\u001b!\u0000"+"\t\t\t";
			       print_str += "\u001b!\u0008"+"Rs "+Math.abs(net_total)+"\u001b!\u0000"+"/-\n";
			       print_str += "\u001b!\u0008"+"("+netamt_words+" rupees)"+"\u001b!\u0000"+"\n";
			       print_str += "------------------------------------------\n";
			       print_str += "\u001b!\u0008"+"PAYMENT DETAILS"+"\u001b!\u0000"+"\n";
			       print_str += "------------------------------------------\n";
		       }
	 
		    String token_items= "";
		    String billno = String.valueOf(BillNo);
	        String year=Constants.year;
	        String outlet_uuid = String.valueOf(Integer.valueOf(outlet_uuids)+10);
			String commonInBill = year+outlet_uuid;
			long presentBillNo = Long.valueOf(billno.replace(commonInBill,""));
			
		    if(token_flag==1) {
		    	token_items= "<table border=\"0\" width=\"100%\">\n"
		    				+ "<!--<tr><td style=\"text-align:center;font-weight:bold;\" colspan=\"4\">"+categoryname+"</td></tr>-->\n"
		    				+ "<tr><td style=\"\" colspan=\"2\"><strong>Date:"+new Date()+"</strong></td><td style=\"\" colspan=\"2\"><strong>Invoice No:"+presentBillNo+"</strong></td></tr>\n"
		    				+ "<tr><span>---------------------------------------------------------------------------</span></tr>\n"
		    				+ "<tr><td style=\"font-size:19px;font-weight:bold;\">No&nbsp;</td><td style=\"font-size:19px;font-weight:bold;\" align=\"center\">Product</td><td align=\"left\" style=\"font-size:19px;font-weight:bold;\">Qty</td><td style=\"font-size:19px;font-weight:bold;\">Unit</td></tr>\n"
		    				+ items_grid_inventory+"</table><span>---------------------------------------------------------------------------</span><div align=\"center\"><span style=\"text-align:center; font-style: italic;\">COLLECT FROM COUNTER </br>THANK YOU</span></div></div>";
		    	
		    	print_token_str = "------------------------------------------\n";
		    	print_token_str += Constants.center+categoryname+"\n";
		    	print_token_str += "\u001b!\u0008"+"Date:"+new Date()+"\u001b!\u0000"+"\n";
		    	print_token_str += "\u001b!\u0008"+"Invoice No: "+presentBillNo+"\u001b!\u0000"+"\n";
		    	print_token_str += print_token_grid;
		    	
		    	print_token_str += Constants.cutpaper;
		    	return items_details+","+token_items+"@"+print_str+"@@"+print_token_str;
		    }
		} catch(Exception sqlException) {
			System.out.println("****items details are not coming**********");
			sqlException.printStackTrace();
		}finally {
			session.close();
		}
	    return items_details+"@"+print_str;
	}

	
	
	
	@Override
	public String gstDetails(List<Pos_Outlet_Bom_Items> bomItemsObjList, int btobordresCount) {
		
		String print_str = "";
		String gst_details = "";
		
		/*********** For all bills ***************/
		if(btobordresCount == 0) {
			 gst_details = "<table border=\"2\" width=\"98%\">\n"
								+ "<tr><td width=\"20%\" align=\"center\">TAX %</td>\n"
								+ "<td width=\"38%\" align=\"center\">TAXABLE VALUE</td>\n"
								+ "<td width=\"20%\" align=\"center\">CGST</td>\n"
								+ "<td width=\"20%\" align=\"center\">SGST</td></tr>\n"
								+ "<span>----------------------------------------------------------------------------</span>\n";
			
			/*******************for linux***************/
			 print_str = "------------------------------------------\n";
			 print_str += "TAX%"+"\t";   
			 print_str += " TAXABLE_VALUE\t";
			 print_str += "  CGST\t";
			 print_str += "   SGST\n";
			 print_str += "------------------------------------------\n";
		} 
		
		/*********** Only b to b order case ***************/
		else {
			gst_details = " <table border=\"2\" width=\"98%\">\n" + 
					      "<tr><td width=\"20%\" align=\"center\">TAX %</td><td width=\"38%\" align=\"center\">TAXABLE VALUE</td>"
					      + "<td width=\"20%\" align=\"center\">IGST</td>"
					      + "<td width=\"20%\" align=\"center\">CGST</td>"
					      + "<td width=\"20%\" align=\"center\">SGST</td></tr>\n" + 
					      "<span>----------------------------------------------------------------------------------------------------------------</span>";
		
			/*******************for linux***************/
			 print_str = "------------------------------------------\n";
			 print_str += "TAX%"+"\t";   
			 print_str += "TAXABLE_VALUE\t";
			 print_str += "IGST\t";
			 print_str += "CGST  ";
			 print_str += "SGST\n";
			 print_str += "------------------------------------------\n";
		
		}
		
		//Gst - Summary Details
        List<GstAndCgstBean> gstList = new ArrayList<GstAndCgstBean>();
        List<GstAndCgstBean> cgstList = new ArrayList<GstAndCgstBean>();
        
        int totalitemsCount = 0;
         try {
        	 totalitemsCount = bomItemsObjList.size();
         }catch(Exception e) {
             e.printStackTrace();
         }
         
        for(Pos_Outlet_Bom_Items positemBean : bomItemsObjList) {
            double gst_perc = positemBean.getGST_perc();
            double perc_gst_amt =0d;
            double perc_cgst_amt = 0d;
            try {
                double GST_perc = positemBean.getGST_perc();
                double price = positemBean.getPrice();
                double cgst_amt = positemBean.getCGST_amt();
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
        int count_gstkey = 0;
        Double totalvaluegst = 0d;
		Double totalvaluecgst = 0d;
		Double totalvalueigst = 0d;
		Double totalvalueigst_val = 0d;
		DecimalFormat df = new DecimalFormat("0.00");
		
		for(Double gstKey : keysInGstAmountSum) {
			
			/**************For all bills**************/
			if(btobordresCount == 0) {
	        	gst_details +="<tr><td align=\"center\">[<strong>GST " + gstKey +"</strong>]</td>\n" + 
	        			"<td  align=\"center\">"+df.format(gstAndAmountSum.get(gstKey))+"</td>\n" + 
	        			"<td align=\"center\">" + df.format(cgstAndAmountSum.get(gstKey)) + "</td>\n" + 
	        			"<td  align=\"center\">" + df.format(cgstAndAmountSum.get(gstKey)) + "</td></tr>";
	        	
	        	/**************for linux************/
	        	Double tax = gstKey;
	        	String item_taxvalue = "";
	        	String Taxable_value = df.format(gstAndAmountSum.get(gstKey));
	        	String CGST = df.format(cgstAndAmountSum.get(gstKey));
	        	String SGST = df.format(cgstAndAmountSum.get(gstKey));
	        	
	        	if(tax>=0 && tax<10)
	                item_taxvalue= "0"+gstKey;
	            else
	                item_taxvalue= ""+gstKey;
	        	print_str += item_taxvalue+"\t   ";
	        	int fixed_length1=8;
	            int tax_len=Taxable_value.length();
	            
	            /*********for taxable value*************/
	            
	            if(tax_len<fixed_length1){
	            	print_str +=Taxable_value;
	                int spaces=fixed_length1-tax_len;
	                for(int k=0;k<spaces;k++){
	                	print_str +=" ";
	                } 
	             }else if (tax_len>fixed_length1) {
		            int spaces=substr(Taxable_value+" ", 0,8);
		            print_str += spaces;
		         }else{
	        	    print_str += Taxable_value;
	             }
	             print_str += "      ";
	        	
	             /**********for cgst value*************/
	             int fixed_length2=6;
	             int cgst_len=CGST.length();
	             if(cgst_len<fixed_length2){
		             int spaces=fixed_length2-cgst_len;
		             print_str +=CGST+" ";
		             for(int k=0;k<spaces;k++){
		                 print_str +=" ";
		             }     
	             }else if (cgst_len>fixed_length2) {
	                int spaces=substr(CGST+" ", 0,8);
	                print_str += spaces;
	             }else{
	                print_str += CGST;
	             }
	             print_str += "  ";
	             
	           //**********for sgst value*************
	             int sgst_len=SGST.length();
	             if(sgst_len<fixed_length2){
	                 int spaces=fixed_length2-sgst_len;
	                 print_str +=SGST+" ";
	                 for(int k=0;k<spaces;k++){
	                     print_str +=" ";
	                 }     
	             }
	             else if (sgst_len>fixed_length2) {
	                    int spaces=substr(SGST+" ", 0,6);
	                    print_str += spaces;
	             }
	             else{
	                    print_str += SGST;
	             }
	             print_str += "\n";
			}
             
             /****************For b to b order case*****************/
			else {
				totalvalueigst = cgstAndAmountSum.get(gstKey) + cgstAndAmountSum.get(gstKey);
				
				gst_details +="<tr><td align=\"center\">[<strong>GST " + gstKey +"</strong>]</td>\n" + 
	        			"<td  align=\"center\">"+df.format(gstAndAmountSum.get(gstKey))+"</td>\n" +
						"<td align=\"center\">"+df.format(totalvalueigst)+"</td>\n"+
	        			"<td align=\"center\">" + "0" + "</td>\n" + 
	        			"<td  align=\"center\">" + "0" + "</td></tr>\n";
				
				/******************for linux**********************/
				Double tax = gstKey;
	        	String item_taxvalue = "";
	        	String Taxable_value = df.format(gstAndAmountSum.get(gstKey));
	        	String CGST = df.format(cgstAndAmountSum.get(gstKey));
	        	String SGST = df.format(cgstAndAmountSum.get(gstKey));
	        	String IGST = df.format(totalvalueigst);
	        	
	        	if(tax>=0 && tax<10)
	                item_taxvalue= "0"+gstKey;
	            else
	                item_taxvalue= ""+gstKey;
	        	
		        print_str += item_taxvalue+"      ";
		        int fixed_length1=8;
		        int tax_len=Taxable_value.length();
		        if(tax_len<fixed_length1){
		             
		            print_str +=Taxable_value;
		            int spaces=fixed_length1-tax_len;
		            for(int k=0;k<spaces;k++){
		                print_str +=" ";
		            } 
		            
		        }
		        else if (tax_len>fixed_length1) {
		               int spaces=substr(Taxable_value+" ", 0,8);
		               print_str += spaces;
		        }
		        else{
		              print_str += Taxable_value;
		        }
		        print_str += "     ";
		        
		        //**********for IGST value*************
		        int fixed_length2=6;
		        int igst_len=IGST.length();
		    if(igst_len<fixed_length2){
		        print_str +=IGST+" ";
		        int spaces=fixed_length2-igst_len;
		        for(int k=0;k<spaces;k++){
		            print_str +=" ";
		        }
		          
		    }
		    else if (igst_len>fixed_length2) {
		           int spaces=substr(IGST+" ", 0,8);
		           print_str += spaces;
		    }
		    else{
		           print_str += IGST;
		    }
		           
		        //**********for cgst value*************
		        print_str +="\t"+"0";
		           
		    //**********for sgst value*************
		        print_str +="\t"+"0";

		        print_str += "\n";
			}
             
        	totalvaluegst +=gstAndAmountSum.get(gstKey);
        	totalvaluecgst +=cgstAndAmountSum.get(gstKey);
			totalvalueigst_val +=totalvalueigst;


        	count_gstkey++;
        }
		
		
	   /**********grand total****************/
		
       if(count_gstkey > 1) {
    	   
    	   /*********for all bills***********/
    	   if(btobordresCount == 0) {
	    	   gst_details += "<tr><td  align=\"center\"><strong>Grand Total</strong></td><td  align=\"center\">"+df.format(totalvaluegst)+"</td>\n"
	        			   + "<td  align=\"center\">"+df.format(totalvaluecgst)+"</td><td  align=\"center\">"+df.format(totalvaluecgst)+"</td></tr>\n";
	    	   
	    	   /***************for linux*********/
	    	   print_str += "------------------------------------------\n";
	    	   print_str += "\u001b!\u0008"+"Grandtotal:"+"\u001b!\u0000"+" ";
	    	   int fixed_length2=8;
	    	   String total_gst = df.format(totalvaluegst);
	    	   String total_cgst = df.format(totalvaluecgst);
	    	   String total_sgst = df.format(totalvaluecgst);
	    	   int total_gst_len = total_gst.length();
	    	   if(total_gst_len<fixed_length2){
	    	        print_str +=total_gst+" ";
	    	        int spaces=fixed_length2-total_gst_len;
	    	        for(int k=0;k<spaces;k++){
	    	            print_str +=" ";
	    	        }
	    	    }
	    	    else if (total_gst_len>fixed_length2) {
	    	        int spaces=substr(total_gst+" ", 0,8);
	    	        print_str += spaces;
	    	    }
	    	    else{
	    	        print_str += total_gst;
	    	    }   
	    	    print_str += "    ";
	    	    
		    	  //**********for total cgst value*************
		          int total_cgst_len=total_cgst.length();
		          if(total_cgst_len<fixed_length2){
		            print_str +=total_cgst;
		            int spaces=fixed_length2-total_cgst_len;
			            for(int k=0;k<spaces;k++){
			                print_str +=" ";
			            } 
		           }
		          else if (total_cgst_len>fixed_length2) {
		               int spaces=substr(total_cgst+" ", 0,8);
		               print_str += spaces;
		          }
			      else{
			          print_str += total_cgst;
			     }
		         print_str += " ";
		         
		        //**********for total sgst value*************
		        int total_sgst_len=total_sgst.length();
		        if(total_sgst_len<fixed_length2){
		            print_str +=total_sgst+" ";
		            int spaces=fixed_length2-total_sgst_len;
		            for(int k=0;k<spaces;k++){
		                print_str +=" ";
		            }  
		              
		        }else if (total_sgst_len>fixed_length2) {
		            int spaces=substr(total_sgst+" ", 0,8);
		            print_str += spaces;
		        }else{
		            print_str += total_sgst;
		        }
		        
		        print_str += "\n";
    	   }
    	   
    	   /**************Only for b to b order***************/
    	   
    	   else {
    		   gst_details += "<tr><td  align=\"center\"><strong>Grand Total</strong></td><td  align=\"center\">"+df.format(totalvaluegst)+"</td>\n"
        			   	   +"<td  align=\"center\">"+totalvalueigst_val+"</td>"
    				       + "<td  align=\"center\">"+"0"+"</td><td  align=\"center\">"+"0"+"</td></tr>\n";
    		   
    		   /*************for linux****************/
    		   print_str += "------------------------------------------\n";
	    	   print_str += "\u001b!\u0008"+"Grandtotal:"+"\u001b!\u0000"+" ";
	    	   int fixed_length2=8;
	    	   String total_gst = df.format(totalvaluegst);
	    	   String total_cgst = df.format(totalvaluecgst);
	    	   String total_sgst = df.format(totalvaluecgst);
	    	   String toatl_igst_val = df.format(totalvalueigst_val);
	    	   
	    	   int total_gst_len = total_gst.length();
	    	   if(total_gst_len<fixed_length2){
	    	        print_str +=total_gst+" ";
	    	        int spaces=fixed_length2-total_gst_len;
	    	        for(int k=0;k<spaces;k++){
	    	            print_str +=" ";
	    	        }
	    	    }
	    	    else if (total_gst_len>fixed_length2) {
	    	        int spaces=substr(total_gst+" ", 0,8);
	    	        print_str += spaces;
	    	    }
	    	    else{
	    	        print_str += total_gst;
	    	    }   
	    	    print_str += "  ";
	    	    
	    	  //**********for total Igst value*************
	    	    
		        int total_igst_len=toatl_igst_val.length();
		        if(total_igst_len<fixed_length2){
		            print_str +=toatl_igst_val+" ";
		            int spaces=fixed_length2-total_igst_len;
		            for(int k=0;k<spaces;k++){
		                print_str +=" ";
		            }  
		              
		        }else if (total_igst_len>fixed_length2) {
		            int spaces=substr(toatl_igst_val+" ", 0,8);
		            print_str += spaces;
		        }else{
		            print_str += toatl_igst_val;
		        }
		    	  
		        //**********for total cgst value*************
		          
		        print_str += " "+"0";
		        //**********for total sgst value*************
		        print_str += "\t"+"0";
		        
		        print_str += "\n";
    	   
    	   }
	    
       }
       gst_details += "</table>\n";
       gst_details += "<div id=\"barcodediv\" align=\"center\"><img id=\"hiddenVal\"/></div>\n"
	        		   + "<span>----------------------------------------------------------------------------</span>\n"
	        		   + "<table border=\"0\" width=\"100%\">\n"
	        		   + "<tr><td style=\"text-align:center;font-weight:bold;\">*VISIT AGAIN*<br />*THANK YOU*</td></tr>\n"
	        		   + "<tr><td style=\"text-align:center;\"><strong>Note: Goods once sold can not be taken back or exchanged.</strong></td></tr>\n"
	        		   + "</table><span>---------------------------------------------------------------------------</span><div align=\"center\"><span style=\"text-align:center; font-style: italic;\">Powered by Ramer Software</span></div></div>";
       
       print_str += "------------------------------------------\n";
       print_str +=Constants.center+"\u001b!\u0008"+"*VISIT AGAIN*"+"\u001b!\u0000"+"\n";
       print_str +=Constants.center+"\u001b!\u0008"+"*THANK YOU*"+"\u001b!\u0000"+"\n";
       print_str +=Constants.center+"\u001b!\u0008"+"Note:Goods once sold can not be taken back or exchanged."+"\u001b!\u0000"+"\n";
       print_str += "------------------------------------------\n";
       print_str +=Constants.center+"\u001b!\u0008"+"Powered by Ramer Software"+"\u001b!\u0000"+"\n";
       
		return gst_details+"@"+print_str;
	}

	private int substr(String string, int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public String getpreOrderAmountDetails(double balance_amtcheck, List<Pos_Outlet_Bom_Transaction> bomTransactionList) {
			
		   String items_grid = "";
		   String print_str = "";
	       int totalTransactionsCount = 0;
	       int checkh = 0;
	       int check_else = 0;
	       
	       try {
	        	 totalTransactionsCount = bomTransactionList.size();
	       }catch(Exception e) {
	             e.printStackTrace();
	       }
		 for(int i2=0;i2<totalTransactionsCount;i2++) {
		       Pos_Outlet_Bom_Transaction bomtrans = (Pos_Outlet_Bom_Transaction) bomTransactionList.get(i2);
		       items_grid += "<tr><td style=\"text-align:left;\"><strong>";
		       if (Math.round(balance_amtcheck) !=0.0) {
		    	   System.out.println("*************billscommfn********ifcase::::"+Math.round(balance_amtcheck)+"*******");
		            if (checkh == 0) {
		                items_grid += "Advance Amount("+bomtrans.getTransaction_type()+")";
						items_grid += "<td style=\"padding-left:2px;\"><label>Rs</label> <strong>"+bomtrans.getAmount()+"/-</strong></td></tr>";      
						String advance_pay= "Advance Amount("+bomtrans.getTransaction_type()+")";
						   
						/*********for linux*****/
						print_str += "\u001b!\u0008"+"Advance Amount("+bomtrans.getTransaction_type()+")"+"\u001b!\u0000"+"\t\t";
						print_str += "\u001b!\u0008"+"Rs "+bomtrans.getAmount()+"\u001b!\u0000"+"/- \n";
						
		                
		            }else {
		                check_else = 1;
		                items_grid += "Paid Amount("+bomtrans.getTransaction_type()+")</strong> </td>";
						items_grid += "<td style=\"padding-left:2px;\"><label>Rs</label> <strong>"+bomtrans.getAmount()+"/-</strong></td></tr>";
		                String advance_paid="Paid Amount("+bomtrans.getTransaction_type()+")";
		                
		                /*********for linux*****/
						print_str += "\u001b!\u0008"+"Paid Amount("+bomtrans.getTransaction_type()+")"+"\u001b!\u0000"+"\t\t";
						print_str += "\u001b!\u0008"+"Rs "+bomtrans.getAmount()+"\u001b!\u0000"+"/- \n";
		                
		                
		            }
		       }else{
		    	   System.out.println("*************billscommfn********elsecase::::"+Math.round(balance_amtcheck)+"*******");
			    	   if (bomtrans.getCard_type()!=null){
						  items_grid += "<tr><td style=\"text-align:left;\"><strong>"+bomtrans.getTransaction_type()+ "("+bomtrans.getCard_type()+")</strong> </td>\n"
						  			 + "<td><label>Rs</label> <strong>"+bomtrans.getAmount()+"/-</strong></td></tr>";
						  String advance_pay="Advance Amount("+bomtrans.getTransaction_type()+"["+bomtrans.getCard_type()+"]"+")";
						  
						 /*********for linux*****/
						  
						 print_str += "\u001b!\u0008"+bomtrans.getTransaction_type()+"("+bomtrans.getCard_type()+")"+"\u001b!\u0000"+"\t\t";
						 print_str += "\u001b!\u0008"+"Rs "+bomtrans.getAmount()+"\u001b!\u0000"+"/- \n";
						  
						  
		            }else{
		         	  String advance_pay="Advance Amount("+bomtrans.getTransaction_type()+")";
		         	  items_grid += "<tr><td style=\"text-align:left;\"><strong>"+bomtrans.getTransaction_type()+"</strong> </td>\n"
		         	  			 + "<td><label>Rs</label> <strong>"+bomtrans.getAmount()+"/-</strong></td></tr>";
		         	   
		         	   /*********for linux*****/
						print_str += "\u001b!\u0008"+bomtrans.getTransaction_type()+"\u001b!\u0000"+"\t\t";
						print_str += "\u001b!\u0008"+"Rs "+bomtrans.getAmount()+"\u001b!\u0000"+"/- \n";
		         	  
					   }
			   }
		       checkh++;
		       
		    }
		 String table_viewstatus = "";
		 String linux_table_viewstatus = "";
		 items_grid += "<tr><td style=\"text-align:left;\"><strong>";
		 if (Math.round(balance_amtcheck) != 0) {
		      double bal_amt = balance_amtcheck;
		      items_grid += "Balance Amount";
		      items_grid += "</strong></td><td style=\"padding-left:1px;\"><label>Rs</label> <strong>"+Math.abs(bal_amt)+"/-</strong></td></tr>";
		      table_viewstatus = "<table  border=\"1\" width=\"100%\"><tr><td align=\"center\"  "
		      					+ "style=\"font-weight:bold;\">Status</td><td align=\"center\" style=\"font-weight:bold;\">\n"
		      					+ "Not Delivered</td></tr></table>\n";
		      
		      /************for linux***********/
		      print_str += "\u001b!\u0008"+"Balance Amount:"+"\u001b!\u0000"+"\t\t  ";
		      print_str += "\u001b!\u0008"+"Rs "+Math.abs(bal_amt)+"\u001b!\u0000"+"/- \n";
		      
		      linux_table_viewstatus += "\u001b!\u0008"+"Status"+"\u001b!\u0000"+"\t\t";
              linux_table_viewstatus += "\u001b!\u0008"+"Not Delivered"+"\u001b!\u0000"+"\n";
		      
		 }
		 
		 if (check_else == 1) {
			 double bal_amt = balance_amtcheck;
			 items_grid+="<strong>Balance Amount:</strong></strong></td>\n" + 
			 			 "\n" + 
			 			 "<td><label style=\"font-weight:normal;\">Rs</label> <strong>"+Math.abs(bal_amt)+"/-</strong></td>\n" + 
			 			 "</tr>";
			 
			 /**********for linux******/
             print_str += "\u001b!\u0008"+"Balance Amount:"+"\u001b!\u0000"+"\t\t  ";
             print_str += "\u001b!\u0008"+"Rs "+Math.abs(bal_amt)+"\u001b!\u0000"+"/- \n";
             
	     }
		 items_grid += "</table><span>----------------------------------------------------------------------------</span><div>\n"+table_viewstatus
   			   + "</div><div class=\"divTable\">\n"
   			   + "<div class=\"divTableBody\">\n"
   			   + "<div class=\"divTableRow\">\n"
   			   + "<div class=\"\"><strong>GST SUMMARY</strong></div>\n"
   			   + "<div class=\"divTableCell\"></div>\n"
   			   + "<div class=\"divTableCell\"></div>\n"
   			   + "<div class=\"divTableCell\"></div>\n"
   			   + "</div></div></div>\n";
		 
		 /**********for linux******/
         print_str += "------------------------------------------\n";
         print_str += linux_table_viewstatus+"\n";
         print_str += "------------------------------------------\n";
         print_str += "\u001b!\u0008"+"GST SUMMARY"+"\u001b!\u0000"+"\n";
		 
		return items_grid+"@"+print_str;
	}
	
}

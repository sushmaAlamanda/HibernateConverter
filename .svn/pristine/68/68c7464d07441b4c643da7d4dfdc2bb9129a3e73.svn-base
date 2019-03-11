package com.ramersoft.pos.ui.libs;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ramersoft.pos.entities.Pos_CreditNoteDetails;
import com.ramersoft.pos.entities.Pos_Outlet_Bills;
import com.ramersoft.pos.entities.Pos_Outlet_ProformaBills;
import com.ramersoft.pos.ui.beans.Singleton;

/**
 *  Contains Functions about Next Bills and Transaction,BOM,InvoiceID,AmountID.........
 */

@Component
public class BillsRandomGenerations {
    
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	ERPFunctions erpFunObj;
	
	
	public Long getbill_NextProformaNumber(HttpSession httpSession) {
		  
		String outlet_uuids = (String)httpSession.getAttribute("Outlet_uuid");
		System.out.println("outlet_uuid is:"+outlet_uuids);
		Session session = null;
		try {
			long maxBillNo = 0L;
			String maxBillNoString ="";
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Pos_Outlet_ProformaBills.class).setProjection(Projections.max("Billno"));
			criteria.setMaxResults(1);
		    try {
			maxBillNo = (long)criteria.uniqueResult();
			maxBillNoString = String.valueOf(maxBillNo);
		    }catch(NullPointerException npe) {
		    	System.out.println("************it is catch***********");
		    	long countNo=0L;
		    	String countNoString ="";
		    	Criteria query_for_count = session.createCriteria(Pos_Outlet_ProformaBills.class).setProjection(Projections.rowCount());
		    	countNo = (long)query_for_count.uniqueResult();
				if(countNo == 0) {
					System.out.println("************if  ***********");
					maxBillNoString = String.valueOf(0);
					System.out.println("************"+maxBillNoString+"***********");
				}
				else {
					System.out.println("------------error----------");
				}
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
		    System.out.println("maxBillNo is:"+maxBillNo);
		    
		    String year="";
			    try {
			    year = erpFunObj.getLocalDataInfo("current_year");
			    }catch(Exception e) {
			    	e.printStackTrace();
			    }
			System.out.println(year);
		    
			System.out.println(Singleton.getOutlet_uuid()+"---"+Singleton.getUser_uuid());
			
			String outlet_uuid = String.valueOf(Integer.valueOf(outlet_uuids)+10);
			String commonInBill = "88"+year+outlet_uuid;
			long presentBillNo = Long.valueOf(maxBillNoString.replace(commonInBill,""));
			
		    System.out.println("Present available bill no is:"+presentBillNo);
		    long nextBillNo = presentBillNo+1;
		    String nextBillNoString = Long.toString(nextBillNo);
		    String finalBillNoString = commonInBill+nextBillNoString;
		    long finalBillNo = Long.valueOf(finalBillNoString);
		    
		    System.out.println("This is the next Bill no:"+finalBillNo);
		    
			return finalBillNo;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		  
		return null;		
	
	}
	
	
	public Long generateUniqueToken(HttpSession httpSession,String columnName,int length) {
		
		String outlet_uuids = (String)httpSession.getAttribute("Outlet_uuid");
		System.out.println("outlet_uuid is:"+outlet_uuids);
		
		
		String field = columnName;
		int fieldLength = length;
		
	    System.out.println(outlet_uuids+" ---- " + field + " ---- "+ fieldLength);
		Session session = null;
		
		try {		   						
			int min = 9999;
			int max = 98888;
			Random ran = new Random();
			int randomInt = min + ran.nextInt(max - min + 1);
			System.out.println("random number is:"+randomInt);
			String randomNumString = Integer.toString(randomInt);
			String outlet_uuid = String.valueOf(Integer.valueOf(outlet_uuids)+10);
			String genereatedTokenStr = outlet_uuid+randomNumString;
			long generatedToken = Long.parseLong(genereatedTokenStr);
			System.out.println("generated long for given field is:"+generatedToken);
			System.out.println("going to check valid token or not");
			boolean existence = false;
			try {
				existence = checkValidToken(field,generatedToken,outlet_uuids);
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("checking existence of the token:"+existence);
			
			if(existence == true){
				return generateUniqueToken(httpSession,columnName,length);
			}else {				
				return generatedToken;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	
	}//end of generateUniqueToken
	
	
	
	public boolean checkValidToken(String columnName, long generatedToken,String outlet_uuid) {
		
		System.out.println("I am in checkValidToke");
		System.out.println(columnName+" ---- "+ generatedToken+" ---- "+outlet_uuid);
		Session session = null;
		try {
		    //session = sessionFactory.getCurrentSession();
			session = sessionFactory.openSession();
		    Criteria criteria = session.createCriteria(Pos_Outlet_Bills.class);
		    criteria.add(Restrictions.eq(columnName,generatedToken));
		    criteria.add(Restrictions.eq("outlet_uuid",outlet_uuid));		
		    return criteria.uniqueResult()!=null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}  //end of checkValidToken

    
    /******** get next number of Cahbill*********/
	
	public Long getbill_NextNumber(HttpSession httpSession) {
		String outlet_uuids = (String)httpSession.getAttribute("Outlet_uuid");
		System.out.println("outlet_uuid is:"+outlet_uuids);
		Session session = null;
		try {
			long maxBillNo = 0L;
			String maxBillNoString ="";
			session = sessionFactory.openSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("SELECT Max(\"Billno\") as \"Billno\" from pos_outlet_bills where CAST('Billno' AS TEXT) NOT LIKE '88%' and bill_type!='Estimate Bill'");
			List <BigInteger> rows = (List) query.list();
			System.out.println(rows);
			
			/*Criteria criteria = session.createCriteria(Pos_Outlet_Bills.class)
					.setProjection(Projections.max("Billno"))
					.add(Restrictions.ne("bill_type","Estimate Bill"))
					.add(Restrictions.not(Restrictions.like("BillNoString", "88%")))
					.setMaxResults(1);*/
		    try {
				maxBillNo = rows.get(0).longValue();
				maxBillNoString = String.valueOf(maxBillNo);
		    }catch(NullPointerException npe) {
		    	System.out.println("************it is catch***********");
		    	long countNo=0L;
		    	String countNoString ="";
		    	Criteria query_for_count = session.createCriteria(Pos_Outlet_Bills.class).setProjection(Projections.rowCount());
		    	countNo = (long)query_for_count.uniqueResult();
		    	/*SQLQuery query_for_count = session.createSQLQuery("SELECT count(*) from pos_outlet_bills");
				List <BigInteger> count_rows = (List) query_for_count.list();
				System.out.println(count_rows);
				countNo = count_rows.get(0).longValue();
				countNoString = String.valueOf(countNo);*/
				if(countNo == 0) {
					System.out.println("************if  ***********");
					maxBillNoString = String.valueOf(0);
					System.out.println("************"+maxBillNoString+"***********");
				}
				else {
					System.out.println("------------error----------");
				}
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
		    System.out.println("maxBillNo is:"+maxBillNoString);
		    
		    String year="";
			try {
			    year = erpFunObj.getLocalDataInfo("current_year");
			}catch(Exception e) {
			    	e.printStackTrace();
		    }
			System.out.println("year is "+year);
		    
			System.out.println(Singleton.getOutlet_uuid()+"---"+Singleton.getUser_uuid());
			
			String outlet_uuid = String.valueOf(Integer.valueOf(outlet_uuids)+10);
			String commonInBill = year+outlet_uuid;
			long presentBillNo = Long.valueOf(maxBillNoString.replace(commonInBill,""));
			
		    System.out.println("Present available bill no is:"+presentBillNo);
		    long nextBillNo = presentBillNo+1;
		    String nextBillNoString = Long.toString(nextBillNo);
		    String finalBillNoString = commonInBill+nextBillNoString;
		    long finalBillNo = Long.valueOf(finalBillNoString);
		    
		    System.out.println("This is the next Bill no:"+finalBillNo);
		    
		    //System.exit(0);
		    
			return finalBillNo;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;		
	} 
	//***********************end of getbill_NextNumber*******************//

	
	
	public long getestimatebill_nextnumber(HttpSession httpSession) {
		String outlet_uuids = (String) httpSession.getAttribute("Outlet_uuid");
		Session session = null;
		String presentNumberStr = "";
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			System.out.println("hi");
			SQLQuery query = session.createSQLQuery(
					"SELECT count(*)  as count FROM POS_OUTLET_BILLS where bill_type='Estimate Bill' and outlet_uuid = '"
							+ outlet_uuids + "'");
			List<BigInteger> rows = (List) query.list();
			BigInteger presentNumber = rows.get(0);
			System.out.println(presentNumber);
			presentNumberStr = String.valueOf(presentNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String year = "";
		try {
			year = erpFunObj.getLocalDataInfo("current_year");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("year is " + year);
		String outlet_uuid = String.valueOf(Integer.valueOf(outlet_uuids) + 10);
		String commonInBill1 = year + outlet_uuid;
		long presentBillNo = Long.valueOf((String) presentNumberStr.replace(commonInBill1, ""));
		System.out.println("Present available bill no is:" + presentBillNo);
		long nextBillNo = presentBillNo + 1;
		System.out.println("next billno:" + nextBillNo);
		String nextBillNoStr = String.valueOf(nextBillNo);
		String returnStr = "99" + year + outlet_uuid + nextBillNoStr;
		long returnLongNum = Long.valueOf(returnStr);
		System.out.println(returnLongNum);
		return returnLongNum;
	}
	// end of getestimatebill_Nextnumber

	
	public int getFourDigitRandNumber() {
        int min = 1000;
        int max = 10000;
        Random ran = new Random();
        int randomInt = min + ran.nextInt(max - min + 1);
        return randomInt;
    }
	
	private boolean checkValidToken_Credit(String columnName, String randomNumString, HttpSession httpSession) {
		System.out.println("I am in checkValidToken credit");
		String outlet_uuid = (String) httpSession.getAttribute("Outlet_uuid");
		System.out.println(columnName+" ---- "+ randomNumString+" ---- "+outlet_uuid);
		Session session = null;
		try {
		    //session = sessionFactory.getCurrentSession();
			session = sessionFactory.openSession();
		    Criteria criteria = session.createCriteria(Pos_CreditNoteDetails.class);
		    criteria.add(Restrictions.eq(columnName,randomNumString));
		    criteria.add(Restrictions.eq("outlet_uuid",outlet_uuid));	
		    //System.out.println("dgdfgfgdtr :::"+criteria.uniqueResult());
		    return criteria.uniqueResult()!=null;
		    //return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public String generateUniqueToken_Credit(String ColumnName, int billnoLength,HttpSession httpSession) {
		System.out.println("in generate unique token credit");
		System.out.println(ColumnName);
		System.out.println(billnoLength);
		int min = 9999;
		int max = 98888;
		System.out.println("function generate token");
		Random ran = new Random();
		int randomInt = min + ran.nextInt(max - min + 1);
		String randomNumString = Integer.toString(randomInt);
		System.out.println("randomNumStringrandomNumString ::::"+randomNumString);
		boolean existence = false;
		try {
			existence = checkValidToken_Credit(ColumnName,randomNumString,httpSession);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("checking existence of the token:"+existence);
		if(existence == true){
			return generateUniqueToken_Credit(ColumnName,billnoLength,httpSession);
		}else {				
			return randomNumString;
		}
	}
	

}//class

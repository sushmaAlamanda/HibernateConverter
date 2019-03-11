package com.ramersoft.pos.ui.dao;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transaction;

import org.apache.tomcat.util.buf.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ramersoft.pos.entities.ERP_Outlets;
import com.ramersoft.pos.entities.Pos_CreditNoteDetails;
import com.ramersoft.pos.entities.Pos_Outlet_BTOBOrders;
import com.ramersoft.pos.entities.Pos_Outlet_Billpark_Items;
import com.ramersoft.pos.entities.Pos_Outlet_Bills;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Amounts;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Invoice;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Items;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Transaction;
import com.ramersoft.pos.entities.Pos_Outlet_PreOrders;
import com.ramersoft.pos.entities.Pos_Outlet_ProformaBills;
import com.ramersoft.pos.enums.BTOBOrders_status;
import com.ramersoft.pos.enums.Bom_Invoice_Status;
import com.ramersoft.pos.enums.ERP_Outlets_Status;
import com.ramersoft.pos.enums.Pos_outlet_billpark_items_status;
import com.ramersoft.pos.enums.PreOrders_OrderStatus;
import com.ramersoft.pos.ui.beans.Billpark_Details_Bean;
import com.ramersoft.pos.ui.beans.CategoriesBean;
import com.ramersoft.pos.ui.beans.DeliveryOrdersBean;
import com.ramersoft.pos.ui.beans.ItemsBean;
import com.ramersoft.pos.ui.beans.OrdersDataBean;
import com.ramersoft.pos.ui.beans.PartialViewItemsBean;
import com.ramersoft.pos.ui.beans.ReturnViewBean;
import com.ramersoft.pos.ui.libs.BillsRandomGenerations;
import com.ramersoft.pos.ui.libs.Constants;
import com.ramersoft.pos.ui.libs.ERPFunctions;
import com.ramersoft.pos.ui.service.BillsCommonFnsService;

@Repository
public class OrdersDaoImpl implements OrdersDao {
 
	 @Autowired
	 ERPFunctions erpFunObj;
	 
	 @Autowired
	 PosDaoImpl posDaoObj;
	 
	
	 @Autowired
     BillsRandomGenerations billsRandFuncts; 
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
    BillsCommonFnsService commonMethodsObj;


	Transaction tx;

	@Override
	public void generatePreOrderBill() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Billpark_Details_Bean> getBillParkData(String outlet_uuid, String user_uuid,Date dayStartTime,Date dayEndTime) {
		System.out.println(outlet_uuid+" "+user_uuid+""+dayStartTime+" "+dayEndTime);
		
		
		Session session = null;
		
		List<Billpark_Details_Bean> billParkList = new  ArrayList<Billpark_Details_Bean>();
		try {
			/****************** By using criteria created_date is not coming*******************/
			/*session = sessionFactory.getCurrentSession();
			session.beginTransaction();
		    ProjectionList projList = Projections.projectionList();
		    Criteria criteria = session.createCriteria(Pos_Outlet_Billpark_Items.class);
		    projList.add(Projections.distinct(Projections.property("phone_number")));
		    projList.add(Projections.property("created_date"));
		    	    
		    criteria.add(Restrictions.ge("created_date", dayStartTime));
		    criteria.add(Restrictions.le("created_date", dayEndTime));
		    criteria.add(Restrictions.eq("status", "Active"));
		    criteria.add(Restrictions.eq("outlet_uuid", outlet_uuid));
		    criteria.add(Restrictions.eq("created_by", user_uuid));
		    criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		    
		    
		   // projList.add(Projections.groupProperty("phone_number"));
		   // projList.add(Projections.property("created_date"));
		   // projList.add(Projections.groupProperty("created_date"));
		    
		    criteria.setProjection(projList);
		    
		    List  ordersData = criteria.list();
			Iterator it = ordersData.iterator();
			while (it.hasNext()) {
				Object ob[] = (Object[]) it.next();
				System.out.println(ob[0]+"-----"+ob[1]);
			}*/
		    
		    
		   //Criteria criteria = session.createCriteria(Pos_Outlet_Billpark_Items.class)
							    		/*.setProjection(Projections.distinct(Projections.property("phone_number"))
							    		.setProjection(Projections.property("created_date"))
							    	    //.add(Projections.groupProperty("phone_number")))
							    	    //.add(Projections.groupProperty("created_date"))
							    	    .add(Restrictions.ge("created_date", dayStartTime))
							    	    .add(Restrictions.le("created_date", dayEndTime))
							    	    .add(Restrictions.eq("status", "Active"))
							    	    .add(Restrictions.eq("outlet_uuid", outlet_uuid))
							    	    .add(Restrictions.eq("created_by", user_uuid));*/
		    							//.setResultTransformer(Transformers.aliasToBean(Pos_Outlet_Billpark_Items.class));
		    							//.addOrder(Order.desc("created_date"));
		    							 
		  //  criteria.setProjection(Projections.distinct(projList));
		    //criteria.setProjection(Projections.distinct(projList));
		    
			//List result = criteria.list();
		    
			//System.out.println("result is"+result);
			
		    
	            /*int totalRecords = 0;
		            try {
		            	totalRecords = result.size();
		            	for(int i=0;i<totalRecords;i++) {
		            		Billpark_Details_Bean billPark = new Billpark_Details_Bean();
		            		String phone_number = (String)result.get(i);	                  
			                Date created_date = new Date();
			                System.out.println(phone_number+" ----- "+created_date);
			                billPark.setPhone_number(phone_number);
			                billPark.setCreated_date(created_date);
			                System.out.println("***************************");
				    		System.out.println(billPark.getPhone_number());
				    		System.out.println(billPark.getCreated_date());
				    		System.out.println("***************************");
			                billParkList.add(billPark);
		            	}
		            }catch(Exception e) {
		            	e.printStackTrace();
		            }*/
			/****************** By using criteria created_date is not coming end*******************/
			session=sessionFactory.openSession();
			session.beginTransaction();
			/*Query qry = session.createQuery("select DISTINCT ON (phone_number) phone_number, created_date from pos_outlet_billpark_items a where status=:status and outlet_uuid=:outlet_uuid and created_by=:user_uuid and created_date between :dayStartTime and :dayEndTime");
			qry.setParameter("outlet_uuid", outlet_uuid);
			qry.setParameter("created_by", user_uuid);
			qry.setParameter("status", "Active");
			qry.setTimestamp("dayStartTime", dayStartTime);
			qry.setTimestamp("dayEndTime", dayEndTime);*/
			
			SQLQuery qry = session.createSQLQuery("select DISTINCT ON (phone_number) phone_number ,created_date from pos_outlet_billpark_items  where outlet_uuid = '"+outlet_uuid+"' and created_date >='"+dayStartTime+"' and created_date <='"+dayEndTime+"' and created_by = '"+user_uuid+"' and status = 'Active'");
		    
			List<Object[]> results = (List<Object[]>)qry.list(); 
		    int totalRecords = 0;
            	try {
	            	totalRecords = results.size();
	            }catch(Exception e) {
	            	e.printStackTrace();
	            }
        	      
	            for(Object[] result: results){
	            	Billpark_Details_Bean billPark = new Billpark_Details_Bean();
	            	String phone_number = (String)result[0];	                  
	                Date created_date = (Date)result[1];
	                System.out.println(phone_number+" ----- "+created_date);
	                billPark.setPhone_number(phone_number);
	                billPark.setCreated_date(created_date);
	                billParkList.add(billPark);
	            }
	    
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null)
				 session.close();
		}
		session.close();
			
		return billParkList;
	}
	
	
	
	/* to get bill park items of billparked bill*/
	@Override
	public List<Pos_Outlet_Billpark_Items> getBillParkItems(String outlet_uuid, String user_uuid, String billParkNumber, Date startTime,Date endTime) {
		
		List <Pos_Outlet_Billpark_Items> billparkObjList  = new ArrayList <Pos_Outlet_Billpark_Items>();
		Session session = null;
		
		try {
		System.out.println("In ordersdao impl data is"+outlet_uuid+" "+user_uuid+""+billParkNumber + " "+ endTime+ " "+startTime);
		session = sessionFactory.openSession();
		session.beginTransaction();
		//List<Pos_Outlet_Billpark_Items> billparkObjList = new ArrayList<Pos_Outlet_Billpark_Items>();
		
		Criteria criteria = session.createCriteria(Pos_Outlet_Billpark_Items.class);
		criteria.add(Restrictions.eq("phone_number", billParkNumber));
		criteria.add(Restrictions.ge("created_date", startTime));
		criteria.add(Restrictions.le("created_date", endTime));
		criteria.add(Restrictions.eq("status", Pos_outlet_billpark_items_status.Active));
		criteria.add(Restrictions.eq("outlet_uuid", outlet_uuid));
		criteria.add(Restrictions.eq("created_by", user_uuid));
		
		criteria.setProjection(Projections.projectionList()
			      .add(Projections.property("name"), "name")
			      .add(Projections.property("unit"), "unit")
			      .add(Projections.property("quantity"), "quantity")
			      .add(Projections.property("portion_uuid"), "portion_uuid")
			      .add(Projections.property("discount_perc"), "discount_perc")
			      .add(Projections.property("item_uuid"), "item_uuid")
			      .add(Projections.property("GST_perc"), "GST_perc")
			      .add(Projections.property("price_bd"), "price_bd")
			      .add(Projections.property("size"), "size")
			      );
		criteria.setResultTransformer(Transformers.aliasToBean(Pos_Outlet_Billpark_Items.class));
		billparkObjList = criteria.list();
		System.out.println("criteria is "+criteria.list());
		System.out.println("size is "+billparkObjList.size());
		System.out.println("data is "+billparkObjList);
		
		if(billparkObjList!=null) {
			deleteBillPark(billParkNumber, outlet_uuid, startTime, endTime, user_uuid);
		}
		
		}catch(Exception e) {
			
		}finally {
			session.close();
		}
		return billparkObjList;
	}
	
	
	@Override
	public void deleteBillPark(String billParkNumber, String outlet_uuid,Date dayStartTime,Date dayEndTime,String user_uuid) {
		Session session = sessionFactory.openSession();
		try {
		System.out.println("billParkNumber" + billParkNumber);
		
		String Query = "UPDATE pos_outlet_billpark_items set status =:status where phone_number =:billParkNumber and outlet_uuid =:outlet_uuid and created_date between :startTime and :endTime and created_by =:user_uuid and status ='Active'";
		Query query = session.createQuery(Query);
		query.setParameter("billParkNumber", billParkNumber);
		query.setParameter("outlet_uuid", outlet_uuid);
		query.setTimestamp("startTime", dayStartTime);
		query.setTimestamp("endTime", dayEndTime);
		query.setParameter("user_uuid", user_uuid);
		query.setParameter("status", Pos_outlet_billpark_items_status.InActive);
		
		int result = query.executeUpdate();
		System.out.println("result is "+result);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<DeliveryOrdersBean> getHoursDeliveryData(String hours,String outlet_uuid) {
		System.out.println("I am in orders dao impl hours value is:"+hours);
		
		Date present_time = new Date();
		Date hours_later = null;
		if(!hours.equals("all")) {
			int uptoHours = Integer.parseInt(hours);
			/**
			 * https://stackoverflow.com/questions/3581258/adding-n-hours-to-a-date-in-java
			 */			
			 final long hoursInMillis = 60L * 60L * 1000L;
			 hours_later = new Date(present_time.getTime() + 
	                        (uptoHours * hoursInMillis));
	         //System.out.println("Date is:"+newDate);			
		}
		
		System.out.println("times :"+present_time+" ----- "+hours_later);
		List<DeliveryOrdersBean> deliveryOrdersList = new ArrayList<DeliveryOrdersBean>();
		
		Session session = null;
        try {    	
            session=sessionFactory.openSession();            
            if(session!=null) {
				session.beginTransaction();  
				System.out.println("before query");
			 Pos_Outlet_PreOrders pop = new Pos_Outlet_PreOrders();
				Criteria  criteria  =session.createCriteria(pop.getClass());
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("Delivery_datetime"))
						.add(Projections.property("BillNo"))
						.add(Projections.property("Customer_name"))
						.add(Projections.property("Phonenumber"))
						); 
				criteria.addOrder(Order.desc("uuid"));
			    criteria.add(Restrictions.eq("outlet_uuid",outlet_uuid));
			//  criteria.add(Restrictions.eq("Orderstatus",PreOrders_OrderStatus.Pending));
				if(!hours.equals("all")) {
					criteria.add(Restrictions.between("Delivery_datetime", present_time, hours_later));
				}
				List  ordersData = criteria.list();
				Iterator it = ordersData.iterator();
				while (it.hasNext()) {
					Object ob[] = (Object[]) it.next();
					System.out.println((Date)ob[0]+"-----"+(Long)ob[1]+"-----"+(String)ob[2]+"-----"+(String)ob[3]);
					DeliveryOrdersBean deliveryOrderObj = new DeliveryOrdersBean();
					deliveryOrderObj.setDelivery_datetime((Date)ob[0]);
					deliveryOrderObj.setBillNo((Long)ob[1]);
					deliveryOrderObj.setCustomer_name((String)ob[2]);
					deliveryOrderObj.setPhonenumber((String)ob[3]);
					deliveryOrdersList.add(deliveryOrderObj);
				}	
				
				System.out.println("size of the list is:"+deliveryOrdersList.size());
				System.out.println("*********** list is:"+deliveryOrdersList);
				System.out.println("before second query");
				Criteria  criteria2  =session.createCriteria(Pos_Outlet_BTOBOrders.class);
				criteria2.setProjection(Projections.projectionList()
						.add(Projections.property("delivery_datetime"))
						.add(Projections.property("Billno"))
						.add(Projections.property("customer_name"))
						.add(Projections.property("phonenumber"))
						); 
				criteria2.addOrder(Order.desc("uuid"));
			    criteria2.add(Restrictions.eq("outlet_uuid",outlet_uuid));
			 //   criteria2.add(Restrictions.eq("orderstatus",BTOBOrders_status.pending));
			    if(!hours.equals("all")) {
					criteria2.add(Restrictions.between("delivery_datetime", present_time, hours_later));
				}
				List  ordersData2 = criteria2.list();
				Iterator it2 = ordersData2.iterator();
				while (it2.hasNext()) {
					Object ob[] = (Object[]) it2.next();
					System.out.println((Date)ob[0]+"-----"+(Long)ob[1]+"-----"+(String)ob[2]+"-----"+(String)ob[3]);
					DeliveryOrdersBean deliveryOrderObj = new DeliveryOrdersBean();
					deliveryOrderObj.setDelivery_datetime((Date)ob[0]);
					deliveryOrderObj.setBillNo((Long)ob[1]);
					deliveryOrderObj.setCustomer_name((String)ob[2]);
					deliveryOrderObj.setPhonenumber((String)ob[3]);
					deliveryOrdersList.add(deliveryOrderObj);
				}	
		
				System.out.println("size of the list is:"+deliveryOrdersList.size());
								
				return deliveryOrdersList;
				
            }
        } catch (Exception e) {
            System.out.println("error happend while getting result sub categories data");
            e.printStackTrace();
        }finally {
			if(session != null)
				 session.close();
		}
			
		return null;
	}

	@Override
	public void insertPreOrderDetails(Pos_Outlet_Bom_Amounts tableBillsObj) {
		//System.out.println("In insertPreOrdersDeatils skfjsjkfjsklfjskfjsdkf");
		Session session=null;
		try {
		session=sessionFactory.openSession();
	    session.beginTransaction();
	//	session.getTransaction().begin();
	    session.save(tableBillsObj);
	    /*if ( !session.getTransaction().wasCommitted()) { 
	    	session.beginTransaction().commit();
	    }*/
        session.getTransaction().commit();
		} catch(Exception sqlException) {
			/*if(null != session.getTransaction()) {
			System.out.println("\n.......Transaction Is Being Rolled Back.......");
			session.getTransaction().rollback();
			}*/
			sqlException.printStackTrace();
		} finally {
			session.close();
		}

	}
	
	@Override
	public Boolean getReturndate(String searchBillNo, Date prst_DateTime, Date twoDaysPrev_DateTime) {
		System.out.println("search BillNo is:" + searchBillNo);
		long cmplBillNo = Long.valueOf(searchBillNo);
		System.out.println("long bill no is:" + cmplBillNo + "---pstDate:" + prst_DateTime + "----2days before:"
				+ twoDaysPrev_DateTime);

		Session session = null;
		try {
			// session = sessionFactory.getCurrentSession();
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Pos_Outlet_Bom_Invoice.class);
			criteria.add(Restrictions.eq("Billno", cmplBillNo));
			criteria.add(Restrictions.between("created_date", twoDaysPrev_DateTime, prst_DateTime));
			criteria.setProjection(Projections.rowCount());
			return criteria.uniqueResult() != null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return false;
	}
    
	public String generateReciptReturnView(HttpServletRequest request, HttpSession session) {
		System.out.println("in service of genearate return of dao impl");
		return null;
	}

	/*
	 * =============ToDo: should be in libs package in one common
	 * function=======================
	 */
    
	@Override
	public List<Pos_Outlet_Bom_Invoice> getBillnoInvoiceDetails(String Billno, String outlet_uuid) {
		System.out.println("in daoimpl invoice details");
		System.out.println("billno & outlet no:" + Billno + " " + outlet_uuid);

		Long billno = Long.parseLong(Billno);
		List<Pos_Outlet_Bom_Invoice> bomInvoiceResult = new ArrayList<Pos_Outlet_Bom_Invoice>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Pos_Outlet_Bom_Invoice.class);
			criteria.add(Restrictions.eq("Billno", billno));
			Object result = criteria.uniqueResult();
			Pos_Outlet_Bom_Invoice bomInvoice = (Pos_Outlet_Bom_Invoice) result;
			bomInvoiceResult.add(bomInvoice);
			/*System.out.println("******************");
			System.out.println(bomInvoice.toString());
			System.out.println("******************");*/
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return bomInvoiceResult;
	}
     
	@Override
	public List<Pos_Outlet_Bills> getBillsData(String Billno, String outlet_uuid) {

		System.out.println("in daoimpl getBillsDataaaaaaaaaaaaaa");
		System.out.println("billno & outlet no:" + Billno + " " + outlet_uuid);

		Long billno = Long.parseLong(Billno);
		List<Pos_Outlet_Bills> billsResultList = new ArrayList<Pos_Outlet_Bills>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Pos_Outlet_Bills.class);
			criteria.add(Restrictions.eq("Billno", billno));
			criteria.add(Restrictions.eq("outlet_uuid", outlet_uuid));
			Object result = criteria.uniqueResult();
			Pos_Outlet_Bills billsResult = (Pos_Outlet_Bills) result;
			billsResultList.add(billsResult);
			System.out.println("******************");
			System.out.println(billsResult.toString());
			System.out.println("******************");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return billsResultList;
	}
     
	
	@Override
	public List<Pos_Outlet_Bom_Transaction> getTransactionsData(String Billno, String outlet_uuid) {

		System.out.println("in daoimpl getBillsData");
		System.out.println("billno & outlet no:" + Billno + " " + outlet_uuid);

		Long billno = Long.parseLong(Billno);
		List<Pos_Outlet_Bom_Transaction> bomTransactionList = new ArrayList<Pos_Outlet_Bom_Transaction>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Pos_Outlet_Bom_Transaction.class);
			criteria.add(Restrictions.eq("Billno", billno));
			criteria.add(Restrictions.eq("outlet_uuid", outlet_uuid));
			Object result = criteria.uniqueResult();
			Pos_Outlet_Bom_Transaction bomTransactionResult = (Pos_Outlet_Bom_Transaction) result;
			bomTransactionList.add(bomTransactionResult);
			System.out.println("******************");
			System.out.println(bomTransactionResult.toString());
			System.out.println("******************");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return bomTransactionList;
	} 
    
	@Override
	public List<Pos_Outlet_PreOrders> getPreOrdersData(String Billno, String outlet_uuid) {

		System.out.println("in daoimpl getBillsData");
		System.out.println("billno & outlet no:" + Billno + " " + outlet_uuid);
		Long billno = Long.parseLong(Billno);
		List<Pos_Outlet_PreOrders> preOrdersList = new ArrayList<Pos_Outlet_PreOrders>();
		Session session = null;		
		  try { 
			 session = sessionFactory.getCurrentSession();
			 session.beginTransaction(); Criteria criteria =
			 session.createCriteria(Pos_Outlet_PreOrders.class);
			 criteria.add(Restrictions.eq("BillNo",billno));
			 criteria.add(Restrictions.eq("outlet_uuid",outlet_uuid)); Object result =
			 criteria.uniqueResult();
			 Pos_Outlet_PreOrders preOrdersResult =(Pos_Outlet_PreOrders) result; 
			 preOrdersList.add(preOrdersResult);
			 System.out.println("******************");
		     System.out.println(preOrdersResult.toString());
		     System.out.println("******************");
		     
		  }catch(NullPointerException npe){ 
			  System.out.println("nullpointer exception occured for preorders so there is no record in preorderData with this billno"); 				return preOrdersList; 
		  }catch(Exception e) { 
			  e.printStackTrace();
		  }finally {
			  if(session != null) { 
				  session.close();
              }
		  }
		
		  return preOrdersList;
	}
   
	
	@Override
	public List<ReturnViewBean> getCompleteBillDetails(String Billno,String outlet_uuid){
		
		System.out.println("in daoimpl getBillsDatarrrrrrrrrra");
		System.out.println("billno & outlet no:" + Billno + " " + outlet_uuid);
		Long billno = Long.parseLong(Billno);
		List<ReturnViewBean> returnViewList = new ArrayList<ReturnViewBean>();
		Session session = null;
		int i = 0;
		try {		
			session = sessionFactory.openSession();
			Query qry = session.createQuery(
					"select a.item_uuid,b.hsn_uuid,c.code as hsncode,d.portion_name,a.Billno,b.item_name,a.portion_uuid,a.GST_perc,a.quantity,a.original_price,a.uuid,a.CGST_amt,a.price, a.discount_amt, a.adt_discount_amt "
					+ "from pos_outlet_bom_items a,pos_items b,pos_hsncodes c,pos_unit_portions d,"
					+ "pos_outlet_item_portion_prices e where a.item_uuid = b.uuid and b.hsn_uuid=c.uuid and a.outlet_uuid=:outlet_uuid and d.uuid=e.portion_uuid and a.portion_uuid=e.uuid and a.Billno =:billno");
			qry.setParameter("outlet_uuid", outlet_uuid);
			qry.setParameter("billno", billno);
			// qry.setResultTransformer(Transformers.aliasToBean(ReturnViewBean.class));
			// qry.setResultTransformer(new
			// AliasToBeanResultTransformer(ReturnViewBean.class));

			List<Object[]> results = (List<Object[]>) qry.list();

			// List<ReturnViewBean> results = qry.list();

			int totalRecords = 0;
			try {
				totalRecords = results.size();
			} catch (Exception e) {
				e.printStackTrace();
			}
             
			System.out.println("total records are::::" + totalRecords);
			
            for (Object[] result : results) {
			    ReturnViewBean returnBeanObj = new ReturnViewBean();
				
				String item_uuid = (String) result[0];
				String hsn_uuid = (String) result[1];
				String hsncode = (String) result[2];
				String portion_name = (String) result[3];
				Long Billnos = (Long) result[4];
				String item_name = (String) result[5];
				String portion_uuid = (String) result[6];
				Double GST_perc = (Double) result[7];
				Double quantity = (Double) result[8];
				Double original_price = (Double) result[9];
				String uuid = (String) result[10];
				Double CGST_amt = (Double) result[11];
				Double price = (Double)result[12];
				Double discount_amt = (Double)result[13];
				Double adt_discount_amt = (Double)result[14];
				System.out.println("item_uuid:" + item_uuid + "---hsn_uuid:" + hsn_uuid + "---hsncode:" + hsncode
						+ "--portion_name:" + portion_name + "--Billnos:" + Billnos + "---item_name:" + item_name
						+ "---portion_uuid:" + portion_uuid + "---GST_perc:" + GST_perc + "---quantity:" + quantity
						+ "--original_price:" + original_price + "---uuid:" + uuid + " ---CGST_amt" + CGST_amt+"---price:"+price);
				
				returnBeanObj.setUuid(uuid);
				returnBeanObj.setItem_uuid(item_uuid);
				returnBeanObj.setItem_name(item_name);
				returnBeanObj.setPortion_uuid(portion_uuid);
				returnBeanObj.setPortion_name(portion_name);
				returnBeanObj.setGST_perc(GST_perc);
				returnBeanObj.setBillno(Billnos);
				returnBeanObj.setQuantity(quantity);
				returnBeanObj.setOriginal_price(original_price);
				returnBeanObj.setCGST_amt(CGST_amt);
				returnBeanObj.setHsncode(hsncode);
				returnBeanObj.setPrice(price);
				returnBeanObj.setAdt_discount_amt(adt_discount_amt);
				returnBeanObj.setDiscount_amt(adt_discount_amt);
				System.out.println("********************************");
				System.out.println(returnBeanObj.toString());
				System.out.println("********************************");
				returnViewList.add(i,returnBeanObj);
				i++;
				System.out.println(returnViewList);
			}

			
		} catch (Exception e) {
			System.out.println("error happend while getting bigger query resultttttttttttttt result");
			e.printStackTrace();
		}finally {
			if(session != null) { 
				  session.close();
            }
		}
		
		return returnViewList;
	}
	
	public Double getReturnOrderItemsAmount(String returnIDs,String outlet_uuid){
		System.out.println("**********in getReturnOrderItemsAmount dao impl***********");
		 System.out.println(returnIDs);
		 Double price = 0d;
		 Session session = null;
	        try {
	        	session = sessionFactory.openSession();
	        	/**
	        	 * Add status condition: and status='Active'
	        	 */
	        	String hql = "select sum(price) as returnAmount from pos_outlet_bom_items where outlet_uuid=:outlet_uuid  and uuid in ("+returnIDs+")";
	        	System.out.println("query for return amount is:"+hql);
	        	Query query = session.createQuery(hql);
	        	query.setParameter("outlet_uuid", outlet_uuid);
	        	//query.setParameter("returnUUIDList", uuidInCond);
	        	price = (Double) query.uniqueResult();
	        	System.out.println("Price value is:::::::::::"+price);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
				if (session != null) {
					session.close();
			    }
	        }
	        return price;
	}
	@Override
	public List<Pos_Outlet_Bom_Invoice> get_view_search(HttpServletRequest request, HttpSession sessions,Date startTime,Date endTime) {
		System.out.println("=========in get_view_search dao impl================");
		
		List<Pos_Outlet_Bom_Invoice>  invoiceDetails = new ArrayList<Pos_Outlet_Bom_Invoice>();
		  Session session = null;
		  try {
		        session = sessionFactory.openSession();
		        if(session!=null) {
				session.beginTransaction();  
				Criteria  criteria  =session.createCriteria(Pos_Outlet_Bom_Invoice.class);				
			   // criteria.add(Restrictions.eq("status",Bom_Invoice_Status.Active));
			    criteria.add(Restrictions.between("created_date",startTime,endTime));
			    //criteria.setProjection(Projections.groupProperty("Billno"));
			    criteria.addOrder(Order.asc("Billno"));
			    
				 invoiceDetails = criteria.list();
				System.out.println("invoice details are:"+invoiceDetails);
				session.getTransaction().commit();
				try {
					invoiceDetails.forEach(System.out::println);
				}catch(Exception e) {
					e.printStackTrace();
				}
				//return outlets;
		        }							
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally{
	        	if(session!=null) {
	        		session.close();
	        	}
	        }
		  
		  return invoiceDetails;
	}
	
	
	@Override
	public List<OrdersDataBean> orderDataList(HttpServletRequest request,String outlet_uuid,Date startTime,Date endTime){
		
		System.out.println("start time & end time is:"+startTime+"-"+endTime);
		System.out.println("Outlet_uuid is:"+outlet_uuid);
        List<OrdersDataBean>  invoiceDetailsList = new ArrayList<OrdersDataBean>();
		  Session session = null;
		  try {
		        session = sessionFactory.openSession();
		        if(session!=null) {
				session.beginTransaction();  
				
				Criteria  criteria  =session.createCriteria(Pos_Outlet_Bom_Invoice.class);
				Criteria  criteriaForProForma  =session.createCriteria(Pos_Outlet_ProformaBills.class);
				
				ProjectionList p1=Projections.projectionList();
		        p1.add(Projections.property("Billno"));
		        p1.add(Projections.property("bill_amt"));	
		        p1.add(Projections.property("balance_amt"));
		        p1.add(Projections.property("paid_amt"));
		        p1.add(Projections.property("created_date"));    
		        
		        criteria.setProjection(p1);
			   // criteria.add(Restrictions.eq("status",Bom_Invoice_Status.Active));
			    criteria.add(Restrictions.between("created_date",startTime,endTime));
			    criteria.add(Restrictions.eq("outlet_uuid",outlet_uuid));
			  //criteria.setProjection(Projections.groupProperty("Billno"));
			  //criteria.addOrder(Order.asc("Billno"));
			    
			    criteriaForProForma.setProjection(p1);
			   // criteriaForProForma.add(Restrictions.eq("status",Bom_Invoice_Status.Active));
			    criteriaForProForma.add(Restrictions.between("created_date",startTime,endTime));
			    criteriaForProForma.add(Restrictions.eq("outlet_uuid",outlet_uuid));
			  //  criteriaForProForma.add(Restrictions.isNull("Maintax_Invoice"));
			    criteriaForProForma.add(Restrictions.eq("Maintax_Invoice",0L));
			    //criteriaForProForma.addOrder(Order.asc("Billno"));
			    
			    
				// invoiceDetails = criteria.list();
			    List<Object[]> invoiceDetails  =  (List<Object[]>)criteria.list();
			    List<Object[]> proFormaDetails = (List<Object[]>)criteriaForProForma.list();
			    
			    for (Object[] result : invoiceDetails) {
			    	OrdersDataBean ordersBeanObj = new OrdersDataBean();
			    	ordersBeanObj.setBillno((Long)result[0]);
			    	ordersBeanObj.setBill_amt((Double)result[1]);
			    	ordersBeanObj.setBalance_amt((Double)result[2]);
			    	ordersBeanObj.setPaid_amt((Double)result[3]);
			    	ordersBeanObj.setCreated_date((Date)result[4]);
			    	System.out.println(ordersBeanObj.toString());
			    	invoiceDetailsList.add(ordersBeanObj);
			    }
			    
			  /*  try {
			    invoiceDetailsList.addAll(mapperForOrdersData(invoiceDetails));
			    invoiceDetailsList.addAll(mapperForOrdersData(proFormaDetails));
			    }catch(Exception e) {
			    	System.out.println("Problem in the mapper for OrderDetails");
			    	e.printStackTrace();
			    }*/
			    // invoiceDetails = criteria.list();
			    
				
			    for (Object[] result : proFormaDetails) {
			    	OrdersDataBean ordersBeanObj = new OrdersDataBean();
			    	ordersBeanObj.setBillno((Long)result[0]);
			    	ordersBeanObj.setBill_amt((Double)result[1]);
			    	ordersBeanObj.setBalance_amt((Double)result[2]);
			    	ordersBeanObj.setPaid_amt((Double)result[3]);
			    	ordersBeanObj.setCreated_date((Date)result[4]);
			    	System.out.println(ordersBeanObj.toString());
			    	invoiceDetailsList.add(ordersBeanObj);
			    }
			  	    
			    
				/*for(Object me: invoiceDetails1) {
					OrdersDataBean you = (OrdersDataBean)me;
					System.out.println(you.toString());
					invoiceDetails.add(you);
				}*/
				
			    System.out.println("invoice details are:"+invoiceDetails);
				session.getTransaction().commit();
				try {
					invoiceDetails.forEach(System.out::println);
				}catch(Exception e) {
					e.printStackTrace();
				}
				//return outlets;
		        }							
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally{
	        	if(session!=null) {
	        		session.close();
	        	}
	        }
		  
		  
		  try {
              System.out.println("=================trying to sort the invoiceDetailsList objects============================");
              //lambda expression
              invoiceDetailsList.sort(Comparator.comparing(o -> o.getCreated_date(),Comparator.reverseOrder()));
             // invoiceDetailsList.forEach(System.out::println);
              System.out.println("==========================================================================================");
          }catch(Exception e) {
        
          }
		  
		  
		  return invoiceDetailsList;
        
	}
	
	
	public  List<OrdersDataBean> mapperForOrdersData(List<Object[]> Details){
		
		List<OrdersDataBean>  invoiceDetailsList = new ArrayList<OrdersDataBean>();
		 
		for (Object[] result : Details) {
	    	OrdersDataBean ordersBeanObj = new OrdersDataBean();	    	
	    	System.out.println((Long)result[0]+" "+(Double)result[1]+" "+(Double)result[2]+" "+(Date)result[4]);
	    	ordersBeanObj.setBillno((Long)result[0]);
	    	ordersBeanObj.setBill_amt((Double)result[1]);
	    	ordersBeanObj.setBalance_amt((Double)result[2]);
	    	ordersBeanObj.setPaid_amt((Double)result[3]);
	    	ordersBeanObj.setCreated_date((Date)result[4]);
	    	System.out.println(ordersBeanObj.toString());
	    	invoiceDetailsList.add(ordersBeanObj);
	    }
	    		
		return invoiceDetailsList;
	}

	@Override
	public List<Pos_Outlet_ProformaBills> getProFormaBillsData(String Billno, String outlet_uuid) {
		System.out.println("in daoimpl getBillsData");
		System.out.println("billno & outlet no:" + Billno + " " + outlet_uuid);
		Long billno = Long.parseLong(Billno);
		List<Pos_Outlet_ProformaBills> proFormaBillsList = new ArrayList<Pos_Outlet_ProformaBills>();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Pos_Outlet_ProformaBills.class);
			criteria.add(Restrictions.eq("Billno", billno));
			criteria.add(Restrictions.eq("outlet_uuid", outlet_uuid));
			//Object result = criteria.uniqueResult();
			proFormaBillsList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return proFormaBillsList;
	}

	@Override
    public double getTotalTransactionSum(Date startTime,Date endTime) {
        System.out.println("***********In getTotalTransactionSum dao impl");            
        double amount = 0d;
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Pos_Outlet_Bom_Transaction.class);
            criteria.setProjection(Projections.sum("amount"));
            criteria.add(Restrictions.between("created_date", startTime, endTime));
            List list = criteria.list();
            session.getTransaction().commit();
            amount = (double)list.get(0);
        }catch(NullPointerException npe) {
            System.out.println("NullPointer Exception caught for getTotalTransactionSum");
            npe.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(session != null)
               session.close();
        }
                
        return amount;
    }

	@Override
	public List<Pos_Outlet_BTOBOrders> getbtobOrdersData(String Billno, String outlet_uuid) {
		System.out.println("in daoimpl getBillsData");
		System.out.println("billno & outlet no:" + Billno + " " + outlet_uuid);
		Long billno = Long.parseLong(Billno);
		List<Pos_Outlet_BTOBOrders> btobOrdersList = new ArrayList<Pos_Outlet_BTOBOrders>();
		Session session = null;		
		  try { 
			 session = sessionFactory.getCurrentSession();
			 session.beginTransaction(); 
			 Criteria criteria = session.createCriteria(Pos_Outlet_BTOBOrders.class);
			 criteria.add(Restrictions.eq("Billno",billno));
			 criteria.add(Restrictions.eq("outlet_uuid",outlet_uuid)); 
			 Object result = criteria.uniqueResult();
			 Pos_Outlet_BTOBOrders btobOrdersResult =(Pos_Outlet_BTOBOrders) result; 
			 btobOrdersList.add(btobOrdersResult);
			 System.out.println("******************");
		     System.out.println(btobOrdersResult.toString());
		     System.out.println("******************");
		     
		  }catch(NullPointerException npe){ 
			  System.out.println("nullpointer exception occured for preorders so there is no record in preorderData with this billno"); 				
			  return btobOrdersList; 
		  }catch(Exception e) { 
			  e.printStackTrace();
		  }finally {
			  if(session != null) { 
				  session.close();
              }
		  }
		
		  return btobOrdersList;
	}
	
	
	public long getReturnOrderItemsBillNo(String returnIDs,String outlet_uuid){
		System.out.println("**********in getReturnOrderItemsAmount dao impl***********");
		 System.out.println(returnIDs);
		 long Billno = 0l;
		 Session session = null;
	        try {
	        	session = sessionFactory.openSession();
	        	String hql = "select Billno as Billno from pos_outlet_bom_items where outlet_uuid=:outlet_uuid and uuid in ("+returnIDs+")";
	        	Query query = session.createQuery(hql);
	        	query.setParameter("outlet_uuid", outlet_uuid);
	        	//query.setParameter("returnUUIDList", uuidInCond);
	        	Billno = (long) query.uniqueResult();
	        	System.out.println("Billno value is:::::::::::"+Billno);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
				if (session != null) {
					session.close();
			    }
	        }
	        return Billno;
	}
	
	
	public Double getReturnOrderItemsPrice(long Billno,String outlet_uuid){
		System.out.println("**********in getReturnOrderItemsAmount dao impl***********");
		 System.out.println(Billno);
		 Double price = 0d;
		 Session session = null;
	        try {
	        	session = sessionFactory.openSession();
	        	// and status = returned should add after 
	        	String hql = "select sum(price) as returnAmount from pos_outlet_bom_items where outlet_uuid=:outlet_uuid and Billno="+Billno;
	        	Query query = session.createQuery(hql);
	        	query.setParameter("outlet_uuid", outlet_uuid);
	        	//query.setParameter("returnUUIDList", uuidInCond);
	        	price = (Double) query.uniqueResult();
	        	System.out.println("Price value is:::::::::::"+price);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
				if (session != null) {
					session.close();
			    }
	        }
	        return price;
	}
	
	public double getPtoformaBillsBalanceamount(long Billno){
		System.out.println("**********in getReturnOrderItemsAmount dao impl***********");
		 System.out.println(Billno);
		 Double bal_amount = 0d;
		 Session session = null;
	        try {
	        	session = sessionFactory.openSession();
	        	String hql = "select balance_amt as returnAmount from pos_outlet_proformabills "
	        				+ "where (Maintax_Invoice=:maintaxInvoice) OR (Billno="+Billno+")";
	        	Query query = session.createQuery(hql);
	        	query.setParameter("maintaxInvoice", Billno);
	        	bal_amount = (Double) query.uniqueResult();
	        	System.out.println("bal_amount value is:::::::::::"+bal_amount);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
				if (session != null) {
					session.close();
			    }
	        }
	        return bal_amount;
	}

	
	@Override
	public List<PartialViewItemsBean> getOrderItemsModel(Long billno,String outlet_uuid) {
		
		List<PartialViewItemsBean> returnPartialItemsList = new ArrayList<PartialViewItemsBean>();
		Session session = null;
		int i = 0;
		try {		
			session = sessionFactory.openSession();
			
			String qryHql = "select bi.quantity,bi.price_bg,bi.portion_uuid,bi.Billno,bi.discount_perc,i.item_name,bi.item_uuid,bi.GST_perc,bi.size,bi.price,bi.price_bd,up.portion_name from pos_outlet_bom_items bi,pos_items i,pos_outlet_item_portion_prices pp,pos_unit_portions up where bi.item_uuid=i.uuid and pp.uuid=bi.portion_uuid and pp.portion_uuid=up.uuid  and bi.Billno=:billno and bi.outlet_uuid =:outlet_uuid ";
			Query qry = session.createQuery(qryHql);
		    /*---and bi.status='InActive' ----*/
			qry.setParameter("outlet_uuid", outlet_uuid);
			qry.setParameter("billno", billno);
		    
			System.out.println("Qry for getOrderItemsModel is:::::"+qryHql);
			List<Object[]> results = (List<Object[]>) qry.list();

			// List<ReturnViewBean> results = qry.list();

			int totalRecords = 0;
			try {
				totalRecords = results.size();
			} catch (Exception e) {
				e.printStackTrace();
			}
             
			System.out.println("total records are::::" + totalRecords);
			
            for (Object[] result : results) {
            	PartialViewItemsBean partialBeanObj = new PartialViewItemsBean();
            	//String item_name = (String) result[5];
            	Double quantity = (Double) result[0];
            	Double price_bg = (Double)result[1];
            	String portion_uuid = (String) result[2];
            	Long Billnos = (Long) result[3];
            	Double discount_perc = (Double)result[4];
            	String item_name = (String) result[5];
				String item_uuid = (String) result[6];
				Double GST_perc = (Double) result[7];
				Double size = (Double) result[8];
				Double price= (Double)result[9];
				Double price_bd = (Double)result[10];
				String portion_name = (String) result[11];
				
				/*String hsn_uuid = (String) result[1];
				String hsncode = (String) result[2];
				*/
				
				
				
			/*	Double GST_perc = (Double) result[7];
				
				Double original_price = (Double) result[9];
				String uuid = (String) result[10];
				Double CGST_amt = (Double) result[11];
				
				Double discount_amt = (Double)result[13];
				Double adt_discount_amt = (Double)result[14];*/
				System.out.println("item_uuid:" + item_uuid + "---price_bg:" + price_bg + "---discount_perc:" + discount_perc
						+ "--portion_name:" + portion_name + "--Billnos:" + Billnos + "---item_name:" + item_name
						+ "---portion_uuid:" + portion_uuid + "---GST_perc:" + GST_perc + "---quantity:" + quantity
						+ "--size:" + size + "---item uuid:" + item_uuid + " ---CGST_perc" + GST_perc+"---price:"+price+" -----price_bd"+price_bd);
				
				partialBeanObj.setQuantity(quantity);
				partialBeanObj.setPrice_bg(price_bg);
				partialBeanObj.setPortion_uuid(portion_uuid);
				partialBeanObj.setBillno(Billnos);
				partialBeanObj.setDiscount_perc(discount_perc);
				partialBeanObj.setItem_name(item_name);
				partialBeanObj.setItem_uuid(item_uuid);
				partialBeanObj.setGST_perc(GST_perc);
				partialBeanObj.setSize(size);
				partialBeanObj.setPrice(price);
				partialBeanObj.setPrice_bd(price_bd);
				partialBeanObj.setPortion_name(portion_name);
				
				/*partialBeanObj.setUuid(uuid);
				partialBeanObj.setOriginal_price(original_price);
				partialBeanObj.setCGST_amt(CGST_amt);
				partialBeanObj.setHsncode(hsncode);
				partialBeanObj.setPrice(price);
				partialBeanObj.setAdt_discount_amt(adt_discount_amt);
				partialBeanObj.setDiscount_amt(adt_discount_amt);*/
				System.out.println("********************************");
				System.out.println(partialBeanObj.toString());
				System.out.println("********************************");
				returnPartialItemsList.add(partialBeanObj);
				i++;
				System.out.println(returnPartialItemsList);
			}

			
		} catch (Exception e) {
			System.out.println("error happend while getting getOrderItemsModel");
			e.printStackTrace();
		}finally {
			if(session != null) { 
				  session.close();
            }
		}
		
		return returnPartialItemsList;

	}

	@Override
	public Double getBalanceAmount(Long billno, String outlet_uuid) {
	      
		
		Double balanceAmount = 0d;
		Session session = null;
		try {
			String Billno = String.valueOf(billno);
			String billNoSubStr = Billno.substring(0,2);
			String hql = "";
			String table = "";
			if(billNoSubStr.equals("88")) {
				table = "pos_outlet_proformabills";
				//hql = "select balance_amt from pos_outlet_proformabills where Billno=:billNo and outlet_uuid=:outlet_uuid  and status = 'Active'";
			}else {
				table = "pos_outlet_bom_invoice";
			}
			hql = "select balance_amt from "+table+" where Billno=:billNo and outlet_uuid=:outlet_uuid  ";
			/**
			 * add condition ----> and status = 'Active'
			 */
			System.out.println("query for getBalanceAmount is:::::"+hql);
			session = sessionFactory.openSession();
        	/*String hql = "select balance_amt as returnAmount from pos_outlet_proformabills "
        				+ "where (Maintax_Invoice=:maintaxInvoice) OR (Billno="+Billno+")";*/
        	Query query = session.createQuery(hql);
        	query.setParameter("billNo", billno);
        	query.setParameter("outlet_uuid", outlet_uuid);
        	balanceAmount = (Double) query.uniqueResult();
        	System.out.println("bal_amount value in getBalanceAmount:::::::::::"+balanceAmount);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		 return balanceAmount;
	}
		
	@Override
	public String getCreditNoteAmount(HttpServletRequest request, String returnIDs, String creditnoteid_value,HttpSession httpSession) {
		
		Long creditnoteid_valueLong = Long.valueOf(creditnoteid_value);
		int count = 0;
		String upcean_code = "";
		String items_grid = "";
		String linux_grid = "";
		
		System.out.println("**********in getCreditNoteAmount dao implimentation***********");
		 System.out.println(returnIDs);
		 LocalDateTime now = LocalDateTime.now();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    String presentDateTime = now.format(formatter);
		 String outlet_uuid = (String) httpSession.getAttribute("Outlet_uuid");
		 String user_uuid = (String) httpSession.getAttribute("User_uuid");
		 System.out.println("-----updated_time------"+presentDateTime+"-----outlet_uuid-----"+outlet_uuid+"------user_uuid-----"+user_uuid);
		 Long generatedbill=0L;
		 String EndResult = "";
		 String RandomCredit = "";
		 Session session = null;
		 try {
		 session = sessionFactory.openSession();
		 String creditqry = " select uuid from pos_outlet_creditnotedetails where generatedbill='"+creditnoteid_value+"' and status='Active' ";
		 String creditquery = " select uuid from pos_outlet_creditnotedetails where generatedbill=:creditnoteval and status='Active' ";
		 System.out.println(creditqry);
		 Query query = session.createQuery(creditquery);
		 query.setParameter("creditnoteval",creditnoteid_valueLong);
		 
		  List I = query.list();
		 
          Iterator it = I.iterator();
    
          while(it.hasNext()) {
              Object resultObjects = it.next();
             String generatedbills = (String) resultObjects;
             System.out.println("generatedbills ::::::"+generatedbills);
             
          }
		 
		  count = I.size();
         
		 System.out.println("**************************************Hello****************************************************************");

		  //generatedbill = (Long) query.uniqueResult();
		// String uuid = (String) query.uniqueResult();
		// System.out.println( "MAx Results : " +query.getMaxResults());
     	 //System.out.println("bal_amount value is:::::::::::"+generatedbill);
     	//  mygeneratedbill = Long.valueOf((String) generatedbill);
     	 // System.out.println("mygeneratedbill :::"+mygeneratedbill);
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 System.out.println("----------------count--------------"+count);
		if(count > 0) {
			 RandomCredit = "creditnote used";
     		 return RandomCredit;
     	 }
     	 else {
     		try {
     			RandomCredit   = billsRandFuncts.generateUniqueToken_Credit("random_credit",Constants.BILLNO_LENGTH, httpSession);
     			System.out.println("setting random_credit..........."+RandomCredit);
     			session = sessionFactory.openSession();
     			
     			Query qry = session.createQuery(
    					"select sum(price) as ReturnAmount,(outlet_uuid) as OutletID,(Billno) as Billno,bills_uuid from pos_outlet_bom_items where outlet_uuid =:outlet_uuid  and status = 'Active' and uuid IN("+returnIDs+") GROUP BY Billno,bills_uuid,outlet_uuid ");
    			qry.setParameter("outlet_uuid", outlet_uuid);
    			List<Object[]> results = (List<Object[]>) qry.list();
    			// List<ReturnViewBean> results = qry.list();
    			Double returnamount = null;
    			String outlet_id = null;
    			Long billno = null;
    			String bills_uuid = null;
    			int totalRecords = 0;
    			try {
    				totalRecords = results.size();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
                 
    			System.out.println("total records are::::" + totalRecords);
    			
                for (Object[] result : results) {
    			     returnamount = (Double) result[0];
    				 outlet_id = (String) result[1];
    				 billno = (Long) result[2];
    				 bills_uuid = (String) result[3];
                }
                System.out.println("returnamount:" + returnamount + "---outlet_id:" + outlet_id + "---hsncode:"+billno+"-----"+bills_uuid);
                List<ItemsBean> pos_itemsBeanlist = new ArrayList<ItemsBean>();	
                session.beginTransaction();
                /*String second = "select b.item_name as item_name,b.hsn_uuid as HSNID,c.code as hsncode,tbl.portion_name,tbl.portion_alias from "
		                +" pos_outlet_bom_items a left join pos_items b on a.item_uuid = b.uuid left join pos_hsncodes c on b.hsn_uuid = c.uuid left join ( select a.uuid as OutletItemPortionPriceID,a.portion_uuid as PortionID,b.portion_name,b.portion_alias from "
						+"pos_outlet_item_portion_prices a  left join pos_unit_portions b on a.portion_uuid = b.uuid  where true ) as tbl on a.portion_uuid = tbl.OutletItemPortionPriceID where a.outlet_uuid = '"+outlet_uuid+"' and a.Billno = '"+billno+"' and a.uuid IN("+returnIDs+")";
				System.out.println(second);*/
				/*Query qry2 = session.createSQLQuery("select a.\"GST_perc\" as gst_perc ,a.quantity,a.\"CGST_amt\" as cgst_amt,a.price,a.adt_discount_amt,a.discount_amt,a.original_price,b.item_name as item_name,b.hsn_uuid as HSNID,c.code as hsncode,tbl.portion_name,tbl.portion_alias from "
		                +" pos_outlet_bom_items a, pos_items b, pos_hsncodes c, ( select a.uuid as OutletItemPortionPriceID,a.portion_uuid as PortionID,b.portion_name,b.portion_alias from "
						+"pos_outlet_item_portion_prices a  , pos_unit_portions b  where true ) as tbl where  a.portion_uuid = tbl.OutletItemPortionPriceID and a.portion_uuid = b.uuid and b.hsn_uuid = c.uuid and a.item_uuid = b.uuid and a.outlet_uuid = '"+outlet_uuid+"' and a.\"Billno\" = '"+billno+"' and a.uuid IN ("+returnIDs+")");
				List<Object[]> results1 = (List<Object[]>) qry2.list();*/
				
				Query qry1 = session.createQuery(
						"select a.GST_perc ,a.quantity,a.CGST_amt,a.price,a.adt_discount_amt,a.discount_amt,a.original_price "
						+ "from pos_outlet_bom_items a,pos_items b,pos_hsncodes c,pos_unit_portions d,"
						+ "pos_outlet_item_portion_prices e where a.item_uuid = b.uuid and b.hsn_uuid=c.uuid and a.outlet_uuid='"+outlet_uuid+"' and d.uuid=e.portion_uuid and a.portion_uuid=e.uuid and a.Billno =:billno and a.uuid IN ("+returnIDs+")");
				
				qry1.setParameter("billno", billno);
				
				List<Object[]> results1 = (List<Object[]>) qry1.list();
				
				int totalRecords1 = 0;
    			try {
    				totalRecords1 = results1.size();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    			
    			List <Pos_Outlet_Bom_Items> bomItemsObjList = new ArrayList<Pos_Outlet_Bom_Items>();
    			
    			
    			for(Object[] result: results1){
    				
    				Pos_Outlet_Bom_Items bomitems = new Pos_Outlet_Bom_Items();
    				double gst_perc = (double) result[0];
    				double cgst_perc  = gst_perc / 2;
    				bomitems.setGST_perc(gst_perc);
    	        	bomitems.setCGST_perc(cgst_perc);
    	        	bomitems.setQuantity((double) result[1]);
    	        	bomitems.setCGST_amt((double) result[2]);
    	        	bomitems.setPrice((double) result[3]);
    	        	bomitems.setAdt_discount_amt((double) result[4]);
    	        	bomitems.setDiscount_amt((double) result[5]);
    	        	bomitems.setOriginal_price((double) result[6]);
    	        	
    	        	System.out.println("**********"+bomitems.toString());
    	        	
    	        	bomItemsObjList.add(bomitems);
    			}
    			
    		
    			System.out.println("total records are::::" + totalRecords1);
				//System.out.println(second);
    			String updateqry = "UPDATE pos_outlet_bom_items set status='Returned' , outlet_uuid='"+outlet_uuid+"' , updated_date ='"+presentDateTime+"' , updated_by='"+user_uuid+"' WHERE uuid IN ("+returnIDs+")";
    			System.out.println(updateqry);
    			Query query = session.createQuery(updateqry);
    			int result = query.executeUpdate();
    			System.out.println("update result is "+result);
    			String uuid_credit = outlet_uuid+erpFunObj.uniqueRandAndTimeStamp();
    			Pos_CreditNoteDetails  tableCreditNoteObj = new Pos_CreditNoteDetails();
    			Long randomCredit = Long.valueOf(RandomCredit);
    			tableCreditNoteObj.setCreditamount(returnamount);
    			tableCreditNoteObj.setGeneratedbill(billno);
    			tableCreditNoteObj.setBills_uuid(bills_uuid);
    			tableCreditNoteObj.setCreated_by(user_uuid);
    			tableCreditNoteObj.setCreated_date(new Date());
    			tableCreditNoteObj.setOutlet_uuid(outlet_uuid);
    			tableCreditNoteObj.setRandom_credit(randomCredit);
    			tableCreditNoteObj.setUuid(uuid_credit);
    			System.out.println("the obj is"+tableCreditNoteObj.toString());
    			System.out.println("*****************************************");
    			
    			
    			try {
    				 
	    			session.save(tableCreditNoteObj);
	    			session.getTransaction().commit();
	    			System.out.println("********Tables successfully commited*************** ");
	    			
	    			/***********windows print****************/
	    			
	    			String outlet_uuids = String.valueOf(Integer.valueOf(outlet_uuid)+10);
	    			String randomCredit1 = randomCredit.toString();
	    			String credit_autoid = org.apache.commons.lang.StringUtils.leftPad(randomCredit1, 6, "0");
	    			String creditnote_check = "9999";
	    	        upcean_code = outlet_uuids + credit_autoid + creditnote_check;
	    			
	    	        
	    	        int sum = 0;
	    	        for (int i = (upcean_code.length() - 1); i >= 0; i--) {
	    	            sum += ((i % 2) * 2 + 1 ) * upcean_code.charAt(i);
	    	        }
	    	        Object checksum = (sum % 10)==0?0:(10 - (sum % 10));
	    	        upcean_code = upcean_code + checksum;
	    	        
	    	        System.out.println("**upcean_code is***"+upcean_code);
	    	        
	    		    int btobordresCount = 0; 
	    			String gst_details = commonMethodsObj.gstDetails(bomItemsObjList, btobordresCount);
	                String[] gst_parts = gst_details.split("@",2);
	    		    items_grid += gst_parts[0];
	    		    
	    		    /************Linux print*******************/
	    		    
	    		    ERP_Outlets outletName = null;
	    		    try {
	    		    	outletName = posDaoObj.getOutletName(request, httpSession);
	    		    }catch(Exception e) {
	    		    	e.printStackTrace();
	    		    }
	    		    
	    		    
	    		    linux_grid += "UTC CODE:"+upcean_code+"\n";
	    		    linux_grid += (char)27+"h"+(char)0; // height
	    		    linux_grid += (char)27+"w"+(char)4; // width
	    		    linux_grid += Constants.center+(char)29+"k"+(char)2+upcean_code+"\0"+(char)0+"\n";  //barcode printing 
	    		    
	    		    
	    		    linux_grid += Constants.left+"Date:"+new Date()+"\n";
	    		    linux_grid += Constants.left+"Credit Note #"+credit_autoid+"\n"+outletName.getGSTtinnumber()+"\n";
	    		    linux_grid += Constants.left+"Outlet Address:"+outletName.getAddress()+"\n";
	    		    linux_grid += gst_parts[1];
	    		    linux_grid += "------------------------------------------\n";
	    		    linux_grid += "Terms &conditions:The promotional discount is only available\n" + 
	    		    			  "to individual consumers for products purchased in person at any\n" + 
	    		    			  "conference worldwide. \n";
	    		    linux_grid += Constants.cutpaper;
	    			
	    		    String os_type = erpFunObj.findOs(request);
	  	    	    String ipAddress = request.getRemoteAddr();
	  	            System.out.println("**Requsted system IP Address : ***" + 
	  	                             ipAddress+"*************"); 
	  	            
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
	    		    	  	            
	    			
    			}catch (Exception sqlException) {
    				if (null != session.getTransaction()) {
    					System.out.println("Transaction Is Being Rolled Backed");
    					session.getTransaction().rollback();
    					items_grid = "Failed";
    					return items_grid;
    				}
    			}
     		}
			catch(Exception e) {
				e.printStackTrace();
			}finally {
				session.close();
			}
     		 
     	 }
		System.out.println("-----------------Random credit is-----------"+RandomCredit);
		 return upcean_code+"||||"+items_grid;
	 }
	
	@Override
	public double getPreviousPriceForThisItemPortion(String outlet_uuids, String item_uuid, String key) {
		
			
		Double prevPrice = 0d;
		Session session = null;
		try {
			
	         session=sessionFactory.openSession();
	         String query = "select price from pos_outlet_item_portion_prices where outlet_uuid=:outlet_uuid and item_uuid=:item_uuid and uuid=:key and status='Active'";
	         System.out.println("query is ::"+query);
	            Query qry = session.createQuery(query);
	            qry.setParameter("outlet_uuid", outlet_uuids);
	            qry.setParameter("item_uuid", item_uuid);
	            qry.setParameter("key", key);
	            // resultStrings=(String[])query.uniqueResult();
	             List I = qry.list(); 
	             Iterator it = I.iterator();
	        	 //System.out.println((String) resultObjects[0]);
	             while(it.hasNext()) {
	            	 Object resultObjects = it.next();
	            	 prevPrice = (Double) resultObjects;
	             }

			
		}catch(Exception e) {
			System.out.println("No data found for pricechange in getPreviousPriceForThisItemPortion method(pos_outlet_item_portion_price table)");
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		System.out.println("prevPrice for uuid is:"+prevPrice);
		return prevPrice;
	}

}

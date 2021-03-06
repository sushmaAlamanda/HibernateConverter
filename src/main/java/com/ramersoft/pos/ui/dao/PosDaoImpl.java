package com.ramersoft.pos.ui.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ramersoft.pos.entities.ERPUSERS;
import com.ramersoft.pos.entities.ERP_Outlets;
import com.ramersoft.pos.entities.Pos_Items;
import com.ramersoft.pos.entities.Pos_Outlet_Billpark_Items;
import com.ramersoft.pos.entities.Pos_Outlet_Day_In_Out_Transaction;
import com.ramersoft.pos.entities.Pos_Outlet_Ordertypes;
import com.ramersoft.pos.enums.Pos_Outlet_Ordertypes_Status;
import com.ramersoft.pos.enums.Pos_outlet_billpark_items_status;
import com.ramersoft.pos.ui.beans.CategoriesBean;
import com.ramersoft.pos.ui.beans.ItemsBean;
import com.ramersoft.pos.ui.beans.OrdersDataBean;
import com.ramersoft.pos.ui.beans.OrderstypesBean;
import com.ramersoft.pos.ui.beans.UnitsBean;

@Repository
public class PosDaoImpl implements PosDao{
    
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	Transaction tx;
	
	
	
	/**
	 * Returns a unique result with dayInDetails 
	 */
	@Override
	public Pos_Outlet_Day_In_Out_Transaction getDayInDataForToday(String outlet_uuid, Date startTime,Date endTime) {
		
		Session session = null;
		try {
		    // session = sessionFactory.getCurrentSession();
			session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Pos_Outlet_Day_In_Out_Transaction.class);
			criteria.add(Restrictions.between("created_date", startTime, endTime));
			criteria.setMaxResults(1);
			Object dayInDataList = criteria.uniqueResult();
			Pos_Outlet_Day_In_Out_Transaction dayInTransactionData = (Pos_Outlet_Day_In_Out_Transaction) dayInDataList;		
			return dayInTransactionData;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null)
				 session.close();
		}
		
		return null;
	}  //end of getDayInDataForToday

    
	
	
	/**
	 * inserts the DayInData into  dayInOutTransaction table
	 * Here Pos_Outlet_Day_In_Out_Transaction class is entity that represents pos_outlet_day_in_out_transaction table in database 
	 */
	@Override
	public Long saveDayInDataForToday(Pos_Outlet_Day_In_Out_Transaction dayInDataObj){		
		Long DayInID=null;
		Session session=null;
		try {
		session=sessionFactory.openSession();
	    session.beginTransaction();
	    session.save(dayInDataObj);
        session.getTransaction().commit();
		} catch(Exception sqlException) {
			if(null != session.getTransaction()) {
			System.out.println("\n.......Transaction Is Being Rolled Back.......");
			session.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(session != null)
				 session.close();
		}
		return null;
     } //saveDayInDataForToday

 
	
	
	/**
	 * Gets Main Categories in respected outlet like sweets,Bakery,Hots..... 
	 */	
	@Override
	public List<CategoriesBean> get_categoryData(String outlet_uuid,int page_length,int page_start) {
	
		List<CategoriesBean> mainCategoriesList = new ArrayList<CategoriesBean>();
		Session  session = null;
        try {    	
            session=sessionFactory.openSession();
            Query qry = session.createQuery("select a.uuid,a.category_name from pos_categories a  where a.uuid in (select DISTINCT a.Parent_CategoryID from pos_categories a,pos_outlet_categories b where a.uuid=b.category_uuid and b.outlet_uuid=:outlet_uuid and b.status=:status)  and status=:status  ORDER BY a.CategoryID ASC");
            qry.setParameter("outlet_uuid", outlet_uuid);
            qry.setParameter("status", "Active");
            qry.setFirstResult(page_start);
            qry.setMaxResults(page_length);
     
            List<Object[]> results = (List<Object[]>)qry.list();
            
            int totalRecords = 0;
	            try {
	            	totalRecords = results.size();
	            }catch(Exception e) {
	            	e.printStackTrace();
	            }
            	      
	            
            for(Object[] result: results){	            	
            	CategoriesBean categoryBean = new CategoriesBean();
            	String uuid = (String)result[0];	                  
                String category_name = (String)result[1];	
              //  System.out.println(uuid+" ----- "+category_name);
                categoryBean.setMax_category(totalRecords);
                categoryBean.setUuid(uuid);
                categoryBean.setCategory_name(category_name);
                mainCategoriesList.add(categoryBean);
            }

            return mainCategoriesList;        
        } catch (Exception e) {
            System.out.println("error happend while getting result");
            e.printStackTrace();
        }finally {
			if(session != null)
				 session.close();
		}
			
		return null;
	} //get_categoryData
	

    
	
	
	/**
	 * Gets the subCategories based on the selected Main Category
	 * @Params ---> page_start & page_length used to limit the query results 
	 */	
	@Override
	public List<CategoriesBean> get_SubCategoryData(String outlet_uuid,String category_uuid,int page_start,int page_length) {
			
		List<CategoriesBean> subCategoriesList = new ArrayList<CategoriesBean>();
		Session session = null;
        try {    	
            session=sessionFactory.openSession();
            
            Query qry = session.createQuery("select a.category_uuid as uuid,b.category_name from pos_outlet_categories a , pos_categories b where a.category_uuid = b.uuid and a.outlet_uuid=:outlet_uuid and b.Parent_CategoryID=:parentCat order by b.category_name");
             qry.setParameter("outlet_uuid", outlet_uuid);
             qry.setParameter("parentCat", category_uuid);
             qry.setFirstResult(page_start);
             qry.setMaxResults(page_length);
     
            List<Object[]> results = (List<Object[]>)qry.list();
            
            int totalRecords = 0;
	            try {
	            	totalRecords = results.size();
	            }catch(Exception e) {
	            	e.printStackTrace();
	            }
            	      
	            
            for(Object[] result: results){	            	
            	CategoriesBean categoryBean = new CategoriesBean();
            	String uuid = (String)result[0];	                  
                String category_name = (String)result[1];	
                System.out.println(uuid+" ----- "+category_name);
                categoryBean.setMax_category(totalRecords);
                categoryBean.setUuid(uuid);
                categoryBean.setCategory_name(category_name);
                subCategoriesList.add(categoryBean);
            }

                    
        } catch (Exception e) {
            System.out.println("error happend while getting result sub categories data");
            e.printStackTrace();
        }finally {
			if(session != null)
				 session.close();
		}
		
        return subCategoriesList;
		
	 }//get_SubCategoryData

  
	
	/**
	 *   Gets Items Data with subCategoryID
	 */
	@Override
	public List<ItemsBean> get_AllItemsData(String outlet_uuid,String subCatID,String search_value1) {
		System.out.println("outlet_uuid and subCatID is"+outlet_uuid+"  and "+subCatID+"search_values is:"+search_value1);
		List<ItemsBean> itemsList = new ArrayList<ItemsBean>();
		
		String query = "select distinct(a.uuid) as uuid, a.unit_uuid, a.item_name, d.discount, b.hsn_uuid, c.tax from pos_items a,pos_item_prices b, pos_hsncodes c, pos_outlet_item_portion_prices d where a.uuid = b.item_uuid and c.uuid = b.hsn_uuid and a.uuid = d.item_uuid and a.category_uuid = :subCatID  and d.outlet_uuid = :outlet_uuid  and a.status=:status and b.status=:status and  d.status=:status  order by a.item_name ";
		try {
			if(search_value1!="") {
				String searchStr = "%"+search_value1.toUpperCase()+"%";
				query = "select distinct(a.uuid) as uuid, a.unit_uuid, a.item_name, d.discount, b.hsn_uuid, c.tax from pos_items a,pos_item_prices b, pos_hsncodes c, pos_outlet_item_portion_prices d where a.uuid = b.item_uuid and c.uuid = b.hsn_uuid and a.uuid = d.item_uuid and a.item_name like '"+searchStr+"'  and d.outlet_uuid = :outlet_uuid  and a.status=:status and b.status=:status and  d.status=:status  order by a.item_name";
				/**/
			}
			
		}catch(Exception e) {
			
		}
		
		
		System.out.println("query to fetch items ::::"+query);
		Session session = null;
        try {    	
            session=sessionFactory.openSession();
            
           /* Query qry = session.createQuery("select distinct(a.uuid) as uuid, a.unit_uuid, a.item_name, d.discount, b.hsn_uuid, c.tax from pos_items a,pos_item_prices b, pos_hsncodes c, pos_outlet_item_portion_prices d where a.uuid = b.item_uuid and c.uuid = b.hsn_uuid and a.uuid = d.item_uuid and a.category_uuid = :subCatID  and d.outlet_uuid = :outlet_uuid  and a.status=:status and b.status=:status and  d.status=:status  order by a.item_name ");*/
            Query qry = session.createQuery(query);
            qry.setParameter("outlet_uuid", outlet_uuid.trim());
            try {
            	if(search_value1.equals("")) {
                   qry.setParameter("subCatID", subCatID.trim());
            	}else {
            	  // qry.setParameter("searchKey", MatchMode.ANYWHERE.toMatchString(search_value1));
            	}
            }catch(Exception e) {
            	
            }
            qry.setParameter("status", "Active");
     
            List<Object[]> results = (List<Object[]>)qry.list();
            
            int totalRecords = 0;
	            try {
	            	totalRecords = results.size();
	            }catch(Exception e) {
	            	e.printStackTrace();
	            }
            	      
	            
            for(Object[] result: results){	            	
            	ItemsBean itemBean = new ItemsBean();
            	String uuid = (String)result[0];	                  
                String unit_uuid = (String)result[1];
                String item_name = (String)result[2];
                double discount = (double)result[3];
                String hsn_uuid = (String)result[4];
                int tax = (int)result[5];
               // System.out.println(uuid+" ----- "+unit_uuid+" ------- "+item_name+" ------ "+discount+" ----- "+hsn_uuid+" ----- "+tax);
                itemBean.setUuid(uuid);
                itemBean.setUnit_uuid(unit_uuid);
                itemBean.setItem_name(item_name);
                itemBean.setDiscount(discount);
                itemBean.setHsn_uuid(hsn_uuid);
                itemBean.setTax(tax);
                itemsList.add(itemBean);
            }
    
        } catch (Exception e) {
            System.out.println("error happend while getting result");
            e.printStackTrace();
        } finally {
			if(session != null)
				 session.close();
		}
		
        //itemsList.forEach(System.out::println);		
		return itemsList;
	}

	
	/**
	 * Gets Units Data
	 */
	@Override
	public List<UnitsBean> get_AllUnitsData(String outlet_uuid,String item_uuid,String unit_uuid) {
				    
	     	//System.out.println("get all units data");
			List<UnitsBean> unitsList = new ArrayList<UnitsBean>();
			Session session = null;
	        try {    	
	            session=sessionFactory.openSession();
	            Query qry = session.createQuery("select a.uuid,a.item_uuid,a.portion_uuid,a.outlet_uuid,a.price,c.portion_name,d.unit_name,b.size from pos_outlet_item_portion_prices a, pos_item_price_partitions b, pos_unit_portions c, pos_units d where a.portion_uuid = b.portion_uuid and a.portion_uuid = c.uuid and d.uuid = c.unit_uuid and a.outlet_uuid=:outlet_uuid and a.item_uuid=:item_uuid and b.item_uuid=:item_uuid and d.uuid=:unit_uuid and a.status=:status and b.status='Active' order by c.portion_order");
	            qry.setParameter("outlet_uuid", outlet_uuid);
	            qry.setParameter("item_uuid", item_uuid); 
	            qry.setParameter("unit_uuid", unit_uuid); 
	            qry.setParameter("status", "Active"); 
	     
	            List<Object[]> results = (List<Object[]>)qry.list();
	            int totalRecords = 0;
		            try {
		            	totalRecords = results.size();
		            }catch(Exception e) {
		            	e.printStackTrace();
		            }
	            	      
        	//	System.out.println("total records are:"+totalRecords);   
	           
		        for(Object[] result: results){	
		        	UnitsBean unitBean = new UnitsBean();
	            	String uuid = (String)result[0];	                  
	                String item_uuids = (String)result[1];
	                String portion_uuid = (String)result[2];
	                String outlet_uuids = (String)result[3];
	                double price = (double)result[4];
	                String portion_name = (String)result[5];
	                String unit_name = (String)result[6];
	                double size = (double)result[7];
	              //  System.out.println(uuid+" ----- "+item_uuids+" ------- "+portion_uuid+" ------ "+outlet_uuids+" ----- "+price+" ----- "+portion_name+" ----- "+unit_name+" ------- "+size);
	                unitBean.setUuid(uuid);
	                unitBean.setItem_uuid(item_uuids);
	                unitBean.setPortion_uuid(portion_uuid);
	                unitBean.setOutlet_uuid(outlet_uuids);
	                unitBean.setPrice(price);
	                unitBean.setPortion_name(portion_name);
	                unitBean.setUnit_name(unit_name);
	                unitBean.setSize(size);
	                unitsList.add(unitBean);
	            }   
	    
	        } catch (Exception e) {
	            System.out.println("error happend while getting result in get_AllUnitsData");
	            e.printStackTrace();
	        } finally {
				if(session != null)
					 session.close();
			}
			
	        //unitsList.forEach(System.out::println);	
		 
		return unitsList;
	}
	
	
	@Override
	public String get_generalorderData() {
		System.out.println("*************get all orderssss data*************");
		String general_uuid=null;
		 Session  session = null;
		try { 
            session=sessionFactory.openSession();
            Query qry = session.createQuery("select uuid from pos_outlet_ordertypes where ordertype_name='general'");
            // resultStrings=(String[])query.uniqueResult();
             List I = qry.list(); 
             Iterator it = I.iterator();
        	 //System.out.println((String) resultObjects[0]);
             while(it.hasNext()) {
            	 Object resultObjects = it.next();
            	 general_uuid = (String) resultObjects;
             }
        } catch (Exception e) {
            System.out.println("error happend while getting result in get_ordertypes");
            e.printStackTrace();
        }finally {
			if(session != null)
				 session.close();
		}
		return general_uuid;
	}

	@Override
	public List<OrderstypesBean> get_ordersData() {
		
		List<OrderstypesBean>  OrderstypesBeanList = new ArrayList<OrderstypesBean>();
		Session session = null;
		try {
		
		System.out.println("In ordersdao impl data is");
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Pos_Outlet_Ordertypes.class);
		ProjectionList p1=Projections.projectionList();
	    p1.add(Projections.property("ordertype_autoid"), "ordertype_autoid");
	    p1.add(Projections.property("ordertype_name"), "ordertype_name");
	    p1.add(Projections.property("bck_color"), "bck_color");
	    p1.add(Projections.property("added_perc"), "added_perc");
	    p1.add(Projections.property("uuid"), "uuid");
	    
	    criteria.add(Restrictions.ne("ordertype_name", "general"));
		criteria.add(Restrictions.eq("status", Pos_Outlet_Ordertypes_Status.Active));
		criteria.setProjection(p1);
		
		System.out.println("criteria is "+criteria.list());
		
		List<Object[]> ordertypeDetails  =  (List<Object[]>)criteria.list();
	    
		    for (Object[] result : ordertypeDetails) {
		    	OrderstypesBean OrderstypesBeanObj = new OrderstypesBean();
		    	OrderstypesBeanObj.setOrdertype_autoid((Long)result[0]);
		    	OrderstypesBeanObj.setOrdertype_name((String)result[1]);
		    	OrderstypesBeanObj.setBck_color((String)result[2]);
		    	OrderstypesBeanObj.setAdded_perc((Long)result[3]);
		    	OrderstypesBeanObj.setUuid((String)result[4]);
		    	System.out.println("*********************************************");
		    	System.out.println(OrderstypesBeanObj.toString());
		    	System.out.println("*********************************************");
		    	OrderstypesBeanList.add(OrderstypesBeanObj);
		    }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null)
				 session.close();
		}
		
		return OrderstypesBeanList;
	}

	@Override
	public ERP_Outlets getOutletName(HttpServletRequest request,HttpSession httpSession) {
		Session session = null;
		ERP_Outlets outletData = new ERP_Outlets();
		try {
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(ERP_Outlets.class);
		criteria.add(Restrictions.eq("uuid", httpSession.getAttribute("Outlet_uuid")));
		//criteria.setProjection(Projections.property("b_unit_name"));
		criteria.setMaxResults(1);
		
		//System.out.println("***criterial list is***"+criteria.list());
		
		//String outlet_name  =  (String) criteria.uniqueResult();
		
		Object outletDataList = criteria.uniqueResult();
		 outletData = (ERP_Outlets) outletDataList;	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null)
				 session.close();
		}
		return outletData;
		
	}
	
	 
	@Override
	public int get_totalCategoryDataNo(String outlet_uuid) {
		
		int totalCatgry = 0;
		    Session  session = null;
        try {    	
            session=sessionFactory.openSession();
            Query qry = session.createQuery("select a.uuid,a.category_name from pos_categories a  where a.uuid in (select DISTINCT a.Parent_CategoryID from pos_categories a,pos_outlet_categories b where a.uuid=b.category_uuid and b.outlet_uuid=:outlet_uuid and b.status=:status)  and status=:status  ORDER BY a.CategoryID ASC");
            qry.setParameter("outlet_uuid", outlet_uuid);
            qry.setParameter("status", "Active");
     
            List<Object[]> results = (List<Object[]>)qry.list();
            
          
	            try {
	            	totalCatgry = results.size();
	            }catch(Exception e) {
	            	e.printStackTrace();
	            }
            	  
            return totalCatgry;        
        } catch (Exception e) {
            System.out.println("error happend while getting totalNoMainCatgry result");
            e.printStackTrace();
        } finally {
			if(session != null)
				 session.close();
		}
		
			
		return totalCatgry;
	} //get_totalCategoryDataNo
	
	
	@Override
	public int get_totalSubCategoryDataNo(String outlet_uuid, String category_uuid) {
		
	int totalSubCatgry = 0;
	 Session  session = null;
        try {    	
        	session=sessionFactory.openSession();
        	Query qry = session.createQuery("select a.category_uuid as uuid,b.category_name from pos_outlet_categories a , pos_categories b where a.category_uuid = b.uuid and a.outlet_uuid=:outlet_uuid and b.Parent_CategoryID=:parentCat order by b.category_name");
            qry.setParameter("outlet_uuid", outlet_uuid);
            qry.setParameter("parentCat", category_uuid);
            
            List<Object[]> results = (List<Object[]>)qry.list();
            
          
	            try {
	            	totalSubCatgry = results.size();
	            }catch(Exception e) {
	            	e.printStackTrace();
	            }
            	  
            return totalSubCatgry;        
        } catch (Exception e) {
            System.out.println("error happend while getting totalNoSubCatgry result");
            e.printStackTrace();
        } finally {
			if(session != null)
				 session.close();
		}
		
			
		return totalSubCatgry;
				
	}

	
}//class

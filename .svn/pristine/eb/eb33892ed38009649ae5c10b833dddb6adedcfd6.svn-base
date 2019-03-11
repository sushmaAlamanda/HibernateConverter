package com.ramersoft.pos.ui.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.ramersoft.pos.entities.ERPUSERS;
import com.ramersoft.pos.entities.ERP_Outlets;
import com.ramersoft.pos.enums.ERP_Outlets_Status;


/**
 * DaoImplement consists of method implements of LoginDao
 */



/**
 * Note:if you mention @Component above the class then there is no need to specify in dispatcher-servlet
 * You can create object where ever you want by just using @Autowired annotation
 */

@Component
public class LoginDaoImpl implements LoginDao{

   
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

   
	
	/**
     * For User authentication
     * It checks for the existence of entered email address
     * @returns  not exist   -- when there is no entry with email
     *           invalid     -- if email exists but password doesn't matched
     *           userid&uuid -- if email and password matched
     */

    public String checkUserExistence(String email, String password) {
        String md5Password = this.getMD5EncryptedValue(password);
        String dbPassword = null;
        Session session = null;
        try {    	
            session=sessionFactory.openSession();
            Query qry = session.createQuery("select p.password,p.uuid,p.userid from erp_users p where p.email=:p1");
            qry.setParameter("p1",email);    		
            // resultStrings=(String[])query.uniqueResult();
             List I = qry.list();     
             Iterator it = I.iterator();
             while(it.hasNext()) {
            	 Object resultObjects[] = (Object[])it.next();
            	 dbPassword = (String) resultObjects[0];
            	 String UserID = Integer.toString((int) resultObjects[2]);
            	 String uuid = (String)resultObjects[1];
                 if (dbPassword.equals(md5Password)) {
                	 //System.out.println("I am in if condition");
                	 return UserID+"||||"+uuid;
                 } else {
                     return "invalid";
                 }
             }                 
        } catch (Exception e) {
            System.out.println("error happend while getting result with given email");
            //e.printStackTrace();
            return "not exist";
            
        }finally{
        	if(session!=null) {
        		session.close();
        	}
        }
        return "not exist";
    }

  
    
    
    /**
     * @param password
     * @return md5 encrypted password
     */
    public String getMD5EncryptedValue(String password) {
        final byte[] defaultBytes = password.getBytes();
        try {
            final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
            md5MsgDigest.reset();
            md5MsgDigest.update(defaultBytes);
            final byte messageDigest[] = md5MsgDigest.digest();
            final StringBuffer hexString = new StringBuffer();
            for (final byte element : messageDigest) {
                final String hex = Integer.toHexString(0xFF & element);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            password = hexString + "";
        } catch (final NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return password;
    }
   
    
    
    
    
    /**
     * this function gets outlet's data from erp_outlets table returns a list
     * which contains every outlets uuid as key and outlet_name as value
     */
    
	@Override
	public List getOutletsInfo() {	
		
		  Session session = null;
		  try {
		        session = sessionFactory.openSession();
		        if(session!=null) {
				session.beginTransaction();  
				Criteria  criteria  =session.createCriteria(ERP_Outlets.class);
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("uuid"))
						.add(Projections.property("b_unit_name"))
						); 
				criteria.addOrder(Order.asc("b_unit_name"));
			    criteria.add(Restrictions.eq("status",ERP_Outlets_Status.Active));
				List  outlets = criteria.list();
				session.getTransaction().commit();
				return outlets;
		        }							
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally{
	        	if(session!=null) {
	        		session.close();
	        	}
	        }

		
		return null;
	}
    
	
	
	
	
	 /**
     * this is function check whether the server type is node or central
     * @returns a string (node or central)
     */	
	@Override
	public String getLocalDataInfo(String fieldName) {
		 String column_value = null;
		 Session session = null;
	        try {
	        	session = sessionFactory.openSession();
	        	String hql = "select column_value from local_data where column_name=:column_name";
	        	Query query = session.createQuery(hql);
	        	query.setParameter("column_name", fieldName);
	        	column_value = (String) query.uniqueResult();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
				if (session != null) {
					session.close();
			    }
	        }
	        return column_value;
	}

	
	
	
	/**
	 * Gets user details with given user_uuid
	 */	
	@Override
	public ERPUSERS checkOtpUserDetails(String user_uuid) {		
		Session session = null;
		try {
		    session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			Criteria criteria = session.createCriteria(ERPUSERS.class);
			criteria.add(Restrictions.eq("uuid",user_uuid));		
			Object result = criteria.uniqueResult();
			ERPUSERS users = (ERPUSERS) result;		
			return users;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null)
				 session.close();
		}
		return null;
		
	}

	
}
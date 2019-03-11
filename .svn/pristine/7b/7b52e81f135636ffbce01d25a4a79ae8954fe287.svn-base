package com.ramersoft.pos.ui.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramersoft.pos.entities.ERPUSERS;
import com.ramersoft.pos.ui.beans.Singleton;
import com.ramersoft.pos.ui.dao.LoginDao;
import com.ramersoft.pos.ui.libs.ERPFunctions;

@Service
public class LoginServiceImpl implements LoginService {
    //static Logger logger = Logger.getLogger(LoginServiceImpl.class);



	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	ERPFunctions erpFunObj;

	@Override
	public String checkUserByLoginDetails(HttpServletRequest request, HttpServletResponse response) {
		/*String email = request.getParameter("email");
		String password = request.getParameter("password");*/
		
		String email = "";
        try {
            if(!request.getParameter("email").equals("null"))
          email = request.getParameter("email");
        }catch(Exception e) {
            e.printStackTrace();
        }

		String password = "";
		       try {
		           if(!request.getParameter("password").equals("null"))
		           password = request.getParameter("password");           
		       }catch(Exception e) {
		           e.printStackTrace();
		       }

		

        LocalDate date = LocalDate.now().plusDays(2);
        System.out.println("local date is:"+date);
        
        String returnMessage = "not exist";
        try {
		 returnMessage = loginDao.checkUserExistence(email, password);
        }catch(Exception e) {
        	
        }

	   if (!returnMessage.equals("not exist") && !returnMessage.equals("invalid")) {
			int min = 1000;
			int max = 10000;
			Random ran = new Random();
			int randomInt = min + ran.nextInt(max - min + 1);
			returnMessage = randomInt + "||||" + returnMessage;
			return returnMessage;
		} else {
			return returnMessage;
		}

	}

	/**
	 * this function gets outlet's data from erp_outlets table returns a HashMap
	 * which contains every outlets uuid as key and outlet_name as value
	 */
	@Override
	public LinkedHashMap<String, String> getOutletInfo() {
		LinkedHashMap<String, String> outletData = new LinkedHashMap<String, String>();
		try {
			List outlets = loginDao.getOutletsInfo();
			//outlets.forEach(System.out::println);
			Iterator it = outlets.iterator();
			while (it.hasNext()) {
				Object ob[] = (Object[]) it.next();
				outletData.put((String) ob[0], (String) ob[1]);
			}

		} catch (Exception e) {
			System.out.println("something went wrong while getting outlet info");
			e.printStackTrace();
		}

		return outletData;

	}
    
	
	/**
	 * Function let's you know that server_type of the db either server or node
	 */
	@Override
	public String getLocalDataInfo(String fieldName) {

		try {
			String dbType = loginDao.getLocalDataInfo("server_type");
			return dbType;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
    
	
	/**
	 * gets user details,outletID  and set the session
	 */
	@Override
	public String checkUserValidOrNot(HttpServletRequest request,  HttpSession httpSession) {
		  String UserID ="";
	        try {
	            if(!request.getParameter("UserID").equals("null"))
	              UserID = request.getParameter("UserID");
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	        
	        String OutletID = "0";
	        try {
	            if(!request.getParameter("OutletID").equals("null"))
	                OutletID = request.getParameter("OutletID");
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	        
	        String user_uuid = "";
	        try {
	            if(!request.getParameter("user_uuid").equals("null"))
	           user_uuid = request.getParameter("user_uuid");
	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	          System.out.println("I am checkUserValidOrNot and user_uuid is:"+user_uuid);
	        
	        if (Integer.parseInt(OutletID) == 0) {
	            OutletID = loginDao.getLocalDataInfo("outletid");
	        }
		
		ERPUSERS userDetForSesn = (ERPUSERS) loginDao.checkOtpUserDetails(user_uuid);
	   // System.out.println("This is user Date:"+userDetForSesn.toString());
		try {
		if (userDetForSesn.equals(null)) {
			return "invalid";
		} else {
			System.out.println(userDetForSesn.getFirstname() + " " + userDetForSesn.getLastname()+userDetForSesn.getUuid());
			httpSession.setAttribute("UserName", userDetForSesn.getFirstname() + " " + userDetForSesn.getLastname());
			httpSession.setAttribute("Outlet_uuid", OutletID);
			httpSession.setAttribute("User_uuid", userDetForSesn.getUuid());
			
			String agent_os = erpFunObj.findOs(request);
			httpSession.setAttribute("os_agent", agent_os);
			
			System.out.println("**********agent_os iss********"+agent_os+"********");
			
			//created on 2019-01-08
			/*Singleton sessionBean = new Singleton();
			Singleton.setUser_name(userDetForSesn.getFirstname() + " " + userDetForSesn.getLastname());
			Singleton.setUser_uuid(userDetForSesn.getUuid());
			Singleton.setOutlet_uuid(OutletID);*/
			
			/********sending string to the event log**************/
			
			/*String internet_date = erpFunObj.gettingDate(); 
			System.out.println(internet_date);
			String login_details = "*******************************Day_In_Details:*******************************\n\t Date: "+internet_date
								 +"\n\t Cashier: "+httpSession.getAttribute("UserName")+
								 "\n**********************************************************************************";
			String store_login_details  = erpFunObj.eventLog(login_details); */
			/*Logger rollingFile = Logger.getLogger("rollingFile");
			rollingFile.log("****************hello file*****************");*/
			/***************************************************/
			
			return "valid";
		}
		}catch(Exception e) {            
            e.printStackTrace();
            return "invalid";
        }
				
	}

}

package com.ramersoft.pos.ui.controllers;


import java.util.HashMap;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ramersoft.pos.ui.service.LoginService;
@Transactional          //@Transactional is a Hibernate related annotation
@Controller 
public class MainController {
     		
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/")
	public ModelAndView showMessage() {
		
		ModelAndView mv = new ModelAndView("login/index");
		HashMap<String,String> outletData = (HashMap<String, String>) loginService.getOutletInfo();
        mv.addObject("outletData", outletData);	       
		return mv;
		
	}
	
	
	
	
	
	
	/**
	 * Backup code for reference
 	 */
	
	/**********
	   To display map elements key value elements
	 /*outletData.forEach((key,value)->{
        	System.out.println(key+"-----"+value);
        });*
        
        
        	/*	System.out.println("Hi");
		*/
		//Session session = sessionFactory.getCurrentSession();
		 
		/*Session session = sessionFactory.openSession();
		session.beginTransaction();  */
		//System.out.println("this is session:"+session);
		/*Query query = session.createQuery("FROM people");
		System.out.println(query.list());
		*/
		// System.out.println(session.createQuery("from erp_users").list());
		
    /*    List<Student> students = session.createQuery("from people").list();
 		for(Student student : students) {
			System.out.println(student.getAge());
		}
	
 		
 		List<ERPUSERS> users = session.createQuery("from erp_users").list();
		
 		for(ERPUSERS user : users) {
			System.out.println(user.getEmail());
		}
 		
 	    List<ERP_Outlets> outlets = session.createQuery("from erp_outlets").list();
		
 		for(ERP_Outlets outlet : outlets) {
			System.out.println(outlet.toString());
		}*/
 		/*// session.close();
 	    ERP_Outlets erpOutlets = new ERP_Outlets();
 		erpOutlets.setOutletID(2334);
 		erpOutlets.setB_unit_name("hsdsisdsdsbesdrnateOutletdsd");
 		erpOutlets.setB_unit_short_name("faasssdfsdfsdorsdsstesting");
 		erpOutlets.setAddress("ddsdvisdfsdfdsddzadgds");
 		erpOutlets.setCreated_date(new Date());
 		erpOutlets.setCreated_by("ebcd6b2f5271ee50");
        erpOutlets.setGSTtinnumber("3732GAPDPV721223G1BC");
 		erpOutlets.setUuid("21960");
 		erpOutlets.setCompany_uuid("c46663a97df8a258");
 		erpOutlets.setLocation_uuid("4a3a1a88efaffa49");
 		erpOutlets.setStatus(ERP_Outlets_Status.Active);
 		
 		
 	    System.out.println(erpOutlets.toString()); 
 		
 		Student student = new Student();
 		student.setFirst_name("Sivakumarabc");
 		student.setLast_name("sivakumar1232");
 		
 		//session.beginTransaction();       
        session.save(erpOutlets);
        session.getTransaction().commit();        
        //session.close(); 
	*
        
	 ********/
	
}

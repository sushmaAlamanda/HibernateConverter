package com.ramersoft.pos.ui.libs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ramersoft.pos.ui.beans.CategoriesBean;
import com.ramersoft.pos.ui.beans.UnitsBean;
import com.sun.xml.internal.ws.util.StringUtils;

@Component
public class ERPFunctions {
	
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * instant.getEpochSecond() gives you the timeStamp since 1970-01-01 in seconds
	 * uniqueRandAndTimeStamp @return a string with 16digit random number and
	 * timestamp. https://www.baeldung.com/current-date-time-and-timestamp-in-java-8
	 */
	public String uniqueRandAndTimeStamp() {
        //random numbers count
		int randNumLimit = 16;

		Random r = new Random();
		StringBuffer randDigit = new StringBuffer();
		while (randDigit.length() < randNumLimit) {
			randDigit.append(Integer.toHexString(r.nextInt()));
		}   
		//random digit
		String randomDigit = randDigit.toString().substring(0, randNumLimit);
		String timeStampSeconds="";
		try {
			timeStampSeconds = getTimeStamp();
		}catch(Exception e) {
			e.printStackTrace();
		}		 
		//complete uuid
		String randomString = randomDigit + timeStampSeconds;
         
		return randomString;
	}
	
	
	public String getTimeStamp() {
		//timestamp
	    Instant instant = Instant.now();
	    long timeStampSeconds = instant.getEpochSecond();
	    String timeStampSecondsStr = String.valueOf(timeStampSeconds);
		return timeStampSecondsStr;
	}
	
	
	/**
	 * 
	 * @param list
	 * @param request
	 * @param session
	 * @return  an css contained String for main categories
	 */
	
	 public String getMainCategoriesData(List<CategoriesBean> list,HttpServletRequest request,HttpSession session) {
		   
			String tableRow = "<tr>";
			int i = 0;
			String btn_sel_cls="";
			for(CategoriesBean categories :  list){
				i++;				
				 if(request.getParameter("selected_maincat_id")!="" && request.getParameter("selected_maincat_id").equals(categories.getUuid())) {
					 btn_sel_cls = "select_cat";
				 }
				tableRow += "<td width='25%'><button class='button1 main_category "+btn_sel_cls +"' data-value='" +i+ "' data-id='"+ categories.getUuid() +" ' >"+categories.getCategory_name()+"</button></td>";
			}			
			if(i < 4) {
				for(int j=i; j<4; j++) {
					i++;
					btn_sel_cls = "disabled_button";
					tableRow += "<td width='25%'><button class='button1 "+btn_sel_cls +"' data-value='"+i+ "' data-id='' >&nbsp;</button></td>";
				}
			}
	    	  tableRow +="</tr>";
			return tableRow;
		}
	
	   
	   /**
	    * @return  sub categories string
 	    */
	   public String getSubCategoriesData(List<CategoriesBean> list,HttpServletRequest request,HttpSession session) {
	    	 
			String tableRow = "";
			int i = 0;
			String btn_sel_cls="";
			for(CategoriesBean categories :  list){
				i++;
				try {
				if(request.getParameter("selected_subcat_id")!=null) {	
				 if(request.getParameter("selected_subcat_id")!="" && request.getParameter("selected_subcat_id").equals(categories.getUuid())) {
					 btn_sel_cls = "selected_subcat";
				 }
				}
				 }catch(Exception e) {
					 System.out.println("selected_subcat_id is not setted");
				 }
				tableRow += "<tr data-id='" + categories.getUuid() + "' data-value='"+i+ "' class='sub_catdiv'><td><button class='_button button3 sub_category "+btn_sel_cls +"' data-id='"+categories.getUuid()+"'><p> "+categories.getCategory_name()+ "</p></button></td></tr>";
			}
			
			if(i < 6) {
				for(int j=i; j<6; j++) {
					i++;
					//btn_sel_cls = "disabled_button";
					tableRow += "<tr data-id='' data-value='"+i+"' class=''><td><button class='_button button3' style='background-color:lightgray;' data-id=''><p></p></button></td></tr>";
				}
			}
	    	  
			return tableRow;
       }
	   
	   
	   
	   
	   /**
	    *  returns units css string 
	    */	   
	   public String getUnitsList(List<UnitsBean> unitsList,HttpServletRequest request,HttpSession session) {
	        String unit = "";
	        
	        for(UnitsBean unitBean : unitsList) {
	           unit += "<tr><td><button data-item='"+ unitBean.getItem_uuid()+"'  data-value='" + unitBean.getUuid()+"'  data-id='"+ unitBean.getUnit_name()+","+unitBean.getPrice()+","+unitBean.getPortion_name()+","+unitBean.getSize()+"' class='_button button5 unit-btn-link'>"+unitBean.getPortion_name()+"</button></td></tr>";
	        }
	        return unit;
	    }
	   
	   
	   

		 /**
	     * this is function check whether the server type is node or central
	     * @returns a string (node or central)
	     */	
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
		
	  /*************for getting the time from the internet**************/
		public String gettingDate(){ 
			String TIME_SERVER = "time-a.nist.gov";   
			NTPUDPClient timeClient = new NTPUDPClient();
			InetAddress inetAddress = null;
			try {
				inetAddress = InetAddress.getByName(TIME_SERVER);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TimeInfo timeInfo = null;
			try {
				timeInfo = timeClient.getTime(inetAddress);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long returnTime = timeInfo.getReturnTime();
			System.out.println("returnTime::: " + returnTime);
			Date time = new Date(returnTime);
			System.out.println("Time from " + TIME_SERVER + ": " + time);
			
		    String internet_date = String.valueOf(time);
			return internet_date;
			
			/*String SERVER_NAME = "pool.ntp.org";

	        NTPUDPClient timeClient = new NTPUDPClient();
	        // We want to timeout if a response takes longer than 10 seconds
	        timeClient.setDefaultTimeout(10000);

	        InetAddress inetAddress = null;
			try {
				inetAddress = InetAddress.getByName(SERVER_NAME);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        TimeInfo timeInfo = null;
			try {
				timeInfo = timeClient.getTime(inetAddress);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        long returnTime = timeInfo.getReturnTime();
	        TimeStamp destNtpTime = TimeStamp.getNtpTime(returnTime);
	        System.out.println("System time:\t" + destNtpTime + "  " + destNtpTime.toDateString());

	        TimeStamp currentNtpTime = TimeStamp.getCurrentTime();
	        System.out.println("Atomic time:\t" + currentNtpTime + "  " + currentNtpTime.toDateString());
	        return null;*/
		}
		/**********end of date*********************/
		
		
		/*************For event log in pos**************/
		public String eventLog(String data){ 
			String details = data;
		    BufferedWriter output = null;
		    try {
		        File file = new File("pos_event_log.txt");
		        output = new BufferedWriter(new FileWriter(file));
		        output.write(details);
		    } catch ( IOException e ) {
		        e.printStackTrace();
		    } finally {
		      if ( output != null ) {
		        try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		    }
			return null;			
		}
		/**********end of event log in pos*********************/
		
		/*************For printer pos**************/
		public String sendingPrinter(String data){ 
			String details = data;
		    BufferedWriter output = null;
		    try {
		        File file = new File("/dev/usb/lp0");
		        output = new BufferedWriter(new FileWriter(file));
		        output.write(details);
		    } catch ( IOException e ) {
		        e.printStackTrace();
		        return "Failed";
		    } finally {
		      if ( output != null ) {
		        try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		      }
		    }
			return "Success";			
		}
		
		
		/**********end of printer pos*********************/
		
		/*************for sending data to the node port********/
		public String nodePrinter(String ipAddress, String linux_grid) {
		      Socket socket = null; 
			  try {
				socket = new Socket(ipAddress, Constants.printer_port);
			} catch (IOException e) {
				e.printStackTrace();
				return "Failed";
			} 
			  System.out.println("***************Connected*********");
			  OutputStream os = null;
			try {
				os = socket.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
				return "Failed";
			}
		      OutputStreamWriter osw = new OutputStreamWriter(os);
		      BufferedWriter bw = new BufferedWriter(osw);
		      try {
				bw.write(linux_grid);
			} catch (IOException e) {
				e.printStackTrace();
				return "Failed";
			}
		      try {
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
				return "Failed";
			}
		    try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		   return "Success";
		}
	      
	      
	      
	      

	   public String[] splitGivenString(String splitString,String delimiter){
			  String[] splitted_strs = splitString.split(delimiter);
			  return splitted_strs;
	   }
	   
	   /**************amount from digits to words************/
	   private static final String[] tensNames = {
			    "",
			    " ten",
			    " twenty",
			    " thirty",
			    " forty",
			    " fifty",
			    " sixty",
			    " seventy",
			    " eighty",
			    " ninety"
			  };

			  private static final String[] numNames = {
			    "",
			    " one",
			    " two",
			    " three",
			    " four",
			    " five",
			    " six",
			    " seven",
			    " eight",
			    " nine",
			    " ten",
			    " eleven",
			    " twelve",
			    " thirteen",
			    " fourteen",
			    " fifteen",
			    " sixteen",
			    " seventeen",
			    " eighteen",
			    " nineteen"
			  };
	   public String convertDigitToString(long number) {
				  if (number == 0) { return "zero"; }

				    String snumber = Long.toString(number);

				    // pad with "0"
				    String mask = "000000000000";
				    DecimalFormat df = new DecimalFormat(mask);
				    snumber = df.format(number);

				    // XXXnnnnnnnnn
				    int billions = Integer.parseInt(snumber.substring(0,3));
				    // nnnXXXnnnnnn
				    int millions  = Integer.parseInt(snumber.substring(3,6));
				    // nnnnnnXXXnnn
				    int hundredThousands = Integer.parseInt(snumber.substring(6,9));
				    // nnnnnnnnnXXX
				    int thousands = Integer.parseInt(snumber.substring(9,12));

				    String tradBillions;
				    switch (billions) {
				    case 0:
				      tradBillions = "";
				      break;
				    case 1 :
				      tradBillions = convertLessThanOneThousand(billions)
				      + " billion ";
				      break;
				    default :
				      tradBillions = convertLessThanOneThousand(billions)
				      + " billion ";
				    }
				    String result =  tradBillions;

				    String tradMillions;
				    switch (millions) {
				    case 0:
				      tradMillions = "";
				      break;
				    case 1 :
				      tradMillions = convertLessThanOneThousand(millions)
				         + " million ";
				      break;
				    default :
				      tradMillions = convertLessThanOneThousand(millions)
				         + " million ";
				    }
				    result =  result + tradMillions;

				    String tradHundredThousands;
				    switch (hundredThousands) {
				    case 0:
				      tradHundredThousands = "";
				      break;
				    case 1 :
				      tradHundredThousands = "one thousand ";
				      break;
				    default :
				      tradHundredThousands = convertLessThanOneThousand(hundredThousands)
				         + " thousand ";
				    }
				    result =  result + tradHundredThousands;

				    String tradThousand;
				    tradThousand = convertLessThanOneThousand(thousands);
				    result =  result + tradThousand;

				    // remove extra spaces!
				    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	   }
	   private static String convertLessThanOneThousand(int number) {
		    String soFar;

		    if (number % 100 < 20){
		      soFar = numNames[number % 100];
		      number /= 100;
		    }
		    else {
		      soFar = numNames[number % 10];
		      number /= 10;

		      soFar = tensNames[number % 10] + soFar;
		      number /= 10;
		    }
		    if (number == 0) return soFar;
		    return numNames[number] + " hundred" + soFar;
		}
     /************************End********************************/

	   /*********to know the os type*******************/
	   public String findOs(HttpServletRequest request) {
	   String  browserDetails  =   request.getHeader("User-Agent");
	   System.out.println("*******browserDetails******"+browserDetails+"*****************");
	   String  userAgent       =   browserDetails;
	   String  user            =   userAgent.toLowerCase();

	   String os = "";
	   String browser = "";

	   //=================OS=======================
	    if (userAgent.toLowerCase().indexOf("windows") >= 0 )
	    {
	        os = "Windows";
	    } else if(userAgent.toLowerCase().indexOf("mac") >= 0)
	    {
	        os = "Mac";
	    } else if(userAgent.toLowerCase().indexOf("x11") >= 0)
	    {
	        os = "Linux";
	    } else if(userAgent.toLowerCase().indexOf("android") >= 0)
	    {
	        os = "Android";
	    } else if(userAgent.toLowerCase().indexOf("iphone") >= 0)
	    {
	        os = "IPhone";
	    } else if(userAgent.toLowerCase().indexOf("linux") >= 0)
	    {
	        os = "Linux";
	    }else{
	        os = "UnKnown, More-Info: "+userAgent;
	    }

	    System.out.println("*******os  is******"+os+"*****************");
	    return os;
	   }
	   
	   
	   
	
}

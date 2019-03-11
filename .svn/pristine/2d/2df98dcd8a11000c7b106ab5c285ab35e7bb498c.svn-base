<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.io.*, java.util.Date,java.text.*,java.util.LinkedHashMap,java.util.List,java.util.*" %> 
<%@ page import="com.ramersoft.pos.entities.Pos_Outlet_Day_In_Out_Transaction" %>
<%@ page import="com.ramersoft.pos.entities.ERP_Outlets" %>
<%@ page import="com.ramersoft.pos.ui.beans.CategoriesBean" %>
<%@ page import="com.ramersoft.pos.ui.beans.OrdersDataBean" %>
<%@ page import="com.ramersoft.pos.ui.beans.OrderstypesBean" %>
<%

   String userName = (String)session.getAttribute("UserName");
   String outlet_uuid = (String)session.getAttribute("Outlet_uuid");
   
  //day_in_details
   Pos_Outlet_Day_In_Out_Transaction day_in_data = null;
     try{
             if(request.getAttribute("day_in_data")!=null){
	          day_in_data = (Pos_Outlet_Day_In_Out_Transaction)request.getAttribute("day_in_data");	
             }
     }catch(Exception e){
    	 e.printStackTrace();
     }   
     
   
  //Main Categories
    List<CategoriesBean> mainCategories = new ArrayList<CategoriesBean>();
  
    try{
        if(request.getAttribute("MainCategories")!=null)
        	mainCategories = (List<CategoriesBean>)request.getAttribute("MainCategories");	
    }catch(Exception e){
    	e.printStackTrace();
    }
    
    pageContext.setAttribute("mainCategories", mainCategories);
    
    //************to get the uuid of general order bill********//
    String general_uuid = null;
    try{
        if(request.getAttribute("general_uuid")!=null)
        	general_uuid = (String)request.getAttribute("general_uuid");	
    }catch(Exception e){
    	e.printStackTrace();
    }
    pageContext.setAttribute("general_uuid", general_uuid);
    pageContext.setAttribute("outlet_uuid", outlet_uuid);
    
    //************to get the order types**********************//
     List<OrderstypesBean> orderTypes = new ArrayList<OrderstypesBean>();
    try{
        if(request.getAttribute("ordertypes")!=null)
        	orderTypes = (List<OrderstypesBean>)request.getAttribute("ordertypes");	
    }catch(Exception e){
    	e.printStackTrace();
    }
    pageContext.setAttribute("orderTypes", orderTypes);
    
	//******ordersData***************************************//
    List<OrdersDataBean> ordersData = new ArrayList<OrdersDataBean>();
  
    try{
        if(request.getAttribute("orders_data")!=null)
        	ordersData = (List<OrdersDataBean>)request.getAttribute("orders_data");	
    }catch(Exception e){
    	e.printStackTrace();
    }
    pageContext.setAttribute("ordersData", ordersData);
    System.out.println("*********index.jsp of pos********************");
    System.out.println(ordersData);
    System.out.println("ordersData length is:"+ordersData.size());
    System.out.println("*****************************");
    
    /**********transaction sum**********/
    Double transactionSumData = 0d;
    try{
        if(request.getAttribute("transactionSumData")!=null)
            transactionSumData = (Double)request.getAttribute("transactionSumData");    
    }catch(Exception e){
        e.printStackTrace();
    }
    
    pageContext.setAttribute("transactionSumData", transactionSumData);
    
    /**************to get the current year*******************/
    String year = "";
    try{
        if(request.getAttribute("year")!=null)
            year = (String)request.getAttribute("year");    
    }catch(Exception e){
        e.printStackTrace();
    }   
    pageContext.setAttribute("year", year);
    System.out.println("*********index.jsp of pos********************");
       System.out.println("current_year is:"+year);
    System.out.println("*****************************");
    
    int outlet_uuid_int  = Integer.valueOf(outlet_uuid)+10;
    String outlet_uuidCnct = String.valueOf(outlet_uuid_int);
    pageContext.setAttribute("outlet_uuidCnct", outlet_uuidCnct);
    
    
    /************to get outlet name***************/
    
   ERP_Outlets outlet_data = null;
   String outletName = "";
   String Gstinnumber = "";
   String outletAddress = "";
   
     try{
             if(request.getAttribute("outletName")!=null){
            	 outlet_data = (ERP_Outlets)request.getAttribute("outletName");	
            	 
            	 outletName = outlet_data.getB_unit_name();
            	 System.out.println("***outlet_data is"+outletName);
                 Gstinnumber = outlet_data.getGSTtinnumber();
                 outletAddress = outlet_data.getAddress();
             }
     }catch(Exception e){
    	 e.printStackTrace();
     }  
     pageContext.setAttribute("outletName", outletName);
     pageContext.setAttribute("Gstinnumber", Gstinnumber);
     pageContext.setAttribute("outletAddress", outletAddress);
     
     Integer max_pages =    (Integer)request.getAttribute("categories_max_cnt");
     pageContext.setAttribute("max_pages", max_pages);
%>

<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale=1.0">
	<!-- <meta http-equiv="refresh" content="<?php echo $sec?>;URL='<?php echo $page?>'">-->
	<title>POINT OF SALE ERP/2 |  POS </title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/public/img/logoimage.jpg">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/public/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/public/css/jquery.toast.min.css">
	<script type="text/javascript"> 
		var server_url = ""; 
	</script>
	<script src="${pageContext.request.contextPath}/resources/public/js/jquery_3.2.1_jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/public/js/calculator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/public/js/jquery.toast.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/public/plugins/jquery.keypad.package/css/jquery.keypad.css">
	<script src="${pageContext.request.contextPath}/resources/public/plugins/jquery.keypad.package/js/jquery.plugin.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/public/plugins/jquery.keypad.package/js/jquery.keypad.js"></script>
	<!--- saritha includes dattables code-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/public/css/datatables.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/public/css/datatables.min.css">
        <!--<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/plug-ins/1.10.19/pagination/four_button.js"></script>-->
	<link href="${pageContext.request.contextPath}/resources/public/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/public/js/datatables.min.js"></script>
	
	<script type="text/javascript" charset="utf8" src="${pageContext.request.contextPath}/resources/public/js/jquery-ui.js"></script>
        <link href="${pageContext.request.contextPath}/resources/public/css/jquery-ui.css" rel="stylesheet" media="screen">
	<!-- online urls
	<script type="text/javascript" charset="utf8" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.19/themes/cupertino/jquery-ui.css">-->
	
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/public/css/index.css">
	<script src="${pageContext.request.contextPath}/resources/public/js/dist/JsBarcode.all.js"></script>
	<script>
		Number.prototype.zeroPadding = function(){
			var ret = "" + this.valueOf();
			return ret.length == 1 ? "0" + ret : ret;
		};
	</script>
        <script>
        	$(document).on("click","#help",function(e) {
			//alert("hi");
                                $("#view_billpark").hide();
                                $("#view_search").hide();
                                $("#view_deliveryorderbuttons").hide(); 	
								$("#leftmain_table").hide(); 
								$("#view_preorderdetails").hide(); 
								$("#view_estimateorderdetails").hide(); 
								$("#view_orderdetails").hide();
                                $("#view_raise_ticket").hide();	
                                $("#view_help").show();	
		
		});	

	//feedback	
		$(document).on("click","#feedback_close",function(e) {	
		$( "#view_help" ).hide();
		$("#leftmain_table").show();	
	});	
		
		
//for feedback submit
		$(document).on("click","#feedback_submit",function(e) {
		var outlet = $("#outlet").val();
		//alert(outlet);
	    var cashier = $("#cashier").val();
		//alert(cashier);
		var issue_type=$("#issue_type").val();
			//alert(issue_type);
			var description=$("#help_description").val();
			//alert(description);
			var priority=$("#priority").val();
			//alert(priority);
			if(priority=='' || issue_type==''){
				//alert("select");
				$('#msg_feedback').html('Please fill all the required fields');
			}

			
			else {
				//alert("else");
				$('#msg_feedback').html(' ');
		var params_data = {	
			   'outlet':outlet,
			    'cashier':cashier,
			    'issue_type':issue_type,
			   'description':description,
			    'priority':priority
		}
		
		$.ajax({
			
				url: "index.php?ref=<?php echo $btn_encode_url['pos/feedback']; ?>",
		         type:'post',
			     dataType:'json',
		         data: params_data,
		       success: function(data) {
				   //alert(data);
				  $('#msg_feedback').html('Mail sent successfully');
				   window.location.reload(true);
			   }
			  
		    });
			}
		});	
		
	</script>
	  
	<script>
  $( function() {
    $( "#tabs" ).tabs();
  } );
			
  </script>
</head>
<body class="body page-index clearfix">
   
	<div class="main_div">
		<div class="header_div">
			<div class="row hd_1" id="hd_1" >
				<div class="col-md-4">
					<p  style="font-weight: bold;"><lable style="font-weight: bold;">Location:</lable>&nbsp;<%out.print(outletName); %>&nbsp;:&nbsp;<%out.print(userName); %>&nbsp;</p>
				</div>
				<div class="col-md-4" style="text-align: center;font-weight: bold;"><%Date td = new Date();
										String b = new String("");
										SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
										b = format.format(td);
										out.println(b);%>&nbsp;<b class="clock-time"></b></div>
				<div class="col-md-4">
					<span style="float:right;">
						<img id="refresh_button" title="Refresh" src="${pageContext.request.contextPath}/resources/images/refreshnew.png" width="40px" height="40px">
						<img  id="max" title="Minimise" src="${pageContext.request.contextPath}/resources/images/full screen1.png" width="40px" height="40px">&nbsp;
						<img id="logoutstyle" class="_button-1" title="Logout" src="${pageContext.request.contextPath}/resources/images/logout.svg" width="40px" height="40px">&nbsp;
                                                <button type="button" class="btn btn-success" name="help"  id="help">Help</button>
					</span>
				</div>
			</div>
		</div>
		<div class="body_div pos_div" id="Right">
			<div id="body_header_section"></div>
			<div class="row" style="padding:0px;margin:0px;">
				<div class="col-sm-6" style="border: 1px solid #e8e8e8;padding:5px;margin:0px;height:650px;">
					<div class="left_header">
						<table width="100%" border="0">
							<tr>
								<td width="10%">
									<button class="button1 button2 main_cat_nav" id="main_cat_prev" data-value="0" ><b>&lt;</b></button>
								</td>
								<%-- <td><table width="100%" id="main_cat_tbl"><tr>
									<?php $i=0; foreach ($allcategories as $key => $value):  $i++; ?>
									<?php if($i<=4) { ?>
									<td width="25%">
									<button class="button1 categories_list main_category <?php if($i ==2){ echo "bakery"; } else if($i==1) { echo "selected_cat sweetsdummy"; } ?>" data-id="<?php if($value['CategoryID']!="") { echo $value['CategoryID']; } ?>" ><?php if($value['category_name']!="") { echo $value['category_name']; } ?><!--<br><span style="display: none;" class="main-cat-tick selected-tick<?php if($value['CategoryID']!="") { echo $value['CategoryID']; } ?>">â</span>--></button>
									</td>
									<?php } ?>
									<?php endforeach ?> 
								</tr></table></td> --%>
								
								<td><table width="100%" id="main_cat_tbl"><tr>
									<%! int loopCount = 0;%>
									<c:forEach items="${mainCategories}" var="category">
									    <% loopCount++; %>					  
									    <td width="25%">
											<button class="button1 categories_list main_category 
											<%if(loopCount==1){
												out.print("selected_cat sweetsdummy");
												}else if(loopCount == 2){
					                            out.print("bakery");
											} %>>" data-id="<c:out value="${category.uuid}"/>" ><c:out value="${category.category_name}"/> 
											</button>
									    </td>
									</c:forEach>
								</tr></table></td>
								
								
								<td width="10%">
									<button class="button1 button2 main_cat_nav" id="main_cat_next" data-value="1" ><b>&gt;</b></button>
								</td>
							</tr>
						</table>
					</div>
					<div class="left_content">
						<!-- B to B Order Details-->
						<div id="btob_orderdetails">
							<div class="modalviewcontent">
								<!-- Modal Header -->
								<div class="modal-header">
									<h5 class="modal-title" id="myModalLabel" style="text-align:center;">BUSSINESS TO BUSSINESS ORDER DETAILS</h5>
								</div>
								<div class="modal-body" id="">
									
									
										<div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label for="cashier">Buyer GST Tin Number&nbsp;<span style="color:red;">*</span></label>
													<input type="text" class="form-control defaultKeypad" id="gsttin" name="gsttinnumber" value="">
												</div>	
												</div>	
											<div class="col-sm-6">
												<div class="form-group">
													<label for="cashier">Customer Name&nbsp;<!--qwertyKeypad<span style="color:red;">*</span>--></label>
													<input type="text" class="form-control qwertyKeypad" id="cust_name" name="cust_name" value="">
												</div>	
											</div>
										</div>	
									   <div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label for="cashier">Phone Number&nbsp;<span style="color:red;">*</span></label>
													<input type="text" class="form-control numKeypad" id="btobphone" name="btobphone" value="">
												</div>	
											</div>		
											<div class="col-sm-6">
												<div class="form-group">
													<label for="cashier">Buyer Address&nbsp;<span style="color:red;">*</span></label>
													<textarea class="form-control defaultKeypad" id="btob_address" name="btob_address"></textarea>
												</div>
											</div>
										</div>
									    <div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label for="dayout_date">Date & Time of Delivery&nbsp;<span style="color:red;">*</span></label>
													<div class="input-group date form_datetime" data-date-format="dd MM yyyy - HH:ii p" data-link-field="btob_input2">
														<input class="form-control" size="24" type="text" style="" value="" readonly>
														<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
														<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
													</div>
													<input type="hidden" id="btob_input2" value="" /><br/>
												</div> 
										   </div>	
											<div class="col-sm-6">	
												<div class="form-group">
													<label for="cashier">Advance / Full Payment&nbsp;<!--qwertyKeypad<span style="color:red;">*</span>--></label>
													<input type="text" class="form-control numKeypad" id="btob_advance" name="btob_advance" value="">
												</div>	
											</div>
										</div>
                                                                              
										<div class="row">
											<div class="col-sm-12">
												<div class="form-group">
													<label for="cashier">Payment Mode&nbsp;</label>
													<input type="radio" class="big" id="btob_modeid" name="btob_mode" checked value="cash"> <label style="position: absolute;padding: 11px;">CASH </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="radio" name="btob_mode" class="big" id="btob_modeid"  value="card"> <label style="position: absolute;padding: 11px;">CARD</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="radio" name="btob_mode" class="big" id="btob_modeid"  value="ewallet"> <label style="position: absolute;padding: 11px;">EWALLET</label>
												</div>		
												<div class="form-group" id="cardtypes_btob">
													<label><input type="radio" id="btobcake" name="card_type_val_btob"  value="Visa"> <img width="90px" height="" id="cake_type_cakevisa" src="${pageContext.request.contextPath}/resources/images/VISA2.jpg" alt="Visa"> </label>
													<label><input type="radio" name="card_type_val_btob" id="btobcake"  value="Master">  <img  width="90px" height="" id="cake_type_cakemaster" src="${pageContext.request.contextPath}/resources/images/mastercard2.png" alt="Master"> </label>
													<label><input type="radio" name="card_type_val_btob" id="btobcake"  value="Mastero">  <img  width="90px" height="" id="cake_type_cakemaestro" src="${pageContext.request.contextPath}/resources/images/maestrocard1.png" alt="Mastero"></label>
													<label><input type="radio" name="card_type_val_btob" id="btobcake"  value="AmericanExpress">  <img  width="90px" height="64px"  id="cake_type_cakeamerica" src="${pageContext.request.contextPath}/resources/images/3.png" alt="AmericanExpress"></label>
													<label><input type="radio" name="card_type_val_btob" id="btobcake"  value="Discover">  <img  width="90px" height="" id="cake_type_cakediscover" src="${pageContext.request.contextPath}/resources/images/4.png" alt="Discover"></label>
													<!--<label><input type="radio" name="card_type_val_cake" id="typecake"  value="VisaElectronic">  <img  width="105px" height="" id="cake_type_cakevisaelectronic" src="${pageContext.request.contextPath}/resources/images/6.png" alt="VisaElectronic"></label>-->
												</div>
												<div class="form-group" id="ewallet_btob">
													<label><input type="radio" id="btobpaytm" name="ewallet_val_btob"  value="Paytm"> <img width="130px" height="" id="btob_type_paytm" src="${pageContext.request.contextPath}/resources/images/paytm.jpg" alt="Paytm"> </label>
													
												</div>
											</div>
											<div class="form-group" style="margin-top:30px;">
												<label id="msg_btob" style="color:red;" for="">(*) Fields Are Required Fields</label>
											</div>		   
											<div class="form-group">
												<button type="button" class="btn btn-primary submitBtn" id="btob_submit">Place Order</button>&nbsp;&nbsp;
												<button type="button" class="btn btn-primary close_order_Btn" id="btob_close">Close</button>
											</div>
										</div>
									</div>
							</div>
						</div>
						
                                                <!-----view billpark(items)---->
						<div id="view_billpark" style="display:none">			
						     <div class="modalviewcontent">
								<!-- Modal Header -->
								         <div class="modal-header">
									        <h5 class="modal-title" id="myModalLabel" style="text-align:center;">BILLPARK DETAILS</h5></div> 
								 <div class="modal-body" id="billpark_details" style="text-align:center;padding-left:0px;padding-right:0px;"> </div>
								  <div id="view_billpark2"></div>
							 </div> 
						</div>        
                                                
					
                                              <!-- Help FORM -->	
						<div id="view_help" >
						        <div class="modalviewcontent">
						                <!-- Modal Header -->
								         <div class="modal-header">
									        <h5 class="modal-title" id="myModalLabel" style="text-align:center;">Help</h5>
								        </div>
							      <div class="modal-body" id="help_details"> 
                                                                  <div id="tabs">
				                                     <ul>
                                                                        <li><a href="#tabs-1">Contact</a></li>
                                                                         <li><a href="#tabs-2">FAQ</a></li>
                                                                       <li><a href="#tabs-3">Raise Ticket</a></li>
                                                                       <a href="http://localhost/helpdocument/help.html" target="_blank"><li style="list-style: none;float: left;position: relative;top: 1px;margin: 0 .2em 1px 0;border-bottom: 0 !important;padding: 0;white-space: nowrap;cursor: pointer;float: left;padding: .5em 1em;text-decoration: none;color: #2779aa;border: 1px solid #aed0ea;background: #d7ebf9 url(${pageContext.request.contextPath}/resources/images/ui-bg_glass_80_d7ebf9_1x400.png) 50% 50% repeat-x;font-weight: bold;color: #2779aa;">Document</li></a>
                                                                </ul>
															             <div id="tabs-1">
																			 <br>
																			 <p style="font-size: 15px;
                                                                                                                                                                    font-family: inherit;
                                                                                                                                                                    font-style: oblique;">Thank you for your interest. 
																			 Please fill out the form in feedback tab to leave a statement or to report a 
																			 technical problem and we will get back to you at our earliest convenience.</p><br>
																			 <h4><b><i>Get In touch with us</i></b></h4>
																			<p> <img  id="phone" title="phone" src="${pageContext.request.contextPath}/resources/images/phone.png" width="30px" height="30px">&nbsp;&nbsp;<span style="font-size:19px">+91-7995950981</span></p>
                                                                       </div> 
													                  
													                        <div id="tabs-2">
                                                                                       <p style="font-size:23px"><b><i>1.How to Restart the XAMPP?</i></b></p>
														          <p style="font-size:16px;font-style: italic">Ans:Go to windows select xampp icon and right click restart</p>  
								                         </div>
													 <div id="tabs-3">
														 <div class="row">
														 <div class="col-sm-6">
	                                                        <div class="form-group">
							                               <label for="cashier">Outlet Name:</label>
							                           <input type="text" class="form-control" id="outlet" name="outlet"value="<?php echo $getoutletname[0]['b_unit_name'];?>" readonly />
						                           </div>
															 </div>
														 
											    <div class="col-sm-6">
												<div class="form-group">
							                        <label for="cashier">Cashier:</label>
							                         <input type="text" class="form-control" id="cashier" name="cashier"  value="<?php echo $_SESSION['UserName']; ?>" readonly />
						                        </div>
															 </div>
															 </div>
												 <div class="row"> 
													 <div class="col-sm-6">
												<div class="form-group">
							                        <label for="cashier">Issue Type:<span style="color:red;">*</span></label>
							                        <select name="issue_type" id="issue_type" class="form-control" ><option value=''>Select</option>
														<option value="pos">pos</option>
														<option value="general">general</option>
													</select>&nbsp;&nbsp;
														</div> 
													 </div>
												  <div class="col-sm-6"> 
												<div class="form-group">
													<label for="help">Description</label>
													<textarea class="form-control defaultKeypad" id="help_description" name="help_description" ></textarea>
												</div>
													 </div>
														 </div>	   
														  <div class="row"> 
															  <div class="col-sm-6"> 
											     <div class="form-group">
							                        <label for="cashier">Priority:<span style="color:red;">*</span></label>
							                         <select id="priority" name="priority" class="form-control"><option value="">select</option>
														                                                   <option value="Emergency">Emergency</option>
														                                                   <option value="Normal">Normal</option>
												  </select>
						                     </div>	
															  </div>
															  		  <div class="col-sm-6"> 
												             <div class="form-group">
							                             <label for="help">Attachment:</label>
							                             <input type="file" class="form-control" id="attachment" name="attachment" readonly />
						                        </div>
															  </div>
															  <div class="form-group" style="margin-top:30px;">
												<label id="msg_feedback" style="color:red;" for="">(*) Fields Are Required Fields</label>
											</div>	
														 </div>
														 
											    <div class="form-group">
													<button type="button" class="btn btn-primary submitBtn" id="feedback_submit">submit</button>&nbsp;&nbsp;
													<button type="button" class="btn btn-primary close_Btn" id="feedback_close">Close</button>
												</div>	
													 
															 
															 
														 </div>
														 
  </div> 
															  
									</div>		  
													      </div> 	 
									
							</div>   
                                            <!--Serch Remodel-->   
                                       <!--<div id="view_search" >
						        <div class="modalviewcontent">
						               
									<div class="modal-body">
                                                                            <div class="row">
                                                                                <div class="col-sm-12" >
                                                                                 <div class="form-group" style="margin-bottom: 5px !important;">
                                                                                         <input type="text" class="form-control numKeypad" id="orderid" name="orderid" placeholder="OrderId" style="width:77%;display: inline-block;"/>&nbsp;&nbsp;<button type="button" class="btn btn-primary searchorder_submitBtn" id="searchordersave" >SEARCH</button>                    
                                                                                 </div>
                                                                                </div>
                                                                            </div>
                                                                            <div id="heading_orders">Today's Orders List</div>
                                                                                <div id="search_content"></div>
                                                                        </div>
						             </div>
                                        </div>-->
				     
					<!--Saritha view order code details -->
						<div id="view_orderdetails">
							<div class="modalviewcontent">
								<!-- Modal Header -->
								<div class="modal-header">
									<h5 class="modal-title" id="myModalLabel" style="text-align:center;">ORDER DETAILS</h5>
								</div>
								<div class="modal-body" id="order_viewdetails"></div>
							</div>
						</div>
							<div id="view_estimateorderdetails">
								<div class="modalviewcontent">
								<!-- Modal Header -->
									<div class="modal-header">
										<h5 class="modal-title" id="myModalLabel" style="text-align:center;">CAKE ORDER DETAILS</h5>
									</div>
									<div class="modal-body" id="order_estimateviewdetails">
										<div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label for="cashier">Name&nbsp;<span style="color:red;">*</span></label>
													<input type="text" class="form-control qwertyKeypad" id="cakecustomer_name" name="cakecustomer_name" value="">
												</div>	
												<div class="form-group">
													<label for="cashier">Advance&nbsp;<!--<span style="color:red;">*</span>--></label>
													<input type="text" class="form-control numKeypad" id="cakeadvance" name="cakeadvance" value="">
												</div>	
											</div>
											<div class="col-sm-6">
												<div class="form-group">
													<label for="cashier">Phone Number&nbsp;<span style="color:red;">*</span></label>
													<input type="text" class="form-control numKeypad" id="cakephone_num" name="cakephone_num" value="">
												</div>	
											</div>		
											<div class="col-sm-6">
												<div class="form-group">
													<label for="cashier">Address&nbsp;<span style="">(OPTIONAL)</span></label>
													<textarea class="form-control defaultKeypad" id="cakecust_address" name="cakecust_address"></textarea>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-12">
												<div class="form-group">
													<label for="dayout_date">Date & Time of Delivery&nbsp;<span style="color:red;">*</span></label>
													<div class="input-group date form_datetime col-md-5" data-date-format="dd MM yyyy - HH:ii p" data-link-field="cakedtp_input2">
														<input class="form-control" size="24" type="text" style="" value="" readonly>
														<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
														<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
													</div>
													<input type="hidden" id="cakedtp_input2" value="" /><br/>
												</div> 
											</div> 
										</div>
										<div class="row">
											<div class="col-sm-12">
												<div class="form-group">
													<label for="cashier">Payment Mode&nbsp;</label>
													<input type="radio" class="big" id="payment_modeid" name="payment_mode_cake" checked value="cash"> <label style="position: absolute;padding: 11px;">CASH </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="radio" name="payment_mode_cake" class="big" id="payment_modeid"  value="card"> <label style="position: absolute;padding: 11px;">CARD</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="radio" name="payment_mode_cake" class="big" id="payment_modeid"  value="ewallet"> <label style="position: absolute;padding: 11px;">EWALLET</label>
												</div>		
												<div class="form-group" id="cardtypes_cake">
													<label><input type="radio" id="typecake" name="card_type_val_cake"  value="Visa"> <img width="130px" height="" id="cake_type_cakevisa" src="${pageContext.request.contextPath}/resources/images/VISA2.jpg" alt="Visa"> </label>
													<label><input type="radio" name="card_type_val_cake" id="typecake"  value="Master">  <img  width="105px" height="" id="cake_type_cakemaster" src="${pageContext.request.contextPath}/resources/images/mastercard2.png" alt="Master"> </label>
													<label><input type="radio" name="card_type_val_cake" id="typecake"  value="Mastero">  <img  width="105px" height="" id="cake_type_cakemaestro" src="${pageContext.request.contextPath}/resources/images/maestrocard1.png" alt="Mastero"></label>
													<label><input type="radio" name="card_type_val_cake" id="typecake"  value="AmericanExpress">  <img  width="105px" height="64px"  id="cake_type_cakeamerica" src="${pageContext.request.contextPath}/resources/images/3.png" alt="AmericanExpress"></label>
													<label><input type="radio" name="card_type_val_cake" id="typecake"  value="Discover">  <img  width="105px" height="" id="cake_type_cakediscover" src="${pageContext.request.contextPath}/resources/images/4.png" alt="Discover"></label>
													<!--<label><input type="radio" name="card_type_val_cake" id="typecake"  value="VisaElectronic">  <img  width="105px" height="" id="cake_type_cakevisaelectronic" src="${pageContext.request.contextPath}/resources/images/6.png" alt="VisaElectronic"></label>-->
												</div>
											</div>
											<div class="form-group" style="margin-top:30px;">
												<label id="msg_cakeorder" style="color:red;" for="">(*) Fields Are Required Fields</label>
											</div>		   
											<div class="form-group">
												<button type="button" class="btn btn-primary submitBtn" id="cakeorder_submit">Place Order</button>&nbsp;&nbsp;
												<button type="button" class="btn btn-primary close_order_Btn" id="cakeorder_close">Close</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div id="view_deliveryorderbuttons">
								<div class="modalviewcontent" style="border: 1px solid #ccc;">
									<div class="modal-header">
										<h5 class="modal-title" id="myModalLabel" style="text-align:center;">DELIVERY ORDER DETAILS</h5>
									</div>
									<div class="modal-body" id="viewdeliveryorderbuttons">
										<div class="row">
											<div class="col-sm-4">
												<button type="button" class="btn btn-primary delivery_button" data-id="1" id="delivery_1">1 Hour</button>&nbsp;&nbsp;
											</div>
											<div class="col-sm-4">
												<button type="button" class="btn btn-primary delivery_button" data-id="3" id="delivery_3">3 Hours</button>
											</div>
											<div class="col-sm-4">
												<button type="button" class="btn btn-primary delivery_button" id="delivery_6">6 Hours</button>
											</div>
										</div>
										<div class="row">&nbsp;</div>
										<div class="row">
											<div class="col-sm-4">
												<button type="button" class="btn btn-primary delivery_button" id="delivery_12">12 Hours</button>&nbsp;&nbsp;
											</div>
											<div class="col-sm-4">
												<button type="button" class="btn btn-primary delivery_button" id="delivery_24">24 Hours</button>&nbsp;&nbsp;
											</div>
											<div class="col-sm-4">
												<button type="button" class="btn btn-primary delivery_button" id="delivery_36">36 Hours</button>
											</div>
										</div>
										<div class="row">&nbsp;</div>
										<div class="row">
											<div class="col-sm-4">
												<button type="button" class="btn btn-primary delivery_button" id="delivery_72">72 Hours</button>
											</div>
											<div class="col-sm-4">
												<button type="button" class="btn btn-primary delivery_button" id="delivery_all">All Deliveries</button>
											</div>
										</div>
									</div>
								</div>
								<div class="row" style="padding: 22px;">
									<button type="button" class="btn btn-primary delivery_orders_close" id="deliveryorders_close">Close</button>
								</div>
							</div>     
							<!-- PRE ORDER FORM -->		  
							<div id="view_preorderdetails">
								<div class="modalviewcontent">
								<!-- Modal Header -->
									<div class="modal-header">
										<h5 class="modal-title" id="myModalLabel" style="text-align:center;">PRE ORDER DETAILS</h5>
									</div>
									<div class="modal-body" id="order_estimateviewdetails">
										<div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label for="cashier">Name&nbsp;<span style="color:red;">*</span></label>
													<input type="text" id="precustomer_name" class="form-control qwertyKeypad"  name="precustomer_name" value="">
												</div>	
												<div class="form-group">
													<label for="cashier">Address&nbsp;<span>(OPTIONAL)</span></label>
													<textarea class="form-control defaultKeypad" id="cust_address" name="cust_address"></textarea>
												</div>			
												<div class="form-group">
													<label for="dayout_date">Date & Time of Delivery&nbsp;<span style="color:red;">*</span></label>
													<div class="input-group date form_datetime col-md-5" data-date-format="dd MM yyyy - HH:ii p" data-link-field="dtp_input2">
														<input class="form-control" size="24" type="text" style="width:196px;" value="" readonly>
														<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
														<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
													</div>
													<input type="hidden" id="dtp_input2" value="" /><br/>
												</div>	
											</div>
											<div class="col-sm-6">
												<div class="form-group">
													<label for="cashier">Phone Number&nbsp;<span style="color:red;">*</span></label>
													<input type="text" class="form-control numKeypad" id="prephone_num" name="prephone_num" value="">
												</div>	
												<div class="form-group">
													<label for="cashier">Advance Amount&nbsp;<!--<span style="color:red;">*</span>--></label>
													<input type="text" class="form-control numKeypad" id="preadvance" name="preadvance" value="">
												</div>
                                                                                                <div class="form-group">
                                                                                                            <label for="addons">AddOns[Mention size of the item]&nbsp;<!--<span style="color:red;">*</span>--></label>
                                                                                                            <textarea class="form-control defaultKeypad" id="addons" name="addons"></textarea>
                                                                                                    </div>
											</div>
										</div>	
										<div class="row">
											<div class="col-sm-12">
												<div class="form-group">
                                                                                                    <div class="row">
													<label for="cashier">Payment Mode&nbsp;</label>
													<input type="radio" class="big" id="payment_modeid" name="payment_mode" checked value="cash"> <label style="position: absolute;padding: 11px;">CASH </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="radio" name="payment_mode" class="big" id="payment_modeid"  value="card"> <label style="position: absolute;padding: 11px;">CARD</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="radio" name="payment_mode" class="big" id="payment_modeid"  value="ewallet"> <label style="position: absolute;padding: 11px;">EWALLET</label>
                                                                                                    </div>
                                                                                                    </div>		
												<div class="form-group" id="cardtypes">
													<label><input type="radio" id="card_type" name="card_type_val"  value="Visa"> <img width="100px" height="" id="card_type_visa" src="${pageContext.request.contextPath}/resources/images/VISA2.jpg" alt="Visa"> </label>
													<label><input type="radio" name="card_type_val" id="card_type"  value="Master">  <img  width="90px" height="" id="card_type_master" src="${pageContext.request.contextPath}/resources/images/mastercard2.png" alt="Master"> </label>
													<label><input type="radio" name="card_type_val" id="card_type"  value="Mastero">  <img  width="90px" height="" id="card_type_maestro" src="${pageContext.request.contextPath}/resources/images/maestrocard1.png" alt="Mastero"></label>
													<label><input type="radio" name="card_type_val" id="card_type"  value="AmericanExpress">  <img  width="90px" height="64px"  id="cake_type_america" src="${pageContext.request.contextPath}/resources/images/3.png" alt="AmericanExpress"></label>
													<label><input type="radio" name="card_type_val" id="card_type"  value="Discover">  <img  width="90px" height="" id="cake_type_discover" src="${pageContext.request.contextPath}/resources/images/4.png" alt="Discover"></label>
												</div>	
												<div class="form-group" id="ewallet_preorder">
													<label><input type="radio" id="preorderpaytm" name="ewallet_val_preorder"  value="Paytm"> <img width="130px" height="" id="preorder_type_paytm" src="${pageContext.request.contextPath}/resources/images/paytm.jpg" alt="Paytm"> </label>
													
												</div>
												<div class="form-group" style="margin-top:6px;">
													<label id="msg_preorder" style="color:red;" for="">(*) Fields Are Required Fields</label>
												</div>		   
												<div class="form-group">
													<button type="button" class="btn btn-primary submitBtn" id="preorder_submit">Place Order</button>&nbsp;&nbsp;
													<button type="button" class="btn btn-primary close_order_Btn" id="preorder_close">Close</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>  
							<table id="leftmain_table" width="100%" border="0">
								<tr>
									<td  valign="top"><table width="100%" id="add_sub_categories" border="0"></table></td>
									<td  valign="top" align="center"><table width="99%" border="0" class="add_items_list"></table></td>
									<td  valign="top" style="margin:"><table width="100%" class="add_unit_list"></table></td>
								</tr>
								<tr style="">
									<td><table width="100%"><tr>
										<td>
											<button class="_button _button-57 sub_cat_nav" id="sub_cat_prev" data-value="0" >&lt;</button>
										</td>
										<td></td>
										<td> 
											<button class="_button _button-57 sub_cat_nav" id="sub_cat_next" data-value="1" >&gt;</button>
										</td>
									</tr></table></td>
									<td><table width="100%"><tr>
										<td width="20%">
											<button class="_button _button-57 items_nav" id="items_prev" data-value="0">&lt;</button>
										</td>
										<td width="60%"><button class="_button search_btn" style="background:#888888;height:54px;" id="items_pagination_disp" >-/-</button> </td>
										<td width="20%"> 
											<button class="_button _button-57 items_nav" id="items_next"  data-value="1">&gt;</button>
										</td>
									</tr></table></td>
									<!--<td>
										<button class="_button _button-57" id="barimage">Barcode</button>
									</td>-->
									<td>
										<button class="_button _button-57" id=""></button>
									</td>
								</tr>
								<tr>
									<td width="20%">
										 <button class="_button _button-57" id='voucher'>Voucher</button><!--<img src="${pageContext.request.contextPath}/resources/images/keypad.png"  class="keyboard_img"/>-->
									</td>
									<td width="60%" align="center"><table width="99%"><tr>
										<td><!--<img src="${pageContext.request.contextPath}/resources/images/keypad.png"/>--><input type="text" class="search_input" id="defaultKeypad"></td>
										<td width="20%"><button class="_button search_btn" id="barcodescanning">BARCODE</button></td>
										<td width="33%"><button class="_button search_btn" id="upcean">UPC/EAN</button></td>
									</tr></table></td>
									<td width="20%">
										<button class="_button search_btn clear_btn" id="clear_button">CLEAR</button>
									</td>
								</tr>
							</table>
					</div>
				</div>
				<div class="col-sm-6" style="border: 1px solid #e8e8e8;    border-left: 0px;padding:5px;margin:0px;height:650px;">
					<div class="right_header">
						<table width="100%" border="0">
							<tr>
								<td width="18%"><button id="payment_btn" class="button1 button1_sub grdtot">Rs 0.00/-</button></td>
								<td width="18%"><button class="button1 button1_sub openorder" >OPEN ORDER</button></td>
								<td width="18%"><button class="button1 button1_sub billparkview" >BILL PARK</button></td>
								<td width="18%"><button class="button1 btn_searchorder">SEARCH ORDER</button></td>
								<td width="18%"><button class="button1 button1_sub " id="bill_discount_btn" >DISCOUNT</button></td>
								<td width="10%"><button class="button1 clear_all_data" title="Clear data" style="font-weight:bold;">CLEAR DATA</button></td>
							</tr>
						</table>
					</div>
					<div class="right_content">
						<div class="selected_items_div">
							<!-- Return Orders-->
							<div class="" id="returnorder">
							<!-- Modal Header -->
								<!--<div class="modal-header">
								<h5 class="modal-title" id="myModalLabel" style="text-align:center;">SEARCH ORDER</h5>
								</div>-->
								<div class="modal-body">
									<div id="errormessage"></div>
									<div class="row">
										<div class="col-sm-1modalsearchcontent2" >
											<div class="form-group">
												<!--<label for="orderid">OrderID</label>-->
												<input type="text" class="form-control numKeypad" id="orderid_return" name="orderid_return" placeholder="OrderId" style="width:77%;display: inline-block;"/>&nbsp;&nbsp;
												<button type="button" class="btn btn-primary returnorder_submitBtn" id="searchorder_returnsave" data-id="OrderId">SEARCH</button>                    
											</div>
								</div>
								</div>
								</div> 
								<div class="modal-footer">
									<button type="button" class="btn btn-primary searchorderBtnclose" id="billparkBtnclose" >Close</button>
								</div>
							</div>
							<div class="modalsearchcontent" id="searchorder">
							<!-- Modal Header -->
								<!--<div class="modal-header">
								<h5 class="modal-title" id="myModalLabel" style="text-align:center;">SEARCH ORDER</h5>
								</div>-->
								<div class="modal-body">
									<div id="errormessage"></div>
									<div class="row">
										<div class="col-sm-12" >
											<div class="form-group">
												<!--<label for="orderid">OrderID</label>-->
												<input type="text" class="form-control defaultKeypad" id="orderid" name="orderid" placeholder="OrderId" style="width:77%;display: inline-block;"/>&nbsp;&nbsp;<button type="button" class="btn btn-primary searchorder_submitBtn" id="searchordersave" >SEARCH</button>                    
											</div>
											<%
											     if(ordersData.size()<=0){
											    	 out.print("<span id=\"span_style\"> No Orders Today</span>");
											     }else{
											    	 double total1 = 0d;		
											    	 int count1 = 0;
											    	 
											%>  	 
											 
											<div id="heading_orders">Today's Orders List</div>
											<c:set var="total" value="${0}"/>
											<c:set var="count" value="${0}"/>
											<table border="1" id="orderlist_table" width="100%">
												<thead>
													<tr>
														<th>Invoice No</th>
														<!-- <th>No</th>-->
														<th>BillAmount</th>
														<th>Status</th>
														<!--<th>Balance Amount</th>-->
														<th>Time</th>
														<th>Action</th>
													</tr>
												</thead>
												<c:forEach items="${ordersData}" var="ordersData">
												<c:set var="billno" value="${ordersData.billno}"/>
                                                     <%
                                                          String billNoStr = "";                                                        
                                                          Long billnos =  (Long)pageContext.getAttribute("billno");
                                                          String billno = "";
                                                           try{
                                                               billno = String.valueOf(billnos);
                                                           }catch(Exception e){
                                                               e.printStackTrace();
                                                           }
                                                      //    System.out.println("billno is:"+billno);
                                                          String billTypeNo = billno.substring(0,2);
                                                      //   System.out.println("First 2 digits of number is:"+billTypeNo);
                                                          System.out.println("*******billSubString is:*******:"+billTypeNo);
                                                          if(billTypeNo.equals("88")){
                                                              String outletPlusYear = "88"+year+outlet_uuidCnct;
                                                              billNoStr = billno.replace(outletPlusYear, "");
                                                          }else{
                                                              String outletPlusYear = year+outlet_uuidCnct;
                                                              billNoStr = billno.replace(outletPlusYear, "");
                                                          }
                                                      //    System.out.println("after the replace billNoStr is:"+billNoStr);                                                     
                                                     %>
												
												   <c:set var="bal_amt" value="${ordersData.paid_amt - ordersData.balance_amt}" />
												   <tr>
													<td align="center" width="35%">
														<input type="hidden" name="order_idvalue" value="<c:out value="${ordersData.billno}"/>" id="order_idvalue" >
														<%-- <c:out value="${ordersData.billno}"/> --%>
														<%=billNoStr%>
													</td>
													<td align="center"  width="10%">
														<c:out value="${ordersData.bill_amt}"/>
													</td>
													<td  align="center" width="10%">
														<%-- <c:out value="paid" /> --%>
														<c:choose>
													       <c:when test="${bal_amt >=0}">
													              <c:out value="Paid"/>
													       </c:when>
													       <c:otherwise>
													              <c:out value="Pending"/>
													       </c:otherwise>
													 	</c:choose>
													</td>
													<td width="10%">
														<c:out value="${ordersData.created_date}"/>
													</td>
													<td width="40%">
														<img title="View Order" src="${pageContext.request.contextPath}/resources/images/preview.png" id="btn_vieworder" width="45px" height="50px" data-id="<c:out value="${ordersData.billno}"/>">
														<img title="Print Order" src="${pageContext.request.contextPath}/resources/images/print.ico" width="35px" height="35px" id="printimg_submitBtn" data-id="<c:out value="${ordersData.billno}"/>">
													</td>												
												</tr>
												       <c:set var="PaidAmount" value="${0}"/>
													   <c:choose>
													       <c:when test="${ordersData.balance_amt < 0}">
													              <c:set var="PaidAmount" value="${ordersData.paid_amt + ordersData.balance_amt}"/>
													       </c:when>
													       <c:otherwise>
													              <c:set var="PaidAmount" value="${ordersData.paid_amt}"/>
													       </c:otherwise>
													   </c:choose>
												      <c:set var="total" value="${total + PaidAmount}"/>
											</c:forEach>
 
											</table>
											 <%    
											      }
											 %>
										</div>
	
									<!-- 	</div> -->
										<% if(transactionSumData>0){%>
                                        <label style="font-weight:bold;">Total Sale Amount:</label>&nbsp;Rs&nbsp;<%=transactionSumData%>/-
                                        <% } %>
									</div>
								</div> 
								<div class="modal-footer">
									<button type="button" class="btn btn-primary searchorderBtnclose" id="billparkBtnclose" >Close</button>
								</div>
							</div>
							<!-- Delivery orders --> 
							<div class="modalsearchcontent" id="deliveryorderdetails"></div> 
							<table id="main_table" width="100%" class="sitems_gird" border="0" cellpadding="2" cellspacing="2">
								<thead>
									<tr>
										<th width="4%">#</th><th width="20%">Item Name</th><th width="4%">Qty</th><th width="10%">Unit</th><th width="10%">Size</th><th width="10%">Price</th><th width="10%">Dis</th><th width="7%">GST</th><th width="10%">Tot Price</th><th width="17%" > Actions</th>
									</tr>
								</thead>
								<tbody id="final_items"></tbody>
								<tfoot>
									<tr><td align="right" id="returnamount"></td></tr>
									<tr id="footstyle">
										<td>Qty: </td><td class="qalltot" width="10%">0</td>
										<td>CGST: </td><td width="10%"><span id="cgst_amt_span">0</span> </td>
										<td>SGST: </td><td width="10%"><span id="sgst_amt_span">0</span></td>
										<td  align="right" colspan="5">Total: </td>
										<td style="color:red;" width="20%"><b><span class="grdtot grdtot_font"> Rs 0.00/-</span></b></td>
										<td width="10%"> <button id="up_down_btn" class="_button search_btn clear_btn" rel="down" style="background: deepskyblue;height: 30px;"> <img style="width:43%" src="${pageContext.request.contextPath}/resources/public/images/down_up5.png" /> </button></td>
									</tr>
								</tfoot>
							</table>
						</div>
						<div class="right_bottom_div">
							<table width="100%">
								<tr>
									<td width="50%" valign="top">
										<div class="display_screen" id="welcome_screen_div" style="display:block;">
											<p style="margin:10px 20px;font-size:14px;"><b> Welcome To ERP </b></p>
											<div class="pos-clock" id="terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_scan_scanTabContent_clock"><div class="clock-time" id="terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_scan_scanTabContent_clock_clock">15:45</div><div id="terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_scan_scanTabContent_clock_date"><?php echo date("Y-m-d");?></div>
											</div>
										</div>
										<div class="display_screen" id="payment_srn_div" style="padding:5px;display:none;">
											<span style="width:70%;float:left;border-bottom:1px solid gray;"><p style="font-size: 11px;" id="payment_amt_p"><b> <b style="font-size: 17px;"><span class="pay_grdtot">0ff</span> </b> remaining to pay </b></p></span>
											<span style="width:30%; float:left;">
												<button style="width: 85%; max-width: 125px; float: right;height: 2.5em; display: block; clear: right; font-weight: normal; padding: 0px;" class="btnlink" id="btn_amt"><img src="${pageContext.request.contextPath}/resources/public/${pageContext.request.contextPath}/resources/images/iconCheck.png"></button>
											</span>
											<div>
												<table width="100%" id="price_tbl">
													<!-- <tr> <th> Cash </th> <td> 200.00 </td><td> <div id="del_price_div_1" class="del_price_div"> X </div> </td></tr>
													<tr> <th> Voucher </th> <td> 200.00 </td><td> <div class="del_price_div" > X </div> </td></tr> -->
												</table>
											</div>
											<span id="card_popup"></span>
										</div>
										<div class="display_screen" id="bill_discount_srn_div" style="display:none;" >
											<span style="width:80%;float:left;border-bottom:1px solid gray;height: 45px;"><p style="font-size: 12px;margin: 3%;" id=""><b>Total Bill(before GST) : <b style="font-size: 17px;"><span class="disc_befgst_amt" id="disc_befgst_amt">â¹0/-</span> </b></b></p></span>
											<span style="width:20%; float:left;">
												<button style="width: 85%; max-width: 125px; float: right;height: 2.5em; display: block; clear: right; font-weight: normal; padding: 0px;background-color:red;color:white;" class="btnkeyboard btn_del_dis " id="btn_del_dis">X</button>
											</span>
											<table width="100%">
												<tr><th><span id="addt_dis_amt" style="font-size: 11px; color: forestgreen;"></span></th></tr>
												<tr><td id="adt_dis_items_list"></td></tr>
											</table>
										</div>
										<div class="display_screen" id="edit_item_srn_div" style="display:none;">
											<table width="100%" class="edit_item_cls">
												<tr>
													<th width="30%" valign="top">
														<img style="width: 120px;height: 95px;" src="${pageContext.request.contextPath}/resources/images/item.jpg">
													</th>
													<th width="70%" valign="top">
                                                                                                                <button rel="-" class="btnkeyboard" style="background-color:#843232;width:50%;width: 45%;display: inline-block;height: 45px;">-</button>
												                <button rel="+" class="btnkeyboard"  style="background-color:#288828;width:50%;display: inline-block;height: 45px;">+</button>
														<div id="prod_name"><b>PANEER KALAKAND</b> </div>
														<div id="prod_desc"><i class="prod_desc_cls" id="prod_desc">You can add description here from items.</i> </div>
													</th>
												</tr>
											</table>
											<table width="100%" class="edit_item_cls">
												<tr>
													<th width="45%">QUANTITY </th><td> &nbsp; &nbsp; &nbsp; &nbsp;:</td>
													<td>
														<span id="prod_qty"> 5</span><input name='quantity' class='quant_text' style="display:none;" type='text' id='quantity_newval' value='' style="width: 44px !important;">
														<span id ="span_weight" style="display:none;"></span>
													</td>
												</tr>
												<tr>
													<th>PRICE </th><td> &nbsp; &nbsp; &nbsp; &nbsp;:</td>
													<td>
														<span id="prod_price">400</span><input name='price' class='displayBox' style="display:none;" type='text' id='price_newval' value='' style="width: 44px !important;">
													</td>
												</tr>
												<tr>
													<th>DISCOUNT </th><td> &nbsp; &nbsp; &nbsp; &nbsp;:</td>
													<td>
														<span id="prod_discount"> 5%</span><input name='discount' class='dis_text' style="display:none;" type='text' id='dis_newval' value='' style="width: 44px !important;">
													</td>
												</tr>
                                                                                                <tr>
													<th>SIZE </th><td> &nbsp; &nbsp; &nbsp; &nbsp;:</td>
													<td>
														<span id="prod_size"> 5%</span><input name='size' class='dis_text' style="display:none;" type='text' id='size_newval' value='' style="width: 44px !important;">
													</td>
												</tr>
												<!--<tr>
													<th>CGST (<span id="prod_cgst">(5%)</span>%) </th><td>:</td>
													<td><span id="cgst_amt">20</span></td>
												</tr>
												<tr>
													<th>SGST (<span id="prod_sgst">(5%)</span>%) </th><td>:</td>
														<td><span id="sgst_amt">20</span></td>
												</tr>-->
												<tr>
													<th>TOTAL PRICE </th><td> &nbsp; &nbsp; &nbsp; &nbsp;:</td>
													<td><span id="prod_tot_price"> 500 </span></td>
												</tr>
											</table>
											<table width="100%" style="margin-top:10px;">
												<tr>
													<td width="50%"><button rel="" class="button6" style="" >CHECK STOCK</button></td>
													<!--<td width="50%"> <img src="${pageContext.request.contextPath}/resources/public/${pageContext.request.contextPath}/resources/images/delete_btn.png" style="width: 30px;" /></td>-->
													<td width="50%"> 
														<a data-item="1" data-value="7" data-id="delete" class="item_unit_action" href="" data-toggle="modal" data-target=""><button rel="" class="button6" style="background:#e24545;color:white;" >DELETE</button></a>
													</td>
													<!--<td width="50%"><button rel="" class="button6" style="" >Return Line</button></td>-->
												</tr>
											</table>
											<input type="hidden" id="hdn_item_portion_id" value="" />
										</div>
									</td>
									<td width="50%" valign="top"><table width="100%" id="calc_tbl" class="calc_tbl" >
										<tr>
											<td colspan="3">
												<div class="displayBox"><p class="displayText" id="display"></p></div>
											</td>
											<td>
												<button class="btnkeyboard" rel="back" id="back_val" style="background:lightgray;"><img src="${pageContext.request.contextPath}/resources/images/iconBackspace.png"></button>
											</td>
										</tr>
										<tr>
											<td width="23%"><button rel="/" class="btnkeyboard">/</button></td>
											<td width="23%"><button rel="*" class="btnkeyboard">*</button></td>
											<td width="23%"><button rel="%" class="btnkeyboard">%</button></td>
											<td width="31%">
												<button rel="cash" class="btnkeyboard btnkeyboard_actions btnselected pay_btns fun_btns">Cash</button>
												<button rel="quantity" class="btnkeyboard btnkeyboard_actions edit_item_btn_cls fun_btns" id="btn_fnc_qty" >QUANTITY</button>
												<button rel="" class="btnkeyboard disabled_button disc_btn_cls fun_btns" >&nbsp;</button> 
											</td>
										</tr>
										<tr>
											<td><button rel="7" class="btnkeyboard">7</button></td>
											<td><button rel="8" class="btnkeyboard">8</button></td>
											<td><button rel="9" class="btnkeyboard">9</button></td>
											<td>
												<button class="btnkeyboard btnkeyboard_actions pay_btns fun_btns" rel="card" >Card</button>
												<button rel="price" class="btnkeyboard btnkeyboard_actions edit_item_btn_cls fun_btns"  id="btn_fnc_price">PRICE</button>
												<button rel="" class="btnkeyboard disabled_button disc_btn_cls fun_btns" >&nbsp;</button> 
											</td>
										</tr>
										<tr>
											<td><button rel="4" class="btnkeyboard">4</button></td>
											<td><button rel="5" class="btnkeyboard">5</button></td>
											<td><button rel="6" class="btnkeyboard">6</button></td>
											<td>
												<button rel="discount" class="btnkeyboard btnkeyboard_actions edit_item_btn_cls fun_btns" id="btn_fnc_dis">DISCOUNT</button>
												<button rel="discount" class="btnkeyboard btnkeyboard_actions disc_btn_cls fun_btns" id="btn_fun_fdisc" >DISCOUNT</button>
												<table width="100%"><tr>
												<td ><button class="btnkeyboard btnkeyboard_actions pay_btns fun_btns" rel="voucher fun_btns" >Voucher</button></td>
												<!--<td width="50%"><button class="btnkeyboard btnkeyboard_actions pay_btns fun_btns" rel="ewallet" >EWallet</button></td>-->
												<!--<button class="btnkeyboard btnkeyboard_actions pay_btns fun_btns" rel="ewallet" >EWallet</button>-->
											</tr></table>
												
												<!--<button class="btnkeyboard btnkeyboard_actions pay_btns fun_btns" rel="voucher fun_btns" >Voucher</button>
												<button rel="discount" class="btnkeyboard btnkeyboard_actions edit_item_btn_cls fun_btns" id="btn_fnc_dis">DISCOUNT</button>
												<button rel="discount" class="btnkeyboard btnkeyboard_actions disc_btn_cls fun_btns" id="btn_fun_fdisc" >DIShCOUNT</button> -->
											</td>
										</tr>
										<tr>
											<td><button rel="1" class="btnkeyboard">1</button></td>
											<td><button rel="2" class="btnkeyboard">2</button></td>
											<td><button rel="3" class="btnkeyboard">3</button></td>
											<td><table width="100%"><tr>
                                                                                                   <td><button rel="size" class="btnkeyboard btnkeyboard_actions edit_item_btn_cls fun_btns"  id="btn_fnc_size">SIZE</button></td>
												<!--<td width="50%"><button rel="-" class="btnkeyboard" style="background-color:#843232;">-</button></td>
												<td width="50%"><button rel="+" class="btnkeyboard"  style="background-color:#288828;">+</button></td>-->
												<button class="btnkeyboard btnkeyboard_actions pay_btns fun_btns" rel="ewallet" >EWallet</button>
												<button rel="" class="btnkeyboard disabled_button disc_btn_cls fun_btns" >&nbsp;</button>
											</tr></table></td>
										</tr>
										<tr>
											<td colspan="2"><button rel="0" class="btnkeyboard">0</button></td>
											<td><button rel="." class="btnkeyboard">.</button></td>
											<td>
												<button rel="=" class="btnkeyboard" id="equal_button" style="background-color:lightgrey;"><img src="${pageContext.request.contextPath}/resources/images/iconEnter.png"></button>
											</td>
										</tr>
									</table></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer_div">
			<div class="fun_div">
				<table width="100%">
					<tr>
						<td>
							<div id="menu" class="button9">
								<ul>
									<li><center><a href="#">Menu</a></center>
										<ul>
												<!-- <li><a href="#">Customers</a></li> -->
												<%--<li><a target="_blank" href="admin/index.php?ref=<?php echo $btn_encode_url['dashboard']; ?>">Backend/Backoffice</a></li> --%>
                                                      <% int i=1; %>
                                                      <c:forEach items="${orderTypes}" var="orderTypes">
                                                      	<li style="background:<c:out value="${orderTypes.bck_color}"/>;cursor:pointer;">
                                                      		<a id="ordertypes_<%=i %>" data-name="<c:out value="${orderTypes.ordertype_name}"/>" data-id="<c:out value="${orderTypes.uuid}"/>|||<c:out value="${orderTypes.added_perc}"/>"><c:out value="${orderTypes.ordertype_name}"/></a>
                                                      	</li>
                                                      	<%i++; %>
                                                      </c:forEach>
										</ul>
									</li>   
								</ul>
							</div>
							<!--<button class="button9" id="menu_button">Menu</button>-->
						</td>
						<!--<td><button class="button9" id="refresh_button"><img src="${pageContext.request.contextPath}/resources/images/refresh_white.png" alt="refresh"  width="16%">Refresh</button></td>-->
						<td><button class="button9" id='return_order'>Return Order</button></td>      
						
						<!--<td><button class="button9 btn_reprint">Re Print</button></td>-->
						<td><button class="button9" id="deliveryorders">Delivery Orders</button></td>      
						<td><button class="button9" id="btob">B 2 B</button></td>
						<td><button class="button9" id="pre_order">Cake / Pre Order</button></td>
						<td><button class="button9" id="estimate_order">Estimate Order</button></td>
						<td><button class="button9" id="tokenbill_button">Token Bill</button></td>
						<td><button id ="cashbill_button" class="button9">Cash Bill </button></td>
					</tr>
				</table>
			</div>
		</div>
		<input type="hidden" name="item_hidden_val" id="item_hidden_val" value="">
		<input type="hidden" name="item_dis_val" id="item_dis_val" value="">
                <input type="hidden" name="item_size" id="item_size" value="">
		<input type="hidden" name="selected_maincat_id" id="selected_maincat_id" value="" />
		<input type="hidden" name="selected_subcat_id" id="selected_subcat_id" value="" />
		<input type="hidden" name="selected_item_id" id="selected_item_id" value="" />
	</div>
	<div class="container">
		<!-- <h2>Modal Example</h2> -->
		<!-- Trigger the modal with a button -->
		<!-- <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button> -->
		<!-- Delete Transaction in search order --->
		<div class="modal fade" id="deleteoRderedModal" role="dialog">
			<div class="modal-dialog">
			<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">DELETE ORDER</h4>
					</div>
					<div class="modal-body"><p>Are you sure want to delete this transaction?</p></div>
					<div class="modal-footer">
						<input type="hidden" name="del_ordered_val" id="del_ordered_val" value="">
						<button data-id="" type="button" class="btn btn-danger delete_ordered" id="delete_ordered">Delete</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<!--Delete  Modal -->
		<div class="modal fade" id="deleteModal" role="dialog">
			<div class="modal-dialog">
			<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">DELETE ITEM</h4>
					</div>
					<div class="modal-body"><p>Are you sure want to delete this item?</p></div>
					<div class="modal-footer">
						<input type="hidden" name="del_item_val" id="del_item_val" value="">
						<button data-id="" type="button" class="btn btn-danger delete_item" id="delete_item">Delete</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="common_model" role="dialog"></div>
		<!-- Dayout Modal-->
		<div class="modal fade" id="logout_Modal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p style="text-align: center;">Are you Sure To Logout?</p></div>
					<div class="modal-footer">
						<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary oklogout_item" id="oklogout_item">Yes</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="check_dayoutModal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
				<!-- <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Day Out</h4>
				</div>-->
					<div class="modal-body"><p style="text-align: center;">'Day End' is not done, Do you want to 'Day End'?</p></div>
					<div class="modal-footer">
						<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary okdayout_item" id="okdayout_item">Yes</button>
						<button type="button" class="btn btn-default" data-dismiss="modal" id="okdayoutclose_item">No</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Dayout Modal-->
		<div class="modal fade" id="preorder_Modal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p style="text-align: center;">Please Select Items First</p></div>
					<div class="modal-footer">
						<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary" id="">Ok</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="btobprint_Modal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><label>Delivery Person</label>
                                            <select  class="deliveryperson" name="deliveryperson"  id="deliveryperson">
                                                <option value="Customer">Customer</option>
                                                <option value="Transporter">Transporter</option>
                                            </select>
						</div>
					<div class="modal-footer">
						<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary" id="btn_delivery">Ok</button>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="modal fade" id="cashbill_Modal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p id="ordertype_msg" style="text-align: center;">Please Select Items First</p></div>
					<div class="modal-footer">
					<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary" id="">Ok</button>
					</div>
				</div>
			</div>
		</div>
        <div class="modal fade" id="order_Modal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p id="" style="text-align: center;">Bill is not inserted in database. please try again...</p></div>
					<div class="modal-footer">
					<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary" id="order_id">Ok</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="printer_settings_modal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p id="" style="text-align: center;">Please Check Printer Settings!....</p></div>
					<div class="modal-footer">
					<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary" id="printer_settings_id">Ok</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="returnitem_Modal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p style="text-align: center;">Please Select Return Items First</p></div>
					<div class="modal-footer">
					<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary" id="">Ok</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="estimatebill_Modal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p style="text-align: center;">Please Select Items First</p></div>
					<div class="modal-footer">
						<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary" id="">Ok</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="cakeorder_Modal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p style="text-align: center;">Please Select Items First</p></div>
					<div class="modal-footer">
						<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary" id="">Ok</button>
					</div>
				</div>
			</div>
		</div>
		<!-- Delete Park Order modal -->  
		<div class="modal fade" id="delete_parkorderModal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p style="text-align: center;">Are You Sure Want To Delete Park Order</p></div>
					<div class="modal-footer">
						<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary okparkorder_item" id="okparkorder_item">Ok</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<!--Bill Parked Success  Modal -->
		<div class="modal fade" id="bill_parksuccesModal" role="dialog">
			<div class="modal-dialog">
			<!-- Modal content-->
				<div class="modal-content" style="width:40%">
					<!--<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">DELETE ITEM</h4>
					</div>-->
					<div class="modal-body"><p style="font-size: 14px;text-align: center;">Bill Parked Successfully</p></div>
					<div class="modal-footer">
					<button data-id="" type="button" data-dismiss="modal" class="btn btn-primary ok_item" id="ok_item">Ok</button>
					<!--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>-->
					</div>
				</div>
			</div>
		</div>
		<!---- Order Deleted Successfully-->
		<div class="modal fade" id="orderdeleteModal" role="dialog">
			<div class="modal-dialog">
			<!-- Modal content-->
				<div class="modal-content" style="width:40%">
					<!--<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">DELETE ITEM</h4>
					</div>-->
					<div class="modal-body"><p style="font-size: 14px;text-align: center;">Order Removed Successfully</p></div>
					<div class="modal-footer">
						<button data-id="" type="button" data-dismiss="modal" class="btn btn-primary ok_order" id="ok_order">Ok</button>
						<!--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>-->
					</div>
				</div>
			</div>
		</div>
		
		<!-- UPCEAN Modal-->
		<div class="modal fade" id="upcean_Modal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p style="text-align: center;">Please Enter EAN 13 Digit Code</p></div>
					<div class="modal-footer">
					<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary" id="">Ok</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Voucher Modal -->
			<div class="modal fade" id="voucher_Modal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p style="text-align: center;" id="voucher_error">Please Enter Voucher Code</p></div>
					<div class="modal-footer">
					<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary" id="">Ok</button>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="modal fade" id="creditnotestatus_Modal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p style="text-align: center;">Credit Note Already Used</p></div>
					<div class="modal-footer">
					<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary" id="">Ok</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="creditnotemsgModal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p style="text-align: center;">Please select the items Which are need to Be credited</p></div>
					<div class="modal-footer">
					<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary" id="">Ok</button>
					</div>
				</div>
			</div>
		</div>
                <div class="modal fade" id="creditnoteusedmsgModal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p style="text-align: center;">Credit Amount Already Given On This Bill</p></div>
					<div class="modal-footer">
					<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary" id="">Ok</button>
					</div>
				</div>
			</div>
		</div>
                <div class="modal fade" id="returndate_Modal" role="dialog">
			<div class="modal-dialog" style="width: 34%;">
			<!-- Modal content-->
				<div class="modal-content">
					<!-- <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Day Out</h4>
					</div>-->
					<div class="modal-body"><p style="text-align: center;">Time Period Exceeded Order Is Not Valid For Return</p></div>
					<div class="modal-footer">
					<button data-id="" type="button" data-dismiss="modal"  class="btn btn-primary" id="">Ok</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="print_data"></div><div id="token_print_data"></div>
	<div id="printbtob_data"></div>
        <div id="printpreorder_total_data"></div>
        <div id="printbtob_total_data"></div>
	<!--- DAY IN Screen---------------->
	 <%  
	  try{	
		  Long dayInID = 0L;
		  try{		  
		  dayInID = day_in_data.getDay_In_Out_AutoID();
		  }catch(Exception e){
			  e.printStackTrace();
		  }
	     	if(day_in_data==null || dayInID ==0L) { %>	  	
			<script type="text/javascript"> 
				$( function() {
		            $( "#dayindiv" ).dialog({
		                  modal: true		     
		            });
		         } );
			</script>
	<%       }
	     }catch(Exception e){
		  e.printStackTrace();	
		 }
	%>
	<!-- Saritha Print Modal-->
	<div class="modal-dialog" id="billpark"> 
		<div class="modal-content">
		<!-- Modal Header -->
			<div class="modal-header">
				<!-- <button type="button" id="dayincancel" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span>
					<span  class="sr-only">Close</span>
				</button>  -->
				<h5 class="modal-title" id="myModalLabel" style="text-align:center;">Bill Park Order</h5>
			</div>
			<!-- Modal Body -->
			<div class="modal-body">
				<p class="billpark_msg"></p>             
				<div class="row">
					<div class="col-sm-12" >
						<span id="phonenumber_msg" style="display:none;text-align:center;color:red;">Order Number Already Exists</span>
						<div class="form-group">
							<label for="phone_number">Order Number</label>
							<input type="text" class="form-control defaultKeypad" required id="phone_number" placeholder="Enter 2 digit number" name="phone_number" />
						</div>   
						<!--<div class="form-group">
						<label for="phone_number">Advanced Amount</label>
						<input type="text" class="form-control numKeypad" required id="advance_amount" placeholder="Enter advanced amount" name="advance_amount" />
						</div>   -->
					</div>
				</div>
			</div>
			<!-- Modal Footer -->
			<div class="modal-footer">      
				<button type="button" class="btn btn-primary billparkBtnclose" id="billparkBtnclose" >Close</button>
				<button type="button" class="btn btn-primary billpark_submitBtn" id="billparksave" >SAVE</button>
			</div>
		</div>
	</div>
	<div class="modal-dialog" id="openorder"> 
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<h5 class="modal-title" id="myModalLabel" style="text-align:center;">OPEN ORDER DETAILS</h5>
			</div>
			<!-- Modal Body -->
			<div class="modal-body">  
                            <p class="openorder_msg" style="font-weight:bold;color:red;"></p>
				<div class="row"><div class="col-sm-2" ><label class="openorderlabel" for="open_order_price">Qty</label></div><div class="col-sm-3"><input type="text" class="form-control numKeypad" id="open_order_qty" name="open_order_qty" value="" />   </div><div class="col-sm-7"><ul class="order-unit"><li><input type="radio" id="KG" name="unit" value="KG" /><label for="KG">KG</label></li><li><input type="radio" id="PC" name="unit" value="PC"  /><label for="PC">PC</label></li><li><input type="radio" id="Unit" name="unit" value="Unit" /><label for="Unit">Unit</label></li></ul></div></div><div class="row"><div class="col-sm-2"><label class="openorderlabel" for="open_order_price">Price </label></div><div class="col-sm-5" ><input type="text" class="form-control numKeypad" id="open_order_price" name="open_order_price" value="" width="125%" style="margin-bottom: 6px;" /></div><div class="col-sm-5" ></div></div><div class="row"><div class="col-sm-2"><label class="openorderlabel" for="open_order_tax">Tax</label></div><div class="col-sm-10"><ul class="order-tax"><li><input type="radio" id="tax0" value="0" name="tax" /><label for="tax0">0%</label></li><li><input type="radio" id="tax5" value="5"  name="tax" /><label for="tax5">5%</label></li><li><!--<input type="radio" id="tax12" value="12"  name="tax" />--><label for="tax12" style="background: #DDD;cursor:none;">12%</label></li><li><input type="radio" id="tax18" value="18"  name="tax" /><label for="tax18">18%</label></li><li><!--<input type="radio" id="tax28" value="28"  name="tax" />--><label for="tax28" style="background: #DDD;cursor:none;">28%</label></li></ul></div></div>
				<div id="orderadd" ></div>
			</div>
			<!-- Modal Footer -->
			<div class="modal-footer"> 
				<button type="button" class="btn btn-primary openorderBtnclose" id="openorderclose" >Close</button>
				<button type="button" class="btn btn-primary openorderBtnadd" id="openorderadd" >+</button>
				<button type="button" class="btn btn-primary openorderBtnminus" disabled='disabled' id="openorderminus" >-</button>
				<button type="button" class="btn btn-primary openorderBtn" id="openordersave" >SAVE</button>
			</div>
		</div>
	</div>
	
	<div class="modal-dialog" id="dayindiv"> 
		<div class="modal-content">
		<!-- Modal Header -->
			<div class="modal-header">
				<!--   <button type="button" id="dayincancel" class="close" data-dismiss="modal">
				<span aria-hidden="true">&times;</span>
				<span  class="sr-only">Close</span>
				</button>  -->
				<h5 class="modal-title" id="myModalLabel" style="text-align:center;">DAY IN DETAILS</h5>
			</div>
			<!-- Modal Body -->
			<div class="modal-body">
				<p id="dayin_msg" style="">Please fill the required fields</p>             
				<div class="row">
					<div class="col-sm-6" >
						<div class="form-group">
							<label for="dayin_date">Date</label>
							<input type="text" class="form-control" id="dayin_date" name="dayin_date" value="<%out.println(b);%>" readonly />
						</div>
						<div class="form-group">
							<label for="counter_number">CounterNo<span style="color:red">*</span></label>
							<input type="text" class="form-control numKeypad" id="c_number" name="c_number" placeholder="Counter Number "/>
						</div>
						<div class="form-group">
							<label for="amount">Opening Balance<span style="color:red">*</span></label>
							<input type="text" class="form-control numKeypad" id="amount" name="amount" placeholder="Enter Amount"  value="" />
						</div>
						<div class="form-group">
							<label style="color:red">(*) Fields are required</label>
						</div>
					</div>
					<div class="col-sm-6" >
						<div class="form-group">
							<label for="time">Time</label>
							<input type="text" class="form-control" id="time" name="time" value="<% java.util.Date dates = new java.util.Date();
			                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			                             out.println(sdf.format(dates));%>" readonly/>                        
						</div>
						<div class="form-group">
							<label for="cashier">Cashier</label>
							<input type="text" class="form-control" id="cashier" name="cashier"  value="<%=session.getAttribute("UserName")%>" readonly />
						</div>
						<div class="form-group" style="margin-top: 41px;">
							<input type="checkbox" name="confirm_cash_balance" id="confirm_cash_balance" value="confirm" checked>
							<label for="inputName">Confirm Cash Balance</label>
						</div>
					</div>
				</div>
			</div>
			<!-- Modal Footer -->
			<div class="modal-footer">               
				<button type="button" class="btn btn-primary submitBtn" id="dayinsave" >SAVE</button>
			</div>
		</div>
	</div>
	<div class="modal-dialog" id="dayoutdiv1"> 
		<div class="modal-content">
			<!-- Modal Header -->
			<div class="modal-header">
				<!--   <button type="button" id="dayincancel" class="close" data-dismiss="modal">
				<span aria-hidden="true">&times;</span>
				<span  class="sr-only">Close</span>
				</button>  -->
				<h5 class="modal-title" id="myModalLabel" style="text-align:center;">DAY OUT DETAILS</h5>
			</div>
			<!-- Modal Body -->
			<div class="modal-body">
				<p class="statusMsg"></p>
				<div class="row">
					<div class="col-sm-4" >
						<div class="form-group">
							<label for="dayout_date">Date</label>
							<input type="text" class="form-control" id="dayout_date" name="dayout_date" value="<?php echo $dayinoutdata[0]['dayin_date']; ?>" readonly/> 
						</div>
						<div class="form-group">
							<label for="dayend_time">Time</label>
							<input type="text" class="form-control" id="dayend_time" name="dayend_time" value="<?php echo date('H:i:s'); ?>" readonly/>
						</div>   
						<div class="form-group">						
							<label for="counter_number">CounterNo</label>
							<input type="number" class="form-control" id="counter_number" name="counter_number" value="<?php echo $dayinoutdata[0]['counter_number']; ?>" readonly/>
						</div>
						<div class="form-group">
							<label for="cashier">Cashier</label>
							<input type="text" class="form-control" id="cashier" name="cashier"  value="<?php echo $dayinoutdata[0]['cashier_name']; ?>" readonly />
							<input type="hidden" class="form-control" id="day_in_out_autoid" name="day_in_out_autoid"  value="<?php echo $dayinoutdata[0]['Day_In_Out_AutoID']; ?>"  />
						</div>
						<div id="msg_dayout"> (*) Fields Are Required Fields	</div>
					</div>
					<div class="col-sm-4" >
						<div class="form-group">
							<label for="cash_sales_return">Cash Sales Return<span style="color: red;font-size: 15px;">*</span></label>
							<input type="text" class="form-control numKeypad" id="cash_sales_return" required name="cash_sales_return" placeholder="Enter Amount"  value="0" />
						</div>
						<div class="form-group">
							<label for="coupon_sales_return">Coupon Sales Return<span style="color: red;font-size: 15px;">*</span></label>
							<input type="text" class="form-control numKeypad" id="coupon_sales_return" required name="coupon_sales_return" placeholder="Enter Amount"  value="0" />
						</div>
						<div class="form-group">
							<label for="cash_in_drawer">Cash In Drawer<span style="color: red;font-size: 15px;">*</span></label>
							<input type="text" class="form-control numKeypad" id="cash_in_drawer" required name="cash_in_drawer" placeholder="Enter Amount"  value="" />
						</div>
						<div class="form-group">
							<label for="gift_voucher_amount">Gift Voucher Amount<span style="color: red;font-size: 15px;">*</span></label>
							<input type="text" class="form-control numKeypad" id="gift_voucher_amount" required name="gift_voucher_amount" placeholder="Enter Amount"  value="0" />
						</div>
					</div>
					<div class="col-sm-4" >
						<div class="form-group">
							<label for="card_amount">Card Amount<span style="color: red;font-size: 15px;">*</span></label>
							<input type="text" class="form-control numKeypad" id="card_amount" required name="card_amount" placeholder="Enter Amount"  value="" />
						</div>
						<div class="form-group">
							<label for="coupon_amount">Coupon Amount<span style="font-size: 15px;color: red;">*</span></label>
							<input type="text" class="form-control numKeypad" id="coupon_amount" required name="coupon_amount" placeholder="Enter Amount"  value="0" />
						</div>
						<div class="form-group">
							<label for="cash_in">Cash In<span style="color: red;font-size: 15px;">*</span></label>
							<input type="text" class="form-control numKeypad" id="cash_in" required name="cash_in" placeholder="Enter Amount"  value="0" />
						</div>
						<div class="form-group">
							<label for="cash_out">Cash Out<span style="color: red;font-size: 15px;">*</span></label>
							<input type="text" class="form-control numKeypad" id="cash_out" required name="cash_out" placeholder="Enter Amount"  value="0" />
						</div>
						<div class="form-group">
							<label for="credit_sales_amount">Credit Sales Amount<span style="color: red;font-size: 15px;">*</span></label>
							<input type="text" class="form-control numKeypad" id="credit_sales_amount" required name="credit_sales_amount" placeholder="Enter Amount"  value="0" />
						</div>
						<div class="form-group">
                            <label for="Paytm_amount">Paytm Amount<span style="color: red;font-size: 15px;">*</span></label>
                            <input type="text" class="form-control numKeypad" id="paytm_amount" required name="paytm_amount" placeholder="Enter Amount"  value="0" />
                        </div>
					</div>
				</div>
			</div>
			<!-- Modal Footer -->
			<div class="modal-footer">  
				<button type="button" class="btn btn-primary dayoutBtnclose" id="billparkBtnclose" >Close</button>
				<button type="button" class="btn btn-primary submitBtn" id="dayoutsave1" >SAVE</button>
			</div>
		</div>
	</div>
	<div class="modal-dialog" id="eodsummary"></div>
	<div class="modal-dialog" id="bill_parksummary"></div>
	<input type ="hidden" name="" id="balance_amt" value="">
	<div id="barcodediv">
	<img id="barcode2"/>
	</div>
	<div id="print_data"></div>
        <div id="print_preorderdata"></div>
</body>
</html>

<%@include file="categoriesToUnits.jsp" %>
<%-- <%@include file="ordersScript.jsp" %> --%>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/public/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/public/bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript">
    var dateToday = new Date();

	$('.form_datetime').datetimepicker({
		weekStart: 1,
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		showMeridian: 1,
                startDate : dateToday
	});
</script>   
<script type="text/javascript">

var voucher_arr ={};
var order_arr ={};
var voucherid;
var voucher_discount;
var show;
var billcheck=null;	
var currentRequest = null; 
  
		//Day in save form data post to pos(controller)/setDayInData(Method)
		$("#dayinsave").click(function() {
			
			var dayin_date = $("#dayin_date").val();
			var counter_number = $("#c_number").val();
			var amount = $("#amount").val();
			
			/*alert(dayin_date);
			alert(counter_number);
			alert(amount);*/
			/*if(counter_number == '' || amount==''){
				$('#dayindiv').modal('show'); 
				$('#dayin_msg').show();
			}*/
			
			var time = $("#time").val();
			var cashier = $("#cashier").val();	
			alert(dayin_date+" "+counter_number+" "+amount+" "+time);
			var parm_obj = {};
			parm_obj['dayin_date']= dayin_date;
			parm_obj['counter_number']= counter_number;
			parm_obj['amount']= amount;
			parm_obj['time']= time;
			parm_obj['cashier']= cashier;			
			if($("#confirm_cash_balance").prop("checked") == true && counter_number!='' && amount!=''){					
				$.ajax({
					type: "post",
					//url:"index.php?ref=<?php echo $btn_encode_url['pos/setDayInData']; ?>",
				    url:'${pageContext.request.contextPath}/Pos/setDayInData',
					data: parm_obj,
					async: true,
					beforeSend: function(data) {},
					success: function(r, textStatus, xmlReq) {
						if(r){
							var json_data =  jQuery.parseJSON(r);
						}
						$("#dayindiv").hide();
						window.location.reload(true);
					},
					error: function(xmlReq, textStatus, errorThrown) {}
				});
			}else {
				$('#dayin_msg').show();
			}
		});
	
        
	    $('.numKeypad').keypad();	    
		$(".keyboard_img").on('click',function(){
			$( "#defaultKeypad" ).focus();
		});
		$('#defaultKeypad').keypad({layout: $.keypad.qwertyLayout});
		$('.defaultKeypad').keypad({layout: $.keypad.qwertyLayout});
		$('#themeRollerSelect').change(function() {
			$('#themeUI').attr('href', 'themesDemo/' + $(this).val() + '/jquery-ui.css'); 
		});
		  
		 
		
		var btn_amt_img = '<img src="${pageContext.request.contextPath}/resources/public/images/iconCheck.png">';
		var dialogs_url  = '${pageContext.request.contextPath}/Pos/getdialogs';
		var generate_disc_url = '${pageContext.request.contextPath}/Orders/generate_overall_discount';
		var selectedItemArray ={};
		var selectedItemNewArray = {};
		var order_arr = {};
		var selectedItemsGstArray = {};
		var selectedItemstot = {};
		var ReturnAmount_arr ={};
		var payment_mode_ary = {};
		var overall_bill_disc = {}; 			

		$.fn.bindselecteditems_grid = function(params={}){
		var item_inc_val =0; 
		var fval='';
		$('#final_items').html('');
		//console.log(ReturnAmount_arr['ReturnAmount']);
		$.fn.calc_TotalPrice();
		if(params['action'] !=undefined && typeof overall_bill_disc !=undefined && overall_bill_disc['cur_amount']>0 ){
			var p_obj = {
				'cur_amount':overall_bill_disc['cur_amount'],
				'dis_perc':overall_bill_disc['dis_perc'],
				'selectedItemArray':selectedItemArray,                                      
				'selectedItemstot':selectedItemstot,
				'selectedItemsGstArray':selectedItemsGstArray,
			}
			$.fn.calculate_overall_discount(p_obj);
			return false;
		}
		$.each(selectedItemArray, function( index, value ) {
			item_inc_val++;
			var dis_amt_a = parseFloat(value['discount']['amount']);
			var dis_amt_per = parseFloat(value['discount']['percentage']);
			if(typeof value['additional_discount'] !='undefined' ){
				dis_amt_a += value['additional_discount']['amount'];
				dis_amt_per += value['additional_discount']['percentage'];
			}
			if(dis_amt_per >100) dis_amt_per =100;
			fval +='<tr class="" id="'+index+'">';
			fval +='<td class="selected_item_row" width="4%">'+ item_inc_val +'</td>'; 
			fval +='<td class="selected_item_row" width="20%">'+value['name']+'</td>';
			fval +='<td width="4%" class="qtot selected_item_row">'+ value['qty'] +'</td>';
			fval +='<td class="selected_item_row" width="10%" align="center">'+  value['unit'] +'</td>';
                        fval +='<td class="selected_item_row" width="10%" align="center">'+  value['size'] +'</td>';
			fval +='<td class="selected_item_row" width="10%" align="center">'+ round( value['price'],2) +'</td>';
			fval +='<td class="selected_item_row" width="10%" align="center">'+ round(dis_amt_a,2) +'</br>('+round(dis_amt_per,2)+'%)</td>';
			fval +='<td class="selected_item_row" width="7%" align="right">'+  value['gst']['all']['percentage'] +'%</td>';
			fval +='<td width="10%" class="subtot selected_item_row"  align="center">'+ round(value['total'],2)+'</td>';
			fval +='<td width="17%" > ';
			fval +='<a data-item="'+ value['item_id'] +'" data-value="'+ index +'" data-id="add" class="item_unit_action" href="javascript:void(0);"><span class="plus"><img src="${pageContext.request.contextPath}/resources/images/add.png" class="add_remove_icon_cls"></span></a>';
			fval +='<a data-item="'+ value['item_id'] +'" data-value="'+ index +'" data-id="less" class="item_unit_action" href="javascript:void(0);"><span class="minus"><img src="${pageContext.request.contextPath}/resources/images/less.png" class="add_remove_icon_cls"></span></a>';
			fval +='<a data-item="'+ value['item_id'] +'" data-value="'+ index +'" data-id="delete" class="item_unit_action" href="" data-toggle="modal" data-target=""><span class="delete"><img src="${pageContext.request.contextPath}/resources/images/delete.png" class="add_remove_icon_cls"></span></a>';
			fval +='</td>';
			fval +='</tr>';
		});
		$('#final_items').append(fval);
		if(params['portion_id'] !='undefined' && params['portion_id']!=''){
			$('#final_items tr#'+params['portion_id']).addClass('rowselected');
			var cur_div_stat = $("div#edit_item_srn_div").css("display");
			var  cur_edit_item_id = $("div#edit_item_srn_div input#hdn_item_portion_id").val();
			if(cur_div_stat =='block' && cur_edit_item_id == params['portion_id']){
				if(params['action']=='delete'){
					$("div.display_screen").hide();
					$("div#edit_item_srn_div input#hdn_item_portion_id").val('');
					$("div#welcome_screen_div").show();
				}else {
					$('#final_items tr#'+params['portion_id']+'').find('td:first').trigger('click');
				}
			}
		}
		if(item_inc_val==0){
			$("div.display_screen").hide();
			$("div#edit_item_srn_div input#hdn_item_portion_id").val('');
			$("div#welcome_screen_div").show();
		}
		return false;
	};
	
	$.fn.calc_TotalPrice =function (){ 
				var qty_val = 0; 
				var tot_price = 0;
				var tot_priceitems = 0;
				var tot_gst_price = 0;
				var tot_sgst_price =0 ;
				var tot_cgst_price = 0;
				var adit_dis_amt=0;
				var tot_before_gst = 0;
				var discount_value=0;
				var total_billprice=0;
		if((typeof selectedItemstot['balance_amount']== 'undefined') && (typeof ReturnAmount_arr['ReturnAmount']== 'undefined')){
					console.log('balance_amountoooooooooooo');
					$.each(selectedItemArray, function( index, value ) {
						qty_val +=parseFloat(value['qty']);
						var qty_price = parseFloat(value['qty'])*value['price'];
						if(value['discount']['percentage']>0)
							var dis_amt = (qty_price*value['discount']['percentage'])/100;
						else {
							var dis_amt = value['discount']['amount'];
							var dis_per = ((dis_amt/qty_price)*100);
							selectedItemArray[index]['discount']['percentage'] = round(dis_per,2);
						}
						selectedItemArray[index]['discount']['amount']= round(dis_amt,2);
						var item_tot_price =  qty_price-dis_amt;
						tot_before_gst +=item_tot_price;
						selectedItemsGstArray[index]['tprice'] = item_tot_price;
						if(typeof selectedItemArray[index]['additional_discount'] !='undefined' ){
							adit_dis_amt = selectedItemArray[index]['additional_discount']['amount'];
							item_tot_price =item_tot_price-adit_dis_amt; 
						}
						// ---------- GST Cal --------
						var gst_amt = (item_tot_price*parseInt(selectedItemArray[index]['gst']['all']['percentage']))/(100+parseInt(selectedItemArray[index]['gst']['all']['percentage']));
						tot_gst_price +=gst_amt;
						var sgst = cgst = gst_amt/2;
						tot_sgst_price +=sgst;
						tot_cgst_price +=cgst;
						selectedItemArray[index]['gst']['all']['amount']=round(gst_amt,2);
						selectedItemArray[index]['gst']['sgst']['amount']=round(sgst,2);
						selectedItemArray[index]['gst']['cgst']['amount']=round(cgst,2);
						// ---------- GST Cal --------
						tot_price +=item_tot_price;
						console.log(tot_price);
						selectedItemArray[index]['total']= round(item_tot_price,2);
					});
					 tot_price= round(tot_price);
			console.log(tot_price);
				}
				else if (typeof ReturnAmount_arr['ReturnAmount']!= 'undefined'){ // Do another set of actions
					console.log(retunIDs);
					$.each(selectedItemArray, function( index, value ) {
						qty_val +=parseInt(value['qty']);
						var qty_price = value['qty']*value['price'];
						if(value['discount']['percentage']>0)
							var dis_amt = (qty_price*value['discount']['percentage'])/100;
						else {
							var dis_amt = value['discount']['amount'];
							var dis_per = ((dis_amt/qty_price)*100);
							selectedItemArray[index]['discount']['percentage'] = round(dis_per,2);
						}
						selectedItemArray[index]['discount']['amount']= round(dis_amt,2);
						var item_tot_price =  qty_price-dis_amt;
						tot_before_gst +=item_tot_price;
						selectedItemsGstArray[index]['tprice'] = item_tot_price;
						if(typeof selectedItemArray[index]['additional_discount'] !='undefined' ){
							adit_dis_amt = selectedItemArray[index]['additional_discount']['amount'];
							item_tot_price =item_tot_price-adit_dis_amt; 
						}
						// ---------- GST Cal --------
						var gst_amt = (item_tot_price*parseInt(selectedItemArray[index]['gst']['all']['percentage']))/(100+parseInt(selectedItemArray[index]['gst']['all']['percentage']));
						tot_gst_price +=gst_amt;
						var sgst = cgst = gst_amt/2;
						tot_sgst_price +=sgst;
						tot_cgst_price +=cgst;
						selectedItemArray[index]['gst']['all']['amount']=round(gst_amt,2);
						selectedItemArray[index]['gst']['sgst']['amount']=round(sgst,2);
						selectedItemArray[index]['gst']['cgst']['amount']=round(cgst,2);
						// ---------- GST Cal --------
						tot_price +=item_tot_price;
						selectedItemArray[index]['total']= round(item_tot_price,2);
					});
					tot_priceitems= ReturnAmount_arr['ReturnAmount']-round(tot_price);
					if(tot_priceitems > 0){
						tot_price= 0;
					}
					else if(tot_priceitems < 0){
						tot_price= round(tot_price)-ReturnAmount_arr['ReturnAmount'];
						tot_priceitems=0;
					}
					else{
						tot_price= 0;
						tot_priceitems=0;
						payment_mode_ary['Cash'] = {
					'amount': tot_price
				}
					}
					document.getElementById("returnamount").innerHTML = 'Return Amount = Rs ' +tot_priceitems+' /-';
				}
				
		        else{
					$.each(selectedItemArray, function( index, value ) {
						qty_val +=parseInt(value['qty']);
						var qty_price = value['qty']*value['price'];
						if(value['discount']['percentage']>0)
							var dis_amt = (qty_price*value['discount']['percentage'])/100;
						else {
							var dis_amt = value['discount']['amount'];
							var dis_per = ((dis_amt/qty_price)*100);
							selectedItemArray[index]['discount']['percentage'] = round(dis_per,2);
						}
						selectedItemArray[index]['discount']['amount']= round(dis_amt,2);
						var item_tot_price =  qty_price-dis_amt;
						tot_before_gst +=item_tot_price;
						selectedItemsGstArray[index]['tprice'] = item_tot_price;
						if(typeof selectedItemArray[index]['additional_discount'] !='undefined' ){
							adit_dis_amt = selectedItemArray[index]['additional_discount']['amount'];
							item_tot_price =item_tot_price =item_tot_price-adit_dis_amt; 
						}
						// ---------- GST Cal --------
						var gst_amt = (item_tot_price*selectedItemArray[index]['gst']['all']['percentage'])/100;
						tot_gst_price +=gst_amt;
						var sgst = cgst = gst_amt/2;
						tot_sgst_price +=sgst;
						tot_cgst_price +=cgst;
						selectedItemArray[index]['gst']['all']['amount']=round(gst_amt,2);
						selectedItemArray[index]['gst']['sgst']['amount']=round(sgst,2);
						selectedItemArray[index]['gst']['cgst']['amount']=round(cgst,2);
						tot_price +=item_tot_price;
						selectedItemArray[index]['total']= round(item_tot_price,2);
						if(adit_dis_amt >0){
							discount_value=adit_dis_amt;
						}
					});
					tot_pricevalue= selectedItemstot['balance_amount']-discount_value;
					tot_price=tot_pricevalue.toFixed(0);
				}
				selectedItemstot = {
					'qty_val':round(qty_val,2),
					'tot_price':round(tot_price,2),
					'tot_before_gst':round(tot_before_gst,2),
					'tot_gst_amout':round(tot_gst_price,2),
					'tot_sgst_amout':round(tot_sgst_price,2),
					'tot_cgst_amout':round(tot_cgst_price,2),
					'remaining_amount':round(tot_price,2)
				};
				$.each(selectedItemArray, function( index, value ) {
					selectedItemsGstArray[index]['totalbillprice'] = selectedItemstot['tot_price'];
				});
				$('.grdtot').text('₹'+selectedItemstot['tot_price'].toFixed(2)+'/-');
				$("span#cgst_amt_span").html(tot_cgst_price.toFixed(2));
				$("span#sgst_amt_span").html(tot_sgst_price.toFixed(2));
				$("span#disc_befgst_amt").html('₹'+selectedItemstot['tot_before_gst']+'/-');
				$.fn.bindpaymentGrid();
				$('.qalltot').text(qty_val);
				return false;
			}
			
			var selectedItemNewArray = {};
			$(document).on("click",".unit-btn-link",function() {
				$("#searchorder").css("display", "none");	
				$("#deliveryorderdetails").hide();
		        $("#returnorder").hide();
				$("#main_table").css("display", "block");
				var oprice = $(this).data('id');
				var arr = oprice.split(',');
				var portion_id = $(this).data('value');
				var item_name = $('#item_hidden_val').val();
				var discount_val = $('#item_dis_val').val();
		        //var size_val = $('#item_size').val();
				var item_id = $(this).data('item');
				var gst = $("button.selected_item").data('gst');
				//alert(gst);
				var tqun =0;
				var action_val = '';
		                if(typeof order_arr['perc_type'] !='undefined'){
		                    //alert(order_arr['perc_type']);
		                    var priceitem=parseInt(order_arr['perc_type'])+100;
		                    var price_ordertypes=arr[1]*(priceitem/100);
		                }
		                else
		                {
		                    var price_ordertypes=arr[1];
		                }
				if(typeof selectedItemArray =='undefined' || selectedItemArray ==null) selectedItemArray = {};
				if(typeof selectedItemArray[portion_id]!=='undefined' ){
					var tqun= parseInt(selectedItemArray[portion_id]['qty']);
					selectedItemArray[portion_id]['qty'] =tqun + parseInt(1);
					action_val ='add';
				}else {
					var cgst = sgst = gst/2;
					var dis_amt = ((arr[1] * 1)*discount_val/100);
					selectedItemArray[portion_id] = {
						'unit': arr[2],
						'original_price': price_ordertypes,
						'price': price_ordertypes,
						'name': item_name,
		                                'size': arr[3],
						'qty':1,
						'item_id':item_id,
						'original_discount':{
							'percentage': discount_val,
							'amount': dis_amt
						},
						'discount': {
							'percentage':discount_val,
							'amount': dis_amt
						},
		                                'additional_discount': {
							'percentage':0,
							'amount': 0
						},
						'gst':{
							'all':{
								'percentage':gst,
								'amount':'',
							},
							'cgst':{
								'percentage':cgst,
								'amount':'',
							},
							'sgst':{
								'percentage':sgst,
								'amount':'',
							}
						},
						'total':arr[1]
					}
					selectedItemsGstArray[portion_id] ={
						'portion_id':portion_id,
						'item_id':item_id,
						'tprice': round((arr[1]- dis_amt),2),
						'gst':gst
					}
				}
				var params = {
					'portion_id':portion_id,
					'action':action_val
				}
				$.fn.bindselecteditems_grid(params);
				$('.button5').removeClass('selected_unit');
				$(this).addClass('selected_unit');
				return false;
			});
		
	function round(value, exp) {
		if (typeof exp === 'undefined' || +exp === 0)
			return Math.round(value);
		value = +value;
		exp = +exp;
		if (isNaN(value) || !(typeof exp === 'number' && exp % 1 === 0))
			return NaN;
		// Shift
		value = value.toString().split('e');
		value = Math.round(+(value[0] + 'e' + (value[1] ? (+value[1] + exp) : exp)));
		// Shift back
		value = value.toString().split('e');
		return +(value[0] + 'e' + (value[1] ? (+value[1] - exp) : -exp));
	}
		
	
	$(document).on("click",".item_unit_action", function() {
		var portion_id = $(this).data('value');
		var item_act = $(this).data('id');
		var params ={
			'portion_id':portion_id,
			'action':item_act,
			'qty':1
		}
		$.fn.updateQty(params);
	});
	
		
	$.fn.updateQty = function(params){
		var portion_id = params['portion_id'];
		var action = params['action'];
		var item_qty = parseInt(selectedItemArray[portion_id]['qty']);
		switch(params['action']) { 
			case 'add':
				selectedItemArray[portion_id]['qty'] =item_qty+params['qty'];
				break;
			case 'less':
				if((item_qty-params['qty'])>=1) {
					selectedItemArray[portion_id]['qty'] =item_qty - params['qty'];
				}else {
					params['action'] = 'delete';
					$("div#deleteModal input#del_item_val").val(portion_id); 
					$('#deleteModal').modal('show');
				}
				break;		
			case 'delete':
				$("div#deleteModal input#del_item_val").val(portion_id);
				$('#deleteModal').modal('show');
				return false;
				break;
		}
		if(params['action'] =='add' || params['action']=='less'){
			var params_obj = {
				'portion_id':portion_id,
				'action':action
			}
			$.fn.bindselecteditems_grid(params_obj);
		}
	}
            $.fn.updateSize = function(params){
		var portion_id = params['portion_id'];
		var action = params['action'];
		var item_qty = parseInt(selectedItemArray[portion_id]['size']);
		switch(params['action']) { 
			case 'add':
				selectedItemArray[portion_id]['size'] =item_qty+params['size'];
				break;
			case 'less':
				if((item_qty-params['qty'])>=1) {
					selectedItemArray[portion_id]['size'] =item_qty - params['size'];
				}else {
					params['action'] = 'delete';
					$("div#deleteModal input#del_item_val").val(portion_id); 
					$('#deleteModal').modal('show');
				}
				break;		
			case 'delete':
				$("div#deleteModal input#del_item_val").val(portion_id);
				$('#deleteModal').modal('show');
				return false;
				break;
		}
		if(params['action'] =='add' || params['action']=='less'){
			var params_obj = {
				'portion_id':portion_id,
				'action':action
			}
			$.fn.bindselecteditems_grid(params_obj);
		}
	}
            $(document).on("click",".delete_item", function() {
        		var fid = $('#del_item_val').val();
        		var params_obj = {
        			'portion_id':fid,
        			'action':'delete'
        		}
        		$.fn.removeselecteditem(params_obj);
        		$('#'+fid).remove();
        		$('#deleteModal').modal('hide');
        	});
        	$.fn.removeselecteditem = function(params_obj){
        		delete selectedItemArray[params_obj['portion_id']];
        		delete selectedItemsGstArray[params_obj['portion_id']];
        		$.fn.bindselecteditems_grid(params_obj);
        	};
	
		 $(document).on("click","#clear_button",function(e) {	
        		$('#defaultKeypad').val(''); //defaultKeypad is textbox ID
        		//$( "#defaultKeypad" ).focus();
        	});	
            
            $(document).on("click",".clear_all_data", function() {
        		selectedItemArray ={};
        		payment_mode_ary = {};
        		selectedItemstot = {};
        		selectedItemsGstArray = {};
        		overall_bill_disc = {};
                        order_arr = {};
                        countorder=0;
                        $('[id^="ordertypes"]').prop('disabled', false);
                           // $('[id^="ordertypes"]').css("background-color","#ccc");
                        $('[id^="ordertypes"]').css("cursor","pointer");
        		billcheck = null;
        		$.fn.bindselecteditems_grid();
        	});
		
            $(document).on("click","#btn_fnc_size", function() {
        		calc_clicks = 0;
        		var displayBox = document.getElementById("display");
        		displayBox.innerHTML = "";
        		$("#quantity_newval").css("display", "none");
        		$( "span#prod_qty" ).show();
        		$( "span#prod_discount" ).show();
        		$("#span_weight").css("display", "none");
        		$("#dis_newval").css("display", "none");
        		var prod_size_val= $('#prod_size').text();
        		var myW = 40;
        		$( "span#prod_size" ).hide();
        		$("#size_newval").css({"width" : myW,"display" : 'block'});	
        		$("#size_newval").val(prod_size_val);
        		$checkprice=0;
        	});
            
         // Open Order Item insert to list of items
    		$(document).on("click","button.openorder",function(e) {
                            $("#view_help").hide();
                            $("#view_billpark").hide();	
    			$("#leftmain_table").show();
    			$("#orderadd").html("");
    			$("#open_order_price").val("");
    			$("#open_order_qty").val("");
    			//$("input[name='tax'][value='5']").prop('checked', true);
    			//$("input[name='unit'][value='PC']"). prop('checked', true);
    			id=0;
    			$('.openorderBtnadd').prop('disabled', false);
    			$('.openorderBtnminus').prop('disabled', true);
    			$("#openorder").css("display", "block");	
    		});
         
         
    		$(document).on("click","button.openorderBtnclose",function(e) {
    			$("#openorder").css("display", "none");	
    		});
    		
    		
    		$(document).on("click","button.openorderBtnminus",function(e) {
    			if(id==1){
    				id--
    				$("#addcontent1").remove();					
    				$('.openorderBtnminus').prop('disabled', true);
    				$('.openorderBtnadd').prop('disabled', false);
    			}
    			if(id==2){
    				$("#addcontent2").remove();
    				$('.openorderBtnminus').prop('disabled', false);
    				id--
    			}
    		});
    		
    		$(document).on("click","button.openorderBtnadd",function(e) {
    			++id;
    			if(id>'2'){
    				id=2;
    				$('.openorderBtnadd').prop('disabled', true);
    				return false;
    			}
    			var addorder='<div id="addcontent'+id+'"><p class="openorder_msg'+id+'"></p><span>----------------------</span><div class="row"><div class="col-sm-2" ><label class="openorderlabel" for="open_order_price">Qty</label></div><div class="col-sm-3"><input type="text" class="form-control numKeypad" id="open_order_qty'+id+'" name="open_order_qty'+id+'" value="" />   </div><div class="col-sm-7"><ul class="order-unit'+id+'"><li><input type="radio" id="KG'+id+'" name="unit'+id+'" value="KG" /><label for="KG'+id+'">KG</label></li><li><input type="radio" id="PC'+id+'" name="unit'+id+'" value="PC"   /><label for="PC'+id+'">PC</label></li><li><input type="radio" id="Unit'+id+'" name="unit'+id+'" value="Unit" /><label for="Unit'+id+'">Unit</label></li></ul></div></div><div class="row"><div class="col-sm-2"><label class="openorderlabel" for="open_order_price">Price </label></div><div class="col-sm-5" ><input type="text" class="form-control numKeypad" id="open_order_price'+id+'" name="open_order_price'+id+'" value="" width="125%" style="margin-bottom: 6px;" /></div><div class="col-sm-5" ></div></div><div class="row"><div class="col-sm-2"><label class="openorderlabel" for="open_order_tax">Tax</label></div><div class="col-sm-10"><ul class="order-tax'+id+'"><li><input type="radio" id="tax0'+id+'" value="0" name="tax'+id+'" /><label for="tax0'+id+'">0%</label></li><li><input type="radio" id="tax5'+id+'" value="5"  name="tax'+id+'"/><label for="tax5'+id+'">5%</label></li><li><!--<input type="radio" id="tax12'+id+'" value="12"  name="tax'+id+'" style="background: #DDD;cursor:none;"/>--><label for="tax12'+id+'" style="background: #DDD;cursor:none;">12%</label></li><li><input type="radio" id="tax18'+id+'" value="18"  name="tax'+id+'" /><label for="tax18'+id+'">18%</label></li><li><!--<input type="radio" id="tax28'+id+'" value="28"  name="tax'+id+'" style="background: #DDD;"/>--><label for="tax28'+id+'" style="background: #DDD;cursor:none;">28%</label></li></ul></div></div></div>';	
    			$(addorder).appendTo('#orderadd').find('input.numKeypad').keypad();
    			$('.openorderBtnminus').prop('disabled', false);
    			if(id=='2') {
    				$('.openorderBtnadd').prop('disabled', true);
    			}
    		});
    		
    		$(document).on("click","button.openorderBtn",function(e) {
    			var outlet_sesionid="<%=outlet_uuid%>";
    			$('#searchorder').hide();
    			$('#main_table').show();
    			var open_order_price = $("#open_order_price").val();
    			var open_order_qty = $("#open_order_qty").val();
    			var open_order_tax = $("input[name='tax']:checked").val();;
    			var open_order_unit = $("input[name='unit']:checked"). val();
                            var tax_size = $("input[name='tax']:checked").size();
                            var unit_size = $("input[name='unit']:checked").size();
                            //alert(tax_size);alert(unit_size);
                            if(open_order_qty =='')
                                $(".openorder_msg").text('Please Enter Quantity');
                            else if(open_order_price =='')
                                $(".openorder_msg").text('Please Enter Price');
                            else if(tax_size==0 )
                                $(".openorder_msg").text('Please Select Tax');
                           else if(unit_size ==0)
                           {
                              $(".openorder_msg").text('Please Select Unit');	
                           }
                           else{
    			var selectedItemopenorder =[];
    			selectedItemopenorder[0] = [open_order_price,open_order_qty,open_order_tax,open_order_unit];
                        }
    			if(id>1) {
                                
                               
    				var open_order_price2 = $("#open_order_price2").val();
    				var open_order_qty2 = $("#open_order_qty2").val();
    				var open_order_tax2 = $("input[name='tax2']:checked").val();;
    				var open_order_unit2 = $("input[name='unit2']:checked"). val();
                                    var tax_size2 = $("input[name='tax2']:checked").size();
                                    var unit_size2 = $("input[name='unit2']:checked").size();
                                     if(open_order_qty2 =='')
                                        $(".openorder_msg2").text('Please Enter Quantity');
                                    else if(open_order_price2 =='')
                                        $(".openorder_msg2").text('Please Enter Price');
                                    else if(tax_size2==0 )
                                        $(".openorder_msg2").text('Please Select Tax');
                                    else if(unit_size2 ==0)
                                    {
                                       $(".openorder_msg2").text('Please Select Unit');	
                                    }
                                    
                                    
                                    else{
    				selectedItemopenorder[2] = [open_order_price2,open_order_qty2,open_order_tax2,open_order_unit2];
                                }
    			}
    			if(id>0) {
    				var open_order_price1 = $("#open_order_price1").val();
    				var open_order_qty1 = $("#open_order_qty1").val();
    				var open_order_tax1 = $("input[name='tax1']:checked").val();;
    				var open_order_unit1 = $("input[name='unit1']:checked"). val();
                                    var tax_size1 = $("input[name='tax1']:checked").size();
                                    var unit_size1 = $("input[name='unit1']:checked").size();
                                     if(open_order_qty1 ==''){
                                        $(".openorder_msg1").text('Please Enter Quantity');
                                        //alert(id);
                                    }
                                    else if(open_order_price1 =='')
                                        $(".openorder_msg1").text('Please Enter Price');
                                    else if(tax_size1==0 )
                                        $(".openorder_msg1").text('Please Select Tax');
                                    else if(unit_size1 ==0)
                                    {
                                       $(".openorder_msg1").text('Please Select Unit');	
                                    }
                                    else{
    				selectedItemopenorder[1] = [open_order_price1,open_order_qty1,open_order_tax1,open_order_unit1];
                                }
    			}
    			$.each(selectedItemopenorder, function( index, value ) {
    				var portion_id = 0;
    				if(index==0){
    					var item_id = '10000';	
    					var item_name = 'OPEN ORDER-1';		
    				}else if(index==1){			
    					var item_id = '20000';	
    					var item_name = 'OPEN ORDER-2';		
    				}else if(index==2){			
    					var item_id = '30000';	
    					var item_name = 'OPEN ORDER-3';		
    				}
    				switch(value[3]){
    					case 'KG':	
    						portion_id = '11111'+index+outlet_sesionid;	
    						break;
    					case 'PC':	
    						portion_id = '22222'+index+outlet_sesionid;	
    						break;
    					case 'Unit':	
    						portion_id = '33333'+index+outlet_sesionid;	
    						break;
    				}
    				var discount_val = '0';
    				var dis_amt = '0';
    				// open order Item ID Default value
    				var gst = value[2];
    				var sgst;	
    				var tqun =0;
    				var action_val = '';
    				if(typeof selectedItemArray =='undefined' || selectedItemArray ==null) selectedItemArray = {};
    				if(typeof selectedItemArray[portion_id]!=='undefined' ){
    					var tqun= parseInt(selectedItemArray[portion_id]['qty']);
    					selectedItemArray[portion_id]['qty'] =tqun + parseInt(1);
    					action_val ='add';
    				}else {
    					var cgst = sgst = gst/2;
    					var dis_amt = round(((value[0] * 1)*discount_val/100),2);
    					selectedItemArray[portion_id] = {
    						'unit': value[3],
    						'original_price': value[0],
    						'price': value[0],
    						'name': item_name,
    						'qty':value[1],
    						'item_id':item_id,
    						'size':0,
    						'original_discount':{
    							'percentage': discount_val,
    							'amount': dis_amt
    						},
    						'discount': {
    							'percentage':discount_val,
    							'amount': dis_amt
    						},
    						'gst':{
    							'all':{
    								'percentage':gst,
    								'amount':'',
    							},
    							'cgst':{
    								'percentage':cgst,
    								'amount':'',
    							},
    							'sgst':{
    								'percentage':sgst,
    								'amount':'',
    							}
    						},
    						'total':value[1]*value[0]
    					}
    					selectedItemsGstArray[portion_id] ={
    						'portion_id':portion_id,
    						'item_id':item_id,
    						'tprice': round((value[0] - dis_amt),2),
    						'gst':gst
    					}
    				}
    				var params = {
    					'portion_id':portion_id,
    					'action':action_val
    				}
    				$.fn.bindselecteditems_grid(params);
    				$('.button5').removeClass('selected_unit');
    				$(this).addClass('selected_unit');
    			});
                            $("#openorder").css("display", "none");	
                        
    				
    		});
            
    		
    		$(document).on("click",".selected_item_row", function() {
    			$("#quantity_newval").css("display", "none");
    			$("#price_newval").css("display", "none");
    	                $("#size_newval").css("display", "none");
    			$("#span_weight").css("display", "none");
    			$( "span#prod_qty" ).show();
    	                $( "span#prod_size" ).show();
    			$( "span#prod_price" ).show();
    			var cur_d = $("button#up_down_btn" ).attr('rel');
    			if(cur_d=='down'){
    				$("button#up_down_btn" ).trigger('click');
    			}
    			var trId = $(this).closest('tr').prop('id');
    			$('#final_items tr').removeClass('rowselected');
    			$(this).closest('tr').addClass('rowselected');
    			var cur_div_stat = $("div#edit_item_srn_div").css("display");
    			if(cur_div_stat !='block'){
    				$('.fun_btns').hide();
    				$("button.btnkeyboard_actions").removeClass('btnselected');
    				$("button#btn_fnc_qty").addClass('btnselected');
    				$('.edit_item_btn_cls').show();
    			}
    			$('.display_screen').hide();
    			$('#edit_item_srn_div').show();
    			var cur_edit_item_data = selectedItemArray[trId];
    			$("div#edit_item_srn_div a.item_unit_action").attr({'data-item':cur_edit_item_data['item_id'],'data-value':trId});
    			$("div#prod_name b").html(cur_edit_item_data.name);
    			$("span#prod_qty").html(cur_edit_item_data.qty +'<i style="margin-left:10px;">('+cur_edit_item_data.unit+')</i>');
    			$("span#prod_price").html(cur_edit_item_data.price);
    	                $("span#prod_size").html(cur_edit_item_data.size);
    			if(cur_edit_item_data.additional_discount){
    				$("span#prod_discount").html(cur_edit_item_data.additional_discount.amount +'(@'+round(cur_edit_item_data.additional_discount.percentage,2)+'%)');
    			}else{
    				$("span#prod_discount").html(cur_edit_item_data.discount.amount +'(@'+round(cur_edit_item_data.discount.percentage,2)+'%)');
    			}
    			$("span#prod_cgst").html(cur_edit_item_data['gst']['cgst']['percentage']);
    			$("span#cgst_amt").html(cur_edit_item_data['gst']['cgst']['amount']);
    			$("span#prod_sgst").html(cur_edit_item_data['gst']['sgst']['percentage']);
    			$("span#sgst_amt").html(cur_edit_item_data['gst']['sgst']['amount']);
    			$("span#prod_tot_price").html('<b>'+cur_edit_item_data.total+'</b>');
    			$("input#hdn_item_portion_id").val(trId);
    			return false;
    		});
            
    		
    		//-------------- Calculator Slide Down and UP event --------------
    		$( "button#up_down_btn" ).click(function() {
    			var cur_st = $(this).attr('rel');
    			if(cur_st=='down'){
    				$("table.sitems_gird tbody").css({'height':'230px','min-height':'230px'});
    				$( "button#up_down_btn" ).attr({'rel':'up'});
    				$( "div.right_bottom_div" ).slideToggle( "slow","swing", function(){});
    			}else{
    				$( "div.right_bottom_div" ).slideToggle( "slow","swing", function(){
    					$("table.sitems_gird tbody").css({'height':'510px','min-height':'510px'});
    					$( "button#up_down_btn" ).attr({'rel':'down'});
    				});
    			}
    		});
    		//-------------- Calculator Slide Down and UP event --------------
    		
    		
    		
    var elem = document.getElementById("max");
	elem.onclick = function() {
		toggleFullScreen();
	}

	function toggleFullScreen() {
		
		if (!document.fullscreenElement &&    // alternative standard method
		!document.mozFullScreenElement && !document.webkitFullscreenElement) {  // current working methods
			if (document.documentElement.requestFullscreen) {
				
				document.documentElement.requestFullscreen();
			} else if (document.documentElement.mozRequestFullScreen) {
				
				document.documentElement.mozRequestFullScreen();
			} else if (document.documentElement.webkitRequestFullscreen) {
				
				document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
			}
		} else {
			if (document.cancelFullScreen) {
				document.cancelFullScreen();
			} else if (document.mozCancelFullScreen) {
				document.mozCancelFullScreen();
			} else if (document.webkitCancelFullScreen) {
				document.webkitCancelFullScreen();
			}
		}
	}
	
	
	// 27-11-2018
	//--------- Bill Overall Discount button click event --------------------
	$.fn.getAdditional_dis_items = function(){
		var overall_adt_dis_amt = 0;
		var overall_adt_dis_perc = 0; 
		$("td#adt_dis_items_list").html('');
		var dis_tbl_data = '<table width="100%" cellpadding="5" cellspacing="5" id="add_dis_items_tbl"><tr><th>Item</th><th> Add Dis(%)</th><th>Amount</th></tr>';
		$.each(selectedItemArray,function(index,value){
			if(typeof value['additional_discount'] !='undefined' ){
				if(value['additional_discount']['amount']>0){
					var adt_da = parseFloat(value['additional_discount']['amount']);
					var adt_dps = parseFloat(value['additional_discount']['percentage']);
					dis_tbl_data +='<tr><td>'+value['name']+'('+value['unit']+')</td><td>'+value['additional_discount']['percentage']+'%</td><td>'+value['additional_discount']['amount']+'</td></tr>';
					overall_adt_dis_amt +=adt_da;
					overall_adt_dis_perc +=adt_dps;
				}
			}
		});
		dis_tbl_data +='</table>'; 
		$("td#adt_dis_items_list").html(dis_tbl_data);
	}
	
	//discount button click event
	 $("button#bill_discount_btn").on('click',function(){
		//alert("sssss");
		 if (typeof selectedItemstot['balance_amount'] == 'undefined') {
			selectedItemstot['tot_price']=selectedItemstot['tot_price'];
		}else{
			selectedItemstot['tot_price']=selectedItemstot['balance_amount'];
		}
		$("div.display_screen").hide();
		$("div#bill_discount_srn_div").show();
		$("button.fun_btns").hide();
		$("button.btnkeyboard_actions").removeClass('btnselected');
		$("button#btn_fun_fdisc").addClass('btnselected');
		$("button.disc_btn_cls").show();
		var cur_d = $("button#up_down_btn" ).attr('rel');
		if(cur_d=='down'){
			$("button#up_down_btn" ).trigger('click');
		}
	}); 
	
		 /*--------------------------------------------------------- Starting of BillPark Events ---------------------------*/
	      
        //on click function of bill park button (Displaying BillParked bills)
         $(document).on("click","button.billparkview",function(e) {
           // alert("I am in billpark view functionality");
        	$("#view_help").hide();
			$("#leftmain_table").show();
            $("#billpark").hide();
			var itemslist=$('#final_items tr').length;
			if(itemslist > 0){
				$("#billpark").css("display", "block");	
			}else{
				//alert('else');
				var parm_obj = {};	
				$("#openorder").hide();	
                $("#leftmain_table").css("display", "none");
				$("#view_billpark").show();
                $("#billpark").hide();
                                
				$.ajax({
					type: "post",
					//url:"index.php?ref=<?php echo $btn_encode_url['pos/getBillParkData']; ?>",
				    url:'${pageContext.request.contextPath}/Orders/getBillParkData',			
					data: parm_obj,
					async: true,
					beforeSend: function(data) {},
					success: function(r, textStatus, xmlReq) {
						//alert(r);
						var s=r.length;
						if(s==2){						
						var output='<h2 style="color:red;text-align:center;margin-top:-7px";>No Items in Billpark</h2>';
					    $('#view_billpark2').html(output);						
						}
						if(r){
							//var displayResources = $('#bill_parksummary');
                            var displayResources = $('#billpark_details');
							//var output ='<div id="printcontent" class="modal-content"><div class="modal-header"><h5 class="modal-title" id="myModalLabel" style="text-align:center;">Bill Park Order </h5>  </div><div class="modal-body" style="height:150px;overflow: scroll;"><div class="row">';
							var output='';
                            var json_data =  jQuery.parseJSON(r);
							$.each(json_data, function( k, v ) {
								var d = new Date(v['created_date']);
								d.getHours(); // => 9
								d.getMinutes(); // =>  30
								if(k%6==0)
									output +='</div><div class="row"><div class="col-sm-2" ><button  data-billno="'+v["phone_number"]+'" class="_button buttonbill" ><p>'+v["phone_number"]+'</p><p> ' + v["created_date"] + ' </p>'+'</button> <button  data-billno="'+v["phone_number"]+'" class="btn btn-danger deletebillpark" >DELETE</button></div>';
								else									
									output +='<div class="col-sm-2" ><button  data-billno="'+v["phone_number"]+'" class="_button buttonbill" ><p>'+v["phone_number"]+'</p><p> ' + v["created_date"] + '  </p>'+'</button><button  data-billno="'+v["phone_number"]+'" class="btn btn-danger deletebillpark" >DELETE</button></div>';
							});
							output +='</div></div>';
						}
						displayResources.html(output);
						$("#billpark_details").css("display", "block");
					},
					error: function(xmlReq, textStatus, errorThrown) {}
				});
			}
		});
    	
		 
    	//deleting bill park	
 		$('#billpark_details').on("click","button.deletebillpark",function(e) {
			var billparkno = $(this).data('billno');
			//alert(billparkno);
			$('#delete_parkorderModal').modal('show');
			$("#okparkorder_item").click(function(){
				$(this).data('clicked', true);
				if($('#okparkorder_item').data('clicked')) {
                    $("#view_billpark").hide();
					$("#leftmain_table").show();
					var params_data = {
						'billno':billparkno
					}
					//alert("This is bill park number:"+billparkno);
					$.ajax({
						type: "post",
						url:'${pageContext.request.contextPath}/Orders/deleteBillPark',
						//url:"index.php?ref=<?php  echo $btn_encode_url['pos/delete_billpark']; ?>",
						data: params_data
					});
				}
			});
		});
 	    		
    	
        //Saving BillPark Data		
 		$(document).on("click","button.billpark_submitBtn",function(e) {
			var phone_number = $("#phone_number").val();
			if(phone_number == ''){
				$(".billpark_msg").text('Please Enter Order Number');	
				$("#phone_number").focus();
			}else{
				var params_data = {
					'selectedItemArray':selectedItemArray,
					'phone_number':phone_number
				}				
				var params_data_json = JSON.stringify(params_data);
			    console.log(params_data_json);
				$.ajax({
					//url: "index.php?ref=<?php echo $btn_encode_url['pos/addBillPark']; ?>",
				    url:'${pageContext.request.contextPath}/Orders/addBillPark',
					type:'post',
					//dataType:'json',
					data: {data_array:params_data_json},
					//data:params_data,
					//data: params_data_json,
					beforeSend: function(data) {},
					success: function(data) {
						 if(data){
							if(data =="Duplicate Phone Number"){
								$('#phonenumber_msg').show(); 
							}
							if(data =="Success"){
								$('#bill_parksuccesModal').modal('show');
								$("#ok_item").click(function(){
									$(this).data('clicked', true);
									if($('#ok_item').data('clicked')) {
									location.reload(true);
									}
								});
							}
							var tparams_obj = {
								'text': 'Bill parked Successfully...',
								'textAlign': 'center',
								'showHideTransition':'fade',
								'position':'center'
							}
                                                                           
							//$.fn.updateToast(tparams_obj);
						} 
					},
					error: function(xmlReq, textStatus, errorThrown) {}
				});
			} 
		});		
        
		
		// to get bill park items from the grid box
		$(document).on("click",".buttonbill",function() {
			$('#searchorder').hide();
			$('#main_table').show();
			var billparkno = $(this).data('billno');
			var params_data = {
				'billno':billparkno
			}
			//alert(billparkno);
			var params_data_json = JSON.stringify(params_data);
			$.ajax({
				type: "post",
				url:'${pageContext.request.contextPath}/Orders/getBillParkItems',
				data: {data_array:params_data_json},
				//async: true,
				beforeSend: function(data) {},
				success: function(r, textStatus, xmlReq) {
					if(r){
						alert("result is "+r);
						var selectedItembillpark =  jQuery.parseJSON(r);
                        // console.log(selectedItembillpark);
						$.each(selectedItembillpark, function( index, value ) {		 
							var portion_id = value['portion_uuid'];
							var item_name = value['name'];
							var discount_val = value['discount_perc'];
							var item_id = value['item_uuid'];
							var gst = value['GST_perc'];
							var sgst;	
							var tqun =0;
							var action_val = '';
                            console.log('********************************');
                            console.log(selectedItemArray);
							if(typeof selectedItemArray =='undefined' || selectedItemArray ==null) selectedItemArray = {};
							if(typeof selectedItemArray[portion_id]!=='undefined' ){
								var tqun= parseInt(selectedItemArray[portion_id]['qty']);
								selectedItemArray[portion_id]['qty'] =tqun + parseInt(1);
								action_val ='add';
							}else {
								var cgst = sgst = gst/2;
								var dis_amt = round(((value['price_bd'] * 1)*discount_val/100),2);
								selectedItemArray[portion_id] = {
									'unit': value['unit'],
									'original_price': value['price_bd'],
									'price': value['price_bd'],
									'name': item_name,
									'qty':value['quantity'],
                                    'size':value['size'],
									'item_id':item_id,
									'original_discount':{
										'percentage': discount_val,
										'amount': dis_amt
									},
									'discount': {
										'percentage':discount_val,
										'amount': dis_amt
									},
									'gst':{
										'all':{
											'percentage':gst,
											'amount':'',
										},
										'cgst':{
											'percentage':cgst,
											'amount':'',
										},
										'sgst':{
											'percentage':sgst,
											'amount':'',
										}
									},
									'total':value['price_bd']
								}
								selectedItemsGstArray[portion_id] ={
									'portion_id':portion_id,
									'item_id':item_id,
									'tprice': round((value['price_bd']- dis_amt),2),
									'gst':gst
								}
							}
							var params = {
								'portion_id':portion_id,
								'action':action_val
							}
							$.fn.bindselecteditems_grid(params);
							$('.button5').removeClass('selected_unit');
							$(this).addClass('selected_unit');
						});
					}
				},
				error: function(xmlReq, textStatus, errorThrown) {}
			});
		});
		
        
 		$(document).on("click","button.billparkBtnclose",function(e) {	
 			$("#billpark").css("display", "none");	
 		});
      
      /*-------------------- Ending of BillPark Events  ----------------------------*/
	
	//--------- Bill Overall Discount button click event --------------------	
    
	  $.fn.calculate_overall_balance_discount = function(parm_obj){
		alert("in overall balance discount..");	
		 var params_data_json = JSON.stringify(parm_obj);
			//console.log();
		$.ajax({
				type: "post",
			//	var generate_disc_url = "index.php?ref=<?php echo $btn_encode_url['pos/generate_overall_discount']; ?>";
				url: generate_disc_url,
			   // url:'${pageContext.request.contextPath}/Pos/generate_overall_discount',
			    data: {data_array:params_data_json},
				async: true,
				beforeSend: function(data) {},
				success: function(r, textStatus, xmlReq) {
					if(r){
						var json_data =  jQuery.parseJSON(r);
						selectedItemArray = json_data['selectedItemArray'];
						overall_bill_disc = json_data['over_dis'];
						if(selectedItemArray == null) $.fn.resetData();
					    var html_d =  'Additional Discount Amount : ₹'+json_data['over_dis']['amount']+'/- <span id="addt_dis_per">('+json_data['over_dis']['percentage']+'%)</span>';
						$("span#addt_dis_amt").html(html_d);
						var p ={};
						$.fn.bindpartiallyselecteditems_grid(p);
						$.fn.getAdditional_dis_items();
					}
				},
				error: function(xmlReq, textStatus, errorThrown) {}
			});
		} 
      
	  $.fn.bindpartiallyselecteditems_grid = function(params={}){
			var item_inc_val =0; 
			var fval='';
			$('#final_items').html('');
			$.fn.calc_partialorderTotalPrice();
			if(params['action'] !=undefined && typeof overall_bill_disc !=undefined && overall_bill_disc['cur_amount']>0 ){
				var p_obj = {
					'cur_amount':overall_bill_disc['cur_amount'],
					'dis_perc':overall_bill_disc['dis_perc'],
					'selectedItemArray':selectedItemArray,                                      
					'selectedItemstot':selectedItemstot,
					'selectedItemsGstArray':selectedItemsGstArray,
				}
				console.log("In bindpartiallyselecteditems-grid");
				console.log(p_obj);
				console.table(p_obj);
				$.fn.calculate_overall_balance_discount(p_obj);return false;
			}
			$.each(selectedItemArray, function( index, value ) {
				item_inc_val++;
				var dis_amt_a = parseFloat(value['discount']['amount']);
				var dis_amt_per = parseFloat(value['discount']['percentage']);
				if(typeof value['additional_discount'] !='undefined' ){
					dis_amt_a += value['additional_discount']['amount'];
					dis_amt_per += value['additional_discount']['percentage'];
				}
				if(dis_amt_per >100) dis_amt_per =100;
				fval +='<tr class="" id="'+index+'">';
				fval +='<td class="selected_item_row" width="4%">'+ item_inc_val +'</td>'; 
				fval +='<td class="selected_item_row" width="20%">'+value['name']+'</td>';
				fval +='<td width="4%" class="qtot selected_item_row">'+ value['qty'] +'</td>';
				fval +='<td class="selected_item_row" width="10%">'+  value['unit'] +'</td>';
	                        fval +='<td class="selected_item_row" width="10%">'+  value['size'] +'</td>';
				fval +='<td class="selected_item_row" width="10%">'+  round(value['price'],2) +'</td>';
				fval +='<td class="selected_item_row" width="10%" align="right">'+ round(dis_amt_a,2) +'</br>('+round(dis_amt_per,2)+'%)</td>';
				fval +='<td class="selected_item_row" width="7%" align="right">'+  value['gst']['all']['percentage'] +'%</td>';
				fval +='<td width="10%" class="subtot selected_item_row">'+ round(value['total'],2)+'</td>';
				fval +='<td width="17%" > ';
				fval +='<a data-item="'+ value['item_id'] +'" data-value="'+ index +'" data-id="add" class="item_unit_action" href="javascript:void(0);"><span class="plus"><img src="${pageContext.request.contextPath}/resources/images/add.png" class="add_remove_icon_cls"></span></a>';
				fval +='<a data-item="'+ value['item_id'] +'" data-value="'+ index +'" data-id="less" class="item_unit_action" href="javascript:void(0);"><span class="minus"><img src="${pageContext.request.contextPath}/resources/images/less.png" class="add_remove_icon_cls"></span></a>';
				fval +='<a data-item="'+ value['item_id'] +'" data-value="'+ index +'" data-id="delete" class="item_unit_action" href="" data-toggle="modal" data-target=""><span class="delete"><img src="${pageContext.request.contextPath}/resources/images/delete.png" class="add_remove_icon_cls"></span></a>';
				fval +='</td>';
				fval +='</tr>';
			});
			$('#final_items').append(fval);
			if(params['portion_id'] !='undefined' && params['portion_id']!=''){
				$('#final_items tr#'+params['portion_id']).addClass('rowselected');
				var cur_div_stat = $("div#edit_item_srn_div").css("display");
				var  cur_edit_item_id = $("div#edit_item_srn_div input#hdn_item_portion_id").val();
				if(cur_div_stat =='block' && cur_edit_item_id == params['portion_id']){
					if(params['action']=='delete'){
						$("div.display_screen").hide();
						$("div#edit_item_srn_div input#hdn_item_portion_id").val('');
						$("div#welcome_screen_div").show();
					}else {
						$('#final_items tr#'+params['portion_id']+'').find('td:first').trigger('click');
					}
				}
			}
			if(item_inc_val==0){
				$("div.display_screen").hide();
				$("div#edit_item_srn_div input#hdn_item_portion_id").val('');
				$("div#welcome_screen_div").show();
			}
			return false;
		}; 
		 $.fn.calc_partialorderTotalPrice =function (){ 
			var qty_val = 0; 
			var tot_price = 0;
			var billnumber = 0;
			var tot_gst_price = 0;
			var tot_sgst_price =0 ;
			var tot_cgst_price = 0;
			var balanceamount;
			var tot_before_gst = 0;
			var discount_value=0;
			var adit_dis_amt =0;
			var tot_priceafterdiscount =0;
			var tot_pricevalue=0;
			$.each(selectedItemArray, function( index, value ) {
				qty_val +=parseInt(value['qty']);
				var qty_price = value['qty']*value['price'];
				var Billno = value['billno'];
				if(value['discount']['percentage']>0)
					var dis_amt = (qty_price*value['discount']['percentage'])/100;
				else {
					var dis_amt = value['discount']['amount'];
					var dis_per = ((dis_amt/qty_price)*100);
					selectedItemArray[index]['discount']['percentage'] = round(dis_per,2);
				}
				selectedItemArray[index]['discount']['amount']= round(dis_amt,2);
				var item_tot_price =  qty_price-dis_amt;
				tot_before_gst +=item_tot_price;
				selectedItemsGstArray[index]['tprice'] = item_tot_price;
				if(typeof selectedItemArray[index]['additional_discount'] !='undefined' ){
					adit_dis_amt = selectedItemArray[index]['additional_discount']['amount'].toFixed(0);
					item_tot_price =item_tot_price =item_tot_price-adit_dis_amt; 
				}
				// ---------- GST Cal --------
				var gst_amt = (item_tot_price*parseInt(selectedItemArray[index]['gst']['all']['percentage']))/(100+parseInt(selectedItemArray[index]['gst']['all']['percentage']));
				tot_gst_price +=gst_amt;
				var sgst = cgst = gst_amt/2;
				tot_sgst_price +=sgst;
				tot_cgst_price +=cgst;
				selectedItemArray[index]['gst']['all']['amount']=round(gst_amt,2);
				selectedItemArray[index]['gst']['sgst']['amount']=round(sgst,2);
				selectedItemArray[index]['gst']['cgst']['amount']=round(cgst,2);
				tot_price +=item_tot_price;
				selectedItemArray[index]['total']= round(item_tot_price,2);
				billnumber =selectedItemArray[index]['billno'];
				if(adit_dis_amt >0){
					discount_value+=parseInt(adit_dis_amt);
					tot_pricevalue= selectedItemstot['balance_amount']-parseInt(discount_value);
					tot_priceafterdiscount=tot_pricevalue.toFixed(0);
				}
			});
			var billno_data = {
					'billno':billnumber,
				}
				$.ajax({
					url: "index.php?ref=<?php echo $btn_encode_url['pos/getbalanceamountof_preorder']; ?>",
					type:'post',
					dataType:'json',
					data: billno_data,
					success: function(r, textStatus, xmlReq) {
						if(r){
							balanceamount= r[0]['balance_amt'];
							$("#balance_amt").val(balanceamount);
							var balance_amount = $("#balance_amt").val();
						}
					},
					async: false
				});
				var balance_amount = $("#balance_amt").val();
				if(tot_priceafterdiscount >0){
					var totalpricevalue=tot_price+discount_value;
					selectedItemstot = {
						'qty_val':round(qty_val,2),
						'tot_price':round(tot_price,2),
						'tot_before_gst':round(totalpricevalue,2),
						'tot_gst_amout':round(tot_gst_price,2),
						'tot_sgst_amout':round(tot_sgst_price,2),
						'tot_cgst_amout':round(tot_cgst_price,2),
						'remaining_amount':round(tot_priceafterdiscount,2),
						'balance_amount':round(tot_priceafterdiscount,2)
					};
					$('.grdtot').text('₹'+selectedItemstot['balance_amount'].toFixed(2)+'/-');
					$("span#cgst_amt_span").html(tot_cgst_price.toFixed(2));
					$("span#sgst_amt_span").html(tot_sgst_price.toFixed(2));
					$("span#disc_befgst_amt").html('₹'+selectedItemstot['tot_before_gst']+'/-');
				}else{
					selectedItemstot = {
						'qty_val':round(qty_val,2),
						'tot_price':round(tot_price,2),
						'tot_before_gst':round(tot_price,2),
						'tot_gst_amout':round(tot_gst_price,2),
						'tot_sgst_amout':round(tot_sgst_price,2),
						'tot_cgst_amout':round(tot_cgst_price,2),
						'remaining_amount':round(balance_amount,2),
						'balance_amount':round(balance_amount,2)
					};
				$.each(selectedItemArray, function( index, value ) {
					selectedItemsGstArray[index]['totalbillprice'] = selectedItemstot['tot_price'];
				});
				$('.grdtot').text('₹'+selectedItemstot['balance_amount'].toFixed(2)+'/-');
				$("span#cgst_amt_span").html(tot_cgst_price.toFixed(2));
				$("span#sgst_amt_span").html(tot_sgst_price.toFixed(2));
				$("span#disc_befgst_amt").html('₹'+selectedItemstot['tot_before_gst']+'/-');
				}
				$.fn.bindpreorderpaymentGrid();
				$('.qalltot').text(qty_val);
				return false;
			}
	
		 
			/*----------------- Cake Order Button Code -------------------------*/
			$(document).on("click","#clear_button",function(e) {	
				$('#defaultKeypad').val(''); //defaultKeypad is textbox ID
				$( "#defaultKeypad" ).focus();
			});	
		
			var qwertyLayout = [
				$.keypad.qwertyAlphabetic[0] + $.keypad.CLOSE, 
				$.keypad.HALF_SPACE + $.keypad.qwertyAlphabetic[1] + 
				$.keypad.HALF_SPACE + $.keypad.CLEAR, 
				$.keypad.SPACE + $.keypad.qwertyAlphabetic[2] + 
				$.keypad.SHIFT + $.keypad.BACK
			];
			$('.qwertyKeypad').keypad({keypadOnly: false, layout: qwertyLayout});
		
            
		//-------------- Clock Functionality --------------------
		updateClock();
		setInterval(updateClock, 1000);
		function updateClock ( ){
			var currentTime = new Date ( );
			var cur_date =currentTime.getDate() + "-" + currentTime.getMonth() + "-" + currentTime.getYear(); 
			var x = new Date()
			var x1=x.getMonth() + "/" + x.getDate() + "/" + x.getFullYear(); 
			var currentHours = currentTime.getHours ( );
			var currentMinutes = currentTime.getMinutes ( );
			var currentSeconds = currentTime.getSeconds ( );
			currentMinutes = ( currentMinutes < 10 ? "0" : "" ) + currentMinutes;
			currentSeconds = ( currentSeconds < 10 ? "0" : "" ) + currentSeconds;
			currentHours = ( currentHours == 0 ) ? 12 : currentHours;
			var currentTimeString = currentHours + ":" + currentMinutes + ":" + currentSeconds ;
			$(".clock-time").html(currentTimeString);
			$("div.clock-date").html(cur_date);
		}
		$( "#hd_2" ).click(function() {
			$( "#hd_2" ).slideUp('fast');
			$( "#hd_1" ).slideDown( "slow", function() {});
		});
		$( "#down_array" ).click(function() {
			$( "#hd_1" ).slideUp( "slow",function(){
				$( "#hd_2" ).slideDown( "slow", function() {});
			});
		});
		
		
		/***************orders script***************************/
	
	/* ----------- Cake order/PreOrder	---------------- */
	
	$(document).on("click","button#pre_order",function(e) {
		e.preventDefault();
		var itemslist=$('#final_items tr').length;
		if(itemslist <= 0){
			$('#preorder_Modal').modal('show');
		}else{
			$("#view_deliveryorderbuttons").hide(); 	
			$("#leftmain_table").hide(); 
			$("#view_preorderdetails").show(); 
			$("#view_estimateorderdetails").hide(); 
			$("#view_orderdetails").hide();
	        $("#btob_orderdetails").hide();
		}
	});	
	
	
	
	$(document).on("click","button#preorder_submit",function(e) {
		e.preventDefault();
                var phoneno = /^\d{10}$/;
                //alert(phoneno);
		var customer_name = $("#precustomer_name").val();
                var addons=$("#addons").val();
		var deliverydatetime = $("#dtp_input2").val();
		var payment_mode = $("input[name='payment_mode']:checked").val();
		if(payment_mode == 'card'){
			var cardtype_mode = $("input[name='card_type_val']:checked").val();
		}
		var phone_num = $("#prephone_num").val();
                //alert(prephone_num.value);
      	var general_uuid="<%=general_uuid%>"; //uncomment it  
  
		var advance = $("#preadvance").val();
		var datetime_delivery = $("#pre_datetime_delivery").val();
		var cust_address = $("#cust_address").val();
		if(customer_name =='' || phone_num =='' || deliverydatetime == ''){
			$('#msg_preorder').html('Please fill all the required fields');
		}
                else if(prephone_num.value.match(phoneno))
                {
                    //alert("Hi");
                        var preorderItemsadvance =  $("#preadvance").val();
			if(preorderItemsadvance == '')
				var selectedItemsadvance=0;
			else 
				var selectedItemsadvance =  preorderItemsadvance;
			var params_data = {
				'selectedItemArray':selectedItemArray,
				'payment_mode_val':payment_mode,
				'selectedItemstot':selectedItemstot,
				'selectedItemsadvance':selectedItemsadvance,
				'overall_bill_disc':overall_bill_disc,
				'bill_type':'Partial Payment',
				'customer_name':customer_name,
				'phone_num':phone_num,
				'cust_address':cust_address,
				'cardtype_mode':cardtype_mode,
				'deliverydatetime':deliverydatetime,
                'addons':addons,
                'trans_type':general_uuid,
                'token_flag':'1'
			}
			
			var params_data_json = JSON.stringify(params_data);
			console.log("params_data_json is:"+params_data_json);
			if(billcheck==null  && parseFloat(selectedItemstot['qty_val']) > 0){
			var printableObjects = []; 
			billcheck=$.ajax({
				//url: "index.php?ref=<?php echo $btn_encode_url['pos/generatePreOrderBill']; ?>",
			    url:'${pageContext.request.contextPath}/Orders/generatePreOrderBill',
				type:'post',
				dataType:'text',
				data: {data_array:params_data_json},
				beforeSend: function(data) {},
				success: function(r, xmlReq) {
					if(r){
						//alert(r);
						if(r=='Failed'){
							$('#order_Modal').modal('show');
	                        $("#order_id").click(function(){
	                        });
						}else{

							if(r=="Linux@Success"){
								window.location.reload(true);
							}else if(r=="Linux@Failed"){
								$('#printer_settings_modal').modal('show');
		                        $("#printer_settings_id").click(function(){
		                        	window.location.reload(true);
		                        });
							}
							else{
								window.location.reload(true);
								$("#print_data").html(r);
								var print_last= $("#print_data").html();
								$("button.clear_all_data").trigger('click');
								printableObjects.push(print_last);  // pushing response to array.
								printableObjects.forEach(function(d){
									printContent(d);
								});
							}
						}
						
						/* var pdata = {
							'Billno': r.Billno
						};
                        if(pdata['Billno'] == false){
                              $('#order_Modal').modal('show');
                              $("#order_id").click(function(){
                              	window.location.reload(true);
                              });

                         }
                         else{
							$.fn.genrate_preorderreceipt(pdata);
						}
                        $("button.clear_all_data").trigger('click'); */
					}
				},
				error: function(xmlReq, textStatus, errorThrown) {}
			});
				}
		}
                else {
                //alert("hello");
                $('#msg_preorder').html('(*)Please enter valid phonenumber');
                }
	}); 

	
	
	$(document).on("click","#preorder_close",function(e) {	
		$( "#view_preorderdetails" ).hide();
		$("#leftmain_table").show();	
	});	
	
	
	$(document).on("click","#cakeorder_close",function(e) {	
		$( "#view_estimateorderdetails" ).hide();
		$("#leftmain_table").show();	
	});	
    
	
	
	/*===================starting of delivery orders=====================*/
	
	
	// delivery_orders
	$(document).on("click","#deliveryorders",function(e) {
            $("#view_billpark").hide();
            $("#view_help").hide();
			$("#leftmain_table").hide();
			$("#view_estimateorderdetails").hide(); 
			$("#view_preorderdetails").hide(); 
			$("#view_orderdetails").hide(); 
			$("#view_deliveryorderbuttons").show(); 
	});

	
	$( '[id^="delivery_"]' ).on("click",function(){
		$("#main_table").hide();
		var idvaluedelivery=$(this).attr('id');
		var id_delivery = idvaluedelivery.split("_");
		var hoursvalue=id_delivery[1];
		$.ajax({
			//url: "index.php?ref=<?php echo $btn_encode_url['pos/gethoursdeliverydata']; ?>",
			url:"${pageContext.request.contextPath}/Orders/gethoursdeliverydata",
			type:'post',
			dataType : 'text',
			data:  { hoursvalue : hoursvalue },
			beforeSend: function(data) {},
			success: function(r, xmlReq) {
				if(r){
				$(".right_content").show();
				$("#deliveryorderdetails").show();
				$("#deliveryorderdetails").html(r);
				}
			},
			error: function(xmlReq, textStatus, errorThrown) {}
		});
		$("#deliveryorderdetails").show();
	});
	
	$(document).on("click","#deliveryorders_close", function() {
		$("#view_deliveryorderbuttons").hide();
		$("#leftmain_table").show();
	});
	
    $(document).on("click",".close_delivery", function() {
		$("#deliveryorderdetails").hide();
		$("#main_table").show();
	});
    
    
	$(document).ready(function () {
		$(document).on("click",".delivery_button", function() {
			$("#searchorder").hide();
		});
	});

     	
	/*========================delivery orders end=======================*/
	
	/**************payment code********************************************/
	$(document).on("click","button#payment_btn", function(e) {
		e.preventDefault();
		var itemslist=$('#final_items tr').length;
		if(itemslist <= 0){
			$('#cashbill_Modal').modal('show');
		}else{
			$('.display_screen').hide();
			$('#payment_srn_div').show(); 
			$('.fun_btns').hide();
			$('.pay_btns').removeClass('btnselected').show();
			$('.pay_btns[rel="cash"]').addClass('btnselected');
			var cur_d = $("button#up_down_btn" ).attr('rel');
			//alert(cur_d);
			if(cur_d=='down'){
				$("button#up_down_btn" ).trigger('click');
			}
		}
	  });
	// Calculator 
	$('#equal_button').on('click', function(evt) {
		calc_clicks = 0;
		$("#dis_newval").css("display", "none");
		$( "span#prod_discount" ).show();
	});
	$('.btnkeyboard').on('click', function(evt) {
		var buttonPressed = $(this).attr('rel');
		var buttonPressed_val = $(this).html();
		//alert(buttonPressed_val);
		if($('#price_newval').css('display') == 'block'){
			if(calc_clicks == 0){
				//alert(buttonPressed_val);
				$("#price_newval").val(buttonPressed_val);
				calc_clicks++;
			}else{
				$('#price_newval').append(buttonPressed_val);
				var currentVal = $('#price_newval').val();
				var newVal = currentVal + buttonPressed_val;
				$('#price_newval').val(newVal);
				calc_clicks++;
			}
		}else if($('#quantity_newval').css('display') == 'block'){
			if(calc_clicks == 0){
				$("#quantity_newval").val(buttonPressed_val);
				calc_clicks++;
			}else{
				$('#quantity_newval').append(buttonPressed_val);
				var currentVal = $('#quantity_newval').val();
				var newVal = currentVal + buttonPressed_val;
				$('#quantity_newval').val(newVal);
				calc_clicks++;
			}
		}else if($('#dis_newval').css('display') == 'block'){
			if(calc_clicks == 0){
				$("#dis_newval").val(buttonPressed_val);
				calc_clicks++;
			}else{
				$('#dis_newval').append(buttonPressed_val);
				var currentVal = $('#dis_newval').val();
				var newVal = currentVal + buttonPressed_val;
				$('#dis_newval').val(newVal);
				calc_clicks++;
			}
		}
                else if($('#size_newval').css('display') == 'block'){
                   // alert('size_newval');
			if(calc_clicks == 0){
				$("#size_newval").val(buttonPressed_val);
				calc_clicks++;
			}else{
				$('#size_newval').append(buttonPressed_val);
				var currentVal = $('#size_newval').val();
				var newVal = currentVal + buttonPressed_val;
				$('#size_newval').val(newVal);
				calc_clicks++;
			}
		}
	});
	$(document).on("click","button.btn_amt",function(e) {
		//alert('btn_amt');
		var params_obj = {
			'rem_amt':true
		}
		$.fn.addAmount(params_obj);
	});
	
	//******************Click on payment Button*******************//
	$(document).on("click","button.btn_payment",function(e) {
		e.preventDefault();
		console.log(payment_mode_ary);
		var general_uuid="<%=general_uuid%>";
        //alert(general_uuid);
		if(typeof ReturnAmount_arr['ReturnAmount'] != 'undefined'){
			//alert(ReturnAmount_arr['ReturnAmount_autoids']);
			console.log(ReturnAmount_arr['ReturnAmount']);
			console.log(ReturnAmount_arr['ReturnAmount_autoids']);
			var params_data = {
				'selectedItemArray':selectedItemArray,
				'payment_mode_ary':payment_mode_ary,
				'selectedItemstot':selectedItemstot,
				'overall_bill_disc':overall_bill_disc,
				'bill_type':'payment',
				'ReturnAmount':ReturnAmount_arr['ReturnAmount'],
				'ReturnAmount_autoids':ReturnAmount_arr['ReturnAmount_autoids'],
				'random_credit':ReturnAmount_arr['random_credit'],
				'retunIDs':retunIDs,
	            'trans_type':general_uuid
		    }
		}
		else if(typeof voucher_arr['voucherid'] != 'undefined'){
			console.log(voucher_arr['voucherid']);
			console.log(voucher_arr['voucher_discount']);
			var params_data = {
				'selectedItemArray':selectedItemArray,
				'payment_mode_ary':payment_mode_ary,
				'selectedItemstot':selectedItemstot,
				'overall_bill_disc':overall_bill_disc,
				'bill_type':'payment',
				'voucherid':voucher_arr['voucherid'],
				'voucher_discount':voucher_arr['voucher_discount'],
				'vouchertype':voucher_arr['vouchertype']
		    }
		}
       else if(typeof order_arr['trans_type'] != 'undefined'){
            //alert(order_arr['trans_type']);
            var params_data = {
                  'selectedItemArray':selectedItemArray,
                  'payment_mode_ary':payment_mode_ary,
                  'selectedItemstot':selectedItemstot,
                  'overall_bill_disc':overall_bill_disc,
                  'bill_type':'payment',
                  'trans_type':order_arr['trans_type']
             }
          // alert('elseifcondition');
          // alert(params_data['trans_type']);
        }
		else{
           //alert('else case');
			var params_data = {
				'selectedItemArray':selectedItemArray,
				'payment_mode_ary':payment_mode_ary,
				'selectedItemstot':selectedItemstot,
				'overall_bill_disc':overall_bill_disc,
				'bill_type':'payment',
                'trans_type':general_uuid
			}
	   }
	var params_data_json = JSON.stringify(params_data);
	//alert(params_data_json);
	if(billcheck==null  && parseFloat(selectedItemstot['qty_val']) > 0){
		var printableObjects=[];
	   billcheck=$.ajax({
			url: "${pageContext.request.contextPath}/Bills/generateBill",
			type:'post',
			dataType:'text',
			data: {data_array:params_data_json},
			beforeSend: function(data) {},
			success: function(r, textStatus, xmlReq) {
				if(r){
					if(r=='Failed'){
						$('#order_Modal').modal('show');
                        $("#order_id").click(function(){
                        });
					}else{

						if(r=="Linux@Success"){
							window.location.reload(true);
						}else if(r=="Linux@Failed"){
							$('#printer_settings_modal').modal('show');
	                        $("#printer_settings_id").click(function(){
	                        	window.location.reload(true);
	                        });
						}
						else{
							window.location.reload(true);
							$("#print_data").html(r);
							var print_last= $("#print_data").html();
							$("button.clear_all_data").trigger('click');
							printableObjects.push(print_last);  // pushing response to array.
							printableObjects.forEach(function(d){
								printContent(d);
							});
						}
					}
					/* var pdata = {
						'Billno': r.Billno,
						'ReturnAmount':ReturnAmount_arr['ReturnAmount']
					 };
					if(typeof ReturnAmount_arr['ReturnAmount'] != 'undefined'){
						$.fn.genrate_Return_receipt(pdata);
					}

					else{
	                    if(pdata['Billno'] == false){

	                          $('#order_Modal').modal('show');
	                          $("#order_id").click(function(){

	                           });
						   }
                           else{
								$.fn.genrate_receipt(pdata);
                        	}
					}
					$("button.clear_all_data").trigger('click'); */
					}
				},
				error: function(xmlReq, textStatus, errorThrown) {}
			});
		}
	});
	/**************payment code end********************************************/
	/*********** Direct Cash bill******************************/
	$(document).ready( function () {
		$(document).on("click","#cashbill_button", function() {
           var general_uuid="<%=general_uuid%>";
           //alert("general uuid is"+general_uuid);
		   var itemslist=$('#final_items tr').length;
		   //alert(itemslist);
			if(itemslist <= 0){
				$('#cashbill_Modal').modal('show');
			}
			else{
			//$( "#btn_amt" ).trigger( "click" );
				var params_obj = {
					'rem_amt':true
				}
				var cur_mode = 'Cash';	
				if(params_obj['rem_amt']){
					var cur_amount  = selectedItemstot['remaining_amount'];
				}	
				if(cur_amount>0 && typeof selectedItemstot['remaining_amount'] !=='undefined' ){
					payment_mode_ary[cur_mode] = {
						'amount': eval(cur_amount)
					}
				}
			    $.fn.bindpaymentGrid();
				//console.log(payment_mode_ary);
				if(typeof ReturnAmount_arr['ReturnAmount'] != 'undefined'){
					//alert(ReturnAmount_arr['ReturnAmount_autoids']);
					console.log(ReturnAmount_arr['ReturnAmount']);
					console.log(ReturnAmount_arr['ReturnAmount_autoids']);
					var params_data = {
						'selectedItemArray':selectedItemArray,
						'payment_mode_ary':payment_mode_ary,
						'selectedItemstot':selectedItemstot,
						'overall_bill_disc':overall_bill_disc,
						'bill_type':'payment',
						'ReturnAmount':ReturnAmount_arr['ReturnAmount'],
						'ReturnAmount_autoids':ReturnAmount_arr['ReturnAmount_autoids'],
						'random_credit':ReturnAmount_arr['random_credit'],
						'retunIDs':retunIDs,
		                'trans_type':general_uuid
				    }
			     }
				else if(typeof voucher_arr['voucherid'] != 'undefined'){
					console.log(voucher_arr['voucherid']);
					console.log(voucher_arr['voucher_discount']);
					alert('voucher');
					var params_data = {
						'selectedItemArray':selectedItemArray,
						'payment_mode_ary':payment_mode_ary,
						'selectedItemstot':selectedItemstot,
						'overall_bill_disc':overall_bill_disc,
						'bill_type':'payment',
						'voucherid':voucher_arr['voucherid'],
						'voucher_discount':voucher_arr['voucher_discount'],
						'vouchertype':voucher_arr['vouchertype'],
		                'trans_type':general_uuid
				    }
				 }
                else if(typeof order_arr['trans_type'] != 'undefined'){
	                    // alert(order_arr['trans_type']);
	                    var params_data = {
							'selectedItemArray':selectedItemArray,
							'payment_mode_ary':payment_mode_ary,
							'selectedItemstot':selectedItemstot,
							'overall_bill_disc':overall_bill_disc,
							'bill_type':'payment',
		                    'trans_type':order_arr['trans_type']
					 }
                 }
                        
			    else{
					var params_data = {
						'selectedItemArray':selectedItemArray,
						'payment_mode_ary':payment_mode_ary,
						'selectedItemstot':selectedItemstot,
						'overall_bill_disc':overall_bill_disc,
						'bill_type':'payment',
	                    'trans_type':general_uuid
					 }
			     }
				console.log(params_data);
				console.log(billcheck);
				console.log(parseInt(selectedItemstot['qty_val']));
				console.log(billcheck==null);
				var params_data_json = JSON.stringify(params_data);
				//alert(params_data_json);
				if(billcheck==null  && parseInt(selectedItemstot['qty_val']) > 0){
					var printableObjects=[];
					billcheck=$.ajax({
						url: "${pageContext.request.contextPath}/Bills/generateBill",
						type:'post',
						dataType:'text',
						data: {data_array:params_data_json},
						beforeSend: function(data) {},
						success: function(r, textStatus, xmlReq) {
							if(r){
								if(r=='Failed'){
									$('#order_Modal').modal('show');
			                        $("#order_id").click(function(){
			                        });
								}else{
									if(r=="Linux@Success"){
										window.location.reload(true);
									}else if(r=="Linux@Failed"){
										$('#printer_settings_modal').modal('show');
				                        $("#printer_settings_id").click(function(){
				                        	window.location.reload(true);
				                        });
									}
									else{
										window.location.reload(true);
										$("#print_data").html(r);
										var print_last= $("#print_data").html();
										$("button.clear_all_data").trigger('click');
										printableObjects.push(print_last);  // pushing response to array.
										printableObjects.forEach(function(d){
											printContent(d);
										});
									}
								}
							}
						 },
						 error: function(xmlReq, textStatus, errorThrown) {}
					    });
					}
			    //$( ".btn_payment" ).trigger( "click" );
			  }
		});
	});
	function printContent(printDoc){
		var restorepage = document.body.innerHTML;
		var printcontent = printDoc;
		document.body.innerHTML = printcontent;
		window.print();
		document.body.innerHTML = restorepage;
	}
	/*============================cash bill end================================*/
	
	/*============================token bill start================================*/
	var token_bill={};
	$(document).ready( function () {
		$(document).on("click","#tokenbill_button", function() {
			var itemslist=$('#final_items tr').length;
			var general_uuid="<%=general_uuid%>";
			if(itemslist <= 0){
				$('#cashbill_Modal').modal('show');
			}
			else
			{
			//$( "#btn_amt" ).trigger( "click" );
				var params_obj = {
					'rem_amt':true
			    }
				var cur_mode = 'Cash';	
				if(params_obj['rem_amt']){
					var cur_amount  = selectedItemstot['remaining_amount'];
			 	}	
				if(cur_amount>0 && typeof selectedItemstot['remaining_amount'] !=='undefined' ){
					payment_mode_ary[cur_mode] = {
						'amount': eval(cur_amount)
					}
				}
				$.fn.bindpaymentGrid();
				//console.log(payment_mode_ary);
				if(typeof ReturnAmount_arr['ReturnAmount'] != 'undefined'){
					//alert(ReturnAmount_arr['ReturnAmount_autoids']);
					console.log(ReturnAmount_arr['ReturnAmount']);
					console.log(ReturnAmount_arr['ReturnAmount_autoids']);
					var params_data = {
						'selectedItemArray':selectedItemArray,
						'payment_mode_ary':payment_mode_ary,
						'selectedItemstot':selectedItemstot,
						'overall_bill_disc':overall_bill_disc,
						'bill_type':'payment',
						'ReturnAmount':ReturnAmount_arr['ReturnAmount'],
						'ReturnAmount_autoids':ReturnAmount_arr['ReturnAmount_autoids'],
						'random_credit':ReturnAmount_arr['random_credit'],
						'retunIDs':retunIDs,
		                'trans_type':general_uuid
			        }
			   }
			   else if(typeof voucher_arr['voucherid'] != 'undefined'){
					console.log(voucher_arr['voucherid']);
					console.log(voucher_arr['voucher_discount']);
					var params_data = {
						'selectedItemArray':selectedItemArray,
						'payment_mode_ary':payment_mode_ary,
						'selectedItemstot':selectedItemstot,
						'overall_bill_disc':overall_bill_disc,
						'bill_type':'payment',
						'voucherid':voucher_arr['voucherid'],
						'voucher_discount':voucher_arr['voucher_discount'],
						'vouchertype':voucher_arr['vouchertype'],
		                'trans_type':general_uuid
				    }
				}
                        
			  else{
				var params_data = {
					'selectedItemArray':selectedItemArray,
					'payment_mode_ary':payment_mode_ary,
					'selectedItemstot':selectedItemstot,
					'overall_bill_disc':overall_bill_disc,
					'bill_type':'payment',
                    'trans_type':general_uuid,
                    'token_flag':'1'
				}
			  }
				
				console.log(params_data);
				console.log(billcheck);
				console.log(parseInt(selectedItemstot['qty_val']));
				console.log(billcheck==null);
				var params_data_json = JSON.stringify(params_data);
				if(billcheck==null  && parseInt(selectedItemstot['qty_val']) > 0){
				var printableObjects=[];
				billcheck=$.ajax({
					url: "${pageContext.request.contextPath}/Bills/generateBill",
					type:'post',
					dataType:'text',
					data: {data_array:params_data_json},
					beforeSend: function(data) {},
					success: function(r, textStatus, xmlReq) {
						if(r){
							if(r=='Failed'){
								$('#order_Modal').modal('show');
		                        $("#order_id").click(function(){
		                        });
							}else{

								if(r=="Linux@Success"){
									window.location.reload(true);
								}else if(r=="Linux@Failed"){
									$('#printer_settings_modal').modal('show');
			                        $("#printer_settings_id").click(function(){
			                        	window.location.reload(true);
			                        });
								}
								else{
									window.location.reload(true);
									$("#print_data").html(r);
									var print_last= $("#print_data").html();
									$("button.clear_all_data").trigger('click');
									printableObjects.push(print_last);  // pushing response to array.
									printableObjects.forEach(function(d){
										printContent(d);
									});
								}
							}
							/* var pdata = {
								'Billno': r.Billno
							};
                            //alert(pdata['Billno']);
							if(typeof ReturnAmount_arr['ReturnAmount'] != 'undefined'){				
								var pdata = {
									'Billno': r.Billno,
									'ReturnAmount':ReturnAmount_arr['ReturnAmount']
								};
								$.fn.genrate_Return_receipt(pdata);
							}
							else if(typeof token_bill['status'] != 'undefined'){
                                //alert('token');
								$.fn.genrate_tokenreceipt(pdata);
							}
							else{
								$.fn.genrate_receipt(pdata);
							}
							$("button.clear_all_data").trigger('click'); */
						}
					},
				  error: function(xmlReq, textStatus, errorThrown) {}
				});
		     }
			//$( ".btn_payment" ).trigger( "click" );
			}
			/*$( "#payment_btn" ).trigger( "click" );
			$( "#btn_amt" ).trigger( "click" );*/
			token_bill = {
				'status':1,
			};
			//$( ".btn_payment" ).trigger( "click" );
		});
	});
	

	/*============================token bill end================================*/
	
	/*======================return order ===================*/
	
	  	$(document).on("click","button#return_order",function(e) {
			$("#returnorder").show();	
			$("#searchorder").hide();	
			$("#deliveryorderdetails").hide();	
			$(".right_bottom_div").hide();	
			$("#orderprintdiv1").hide();	
			$("#main_table").hide(); 
		});
	     
	    //return order submit
		$(document).on("click","button.returnorder_submitBtn",function(e) {
		    $("#leftmain_table").hide();
			var orderid_return = $("#orderid_return").val();
			alert("return order id is"+orderid_return);
			var session_outlet="<%=outlet_uuid%>";
			alert("session_outlet:"+session_outlet);
			var session_outletconcat = parseInt(session_outlet)+parseInt("10");
                       // var d = new Date();
                       // var n = d.getFullYear();
			var orderid_return_concat = session_outletconcat+orderid_return;
			//var orderid_return_concat = orderid_return;
			alert("orderid_return_concat:"+orderid_return_concat);
			if(orderid_return == ''){
				$("div#errormessage").html("Please Enter Bill Number");
				$("#orderid_return").focus();
			}else{
				$("div#errormessage").html("");
				$("#searchorder").hide();
				$("#view_deliveryorderbuttons").hide();
				var params_data = {
					'Billno':orderid_return_concat,
					'orderIDreturn':orderid_return   //by siva
				}
				$.ajax({
				//url: "index.php?ref=<?php echo $btn_encode_url['pos/getReturndate']; ?>",
				url:'${pageContext.request.contextPath}/Orders/getReturndate',
				type:'post',
				data: params_data,
				success: function(r, textStatus, xmlReq) {
					if(r){
                           alert("in getReturnData r case ");      //alert(r);
						 if(r == 1){
						    alert("in r = 1 case");
                            $("#view_orderdetails").show();
                            $("#leftmain_table").hide();
                            $.fn.genrate_returnvieworder(params_data);
                          }else{
                            $('#returndate_Modal').modal('show');
                            $("#leftmain_table").show();
                         }
					}
				},
				error: function(xmlReq, textStatus, errorThrown) {}
			});
				
			}
		});
	    
		$.fn.genrate_returnvieworder = function(params){
			var printableObjects=[];
			$.ajax({
				//url: "index.php?ref=<?php echo $btn_encode_url['pos/generateReciptReturnView']; ?>",
				url:'${pageContext.request.contextPath}/Orders/generateReciptReturnView',
				type:'post',
				data: params,
				success: function(r, textStatus, xmlReq) {
					if(r){
						
						$("#order_viewdetails").html(r);
					}
				},
				error: function(xmlReq, textStatus, errorThrown) {}
			});
		}
	  
		  //return items  for return order	
		   var ReturnAmount_arr ={};
		   var retunIDs= [];
			$(document).on("click","#View_returnitems",function(event) {
				$("#searchorder").hide();	
				$("#returnorder").hide();	
				$(".sitems_gird").css("display", "block");	
				$("#selected_items_div").css("display", "block");	
				$("#View_partialitems").hide();	
				$("#deliveryorderdetails").hide();	
				event.preventDefault();
				var oTable = $('#orderdetailstable').dataTable();
	                        var rowcollection =  oTable.$("#View_returnitemslist:checked", {"page": "all"});
	                        rowcollection.each(function(index,elem){
	                            var checkbox_value = $(elem).val();
	                            //Do something with 'checkbox_value
	                                retunIDs.push(checkbox_value);
	                        });
				if(retunIDs == ''){
					$('#returnitem_Modal').modal('show');
				}		
				else{
					alert("There are some return order items");
					console.log(retunIDs);
				$.ajax({
					type: "post",
					//url:"index.php?ref=<?php  echo $btn_encode_url['pos/getreturnorderitemsamount']; ?>",
					url:'${pageContext.request.contextPath}/Orders/getreturnorderitemsamount',
					//data: {returnIDs:returnIDs.toString()},
					data: {returnIDs:JSON.stringify(retunIDs)},
					async: true,
					beforeSend: function(data) {},
					success: function(r, textStatus, xmlReq) {
						if(r){
							//alert(r);
							var result_returnsplit=r.split("||||");
							var ReturnAmount=result_returnsplit[0];
							var ReturnAmount_autoids = result_returnsplit[1];
							document.getElementById("returnamount").innerHTML = 'Return Amount = Rs ' +round(ReturnAmount)+' /-';
							ReturnAmount_arr = {
	                                                    'ReturnAmount':round(ReturnAmount,2),
	                                                    'ReturnAmount_autoids':ReturnAmount_autoids,
							};
						    }					
				        },
					error: function(xmlReq, textStatus, errorThrown) {}
				});
				
			}
			});
		
	/*======================================================*/
	 /*============search order==============*/
		    $(document).on("click","button.btn_searchorder",function(e) {
			//$("#view_search").show();
			$("#searchorder").show();
                        $("#view_billpark").hide();
			$("#view_help").hide();
			$("#leftmain_table").show();
			$("#returnorder").hide();	
			$("#deliveryorderdetails").hide();	
			$(".right_bottom_div").hide();	
			$("#orderprintdiv1").css("display", "none");	
			$("#main_table").css("display", "none"); 
		    $.ajax({
			   type: "post",
			   url:"${pageContext.request.contextPath}/Orders/get_view_search",
			   success: function(data) {
				   $("#search_content").html(data);  			
				   }
			});
		});
		/*=======================================*/
		$(document).on("click","button.btn_searchorder",function(e) {
            $("#view_billpark").hide();
            $("#view_help").hide();
			$("#leftmain_table").show();    
			$("#searchorder").show();	
			$("#returnorder").hide();	
			$("#deliveryorderdetails").hide();
			$("#view_deliveryorderbuttons").hide();
			$(".right_bottom_div").hide();	
			$("#orderprintdiv1").css("display", "none");	
			$("#main_table").css("display", "none"); 
		    });
			$(document).on("click","#btn_vieworder",function(e) {
				$("#view_estimateorderdetails").hide();	
	            $("#view_billpark").hide();	
				$("#leftmain_table").css("display", "none");	
				$("#view_preorderdetails").hide();
	            $("#view_search").hide();
			    $("#view_deliveryorderbuttons").hide();
				$("#view_orderdetails").css("display", "block");
				$('#final_items').html('');
				var order_idvalue = $(this).attr("data-id");
				
				var isSearchOrderView = "yes"; //by siva
				
				var params_data = {
					'Billno':order_idvalue
				}
				
				alert("check the console for params_data");
				console.log(order_idvalue);
				$.fn.genrate_vieworder(order_idvalue,isSearchOrderView);
			});
			
			$.fn.genrate_vieworder = function(order_idvalue,isSearchOrderView){
				alert("IN generate_view Orders function");
				var printableObjects=[];
				$.ajax({
				   // url: "index.php?ref=<?php echo $btn_encode_url['pos/generateReciptView']; ?>",
					url:'${pageContext.request.contextPath}/Orders/generateReciptView',
				    type:'post',
					//data: {params:params},    //by siva
					//data:{params:order_idvalue},
					data:{params:order_idvalue, isSearchOrderView: isSearchOrderView},
					success: function(r, textStatus, xmlReq) {
						if(r){
							$("#order_viewdetails").html(r);
						}
					},
					error: function(xmlReq, textStatus, errorThrown) {}
				}); 
			}	
		
	/*======================================================*/

	/*============close button code ========================*/
	$(document).on("click","button.billparkBtnclose",function(e) {	
		$("#billpark").css("display", "none");	
	});
	$(document).on("click","button.dayoutBtnclose",function(e) {	
		$("#dayoutdiv1").css("display", "none");	
	});	
	$(document).on("click","button.searchorderBtnclose",function(e) {	
		$("#searchorder").css("display", "none");	
		$("#main_table").css("display", "block");	
	});		
	/*======================================================*/
	//***********************Cake Order/pre order*********************/
	$(document).ready(function(){
		$("input[name='btob_mode']").click(function() {
                    
		var payment_mode_value = $("input[name='btob_mode']:checked").val();
		if(payment_mode_value =='card'){
			$("#cardtypes_btob").css("display", "block");	
			$("#ewallet_btob").css("display", "none");	
		}else if(payment_mode_value =='ewallet')
		{
			$("#ewallet_btob").css("display", "block");	
			$("#cardtypes_btob").css("display", "none");
		}
			else{
			$("#cardtypes_btob").css("display", "none");
				$("#ewallet_btob").css("display", "none");	
		}
		});
	});
	
	//******************************PRe ORder Payment************************//
	$(document).ready(function(){ 
		$("input[name$='payment_mode']").click(function() {
			var payment_mode_value = $("input[name='payment_mode']:checked").val();
			if(payment_mode_value =='card'){
				$("#cardtypes").show();	
				$("#ewallet_preorder").hide();
			}
			else if(payment_mode_value =='ewallet')
		{
			$("#ewallet_preorder").css("display", "block");	
			$("#cardtypes").css("display", "none");
		}
			else{
				$("#cardtypes").hide();	
				$("#ewallet_preorder").hide();
			}
		});
	});
	$('[id^="preorderpaytm"]').on({
		'click': function(){
			var cardtypeewallet_mode = $("input[name='ewallet_val_preorder']:checked").val();
			var url_text='';
			if(cardtypeewallet_mode == 'Paytm'){
				$("#preorder_type_paytm").attr("src","${pageContext.request.contextPath}/resources/images/latestimages/paytm-1.jpg");
			}
		}
	});
	//***********************Cake Order/pre order end*********************/
	//**********************preorder card click action******************************//
	$('[id^="card_type"]').on({'click': function(){
			//alert('card_type');
			var cardtype_mode = $("input[name='card_type_val']:checked").val();
			var url_text='';
			if(cardtype_mode == 'Visa'){
				$("#card_type_visa").attr("src","${pageContext.request.contextPath}/resources/images/latestimages/visa.png");
				$("#card_type_master").attr("src","${pageContext.request.contextPath}/resources/images/mastercard2.png");
				$("#card_type_maestro").attr("src","${pageContext.request.contextPath}/resources/images/maestrocard1.png");
				$("#cake_type_america").attr("src","${pageContext.request.contextPath}/resources/images/3.png");
				$("#cake_type_discover").attr("src","${pageContext.request.contextPath}/resources/images/4.png");
			}else if(cardtype_mode == 'Master'){
				$("#card_type_master").attr("src","${pageContext.request.contextPath}/resources/images/latestimages/mastercard1.png");
				$("#card_type_visa").attr("src","${pageContext.request.contextPath}/resources/images/VISA2.jpg");
				$("#card_type_maestro").attr("src","${pageContext.request.contextPath}/resources/images/maestrocard1.png");
				$("#cake_type_america").attr("src","${pageContext.request.contextPath}/resources/images/3.png");
				$("#cake_type_discover").attr("src","${pageContext.request.contextPath}/resources/images/4.png");
			}else if(cardtype_mode == 'Mastero'){
				$("#card_type_master").attr("src","${pageContext.request.contextPath}/resources/images/mastercard2.png");
				$("#card_type_visa").attr("src","${pageContext.request.contextPath}/resources/images/VISA2.jpg");
				$("#card_type_maestro").attr("src","${pageContext.request.contextPath}/resources/images/latestimages/maestro2.png");
				$("#cake_type_america").attr("src","${pageContext.request.contextPath}/resources/images/3.png");
				$("#cake_type_discover").attr("src","${pageContext.request.contextPath}/resources/images/4.png");
			}
			else if(cardtype_mode == 'AmericanExpress'){
				$("#card_type_master").attr("src","${pageContext.request.contextPath}/resources/images/mastercard2.png");
				$("#card_type_visa").attr("src","${pageContext.request.contextPath}/resources/images/VISA2.jpg");
				$("#card_type_maestro").attr("src","${pageContext.request.contextPath}/resources/images/maestrocard1.png");
				$("#cake_type_america").attr("src","${pageContext.request.contextPath}/resources/images/latestimages/americanexpress1.png");
				$("#cake_type_discover").attr("src","${pageContext.request.contextPath}/resources/images/4.png");
			}
			else if(cardtype_mode == 'Discover'){
				$("#card_type_master").attr("src","${pageContext.request.contextPath}/resources/images/mastercard2.png");
				$("#card_type_visa").attr("src","${pageContext.request.contextPath}/resources/images/VISA2.jpg");
				$("#card_type_maestro").attr("src","${pageContext.request.contextPath}/resources/images/maestrocard1.png");
				$("#cake_type_america").attr("src","${pageContext.request.contextPath}/resources/images/3.png");
				$("#cake_type_discover").attr("src","${pageContext.request.contextPath}/resources/images/latestimages/dsicouver1.png");
			}
		}
	});
	//**********************card click action end******************************//
	
 //***************************** B To B before payment****************************/
	$(document).on("click","button#btob",function(e) {
		e.preventDefault();
		var itemslist=$('#final_items tr').length;
		if(itemslist <= 0){
			$('#preorder_Modal').modal('show');
		}else{
			$("#view_deliveryorderbuttons").hide(); 	
			$("#leftmain_table").hide(); 
			$("#view_preorderdetails").hide(); 
			$("#view_estimateorderdetails").hide(); 
			$("#view_orderdetails").hide();
			$("#btob_orderdetails").show();
		}
	});

	//*********************b to b order card click action******************************//
	$('[id^="btobcake"]').on({'click': function(){
		var cardtype_mode = $("input[name='card_type_val_btob']:checked").val();
		var url_text='';
		//alert(cardtype_mode);
		if(cardtype_mode == 'Visa'){
			$("#cake_type_cakevisa").attr("src","${pageContext.request.contextPath}/resources/images/latestimages/visa.png");
			$("#cake_type_cakemaster").attr("src","${pageContext.request.contextPath}/resources/images/mastercard2.png");
			$("#cake_type_cakemaestro").attr("src","${pageContext.request.contextPath}/resources/images/maestrocard1.png");
			$("#cake_type_cakeamerica").attr("src","${pageContext.request.contextPath}/resources/images/3.png");
			$("#cake_type_cakediscover").attr("src","${pageContext.request.contextPath}/resources/images/4.png");
		}else if(cardtype_mode == 'Master'){
			$("#cake_type_cakemaster").attr("src","${pageContext.request.contextPath}/resources/images/latestimages/mastercard1.png");
			$("#cake_type_cakevisa").attr("src","${pageContext.request.contextPath}/resources/images/VISA2.jpg");
			$("#cake_type_cakemaestro").attr("src","${pageContext.request.contextPath}/resources/images/maestrocard1.png");
			$("#cake_type_cakeamerica").attr("src","${pageContext.request.contextPath}/resources/images/3.png");
			$("#cake_type_cakediscover").attr("src","${pageContext.request.contextPath}/resources/images/4.png");
		}else if(cardtype_mode == 'Mastero'){
			$("#cake_type_cakemaster").attr("src","${pageContext.request.contextPath}/resources/images/mastercard2.png");
			$("#cake_type_cakevisa").attr("src","${pageContext.request.contextPath}/resources/images/VISA2.jpg");
			$("#cake_type_cakemaestro").attr("src","${pageContext.request.contextPath}/resources/images/latestimages/maestro2.png");
			$("#cake_type_cakeamerica").attr("src","${pageContext.request.contextPath}/resources/images/3.png");
			$("#cake_type_cakediscover").attr("src","${pageContext.request.contextPath}/resources/images/4.png");
		}
		else if(cardtype_mode == 'AmericanExpress'){
			$("#cake_type_cakemaster").attr("src","${pageContext.request.contextPath}/resources/images/mastercard2.png");
			$("#cake_type_cakevisa").attr("src","${pageContext.request.contextPath}/resources/images/VISA2.jpg");
			$("#cake_type_cakemaestro").attr("src","${pageContext.request.contextPath}/resources/images/maestrocard1.png");
			$("#cake_type_cakeamerica").attr("src","${pageContext.request.contextPath}/resources/images/latestimages/americanexpress1.png");
			$("#cake_type_cakediscover").attr("src","${pageContext.request.contextPath}/resources/images/4.png");
		}
		else if(cardtype_mode == 'Discover'){
			$("#cake_type_cakemaster").attr("src","${pageContext.request.contextPath}/resources/images/mastercard2.png");
			$("#cake_type_cakevisa").attr("src","${pageContext.request.contextPath}/resources/images/VISA2.jpg");
			$("#cake_type_cakemaestro").attr("src","${pageContext.request.contextPath}/resources/images/maestrocard1.png");
			$("#cake_type_cakeamerica").attr("src","${pageContext.request.contextPath}/resources/images/3.png");
			$("#cake_type_cakediscover").attr("src","${pageContext.request.contextPath}/resources/images/latestimages/dsicouver1.png");
		}
	}
});

	$('[id^="btobpaytm"]').on({
		'click': function(){
			var cardtypeewallet_mode = $("input[name='ewallet_val_btob']:checked").val();
			var url_text='';
			if(cardtypeewallet_mode == 'Paytm'){
				$("#btob_type_paytm").attr("src","${pageContext.request.contextPath}/resources/images/latestimages/paytm-1.jpg");
			}
		}
	});
	//*********************b to b order card click action end******************************//
	/*************************************** Bto B functionality ************************************/
    $(document).on("click","button#btob_submit",function(e) {
		e.preventDefault();
		var general_uuid="<%=general_uuid%>";
 	    var phoneno = /^\d{10}$/;
	    var gstno = /^([0-9]{2}[a-zA-Z]{4}([a-zA-Z]{1}|[0-9]{1})[0-9]{4}[a-zA-Z]{1}([a-zA-Z]|[0-9]){3}){0,15}$/;
		var btobcustomer_name = $("#cust_name").val();
		var gsttinnumber = $("#gsttin").val();
	            //alert(gsttinnumber);
		var deliverybtobdatetime = $("#btob_input2").val();
		var btobpayment_mode = $("input[name='btob_mode']:checked").val();
		if(btobpayment_mode == 'card'){
			var btobcardtype_mode = $("input[name='card_type_val_btob']:checked").val();
		}
	    if(btobpayment_mode == 'ewallet'){
			var btobcardtype_mode = $("input[name='ewallet_val_btob']:checked").val();
		}
		var btobphone_num = $("#btobphone").val();
		var btobadvance = $("#btob_advance").val();
		var btob_address = $("#btob_address").val();
		
		if(gsttinnumber  == '' || btobcustomer_name =='' || btobphone_num =='' || deliverybtobdatetime == ''){
			$('#msg_btob').html('Please fill all the required fields');
		}
	   else if((gsttin.value.match(gstno)) && (btobphone.value.match(phoneno))){
			//alert("equal");
			var btoborderItemsadvance =  $("#btob_advance").val();
			if(btoborderItemsadvance == '')
				var selectedItemsadvance=0;
			else
				var selectedItemsadvance =  btoborderItemsadvance;
			var params_data = {
				'selectedItemArray':selectedItemArray,
				'payment_mode_val':btobpayment_mode,
				'selectedItemstot':selectedItemstot,
				'selectedItemsadvance':selectedItemsadvance,
				'overall_bill_disc':overall_bill_disc,
				'bill_type':'Partial Payment',
				'customer_name':btobcustomer_name,
				'gsttinnumber':gsttinnumber,
				'phone_num':btobphone_num,
				'cust_address':btob_address,
				'cardtype_mode':btobcardtype_mode,
				'deliverydatetime':deliverybtobdatetime,
	            'trans_type':general_uuid
	         }
			var params_data_json = JSON.stringify(params_data);
			//alert(params_data_json);			
			if(billcheck==null  && parseFloat(selectedItemstot['qty_val']) > 0){
			billcheck=$.ajax({
				url:'${pageContext.request.contextPath}/Orders/generateBtoBOrderBill',
				type:'post',
				dataType:'text',
				data: {data_array:params_data_json},
				beforeSend: function(data) {},
				success: function(r, xmlReq) {
					if(r){
						//alert(r);
						 var today = new Date();
                         var dd = today.getDate();
                             
                         var mm = today.getMonth()+1; 
                         var yyyy = today.getFullYear();
                         var hours = today.getHours();
                         var min = today.getMinutes();
                         if(dd<10) 
                         {
                             dd='0'+dd;
                         } 

                         if(mm<10) 
                         {
                             mm='0'+mm;
                         } 
                         todayggg = dd+'/'+mm+'/'+yyyy;
                         $("#printbtob_data").html(r);
                         var print_last= $("#printbtob_data").html();
                         var gsttin_number= $("#gsttin").val();
                         var billnumber_forreceipt= $("#billnumber_forreceipt").val();
                         var filename=gsttin_number+'_'+todayggg+'_'+billnumber_forreceipt+'_'+'AdvanceBtoBReceipt.doc';
                         var text=print_last;
                                 var element = document.createElement('a');
                                 element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
                                 element.setAttribute('download', filename);

                                 element.style.display = 'none';
                                 document.body.appendChild(element);

                                 element.click();

                                 document.body.removeChild(element);
                               window.location.reload(true);
                               
                               
					/*var inpaidamt=	r.in_paidamt;
					var inbillamt=	r.in_billamt;
					var pdata = {
								'Billno': r.Billno
							};
						
						if(inbillamt == inpaidamt)
						{
	       					   //alert('here');
	                          $.fn.genrate_btobtotalreceipt(pdata);
						}
						else
						{
							//alert('now');
	                         $.fn.genrate_btobrreceipt(pdata);
						}
						$("button.clear_all_data").trigger('click');*/
					}
				},
				error: function(xmlReq, textStatus, errorThrown) {}
			});
		}
	}
	        else
	            {
	            	$('#msg_btob').html('(*)Please enter valid details');
	            }
});
	//**********************************b to b end**********************//
	
	/************Swiggy(15%) and Zomoto(12%) increment the price Integration***********/
	
        var countorder = 0;
        $('[id^="ordertypes"]').on({ 'click': function(){
           if(countorder==0){
               var itemslist=$('#final_items tr').length;
               if(itemslist <= 0){
                  $('#cashbill_Modal').modal('show');
               }
               else{
                  countorder= countorder+1;
                  var order_idvalue = $(this).attr("data-id");
                  var order_name = $(this).attr("data-name");
                  var result_split=order_idvalue.split("|||");
                  var res_autoid=result_split[0];
                  var res_orderperc=result_split[1];
                  $.each(selectedItemArray, function( index, value ) {
						var priceadd_onitem;						
                        var priceaddingonitem=parseInt(res_orderperc)+100;
						priceadd_onitem =value['price']*(priceaddingonitem/100);
						selectedItemArray[index]['price']=priceadd_onitem;
                        $.fn.bindselecteditems_grid();
                   });
                   $('[id^="ordertypes"]').prop('disabled', true);
                   $('[id^="ordertypes"]').css("cursor","not-allowed");
                   $('[id^="ordertypes"]').bind('click', false);
                   order_arr = {
                        'trans_type':res_autoid,
                        'perc_type':res_orderperc,
                        'order_name':order_name,
                   };
                   console.log(order_arr);
            	}
             }
             else{
                 var ordertypemsg="Already Selected "+ order_arr['order_name']+" Order Type";
                 $("#ordertype_msg").text(ordertypemsg);
                 $('#cashbill_Modal').modal('show');
                 
             }
          }
    });
	/***************Zomato and swiggy end*********************************/
	/***************Search the items*********************************/
	$(".search_input").focus(function(){
		$(document).on("click",".keypad-key",function(e) {
		var search_input=	$(".search_input").val();
			var parms_obj = {
				'searchlist':1,
				'search_value':search_input
				}
			$.fn.getItemsData(parms_obj);
		});
		
		$(document).on("click",".keypad-back",function(e) {
		var search_input=	$(".search_input").val();
			
			var parms_obj = {
				'searchlist':1,
				'search_value':search_input
				}
			$.fn.getItemsData(parms_obj);
		});
		
	 });
		
	/*======================================*/
	
	/*===================search order submit button====================*/
	
			$(document).on("click","button.searchorder_submitBtn",function(e) {
			var orderid = $("#orderid").val();
			//alert(orderid);
                        var n = orderid.includes("P-");
                        //alert(n);
			if(orderid == ''){
				$("div#errormessage").html("Please Enter Bill Number");
				$("#orderid").focus();
			}else{
                            //alert('elseee');
                            var params_data = {
					'Billno':orderid
                                    }
                                $("div#errormessage").html("");
				$("#searchorder").css("display", "block");	
				$("#leftmain_table").css("display", "none");	
				$("#view_orderdetails").css("display", "block");
                               // $.fn.genrate_searchvieworder(params_data);
				$.fn.genrate_searchvieworder(orderid);
			}
                               
			
		});
	
	   
		$.fn.genrate_searchvieworder = function(orderid){
				var printableObjects=[];
				$.ajax({
					//url: "index.php?ref=<?php echo $btn_encode_url['pos/generateReciptViewSearch']; ?>",
					url:'${pageContext.request.contextPath}/Orders/generateReciptViewSearch',
					type:'post',
					data: {params:orderid},
					success: function(r, textStatus, xmlReq) {
						if(r){
							$("#order_viewdetails").html(r);
						}
					},
					error: function(xmlReq, textStatus, errorThrown) {}
				});
			}
		
		//---------------- Print Cloase Order ---------------//
		$(document).on("click",".print_closeBtn ",function() {
			$("#leftmain_table").css("display", "block");	
			$("#view_orderdetails").css("display", "none");
		});
		
		
		
		$(document).on("click","#btn_deliveryvieworder",function(e) {
			$("#view_estimateorderdetails").hide();	
			$("#view_deliveryorderbuttons").hide();	
			$("#leftmain_table").css("display", "none");	
			$("#view_preorderdetails").hide();
			$("#view_orderdetails").css("display", "block");
			$('#final_items').html('');
			var order_idvalue = $(this).attr("data-id");
			var params_data = {
				'Billno':order_idvalue
			}
			$.fn.genrate_deliveryvieworder(order_idvalue);
		});
		$.fn.genrate_deliveryvieworder = function(order_idvalue){
			var printableObjects=[];
			$.ajax({
				//url: "index.php?ref=<?php echo $btn_encode_url['pos/generateDeliveryReciptView']; ?>",
				url:'${pageContext.request.contextPath}/Orders/generateDeliveryReciptView',
				type:'post',
				//data: params,
				data: {params:order_idvalue},
				success: function(r, textStatus, xmlReq) {
					if(r){
						$("#order_viewdetails").html(r);
					}
				},
				error: function(xmlReq, textStatus, errorThrown) {}
			});
		}
		
		/*=======search order table pagination========*/
        $(document).ready( function () {        
            $("#orderlist_table").DataTable({
                "pageLength": 5,
                "bLengthChange": false,
                "bInfo": false,
                 searching: false,
                "order": false
                            /*"PaginationType": "four_button",
                            "PageButtonStaticDisabled" : "paginate_button_disabled"*/
            });
        });
		
      //**************Refresh Button****************/
      $(document).on("click","#refresh_button", function() {
    		window.location.reload(true);
       });
      /***************voucher code******************/
      $(document).on("click","#voucher",function(event) {
		var items_id;
		var discountamount;
		var discount_percentageoramt;
		var discountamount_onitem;
		var disountotalbill=0;
		var voucher_discount;
		var vouchertype;
		var search_input=	$(".search_input").val();
		var itemslist=$('#final_items tr').length;
		if(itemslist <= 0){
			$('#cashbill_Modal').modal('show');
		}
		else{
		if(search_input == ''){
			$("#voucher_Modal").modal('show');
	    }
		else{
			var params = {
                'voucher_code':search_input,
				'selectedItemstot':selectedItemstot,
				'selectedItemArray':selectedItemArray
			}
			console.log(params);
			var params_data_json = JSON.stringify(params);
			$.ajax({
					type: "post",
					url:"${pageContext.request.contextPath}/Orders/getVoucherDetails",
					data: {data_array:params_data_json},
					beforeSend: function(data) {},
					success: function(r, textStatus, xmlReq) {
						if(r){
							var res =jQuery.parseJSON(r);
							voucherid= res['uuid'];
                            vouchertype= res['voucher_type'];
							if(res['error']){
								$("#voucher_error").html(res['error']);
								$("#voucher_Modal").modal('show');
							}
							else{
								items_id = res['item_ids'];
								discountamount=res['amount'];
								discount_percentageoramt=res['perc_or_amt'];
								if(items_id == 0){
										if(discount_percentageoramt == 'perc'){
											$.each(selectedItemArray, function( index, value ) {
													var items_arr= items_id.split(',');
													discountamount_onitem =value['price']*(discountamount/100);
													selectedItemArray[index]['discount']['percentage']=discountamount;
													selectedItemArray[index]['discount']['amount']=discountamount_onitem;
													disountotalbill+=discountamount_onitem;
													alert(disountotalbill);
													$.fn.bindselecteditems_grid();
											 });
										}
										else{
											 $.each(selectedItemArray, function( index, value ) {
													var items_arr= items_id.split(',');
													selectedItemArray[index]['discount']['amount']=discountamount;
													disountotalbill+=discountamount;
													$.fn.bindselecteditems_grid();
											  });
									 }
									voucher_discount=parseInt(disountotalbill);
								}
								else{
										if(discount_percentageoramt == 'perc'){
											 $.each(selectedItemArray, function( index, value ) {
													var items_arr= items_id.split(',');
													if(checkValue(value['item_id'], items_arr) =='Exist'){
														discountamount_onitem =value['price']*(discountamount/100);
														selectedItemArray[index]['discount']['percentage']=discountamount;
														selectedItemArray[index]['discount']['amount']=discountamount_onitem;
														disountotalbill+=discountamount_onitem;
													}
													$.fn.bindselecteditems_grid();
												});
											}
										else{
											$.each(selectedItemArray, function( index, value ) {
												var items_arr= items_id.split(',');
												if(checkValue(value['item_id'], items_arr) =='Exist'){
													selectedItemArray[index]['discount']['amount']=discountamount;
												}
													disountotalbill+=discountamount;
													$.fn.bindselecteditems_grid();
											});
									    }
										voucher_discount=disountotalbill;
								   }
							}
							voucher_arr = {'voucherid':voucherid,'voucher_discount':voucher_discount,'vouchertype':vouchertype};
						}
					}
				});
		    }
		}
	 });

		/****************estimate order***************/
		$(document).on("click","button#estimate_order",function(e) {
			var itemslist=$('#final_items tr').length;
			if(itemslist <= 0){
				$('#preorder_Modal').modal('show');
			}else{
				var params_data = {
					'selectedItemArray':selectedItemArray,
					'payment_mode_ary':payment_mode_ary,
					'selectedItemstot':selectedItemstot,
					'overall_bill_disc':overall_bill_disc,
					'bill_type':'Estimate Type'
				}
				var params_data_json = JSON.stringify(params_data);
				if(billcheck==null  && parseFloat(selectedItemstot['qty_val']) > 0){
					//alert("estimate order");
				var printableObjects = [];
				$.ajax({
					//url: "index.php?ref=<?php echo $btn_encode_url['pos/generateestimateBill']; ?>",
					url:'${pageContext.request.contextPath}/Orders/generateestimateBill',
					type:'post',
					dataType:'text',
					data: {data_array:params_data_json},
					beforeSend: function(data) {},
					success: function(r, textStatus, xmlReq) {
						if(r){
							if(r=='Failed'){
								$('#order_Modal').modal('show');
		                        $("#order_id").click(function(){
		                        });
							}else{

								if(r=="Linux@Success"){
									window.location.reload(true);
								}else if(r=="Linux@Failed"){
									$('#printer_settings_modal').modal('show');
			                        $("#printer_settings_id").click(function(){
			                        	window.location.reload(true);
			                        });
								}
								else{
									window.location.reload(true);
									$("#print_data").html(r);
									var print_last= $("#print_data").html();
									$("button.clear_all_data").trigger('click');
									printableObjects.push(print_last);  // pushing response to array.
									printableObjects.forEach(function(d){
										printContent(d);
									});
								}
							}

							/* var pdata = {
								'Billno': r.Billno
							};
							$.fn.genrate_estimatereceipt(pdata);
							$("button.clear_all_data").trigger('click'); */
						}
					},
					error: function(xmlReq, textStatus, errorThrown) {}
				});
			}
		}
		});
		/*********************************************/
		
		/**********duplicate bill********************/
		$(document).on("click","#printimg_submitBtn",function(e) {
			var order_idvalue = $(this).attr("data-id");
			alert('result is'+order_idvalue);
			var params_data = {
				'Billno':order_idvalue
			}
			$.fn.genrate_duplicatereceipt(params_data);
		});
		
		$.fn.genrate_duplicatereceipt = function(params){
			var printableObjects=[];
			var params_data_json = JSON.stringify(params);
			alert("paramsssss"+params_data_json);
			$.ajax({
				url:"${pageContext.request.contextPath}/Orders/generateDuplicateRecipt",
				type:'post',
				data: params,
				success: function(r, textStatus, xmlReq) {
					if(r){

						if(r=="Linux@Success"){
							window.location.reload(true);
						}else if(r=="Linux@Failed"){
							$('#printer_settings_modal').modal('show');
	                        $("#printer_settings_id").click(function(){
	                        	window.location.reload(true);
	                        });
						}
						else{
							window.location.reload(true);
	                        printableObjects.push(r);  // pushing response to array.
							printableObjects.forEach(function(d){
								printContent(d);
							});
                      }
					}
				},
				error: function(xmlReq, textStatus, errorThrown) {}
			});
		}
		
	
		
		/*----------------view partialbill items-------------*/
   		$(document).on("click","#View_partialitems",function() {
	$('#cashbill_button').prop('disabled', true);
                $("#cashbill_button").css("background-color","#ccc");
	$('#tokenbill_button').prop('disabled', true);
                $("#tokenbill_button").css("background-color","#ccc");
	var bill_no = $(this).data('billno');
	$("#searchorder").hide();	
	$(".sitems_gird").css("display", "block");	
	$("#selected_items_div").css("display", "block");	
	$("#View_partialitems").hide();	
	$("#view_deliveryorderbuttons").hide();	
	$("#deliveryorderdetails").hide();	
	var params_data = {
		'billno':bill_no
	}
                
	$.ajax({
		type: "post",
		//url:"index.php?ref=<?php  echo $btn_encode_url['pos/getpartialorderitems']; ?>",
		url:"${pageContext.request.contextPath}/Orders/getPartialOrderItems",
	    //data: params_data,
		data:{Billno:bill_no},
	    async: true,
		beforeSend: function(data) {},
		success: function(r, textStatus, xmlReq) {
			if(r){
				//var selectedItembillpark =  jQuery.parseJSON(r);
				var selectedItembillpark =  r;
				console.log(selectedItembillpark);
				selectedItemArray ={};
				$.each(selectedItembillpark, function( index, value ) {
					var quantity = value['quantity'];
					var price_bg = value['price_bg'];
					//var portion_id = value['PortionID'];
					var portion_id = value['portion_uuid'];
					var item_name = value['item_name'];
					var Billno = value['Billno'];
					var unit_val = value['portion_name'];
					var discount_val = value['discount_perc'];
					//var item_id = value['ItemID'];
					var item_id = value['item_uuid'];
					var gst = value['GST_perc'];
					var size = value['size'];
					var ogprice = value['price']/quantity;
					var sgst;	
					var tqun =0;
					var action_val = '';
					if(typeof selectedItemArray =='undefined' || selectedItemArray ==null) selectedItemArray = {};
					if(typeof selectedItemArray[portion_id]!=='undefined' ){
						var tqun= parseInt(selectedItemArray[portion_id]['qty']);
						selectedItemArray[portion_id]['qty'] =tqun + parseInt(1);
						action_val ='add';
					}else {
						var cgst = sgst = gst/2;
						var dis_amt = round(((value['price_bd'] * 1)*discount_val/100),2);
						selectedItemArray[portion_id] = {
							'unit':unit_val,
							'original_price': ogprice,
							'price': ogprice,
							'name': item_name,
							'billno': value['Billno'],
							'qty':quantity,
							'item_id':item_id,
							'size':size,
							'original_discount':{
								'percentage': discount_val,
								'amount': dis_amt
							},
							'discount': {
								'percentage':discount_val,
								'amount': dis_amt
							},
							'gst':{
								'all':{
									'percentage':gst,
									'amount':'',
								},
								'cgst':{
									'percentage':cgst,
									'amount':'',
								},
								'sgst':{
									'percentage':sgst,
									'amount':'',
								}
							},
							'total':value['price_bd']
						}
						selectedItemsGstArray[portion_id] ={
							'portion_id':portion_id,
							'item_id':item_id,
							'tprice': round((value['price']- dis_amt),2),
							'gst':gst
						}
						
					}
					var params = {
						'portion_id':portion_id,
						'action':action_val,
						'Billno':Billno
					}
					
					$.fn.bindpartiallyselecteditems_grid(params);
					$('.button5').removeClass('selected_unit');
					$(this).addClass('selected_unit');
				});
			} 
	},
		error: function(xmlReq, textStatus, errorThrown) {}
	});
});

/*---------------------------------------------------*/


/*----------preorder full payment-----------------*/
      		 $.fn.calc_partialorderTotalPrice =function (){ 
	var qty_val = 0; 
	var tot_price = 0;
	var billnumber = 0;
	var tot_gst_price = 0;
	var tot_sgst_price =0 ;
	var tot_cgst_price = 0;
	var balanceamount;
	var tot_before_gst = 0;
	var discount_value=0;
	var adit_dis_amt =0;
	var tot_priceafterdiscount =0;
	var tot_pricevalue=0;
	$.each(selectedItemArray, function( index, value ) {
		qty_val +=parseInt(value['qty']);
		var qty_price = value['qty']*value['price'];
		var Billno = value['billno'];
		if(value['discount']['percentage']>0)
			var dis_amt = (qty_price*value['discount']['percentage'])/100;
		else {
			var dis_amt = value['discount']['amount'];
			var dis_per = ((dis_amt/qty_price)*100);
			selectedItemArray[index]['discount']['percentage'] = round(dis_per,2);
		}
		selectedItemArray[index]['discount']['amount']= round(dis_amt,2);
		var item_tot_price =  qty_price-dis_amt;
		tot_before_gst +=item_tot_price;
		selectedItemsGstArray[index]['tprice'] = item_tot_price;
		if(typeof selectedItemArray[index]['additional_discount'] !='undefined' ){
			adit_dis_amt = selectedItemArray[index]['additional_discount']['amount'].toFixed(0);
			item_tot_price =item_tot_price =item_tot_price-adit_dis_amt; 
		}
		// ---------- GST Cal --------
		var gst_amt = (item_tot_price*parseInt(selectedItemArray[index]['gst']['all']['percentage']))/(100+parseInt(selectedItemArray[index]['gst']['all']['percentage']));
		tot_gst_price +=gst_amt;
		var sgst = cgst = gst_amt/2;
		tot_sgst_price +=sgst;
		tot_cgst_price +=cgst;
		selectedItemArray[index]['gst']['all']['amount']=round(gst_amt,2);
		selectedItemArray[index]['gst']['sgst']['amount']=round(sgst,2);
		selectedItemArray[index]['gst']['cgst']['amount']=round(cgst,2);
		tot_price +=item_tot_price;
		selectedItemArray[index]['total']= round(item_tot_price,2);
		billnumber =selectedItemArray[index]['billno'];
		if(adit_dis_amt >0){
			discount_value+=parseInt(adit_dis_amt);
			tot_pricevalue= selectedItemstot['balance_amount']-parseInt(discount_value);
			tot_priceafterdiscount=tot_pricevalue.toFixed(0);
		}
	});
	var billno_data = {
			'billno':billnumber,
		}
		$.ajax({
			//url: "index.php?ref=<?php echo $btn_encode_url['pos/getbalanceamountof_preorder']; ?>",
		    url:'${pageContext.request.contextPath}/Orders/getBalanceAmountOfPreorder',
			type:'post',
			dataType:'json',
			data: billno_data,
			success: function(r, textStatus, xmlReq) {
				if(r){
					//balanceamount= r[0]['balance_amt'];
					balanceamount = r ;
					$("#balance_amt").val(balanceamount);
					//var balance_amount = $("#balance_amt").val();
				}
			},
			async: false
		});
		var balance_amount = $("#balance_amt").val();
		if(tot_priceafterdiscount >0){
			var totalpricevalue=tot_price+discount_value;
			selectedItemstot = {
				'qty_val':round(qty_val,2),
				'tot_price':round(tot_price,2),
				'tot_before_gst':round(totalpricevalue,2),
				'tot_gst_amout':round(tot_gst_price,2),
				'tot_sgst_amout':round(tot_sgst_price,2),
				'tot_cgst_amout':round(tot_cgst_price,2),
				'remaining_amount':round(tot_priceafterdiscount,2),
				'balance_amount':round(tot_priceafterdiscount,2)
			};
			$('.grdtot').text('₹'+selectedItemstot['balance_amount'].toFixed(2)+'/-');
			$("span#cgst_amt_span").html(tot_cgst_price.toFixed(2));
			$("span#sgst_amt_span").html(tot_sgst_price.toFixed(2));
			$("span#disc_befgst_amt").html('₹'+selectedItemstot['tot_before_gst']+'/-');
		}else{
			selectedItemstot = {
				'qty_val':round(qty_val,2),
				'tot_price':round(tot_price,2),
				'tot_before_gst':round(tot_price,2),
				'tot_gst_amout':round(tot_gst_price,2),
				'tot_sgst_amout':round(tot_sgst_price,2),
				'tot_cgst_amout':round(tot_cgst_price,2),
				'remaining_amount':round(balance_amount,2),
				'balance_amount':round(balance_amount,2)
			};
		$.each(selectedItemArray, function( index, value ) {
			selectedItemsGstArray[index]['totalbillprice'] = selectedItemstot['tot_price'];
		});
		$('.grdtot').text('₹'+selectedItemstot['balance_amount'].toFixed(2)+'/-');
		$("span#cgst_amt_span").html(tot_cgst_price.toFixed(2));
		$("span#sgst_amt_span").html(tot_sgst_price.toFixed(2));
		$("span#disc_befgst_amt").html('₹'+selectedItemstot['tot_before_gst']+'/-');
		}
		$.fn.bindpreorderpaymentGrid();
		$('.qalltot').text(qty_val);
		return false;
	}
 
 
 
 $.fn.bindpreorderpaymentGrid = function(){
		var sale_tbl_bdy= '';
		var i =0;
		var pay_amt = 0;
		$.each(payment_mode_ary, function( index, value ) {
				i++;
				if(index =='Card'){
					pay_amt +=parseFloat(value['amount']);
					sale_tbl_bdy +='<tr id="tr_'+i+'" data-rel="'+index+'"><th>'+index+'('+value['card_type']+')'+' <br/><b style="color:lightgray;font-size:9px;"> **** **** **** '+index+'</b></th><td>'+value['amount']+'</td><td> <div id="del_preprice_div_'+i+'" class="del_preorderprice_div" data-rel="'+i+'"> X </div> </td></tr>';
					i++;
				}else {
					pay_amt +=parseFloat(value['amount']);
					sale_tbl_bdy +='<tr id="tr_'+i+'" data-rel="'+index+'"><th>'+index+'</th><td>'+value['amount']+'</td><td> <div id="del_price_div_'+i+'" class="del_preorderprice_div" data-rel="'+i+'"> X </div> </td></tr>';
				}
		});
		selectedItemstot['remaining_amount'] =round((selectedItemstot['balance_amount'] - pay_amt),2);
		$("button.btnlink").addClass('btn_balamt');
		$("button.btnlink").removeClass('btn_balpayment');
		$("button.btnlink").html(btn_amt_img);
		if(selectedItemstot['remaining_amount'] <=0){
			$("button.btnlink").removeClass('btn_balamt');
			$("button.btnlink").addClass('btn_balpayment');
			$("button.btnlink").html('Done');
		}
		if(selectedItemstot['remaining_amount']<0){
			var str_data = '<b> <b style="font-size: 17px;"><span class="pay_grdtot">0</span> </b> Chahhnge </b>';
			$('p#payment_amt_p').html(str_data);
			change_amt = Math.abs(selectedItemstot['remaining_amount']);
			$('.pay_grdtot').text('₹'+change_amt.toFixed(2)+'/-');
		}else {
			var str_data = '<b> <b style="font-size: 17px;"><span class="pay_grdtot">0</span> </b> remaining to pay </b>';
			$('p#payment_amt_p').html(str_data);
			$('.pay_grdtot').text('₹'+selectedItemstot['remaining_amount'].toFixed(2)+'/-');
		}
		$("table#price_tbl").html(sale_tbl_bdy);
	}
     
 
	//***********Click on Pre Order payment Button*****************//
	$(document).on("click","button.btn_balpayment",function(e) {
		e.preventDefault();
		console.log(selectedItemstot);
                    var general_uuid="<%=general_uuid%>";
                    //alert(general_uuid);
                    var params_data = {
			'selectedItemArray':selectedItemArray,
			'payment_mode_ary':payment_mode_ary,
			'selectedItemstot':selectedItemstot,
			'overall_bill_disc':overall_bill_disc,
			'bill_type':'payment',
             'trans_type':general_uuid
                    }
                    if(billcheck==null  && parseFloat(selectedItemstot['qty_val']) > 0){
                    	var printableObjects = [];
			billcheck=$.ajax({
			//url: "index.php?ref=<?php echo $btn_encode_url['pos/generatePreOrderTotalBill']; ?>",
			url:'${pageContext.request.contextPath}/Orders/generatePreOrderTotalBill',
			type:'post',
			dataType:'text',
			data: {data_array:JSON.stringify(params_data)},
			beforeSend: function(data) {},
			success: function(r, textStatus, xmlReq) {
				if(r){
					alert(r);
					if(r=='Failed'){
						$('#order_Modal').modal('show');
                        $("#order_id").click(function(){
                        });
                        
					}else{

						if(r=="Linux@Success"){
							window.location.reload(true);
						}else if(r=="Linux@Failed"){
							$('#printer_settings_modal').modal('show');
	                        $("#printer_settings_id").click(function(){
	                        	window.location.reload(true);
	                        });
						}else if(r.includes("@btobOrder")){
							var today = new Date();
                            var dd = today.getDate();
                                
                            var mm = today.getMonth()+1; 
                            var yyyy = today.getFullYear();
                            var hours = today.getHours();
                            var min = today.getMinutes();
                            if(dd<10) 
                            {
                                dd='0'+dd;
                            } 

                            if(mm<10) 
                            {
                                mm='0'+mm;
                            } 
                            todayggg = dd+'/'+mm+'/'+yyyy;
                           // var todaydate=<?php echo date("j F y"); ?>;
                           //alert(today);
                            //var new_str = today.substring(0, today.indexOf("2018"));;
                            //alert(new_str);
                            $("#printbtob_total_data").html(r);
                            var print_last= $("#printbtob_total_data").html();
                            //alert(print_last);
                            var gsttin_number= $("#gsttin_number").val();
                            var billnumber_forreceipt= $("#billnumber_forreceipt").val();
                            var filename=gsttin_number+'_'+todayggg+'_'+billnumber_forreceipt+'_'+'BtoBReceipt.doc';
                            //var filename=today+'_'+'AdvanceBtoBReceipt.doc';
                            var text=print_last;
                                    var element = document.createElement('a');
                                    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
                                    element.setAttribute('download', filename);

                                    element.style.display = 'none';
                                    document.body.appendChild(element);

                                    element.click();

                                    document.body.removeChild(element);
                                  window.location.reload(true);
                            window.location.reload(true);
						}
						else{
							window.location.reload(true);
							$("#print_data").html(r);
							var print_last= $("#print_data").html();
							$("button.clear_all_data").trigger('click');
							printableObjects.push(print_last);  // pushing response to array.
							printableObjects.forEach(function(d){
								printContent(d);
							});
						}
					}
					
		
                                     /*   if(r.Billno == false){
                                                        $('#order_Modal').modal('show');
                                                        $("#order_id").click(function(){
                                                                            window.location.reload(true);
                                                    });
                                                        
                                        }
                                        else{
                                        var billnum=r.Billno;
                                        var result_split=billnum.split("||||");
                                        var res_mainbillno=result_split[0];
                                        var res_flagdata=result_split[1];
					var pdata = {
						'Billno': res_mainbillno
					};
                                            if(res_flagdata == 'BTOBORDER')
                                            $.fn.genrate_btobtotalreceipt(pdata); 
                                            if(res_flagdata == 'PREORDER')
					$.fn.genrate_preordertotalreceipt(pdata);
					$("button.clear_all_data").trigger('click');
				}*/
                                }
			},
			error: function(xmlReq, textStatus, errorThrown) {}
                            });
                    }
	});
 
 

//---------end of preorder full payment----------*


	/*-------------Credit note click event------------------*/
     	$(document).on("click","#creditnote",function(event) {
	event.preventDefault();
   var creditnoteid_value=$("#orderprint_value").val();
  // alert(creditnoteid_value);
	var print_style=$("#printstyle option:selected").val();
	//alert("print_style"+print_style);alert("creditnoteid_value"+creditnoteid_value);
                var oTable = $('#orderdetailstable').dataTable();
                console.log(oTable);
                var rowcollection =  oTable.$("#View_returnitemslist:checked", {"page": "all"});
                 console.log(rowcollection);
                rowcollection.each(function(index,elem){
                        var checkbox_value = $(elem).val();
                        //Do something with 'checkbox_value'
                        retunIDs.push(checkbox_value);
                });
               // alert("retunIDs"+retunIDs);
               //var returnIDs = JSON.stringify(retunIDs);
              // alert("returnIDs"+returnIDs);
                console.log(retunIDs);
        var params_data = {
			'retunID_value':retunIDs,
            'creditnoteid_value':creditnoteid_value
		}
	$("#searchorder").hide();	
	$("#returnorder").hide();	
	$(".sitems_gird").css("display", "block");	
	$("#selected_items_div").css("display", "block");	
	$("#View_partialitems").hide();	
	$("#deliveryorderdetails").hide();
        if(retunIDs =='')
        {
                $('#creditnotemsgModal').modal('show');
        }	
        else{
      var json_params_data = JSON.stringify(params_data);
            // alert("params_data alert"+json_params_data);
             console.log(json_params_data);
             
      var GSTtinnumber = "<%=Gstinnumber %>";
      var outletaddress = "<%=outletAddress %>";      
             
             
    var todaydate=Date();
    var todaydatelast_value= todaydate.slice(0 , 24);
	$.ajax({
		url:"${pageContext.request.contextPath}/Orders/getCreditNote",
		type: "post",
		//dataType:'json',
        data:{retunID_value:JSON.stringify(retunIDs),creditnoteid_value:creditnoteid_value},
		//data: {data_array:json_params_data},
		beforeSend: function(data) {},
		success: function(r, textStatus, xmlReq) {
			//alert(r);
		        if(r){
		        	
		        	if(r=='Failed'){
						$('#order_Modal').modal('show');
                        $("#order_id").click(function(){
                        });
					}else if(r=='creditnote used'){
                        $('#creditnoteusedmsgModal').modal('show');
                    }else{

						if(r=="Linux@Success"){
							window.location.reload(true);
						}else if(r=="Linux@Failed"){
							$('#printer_settings_modal').modal('show');
	                        $("#printer_settings_id").click(function(){
	                        	window.location.reload(true);
	                        });
						}
						else{
                            //alert(r+'else');
                          window.location.reload(true);
                            var result_split=r.split("||||");
                            var barcode_val=result_split[0];
                            //alert(barcode_val);
                            var creditnote_autoid=barcode_val.substr(2, 7);
                            var taxable_val=result_split[1];
                            
                            /*JsBarcode("#barcode2", barcode_val, {
								  format:"EAN13",
								  displayValue:true,
								  fontSize:18
							});*/
							
                            var barcodediv = $("#barcodediv").html();
                            //alert(barcodediv);
                            if(print_style == '40'){
                            	//alert('print_style');
		
                                var printBlock='<table style="border-left: 2px dashed #333;border-right: 2px dashed #333;width=599px"><tr><td style="width:159px;position:relative;-ms-transform: rotate(90deg);-webkit-transform: rotate(90deg); transform: rotate(90deg);">'+barcodediv+'</td>'+
                                '<td style="width:200px;"><img src="${pageContext.request.contextPath}/resources/images/New_Logo.png" alt="#" width="70" height="70" style="float:right"><br><h2 style="font-size:10px;border: 1px solid black;width:96%;text-align: center;">UTC CODE:'+'<span>'+barcode_val+'</span></h2>'+
                                '<h2 style="font-size:12px;">Date<br><span>'+todaydatelast_value+'</span></h2>'+
	'<h3 style="margin-top: 0px;font-size:12px">Credit Note #<span>'+creditnote_autoid+'<br>'+GSTtinnumber+'</span></h3>'+
                                '<h3 style="margin-top: 0px;font-size:12px">Outlet Address:<span>'+outletaddress+'</span></h3>'+
                                '</td>'+
                                '</tr>'+
	'<tr><td colspan="3">'+taxable_val+'</td></tr><tr><td colspan="3"><h3 style="font-size: 12px;margin-left:10px;margin-top:8px;text-align:justify;"><strong>Terms &amp;conditions:</strong>The promotional discount is only available to individual consumers for products purchased in person at any conference worldwide</h3></td></tr></table>';
		
                            }
                            else{
                            	//alert('print_style');
	var printBlock ='<div class="container" style="max-width:1024px;margin:auto;border: 1px solid #ccc;"><div class="row"><div class="col-md-12"><img src="images/New_Logo.png" alt="#" width="100" height="100" style="float:right"></div></div><table border="0"><tr><td width="40%"><h3  style="margin-top:-90px;">CREDIT NOTE</h3><p>'+outletaddress+'</p></td><td  width="30%"><p style="font-weight:bold;">Date</p><p>'+todaydatelast_value+'</p><p style="font-weight:bold;">Credit Note #</p><p>'+creditnote_autoid+'</p><p style="font-weight:bold;">Reference</p><p>Refund</p></td><td  width="30%"><h3  style="margin-top:-90px;">Outlet Address</h3><p>'+outletaddress+'</p></td></tr></table><div class="row"><div class="col-sm-12"></div>'+taxable_val+'<div class="col-sm-5" align="center" style="margin-top:30px;"><p>'+barcodediv+'</p></div><div><h3 style="font-size: 12px;text-align:justify;"><strong>Terms &amp;conditions:</strong>The promotional discount is only available to individual consumers for products purchased in person at any conference worldwide</h3></div></div></div>'
	
                            }
                            //alert(printBlock);
                            
                            window.location.reload(true);
                            var restorepage = document.body.innerHTML;
                            var printcontent = printBlock;
                            document.body.innerHTML = printcontent;
                            window.print();
                            document.body.innerHTML = restorepage;
                        
						}
					}
		     }
		        	
		        	
        },
		error: function(xmlReq, textStatus, errorThrown) {}
	});
            }

});

/*-------------End of Credit note click event-----------*/
  	//date 	07/03/19
	
    $("button#main_cat_next").data( 'max',<%=max_pages%>); 
    $("button.selected_cat").trigger('click');
		
	
 /*    $(".search_input").focus(function(){
		$(document).on("click",".keypad-key",function(e) {
		
		
		var search_input=	$(".search_input").val();
			
			var parms_obj = {
				'searchlist':1,
				'search_value':search_input
				}
			$.fn.getItemsData(parms_obj);
		});
		
		
		$(document).on("click",".keypad-back",function(e) {
		var search_input=	$(".search_input").val();
			
			var parms_obj = {
				'searchlist':1,
				'search_value':search_input
				}
			$.fn.getItemsData(parms_obj);
		});
		
	 });
 */
</script>

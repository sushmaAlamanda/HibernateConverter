<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="login_header.jsp"%>
<!--This is the model  creation starts for OTP-->
<!--This is the model  creation starts for OTP-->

	<style>
			.outlet{
width : 25% ; 
color:black;
display:none ;
    height: 34px;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
   margin-right : 20px;
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);

    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
	</style>
	
	
<div class="container">
  <!-- Trigger the modal with a button -->
  <!-- Modal -->
  <div class="modal" id="otp_Modal" role="dialog">
    <div class="modal-dialog">
       <!-- Modal content-->
        <div class="modal-content">
           <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">You can verify OTP here</h4>
        </div>
        <div class="modal-body" style="background-color: white">
           <div class="modal-body">
                        <div class="form-group">
                            <label for="dummyText"   class="control-label col-xs-4">Enter OTP</label>
                             <div class="input-group col-xs-7">
                             <input type="text" name="dummyText" id="otp_value" class="form-control vkeypad_layout" />
                            </div>
                         </div>
                <div class="form-group">
					        
                            <a href="#" id="regenerate" style="margin-right:81px;">Click here to Regenerate OTP</a>
                            <button type="button" id="otp-submit" class="btn btn-default" style="margin-left:206px;">submit</button> <label id="error" style="color:red;margin-right:136px;" for=""></label>
                            
                         </div>
			   
            </div>
        </div>
        
        
      </div>
      
    </div>
  </div>
   <input type="hidden" name="otpgeneration_val" id="otpgeneration_val" value="">
   
</div>

<!--This is the model  creation ending for OTP-->

<div class="agileinfo-dot">
	<label id="error-msg" class="error" ></label>
	<div class="container">
		<div class="row">
			<div class="col-sm-3">				
				<div class=" login-w3l">
					<img src="resources/images/New_Logo.png" alt="image">
				</div>
			</div>
			<div class="col-sm-9 section">
				<div class=" profile-agileits bg-color-agile">			
					<div class="login-form">		
						<form class="form-inline" method="post" id="login_form" name="login_form"> 
							<input id="user" type="text" name="user" placeholder="Username" class="vkeypad form-control " value="" >	
							<input id="pass" type="password" name="pass" placeholder="Password" class="vkeypad form-control" value="" >
							<!--<input id="otp" type="password" name="OTP" placeholder="OTP" class="vkeypad form-control " value="">-->
 							<button class="btn btn-primary lang" key="otp" id="login-submit" style="color: #fff;border: 1px solid #7d7d7d;background:transparent !important;padding-right: -5spx;"> 							
								Generate OTP
							</button>
						</form>		
					</div>
				</div>
				 <select name='outlet' class='outlet' id="outlet_selection">
                  <option value="0">Select Outlet</option>
                <c:forEach items="${outletData}" var="entry">
			      <option value="${entry.key}"> ${entry.value}</option>
			     </c:forEach>
                 </select>
				<button  id="central-submit" class="btn btn-primary lang" style="color: #fff;border: 1px solid #7d7d7d;background:transparent !important;padding-right: -5spx;display:none;padding: 8px 15px;"> 							
								submit
				</button>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-md-offset-0 text-center">			
				<div class="row">			
					<div class="col-sm-3">
						<div class="profile-agileits  bg-color-agile" id="sec-1">
							<div class="profile-pic wthree">
								<div class="box">
									<div class="display">
										<input type="text" readonly size="30" id="d" style="width: 90%;margin-top: 12px;border: 1px solid #7d7d7d;background: transparent !important;height: 60px;border-radius:5px;font-size:25px;color:white;">
									</div>
									<div class="keys">
										<p>
											<input type="button" class="button gray" value="mrc" onclick='c("")'>
											<input type="button" class="button gray" value="m-" onclick='c("")'>
											<input type="button" class="button gray" value="m+" onclick='c("")'>
											<input type="button" class="button pink" value="/" onclick='v("/")'>
										</p>
										<p>
											<input type="button" class="button black"value="7" onclick='v("7")'>
											<input type="button" class="button black" value="8" onclick='v("8")'>
											<input type="button" class="button black" value="9" onclick='v("9")'>
											<input type="button" class="button pink" value="*" onclick='v("*")'>
										</p>
										<p>
											<input type="button" class="button black" value="4" onclick='v("4")'>
											<input type="button" class="button black" value="5" onclick='v("5")'>
											<input type="button" class="button black" value="6" onclick='v("6")'>
											<input type="button" class="button pink" value="-" onclick='v("-")'>
										</p>
										<p>
											<input type="button" class="button black" value="1" onclick='v("1")'>
											<input type="button" class="button black" value="2" onclick='v("2")'>
											<input type="button" class="button black" value="3" onclick='v("3")'>
											<input type="button" class="button pink" value="+" onclick='v("+")'>
										</p>
										<p>
											<input type="button" class="button black" value="0" onclick='v("0")'>
											<input type="button" class="button black" value="." onclick='v(".")'>
											<input type="button" class="button black" value="C" onclick='c("")'>
											<input type="button" class="button orange" value="=" onclick='e()'>
										</p>
									</div>
								</div>
							</div>	
							<div class="clear"></div>
						</div>
					</div>
				</div>	
				<div class="row">
					<div class="col-sm-3">
						<div id="sec-2" style="text-align:center;padding:1em 0;margin-top: -15px;">						
							<div class="pos-clock" id="terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_scan_scanTabContent_clock">
								<div class="clock-time" id="terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_scan_scanTabContent_clock_clock">15:45</div>
								<div id="terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_scan_scanTabContent_clock_date" style="font-size:25px;"><?php echo date("Y-m-d");?></div>								
							</div>
							<div style="border-bottom: 1px solid #7d7d7d;width: 100%;margin-top: 90px;"></div> 
							<div class="skills float-rt">
								<div class="grid-sub1">
									<h3>Quick links:</h3>	
									<ul class="nav-list">
										<li><a href="#">1.Raise Ticket</a></li>
										<li><a href="#">2.List of documents</a></li>
										<li><a href="#">3.Public Holidays</a></li>
										<li><a href="#">4.Today Deliveries</a></li>
										<li><a href="#">5.Feedback</a></li>
										<li><a href="#">6.CRM</a></li>
									</ul>
									<div class="clear"></div>
									<div class="clear"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>	
	</div>
	<div class="clear"></div>	
	<div class="container">
		<div class="row">
			<div class="weather">	
				<a class="weatherwidget-io" href="https://forecast7.com/en/17d6983d22/visakhapatnam/" data-label_1="VISHAKHAPATNAM" data-label_2="WEATHER" data-theme="original" data-baseColor="#632070" >VISHAKHAPATNAM WEATHER</a>
				<script>
					!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="https://weatherwidget.io/js/widget.min.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","weatherwidget-io-js");
				</script>
			</div>
		</div>
	</div>
	<div class="row">	
		<div class="col-sm-12">	
			<p class="footer">POWERED BY RMR</p>
			<p class="footer1">VER 0.9.4</p>
		</div>
	</div>
</div>	


<script>
$(document).ready(function(){
 
       $('.vkeypad').keyboard({layout: 'qwerty'});
        $('.vkeypad_layout').keyboard({layout: 'num',customLayout: null});
        $("#central-submit").on('click',function(){
             // $('#otp_Modal').modal('show');
            var user = $("#user").val();
            var password = $("#pass").val();
            var outlet = $('.outlet').find('option:selected').val();
           //alert(outlet);
            if(outlet == '0')
            {                    
                $('#error-msg').html("Please select Outlet");  
            }
            else{
             $.ajax({
                        //url: "index.php?ref=<?php echo $btn_encode_url['login/check_login']; ?>",
                        url: "${pageContext.request.contextPath}/Login/checkUser",
                        type:'post',
                        data: {email : user , password : password},
                        success:function(data){
                            $('#error-msg').hide();
                            //$("#login-submit").hide();
                             $('#otp_Modal').modal('show');
                                     var data_sp= data.split("||||");
                                    $("#otp_value").val(data_sp[0]);
                                    $("#otpgeneration_val").val(data);
                        }
                            });  }  });  });
</script>  




<script type="text/javascript">
	$(document).ready(function() {
		$('.vkeypad').keyboard({layout: 'qwerty'});
		$('.vkeypad_layout').keyboard({layout: 'num',customLayout: null});
		$("#login-submit").on('click',function(){
			var user = $("#user").val();
			var password = $("#pass").val();
		
			//var validator=$("form#login_form").validate({
			//	submitHandler: function(form) {
					  $.ajax({					
							url:'Login/checkUser',
							type : 'post',
							dataType:'text',
							data : {
								email:user,
								password:password,
							   // call_to:"check_login"
							},
							success : function(data) {
								//alert("I am in success function");
								//alert(data);
								if(data=="invalid"){
									$('#error-msg').html("Please enter valid password");  
								} else if(data=='not exist'){
									$('#error-msg').html("Please enter Email & Password");
								} else {
									 $.ajax({
			                              // url: "index.php?ref=<?php echo $btn_encode_url['login/localdata']; ?>",
			                               url:'Login/localData',
			                               type:'post',
			                               success:function(data1){
											  if(data1=="node")
										       {
												  var data_sp= data.split("||||");
										            $("#otpgeneration_val").val(data);
				                                    $("#otp_value").val(data_sp[0]);
											    $('#otp_Modal').modal('show');
									         	
										        }
										       else{
												   $("#user").hide();
												   $("#pass").hide();
												   $("#login-submit").hide();
												   $("#outlet_selection").show();
												   $("#central-submit").show();
												}
								         
								            }  
										 }); 
		                        }
							}
						});	
					  return false;
					  //  }
		//	});
		});
	});
</script>

                     
<script>
      //-------------This is for OTP creation--------------------
$(document).ready(function(){
    $("#otp-submit").click(function(){
        //alert("hi");
        var ovalue=$("#otp_value").val();
        if(ovalue==''){
            //alert("please enter otp");
            $("#error").html("*please enter otp");
        }
        
        else{
            //alert("hi");
            var otpgeneration_val=$("#otpgeneration_val").val();
            //alert(otpgeneration_val);
            var res_genval = otpgeneration_val.split("||||");
            var generatedotpvalue =res_genval[0];
			var OutletID = $('.outlet').find('option:selected').val();
             var UserID = res_genval[2];
		     var user_uuid=res_genval[2];
		    //alert(OutletID);
             if(ovalue == generatedotpvalue){
               
               var params_data = {
                      'UserID':UserID,
                      'OutletID' : OutletID,
                      'user_uuid' : user_uuid
                                 }
                        $.ajax({
                      //url: "index.php?ref=<?php echo $btn_encode_url['login/check_otp']; ?>",
                    	url: "Login/checkOtp",
                        type:'post',
                        data: params_data,
                        success:function(data){
                             
                             if(data == 'valid'){
                            	 //  alert("I am in if condition");
                                 var location = "${pageContext.request.contextPath}"+"/Pos/Index";
                            //   var location = "${pageContext.request.contextPath}"+"/";
                                 
                            	   document.location=location;
                                              }
                            
                                  }
                       }); 
            }else{
               $("#error").html("*Please enter valid otp");
            }
        }
    });
});
</script>  



<script>
    $(document).ready(function(){
    $("#regenerate").click(function(){
    var otpgeneration_val=$("#otpgeneration_val").val();
    var res_genval = otpgeneration_val.split("||||");
    var generatedotpvalue =res_genval[0];
    var UserID = res_genval[2];
    var params_data = {
        'user_uuid':UserID
    }
         $.ajax({
                          // url: "index.php?ref=<?php echo $btn_encode_url['login/regenerate_otp']; ?>",
                            url: "${pageContext.request.contextPath}/Login/regenerateOtp",
                            type:'post',
                            data: params_data,
                            success:function(data){
                                $("#otp_value").val(data);
                               // $("#otpgeneration_val").val(data);
                               // var ovalue=$("#otp_value").val();
                              //  var otpgeneration_val=$("#otpgeneration_val").val();
                              //  if(ovalue==otpgeneration_val){
                                       $.ajax({
                                            url:"${pageContext.request.contextPath}/Login/checkOtp",
                                               type:'post',
                                            data: params_data,
                                            success:function(data){
                                                 if(data != 'valid'){
                                                     var location = "${pageContext.request.contextPath}"+"/Pos/Index";
                                                     document.location=location;
                                                 }
                                               
                                              }
                                       });
                                  
                             //   }
                                
                             }
                               
           });
        
         
    $("#regenerate").hide();
    $("#error").hide();
    $("#otp-submit").show();
    
    });
});     
</script>

<script>
	//-------------------- Main Category Functionality -------------------------
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
		$("div.clock-date").html(x1);
	}
	$( "#hd_2" ).click(function() {
		$( "#hd_2" ).slideUp('fast');
			$( "#hd_1" ).slideDown( "slow", function() {
		});
	});
	$( "#down_array" ).click(function() {
		$( "#hd_1" ).slideUp( "slow",function(){
			$( "#hd_2" ).slideDown( "slow", function() {});
		});
	});
</script>
<%@ include file="login_footer.jsp"%>

<%-- <script type="text/javascript">
	
	/* ----------- Cake order/PreOrder	---------------- */
	
/*	$(document).on("click","button#pre_order",function(e) {
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
                var general_uuid='<?php echo $uuid_general; ?>';
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
                'trans_type':general_uuid
			}
			
			var params_data_json = JSON.stringify(params_data);
			console.log("params_data_json is:"+params_data_json);
			if(billcheck==null  && parseFloat(selectedItemstot['qty_val']) > 0){
			billcheck=$.ajax({
				//url: "index.php?ref=<?php echo $btn_encode_url['pos/generatePreOrderBill']; ?>",
			    url:'${pageContext.request.contextPath}/Orders/generatePreOrderBill',
				type:'post',
				dataType:'json',
				data: {data_array:params_data_json},
				beforeSend: function(data) {},
				success: function(r, xmlReq) {
					if(r){
                                            
						var pdata = {
							'Billno': r.Billno
						};
                                                //alert(pdata['Billno']);
                                                if(pdata['Billno'] == false){
                                                            //alert('if');
                                                            //var dbmsg="This bill is not inserted in database. please try again...";
                                                            //$("#ordertype_msg").text(dbmsg);
                                                            //$('#cashbill_Modal').modal('show');
                                                            $('#order_Modal').modal('show');
                                                            $("#order_id").click(function(){
                                                                        //$(this).data('clicked', true);
                                                                        //if($('#oklogout_item').data('clicked')) {
                                                                                //$('#check_dayoutModal').modal('show');
                                                                                window.location.reload(true);
                                                                        //}
                                                        });

                                                }
                                                else{
						$.fn.genrate_preorderreceipt(pdata);
						
                                            }
                                            $("button.clear_all_data").trigger('click');
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
  
	*/
	
	/*===================starting of delivery orders=====================*/
	
	
	// delivery_orders
	/*$(document).on("click","#deliveryorders",function(e) {
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
	*/
	/*===================================================================*/
	//<script type="text/javascript">
	
	/* ----------- Cake order/PreOrder	---------------- */
	
	/*$(document).on("click","button#pre_order",function(e) {
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
                var general_uuid='<?php echo $uuid_general; ?>';
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
                'trans_type':general_uuid
			}
			
			var params_data_json = JSON.stringify(params_data);
			console.log("params_data_json is:"+params_data_json);
			if(billcheck==null  && parseFloat(selectedItemstot['qty_val']) > 0){
			billcheck=$.ajax({
				//url: "index.php?ref=<?php echo $btn_encode_url['pos/generatePreOrderBill']; ?>",
			    url:'${pageContext.request.contextPath}/Orders/generatePreOrderBill',
				type:'post',
				dataType:'json',
				data: {data_array:params_data_json},
				beforeSend: function(data) {},
				success: function(r, xmlReq) {
					if(r){
                                            
						var pdata = {
							'Billno': r.Billno
						};
                                                //alert(pdata['Billno']);
                                                if(pdata['Billno'] == false){
                                                            //alert('if');
                                                            //var dbmsg="This bill is not inserted in database. please try again...";
                                                            //$("#ordertype_msg").text(dbmsg);
                                                            //$('#cashbill_Modal').modal('show');
                                                            $('#order_Modal').modal('show');
                                                            $("#order_id").click(function(){
                                                                        //$(this).data('clicked', true);
                                                                        //if($('#oklogout_item').data('clicked')) {
                                                                                //$('#check_dayoutModal').modal('show');
                                                                                window.location.reload(true);
                                                                        //}
                                                        });

                                                }
                                                else{
						$.fn.genrate_preorderreceipt(pdata);
						
                                            }
                                            $("button.clear_all_data").trigger('click');
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
	});	*/
	
	
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
					billcheck=$.ajax({
						url: "${pageContext.request.contextPath}/Orders/generateBill",
						type:'post',
						dataType:'json',
						data: {data_array:params_data_json},
						beforeSend: function(data) {},
						success: function(r, textStatus, xmlReq) {
							if(r){
								var pdata = {
								   'Billno': r.Billno
								};
							    if(typeof ReturnAmount_arr['ReturnAmount'] != 'undefined'){				
									var pdata = {
										'Billno': r.Billno,
								         'ReturnAmount':ReturnAmount_arr['ReturnAmount']
							        };
	                                $.fn.genrate_Return_receipt(pdata);
	                            }
								/*else if(typeof token_bill['status'] != 'undefined')
								{
	                                                            alert('token_bill');
									$.fn.genrate_tokenreceipt(pdata);
								}*/
								else{
	                              //alert('cash_bill');
								  //$.fn.genrate_receipt(pdata);
	                             if(pdata['Billno'] == false){
	                             	//alert('if');
	                                //var dbmsg="This bill is not inserted in database. please try again...";
	                                //alert(dbmsg);
	                                //$("#ordertype_msg").text(dbmsg);
	                                $('#order_Modal').modal('show');
	                                $("#order_id").click(function(){
	                                //$(this).data('clicked', true);
	                                //if($('#oklogout_item').data('clicked')) {
	                                //$('#check_dayoutModal').modal('show');
	                                	window.location.reload(true);
	                                //}
	                                });
	                                //window.location.reload(true);
	                              } else{
	                                  //alert('cash_bill');
									  $.fn.genrate_receipt(pdata);
								    }
								  }
								  $("button.clear_all_data").trigger('click');
							    }
						     },
						   error: function(xmlReq, textStatus, errorThrown) {}
					    });
					}
			    //$( ".btn_payment" ).trigger( "click" );
			  }
		});
	});

</script>
	
	
		
</script> --%>
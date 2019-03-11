$(document).ready(function() {
  var displayBox = document.getElementById("display");
  var hasEvaluated = false;
	
  //CHECK IF 0 IS PRESENT. IF IT IS, OVERRIDE IT, ELSE APPEND VALUE TO DISPLAY
	  function clickNumbers(cval) {
		if (displayBox.innerHTML === "0" || (hasEvaluated == true && !isNaN(displayBox.innerHTML))) {
		  displayBox.innerHTML = cval;
		} else {
		  displayBox.innerHTML += cval;
		}
		hasEvaluated = false;
	  }

  //PLUS MINUS
  $("#plus_minus").click(function() {
    if (eval(displayBox.innerHTML) > 0) {
      displayBox.innerHTML = "-" + displayBox.innerHTML;
    } else {
      displayBox.innerHTML = displayBox.innerHTML.replace("-", "");
    }
  });

  //ON CLICK ON NUMBERS
  
	isNumber = function(value) {
		return !isNaN(value);
	}
	
	isOperator = function(value) {
		return value === '/' || value === '*' || value === '+' || value === '-' || value === '%' ;
	};
  
   $('.btnkeyboard').on('click', function(evt) {
    //var buttonPressed = $(this).html();
    var buttonPressed = $(this).attr('rel');
    var buttonPressed_val = $(this).html();
    console.log(buttonPressed);
   var par_ob = {
		'buttonPressed':buttonPressed,
		'buttonPressed_val':buttonPressed_val
	}
    if (buttonPressed === "C") {
		 displayBox.innerHTML = "0";
		$("#display").css("font-size", "30px");
		//$("#display").css("margin-top", "110px");
		//$("button").prop("disabled", false);
	  
    } else if (buttonPressed === "back") {
		var cur_cal_val = displayBox.innerHTML;
		var currentEntry = cur_cal_val.substring(0, cur_cal_val.length-1);
		displayBox.innerHTML = currentEntry;
    }  else if (buttonPressed === "+/-") {
		currentEntry *= -1;
    } else if (buttonPressed === '.') {
		//alert(displayBox.innerHTML.indexOf("."));
		if (displayBox.innerHTML.indexOf(".") === -1 ||
		  (displayBox.innerHTML.indexOf(".") !== -1 && displayBox.innerHTML.indexOf("+") !== -1) ||
		  (displayBox.innerHTML.indexOf(".") !== -1 && displayBox.innerHTML.indexOf("-") !== -1) ||
		  (displayBox.innerHTML.indexOf(".") !== -1 && displayBox.innerHTML.indexOf("Ã—") !== -1) ||
		  (displayBox.innerHTML.indexOf(".") !== -1 && displayBox.innerHTML.indexOf("Ã·") !== -1)) {
		  clickNumbers(".");
		}
    } else if (isNumber(buttonPressed)) {
		//alert(displayBox.innerHTML);
		checkLength(displayBox.innerHTML);
		clickNumbers(buttonPressed_val);
    } else if (isOperator(buttonPressed)) {
		evaluate(par_ob);
		
     // currentEntry = '';
    } else if(buttonPressed === '%') {
      currentEntry = currentEntry / 100;
    } else if (buttonPressed === '=') {
		
		evaluate(par_ob);
    }
  });
  
  //EVAL FUNCTION
	function evaluate(par_ob = null) {
		var cur_mode = $("button.btnselected").attr('rel'); 
		displayBox.innerHTML = displayBox.innerHTML.replace(",", "");
		displayBox.innerHTML = displayBox.innerHTML.replace("Ã—", "*");
		displayBox.innerHTML = displayBox.innerHTML.replace("Ã·", "/");
		if (displayBox.innerHTML.indexOf("/0") !== -1) {
			// $("#display").css("font-size", "70px");
			// $("#display").css("margin-top", "124px");
			//$("button").prop("disabled", false);
			//$(".clear").attr("disabled", false);
			displayBox.innerHTML = "Undefined";
		}
		var cal_value = $.trim(displayBox.innerHTML);
		var params = {
		'operator':par_ob['buttonPressed'],
		'buttonPressed_val':par_ob['buttonPressed_val'],
		'cur_mode':cur_mode
		}
    //alert(cur_mode);
		switch(cur_mode){

			case 'quantity':
				$.fn.generate_item_price(params);
			break;
			case 'price':
			case 'discount':
				//alert(cur_mode);
				var n = cal_value.indexOf("%");
				if(n>0){
					params['dis_perc']=true;
					var valNew=cal_value.split('%');
					var evaluate = eval(valNew[0]);
				}else {
					var evaluate = eval(cal_value);
				}
				//alert(evaluate);
				if (evaluate.toString().indexOf(".") !== -1) {
				//evaluate = evaluate.toFixed(5);
				}
				checkLength(evaluate);
				displayBox.innerHTML = evaluate;
	
				checkLength(evaluate);
				displayBox.innerHTML += par_ob['buttonPressed_val'];
				if(par_ob['buttonPressed'] ==='='){
					hasEvaluated = true;
					$.fn.updatePrice(params);
				}
			break;
			default:
				var n = cal_value.indexOf("%");
				if(n>0){
					params['dis_perc']=true;
					var valNew=cal_value.split('%');
					var evaluate = eval(valNew[0]);
				}else {
					var evaluate = eval(cal_value);
				}
				if (evaluate.toString().indexOf(".") !== -1) {
					//evaluate = evaluate.toFixed(5);
				}
				checkLength(evaluate);
				displayBox.innerHTML = evaluate;

				checkLength(evaluate);
				displayBox.innerHTML += par_ob['buttonPressed_val'];

				if(par_ob['buttonPressed'] ==='='){
					hasEvaluated = true;	
					$.fn.addAmount(params);
				}
				//displayBox.innerHTML += par_ob['buttonPressed_val'];
			break;

		}
	}
	
	$.fn.resetData = function(){
	   selectedItemArray ={};
		 payment_mode_ary = {};
		 selectedItemstot = {};
		 selectedItemsGstArray = {};
		 overall_bill_disc = {};
	};
 
	$.fn.calculate_overall_discount = function(parm_obj){
    console.log('yes here too----------------------');
    console.log(parm_obj);
   // alert('yes here too');
		$.ajax({
				type: "post",
				url:generate_disc_url,
				data: parm_obj,
				async: true,
				beforeSend: function(data) {
				},
				success: function(r, textStatus, xmlReq) {
					if(r){
						var json_data =  jQuery.parseJSON(r);
						console.log(json_data['selectedItemArray']);
           // return false;
					
						selectedItemArray = json_data['selectedItemArray'];
						overall_bill_disc = json_data['over_dis'];
						if(selectedItemArray == null) $.fn.resetData();
						
            
             // console.log(overall_bill_disc);
            	var html_d =  'Additional Discount Amount : ₹'+json_data['over_dis']['amount']+'/- <span id="addt_dis_per">('+json_data['over_dis']['percentage']+'%)</span>';
		      	  //alert(html_d);
		        	$("span#addt_dis_amt").html(html_d);
         			   var p ={};
						$.fn.bindselecteditems_grid(p);
						$.fn.getAdditional_dis_items();
						//var tbl_data = json_data.tbl_data;
						//$('#main_cat_tbl').html(tbl_data);
						//$("button#sub_cat_next").data( "max",json_data.max_pages );
					}
				},
				error: function(xmlReq, textStatus, errorThrown) {
				}
			});
	}
	
	
	
	
	
	$.fn.updatePrice = function(params){
		var cur_amount = parseFloat($("p#display").html());
		//alert(cur_amount);
    var cur_div_stat = $("div#bill_discount_srn_div").css("display");
    //alert(cur_div_stat);
		var portion_id = $("div#edit_item_srn_div input#hdn_item_portion_id").val();
		//alert(portion_id);
		$("p#display").html('');
		//console.log(selectedItemstot);
		//console.log('yessssssssssssss---------hh---');
		if(cur_div_stat =='block'){
			
			var p_obj = {
				'cur_amount':cur_amount,
				'dis_perc':params['dis_perc'],
				'selectedItemArray':selectedItemArray,
				'selectedItemstot':selectedItemstot,
				'selectedItemsGstArray':selectedItemsGstArray,
			}
			if (typeof selectedItemstot['balance_amount'] == 'undefined') {
		//alert('ifff');
		$.fn.calculate_overall_discount(p_obj);
	}
			
		else
			{
				//alert('yes elseeeeeej');
				$.fn.calculate_overall_balance_discount(p_obj);
				
			}
			//$.fn.calculate_overall_discount(p_obj);
		}else {
			if(params['cur_mode'] =='discount'){
				if(params['dis_perc']){
					//var ttp = selectedItemArray[portion_id]['price']* selectedItemArray[portion_id]['qty'];
					//var percval = parseFloat(ttp*cur_amount/100);
				
					selectedItemArray[portion_id]['discount']['percentage'] = cur_amount;
					selectedItemArray[portion_id]['discount']['amount'] = 0;
				}else{
					selectedItemArray[portion_id]['discount']['percentage'] = 0;
					//alert(cur_amount);
					selectedItemArray[portion_id]['discount']['amount'] = cur_amount;
				}
			}else {
				selectedItemArray[portion_id]['price'] = cur_amount;
			}
			var params = {
				'portion_id':portion_id
			}
			if (typeof selectedItemstot['balance_amount'] == 'undefined') {
		//alert('here');
		$.fn.bindselecteditems_grid(params);
	}
			
		else
			{
				//alert('yes');
				$.fn.bindpartiallyselecteditems_grid(params);
				
			}
			//$.fn.bindselecteditems_grid(params);
		}
	}
	$.fn.generate_item_price = function(params){
    
   // console.log(params);
		var operator = params['operator'];
		var cur_amount = parseFloat($("p#display").html());
   // alert(cur_amount);
		//if(isNaN(cur_amount)){
			//cur_amount = 0;
		//}
    
    if(!isNaN(cur_amount) || operator!='='){
      if(isNaN(cur_amount)){
          cur_amount = 0;
      }
      var  portion_id = $("div#edit_item_srn_div input#hdn_item_portion_id").val();
      switch(operator){
        case '+':
            if(cur_amount==0)cur_amount =1;
            var act ='add'; 
        break;
        case '-':
            if(cur_amount==0)cur_amount =1;
            var act ='less';
        break;
        case '=':
          var act ='add';
          selectedItemArray[portion_id]['qty']=0;
        break;
      }

      if(cur_amount>0){
        var params_obj = {
          'portion_id':portion_id,
          'action':act,
          'qty':cur_amount
        }
        $.fn.updateQty(params_obj);
      }
    }
		$("p#display").html('');
	};
  
	
  //CHECK FOR LENGTH & DISABLING BUTTONS
  function checkLength(num) {
    if (num.toString().length > 7 && num.toString().length < 14) {
      $("#display").css("font-size", "15px");
      //$("#display").css("margin-top", "174px");
    } else if (num.toString().length > 16) {
      num = "Infinity";
      //$("button").prop("disabled", true);
     //$(".clear").attr("disabled", false);
    }
  }

  //TRIM IF NECESSARY
  function trimIfNecessary() {
    var length = displayBox.innerHTML.length;
    if (length > 7 && length < 14) {
      $("#display").css("font-size", "35px");
      $("#display").css("margin-top", "174px");
    } else if (length > 14) {
      displayBox.innerHTML = "Infinity";
      $("button").prop("disabled", true);
      $(".clear").attr("disabled", false);
    }
  }

  
  
  //-------------- Call funct -------------------
  
   $("button.btnkeyboard_actions").on('click',function(){
		$("button.btnkeyboard_actions").removeClass('btnselected');
		$(this).addClass('btnselected');
	});
	
	$.fn.getCardpaymentDetails = function (){
		
		
	};
	$(document).on("click","#card_typepopup",function(e) {
		//alert('meeee');
		var cardtype_mode_popup = $("input[name='card_type_popup']:checked").val();
				//alert(cardtype_mode_popup);
		var card_amount =  $("#card_amount").val();
		//alert(card_amount+'helooo');
		var cur_mode ='Card';
		var cur_mode_arry = payment_mode_ary[cur_mode];
		if( typeof cur_mode_arry === 'undefined' ){
			payment_mode_ary[cur_mode] = {};
			//console.log('dd');
		}
		payment_mode_ary[cur_mode] = {
			  'amount': eval(card_amount),
			  'card_type':cardtype_mode_popup,
		  }
		console.log(payment_mode_ary);
		$("button#payment_btn").trigger('click');
		$("span#card_popup").html('');
		if(typeof selectedItemstot['balance_amount'] !=='undefined')
			{
				$.fn.bindpreorderpaymentGrid();
			}
			else
			{
				$.fn.bindpaymentGrid();
			}
		//$.fn.bindpaymentGrid();
	});
	$(document).on('click','#modal_form_submit',function(e){
		var card_amount =  $("form#card_details_form input#card_amount").val();
		var card_tid=  $("form#card_details_form input#card_tid").val();
		var card_invoice_id =  $("form#card_details_form input#card_invoice_id").val();
		var car_ending =  $("form#card_details_form input#car_ending").val();
		
		var cur_mode ='Card';
		var cur_mode_arry = payment_mode_ary[cur_mode];
		if( typeof cur_mode_arry === 'undefined' ){
			payment_mode_ary[cur_mode] = {};
			//console.log('dd');
		}
		//return false;
		if(typeof payment_mode_ary[cur_mode][car_ending]!=='undefined' ){
		   //console.log(payment_mode_ary);
		   if(isNumber(payment_mode_ary[car_ending]['amount'])){
				payment_mode_ary[cur_mode][car_ending] = {
					'amount':eval(payment_mode_ary[cur_mode][car_ending]['amount']+ card_amount),
					'TID':card_tid,
					'CINV':card_invoice_id,
					'CEND':car_ending,
				}
		   }
		}else {

		  payment_mode_ary[cur_mode][car_ending] = {
			  'amount': eval(card_amount),
			  'TID':card_tid,
			'CINV':card_invoice_id,
			'CEND':car_ending,
		  }
		}
		// payment_mode_ary[cur_mode] = eval(cur_amount);
		//console.log(payment_mode_ary);
		$("button#payment_btn").trigger('click');
		$("div#common_model").html('').modal('hide');
		$.fn.bindpaymentGrid();
			
			
	});
	
	$.fn.getCardpayment_dialog = function(params){
		$.ajax({
			type: "post",
			url:dialogs_url,
			data: params,
			async: true,
			beforeSend: function(data) {
			},
			success: function(r, textStatus, xmlReq) {
				if(r){
					//alert('success');
					//alert(r);
					//var json_data =  jQuery.parseJSON(r);
					//$("div#common_model").html(r).modal('show');
					$("span#card_popup").html(r);
					//$("span#card_popup").css("display", "block");
				}
			},
			error: function(xmlReq, textStatus, errorThrown) {
			}
		});
	}

  $.fn.addAmount = function(params){
	  var cur_mode = $("button.btnselected").html();
	  var cur_amount = eval(parseFloat($("p#display").html()));
		//alert(cur_amount);
		
		if(params['dis_perc']){
			var ttp = selectedItemstot['remaining_amount'];
			cur_amount = parseFloat(ttp*cur_amount/100);
		}
		if(params['rem_amt']){
			cur_amount = selectedItemstot['remaining_amount'];
		}
		//console.log(selectedItemstot['remaining_amount']);
		
		//console.log(selectedItemstot['remaining_amount']-cur_amount);
		if(cur_amount>0 && typeof selectedItemstot['remaining_amount'] !=='undefined' ){
			//selectedItemstot['remaining_amount']  = selectedItemstot['remaining_amount'] - cur_amount;
			//console.log(selectedItemstot);	
			//console.log(cur_amount);
			//console.log(payment_mode_ary[cur_mode]['amount']);
			
			if(cur_mode =='Card'){
				var parms_obj = {
					'dialog':'CardPayment',
					'amount':cur_amount
				}
				/*$("span#card_popup").css("display", "block");
				$("button#payment_btn").trigger('click');
		$("div#common_model").html('').modal('hide');
		$.fn.bindpaymentGrid();*/
				$.fn.getCardpayment_dialog(parms_obj);
				$("p#display").html('');
				return false;
			}
			
			var cur_mode_arry = payment_mode_ary[cur_mode];
			//console.log(cur_mode_arry);
			if( typeof payment_mode_ary !== undefined && cur_mode_arry !== undefined ) {
				//console.log(cur_mode_arry+'jjjhhhhhhhhhhhhssssssssss');
			  // console.log(payment_mode_ary);
			   if(isNumber(cur_mode_arry['amount'])){
					payment_mode_ary[cur_mode] = {
						'amount':eval(payment_mode_ary[cur_mode]['amount']+ cur_amount)
					}
			   }
			}else {
				console.log(cur_mode_arry+'jjjhhhhhhhhhhhhyyy');
			   console.log(payment_mode_ary);
			  payment_mode_ary[cur_mode] = {
				  'amount': eval(cur_amount)
			  }
			}
			// payment_mode_ary[cur_mode] = eval(cur_amount);
			//console.log(payment_mode_ary);
			$("button#payment_btn").trigger('click');
			if(typeof selectedItemstot['balance_amount'] !=='undefined')
			{
				$.fn.bindpreorderpaymentGrid();
			}
			else
			{
				$.fn.bindpaymentGrid();
			}
			
		}else {
			var params_obj = {
				'heading':'Warning',
				'text':'No items are selected',
				'icon':'warning',
				'showHideTransition':'fade',
				'position':'top-right'
			}
			$.fn.gettoast(params_obj);
		}
		$("p#display").html('');
  }
  
  
  $.fn.bindpaymentGrid = function(){
		var sale_tbl_bdy= '';
		//var payment_mode_ary = JSON.stringify(payment_mode_ary);
		//console.log(payment_mode_ary);
		
		var i =0;
		var pay_amt = 0;
		//$("button#payment_btn").trigger('click');
		$.each(payment_mode_ary, function( index, value ) {
			i++;
			if(index =='Card'){
				pay_amt +=parseFloat(value['amount']);
					sale_tbl_bdy +='<tr id="tr_'+i+'" data-rel="'+index+'"><th>'+index+'('+value['card_type']+')'+' <br/><b style="color:lightgray;font-size:9px;"> **** **** **** '+index+'</b></th><td>'+value['amount']+'</td><td> <div id="del_price_div_'+i+'" class="del_price_div" data-rel="'+i+'"> X </div> </td></tr>';
					i++;
			}
			else {
				pay_amt +=parseFloat(value['amount']);
				sale_tbl_bdy +='<tr id="tr_'+i+'" data-rel="'+index+'"><th>'+index+'</th><td>'+value['amount']+'</td><td> <div id="del_price_div_'+i+'" class="del_price_div" data-rel="'+i+'"> X </div> </td></tr>';
			}
		});
		//console.log(pay_amt);
		//console.log(selectedItemstot);
	 // console.log(selectedItemstot['tot_price']+'totalprice');
		//console.log(selectedItemstot['remaining_amount']+'totalll');
		//console.log(selectedItemstot['balance_amount']+'totalll');
		selectedItemstot['remaining_amount'] =round((selectedItemstot['tot_price'] - pay_amt),2);
	 // console.log(selectedItemstot['remaining_amount']+'balanceamountvalueeeeeeeeeeeeee');
		$("button.btnlink").addClass('btn_amt');
		$("button.btnlink").removeClass('btn_payment');
		$("button.btnlink").html(btn_amt_img);
		if(selectedItemstot['remaining_amount'] <=0){
			$("button.btnlink").removeClass('btn_amt');
			$("button.btnlink").addClass('btn_payment');
			$("button.btnlink").html('Done');
		}
		//console.log(selectedItemstot);
		if(selectedItemstot['remaining_amount']<0){
			var str_data = '<b> <b style="font-size: 17px;"><span class="pay_grdtot">0</span> </b> Change </b>';
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
  /*--------------- Before Card desgin changed code popup code ------------------------*/
 
/*	$.fn.bindpaymentGrid = function(){
		var sale_tbl_bdy= '';
		//var payment_mode_ary = JSON.stringify(payment_mode_ary);
		//console.log(payment_mode_ary);
		
		var i =0;
		var pay_amt = 0;
		//$("button#payment_btn").trigger('click');
		$.each(payment_mode_ary, function( index, value ) {
			i++;
			
			if(index =='Card'){
				$.each(value, function( index2, value2 ) {
					pay_amt +=parseFloat(value2['amount']);
					sale_tbl_bdy +='<tr id="tr_'+i+'" data-rel="'+index+'"><th>'+index+' <br/><b style="color:lightgray;font-size:9px;"> **** **** **** '+index2+'</b></th><td>'+value2['amount']+'</td><td> <div id="del_price_div_'+i+'" class="del_price_div" data-rel="'+i+'"> X </div> </td></tr>';
					i++;
				});
			}else {
				pay_amt +=parseFloat(value['amount']);
				sale_tbl_bdy +='<tr id="tr_'+i+'" data-rel="'+index+'"><th>'+index+'</th><td>'+value['amount']+'</td><td> <div id="del_price_div_'+i+'" class="del_price_div" data-rel="'+i+'"> X </div> </td></tr>';
			}
		});
		//console.log(pay_amt);
		//console.log(selectedItemstot);
		selectedItemstot['remaining_amount'] =round((selectedItemstot['tot_price'] - pay_amt),2);
		$("button.btnlink").addClass('btn_amt');
		$("button.btnlink").removeClass('btn_payment');
		$("button.btnlink").html(btn_amt_img);
		if(selectedItemstot['remaining_amount'] <=0){
			$("button.btnlink").removeClass('btn_amt');
			$("button.btnlink").addClass('btn_payment');
			$("button.btnlink").html('Done');
		}
		//console.log(selectedItemstot);
		if(selectedItemstot['remaining_amount']<0){
			var str_data = '<b> <b style="font-size: 17px;"><span class="pay_grdtot">0</span> </b> Change </b>';
			$('p#payment_amt_p').html(str_data);
			change_amt = Math.abs(selectedItemstot['remaining_amount']);
			$('.pay_grdtot').text('₹'+change_amt.toFixed(2)+'/-');
		}else {		
			var str_data = '<b> <b style="font-size: 17px;"><span class="pay_grdtot">0</span> </b> remaining to pay </b>';
			$('p#payment_amt_p').html(str_data);
			$('.pay_grdtot').text('₹'+selectedItemstot['remaining_amount'].toFixed(2)+'/-');
		}
		$("table#price_tbl").html(sale_tbl_bdy);
	}*/

   $(document).on("click","div.del_price_div", function() {
	 //  alert('hiii');
		var cur_rowid = $(this).data('rel');
		var cur_row_type = $(this).closest('tr').data('rel');
	   console.log(selectedItemstot['remaining_amount']); 
	   console.log(selectedItemstot['balance_amount']); 
		//selectedItemstot['remaining_amount'] +=payment_mode_ary[cur_row_type]['amount'];
		delete payment_mode_ary[cur_row_type];
		//console.log(payment_mode_ary);
		$.fn.bindpaymentGrid();
	});
	 $(document).on("click","div.del_preprice_div", function() {
		var cur_rowid = $(this).data('rel');
		var cur_row_type = $(this).closest('tr').data('rel');
		//selectedItemstot['remaining_amount'] +=payment_mode_ary[cur_row_type]['amount'];
		delete payment_mode_ary[cur_row_type];
		//console.log(payment_mode_ary);
		$.fn.bindpreorderpaymentGrid();
	});
});

	

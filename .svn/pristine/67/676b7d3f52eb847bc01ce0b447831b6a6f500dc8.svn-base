$(document).ready(function() {
	var displayBox = document.getElementById("display");
	var hasEvaluated = false;
	//CHECK IF 0 IS PRESENT. IF IT IS, OVERRIDE IT, ELSE APPEND VALUE TO DISPLAY
	function clickNumbers(cval) {
		if (displayBox.innerHTML === "0" || (hasEvaluated == true && !isNaN(displayBox.innerHTML))) {
			displayBox.innerHTML = cval;
		}else{
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
	
	
	$.fn.calculate_overall_discount = function(parm_obj){
		alert("in caluculate_overall_discount.js..............");
		var params_data_json = JSON.stringify(parm_obj); 
		console.log("Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		console.log("I am in calculate_overall_discount calculator.js"+params_data_json);
		$.ajax({
			type: "post",
			url:generate_disc_url,
			data: {data_array:params_data_json},
			async: true,
			beforeSend: function(data) {},
			//success: function(r, textStatus, xmlReq) {
			success: function(r, textStatus, xmlReq){ 
			if(r){
					alert("In success function r");
					console.log("This is data r"+r);
					var json_data =  jQuery.parseJSON(r);
					 selectedItemArray =  JSON.parse(json_data['selectedItemArray']);
					 overall_bill_disc =  JSON.parse(json_data['over_dis']);
					
					/*
					selectedItemArray = JSON.stringify(r['selectedItemArray']);
					overall_bill_disc = JSON.stringify(r['over_dis']);*/
					
					console.log("\n\nThis is selectedItemArray and overall_bill_disc\n");
					console.log(selectedItemArray+"\n\n");
					console.log(overall_bill_disc);
					alert("fields is:"+overall_bill_disc['amount']);
					if(selectedItemArray == null) $.fn.resetData();
					var html_d =  'Additional Discount Amount : ₹'+overall_bill_disc['amount']+'/- <span id="addt_dis_per">('+overall_bill_disc['percentage']+'%)</span>';
					
					//var html_d =  'Additional Discount Amount : ₹'+overall_bill_disc.amount+'/- <span id="addt_dis_per">('+overall_bill_disc.percentage+'%)</span>';
					$("span#addt_dis_amt").html(html_d);
					var p ={};
					$.fn.bindselecteditems_grid(p);
					$.fn.getAdditional_dis_items();
				}
			},
			error: function(xmlReq, textStatus, errorThrown) {}
		});
	}
		
	
	
	//ON CLICK ON NUMBERS
	isNumber = function(value) {
		return !isNaN(value);
	}
	isOperator = function(value) {
		return value === '/' || value === '*' || value === '+' || value === '-' || value === '%' ;
	};
	$('.btnkeyboard').on('click', function(evt) {
		var buttonPressed = $(this).attr('rel');
		var buttonPressed_val = $(this).html();
		var par_ob = {
			'buttonPressed':buttonPressed,
			'buttonPressed_val':buttonPressed_val
		}
		if (buttonPressed === "C") {
			displayBox.innerHTML = "0";
			$("#display").css("font-size", "30px");
		} else if (buttonPressed === "back") {
			var cur_cal_val = displayBox.innerHTML;
			var currentEntry = cur_cal_val.substring(0, cur_cal_val.length-1);
			displayBox.innerHTML = currentEntry;
		}  else if (buttonPressed === "+/-") {
			currentEntry *= -1;
		} else if (buttonPressed === '.') {
			if (displayBox.innerHTML.indexOf(".") === -1 ||
				(displayBox.innerHTML.indexOf(".") !== -1 && displayBox.innerHTML.indexOf("+") !== -1) ||
				(displayBox.innerHTML.indexOf(".") !== -1 && displayBox.innerHTML.indexOf("-") !== -1) ||
				(displayBox.innerHTML.indexOf(".") !== -1 && displayBox.innerHTML.indexOf("Ã—") !== -1) ||
				(displayBox.innerHTML.indexOf(".") !== -1 && displayBox.innerHTML.indexOf("Ã·") !== -1)) {
					clickNumbers(".");
			}
		} else if (isNumber(buttonPressed)) {
			checkLength(displayBox.innerHTML);
			clickNumbers(buttonPressed_val);
		} else if (isOperator(buttonPressed)) {
			evaluate(par_ob);
		} else if(buttonPressed === '%') {
			currentEntry = currentEntry / 100;
		} else if (buttonPressed === '=') {
			evaluate(par_ob);
		}
	});
	//EVAL FUNCTION
	function evaluate(par_ob = null) {
		//alert("in evaluate..............");
		var cur_mode = $("button.btnselected").attr('rel'); 
		displayBox.innerHTML = displayBox.innerHTML.replace(",", "");
		displayBox.innerHTML = displayBox.innerHTML.replace("Ã—", "*");
		displayBox.innerHTML = displayBox.innerHTML.replace("Ã·", "/");
		if (displayBox.innerHTML.indexOf("/0") !== -1) {
			displayBox.innerHTML = "Undefined";
		}
		var cal_value = $.trim(displayBox.innerHTML);
		var params = {
			'operator':par_ob['buttonPressed'],
			'buttonPressed_val':par_ob['buttonPressed_val'],
			'cur_mode':cur_mode
		}
		switch(cur_mode){
			case 'quantity':
				$.fn.generate_item_price(params);
				break;
			case 'price':
			case 'discount':
				//alert("in discount case");
				var n = cal_value.indexOf("%");
				if(n>0){
					params['dis_perc']=true;
					var valNew=cal_value.split('%');
					var evaluate = eval(valNew[0]);
				}else {
					var evaluate = eval(cal_value);
				}
				if (evaluate.toString().indexOf(".") !== -1) {}
				checkLength(evaluate);
				displayBox.innerHTML = evaluate;
				checkLength(evaluate);
				displayBox.innerHTML += par_ob['buttonPressed_val'];
				if(par_ob['buttonPressed'] ==='='){
					hasEvaluated = true;
					$.fn.updatePrice(params);
				}
				break;
                         case 'size':
				$.fn.generate_item_size(params);
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
				if (evaluate.toString().indexOf(".") !== -1) {}
				checkLength(evaluate);
				displayBox.innerHTML = evaluate;
				checkLength(evaluate);
				displayBox.innerHTML += par_ob['buttonPressed_val'];
				if(par_ob['buttonPressed'] ==='='){
					hasEvaluated = true;	
					$.fn.addAmount(params);
				}
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
	
	$.fn.updatePrice = function(params){
		alert("in update price");
		var cur_amount = parseFloat($("p#display").html());
		var cur_div_stat = $("div#bill_discount_srn_div").css("display");
		var portion_id = $("div#edit_item_srn_div input#hdn_item_portion_id").val();
		$("p#display").html('');
		if(cur_div_stat =='block'){
			var p_obj = {
				'cur_amount':cur_amount,
				'dis_perc':params['dis_perc'],
				'selectedItemArray':selectedItemArray,
				'selectedItemstot':selectedItemstot,
				'selectedItemsGstArray':selectedItemsGstArray,
			}
			if (typeof selectedItemstot['balance_amount'] == 'undefined') {
				$.fn.calculate_overall_discount(p_obj);
			}else{
				$.fn.calculate_overall_balance_discount(p_obj);
			}
		}else {
			if(params['cur_mode'] =='discount'){
				if(params['dis_perc']){
					selectedItemArray[portion_id]['discount']['percentage'] = cur_amount;
					selectedItemArray[portion_id]['discount']['amount'] = 0;
				}else{
					selectedItemArray[portion_id]['discount']['percentage'] = 0;
					selectedItemArray[portion_id]['discount']['amount'] = cur_amount;
				}
			}else {
				selectedItemArray[portion_id]['price'] = cur_amount;
			}
			var params = {
				'portion_id':portion_id
			}
			if (typeof selectedItemstot['balance_amount'] == 'undefined') {
				$.fn.bindselecteditems_grid(params);
			}else{
				$.fn.bindpartiallyselecteditems_grid(params);
			}
		}
	}
	$.fn.generate_item_price = function(params){
		var operator = params['operator'];
		var cur_amount = parseFloat($("p#display").html());
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
	
	
        $.fn.generate_item_size = function(params){
		var operator = params['operator'];
		var cur_amount = parseFloat($("p#display").html());
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
					selectedItemArray[portion_id]['size']=0;
					break;
			}
			if(cur_amount>0){
				var params_obj = {
					'portion_id':portion_id,
					'action':act,
					'size':cur_amount
				}
				$.fn.updateSize(params_obj);
			}
		}
		$("p#display").html('');
	};
	//CHECK FOR LENGTH & DISABLING BUTTONS
	function checkLength(num) {
		if (num.toString().length > 7 && num.toString().length < 14) {
			$("#display").css("font-size", "15px");
		} else if (num.toString().length > 16) {
			num = "Infinity";
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
	$.fn.getCardpaymentDetails = function (){};
	$(document).on("click","#card_typepopup",function(e) {
		var cardtype_mode_popup = $("input[name='card_type_popup']:checked").val();
		var card_amount =  $("#card_amount").val();
		var cur_mode ='Card';
		var cur_mode_arry = payment_mode_ary[cur_mode];
		if( typeof cur_mode_arry === 'undefined' ){
			payment_mode_ary[cur_mode] = {};
		}
		payment_mode_ary[cur_mode] = {
			'amount': eval(card_amount),
			'card_type':cardtype_mode_popup,
		}
		$("button#payment_btn").trigger('click');
		$("span#card_popup").html('');
		if(typeof selectedItemstot['balance_amount'] !=='undefined')	{
			$.fn.bindpreorderpaymentGrid();
		}else{
			$.fn.bindpaymentGrid();
		}
	});
	$(document).on("click","#ewallet_typepopup",function(e) {
		//alert("hiii");
		var cardtype_mode_popup = $("input[name='wallet_type_popup']:checked").val();
		var ewallet_amount =  $("#ewallet_amount").val();
		var cur_mode ='EWallet';
		var cur_mode_arry = payment_mode_ary[cur_mode];
		if( typeof cur_mode_arry === 'undefined' ){
			payment_mode_ary[cur_mode] = {};
		}
		payment_mode_ary[cur_mode] = {
			'amount': eval(ewallet_amount),
			'card_type':cardtype_mode_popup,
		}
		$("button#payment_btn").trigger('click');
		$("span#card_popup").html('');
		if(typeof selectedItemstot['balance_amount'] !=='undefined')	{
			$.fn.bindpreorderpaymentGrid();
		}else{
			$.fn.bindpaymentGrid();
		}
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
		}
		if(typeof payment_mode_ary[cur_mode][car_ending]!=='undefined' ){
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
			beforeSend: function(data) {},
			success: function(r, textStatus, xmlReq) {
				if(r){
					$("span#card_popup").html(r);
				}
			},
			error: function(xmlReq, textStatus, errorThrown) {}
		});
	}
	$.fn.getewalletpayment_dialog = function(params){
		$.ajax({
			type: "post",
			url:dialogs_url,
			data: params,
			async: true,
			beforeSend: function(data) {},
			success: function(r, textStatus, xmlReq) {
				if(r){
					$("span#card_popup").html(r);
				}
			},
			error: function(xmlReq, textStatus, errorThrown) {}
		});
	}
	$.fn.addAmount = function(params){
		var cur_mode = $("button.btnselected").html();
		var cur_amount = eval(parseFloat($("p#display").html()));
		if(params['dis_perc']){
			var ttp = selectedItemstot['remaining_amount'];
			cur_amount = parseFloat(ttp*cur_amount/100);
		}
		if(params['rem_amt']){
			cur_amount = selectedItemstot['remaining_amount'];
		}
		if(cur_amount>0 && typeof selectedItemstot['remaining_amount'] !=='undefined' ){
			if(cur_mode =='Card'){
				var parms_obj = {
					'dialog':'CardPayment',
					'amount':cur_amount
				}
				$.fn.getCardpayment_dialog(parms_obj);
				$("p#display").html('');
				return false;
			}
			else if(cur_mode =='EWallet'){
				var parms_obj = {
					'dialog':'EwalletPayment',
					'amount':cur_amount
				}
				$.fn.getewalletpayment_dialog(parms_obj);
				$("p#display").html('');
				return false;
			}
			var cur_mode_arry = payment_mode_ary[cur_mode];
			if( typeof payment_mode_ary !== undefined && cur_mode_arry !== undefined ) {
				if(isNumber(cur_mode_arry['amount'])){
					payment_mode_ary[cur_mode] = {
						'amount':eval(payment_mode_ary[cur_mode]['amount']+ cur_amount)
					}
				}
			}else {
				payment_mode_ary[cur_mode] = {
					'amount': eval(cur_amount)
				}
			}
		$("button#payment_btn").trigger('click');
			if(typeof selectedItemstot['balance_amount'] !=='undefined'){
				$.fn.bindpreorderpaymentGrid();
			}else{
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
		var i =0;
		var pay_amt = 0;
		$.each(payment_mode_ary, function( index, value ) {
			i++;
			if(index =='Card'){
				pay_amt +=parseFloat(value['amount']);
				sale_tbl_bdy +='<tr id="tr_'+i+'" data-rel="'+index+'"><th>'+index+'('+value['card_type']+')'+' <br/><b style="color:lightgray;font-size:9px;"> **** **** **** '+index+'</b></th><td>'+value['amount']+'</td><td> <div id="del_price_div_'+i+'" class="del_price_div" data-rel="'+i+'"> X </div> </td></tr>';
				i++;
			}
			else if(index =='EWallet'){
				pay_amt +=parseFloat(value['amount']);
				sale_tbl_bdy +='<tr id="tr_'+i+'" data-rel="'+index+'"><th>'+index+'('+value['card_type']+')'+' <br/><b style="color:lightgray;font-size:9px;"> **** **** **** '+index+'</b></th><td>'+value['amount']+'</td><td> <div id="del_price_div_'+i+'" class="del_price_div" data-rel="'+i+'"> X </div> </td></tr>';
				i++;
			}
			else {
				pay_amt +=parseFloat(value['amount']);
				sale_tbl_bdy +='<tr id="tr_'+i+'" data-rel="'+index+'"><th>'+index+'</th><td>'+value['amount']+'</td><td> <div id="del_price_div_'+i+'" class="del_price_div" data-rel="'+i+'"> X </div> </td></tr>';
			}
		});
		selectedItemstot['remaining_amount'] =round((selectedItemstot['tot_price'] - pay_amt),2);
		$("button.btnlink").addClass('btn_amt');
		$("button.btnlink").removeClass('btn_payment');
		$("button.btnlink").html(btn_amt_img);
		if(selectedItemstot['remaining_amount'] <=0){
			$("button.btnlink").removeClass('btn_amt');
			$("button.btnlink").addClass('btn_payment');
			$("button.btnlink").html('Done');
		}
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
	$(document).on("click","div.del_price_div", function() {
		var cur_rowid = $(this).data('rel');
		var cur_row_type = $(this).closest('tr').data('rel');
		delete payment_mode_ary[cur_row_type];
		$.fn.bindpaymentGrid();
	});
	$(document).on("click","div.del_preprice_div", function() {
		var cur_rowid = $(this).data('rel');
		var cur_row_type = $(this).closest('tr').data('rel');
		delete payment_mode_ary[cur_row_type];
		$.fn.bindpreorderpaymentGrid();
	});
	
	
	
	
	
	
});
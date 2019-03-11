$.fn.calc_TotalPrice =function (){ 
		var qty_val = 0; 
		var tot_price = 0;
		var tot_gst_price = 0;
		var tot_sgst_price =0 ;
		var tot_cgst_price = 0;
		var adit_dis_amt=0;
		var tot_before_gst = 0;
		var discount_value=0;
		//console.log('yes here--------------------');
		console.log(selectedItemstot['balance_amount']+'in calc_TotalPriceeeeeeeeeeee');
	//console.log(selectedItemArray);
	if(typeof selectedItemstot['balance_amount']== 'undefined')
		{
			
			$.each(selectedItemArray, function( index, value ) {
			qty_val +=parseInt(value['qty']);
			var qty_price = value['qty']*value['price'];
			if(value['discount']['percentage']>0)
				var dis_amt = (qty_price*value['discount']['percentage'])/100;
			else {
				var dis_amt = value['discount']['amount'];
				//var dis_per = round(((dis_amt/qty_price)*100),2);
				//alert(dis_amt);
				var dis_per = ((dis_amt/qty_price)*100);
				selectedItemArray[index]['discount']['percentage'] = round(dis_per,2);
			}
			selectedItemArray[index]['discount']['amount']= round(dis_amt,2);
			var item_tot_price =  qty_price-dis_amt;
			tot_before_gst +=item_tot_price;
			//alert(item_tot_price+'kkiiii');
			selectedItemsGstArray[index]['tprice'] = item_tot_price;
			
			
			if(typeof selectedItemArray[index]['additional_discount'] !='undefined' ){
				 adit_dis_amt = selectedItemArray[index]['additional_discount']['amount'];
				//alert(adit_dis_amt);
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
			
			//item_tot_price = item_tot_price+gst_amt;  // commented by bhaskar 25.11.2017
			
			// ---------- GST Cal --------
			 
			tot_price +=item_tot_price;
			
			selectedItemArray[index]['total']= round(item_tot_price,2);
			
   
			//alert(dis_amt);
		});
		
		/*if(typeof selectedItemstot['balance_amount']== 'undefined')
		{
			alert('iffff');
			tot_price= tot_price;
		}
		else{
			alert('else');
			alert(selectedItemstot['balance_amount']);
			alert('meeee');
			alert(adit_dis_amt+'meeee');
			tot_price=selectedItemstot['balance_amount']-adit_dis_amt;
		}*/
			
			
			
			//alert('iffff');
			tot_price= tot_price;
		}
		else{
			//alert('elseeee in bindselecteditems_grid');
		//tot_price= tot_price;
		
		$.each(selectedItemArray, function( index, value ) {
			qty_val +=parseInt(value['qty']);
			var qty_price = value['qty']*value['price'];
			if(value['discount']['percentage']>0)
				var dis_amt = (qty_price*value['discount']['percentage'])/100;
			else {
				var dis_amt = value['discount']['amount'];
				//var dis_per = round(((dis_amt/qty_price)*100),2);
				//alert(dis_amt);
				var dis_per = ((dis_amt/qty_price)*100);
				selectedItemArray[index]['discount']['percentage'] = round(dis_per,2);
			}
			selectedItemArray[index]['discount']['amount']= round(dis_amt,2);
			var item_tot_price =  qty_price-dis_amt;
			tot_before_gst +=item_tot_price;
			//alert(item_tot_price+'kkiiii');
			selectedItemsGstArray[index]['tprice'] = item_tot_price;
			
			
			if(typeof selectedItemArray[index]['additional_discount'] !='undefined' ){
				 adit_dis_amt = selectedItemArray[index]['additional_discount']['amount'];
				//alert(adit_dis_amt);
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
			
			//item_tot_price = item_tot_price+gst_amt;  // commented by bhaskar 25.11.2017
			
			// ---------- GST Cal --------
			 
			tot_price +=item_tot_price;
			
			selectedItemArray[index]['total']= round(item_tot_price,2);
			if(adit_dis_amt >0)
			{
				//alert(adit_dis_amt+'insideeeeeeee');
				discount_value=adit_dis_amt;
			}
   
		});
		//alert(selectedItemstot['balance_amount']);
		tot_pricevalue= selectedItemstot['balance_amount']-discount_value;
			//alert(discount_value);
			//alert(tot_pricevalue+'totalprice');
			tot_price=tot_pricevalue;
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
		//console.log(selectedItemstot);
		//alert(tot_price+'haiii');
		$('.grdtot').text('₹'+selectedItemstot['tot_price'].toFixed(2)+'/-');
		$("span#cgst_amt_span").html(tot_cgst_price.toFixed(2));
		$("span#sgst_amt_span").html(tot_sgst_price.toFixed(2));
		$("span#disc_befgst_amt").html('₹'+selectedItemstot['tot_before_gst']+'/meme-');
		
		$.fn.bindpaymentGrid();
		$('.qalltot').text(qty_val);

		return false;
	}
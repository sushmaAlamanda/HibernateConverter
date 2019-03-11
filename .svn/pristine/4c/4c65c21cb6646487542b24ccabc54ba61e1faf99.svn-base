<script type="text/javascript">


/* //-------------------- Main Category Functionality ------------------------- */
<%-- $("button#main_cat_next").data( "max","<%=mainCategories.get(0).getMax_category()%>" ); --%> 


$.fn.getMaincategoryData = function(parms_obj = {}){  
	var parm_obj = {};
	parm_obj = parms_obj;
	$.ajax({
		type: "post",
		//url:"index.php?ref=<?php echo $btn_encode_url['pos/getMaincategoryData']; ?>",
		url:"${pageContext.request.contextPath}/Pos/getMainCategoryData",
		async:false,
		data: parm_obj,
		success: function(data) {
			console.log(data);
			//if(r){
			  var json_data =  jQuery.parseJSON(data);
			  var tbl_data = json_data.tbl_data;
			$('#main_cat_tbl').html(tbl_data);
			//}
		},
		//error: function(xmlReq, textStatus, errorThrown) {}
	});
};
$("button.main_cat_nav").on('click',function(){
	var cur_nav_val =parseInt($(this).data('value'));
	var cur_nav_id =$(this).attr('id');
	var next_nav_val = 0;
	var prev_nav_val = 0;
	if(cur_nav_id == 'main_cat_next'){
		next_nav_val = cur_nav_val+1;
		prev_nav_val =cur_nav_val;
	}else {
		next_nav_val = cur_nav_val;
		prev_nav_val =cur_nav_val-1;
		cur_nav_val = cur_nav_val-1;
	}
	var max_page =parseInt($(this).data('max'));
	if(cur_nav_val <0 || cur_nav_val >= max_page){
	}else {
		$("button#main_cat_next").data( "value", next_nav_val );
		$("button#main_cat_prev").data( "value", prev_nav_val );
		var selected_maincat_id = $("input#selected_maincat_id").val();
		var parms_obj = {
			'cur_nav_val':cur_nav_val,
			'selected_maincat_id':selected_maincat_id
		}
		console.log(parms_obj);
		$.fn.getMaincategoryData(parms_obj);
	}
});
//-------------------- Main Category Functionality -------------------------

 	//------------ Main Category Click Events -------------
$(document).on("click",".main_category ",function() {
                $("#view_billpark").hide();
	$("#btob_orderdetails").hide(); 
                $("#view_help").hide(); 
                $("#view_search").hide();
	$("#view_deliveryorderbuttons").hide(); 
	$("#view_estimateorderdetails").hide();	
	$("#view_preorderdetails").hide();	
	$("#leftmain_table").css("display", "block");	
	$("#view_preorderdetails").hide();
	$("#view_orderdetails").css("display", "none");
	$('.button1').removeClass('selected_cat');
	var cat_val = $(this).data('id');
	//alert(cat_val);			 
	$("button#sub_cat_next").data( "value", 1 );
	$("button#sub_cat_prev").data( "value",0 );
	$(this).addClass('selected_cat');
	$("input#selected_subcat_id").val('');
	$('.add_unit_list').html('');
	$('.add_items_list').html('');
	$("input#selected_maincat_id").val(cat_val);
	//$.fn.getSubcategoryData();
	 getSubcategoryData();
});
   //alert("before triggering");
  //  $("button.selected_cat").trigger('click');
 
//----------- End of Main Category Click Event ----------


		//------------ Get Sub Category List -----------------
//$.fn.getSubcategoryData = function(parms_obj={}) {
	function getSubcategoryData(parms_obj={}){
	//alert("Hi hello");
	var parm_obj = {};
	parm_obj = parms_obj;
	var Category_uuid = $("input#selected_maincat_id").val();
//	alert("My category_uuid"+Category_uuid);
	parm_obj['category_uuid']= Category_uuid;
	$.ajax({
		type: "post",
		//url:"index.php?ref=<?php echo $btn_encode_url['pos/getSubcategoryData']; ?>",
		url:'${pageContext.request.contextPath}/Pos/getSubCategoryData',
		data: parm_obj,
		async: false,
		//dataType:'json',
		success: function(data) {
			//if(data){
			
				var json_data =  jQuery.parseJSON(data);
				
				var tbl_data = json_data.tbl_data;
				$('#add_sub_categories').html(tbl_data);
			//	alert(json_data.max_pages);
				$("button#sub_cat_next").data( "max",json_data.max_pages );
				
			//}
		},
		//error: function(xmlReq, textStatus, errorThrown) {}
	});
};
//---------- End of Sub Category List -----------------------
//------------ Sub Category Navigation Event ------------------
$("button.sub_cat_nav").on('click',function(){
        //alert("iam nav");
	var cur_nav_val =parseInt($(this).data('value'));
	var cur_nav_id =$(this).attr('id');
	var next_nav_val = 0;
	var prev_nav_val = 0;
	if(cur_nav_id == 'sub_cat_next'){
		next_nav_val = cur_nav_val+1;
		prev_nav_val =cur_nav_val;
	}else {
		next_nav_val = cur_nav_val;
		prev_nav_val =cur_nav_val-1;
		cur_nav_val = cur_nav_val-1;
	}
	var max_page =parseInt($(this).data('max'));
	if(cur_nav_val <0 || cur_nav_val >= max_page){
	}else {
		$("button#sub_cat_next").data( "value", next_nav_val );
		$("button#sub_cat_prev").data( "value", prev_nav_val );
		var selected_subcat_id = $("input#selected_subcat_id").val();
		var parms_obj = {
			'CategoryID':1,
			'cur_nav_val':cur_nav_val,
			'selected_subcat_id':selected_subcat_id
		}
		//$.fn.getSubcategoryData(parms_obj);
		getSubcategoryData(parms_obj);
	}
});
//----------- End of Sub Category Navigation Event -------------



// Categories Button
$(document).on("click",".sub_category", function() {
$(".add_items_list").show();
$(".add_unit_list").show();	
});


//------------ Sub Category Click Event --------------------
$(document).on("click",".sub_category",function() {
var subcat_val = $(this).data('id');
$("input#selected_subcat_id").val(subcat_val);
$('.add_unit_list').html('');
$('.add_items_list').html('');
var item='';
var item_flag='item_disabled';
var m=0;
$("input#selected_item_id").val('');
$('.button3').removeClass('selected_subcat');
$(this).addClass('selected_subcat');
//alert("before getItemsData");
$.fn.getItemsData();
});
//---------- End of Sub Category Click Event-------------	




//---------- Get Items List ----------------------------
var res_obj = {};
var data_array = {}; //by siva
$.fn.getItemsData = function(parms_obj={}){
var tbl_data='';
var parm_obj = {};
parm_obj = parms_obj;
//alert('hi');
//alert(parm_obj['searchlist']);
if(typeof parm_obj['searchlist'] != 'undefined' && parm_obj['searchlist'] == 1)
{
	var search_value=parm_obj['search_value'];
	parm_obj['search_value']= search_value;
	//alert(search_value);
}
else
{
	var sub_category_id = $("button.selected_subcat").data('id');
    parm_obj['sub_category_id']= sub_category_id;
    
}

var pagn_dis = 1; 
var start = 0;
var end = 0;
if(parms_obj['cur_nav_val'] !=undefined){
	pagn_dis  = (parms_obj['cur_nav_val']+1);
	start=parms_obj['cur_nav_val']*15;
	var datalen = res_obj.dataList.length;
	//var datalen = data_arry.length;
	end=(parms_obj['cur_nav_val']+1)*15;
	if(datalen<end){
		end = datalen;
	}
}else{
	//currentRequest = $.ajax({
	 $.ajax({
		type: "post",
		//url:"index.php?ref=<?php echo $btn_encode_url['pos/getItemsData']; ?>",
		url:'${pageContext.request.contextPath}/Pos/getItemsData',
		data: parm_obj,
		async: false,
		beforeSend : function()    {           
		/* 	 if(currentRequest != null) {
				currentRequest.abort();
			}  */
		},
		success: function(data) {
			//if(r){
			 //  console.table(data);
				
				var json_data =  jQuery.parseJSON(data);
                res_obj.length=0; //by siva
				res_obj =  json_data;
                data_array.length=0; //by siva
				data_array = json_data.dataList;
				
				start=0;
				if(json_data.max_pages ==1){
					//end=res_obj.data_arry.data.length;
					
					end = res_obj.count;
					
				}else{
					end=15;
				}
				
			//}
		},
		async : false,
		//error: function(xmlReq, textStatus, errorThrown) {}
	});
}
$("button#items_next").data( "max",res_obj.max_pages );
if(res_obj.max_pages ==1)pagn_dis =0;
$("button#items_pagination_disp").html(pagn_dis+'/'+res_obj.max_pages );
var table='';
var m=0;
var item='';
var item_flag='item_disabled';
var data = data_array;
//console.log("this is for table data:"+data);
var checklist=0; 
 for(var i = start; i < end; i++){
	   
	    console.log(data[i]);
		m++;
		if(m<16) item_flag = 'item_enabled'; else item_flag = 'item_disabled';
		var str = data[i].item_name.replace('(', ' (' );
		var btn_sel_cls = '';
		if((typeof parms_obj['selected_item_id'] !== 'undefined') && (parms_obj['selected_item_id'] ==data[i].uuid)) btn_sel_cls = 'selected_item';
		if ((checklist % 3) == 0){tbl_data += '<tr>'; }
		tbl_data +='<td align="center" width="33%" class="item_div '+item_flag+'"><button class="_button button4 items '+btn_sel_cls+'" data-id="'+data[i].uuid+'" data-unit="'+data[i].unit_uuid+'" data-name="'+str+'" data-disc="'+ data[i].discount + '"data-code="'+data[i].hsn_uuid+'" data-gst="'+ data[i].tax +'" > '+ str +'</button></td>';
		if ((checklist % 3) == 2) {tbl_data += '</tr>';}
		checklist = checklist+1;
	} 
//alert("at the end of item displaying");
$('table.add_items_list').html(tbl_data);
}
//----------- End of Get Items List ------------------------


//---------  Items Navigation event -------------------------
$("button.items_nav").on('click',function(){
var cur_nav_val =parseInt($(this).data('value'));
var cur_nav_id =$(this).attr('id');
var next_nav_val = 0;
var prev_nav_val = 0;
if(cur_nav_id == 'items_next'){
	next_nav_val = cur_nav_val+1;
	prev_nav_val =cur_nav_val;
}else{
	next_nav_val = cur_nav_val;
	prev_nav_val =cur_nav_val-1;
	cur_nav_val = cur_nav_val-1;
}
var max_page =parseInt($("button#items_next").data('max'));
if(cur_nav_val <0 || cur_nav_val >= max_page ){
}else {
	$("button#items_next").data( "value", next_nav_val );
	$("button#items_prev").data( "value", prev_nav_val );
	var selected_item_id = $("input#selected_item_id").val();
	var parms_obj = {
	'sub_category_id':1,
	'cur_nav_val':cur_nav_val,
	'max_page':max_page,
	'selected_item_id':selected_item_id
	}
	$.fn.getItemsData(parms_obj);
}
});

/*
Items autoComplete
*/
$(".search_input").focus(function(){
$(document).on("click",".keypad-key",function(e) {
var search_input=    $(".search_input").val();            
 var parms_obj = {
     'searchlist':1,
     'search_value':search_input
     }
 $.fn.getItemsData(parms_obj);
});

$(document).on("click",".keypad-back",function(e) {
var search_input=    $(".search_input").val();            
 var parms_obj = {
     'searchlist':1,
     'search_value':search_input
     }
 $.fn.getItemsData(parms_obj);
});        
});
/*
Ending Of Autocomplete
*/

//---------- End of Items Navigation event ----------------
//------------ Sub Category Click Event --------------------
/* $(document).on("click",".sub_category",function() {
	var subcat_val = $(this).data('id');
	$("input#selected_subcat_id").val(subcat_val);
	$('.add_unit_list').html('');
	$('.add_items_list').html('');
	var item='';
	var item_flag='item_disabled';
	var m=0;
	$("input#selected_item_id").val('');
	$('.button3').removeClass('selected_subcat');
	$(this).addClass('selected_subcat');
	$.fn.getItemsData();
}); */

//---------- End of Sub Category Click Event-------------
//------------- ITEMS Click Event --------------------
$(document).on("click",".items",function() {
	var unit_val = $(this).data('id');
	var UnitID = $(this).data('unit');
	$("input#selected_item_id").val(unit_val);
	$('.add_unit_list').html('');
	var unit='';
	var itemname = $(this).data('name');
                //var itemsize = $(this).data('size');
                //alert(itemsize);
	$('#item_hidden_val').val(itemname);
	var dcount = $(this).data('disc');
	$('#item_dis_val').val(dcount);
                //$('#item_size').val(itemsize);
	$('.button4').removeClass('selected_item').addClass('_button button4 items');
	$(this).addClass('_button button4 items selected_item');
	var unit_flag='unit_disabled';
//	alert(unit_val + " "+UnitID);
	var m=0;
	var data =[];
	data['id']=unit_val;
	data['UnitID']= UnitID;
	$.ajax({
	//	url: "index.php?ref=<?php echo $btn_encode_url['pos/getUnitsData']; ?>",
			type:"post",
		url:'${pageContext.request.contextPath}/Pos/getUnitsData',
		
		data: {id : unit_val , UnitID : UnitID },
	//	data:data,
		success: function(data) {
			$('.add_unit_list').append(data);
		}
	});
});
//------------- End of ITEMS Click Event --------------------

 
</script>
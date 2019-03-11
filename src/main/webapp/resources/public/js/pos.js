 var tot_categories =0;
      $(document).on("click",".categories_list",function() {
          var cat_val = $(this).data('id');
          showDefaultCategory(cat_val);
          $(this +'.main-cat-tick').hide();          
          $(this +'.selected-tick'+cat_val).show();
      });

        var j=0;

        $(document).on("click",".down-btn-cls",function() {
          for (var k = 0; k < 5; k++) {
            var start = parseInt(1) + parseInt(j); 
            var end = parseInt(6) + parseInt(j);

            if(tot_categories >= end) {    
              $('.sub_catdiv[data-value='+start+']').removeClass('sub_catdiv cat_anabled').addClass('sub_catdiv cat_disabled');
              $('.sub_catdiv[data-value='+end+']').removeClass('sub_catdiv cat_disabled').addClass('sub_catdiv cat_anabled');
              j++;
          }
          else {
              //upimg
               //$('.sub_catdiv[data-value='+start+']').removeClass('sub_catdiv cat_anabled').addClass('sub_catdiv cat_disabled');
              //$('.sub_catdiv[data-value='+end+']').removeClass('sub_catdiv cat_disabled ').addClass('sub_catdiv cat_anabled');
              //j++;
          }

         }

        });


      function showDefaultCategory(id) {
          $('.prev_cls').hide();
          var cat_val = id; 

          $('#add_sub_categories').html('');
          $('.add_items_list').html('');
          $('.add_unit_list').html('');
          var sub='';
          var cat_length=0;
          var cat_flag='cat_disabled';
          var i=0;

          $.ajax({
            url: "index.php?ref=<?php echo $btn_encode_url['pos/getSubcategoryData']; ?>",
            data: {id :cat_val},
            success: function(data) {
              var data_decode = $.parseJSON(data); 
              tot_categories = data_decode.length;

              var icnt=0;

              if (tot_categories > 0) {
                $.each(data_decode, function (key, val) {
                    i++;
                    if(i<6)
                    cat_flag = 'cat_anabled';
                    else 
                    cat_flag='cat_disabled';
                    
                    if(val['iCount']!=null)
                      icnt = val['iCount'];
                    else 
                      icnt = 0;

                    sub +='<tr data-id="'+val['CategoryID']+'" data-value="'+i+'" class="sub_catdiv '+ cat_flag +'"><td><button class="_button button3"><p>'+val['category_name']+'</p><br><span class="item_spn"> '+icnt+' </span></button></td></tr>';


                    //sub +='<div data-id="'+val['CategoryID']+'" data-value="'+i+'" class="sub_catdiv '+ cat_flag +'"><div class=""><div id="noti-count"><div>'+icnt+'</div></div></div><button type="button" class="button sub_cat"><span style="display: none;" class="sub-cat-tick selected-sub-tick'+val['CategoryID']+'">✓</span><br>';
                    //sub +=val['category_name']+'<br><span class="item_spn"> '+icnt+' </span>';

                   
                    //sub +='</button></div><div class="clear_cls" style="clear:both"></div>';
                });


                //console.log(sub);
                $('#add_sub_categories').append(sub);

                
                
              }
              j=0;

              if(tot_categories > 5)
              $('.prev_cls').show(); 


            }
          });
      }

      showDefaultCategory(1);

      //Item list.
       $(document).on("click",".sub_catdiv",function() {
          var item_val = $(this).data('id');
          $('.add_unit_list').html('');
          $('.add_items_list').html('');
          $('.add_unit_list').html('');
          var item='';
          //var cat_length=0;
          var item_flag='item_disabled';
          var m=0;

          $('span.sub-cat-tick').hide();          
          $('span.selected-sub-tick'+item_val).show();

          //console.log(this +' span.sub-cat-tick');

          $.ajax({
            url: "index.php?ref=<?php echo $btn_encode_url['pos/getItemsData']; ?>",
            data: {id : item_val},
            success: function(data) {
              var item_data_decode = $.parseJSON(data); 
              tot_categories = item_data_decode.length;

              //console.log(item_data_decode);

              if (tot_categories > 0) {
                $.each(item_data_decode, function (key, val) {
                    m++;
                    if(m<16)
                    item_flag = 'item_anabled';
                    else 
                    item_flag = 'item_disabled';

                    item +='<div style="float:left;"><div class="item_div '+ item_flag +'"><button data-id="'+val['ItemID']+'" data-value="'+val['UnitID']+'" data-unit="'+val['UnitID']+'"  data-status="'+val['item_name']+'" type="button" class="button items">'+val['item_name']+'</button></div></div>';
                    
                });
                $('.add_items_list').append(item);
                //$('.prev_cls').show(); 
              }
              //var k=0;

            }
          });
      });



     //Units List.
     $(document).on("click",".items",function() {
        var unit_val = $(this).data('id');
        var UnitID = $(this).data('unit');

        $('.add_unit_list').html('');
        var unit='';
        //var cat_length=0;
        var itemname = $(this).data('status');
        $('#item_hidden_val').val(itemname);

        var unit_flag='unit_disabled';
        var m=0;

        $.ajax({
          url: "index.php?ref=<?php echo $btn_encode_url['pos/getUnitsData']; ?>",
          data: {id : unit_val , UnitID : UnitID },
          success: function(data) {
            var unit_data_decode = $.parseJSON(data); 
            tot_categories = unit_data_decode.length;

            if (tot_categories > 0) {
              $.each(unit_data_decode, function (key, val) {
                  m++;
                  if(m<16)
                  unit_flag = 'unit_anabled';
                  else 
                  unit_flag = 'unit_disabled';

                  unit +='<a data-value="'+ val['PortionID'] +'" data-id="'+ val['unit_name'] +','+ val['price'] +','+ val['portion_name'] +'" class="unit-btn-link '+ unit_flag +'" href="javascript:void(0);"><label class="in-units">' +val['portion_name']+ '</label></a>'; 
                                    
              });
              $('.add_unit_list').append(unit);
              //$('.prev_cls').show(); 
            }

          }
        });
    });

	var prev_val = 0;	
	var prev_qun = 0;
    $(document).on("click",".unit-btn-link",function() {
        $('.fitem-table').show();
        var oprice = $(this).data('id');
        var portion_id = $(this).data('value');
        var item_name = $('#item_hidden_val').val();
        var arr = oprice.split(',');
        var fval='';
        
        var same_row=0;
        var item_unique ='';

        $('#final_items tr').each(function() {
          console.log(this.id);
          
          if (portion_id == this.id) {
            same_row++;
            item_unique = this.id;
          }

        });

        if(same_row == 0) {
		 var max_items_count = $('#final_items tr').length;		 
		 if(max_items_count < 10) {

          fval +='<tr id="'+portion_id+'"><td class="selected_item_row">'+item_name+'</td><td class="selected_item_row">1</td><td class="selected_item_row">'+arr[0]+'</td><td class="selected_item_row">'+arr[1]+'</td><td class="subtot selected_item_row">'+arr[1]+'</td><td><a data-value="'+ portion_id +'" data-id="'+ arr[1] +',add" class="item_unit_action" href="javascript:void(0);"><span class="plus plus-sign"><img class="" height="30px;" width="30px;" src="public/images/add-image.jpg"></span></a> <a data-value="'+ portion_id +'" data-id="'+ arr[1] +',less" class="item_unit_action" href="javascript:void(0);"><span class="minus"> <img class="" height="30px;" width="30px;" src="public/images/less_image.jpg"></span></a> <a data-value="'+ portion_id +'" data-id="'+ arr[1] +',delete" class="item_unit_action" href="javascript:void(0);"><span class="delete"><img class="" height="30px;" width="30px;" src="public/images/delete_image.jpg"></span></a></td></tr>';
		  
		   $('#final_items').append(fval);
		
			calc_TotalPrice();
		  
		 }
        }
        else {
    		 prev_val = $('#'+item_unique).find("td:eq(4)").text();
    		 var f_Price = parseInt(prev_val)+parseInt(arr[1]);
    		 prev_qun = $('#'+item_unique).find("td:eq(1)").text();
    		 var f_qun = parseInt(prev_qun)+parseInt(1);
    		 var tex = $('#'+item_unique).find("td:eq(1)").text(f_qun);
    		 var tex1 = $('#'+item_unique).find("td:eq(4)").text(f_Price);
		 
		      calc_TotalPrice();
		 
        }

    });
	
	$(document).on("click",".item_unit_action", function() {
        $('.fitem-table').show();
        var iua_price = $(this).data('id');
        var portion_id = $(this).data('value');
        var item_name = $('#item_hidden_val').val();
        var u_arr = iua_price.split(',');
        var fval='';

        var same_row=0;
        var item_unique ='';
		
		prev_val = $('#'+portion_id).find("td:eq(4)").text();
		prev_qun = $('#'+portion_id).find("td:eq(1)").text();
		
		switch(u_arr[1]) {
			case 'add':
				var f_Price = parseInt(prev_val)+parseInt(u_arr[0]);
				$('#'+portion_id).find("td:eq(4)").text(f_Price);
				var f_qun = parseInt(prev_qun)+parseInt(1);
				var tex = $('#'+portion_id).find("td:eq(1)").text(f_qun);
				break;
			case 'less':
				if(prev_qun != 1) {
					var f_Price = parseInt(prev_val)-parseInt(u_arr[0]);
					$('#'+portion_id).find("td:eq(4)").text(f_Price);
					var f_qun = parseInt(prev_qun)-parseInt(1);
					var tex = $('#'+portion_id).find("td:eq(1)").text(f_qun);
				}
				else {
				}
				break;
			case 'delete':
				$('#'+portion_id).remove();
				break;
			default:
		}
		
		calc_TotalPrice();

    });
	
	
	function calc_TotalPrice(){
		$('.tal_cls').show();
		var grandTotal=0;
		$(".subtot").each(function () {
			var stval = parseFloat($(this).text());
			grandTotal += isNaN(stval) ? 0 : stval;
		});
		$('.totalclsss').remove();
		$('.grdtot').text('₹'+grandTotal.toFixed(2)+'/-');
	}

  
  $(document).on("click",".selected_item_row", function() {

    $('.item_row_cls').hide();
    $('.item_row_show').show();
    $('.own_item_edit').show();
    //own_item_close

    

  });

  $(document).on("click",".own_item_edit", function() {
    $('.item_row_cls').hide();
    $('.item_row_edit').show();
    $('.own_item_close').show();
  });

  $(document).on("click",".own_item_close", function() {
    $('.item_row_cls').hide();    
    $('.item_row_show').show();
    $('.own_item_edit').show();
  });

  

  $(document).on("click",".edit_item_cls", function() {

  });


  
		
	
	
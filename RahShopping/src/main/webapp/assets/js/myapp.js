$(function() {
	// selecting the active menu (menu=title as mentioned in page.jsp) and
	// "title filled in java code while the active class for
	// the correponding ID which defined in many jsps(like navbar)
	//Menu = title (read from controller) AND (#ID read from navbar)
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;

	case 'Contact Us':
		$('#contact').addClass('active');
		break;

	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#ManageProducts').addClass('active');
		break;	

	default:
		if (menu == 'HOME')
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	//console.log('inside the table ! ');

	// code for jQuery dataTable
	// Create a dataset
	/*
	var products = [ [ '1', 'ABC' ], [ '2', 'DSS' ], [ '3', 'DFS' ],
			[ '4', 'YTQ' ], [ '5', 'IHG' ], [ '6', 'GBN' ], [ '7', 'NVC' ],
			[ '8', 'CKK' ], [ '1', 'PWQ' ],

	];
	*/
	
	
	// id got from listProduct.jsp
	var $table = $('#productListTable');
	// execute bellow code only where we have this table, which means when we opens this page and this variable loaded
	if ($table.length) {
		var jsonUrl='';
		if(window.categoryId=='') {
			jsonUrl=window.contextRoot+ '/json/data/all/products';
		}
		else {
			jsonUrl=window.contextRoot+ '/json/data/category/'+ window.categoryId+ '/products';
		}
		
		//console.log('inside the table ! ');
		$table.DataTable ( {
			//how many records per page
			lengthMenu: [[3,5,10,-1],['3 Records', '5 Records', '10 Records','All']],
			//page length
			pageLength: 5,
			//this if you want to insert them manually 
			//data: products
			//else (using json)
			ajax: {
				url:jsonUrl,
				dataSrc:''
			},
			columns: [
					{
						data:'code',
						mRender: function(data,type,row) {
							return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg" />';
						}
						
					},
					{
						//same name in Json
						data:'name'
					},

					{

						data:'brand'
					},

					{
						data:'unitPrice',
						//to Display the row in customized view we used mRender property
						mRender: function (data,type,row) {
							return '&#8364; '+data
						}
						
					},

					{
						data:'quantity',
						mRender: function(data,type,row) {
							if(data<1) {
								return '<span style ="color:red">Out Of stock!</span>';							
							}
							return data;							
						}
					},
					{
						data: 'id',
						//to cancel the sort functionality 
						bSortable:false,
						//to Display the row in customized view we used mRender property
						mRender: function(data,type,row){
							var str='';
							//&#160 =space
							str +='<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
							
							if(row.quantity <1) {
								str +='<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
							}
							else {
								str +='<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';	
							}
							
							
							return str;
							
						}
						
					}
			]
		});

	}
	
	//dismissing the alert after 3 seconds (used in the manageproducts.jsp)
	//here we're dealing with  alert class
	var $alert = $('.alert');
	if($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
			
		},3000)
	}
	
	//--------------
	//here we are using jquery and bootboxjs
	//to read checkbox from manageproducts checkbox because we define a toggle button with class="switch"
	
	//-------
	// Data table for Admin
	//-------
	//table name got from manageproducts.jsp
	var $adminProductstable = $('#adminProductsTable');
	// execute bellow code only where we have this table, which means when we opens this page and this variable loaded
	if ($adminProductstable.length) {
		var jsonUrl=window.contextRoot+'/json/data/admin/all/products';
	
		//console.log('inside the table ! ');
		$adminProductstable.DataTable ( {
			//how many records per page
			lengthMenu: [[10,30,50,-1],['10 Records', '30 Records', '50 Records','All']],
			//page length
			pageLength: 30,
			//this if you want to insert them manually 
			//data: products
			//else (using json)
			ajax: {
				url:jsonUrl,
				dataSrc:''
			},
			columns: [
			         {
			        	 data:'id'
			         }, 
					{
						data:'code',
						mRender: function(data,type,row) {
							return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminDataTableImg" />';
						}
						
					},
					{
						//same name in Json
						data:'name'
					},

					{

						data:'brand'
					},
					{
						data:'quantity',
						mRender: function(data,type,row) {
							if(data<1) {
								return '<span style ="color:red">Out Of stock!</span>';							
							}
							return data;							
						}
					},
					{
						data:'unitPrice',
						//to Display the row in customized view we used mRender property
						mRender: function (data,type,row) {
							return '&#8364; '+data
						}
						
					},
					{
						data: 'active',
						bSortable:false,
						mRender:function(data,type,row){
							var str='';
									str += '<label class="switch">';
									//means if the product is active then add a checked else don't insert checked
									if(data){
										str += '<input type="checkbox" checked="checked" value="'+ row.id + '"/>';
									}
									else {
										str += '<input type="checkbox"  value="'+ row.id + '"/>';
									}
									str += '<div class="slider"></div> </label>';
									return str;	
						}
						
					},
					{
						data:'id',
						bSortable:false,
						mRender:function(data,type,row) {
							var str='';
							//we can't read the context root by ${contextRoot} it's just read in this way in jsp file
							str+='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
				 			str+='<span class="glyphicon glyphcon-pencil"><</span></a>';
				 			return str;
						}
					}
			],
			initComplete:function() {
				var api=this.api();
				api.$('.switch input[type="checkbox"]').on('change',function() {
					
					var checkbox=$(this);
					var checked= checkbox.prop('checked');
					var dMsg=(checked)?'You want to activate that product?':'you want to deactivate that product?';
					var value=checkbox.prop('value');
					
					bootbox.confirm({
						size:'medium',
						title:'Proudct Activation & Deactivation',
						message:dMsg,
						callback:function(confirmed){
							//if user clicked on ok or cancel
							if(confirmed) {
							console.log(value);
							// added to handle activation and deactivation, this method will be handled by managmentcontroller.java with post method
							var activationUrl=window.contextRoot+'/manage/product/'+value+'/activation';
							$.post(activationUrl,function(data){
								bootbox.alert({
									size:'medium',
									title:'Information',
									message:data
								});	
							});
							
							
							}
							else {
								checkbox.prop('checked',!checked);
							}
						}
					})	
				});
			}
		});

	}
	
	//-----------------
	
});

$(function() {
	//selecting the active menu (menu=title as mentioned in page.jsp) and "title filled in java code while the active class for 
	//the correponding ID which defined in many jsps(like navbar)
	switch(menu) {
	case 'About Us':
			$('#about').addClass('active');
			break;
			
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
		
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
		
	default :
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
		break;		
	}
})
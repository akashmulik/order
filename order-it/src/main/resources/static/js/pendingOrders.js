var header;
var statusOptions;

$(function() {
	header = {'Authorization' : 'Bearer '+Cookies.get('food-jwt-token')};
	$('div.container').hide();
	statusOptions = "<select class='float-right' name='oStat'><option value='1'>Placed</option><option value='2'>Confirmed</option><option value='3'>Packed</option><option value='4'>Out for delivery</option><option value='5'>Delivered</option></select>";
	loadPendingOrders();
});

var a;
function loadPendingOrders() {
	$.ajax({
		url : '/seller/getPendingOrders',
		headers: header
	})
	.done(function(data) {
		var count=0;
		var details = new Array(data.length);
		
		for(i=0;i<data.length;i++) {
			
			var or = data[i];
			var listItems = "<ul class='list-group'>";
			for(j=0;j<or.length;j++) {
				var prodDtl = or[j].product.stock.name +": "+or[j].product.quantity+""+ or[j].product.unit.unitName;
				var li = "<li class='list-group-item'>"+ prodDtl +" X "+or[j].qty+"</li>";
				listItems+=li;
			}
			details[i] = listItems+"</ul>";
			count++;
		}
		
		for(i=0;i<data.length;i++) {
			var or = data[i];
			var card = "<div class='card'>"+
					"<div class='card-header'>"+
					"<a class='card-link' data-toggle='collapse' data-target='#"+ or[0].id.mobileNo +"'>"+ or[0].id.mobileNo +"</a>"+
					"&nbsp;&nbsp;"+statusOptions+""+
					"</div>"+
					"<div id='"+ or[0].id.mobileNo +"' class='collapse' data-parent='#accordion'>"+details[i]+
					"</div>"+
					"</div>";
			$("#accordion").append(card);
			//set order status to dropdown
			$("#accordion").find('a[data-target="#'+or[0].id.mobileNo+'"]').next().val(or[0].oStatus.id);
		}
		$('h5 span').text(' ('+count+')');
		$('div.container').show(400);
	}).fail(function(response) {
		if(response.status == 403 || response.status == 401) {
			window.location.href = "/page/loginPage";
		}
	}).always(function() {
	//	alert("complete");
	});
}

function logout() {
	Cookies.remove('food-jwt-token');
	window.location.href = "/page/loginPage";
}


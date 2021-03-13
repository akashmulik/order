var header;
$(document)
.ajaxStart(function () {
	 $("#overlay").fadeIn(200);
})
.ajaxStop(function () {
	  $("#overlay").fadeOut(200);
});

$(function() {
	header = {'Authorization' : 'Bearer '+Cookies.get('food-jwt-token')};
//	$('div.container').hide();
	loadMyOrders();
});

function loadMyOrders() {
	$.ajax({
		url : '/getLiveOrders',
		headers: header
	})
	.done(function(data) {
		
		var item = data.liveOrder;
		
		$("#orderTable tbody tr").remove();
		var orderTotalAmnt = 0.0;
		for(i=0;i<item.length;i++) {
			orderTotalAmnt = orderTotalAmnt + item[i].amount;
			var row = "<tr>"+
				"<td>"+ item[i].product.stock.name +"</td> "+
				"<td>"+ item[i].product.quantity+""+item[i].product.unit.unitName+"x"+item[i].qty+"</td> "+
				"<td>"+ item[i].pricePerUnit +"</td> "+
				"<td>"+ item[i].amount +"</td> "+
				"</tr>";
			$("#orderTable").append(row);
		}
		var summaryRow = "<tr>"+
		"<td><strong>Total :</strong></td> "+
		"<td></td> "+
		"<td><strong>Rs.</strong></td> "+
		"<td><strong>"+ orderTotalAmnt +"</strong></td> "+
		"</tr>";
	$("#orderTable").append(summaryRow);
	console.log(data);
	if(item.lenght === 0) {
	var dateRow = "<tr>"+
	"<td colspan='2'><strong>Ordered on :</strong></td> "+
	"<td colspan='2'><strong>"+ data.orderDate +"</strong></td> "+
	"</tr>";
	var statusRow = "<tr>"+
	"<td colspan='2'><strong>Order status :</strong></td> "+
	"<td colspan='2'><strong>"+ data.orderStatus.desc +"</strong></td> "+
	"</tr>";
	}
$("#orderTable").append(dateRow);
$("#orderTable").append(statusRow);
$('div.container').show(400);
	}).fail(function(response) {
		alert(data);
		if(response.status == 403 || response.status == 401) {
			window.location.href = "/page/loginPage";
		}
	}).always(function() {
	//	alert("complete");
	});
	
}
//alert function
function showAlert(msg, msgType) {
	$.bootstrapGrowl(msg, { type: msgType, delay: 1500});
}

function removeFromCart(pid) {
	//removing from cart
	var fdata = new FormData();
	fdata.append("prodID", pid);
	fdata.append("qty", 1);
	$.ajax({
		url : '/removeFromCart',
		type: "POST",
        enctype: 'multipart/form-data',
        data: fdata,
        processData: false,
        contentType: false,
        cache: false,
        headers: header
	})
	.done(function(data) {
		loadCart();
		showAlert(data, 'success');
	}).fail(function(data) {
		alert("Temporary issue. Please try in some time.");
	}).always(function() {
	});
}

function logout() {
	Cookies.remove('food-jwt-token');
	window.location.href = "/page/loginPage";
}
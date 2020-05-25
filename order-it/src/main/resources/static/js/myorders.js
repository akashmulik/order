var header;

$(function() {
	header = {'Authorization' : 'Bearer '+Cookies.get('food-jwt-token')};
	loadMyOrders();
});

function loadMyOrders() {
	$.ajax({
		url : '/getLiveOrders',
		headers: header
	})
	.done(function(data) {
		$("#orderTable tbody tr").remove();
		var orderTotalAmnt = 0.0;
		for(i=0;i<data.length;i++) {
			orderTotalAmnt = orderTotalAmnt + data[i].amount;
			var row = "<tr>"+
				"<td>"+ data[i].product.stock.name +"</td> "+
				"<td>"+ data[i].product.quantity+""+data[i].product.unit.unitName+"x"+data[i].qty+"</td> "+
				"<td>"+ data[i].pricePerUnit +"</td> "+
				"<td>"+ data[i].amount +"</td> "+
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
	
	var dateRow = "<tr>"+
	"<td colspan='2'><strong>Ordered on :</strong></td> "+
	"<td colspan='2'><strong>"+ data[0].orderPlacedOn +"</strong></td> "+
	"</tr>";
$("#orderTable").append(dateRow);
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
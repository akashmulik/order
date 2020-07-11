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
	$('div.container').hide();
	loadCart();
});

function loadCart() {
	$.ajax({
		url : '/getMyCartItems',
		headers: header
	})
	.done(function(data) {
		$("#cartTable tbody tr").remove();
		var cartTotalAmnt = 0;
		for(i=0;i<data.length;i++) {
			var totalAmnt = data[i].product.pricePerUnit * data[i].qty;
			cartTotalAmnt = cartTotalAmnt+totalAmnt;
			var row = "<tr>"+
				"<td>"+ data[i].product.stock.name +"</td> "+
				"<td>"+ data[i].product.quantity+""+data[i].product.unit.unitName+"x"+data[i].qty+"</td> "+
				"<td>"+ data[i].product.pricePerUnit +"</td> "+
				"<td>"+ totalAmnt +"</td> "+
				"<td><span class='badge badge-pill badge-danger' onclick='removeFromCart("+data[i].product.productId+")'>X</span></td> "+
				"</tr>";
			$("#cartTable").append(row);
		}
		var summaryRow = "<tr>"+
		"<td><strong>Total :</strong></td> "+
		"<td></td> "+
		"<td></td> "+
		"<td><strong>"+ cartTotalAmnt +"</strong></td> "+
		"<td><strong>Rs.</strong></td> "+
		"</tr>";
	$("#cartTable").append(summaryRow);
	$('div.container').show(400);
	}).fail(function(data) {
		alert(data);
		alert("Temporary issue. Please try in some time.");
		if(data.status == 403 || data.status == 401) {
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
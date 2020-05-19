$(function() {
	
	header = {'Content-Type' : 'application/json',
			'Authorization' : 'Bearer '+Cookies.get('food-jwt-token')};
	
	$.ajax({
		url : '/getMyCartItems',
		headers: header
	})
	.done(function(data) {
		for(i=0;i<data.length;i++) {
			var totalAmnt = data[i].product.pricePerUnit * data[i].qty
			var row = "<tr>"+
				"<td>"+ data[i].product.stock.name +"</td> "+
				"<td>"+ data[i].product.quantity+""+data[i].product.unit.unitName+"x"+data[i].qty+"</td> "+
				"<td>"+ data[i].product.pricePerUnit +"</td> "+
				"<td>"+ totalAmnt +"</td> "+
				"<td>"+ 'X' +"</td> "+
				"</tr>";
			$("#cartTable").append(row);
		}
	}).fail(function(data) {
		//alert(data);
	}).always(function() {
	//	alert("complete");
	});
});
var header;

var $loading = $('#loadingDiv').hide();
$(document)
  .ajaxStart(function () {
    $loading.show();
  })
  .ajaxStop(function () {
    $loading.hide();
  });

$(function() {
	// AJAX
	// Assign handlers immediately after making the request,
	// and remember the jqXHR object for this request
	header = {'Authorization' : 'Bearer '+Cookies.get('food-jwt-token')};
	/*{'Content-Type' : 'application/json',
			'Authorization' : 'Bearer '+Cookies.get('food-jwt-token')};*/
	
	var jqxhr = $.ajax({
		url: '/allProducts',
		headers: header
	}).done(function(data) {
		
		for(i=0; i<data.length;i++) {
		// prepare card to display for every product
		var $product = $('<div/>',{
		    'class':'card mb-3 shadow'
		})
		.append(
				$('<img/>', {
					id: '',
					src: "data:image/png;base64,"+data[i].stock.image,
					alt: 'image'
				})
		)
		.append(
				$('<div/>', {
					'class':'card-body'
				})
				.append(
						$('<h5>', {
							'class':'card-title float-right',
							'text': 'Rs.'+data[i].pricePerUnit
						})
				)
				.append(
						$('<h5>', {
							'class':'card-title',
							'text':data[i].stock.name
						})
				)
				.append(
						$('<h5>', {
							'class':'card-title float-right',
							'text':data[i].quantity+""+data[i].unit.unitName
						})
				)
				.append(
						$('<p/>', {
							'class':'card-text',
							'text':data[i].stock.desc
						})
				)
				.append(
						$('<button/>', {
							'class':'btn btn-primary btn-sm add-to-cart shadow',
							'text':"Add To Cart",
							'onclick': 'addToCart(this,'+ data[i].productId +','+ data[i].maxQtyLimit +')' //'+ data[i].productId +'
						})
				)
				.append(
						$('<p/>', {
							'hidden': '',
							'class': 'card-text float-right items-in-cart',
							'style':'color: red;',
							'text': '0 in cart'
						})
				)
				.append(
						$('<input/>', {
							'value': data[i].productId,
							'hidden': 'hidden'
						})
				)
		);
		$('.product-container').append($product);
		}
		getMyCartItems();
	}).fail(function(response) {
		// case of token expired
		if(response.status == 403 || response.status == 401) {
			window.location.href = "/page/loginPage";
		}
	}).always(function() {});
	
});

function getMyCartItems() {
		$.ajax({
			url : '/getMyCartItems',
			headers: header
		})
		.done(function(data) {
			for(i=0;i<data.length;i++) {
				var str = "input[value="+ data[i].id.prodID +"]";
				$("body").find("div.card").find(str).siblings('p.items-in-cart'). removeAttr('hidden');
				$("body").find("div.card").find(str).siblings('p.items-in-cart').text(data[i].qty+' in cart');
			}
		}).fail(function(data) {
			alert(data);
		}).always(function() {
		//	alert("complete");
		});
}

//alert function
function showAlert(msg, msgType) {
	$.bootstrapGrowl(msg, { type: msgType, delay: 1500});
}

function addToCart(val, pid, max) {
	
	var n = $(val).siblings('p.items-in-cart').text().substr(0,2);
	var total = parseInt(n)+1;
	if(total > max)
		return;
	
	//adding to cart
	var fdata = new FormData();
	fdata.append("prodID", pid);
	fdata.append("qty", 1);
	
	$.ajax({
		url : '/addToCart',
		type: "POST",
        enctype: 'multipart/form-data',
        data: fdata,
        processData: false,
        contentType: false,
        cache: false,
        headers: header
	})
	.done(function(data) {
			showAlert(data, 'success');
			$(val).siblings('p.items-in-cart').text(total+' in cart');
			$(val).siblings('p.items-in-cart').removeAttr('hidden');
	}).fail(function(data) {
		alert("Temporary issue. Please try in some time.");
	}).always(function() {
	});
}

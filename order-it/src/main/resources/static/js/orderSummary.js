var header;
var address;
$(document)
.ajaxStart(function () {
	 $("#overlay").fadeIn(200);
})
.ajaxStop(function () {
	  $("#overlay").fadeOut(200);
});

$(function() {
	header = {'Authorization' : 'Bearer '+Cookies.get('food-jwt-token')};
	getCustAddress();
	
	$("#editAddrs").click(function() {
		$("textarea#addrs").removeAttr('readonly').focus();
	});
	$(".payment-card").hide();
});


//alert function
function showAlert(msg, msgType, delayTime) {
	$.bootstrapGrowl(msg, { type: msgType, delay: delayTime});
}

function confirmAddrs() {
	// if user changed addrs, save it else do nothing
	var enteredAddrs = $("textarea#addrs").val();
	if(enteredAddrs == address) {
		$("textarea#addrs").attr('readonly','true');
		showAlert('Address not modified','success',2000);
		$(".hidden").show(400);
		$('#editAddrs').hide();
		return;
	}
	if(enteredAddrs.length < 40) {
		showAlert('Please enter detailed address','danger',2000);
		return;
	}
	
	var fdata = new FormData();
	fdata.append("address", enteredAddrs);
	
	$.ajax({
		url : '/updateAddrs',
		type: "POST",
        enctype: 'multipart/form-data',
        data: fdata,
        processData: false,
        contentType: false,
        cache: false,
        headers: header
	})
	.done(function(data) {
		showAlert(data, 'success', 2000);
		$("textarea#addrs").attr('readonly','true');
		$(".hidden").show(400);
		$('#editAddrs').hide();
		address = enteredAddrs;
	}).fail(function(response) {
		alert("Temporary issue. Please try in some time.");
	}).always(function() {
	});
};

function getCustAddress() {
	$.ajax({
		url : '/getCustAddress',
        headers: header
	})
	.done(function(data) {
		$("#addrs").text(data);
		address = data;
	}).fail(function(response) {
		alert("Temporary issue. Please try in some time.");
		if(response.status == 403 || response.status == 401) {
			window.location.href = "/page/loginPage";
		}
	}).always(function() {
	});
}

function placeOrder() {
	var payMode = $("input[name='payMode']:checked");
	if(payMode.val() == null) {
		showAlert('Please select Payment mode', 'danger', 2000);
		return;
	}
	if (confirm("Are you sure, place order with pay mode: "+ payMode.next('label').text() +" ?")) {
		$.ajax({
			url : '/placeOrder',
	        headers: header
		})
		.done(function(data) {
			getAlertTypeByResponseCode(data.retCode);
			showAlert(data.msg, getAlertTypeByResponseCode(data.retCode), -1); //-1 means do not auto hide alert msg
			if(data.retCode==1007)
				setTimeout(redirectToOrderPage, 2000);
		}).fail(function(data) {
			alert("Temporary issue. Please try in some time.");
		}).always(function() {
		});
	} else {
	  return;
	}
}

function getAlertTypeByResponseCode(retType) {
	if(retType == 1007)
		return 'success';
	if(retType == 1008)
		return 'danger';
}

var redirectToOrderPage = function() {
	window.location.href = "/page/liveOrdersPage";
}

function logout() {
	Cookies.remove('food-jwt-token');
	window.location.href = "/page/loginPage";
}
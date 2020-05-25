$( document ).ready(function() {
	
	$("input.otp").parent().hide();
	$("button[type='submit']").hide();
	
	//remove jwt token from cookies on load of login/signup page
	Cookies.remove('food-jwt-token');
	
		$("#signup").submit(function(e) {

		    e.preventDefault();
		    var form = $(this);
		   // var url = form.attr('action');
			$.ajax({
				url : '/createUser',
				data: form.serialize(),
				method: 'POST'
			})
			.done(function(data) {
			//	console.log(data);
				showAlert(data.msg, 'danger');
				if(data.retCode === 1007) {
					Cookies.set('food-jwt-token', data.data[0]);
					window.location.href = "/page/productsPage";
				} else {
					// failed
				}
			}).fail(function(data) {
				//alert(data);
			}).always(function() {
			//	alert("complete");
			});
		});

		$("#getOtpBtnOnSignupPage").click(function() {
			
			var isValid = /[789][0-9]{9}/.test($('input[name="mobileNo"]').val());
			if(!isValid) {
				showAlert("Enter correct 10 digits mobile No", "danger");
				return false;
			}
			if (confirm("Is mobile number is correct format ?")) {
			} else {
			  return;
			}
			$.ajax({
				url : '/generateOtp',
				data: $('#signup').serialize(),
				method: 'POST'
			})
			.done(function(data) {				
				showAlert(data.msg, 'success');
				if(data.retCode === 1007) {
					$("#getOtpBtnOnSignupPage").hide();
					$("input.otp").parent().show(400);
					$("button[type='submit']").show(400);
				} else {
					// failed
				}
			}).fail(function(data) {
				alert("Fail");
			}).always(function(data) {
			});
		});
		
		$("#getOtpBtnOnLoginPage").click(function() {
			
			var isValid = /[789][0-9]{9}/.test($('input[name="mobileNo"]').val());
			if(!isValid) {
				showAlert("Enter correct 10 digits mobile No", "danger");
				return false;
			}
			if (confirm("Is mobile number is correct format ?")) {
			} else {
			  return;
			}
			$.ajax({
				url : '/generateOtpForLogin',
				data: $('#login').serialize(),
				method: 'POST'
			})
			.done(function(data) {
				if(data.retCode === 1008)
					showAlert(data.msg, 'danger');
				if(data.retCode === 1007) {
					showAlert(data.msg, 'success');
					$("#getOtpBtnOnLoginPage").hide();
					$("input.otp").parent().show(400);
					$("button[type='submit']").show(400);
				} else {
					// failed
				}
			}).fail(function(data) {
				showAlert('Failed', 'danger');
			}).always(function(data) {
			});
		});
		
		$("#login").submit(function(e) {

		    e.preventDefault();
		    var form = $(this);
			$.ajax({
				url : '/authenticate',
				data: form.serialize(),
				method: 'POST'
			})
			.done(function(data) {
				Cookies.set('food-jwt-token', data);
				window.location.href = "/page/productsPage";
			}).fail(function(jqXHR, textStatus, errorThrown) {
				showAlert(jqXHR.responseText, 'danger');
			}).always(function() {
			//	alert("complete");
			});
		});
		
		function validateMobNo(mob) {
			
		}
});

//alert function
function showAlert(msg, msgType) {
	$.bootstrapGrowl(msg, { type: msgType, delay: 3000});
}
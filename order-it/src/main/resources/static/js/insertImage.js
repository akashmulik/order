$(function() {

	var header = {'Content-Type' : 'application/json',
			'Authorization' : 'Bearer '+Cookies.get('food-jwt-token')};
	
	$("#fileUploadForm").submit(function(e) {
	    e.preventDefault();
	    var form = $('#fileUploadForm')[0];				
	    var formData = new FormData(form);
	    
	var jqxhr = $.ajax({
		method: 'POST',
			url: '/addImage',
			data: formData,
			enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false
		})
		.done(function(data) {
			
		});
	
	});
});
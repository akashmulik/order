<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- bootstrap -->
<link rel="stylesheet" href="#" type="text/css" />
<script src="${pageContext.request.contextPath}/js/bootstrap/jquery-3.1.1.js"></script>
<!-- // bootstrap -->
<script src="${pageContext.request.contextPath}/js/cookies.js"></script>

<script type="text/javascript">

$(function() {

	$.ajax({
		url : '/page/getRoles',
		headers: {'Authorization' : 'Bearer '+Cookies.get('food-jwt-token')}
	}).done(function(data) {
			if(data == '/page/productsPage' || data == "/page/pendingOrdersPage") {
				window.location.href = data;
			}
			else {
				$('body').text('Hello..!! Your account has cretated. It will be active once admin confirms.');
			}
	}).fail(function(data) {
		//alert(data);
	}).always(function() {
		//	alert("complete");
	});
});
</script>

</head>

<body style="overflow: scroll !important;">


</body>
</html>
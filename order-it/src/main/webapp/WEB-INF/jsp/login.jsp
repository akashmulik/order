<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/bootstrap.css" type="text/css" />
<script src="${pageContext.request.contextPath}/js/bootstrap/jquery-3.1.1.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.bootstrap-growl.js"></script>
<script src="${pageContext.request.contextPath}/js/cookies.js"></script>
<script src="${pageContext.request.contextPath}/js/signup.js"></script>

</head>
<body>
	<div class="container">

		<div class="mx-auto mt-5">
			<h4 class="text-center">Logistics</h4>
			<form action="#" method="POST" id="login">

				<fieldset class="form-group">
					<input type="tel" placeholder="Mobile No"
						class="form-control text-center form-control-sm" id="mobileNo"
						name="mobileNo" pattern="[789][0-9]{9}" />
				</fieldset>
				<fieldset class="form-group">
					<input type="tel" placeholder="Enter Otp"
						class="form-control text-center otp form-control-sm" id="otp" name="otp" />
				</fieldset>
				<a class="btn btn-primary btn-block btn-sm" id="getOtpBtnOnLoginPage" href="#">Get Otp</a>
				<button type="submit" class="btn btn-primary btn-block btn-sm">LOGIN</button>
			</form>
			<p class="text-center">
				<a class="badge badge-success" href="/page/signupPage">SIGNUP</a>&nbsp;&nbsp;&nbsp;<small><span  class="text-muted">Crafted By </span><code>Akash M</code></small></p>
		</div>
	</div>

</body>
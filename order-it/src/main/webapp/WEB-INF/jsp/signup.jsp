<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/bootstrap.css" type="text/css" />
 <link rel="stylesheet" href="#" type="text/css" />
<script src="${pageContext.request.contextPath}/js/bootstrap/jquery-3.1.1.js"></script>
 <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js"></script>
<!-- // bootstrap -->
 <script src="${pageContext.request.contextPath}/js/signup.js"></script>
  <script src="${pageContext.request.contextPath}/js/cookies.js"></script>
  <script src="${pageContext.request.contextPath}/js/jquery.bootstrap-growl.js"></script>

</head>
<body>
<div class="container">
<div class="mx-auto mt-5">
			<h4 class="text-center">SIGNUP</h4>

	<form action="signup" method="post" id="signup">
		<div class="form-row">
			<div class="form-group col-lg-6">
				<label for="firstName">Name</label>
				<input type="text" name="firstName" class="form-control text-center form-control-sm" id="firstName" placeholder="Name" required>
			</div>
<!-- 			<div class="form-group col-lg-6">
				<label for="lastName">Last Name</label>
				<input type="text" name="lastName" class="form-control" id="lastName" placeholder="Last Name" required>
			</div>
			<div class="form-group col-lg-6">
				<label for="address">Address</label>
				<textarea class="form-control" name="address" rows="3" required></textarea>
			</div>  -->
			<div class="form-group col-lg-6">
				<label for="mobileNo">Mobile No</label>
				<input type="tel" class="form-control text-center form-control-sm" name="mobileNo" id="mobileNo" pattern="[789][0-9]{9}" placeholder="Mobile No" required>
			</div>
			<a class="btn btn-primary btn-block btn-sm" id="getOtpBtnOnSignupPage" href="#">Get Otp</a>
			<div class="form-group col-lg-6">
				<label for="otp">Otp</label>
				<input type="tel" class="form-control text-center otp form-control-sm" name="otp" pattern="[0-9]{4}" placeholder="OTP">
			</div>
			<button type="submit" class="btn btn-success btn-block btn-sm">SIGNUP</button>
		</div>
	</form>
	<p class="text-center">
				<a class="badge badge-success" href="/page/loginPage">LOGIN</a>&nbsp;&nbsp;&nbsp;<small><span  class="text-muted">Crafted By </span><code>Akash M</code></small></p>
	</div>
</div>
</body>
</html>
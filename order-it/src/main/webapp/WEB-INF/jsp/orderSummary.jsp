<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/bootstrap.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css" />
<link rel="stylesheet" href="#" type="text/css" />
<script
	src="${pageContext.request.contextPath}/js/bootstrap/jquery-3.1.1.js"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js"></script>
<!-- // bootstrap -->
<script src="${pageContext.request.contextPath}/js/orderSummary.js"></script>
<script src="${pageContext.request.contextPath}/js/cookies.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.bootstrap-growl.js"></script>

<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
  <a class="navbar-brand" href="productsPage">Logistics</a>
  <button class="navbar-toggler shadow" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="productsPage">All Products <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="cartPage">Cart</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="liveOrdersPage">My Order</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="loginPage">Logout</a>
      </li>
<!--      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Dropdown
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item active">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li> -->
    </ul>
  </div>
</nav>
</head>

<body style="overflow: scroll !important;">
	<div class="container">

		<div class="container">
		<br>
		<div class="card-columns">
<!-- 		address -->
			<div class="card">
				<div class="card-header"><h5 class="card-title">Address</h5></div>
				<div class="card-body">
					
					<textarea class="form-control" id="addrs" rows="4" readonly></textarea><br>
					<a href="#" id="editAddrs" class="btn btn-primary btn-sm">Edit</a>
					<a href="#" onclick="confirmAddrs()" class="btn btn-success btn-sm">Confirm</a>
				</div>
			</div>
<!-- 		payment -->
			<div class="card payment-card hidden">
				<div class="card-header"><h5 class="card-title">Payment Method</h5></div>
				<div class="card-body">
					<div class="custom-control custom-radio">
						<input type="radio" id="cod" name="payMode"
							class="custom-control-input" value="cod"> <label
							class="custom-control-label" for="cod">Cash On Delivery</label>
					</div>
					<div class="custom-control custom-radio">
						<input type="radio" id="debit" name="payMode"
							class="custom-control-input" value="debitc"> <label
							class="custom-control-label" for="debit">Debit Card</label>
					</div>
					<div class="custom-control custom-radio">
						<input type="radio" id="upi" name="payMode"
							class="custom-control-input" value="upi"> <label
							class="custom-control-label" for="upi">UPI</label>
					</div>
				</div>
				</div>
				<div class="text-center">
				<button class="btn btn-success hidden" onclick="placeOrder()">Place Order</button>
				</div>
		</div>
		</div>
	</div>
</body>
</html>
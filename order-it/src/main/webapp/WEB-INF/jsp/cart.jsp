<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- bootstrap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/bootstrap/bootstrap.css"
	type="text/css" />
<link rel="stylesheet" href="#" type="text/css" />
<script
	src="${pageContext.request.contextPath}/js/bootstrap/jquery-3.1.1.js"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js"></script>
<!-- // bootstrap -->
<script src="${pageContext.request.contextPath}/js/cart.js"></script>
<script src="${pageContext.request.contextPath}/js/cookies.js"></script>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="#">Logistics</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="productsPage">All Products <span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item"><a class="nav-link" href="cartPage">Cart</a>
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
      </li> -->
			<li class="nav-item"><a class="nav-link disabled" href="#"
				tabindex="-1" aria-disabled="true">Disabled</a></li>
		</ul>
	</div>
</nav>
</head>

<body style="overflow: scroll !important;">
	<div class="container">
		<h3>Cart</h3>
		<table class="table table-condensed table-striped" id="cartTable">
			<thead>
				<tr>
					<th>Name</th>
					<th>Qty</th>
					<th>Rate</th>
					<th>Total</th>
					<th>Remove</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products | SomeShop</title>

<link rel="stylesheet"
	href="<c:url value="resources/styles/bootstrap/3.3.5/css/bootstrap.min.css" />" />
<link rel="stylesheet"
	href="<c:url value="resources/styles/bootstrap/3.3.5/css/bootstrap-theme.min.css" />" />
<link rel="stylesheet"
	href="<c:url value="resources/styles/pivotal.css" />" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">


<link rel="icon" href="<c:url value="/images/favicon.png" />">

<style>
#captionRow {
	background: silver;
	padding: 3px;
}

#mainTable {
	margin: 8px;
	align-content: center;
	align-self: center;
}

th, td {
	padding-left: 24px;
	padding-right: 24px;
	padding-top: 16px;
	padding-bottom: 16px;
}

table {
	border: 3px inset black;
}

th {
	border: 2px solid;
	text-align: center;
}

td {
	border: 2px solid;
	text-align: center;
}
</style>

<script type="text/javascript">
	function preventBack() {
		window.history.forward();
	}
	setTimeout("preventBack()", 0);
	window.onunload = function() {
		null
	};
</script>

</head>
<body background="/images/bg_green.jpg">

	<nav
		class="navbar navbar-inverse navbar-static-top navbar-dark bg-dark">
		<ul class="nav navbar-nav mr-auto">
			<li><a class="navbar-brand" href="#"><i class="fa fa-shopping-cart"></i>
					SomeShop</a></li>
			<li class="nav-item"><a class="nav-link" href="/home/${user.id}">Home
			</a></li>
			<li class="nav-item active"><a class="nav-link" href="#">My
					Products</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/addProduct/${user.id}">Add Product</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right" style="padding-right: 16px;">
			<li class="nav-item"><a class="nav-link"
				href="/password/${user.id}">Change Password</a></li>
			<li class="nav-item"><a class="nav-link" href="/"><i
					class="fa fa-sign-out"></i> Logout</a></li>
		</ul>
	</nav>

	<div id="mainTable" align="center">
		<table border="1">
			<tr>
				<th id="captionRow" colspan="4">Product List</th>
			</tr>
			<tr>
				<th>Product ID</th>
				<th>Product Name</th>
				<th>Product Quantity</th>
				<th>Product Origin</th>
			</tr>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.qty}</td>
					<td>${product.origin}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>
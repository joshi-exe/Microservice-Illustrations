<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product | SomeShop</title>

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
	align-content: flex-start;
	align-self: flex-start;
	align-items: flex-start;
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

#failureMessage {
	color: red;
	margin: 8px;
}

#successMessage {
	color: green;
	margin: 8px;
}

#btn {
	display: inline-block;
}

#btn {
	margin-top: 6px;
}

.has-error {
	color: red;
}

body::after {
	content: "";
	opacity: 0.7;
	background: url(/images/bg_brown.jpg);
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	position: absolute;
	z-index: -1;
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
<body>

	<nav
		class="navbar navbar-inverse navbar-static-top navbar-dark bg-dark">
		<ul class="nav navbar-nav mr-auto">
			<li><a class="navbar-brand" href="#"><i
					class="fa fa-shopping-cart"></i> SomeShop</a></li>
			<li class="nav-item"><a class="nav-link" href="/home/${user.id}">Home</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/myProducts/${user.id}">My Products</a></li>
			<li class="nav-item active"><a class="nav-link" href="#">Add
					Product</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right" style="padding-right: 16px;">
			<li class="nav-item"><a class="nav-link"
				href="/password/${user.id}">Change Password</a></li>
			<li class="nav-item"><a class="nav-link" href="/"><i
					class="fa fa-sign-out"></i> Logout</a></li>
		</ul>
	</nav>

	<div id="mainTable" align="center">
		<form:form action="/add/${user.id}" method="post"
			modelAttribute="productForm" novalidate="novalidate">

			<table border="1">
				<tr>
					<th id="captionRow" colspan="2" align="left">Product
						Information</th>
				</tr>
				<tr>
					<td><label class="col-md-3 control-table" for="name">Name</label>
					</td>
					<td><form:input type="text" path="name" id="name"
							class="form-control input-sm" />
						<div class="has-error">
							<b><form:errors path="name" class="help-inline" /></b>
						</div></td>
				</tr>
				<tr>
					<td><label class="col-md-3 control-table" for="qty">Quantity</label>
					</td>
					<td><form:input type="number" path="qty" id="qty"
							class="form-control input-sm" />
						<div class="has-error">
							<b><form:errors path="qty" class="help-inline" /></b>
						</div></td>
				</tr>
				<tr>
					<td><label class="col-md-3 control-table" for="origin">Origin</label>
					</td>
					<td><form:input type="text" path="origin" id="origin"
							class="form-control input-sm" />
						<div class="has-error">
							<b><form:errors path="origin" class="help-inline" /></b>
						</div></td>
				</tr>
				<tr>
					<td colspan="2"><div class="row" id="btn">
							<div class="form-actions float-left">
								<input type="submit" value="Add Product" class="btn btn-primary">
							</div>
							<input id="btn" type="button" value="Cancel"
								class="btn btn-primary"
								onclick="window.location = '/addProduct/${user.id}'"></input>
						</div></td>
				</tr>
			</table>
		</form:form>

		<c:if test="${not empty productForm.message}">
			<c:choose>
				<c:when test="${productForm.isFailure == true}">
					<div id="failureMessage">${productForm.message}</div>
				</c:when>
				<c:otherwise>
					<div id="successMessage">${productForm.message}</div>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>

</body>
</html>
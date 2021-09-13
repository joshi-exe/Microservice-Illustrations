<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Password | SomeShop</title>

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
h3 {
	margin-left: 8px;
	margin-bottom: 32px;
}

.has-error {
	color: red;
}

#failureMessage {
	color: red;
	margin: 8px;
	margin-bottom: 20px;
}

#successMessage {
	color: green;
	margin: 8px;
	margin-bottom: 20px;
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
			<li><a class="navbar-brand" href="#"><i class="fa fa-shopping-cart"></i>
					SomeShop</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/home/${userForm.id}">Home </a></li>
			<li class="nav-item"><a class="nav-link"
				href="/myProducts/${userForm.id}">My Products</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/addProduct/${userForm.id}">Add Product</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right" style="padding-right: 16px;">
			<li class="nav-item active"><a class="nav-link" href="#">Change
					Password</a></li>
			<li class="nav-item"><a class="nav-link" href="/"><i
					class="fa fa-sign-out"></i> Logout</a></li>
		</ul>
	</nav>

	<div>
		<h3>Change Password</h3>
		<c:if test="${not empty userForm.message}">
			<c:choose>
				<c:when test="${userForm.isFailure == true}">
					<div id="failureMessage">${userForm.message}</div>
				</c:when>
				<c:otherwise>
					<div id="successMessage">${userForm.message}</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		<form:form action="update" method="post" modelAttribute="userForm"
			novalidate="novalidate">

			<div class="row">
				<div class="form-group col-md-12">
					<!-- <label class="col-md-3 control-table" for="id">User ID</label> -->
					<div class="col-md-7">
						<form:input type="hidden" path="id" id="id"
							class="form-control input-sm"
							onkeypress="return event.charCode != 32" />
						<div class="has-error">
							<form:errors path="id" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<!-- <label class="col-md-3 control-table" for="userName">UserName</label> -->
					<div class="col-md-7">
						<form:input type="hidden" path="userName" id="userName"
							class="form-control input-sm"
							onkeypress="return event.charCode != 32" />
						<div class="has-error">
							<form:errors path="userName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-table" for="password">Password</label>
					<div class="col-md-7">
						<form:input type="password" path="password" id="password"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="password" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-table" for="confirmPassword">Confirm
						Password</label>
					<div class="col-md-7">
						<form:input type="password" path="confirmPassword"
							id="confirmPassword" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="confirmPassword" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-actions float-right" align="center">
					<input type="submit" value="Submit" class="btn btn-primary">
				</div>
			</div>

		</form:form>
	</div>

</body>
</html>
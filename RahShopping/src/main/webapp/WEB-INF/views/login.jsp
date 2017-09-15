<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />


<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Shopping - ${title}</title>

<script>
	window.menu = '${title}';
	window.contextRoot='${contextRoot}'
</script>


<!-- Bootstrap Core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap Readable theme (added from bootswatch.com)-->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap for tables from (Datatables)-->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">


<!-- Custom CSS -->
<link href="${css}/myapp.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<div class="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
			
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">								
					<a class="navbar-brand" href="${contextRoot}/home">Online Shopping </a>
				</div>
			</div>
		</nav>
		
		
		<!-- Page Content -->
		<div class="content">
			<div class="container">
			
				<!--  This will be displayed if we invalid user/password -->
				<c:if test="${not empty message}">
					<div class="row">
						<div class="col-md-offset-3 col-md-6">
							<div class="alert alert-danger">${message}</div>
					</div>
					</div>
				</c:if>		
				<div class="row">
					<div class="col-md-offset-3 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>Login</h4>
							</div>
						<div class="panel-body">
						<form action="${contextRoot}/login" method="post" class="form-horizontal" id="loginForm">
						<div class="form-group">
							<label for="username" class="col-md-4 control-label">Email: </label>
							<div class="col-md-8">
							<!--  you should have a field with this name to use it by spring-security (username) if you want to change it you need to make a change from spring-security.xml -->
								<input type="text" name="username" id="username" class="form-control"/>
							</div>							
						</div>
						<div class="form-group">
							<label for="password" class="col-md-4 control-label">Password: </label>
							<div class="col-md-8">
								<input type="password" name="password" id="password" class="form-control"/>
							</div>							
						</div>	
						<div class="form-group">						
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" value="Login" class="btn btn-primary"/>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							</div>							
						</div>						
						</form>													
					
					</div>
					<div class="panel-footer">
					<div class="text-right">
						New User - <a href="${contextRoot}/register">Register Here</a>					
					</div>						
			      </div>
				</div>				
				</div>
			</div>								
				
			</div>
		</div>

		<!--  footer -->
		<%@include file="./shared/footer.jsp"%>

		<!-- jQuery -->
		<script src="${js}/jquery.js"></script>

		<!-- jQuery validation from jqueryvalidation.org -->
		<script src="${js}/jquery.validate.js"></script>

		<!-- jQuery validation min -->
		<script src="${js}/jquery.validate.min.js"></script>


		<!-- Bootstrap Core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>
	
		<!--  Self coded javaScript -->
		<script src="${js}/myapp.js"></script>

	</div>
</body>

</html>

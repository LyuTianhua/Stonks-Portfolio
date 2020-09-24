<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="registerStyles.css">
	<title>Document</title>
</head>

<body>

<div class = "my-nav" id="nav-container">
	<nav class="navbar navbar-expand-lg navbar-light bg-custom">
		<a class="navbar-brand text-wrap" href="#" id="nav-text">CSCI 310: Stock Portfolio Tracker Team 10</a>	 
	</nav>
</div>
<div class="container-fluid">
	<div class="row">
		<div class="col-0 col-sm-3 col-md-3 col-lg-3"></div>
		<div class="col-12 col-sm-6 col-md-6 col-lg-6" id="form-outer">
			<div class="row text-center" id="link">
				<a id="link1" class="col-6" href="index.jsp">Sign In</a>
				<a id="link2" class="col-6" href="register.jsp">Register</a>
			</div>
			<form id="login-fields">
				<label class="text-left" id="email" for="exampleInputEmail1">Email Address</label>
				<input type="email" class="form-control" id="exampleInputEmail1">
				<label class="text-left" id="password" for="exampleInputPassword1">Password</label>
				<input type="password" class="form-control" id="exampleInputPassword1">
				<label class="text-left" id="password" for="exampleInputPassword2">Verify Password</label>
				<input type="password" class="form-control" id="exampleInputPassword2">
				<div class="text-center">
					<button id="register" type="submit" class="btn btn-primary">Register</button>
				</div>
			</form>
		</div>
		<div class="col-0 col-sm-3 col-md-3 col-lg-3"></div>
	</div>
</div>

<!-- Not sure if this is necessary since I have it linking to index.jsp
 -->
<script> 
	function login() {
		window.location.replace("Login.jsp");
	}
	function register() {
		window.location.replace("Register.jsp");
	}
</script>
	
</body>
</html>

	
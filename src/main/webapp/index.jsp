<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="loginStyles.css">
	<title>Document</title>
</head>

<body>

<div class = "my-nav">
	<nav class="navbar navbar-expand-lg navbar-light bg-custom">
		<a class="navbar-brand" href="#">CSCI 310: Stock Portfolio Tracker Team 10</a>	 
	</nav>
</div>
<div id="flexStuff">
	<div class="container">
		<div class =  "col-12 col-sm-6 col-md-3">
			<div class = "form-outer">
				<div class="link">
					<a id="link1" href="index.jsp">Sign In</a>
					<a id="link2" href="register.jsp">Register</a>
				</div>
				<form id = "login-fields">
					  <div class="form-group">
					    <label id="email" for="exampleInputEmail1">Email Address</label>
					    <input type="email" class="form-control" id="exampleInputEmail1">
					  </div>
					  <div class="form-group">
					    <label id="password" for="exampleInputPassword1">Password</label>
					    <input type="password" class="form-control" id="exampleInputPassword1">
					  <button id="signin" type="submit" class="btn btn-primary">Sign In</button>
					  </div>
				</form>
			</div>	
		</div>
	</div>
</div>


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

	
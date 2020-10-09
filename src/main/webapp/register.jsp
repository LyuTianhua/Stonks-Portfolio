<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="styles.css">
	<title>Register</title>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-custom my-nav">
	<a class="navbar-brand text-wrap" href="#" id="nav-text">USC CS 310 Stock Portfolio Management</a>	 
</nav>
<div class="container-fluid">
	<div class="row">
		<div class="col-0 col-sm-3 col-md-3 col-lg-3"></div>
		<div class="col-12 col-sm-6 col-md-6 col-lg-6" id="form-outer">
			<div class="row text-center" id="link">
				<a class="col-6" id="link1" href="index.jsp">Sign In</a>
				<a class="col-6" id="link2" href="register.jsp">Register</a>
			</div>
			<form id="login-fields">
				<label class="text-left" id="email" for="exampleInputEmail1">Email Address</label>
				<input class="form-control" id="exampleInputEmail1" type="email" name="email">
				<label class="text-left" id="password" for="exampleInputPassword1">Password</label>
				<input class="form-control" id="exampleInputPassword1" type="password" name="password">
				<label class="text-left" id="VerifyPassword" for="exampleInputPassword2">Verify Password</label>
				<input class="form-control" id="exampleInputPassword2" type="password" name="verifyPassword">
				<div class="text-center" id="Error-Message"></div> 
				<div class="row">
					<button id="register" class="btn btn-primary" type="submit" name="registerButton">Create User</button>
					<button id="cancel" class="btn btn-primary" type="button" name="cancelButton">Cancel</button>
				</div>
			</form>
		</div>
		<div class="col-0 col-sm-3 col-md-3 col-lg-3"></div>
	</div>
</div>

<!-- Not sure if this is necessary since I have it linking to index.jsp
 -->
<script> 
	// When I hit cancel, go back to login page
	document.querySelector("#cancel").onclick = function() {
		window.location.href = "index.jsp";
	}

	document.querySelector("#login-fields").onsubmit = function(event) {
		document.querySelector("#Error-Message").innerHTML = "";
		event.preventDefault();
		let email = document.querySelector("#exampleInputEmail1").value.trim();
		let password = document.querySelector("#exampleInputPassword1").value.trim();
		let confirm = document.querySelector("#exampleInputPassword2").value.trim();

		let httpRequest = new XMLHttpRequest();
		httpRequest.open("POST", "/Signup?" + "email=" + email + "&password=" + password + "&confirm=" + confirm, true);
		httpRequest.send();
		httpRequest.onreadystatechange = function() {
			var msg = httpRequest.responseText.trim();
			if (msg === "1") {
				window.location.href = "index.jsp";
			} else if (msg === "0") {
				// set error message div
				document.querySelector("#Error-Message").innerHTML = "Invalid email or password";
			}
		}			
	}
	
	document.querySelector("#exampleInputPassword2").onchange = function() {
		let password = document.querySelector("#exampleInputPassword1").value.trim();
		let confirm = document.querySelector("#exampleInputPassword2").value.trim();
		if (password !== confirm) {
			document.querySelector("#Error-Message").innerHTML = "Two password doesn't match";
			return false;
		} else {
			document.querySelector("#Error-Message").innerHTML = "";
			return true;
		}
		return true;
	}
	

</script>
	
</body>
</html>

	
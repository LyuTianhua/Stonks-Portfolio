<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"%>

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
		<a class="navbar-brand text-wrap" href="#">CSCI 310: Stock Portfolio Tracker Team 10</a>	 
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
			<form id="login-fields" >
				<label class="text-left" id="email" for="exampleInputEmail1">Email Address</label>
				<input type="text" class="form-control" id="exampleInputEmail1" name="email" placeholder="tu1">
				<label class="text-left" id="password" for="exampleInputPassword1">Password</label>
				<input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="tu1pass">
				<div class="text-center">
					<button id="signin" type="submit" class="btn btn-primary" onclick="login()">Sign In</button>
				</div>
			</form>
		</div>
		<div class="col-0 col-sm-3 col-md-3 col-lg-3"></div>
	</div>
</div>


<script src="https://code.jquery.com/jquery-3.5.0.js" integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<script>
	// function login() {
	// 	window.location.replace("Login.jsp");
	// }
	function register() {
		window.location.replace("Register.jsp");
	}

	console.log(document.getElementById("exampleInputEmail1").val)
	console.log(document.getElementById("exampleInputPassword1").innerText)

	const login = () => {
		$.ajax({
			url: "/login",
			type: "post",
			data: {
				email: document.getElementById("exampleInputEmail1").innerText,
				password: document.getElementById("exampleInputPassword1").innerText
			},
			success: (r) => {
				document.getElementById("exampleInputEmail1").innerHTML = "success";
				console.log("success")
			}
		})
	};

	function signin(e) {
		e.preventDefault()

	}
</script>
	
</body>
</html>


<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-test</artifactId>
	<version>5.1.8.RELEASE</version>
	<scope>test</scope>
</dependency>

<dependency>
	<groupId>org.mockito</groupId>
	<artifactId>mockito-core</artifactId>
	<version>2.23.4</version>
	<scope>test</scope>
</dependency>

<!-- https://mvnrepository.com/artifact/org.mockito/mockito-all -->
<dependency>
	<groupId>org.mockito</groupId>
	<artifactId>mockito-all</artifactId>
	<version>1.9.5-rc1</version>
	<scope>test</scope>
</dependency>
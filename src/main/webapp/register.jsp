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

<%@include file="partials/nav.jsp"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-0 col-sm-3 col-md-3 col-lg-3"></div>
		<div class="col-12 col-sm-6 col-md-6 col-lg-6" id="form-outer">
			<div class="row text-center" id="link">
				<a class="col-6" id="link1" href="index.jsp">Sign In</a>
				<a class="col-6" id="link2" href="register.jsp">Register</a>
			</div>

			<%@include file="partials/registerForm.jsp"%>

		</div>
		<div class="col-0 col-sm-3 col-md-3 col-lg-3"></div>
	</div>
</div>


<script type="text/javascript">

	// When I hit cancel, go back to login page
	const cancl= () => window.location.href = "index.jsp"

	const registr = () => $.ajax({
							url: "Signup",
							type: "Post",
							data: {
								email: $("#rEmail").val(),
								password: $("#rPassword").val(),
								confirm: $("#rConfirm").val()
							},
							success: (res) => {
								if (res == "1") {
									$("#Error-Message").html("")
									window.location.href = "index.jsp";
								} else if (res == "-1") {
									$("#Error-Message").html("Username already exists.")
								} else {
									$("#Error-Message").html("Username and Password invalid.")									
								}
							}
						})

	const match = () => $("#Error-Message").html(
							$("#rPassword").val() !== $("#rConfirm").val() ?
							"Two password doesn't match" : ""
						)

</script>
<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
<script src="https://code.jquery.com/jquery-3.5.0.js" integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>


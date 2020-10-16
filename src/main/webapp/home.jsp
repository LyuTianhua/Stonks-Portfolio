<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="styles.css">
	<title>Home</title>

</head>
<body>

<%
	HttpSession sesh = request.getSession(false);
	int id = sesh.getAttribute("id") == null ? 0 : (int)sesh.getAttribute("id");
	if (id == 0) response.sendRedirect("index.jsp");
%>

<%@include file="partials/nav.jsp"%>

<div class="container">
	<div class="row justify-content-center">
		<div class="btn-groups" role="group">
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#add-stock-modal" id="add-stock-btn" name="add-stock-btn">
				Add Stock
			</button>
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#view-stock-modal" id="view-stock-btn">
				View Stock
			</button>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="row mt-3">
				<img src="https://ak.picdn.net/shutterstock/videos/16504675/thumb/4.jpg" class="img-fluid rounded shadow" alt="">
			</div>
			<div class="row mt-3">
				<h3>Portfolio Stocks</h3>

				<table id="portfolio-stocks" class="table table-hover">
					<%--						filled dynamically--%>
				</table>
			</div>
		</div>
	</div>
</div>

<!-- Add Stock Modal -->
<%@include file="partials/addStockModal.jsp"%>

<!-- View Stock Modal -->
<%@include file="partials/viewStockModal.jsp"%>

<script>
	const logout = () =>
			$.ajax({
				url : "Logout",
				type : "Get",
				success : () => window.location.href = "index.jsp"
			})
	const add = () =>
			$.ajax({
				url : "AddStock",
				type: "Get",
				data : {
					ticker   : $("#ticker").val(),
					date     : $("#date-purchased").val(),
					quantity : $("#quantity").val()
				},
				success : () => location.reload()
			})
	const remove = (t, q) =>
			$.ajax({
				url : "RemoveStock",
				type : "Get",
				data : {
					ticker   : t,
					quantity : $(q).val()
				},
				success : () => location.reload()
			})
	const idleTimer = () => {
		let t;
		window.onload = resetTimer;
		window.onmousemove = resetTimer; // catches mouse movements
		window.onmousedown = resetTimer; // catches mouse movements
		window.onclick = resetTimer;     // catches mouse clicks
		window.onscroll = resetTimer;    // catches scrolling
		window.onkeypress = resetTimer;  //catches keyboard actions
		function resetTimer() {
			clearTimeout(t);
			t = setTimeout(logout, 120000);  // time is in milliseconds (1000 is 1 second)
		}
	}
	idleTimer();
	window.addEventListener( "load", () =>
			$.ajax( {
				url : "LoadProfile",
				type : "Get",
				success : (res) => $("#portfolio-stocks").html(res)
			})
	)
</script>
<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
<script src="https://code.jquery.com/jquery-3.5.0.js" integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
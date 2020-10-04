<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="styles.css">
	<title>USC CS 310 Stock Portfolio Management</title>
</head>
<body>
	<nav class="navbar navbar-light my-nav">
		<a class="navbar-brand text-wrap" href="#">USC CS 310 Stock Portfolio Management</a>	 
		<button id="logout" type="button" class="btn btn-dark pull-right">Log Out</button>
	</nav>

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="row mt-2">
					<p>This is where the graph will go. Size will adjust accordingly.</p>
				</div>
				<div class="row mt-2">
					<h3>Portfolio Stocks</h3>
					<table class="table table-hover">
						<tr>
							<th>Stonk</th>
							<th>Price</th>
							<th>Shares</th>
							<th>24H Gain</th>
						</tr>
						<tr>
							<td>AAPL</td>
							<td>$115.55</td>
							<td>50,129</td>
							<td>+4.5</td>
						</tr>
						<tr>
							<td>AAPL</td>
							<td>$115.55</td>
							<td>50,129</td>
							<td>+4.5</td>
						</tr>
						<tr>
							<td>AAPL</td>
							<td>$115.55</td>
							<td>50,129</td>
							<td>+4.5</td>
						</tr>
						<tr>
							<td>AAPL</td>
							<td>$115.55</td>
							<td>50,129</td>
							<td>+4.5</td>
						</tr>
					</table>
				</div>
				<div class="row mt-2">
					<h3>Viewing Stocks</h3>
					<table class="table table-hover">
						<tr>
							<th>Stonk</th>
							<th>Price</th>
							<th>Shares</th>
							<th>24H Gain</th>
						</tr>
						<tr>
							<td>AAPL</td>
							<td>$115.55</td>
							<td>50,129</td>
							<td>+4.5</td>
						</tr>
						<tr>
							<td>AAPL</td>
							<td>$115.55</td>
							<td>50,129</td>
							<td>+4.5</td>
						</tr>
						<tr>
							<td>AAPL</td>
							<td>$115.55</td>
							<td>50,129</td>
							<td>+4.5</td>
						</tr>
						<tr>
							<td>AAPL</td>
							<td>$115.55</td>
							<td>50,129</td>
							<td>+4.5</td>
						</tr>
					</table>
				</div>		
			</div>
		</div>
	</div>

	<script type="text/javascript">
		document.querySelector("#logout").onclick = function(event) {
			window.location.href = "index.jsp";
		}

	</script>
</body>
</html>
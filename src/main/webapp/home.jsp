<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<nav class="navbar navbar-light my-nav">
		<a class="navbar-brand text-wrap" href="#">USC CS 310 Stock Portfolio Management</a>	 
		<button id="logout" type="button" class="btn btn-dark float-right">Log Out</button>
	</nav>
	<div class="container">
		<div class="row justify-content-center">
			<div class="btn-groups" role="group">
  				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#add-stock-modal" id="add-stock-btn">
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
						<tr>
							<th>Stonk</th>
							<th>Price</th>
							<th>Shares</th>
							<th>Remove Stock</th>
						</tr>


<%--						<tr>--%>
<%--							<td>AAPL</td>--%>
<%--							<td>$115.55</td>--%>
<%--							<td>50,129</td>--%>
<%--							<td>--%>
<%--								<button type="button" class="close" aria-label="Close">--%>
<%--  									<span aria-hidden="true">&times;</span>--%>
<%--								</button>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td>MSFT</td>--%>
<%--							<td>$115.55</td>--%>
<%--							<td>50,129</td>--%>
<%--							<td>--%>
<%--								<button type="button" class="close" aria-label="Close">--%>
<%--  									<span aria-hidden="true">&times;</span>--%>
<%--								</button>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td>TSLA</td>--%>
<%--							<td>$115.55</td>--%>
<%--							<td>50,129</td>--%>
<%--							<td>--%>
<%--								<button type="button" class="close" aria-label="Close">--%>
<%--  									<span aria-hidden="true">&times;</span>--%>
<%--								</button>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td>SPCE</td>--%>
<%--							<td>$115.55</td>--%>
<%--							<td>50,129</td>--%>
<%--							<td>--%>
<%--								<button type="button" class="close" aria-label="Close">--%>
<%--  									<span aria-hidden="true">&times;</span>--%>
<%--								</button>--%>
<%--							</td>--%>
<%--						</tr>--%>
					</table>
				</div>
				<div class="row mt-3">
					<h3>Viewing Stocks</h3>
					<table class="table table-hover">
						<tr>
							<th>Stonk</th>
							<th>Price</th>
							<th>Shares</th>
							<th>Remove Stock</th>
						</tr>
						<tr>
							<td>AAPL</td>
							<td>$115.55</td>
							<td>50,129</td>
							<td>
								<button type="button" class="close" aria-label="Close">
  									<span aria-hidden="true">&times;</span>
								</button>
							</td>
						</tr>
						<tr>
							<td>TSLA</td>
							<td>$115.55</td>
							<td>50,129</td>
							<td>
								<button type="button" class="close" aria-label="Close">
  									<span aria-hidden="true">&times;</span>
								</button>
							</td>
						</tr>
						<tr>
							<td>MSFT</td>
							<td>$115.55</td>
							<td>50,129</td>
							<td>
								<button type="button" class="close" aria-label="Close">
  									<span aria-hidden="true">&times;</span>
								</button>
							</td>
						</tr>
						<tr>
							<td>SPCE</td>
							<td>$115.55</td>
							<td>50,129</td>
							<td>
								<button type="button" class="close" aria-label="Close">
  									<span aria-hidden="true">&times;</span>
								</button>
							</td>
						</tr>
					</table>
				</div>		
			</div>
		</div>
	</div>


	<form id="add-stock-modal-form">
		<div class="form-group">
			<label for="ticker">Ticker</label>
			<input type="text" class="form-control" id="ticker" placeholder="ex. AAPL">
		</div>
		<div class="form-group">
			<label for="quantity">Quantity</label>
			<input type="text" class="form-control" id="quantity" placeholder="ex. 50129">
		</div>
		<div class="form-group">
			<label for="company-name">Company Name</label>
			<input type="text" class="form-control" id="company-name" placeholder="ex. Apple">
		</div>
		<div class="form-group">
			<label for="date-purchased">Date Purchased</label>
			<input type="text" class="form-control" id="date-purchased" placeholder="ex. 12/24/2019">
		</div>
		<div class="form-group">
			<label for="date-sold">Date Sold</label>
			<input type="text" class="form-control" id="date-sold" placeholder="ex. 1/1/2020">
		</div>

		<button type="submit" form="add-stock-modal-form" id = "add-btn"value="submit" class="btn btn-primary">Add Stock</button>
		<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>

	</form>

	<!-- Add Stock Modal -->
<%--	<div class="modal fade" id="add-stock-modal" tabindex="-1" role="dialog" aria-labelledby="add-stock-modal-label" aria-hidden="true">--%>
<%--	  <div class="modal-dialog" role="document">--%>
<%--	    <div class="modal-content">--%>
<%--	      <div class="modal-header">--%>
<%--	        <h5 class="modal-title" id="add-stock-modal-label">Add a stock to your portfolio</h5>--%>
<%--	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--	          <span aria-hidden="true">&times;</span>--%>
<%--	        </button>--%>
<%--	      </div>--%>
<%--	      <div class="modal-body">--%>
<%--	        <form id="add-stock-modal-form">--%>
<%--			  <div class="form-group">--%>
<%--			    <label for="ticker">Ticker</label>--%>
<%--			    <input type="text" class="form-control" id="ticker" placeholder="ex. AAPL">--%>
<%--			  </div>--%>
<%--			  <div class="form-group">--%>
<%--			    <label for="quantity">Quantity</label>--%>
<%--			    <input type="text" class="form-control" id="quantity" placeholder="ex. 50129">--%>
<%--			  </div>--%>
<%--			  <div class="form-group">--%>
<%--			    <label for="company-name">Company Name</label>--%>
<%--			    <input type="text" class="form-control" id="company-name" placeholder="ex. Apple">--%>
<%--			  </div>--%>
<%--			  <div class="form-group">--%>
<%--			    <label for="date-purchased">Date Purchased</label>--%>
<%--			    <input type="text" class="form-control" id="date-purchased" placeholder="ex. 12/24/2019">--%>
<%--			  </div>--%>
<%--			  <div class="form-group">--%>
<%--			    <label for="date-sold">Date Sold</label>--%>
<%--			    <input type="text" class="form-control" id="date-sold" placeholder="ex. 1/1/2020">--%>
<%--			  </div>--%>

<%--				<button type="submit" form="add-stock-modal-form" value="submit" class="btn btn-primary">Add Stock</button>--%>
<%--				<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>--%>

<%--			</form>--%>
<%--	      </div>--%>
<%--	    </div>--%>
<%--	  </div>--%>
<%--	</div>--%>
	
	<!-- View Stock Modal -->
<%--	<div class="modal fade" id="view-stock-modal" tabindex="-1" role="dialog" aria-labelledby="view-stock-modal-label" aria-hidden="true">--%>
<%--	  <div class="modal-dialog" role="document">--%>
<%--	    <div class="modal-content">--%>
<%--	      <div class="modal-header">--%>
<%--	        <h5 class="modal-title" id="view-stock-modal-label">View a stock</h5>--%>
<%--	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--	          <span aria-hidden="true">&times;</span>--%>
<%--	        </button>--%>
<%--	      </div>--%>
<%--	      <div class="modal-body">--%>
<%--	        <form method="GET">--%>
<%--			  <div class="form-group">--%>
<%--			    <label for="ticker1">Ticker</label>--%>
<%--			    <input type="text" class="form-control" id="ticker1" placeholder="ex. AAPL">--%>
<%--			  </div>--%>
<%--			</form>--%>
<%--	      </div>--%>
<%--	      <div class="modal-footer">--%>
<%--	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>--%>
<%--	        <button type="button" class="btn btn-primary">View stock</button>--%>
<%--	      </div>--%>
<%--	    </div>--%>
<%--	  </div>--%>
<%--	</div>--%>
	<script type="text/javascript">
		document.querySelector("#logout").onclick = function(event) {
			window.location.href = "index.jsp";
		}
                                        
		// function idleTimer() {
		// 	let t;
		// 	window.onload = resetTimer;
		// 	window.onmousemove = resetTimer; // catches mouse movements
		// 	window.onmousedown = resetTimer; // catches mouse movements
		// 	window.onclick = resetTimer;     // catches mouse clicks
		// 	window.onscroll = resetTimer;    // catches scrolling
		// 	window.onkeypress = resetTimer;  //catches keyboard actions
		//
		// 	function logout() {
		// 		window.location.href = '/index.jsp';  //Adapt to actual logout script
		// 	}
		//
		// 	function resetTimer() {
		// 		clearTimeout(t);
		// 		t = setTimeout(logout, 3000);  // time is in milliseconds (1000 is 1 second)
		// 	}
		// }
		// idleTimer();

		document.querySelector("#add-stock-modal-form").onsubmit = function(event) {
			event.preventDefault();	
			// let email = document.querySelector("#exampleInputEmail1").value.trim();
			let ticker = document.querySelector("#ticker").value.trim();
			let company = document.querySelector("#company-name").value.trim();
			let quantity = document.querySelector("#quantity").value.trim();

			let httpRequest = new XMLHttpRequest();
			// httpRequest.open("GET", "/AddStock?" + "email=" + email + "&ticker=" + ticker + "&company=" + company + "&quantity=" + quantity, true);
			httpRequest.open("GET", "/AddStock?" + "ticker=" + ticker + "&company=" + company + "&quantity=" + quantity, true);
			httpRequest.send();
			httpRequest.onreadystatechange = function() {			
				const msg = httpRequest.responseText.trim();
				if (msg === "1") {
					// document.querySelector("#testTicker").innerHTML.value = ticker.toString()
					// document.querySelector("#testQuantity").innerHTML.value = quantity.toString()
					// window.location.href = "home.jsp";
					let stockRow = document.createElement("tr");
					let tickerCell = document.createElement("td");
					let priceCell = document.createElement("td");
					let qualityCell = document.createElement("td");
					let removeCell = document.createElement("td");
					
					tickerCell.setAttribute("name", "ticker");
					qualityCell.setAttribute("name", "quantity1");
					
					tickerCell.innerHTML = ticker;
					qualityCell.innerHTML = quantity;
					removeCell.innerHTML = "<button type=\"button\" class=\"close\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>";
					stockRow.appendChild(tickerCell);
					stockRow.appendChild(priceCell);
					stockRow.appendChild(qualityCell);
					stockRow.appendChild(removeCell);
					
					document.querySelector("#portfolio-stocks").appendChild(stockRow);
				} else if (msg === "0") {
					// set error message div
					document.querySelector("#Error-Message").innerHTML = "Password and Email don't match";
				}
			}
		}

	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
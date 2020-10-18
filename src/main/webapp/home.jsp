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
		<div class="btn-group mt-3" role="group">
			<button type="button" class="btn btn-dark" data-toggle="modal"
				data-target="#add-stock-modal" id="add-stock-btn" name="add-stock-btn">
				Add Stock
			</button>
			<button type="button" class="btn btn-dark" data-toggle="modal"
				data-target="#view-stock-modal" id="view-stock-btn">
				View Stock
			</button>
			<button type="button" class="btn btn-dark" data-toggle="modal"
				data-target="#upload-modal" id="upload-btn">
				Upload CSV
			</button>
		</div>
	</div>
	<div class="row justify-content-center m-1">
		<div class="col-md-6">
			<div class="row mt-3">
				<img src="https://ak.picdn.net/shutterstock/videos/16504675/thumb/4.jpg"
					class="img-fluid rounded shadow" alt="">
			</div>
			<div class="row mt-3">
				<h3>Portfolio Stocks</h3>
				<div class="table-responsive">
					<table id="portfolio-stocks" class="table table-hover">
						<%--filled dynamically--%>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Add Stock Modal -->
<%@include file="partials/addStockModal.jsp"%>

<!-- View Stock Modal -->
<%@include file="partials/viewStockModal.jsp"%>

<!-- Upload CSV Modal -->
<%@include file="partials/uploadForm.jsp"%>

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
<script>
	// Check for valid NYSE or NASDAQ ticker
	// Todo: Accept API data to actually check valid ticker
	function checkTicker() {
		if(document.getElementById("ticker").value.length == 0) {
			document.getElementById("ticker-empty").style.display = "inline";
			return false;
		} else {
			document.getElementById("ticker-empty").style.display = "none";
		}
		return true;
	}

	// Check valid quantity
	function checkQuantity() {
		var quantity = document.getElementById("quantity");
		if(isNaN(quantity.value) || quantity.value.length == 0 || quantity.value < 1) {
			document.getElementById("invalid-quantity").style.display = "inline";
		    return false;
		} else {
			document.getElementById("invalid-quantity").style.display = "none";
		}
		return true;
	}

	// Check date sold before date purchased
	// Todo: add check for date only 1 year in the past
	function checkDates() {
	    var datePurchased = new Date(document.getElementById("date-purchased").value);
	    var dateSold = new Date(document.getElementById("date-sold").value);
	    var rightNow = new Date();

	    if(rightNow.getTime() - datePurchased > 31556952000){
	    	document.getElementById("one-year-error").style.display = "inline";
	    	return false;
	    } else {
	    	document.getElementById("one-year-error").style.display = "none";
	    }
	    
	    if(document.getElementById("date-purchased").value.length == 0) {
	    	document.getElementById("purchased-empty").style.display = "inline";
	    	return false;
	    } else {
	    	document.getElementById("purchased-empty").style.display = "none";
	    }
	    
	    if(document.getElementById("date-sold").value.length > 0){
	    	if((dateSold - datePurchased) < 0) {
	    		document.getElementById("invalid-date-sold").style.display = "inline";
	    		return false;
	    	} else {
	    		document.getElementById("invalid-date-sold").style.display = "none";
	    	}
	    }
	    
		return true;
	}

	// Checks valid form inputs before submitting add-stock-form
	function checkAddStockForm() {
		$.ajax({
			url : "TickerChecking",
			type: "Get",
			data : {
				ticker: $("#ticker").val()
			},
			success : (res) => {
				var qtyCheck = checkQuantity();
				var dateCheck = checkDates();
				var tickerEmpty = checkTicker();
				if (res.trim() == "1") {
					document.getElementById("invalid-ticker").style.visibility = "hidden";
					if(qtyCheck && dateCheck) {
						add();
					}
				} else {
					document.getElementById("invalid-ticker").style.visibility = "visible";
					
				}
			}
		})
	}
	
	// Performs ticker check before submitting view stock form
	// TODO: Check valid ticker before adding to view stock
	function checkViewStockForm() {
		// Insert AJAX call for checking valid ticker from API
		
		// Checking if ticker is empty
		if(document.getElementById("ticker").value.length == 0) {
			console.log("View ticker empty.");
			document.getElementById("view-empty").style.display = "inline";
			return false;
		} else {
			document.getElementById("view-empty").style.display = "none";
		}
		return true;
	}

	// Checks if portfolio is up or down for the day and changes
	// portfolio value color and up/down arrow based on each
	function checkUpOrDown() {
		var isUp = <%= session.getAttribute("isUp") %>
		if(isUp){
			document.getElementById("portfolio-value").style.color = "green";
			document.getElementById("up-arrow").style.visibility = "visible";
		} else {
			document.getElementById("portfolio-value").style.color = "red";
			document.getElementById("down-arrow").style.visibility = "visible";
		}
	}

</script>
<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
<script src="https://code.jquery.com/jquery-3.5.0.js" integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>

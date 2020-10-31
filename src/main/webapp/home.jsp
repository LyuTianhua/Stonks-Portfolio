<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="styles.css">
	<title>Home</title>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js" integrity="sha512-d9xgZrVZpmmQlfonhQUvTR7lMPtO7NkZMkA0ABN3PHCbKA5nqylQ/yWlFAyY6hYgdF1Qh6nYiuADWwKB4C2WSw==" crossorigin="anonymous"></script>

</head>
<body>
<%@include file="partials/nav.jsp"%>

<div class="container">
	<div class="row justify-content-center">
		<div class="btn-group col-12 col-md-6 col-lg-6 mt-3" role="group">
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
	<div class="row justify-content-center">
		<div class="col-12 col-md-9 mt-3">
			<%@include file="partials/graph.jsp"%>
		</div>
	</div>
	<div class="row justify-content-center">
		<div class="col-12 col-md-8 mt-3 overflow-auto">
			<h3>Portfolio Stocks</h3>
			<div class="table-responsive">
				<table id="portfolio-stocks" class="table table-hover">
					<%--filled dynamically--%>
				</table>
				<table id="historical-stocks" class="table table-hover">
					<%-- filled dynamically --%>
				</table>
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
	<%if (request.getSession(false).getAttribute("id") == null) {%>
		window.location.replace("index.jsp");
	<%}%>

	const logout = () => $.ajax({
		url : "Logout",
		type : "Get",
		success : () => window.location.href = "index.jsp"
	})

	const add = () => $.ajax({
		url : "AddStock",
		type: "Get",
		data : {
			ticker   : $("#ticker").val(),
			purchased     : $("#date-purchased").val(),
			sold	 : $("#date-sold").val(),
			quantity : $("#quantity").val()
		},
		success : () => location.reload()
	})

	const remove = (t, q) => $.ajax({
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

	var labels = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100,
		101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200,
		201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300,
		301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 362, 363, 364, 365]

	var ctx = document.getElementById('myChart');
	var myChart = new Chart(ctx, {
		type: 'line',
		data: {
			labels,
			datasets: [{
				label: 'portfolio',
				data: [],
				borderWidth: 1
			}]
		},
		options: {
			scales: {
				yAxes: [{
					ticks: {
						beginAtZero: true
					}
				}]
			}
		}
	});

	window.addEventListener( "load", () =>
			$.ajax( {
				url : "LoadProfile",
				type : "Get",
				success : (res) => {
					$("#portfolio-stocks").html(res)
					loadGraph();
				}
			})
	)

	const loadGraph = () => {
		$.ajax({
			url : "LoadGraph",
			type: "Get",
			success: (res) => {
				var graphData = res.dataSets
				myChart.data.datasets = graphData
				myChart.update()
			}
		})
	}

	const graphStock = (ticker) => {
		$.ajax({
			url: "AddStockToGraph",
			data: { ticker },
			success : (res) => {
				var graphData = res.values
				myChart.data.datasets.push({
					label : ticker,
					data : graphData
				})
				myChart.update()
			}

		})
	}

	const uploadCSV = () =>
			$.ajax({
				url: "CSV",
				data: {
					path: document.getElementById("csv-file").files[0].name,
				},
				success: () => location.reload()
			})

</script>
<script>
	// Check for valid NYSE or NASDAQ ticker
	// Todo: Accept API data to actually check valid ticker
	function checkTicker() {
		if(document.getElementById("ticker").value.length === 0) {
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
		if(isNaN(quantity.value) || quantity.value.length === 0 || quantity.value < 1) {
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
		var oneYearAgo = new Date(new Date().setFullYear(new Date().getFullYear() - 1));
		var tomorrow = new Date(new Date().setDate(new Date().getDate() + 1));

		// Adjusting one year ago time for UTC offset
		oneYearAgo.setDate(rightNow.getDate()-1);
		oneYearAgo.setHours(23);
		oneYearAgo.setMinutes(59);
		oneYearAgo.setSeconds(59);
		oneYearAgo.setMilliseconds(999);

		// Adjusting tomorrow's date for UTC offset and making it midnight
		tomorrow.setHours(0);
		tomorrow.setMinutes(0);
		tomorrow.setSeconds(0);
		tomorrow.setMilliseconds(1);

		// Adjusting datePurchased for UTC offset
		datePurchased.setDate(datePurchased.getDate()+1);

		if(datePurchased < oneYearAgo || datePurchased >= tomorrow){
			document.getElementById("one-year-error").style.display = "inline";
			return false;
		} else {
			document.getElementById("one-year-error").style.display = "none";
		}

		if(document.getElementById("date-purchased").value.length === 0) {
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
				if (res.trim() === "1") {
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
		if(document.getElementById("ticker").value.length === 0) {
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

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
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3"></script>
	<script src="https://cdn.jsdelivr.net/npm/hammerjs@2.0.8"></script>
	<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-zoom@0.7.7"></script>

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
				Upload File
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
			<div class="table-responsive">
				<h3>Portfolio Stocks</h3>
				<table id="portfolio-stocks" class="table table-hover">
					<%--filled dynamically--%>
				</table>
				<h3>Historical Stocks</h3>
				<table id="historical-stocks" class="table table-hover">
					<%-- filled dynamically --%>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- Remove Stock Modal -->
<%@include file="partials/removeConfirmModal.jsp"%>

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
			purchased: $("#date-purchased").val(),
			sold	 : $("#date-sold").val(),
			quantity : $("#quantity").val()
		},
		success : () => location.reload()
	})

	const addHistorical = () => $.ajax({
		url : "AddHistorical",
		data : {
			ticker : $("#ticker-view").val()
		},
		success : () => location.reload()
	})

	const loadHistorical = () => {
		$.ajax({
			url: "LoadHistorical",
			success: (res) => $("#historical-stocks").html(res)
		})
	}

	const graphHistorical = (ticker) => $.ajax({
			url: "GraphHistorical",
			data: {
				ticker,
				checked : document.getElementById(ticker + "Historical").checked,
				fromGraph : $("#fromGraph").val(),
				toGraph : $("#toGraph").val()
			},
			success: (res) => {

				if (typeof res == "object") {
					console.log("add dataset")
					console.log(res)
					myChart.data.datasets.push(res)
					myChart.update()
				} else {
					console.log("remove dataset", res)

					let removeIdx;
					var i = 0;
					myChart.data.datasets.forEach((dataset) => {
						if (dataset.label === ticker) {
							removeIdx = i;
						}
						i++;
					})
					console.log("found data set at ", removeIdx);
					myChart.data.datasets.splice(removeIdx, 1);
					myChart.update();

				}
			}
		})

	var ticker_to_be_deleted = ""
	const remove = (t) => {
		console.log("to be deleted ", t)
		$("#ticker_name").text(t)
		ticker_to_be_deleted = t
	}

	const remove_ajax_call = () => {
		console.log("in remove ajax call ", $("#ticker_name").text())
		$.ajax({
			url: "RemoveStock",
			type: "Get",
			data: {
				ticker: $("#ticker_name").text()
			},
			success: () => location.reload()
		})
	}

	const removeHistorical = (ticker) => {
		$.ajax({
			url: "RemoveHistoricalStock",
			type: "Get",
			data: { ticker },
			success: () => location.reload()
		})
	}

	// $("#remove-stock-modal").on('show', (e) => {
	// 	let ticker = $(e.relatedTarget).date('ticker')
	// 	$(e.currentTarget).find('input[name="ticker_tobe_removed"]').val(ticker)
	// })

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

	var ctx = document.getElementById('myChart');
	var myChart = new Chart(ctx, {
		type: 'line',
		data: {
			labels: [],
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
			},
			plugins: {
				zoom: {
					pan: {
						enabled: true,
						mode: 'xy'
					},
					zoom: {
						enabled: true,
						mode: 'xy',
					}
				}
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
					loadHistorical();
				}
			})
	)

	const loadGraph = () => {
	    console.log("calling load graph")
		$.ajax({
			url : "LoadGraph",
			type: "Get",
			data: {
				fromGraph : $("#fromGraph").val(),
				toGraph : $("#toGraph").val()
			},
			success: (res) => {
				myChart.data.datasets[0].data = res.values;
				myChart.data.labels = res.dates.map((d) => new Date(d*1000).toDateString().substring(3, 10));
				myChart.update();
			}
		})
	}

	const uploadCSV = () => $.ajax({
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
					document.getElementById("invalid-ticker").style.display = "none";
					if(qtyCheck && dateCheck) {
						add();
					}
				} else {
					document.getElementById("invalid-ticker").style.display = "inline";

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
			$.ajax({
				url : "TickerChecking",
				type: "Get",
				data : {
					ticker: $("#ticker-view").val()
				},
				success : (res) => {
					var qtyCheck = checkQuantity();
					var dateCheck = checkDates();
					var tickerEmpty = checkTicker();
					if (res.trim() === "1") {
						document.getElementById("invalid-ticker").style.display = "none";
						if(qtyCheck && dateCheck) {
							addHistorical($("#ticker-view").val());
						}
					} else {
						document.getElementById("invalid-ticker").style.display = "inline";

					}
				}
			})
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

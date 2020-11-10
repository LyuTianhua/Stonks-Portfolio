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
	<script src="https://cdn.jsdelivr.net/npm/moment@2.27.0"></script>
	<script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-moment@0.1.1"></script>

</head>
<body>
<%@include file="partials/nav.jsp"%>

<div class="container" onload="checkUpOrDown()">
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
		<div class="btn-group btn-group-toggle" data-toggle="buttons">
  			<label class="btn btn-secondary active">
    		<input type="radio" name="options" id="week" autocomplete="off" onclick="oneWeek()">1 Week
 			</label>
  			<label class="btn btn-secondary">
    		<input type="radio" name="options" id="month" autocomplete="off" onclick="threeMonths()" checked>3 Months
  			</label>
  			<label class="btn btn-secondary">
  			<input type="radio" name="options" id="year" autocomplete="off" onclick="oneYear()">1 Year
  			</label>
		</div>
	</div>
	<div class="text-center">
		<%@include file="partials/portfolioValue.jsp"%>
	</div>
	<div class="row justify-content-center">
		<div class="col-12 col-md-8 mt-3 overflow-auto">
			<div class="table-responsive">
				<h3> Portfolio Stocks </h3>
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


	var fullDatasets = [{}]
	var fullLabels = []

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
				xAxes: [ {
					display: true,
					type: 'time',
					distribution: 'linear',
					time: {
						unit: 'week',
						isoWeekday: true
					}
				}],
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
						mode: 'xy',
						rangeMin: {
							y: 0,
							x: null
						},
						rangeMax: {
							y: 10000,
							x: null
						}
					},
					zoom: {
						enabled: true,
						mode: 'xy',
						rangeMin: {
							y: 0,
							x: null
						},
						rangeMax: {
							y: 10000,
							x: null
						}
					}
				}
			}
		}
	});

	const loadPortfolio = () =>
			$.ajax({
				url: "LoadPortfolio",
				success: (res) => $("#portfolio-stocks").html(res)
			})

	const loadHistorical = () =>
			$.ajax({
				url: "LoadHistorical",
				success: (res) => $("#historical-stocks").html(res)
			})

	var start
	var end
	const loadGraph = () =>
		// checkUpOrDown(); // Updating portfolio value
		$.ajax({
			url: "LoadGraph",
			success: (res) => {
				var data = JSON.parse(res)
				myChart.data.labels = data.timestamps.map( d => new Date( d * 1000 ).getTime())
				myChart.data.datasets = data.datasets
				myChart.update()
				
				var pValue = 0, lValue = 0;
			    var end = data.datasets[0].data.length - 2;
				
				pValue = data.datasets[0].data[end - 1]["y"];
			    lValue = data.datasets[0].data[end]["y"];
			    
			    console.log("pValue: " + pValue);
			    console.log("lValue: " + lValue);
			    
				checkUpOrDown(pValue, lValue); // Updating portfolio value
			}
		})

	const changeDates = () => {
		myChart.options.scales.xAxes[0].ticks.min = $("#fromGraph").val()
		myChart.options.scales.xAxes[0].ticks.max = $("#toGraph").val()
		myChart.update()
	}
	
	const add = () =>
			$.ajax({
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

	const view = () =>
			$.ajax({
				url : "AddHistorical",
				type: "Get",
				data : {
					ticker   : $("#ticker-view").val(),
					purchased: $("#date-purchased-view").val(),
					sold	 : $("#date-sold-view").val(),
					quantity : $("#quantity-view").val()
				},
				success : () => location.reload()
			})

	const remove = (ticker, url) =>
	{
		$("#tickerToBeRemoved").val(ticker)
		$("#urlToRemove").val(url);
	}

	const confirmRemove = () =>
			$.ajax({
				url: $("#urlToRemove").val(),
				data: {
					ticker: $("#tickerToBeRemoved").val(),
				},
				success: () => {
					location.reload()
				}
			})

	const modifyGraph = (ticker, label) => {
		var checked = $("#" + ticker + label).is(":checked")

		if (label === 'Historical') {
			for (var i = 0; i < myChart.data.datasets.length; i++)
				if (myChart.data.datasets[i].label === ticker)
					myChart.data.datasets[i].hidden = !checked
			myChart.update()
		} else {
			$.ajax({
				url: 'ModifyGraph',
				data: {
					ticker
				},
				success: (res) => {
					var data = JSON.parse(res)
					var sign = checked ? 1 : -1

					var oldPortfolio = myChart.data.datasets[0].data

					console.log("before", oldPortfolio)
					for (var i = 0; i < oldPortfolio.length; i++) {
						if (isNaN(oldPortfolio[i])) oldPortfolio[i] = 0
						oldPortfolio[i] += sign * data[i]
						if (oldPortfolio[i] < 1) oldPortfolio[i] = 0
					}
					console.log("after", oldPortfolio)

					myChart.data.datasets[0].data = oldPortfolio
					myChart.update()
				}
			})
		}
	}

	const interval = () => {
		myChart.options.scales.xAxes[0].time.unit = $("#interval").val()
		myChart.update()
	}

	const logout = () =>
			$.ajax({
				url : "Logout",
				type : "Get",
				success : () => window.location.href = "index.jsp"
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

	const uploadCSV = () =>
			$.ajax({
				url: "CSV",
				data: {
					path: document.getElementById("csv-file").files[0].name,
			},
				success: () => location.reload()
			})

	window.addEventListener( "load", () => {
		loadPortfolio()
		loadHistorical()
		loadGraph()
		changeDates()
		idleTimer()
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
	
	//Check date sold before date purchased
	// Todo: add check for date only 1 year in the past
	function checkGraphDates() {
		var datePurchased = new Date(document.getElementById("fromGraph").value);
		var dateSold = new Date(document.getElementById("toGraph").value);
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

		if(datePurchased < oneYearAgo || dateSold >= tomorrow){
			document.getElementById("invalid-dates").style.display = "inline";
			return false;
		} else {
			document.getElementById("invalid-dates").style.display = "none";
		}

		if(document.getElementById("fromGraph").value.length === 0) {
			document.getElementById("empty-from").style.display = "inline";
			return false;
		} else {
			document.getElementById("empty-from").style.display = "none";
		}

		if(document.getElementById("toGraph").value.length > 0){
			if((dateSold - datePurchased) < 0) {
				document.getElementById("empty-to").style.display = "inline";
				return false;
			} else {
				document.getElementById("empty-to").style.display = "none";
			}
		}
		changeDates(); // Potentially change to loadGraph() if issues arise
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
	function checkUpOrDown(pValue, lValue) {
		document.getElementById("portfolio-value-number").textContent = " $" + lValue.toString();
		// Change true to the value of the session variable for portfolio up or down
		if(lValue > pValue) {
			document.getElementById("portfolio-value").style.color = "green";
			document.getElementById("up-arrow").style.display = "inline";
		} else if (lValue < pValue) {
			document.getElementById("portfolio-value").style.color = "red";
			document.getElementById("down-arrow").style.display = "inline";
		} else {
		}
	}
	
	var today = new Date();
	
	function oneWeek() {
		var oneWeekAgo = new Date(today.getTime() - 7*86400000);
		document.getElementById("fromGraph").value = oneWeekAgo.getFullYear().toString() + '-' + (oneWeekAgo.getMonth() + 1).toString().padStart(2, 0) +
	    '-' + oneWeekAgo.getDate().toString().padStart(2, 0);
		document.getElementById("toGraph").value = today.getFullYear().toString() + '-' + (today.getMonth() + 1).toString().padStart(2, 0) +
	    '-' + today.getDate().toString().padStart(2, 0);
		checkGraphDates();
	}
	
	function threeMonths() {
		var earlier = new Date(today);
		earlier.setMonth(earlier.getMonth()-3);
		document.getElementById("fromGraph").value = earlier.getFullYear().toString() + '-' + (earlier.getMonth() + 1).toString().padStart(2, 0) +
	    '-' + earlier.getDate().toString().padStart(2, 0);
		document.getElementById("toGraph").value = today.getFullYear().toString() + '-' + (today.getMonth() + 1).toString().padStart(2, 0) +
	    '-' + today.getDate().toString().padStart(2, 0);
		checkGraphDates();
	}
	
	function oneYear() {
		var earlier = new Date(today);
		earlier.setFullYear(earlier.getFullYear()-1);
		document.getElementById("fromGraph").value = earlier.getFullYear().toString() + '-' + (earlier.getMonth() + 1).toString().padStart(2, 0) +
	    '-' + earlier.getDate().toString().padStart(2, 0);
		document.getElementById("toGraph").value = today.getFullYear().toString() + '-' + (today.getMonth() + 1).toString().padStart(2, 0) +
	    '-' + today.getDate().toString().padStart(2, 0);
		checkGraphDates();
	}
	
	function checkGraphDates() {
		var datePurchased = new Date(document.getElementById("fromGraph").value);
		var dateSold = new Date(document.getElementById("toGraph").value);
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
		if(datePurchased < oneYearAgo || dateSold >= tomorrow){
			document.getElementById("invalid-dates").style.display = "inline";
			return false;
		} else {
			document.getElementById("invalid-dates").style.display = "none";
		}
		if(document.getElementById("fromGraph").value.length === 0) {
			document.getElementById("empty-from").style.display = "inline";
			return false;
		} else {
			document.getElementById("empty-from").style.display = "none";
		}
		if(document.getElementById("toGraph").value.length > 0){
			if((dateSold - datePurchased) < 0) {
				document.getElementById("empty-to").style.display = "inline";
				return false;
			} else {
				document.getElementById("empty-to").style.display = "none";
			}
		}
		loadGraph();
	}
	
	var today = new Date();
	
	function oneWeek() {
		var oneWeekAgo = new Date(today.getTime() - 7*86400000);
		document.getElementById("fromGraph").value = oneWeekAgo.getFullYear().toString() + '-' + (oneWeekAgo.getMonth() + 1).toString().padStart(2, 0) +
	    '-' + oneWeekAgo.getDate().toString().padStart(2, 0);
		document.getElementById("toGraph").value = today.getFullYear().toString() + '-' + (today.getMonth() + 1).toString().padStart(2, 0) +
	    '-' + today.getDate().toString().padStart(2, 0);
		changeDates();
	}
	
	function threeMonths() {
		var earlier = new Date(today);
		earlier.setMonth(earlier.getMonth()-3);
		document.getElementById("fromGraph").value = earlier.getFullYear().toString() + '-' + (earlier.getMonth() + 1).toString().padStart(2, 0) +
	    '-' + earlier.getDate().toString().padStart(2, 0);
		document.getElementById("toGraph").value = today.getFullYear().toString() + '-' + (today.getMonth() + 1).toString().padStart(2, 0) +
	    '-' + today.getDate().toString().padStart(2, 0);
		changeDates();
	}
	
	function oneYear() {
		var earlier = new Date(today);
		earlier.setFullYear(earlier.getFullYear()-1);
		document.getElementById("fromGraph").value = earlier.getFullYear().toString() + '-' + (earlier.getMonth() + 1).toString().padStart(2, 0) +
	    '-' + earlier.getDate().toString().padStart(2, 0);
		document.getElementById("toGraph").value = today.getFullYear().toString() + '-' + (today.getMonth() + 1).toString().padStart(2, 0) +
	    '-' + today.getDate().toString().padStart(2, 0);
		changeDates();
	}


</script>
<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
<script src="https://code.jquery.com/jquery-3.5.0.js" integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>

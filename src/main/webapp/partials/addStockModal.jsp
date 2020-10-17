<!-- Add Stock Modal -->
<div class="modal fade" id="add-stock-modal" tabindex="-1" role="dialog" aria-labelledby="add-stock-modal-label" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="add-stock-modal-label">Add a stock to your portfolio</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="add-stock-form">
		  <div class="form-group">
		    <label for="ticker">Ticker  <span class="text-danger" id="invalid-ticker">Please enter a valid NASDAQ or NYSE ticker.</span></label>
		    <input type="text" class="form-control" id="ticker" placeholder="ex. AAPL" required>
		  </div>
		  <div class="form-group">
		    <label for="quantity">Quantity  <span class="text-danger" id="invalid-quantity">Please enter a valid numerical quantity greater than 0.</span></label>
		    <input type="number" class="form-control" id="quantity" placeholder="ex. 50129" min="0" step="1" required>
		  </div>
		  <div class="form-group">
		    <label for="date-purchased">Date Purchased</label>
		    <input type="date" class="form-control" id="date-purchased" placeholder="ex. 12/24/2019" required>
		  </div>
		  <div class="form-group">
		    <label for="date-purchased">Date Sold  <span class="text-warning">(optional)</span>   <span class="text-danger" id="invalid-date-sold">Please enter a date sold after the date purchased.</span></label>
		    <input type="date" class="form-control" id="date-sold" placeholder="ex. 1/1/2020">
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
        <button id="add-stock-in-modal" type="submit" class="btn btn-primary" form="add-stock-modal-form" value="submit" onclick="checkAddStockForm()">Add Stock</button>
      </div>
    </div>
  </div>
</div>
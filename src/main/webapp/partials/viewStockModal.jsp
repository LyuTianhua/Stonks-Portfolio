<div class="modal fade" id="view-stock-modal" tabindex="-1" role="dialog" aria-labelledby="view-stock-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="view-stock-modal-label">View a stock</h5>
            </div>
            <div class="modal-body">
                <form id="view-stock-form">
                    <div class="form-group">
                        <label for="ticker-view">Ticker <span class="text-danger" id="view-empty">This field is required.</span></label>
                        <input type="text" class="form-control" id="ticker-view" placeholder="ex. AAPL">
                    </div>
		            <div class="form-group">
					    <label for="quantity-view">Quantity <span class="text-danger" id="invalid-quantity">Please enter a valid numerical quantity greater than 0.</span></label>
					    <input type="number" class="form-control" id="quantity-view" placeholder="ex. 50129" min="0" step="1" required>
				  	</div>
				  	<div class="form-group">
				    	<label for="date-purchased-view">Date Purchased <span class="text-danger" id="one-year-error">Please enter a valid date in the past (max 1 year ago from today's date).</span> <span class="text-danger" id="purchased-empty">Please enter a purchase date.</span></label>
				    	<input type="date" class="form-control" id="date-purchased-view" placeholder="ex. 12/24/2019" required>
				  	</div>
				  	<div class="form-group">
				    	<label for="date-sold-view">Date Sold <span class="text-warning">(optional)</span>   <span class="text-danger" id="invalid-date-sold">Please enter a date sold after the date purchased.</span></label>
				    	<input type="date" class="form-control" id="date-sold-view" placeholder="ex. 1/1/2020">
				  </div>
				</form>
            </div>
            <div class="modal-footer">
                <button type="button" id="view-stock-in-modal" class="btn btn-success" value="submit" onclick="view()">View Stock</button>
            	<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>
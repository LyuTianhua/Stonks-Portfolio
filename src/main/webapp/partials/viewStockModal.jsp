<div class="modal fade" id="view-stock-modal" tabindex="-1" role="dialog" aria-labelledby="view-stock-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="view-stock-modal-label">View a stock</h5>
            </div>
            <div class="modal-body">
                <form method="GET">
                    <div class="form-group">
                        <label for="ticker-view">Ticker  <span class="text-danger" id="view-empty">This field is required.</span></label>
                        <input type="text" class="form-control" id="ticker-view" placeholder="ex. AAPL">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" id="view-stock-in-modal" class="btn btn-success" value="submit" onclick="checkViewStockForm()">View Stock</button>
            	<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>
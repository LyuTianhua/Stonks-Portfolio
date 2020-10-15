<div class="modal fade" id="view-stock-modal" tabindex="-1" role="dialog" aria-labelledby="view-stock-modal-label" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="view-stock-modal-label">View a stock</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="view-stock-form" method="GET">
                    <div class="form-group">
                        <label for="ticker1">Ticker</label>
                        <input type="text" class="form-control" id="ticker1" placeholder="ex. AAPL">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="submit" id="view-stock-in-modal" form="view-stock-form" value="submit" class="btn btn-primary">View stock</button>
            </div>
        </div>
    </div>
</div>
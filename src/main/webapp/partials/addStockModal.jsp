<div id="add-stock-modal" class="modal fade" id="add-stock-modal" tabindex="-1" role="dialog" aria-labelledby="add-stock-modal-label" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="add-stock-modal-label">Add a stock to your portfolio</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
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
                        <label for="date-purchased">Date Purchased</label>
                        <input type="date" class="form-control" id="date-purchased" placeholder="ex. 12/24/2019" value="01-01-1970">
                    </div>
                    <button id="add-stock-in-modal" type="submit" form="add-stock-modal-form" value="submit" class="btn btn-primary" onclick="add()">Add Stock</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>

                </form>
            </div>
        </div>
    </div>
</div>

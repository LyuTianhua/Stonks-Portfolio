<!-- Add Stock Modal -->
<div class="modal fade" id="remove-stock-modal" tabindex="-1" role="dialog" aria-labelledby="remove-stock-modal-label" aria-hidden="true">
  <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="remove-stock-modal-label">Confirm</h5>
      </div>
      <div class="modal-body">
        Are you sure you want to remove?
      </div>
      <div class="modal-footer">
          <input type="hidden" id="tickerToBeRemoved" value="">
          <input type="hidden" id="urlToRemove" value="">
          <button id="remove-stock-in-modal" type="button" class="btn btn-success" onclick="confirmRemove()">Delete Stock</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
      </div>
    </div>
  </div>
</div>
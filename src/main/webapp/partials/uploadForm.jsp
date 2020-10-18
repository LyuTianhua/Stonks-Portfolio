<div class="modal fade" id="upload-modal" tabindex="-1" role="dialog" aria-labelledby="upload-modal-label" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="upload-modal-label">Upload a CSV</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="/home.jsp" id="upload-form">
		  <div class="form-group">
		    <label for="csv-file">Choose CSV File  <a class="text-info small-text" href="../resources/example.csv" download>Download an Example CSV</a></label>
		    <input type="file" class="form-control" id="csv-file" accept=".csv,text/csv">
		  </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
        <button type="submit" class="btn btn-primary" form="upload-form">Upload CSV</button>
      </div>
    </div>
  </div>
</div>
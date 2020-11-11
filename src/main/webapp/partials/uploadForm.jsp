<div class="modal fade" id="upload-modal" tabindex="-1" role="dialog" aria-labelledby="upload-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="upload-modal-label">Upload a File</h5>
            </div>
            <div class="modal-body">
                <form action="/home.jsp" id="upload-form">
                    <div class="form-group">
                        <label for="csv-file">Choose a File  <a class="text-info small-text" href="../resources/example.csv" download>Download an Example File</a></label>
                        <input type="file" class="form-control" id="csv-file" accept=".csv,text/csv" required>
                    </div>
                    <div id="uploadCSVError" style="color:red;"/>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" form="upload-form" id="CSV_add_button" onclick="uploadCSV()">Upload File</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>
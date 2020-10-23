<div>
    <canvas id="myChart"  width="400" height="200"></canvas>
    <form class="d-flex justify-content-center" id="formGraph" onchange="changeDuration()">
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="date" name="fromGraph" id="fromGraph" value=365>
            <label class="form-check-label" for="fromGraph">Day</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="date" name="toGraph" id="toGraph" value=0>
            <label class="form-check-label" for="toGraph">Week</label>
        </div>
    </form>
</div>
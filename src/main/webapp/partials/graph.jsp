<div>
    <canvas id="myChart"  width="400" height="200"></canvas>
    <form class="d-flex justify-content-between" id="formGraph" onchange="changeDuration()">
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="radio" id="gDay" value=1>
            <label class="form-check-label" for="gDay">Day</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="radio" id="gWeek" value=7>
            <label class="form-check-label" for="gWeek">Week</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="radio" id="gMonth" value=30>
            <label class="form-check-label" for="gMonth">Month</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="radio" id="gYear" value=365>
            <label class="form-check-label" for="gYear">Year</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="radio" id="g3Years" value=1095>
            <label class="form-check-label" for="g3Years">3 Years</label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="radio" id="g5Years" value=1825>
            <label class="form-check-label" for="g5Years">5 Years</label>
        </div>
    </form>
</div>
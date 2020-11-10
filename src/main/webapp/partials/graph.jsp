<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>

<%

    String now = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

    String year = now.substring(0, now.indexOf("-"));
    String month = now.substring(now.indexOf("-") + 1, now.lastIndexOf("-"));
    String day = now.substring(now.lastIndexOf("-") + 1);

    int yearNumber = Integer.parseInt(year);
    int monthNumber = Integer.parseInt(month);

    int toMonth = monthNumber - 3;

    if (toMonth < 1) {
        toMonth = 12 - toMonth;
        yearNumber--;
    }

    String formattedMonth = toMonth < 10 ? "0" + String.valueOf(toMonth) : String.valueOf(toMonth);
    String threeMonthsAgo = String.valueOf(yearNumber) + "-" + formattedMonth + "-" + day;
%>

<div>
    <canvas id="myChart"  width="400" height="200"></canvas>
    <div class="row d-flex justify-content-center">
            <form class="d-flex justify-content-center d-inline" id="formGraph" onchange="changeDates()">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="date" name="fromGraph" id="fromGraph" value=<%= threeMonthsAgo %>>
                    <label class="form-check-label" for="fromGraph">From</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="date" name="toGraph" id="toGraph" value=<%= now %>>
                    <label class="form-check-label" for="toGraph">To</label>
                </div>
            </form>
            <div class="dropdown d-inline">
                <select id="interval" class="form-control" id="exampleFormControlSelect1" onchange="interval()">
                    <option value="day">Day</option>
                    <option value="week" selected>Week</option>
                    <option value="month">Month</option>
                </select>
            </div>
    </div>


</div>
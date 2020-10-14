
<%  String email = request.getSession().getAttribute("email") == null ? "" : (String) request.getSession().getAttribute("email");   %>

<nav class="navbar navbar-light my-nav">

    <a class="navbar-brand text-wrap" href="#">USC CS 310 Stock Portfolio Management</a>

    <%  if (email.length() > 0) {   %>
        <h3 ><%=email%></h3>
        <button name="logout" id="logout" type="button" class="btn btn-dark float-right" onclick="logout()">Log Out</button>
    <%  }   %>

</nav>
<form id="login-fields">
    <label class="text-left" id="email" for="iEmail">Username</label>
    <input type="email" class="form-control" id="iEmail">
    <label class="text-left" id="password" for="iPassword">Password</label>
    <input type="password" class="form-control" id="iPassword">
    <div class="text-center text-danger">
        <div id="Error-Message"></div>
        <button id="signin" type="button" class="btn btn-primary" onclick="signIn()">Sign In</button>
    </div>
</form>
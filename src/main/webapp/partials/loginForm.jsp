<form id="login-fields">
    <label class="text-left" id="email" for="exampleInputEmail1">Email Address</label>
    <input type="email" class="form-control" id="exampleInputEmail1" name="email">
    <label class="text-left" id="password" for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" name="password">
    <div class="text-center">
        <div id="Error-Message"></div>
        <button id="signin" type="button" class="btn btn-primary" name="signin" onclick="signIn()">Sign In</button>
    </div>
</form>
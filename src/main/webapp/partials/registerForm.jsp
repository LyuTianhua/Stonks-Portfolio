<form id="login-fields">
    <label class="text-left" id="email" for="rEmail">Username</label>
    <input class="form-control" id="rEmail" type="email" required>
    <label class="text-left" id="password" for="rPassword">Password</label>
    <input class="form-control" id="rPassword" type="password" required>
    <label class="text-left" id="VerifyPassword" for="rConfirm">Verify Password</label>
    <input class="form-control" id="rConfirm" type="password" onchange="match()" required>
    <div class="text-center text-danger" id="Error-Message"></div>
    <div class="row">
        <button id="register" type="button" class="btn btn-primary" onclick="registr()">Create User</button>
        <button id="cancel" class="btn btn-primary" type="button" onclick="cancl()">Cancel</button>
    </div>
</form>
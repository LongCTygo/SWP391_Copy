function validatePassword() {
    var password = document.getElementById("password");
    var repeatPassword = document.getElementById("repeat-password");
    var passwordError = document.getElementById("password-error");
    var loginButton = document.getElementById("login-button");

    if (password.value !== repeatPassword.value) {
        passwordError.classList.remove("hidden");
        loginButton.disabled = true;
    } else {
        passwordError.classList.add("hidden");
        loginButton.disabled = false;
    }
}
  function validatePasswordCharacter() {
        var password = document.getElementById("password");
        var passwordError = document.getElementById("password-error-input");
        var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/;

        if (!regex.test(password.value)) {
            passwordError.classList.remove("hidden");
        } else {
            passwordError.classList.add("hidden");
        }
    }
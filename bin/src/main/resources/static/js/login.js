(() => {
    let myStorage = window.localStorage;
    const loginform = document.getElementById("submitLoginForm");
    const signupform = document.getElementById("submitSignupForm");
    const loginSignupSwitch = document.querySelector("#anc-login-signup-switch");
    const loginSignupSwitchLabel = document.querySelector("#label-login-signup-switch");
    const signupFormError = document.querySelector("#error-message");
    const pageHeader = document.querySelector("#header");
    let loginSignupForm = false;
    const loginUser = async (loginInfo) => {
        await fetch(`http://localhost:8082/sessions/authenticate/public`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(loginInfo)
        })
            .then(response => response.json())
            .then(response => {
                if(response.status == 404){
                    alert("Wrong credential");
                    return;
                } else {
                    myStorage.setItem("session-token", response.token);
                    myStorage.setItem("id", response.user.id);
                    goToHomePage()
                    return;
                }
            })
            .catch(error => console.error(error));
    }

    const registerUser = async (registerInfo) => {
        await fetch(`http://localhost:8082/users/public/create`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(registerInfo)
        })
            .then(response => response.json())
            .then(data => {
                myStorage.setItem("session-token", data.token);
                myStorage.setItem("id", data.user.id);
                goToHomePage()
            })
            .catch(error => console.error(error));
    }
    const goToHomePage = () => {
        fetch(`http://localhost:8082/home`)
            .then(response => response.text())
            .then(pagelink => window.location = `${pagelink}`);
    }

    if (myStorage.getItem("session-token")) {
        goToHomePage();
    }

    loginform.addEventListener('submit', event => {
        // submit event detected
        event.preventDefault()
        let usernameInfo = document.querySelector("#username-login").value;
        let passwordInfo = document.querySelector("#password-login").value;

        let loginInfo = {
            username: usernameInfo,
            password: passwordInfo
        }
        loginUser(loginInfo);
    })

    signupform.addEventListener('submit', event => {
        // submit event detected
        event.preventDefault()
        let usernameInfo = document.querySelector("#username-signup").value;
        let passwordInfo = document.querySelector("#password-signup").value;
        let confirmPasswordInfo = document.querySelector("#confirm-password-signup").value;
        let nameInfo = document.querySelector("#name-signup").value;

        if(
            usernameInfo === "" || 
            passwordInfo === "" || 
            confirmPasswordInfo === "" || 
            nameInfo === ""){
                signupFormError.textContent = "Fields missing";
        } else if (passwordInfo === confirmPasswordInfo) {
            let registerInfo = {
                username: usernameInfo,
                name: nameInfo,
                password: passwordInfo
            }
            console.log(registerInfo);
            registerUser(registerInfo);
        } else {
            signupFormError.textContent = "Passwords do not match";
        }
    })

    loginSignupSwitch.onclick = () => {
        loginform.classList.toggle("display-none");
        signupform.classList.toggle("display-none");

        if(loginSignupForm) {
            loginSignupSwitchLabel.textContent = "Not a member?";
            loginSignupSwitch.textContent = "Sign up";
            pageHeader.textContent = "Log in to Choonz";
        } else {
            loginSignupSwitchLabel.textContent = "Already a member?"; 
            loginSignupSwitch.textContent = "Log in";
            pageHeader.textContent = "Sign up to Choonz";
        }
        loginSignupForm = !loginSignupForm;
    }
}
)();
(() => {
    let myStorage = window.localStorage;
    const loginform = document.getElementById("submitLoginForm");
    const loginUser = async (loginInfo) => {
        await fetch(`http://localhost:8082/sessions/authenticate/admin`, {
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
    const goToHomePage = () => {
        fetch(`http://localhost:8082/admindashboard`)
            .then(response => response.text())
            .then(pagelink => window.location = `${pagelink}`);
    }

    if (myStorage.getItem("session-token")) {
        // goToHomePage();
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
}
)();
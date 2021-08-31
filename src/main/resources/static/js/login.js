(() => {
    let myStorage = window.localStorage;
    const form = document.getElementById("submitLoginForm");
    const loginUser = async (loginInfo) => {
       await fetch(`http://localhost:8082/sessions/authenticate`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(loginInfo)
        })
            .then(response => response.json())
            .then(data =>  {myStorage.setItem("session-token", data.token);
                            goToHomePage()})
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

    form.addEventListener('submit', event => {
        // submit event detected
        event.preventDefault()
        let usernameInfo = document.querySelector("#username").value;
        let passwordInfo = document.querySelector("#password").value;

        let loginInfo = {
            username: usernameInfo,
            password: passwordInfo
        }
        loginUser(loginInfo);
            form.addEventListener('submit', event => {
        // submit event detected
        event.preventDefault()
        let usernameInfo = document.querySelector("#username").value;
        let passwordInfo = document.querySelector("#password").value;

        let loginInfo = {
            username: usernameInfo,
            password: passwordInfo
        }
        loginUser(loginInfo);
    })
    })
}
)();
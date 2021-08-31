(() => {

    myStorage = window.localStorage; 

    const form = document.getElementById("submitLoginForm");
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
    const loginUser = (loginInfo) => {
        fetch(`http://localhost:8082/sessions/authenticate`, {
            method: "POST",
            headers: {
              "Content-type": "application/json"
            },
            body: JSON.stringify(loginInfo)
          })
            .then(response => response.json())
            .then(data => myStorage.setItem("token", data.token))
            .then(goToHomePage)
            .catch(error => console.error(error));
      }
      const goToHomePage = () => {
        fetch(`http://localhost:8082/home`)
                .then(response => response.text())
                .then(pagelink => window.location = `${pagelink}`);
      }
}
)();
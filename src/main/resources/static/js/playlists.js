(() => {
  let main = document.querySelector("main");
  let addBtn = document.querySelector(".add");
  let updateBtn = document.querySelector(".update");
  let buttonsAddUpdate = document.querySelector(".buttons-add-update");
  let userIdInput = document.querySelector("#new-user");
  let playlistId;

  const getAllPlaylists = () => {
    fetch(`http://localhost:8082/playlists/read`)
    .then(response => response.json())
    .then(data => displayPlaylist(data))
    .catch(error => console.error(error));
  }
  getAllPlaylists();

  const insertDataOnForm = (data) => {
    let playlistName = document.querySelector("#name");
    let artwork = document.querySelector("#artwork");
    let description = document.querySelector("#description");
    let user = document.querySelector("#user");

    playlistName.value = data.name;
    artwork.value = data.artwork;
    description.value = data.description;
    user.value = data.user.id;
  }

  const fetchPlaylistSingle = (id) => {
    fetch(`http://localhost:8082/playlists/read/${id}`)
      .then((response) => {
          if (response.status !== 200) {
              console.error(`status: ${reponse.status}`);
              return;
          }
          return response.json() 
      })
      .then(data => insertDataOnForm(data))
      .catch((err) => console.error(`${ err }`));
  }

  const getPlaylistIdDropdown = () => {
    playlistId = document.querySelector(".playlist-list-update").value;

    if (playlistId !== "") {
      fetchPlaylistSingle(playlistId);
    } else {
      insertDataOnForm({name: "", description: "", artwork: "", user: {id: ""}})
    }
  }
  
  const loadPlaylist = () => {
    fetch(`http://localhost:8082/playlists/read`)
    .then(response => response.json())
    .then(data => {
      let select = document.querySelector(".playlist-list-update");
      select.addEventListener("change", () => getPlaylistIdDropdown())
      
      for (let i = 0; i < data.length; i++) {
        let option = document.createElement("option");
        option.setAttribute("value", data[i].id);
        option.innerHTML = data[i].name;
        select.appendChild(option);
      }
      })
      .catch(error => console.error(error));
    }
    loadPlaylist();
    
  const goToPlaylistSinglePage = (data, playlistId) => {
    window.location = `${data}?id=${playlistId}`;
  }

  const getPlaylistSinglePage = (playlistId) => {
    fetch(`http://localhost:8082/playlistsingle`)
            .then(response => response.text())
            .then(data => goToPlaylistSinglePage(data, playlistId));
  }

  const addPlaylist = async (playlist, userId) => {
    await fetch(`http://localhost:8082/playlists/create/user/${userId}`, {
      method: "POST",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(playlist)
    })
    .then(response => response.json())
    .then(data => alert("Playlist added!"))
    .catch(error => console.error(error));
    
    location.reload();
  }

  const updatePlaylist = async (playlist, playlistId) => {
    await fetch(`http://localhost:8082/playlists/update/${playlistId}`, {
      method: "PUT",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(playlist)
    })
    .then(response => response.json())
    .then(data => alert("Playlist updated!"))
    .catch(error => console.error(error));
    
    location.reload();
  }

  const deletePlaylist = (playlistId) => {
    fetch(`http://localhost:8082/playlists/delete/${playlistId}`, {
      method: "DELETE",
      headers: {
        "Content-type": "application/json"
      }
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
    
    alert("Playlist deleted!");
    location.reload();
  }

  const displayErrorMessage = (msg) => {
    let alert = document.querySelector("#alert");
    alert.setAttribute("class", "alert alert-danger");
    alert.innerText = msg;
  }

  const displayNoDataMsg = (msg) => {
    let alert = document.querySelector("#alert");
    alert.setAttribute("class", "alert alert-warning");
    alert.innerText = msg;
  }

  const loadUserId = () => {
    if (localStorage.getItem("id") !== null) {
      console.log(localStorage.getItem("id"));
      userIdInput.value = localStorage.getItem("id");
    }
  }
  loadUserId();

  const checkUserIsLoggedIn = () => {
    if (localStorage.getItem("session-token") !== null) {
      let addLink = document.createElement("i");
      addLink.setAttribute("class", "bi bi-plus-circle-fill mb-3 mx-3");
      addLink.setAttribute("type" ,"button");
      addLink.setAttribute("data-bs-toggle", "modal");
      addLink.setAttribute("data-bs-target", "#add-playlist");
      addLink.innerText = " Add new";
      buttonsAddUpdate.appendChild(addLink);
      let editLink = document.createElement("i");
      editLink.setAttribute("class", "bi bi bi-pen-fill mb-3");
      editLink.setAttribute("type" ,"button");
      editLink.setAttribute("data-bs-toggle", "modal");
      editLink.setAttribute("data-bs-target", "#update-playlist");
      editLink.innerText = " Update";
      buttonsAddUpdate.appendChild(editLink);
    } 
  }
  checkUserIsLoggedIn();

  const retrieveUpdateFormDetails = () => {
    let playlistName = document.querySelector("#name").value;
    let artwork = document.querySelector("#artwork").value;
    let description = document.querySelector("#description").value;
    let user = document.querySelector("#user").value;

    if (playlistName !== "" && artwork !== "" && description !== "" && user !== "") {
      let playlist = {
        name: playlistName,
        artwork: artwork,
        description: description
      };
      updatePlaylist(playlist, playlistId);
    } else {
      displayErrorMessage("All fields must be valid!");
    }
  }

  const retrieveAddFormDetails = () => {
    let playlistName = document.querySelector("#new-name").value;
    let artwork = document.querySelector("#new-artwork").value;
    let description = document.querySelector("#new-description").value;
    let user = document.querySelector("#new-user").value;


    if (playlistName !== "" && artwork !== "" && description !== "" && user !== "") {
      let playlist = {
        name: playlistName,
        artwork: artwork,
        description: description
      };
      addPlaylist(playlist, user);
    } else {
      displayErrorMessage("All fields must be valid!");
    }
  }

  const displayPlaylist = (data) => {
    if (data.length > 0) {
      main.setAttribute("class", "row main-section");
      for (let i = 0; i < data.length; i++) {
        console.log(data[i]);
        let card = document.createElement("div");
        card.setAttribute("class", "card p-0");
        card.setAttribute("style", "width: 18rem");
        main.appendChild(card);
        
        let span = document.createElement("span");
        span.addEventListener("click", () => getPlaylistSinglePage(data[i].id));
        let img = document.createElement("img");
        img.setAttribute("src", "https://www.superiorwallpapers.com/download/relaxing-place-for-a-special-summer-holiday-tropical-island-4028x2835.jpg");
        img.setAttribute("class", "card-img-top card-background");
        img.setAttribute("alt", data[i].name);
        span.appendChild(img);
        
        let p = document.createElement("p");
        p.setAttribute("class", "card-text text");
        p.innerText = data[i].name;
        span.appendChild(p);
        card.appendChild(span);
        let deleteIcon = document.createElement("i");
        deleteIcon.setAttribute("class", "bi bi-trash-fill");
        deleteIcon.addEventListener("click", () => deletePlaylist(data[i].id))
        card.appendChild(deleteIcon);
      }
    } else {
      if (localStorage.getItem("session-token") !== null) {
        displayNoDataMsg(":( There are no playlists. But you can start adding new ones now!");
      } else {
        displayNoDataMsg("You need to be an authenticated user to be able to use this page. Please Sign up or Login!");
      }
      main.setAttribute("class", "row");
    } 
  }

  addBtn.addEventListener("click", () => retrieveAddFormDetails());
  updateBtn.addEventListener("click", () => retrieveUpdateFormDetails());
})();
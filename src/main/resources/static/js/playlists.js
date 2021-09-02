(() => {
  let myStorage = window.localStorage;
  let main = document.querySelector("main");
  let addBtn = document.querySelector(".add");
  let updateBtn = document.querySelector(".update");
  let buttonsAddUpdate = document.querySelector(".buttons-add-update");
  let deleteBtn = document.querySelector(".delete");
  let userId;
  let playlistId;

  const getAllPlaylists = () => {
    fetch(`http://localhost:8082/playlists/read`)
    .then(response => response.json())
    .then(data => displayPlaylist(data))
    .catch(error => console.error(error));
  }
  getAllPlaylists();

  const loadUserId = () => {
    if (localStorage.getItem("session-token") !== null) {
      userId = localStorage.getItem("session-token");
    } else {
      userId = "1";
    }
  }
  loadUserId();

  const insertDataOnForm = (data) => {
    console.log(data)
    let playlistName = document.querySelector("#name");
    let description = document.querySelector("#description");

    playlistName.value = data.name;
    description.value = data.description;
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

  const getPlaylistIdDropdown = (action) => {
    if (action === "update") {
      playlistId = document.querySelector(".playlist-list-update").value;
    } else if (action === "delete") {
      playlistId = document.querySelector(".playlist-list-delete").value;
    }

    if (playlistId !== "") {
      fetchPlaylistSingle(playlistId);
    } else {
      insertDataOnForm({ name: "", description: "", artwork: "" });
    }
  }
  
  const loadPlaylistUpdate = () => {
    fetch(`http://localhost:8082/playlists/read`)
    .then(response => response.json())
    .then(data => {
      let select = document.querySelector(".playlist-list-update");
      select.addEventListener("change", () => getPlaylistIdDropdown("update"));
      
      for (let i = 0; i < data.length; i++) {
        let option = document.createElement("option");
        option.setAttribute("value", data[i].id);
        option.innerHTML = data[i].name;
        select.appendChild(option);
      }
      })
      .catch(error => console.error(error));
  }
  loadPlaylistUpdate();
  
  const loadDeletePlaylist = () => {
    fetch(`http://localhost:8082/playlists/read`)
    .then(response => response.json())
    .then(data => {
      let selectDelete = document.querySelector(".playlist-list-delete");
      selectDelete.addEventListener("change", () => getPlaylistIdDropdown("delete"));
      
      for (let i = 0; i < data.length; i++) {
        let option = document.createElement("option");
        option.setAttribute("value", data[i].id);
        option.innerHTML = data[i].name;
        selectDelete.appendChild(option);
      }
      })
      .catch(error => console.error(error));
  }
  loadDeletePlaylist();
    
  const goToPlaylistSinglePage = (data, playlistId) => {
    window.location = `${data}?id=${playlistId}`;
  }

  const getPlaylistSinglePage = (playlistId) => {
    fetch(`http://localhost:8082/playlistsingle`)
            .then(response => response.text())
            .then(data => goToPlaylistSinglePage(data, playlistId));
  }

  const addPlaylist = async (formData, userId) => {
    await fetch(`http://localhost:8082/playlists/create/user/${userId}`, {
      method: 'POST',
      body: formData
    })
    .then(resp => resp.json())
    .then(data => {
      if (data.errors) {
        alert(data.errors)
      }
      else {
        console.log(data)
        alert("Playlist added!");
      }
    })
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
      let removeLink = document.createElement("i");
      removeLink.setAttribute("class", "bi bi-trash-fill mb-3 mx-3");
      removeLink.setAttribute("type" ,"button");
      removeLink.setAttribute("data-bs-toggle", "modal");
      removeLink.setAttribute("data-bs-target", "#delete-playlist");
      removeLink.innerText = " Delete";
      buttonsAddUpdate.appendChild(removeLink);
    } 
  }
  checkUserIsLoggedIn();

  const retrieveUpdateFormDetails = () => {
    let playlistName = document.querySelector("#name").value;
    let description = document.querySelector("#description").value;

    if (playlistName !== "" && description !== "") {
      let playlist = {
        name: playlistName,
        description: description
      };
      updatePlaylist(playlist, playlistId);
    } else {
      displayErrorMessage("All fields must be valid!");
    }
  }

  const retrieveAddFormDetails = () => {
    let playlistName = document.querySelector("#new-name").value;
    let description = document.querySelector("#new-description").value;
    let artwork = document.querySelector("#new-image").files[0];

    if (playlistName !== "" && artwork !== "" && description !== "" && artwork !== "") {
      let formData = new FormData();
      formData.append('file', artwork);
      formData.append('name', playlistName);
      formData.append('description', description);
      formData.append('token', myStorage.getItem("session-token"));
      addPlaylist(formData, userId);
    } else {
      displayErrorMessage("All fields must be valid!");
    }
  }

  const displayPlaylist = (data) => {
    if (data.length > 0) {
      main.setAttribute("class", "row main-section");
      for (let i = 0; i < data.length; i++) {
        let card = document.createElement("div");
        card.setAttribute("class", "card p-0");
        card.setAttribute("style", "width: 13rem");

        let img = document.createElement("img");
        img.setAttribute("alt", data[i].artwork.name);
        img.setAttribute("src", "data:image/" + data[i].artwork.type + ";base64," + data[i].artwork.picByte);
        img.setAttribute("class", "card-img-top card-background");
        card.appendChild(img);
        
        let p = document.createElement("p");
        p.setAttribute("class", "card-text text");
        p.innerText = data[i].name;
        card.appendChild(p);
        card.addEventListener("click", () => getPlaylistSinglePage(data[i].id));
        main.appendChild(card);
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
  deleteBtn.addEventListener("click", () => deletePlaylist(playlistId));
})();
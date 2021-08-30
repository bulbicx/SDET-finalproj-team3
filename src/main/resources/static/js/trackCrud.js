(() => {
  let addBtn = document.querySelector(".add");
  let updateBtn = document.querySelector(".update");
  let trackId;

  const buildTable = (data) => {
    let container = document.querySelector(".box-pages");
    let table = document.createElement("table");
    table.setAttribute("id", "myTable");
    table.setAttribute("class", "table table-striped")
    container.appendChild(table);
    let thead = document.createElement("thead");
    table.appendChild(thead);
    let trHead = document.createElement("tr");
    thead.appendChild(trHead);
    let thId = document.createElement("th");
    thId.innerText = "Track ID";
    trHead.appendChild(thId);
    let thName = document.createElement("th");
    thName.innerText = "Track name";
    trHead.appendChild(thName);
    let thDuration = document.createElement("th");
    thDuration.innerText = "Duration";
    trHead.appendChild(thDuration);
    let thAlbum = document.createElement("th");
    thAlbum.innerText = "Album";
    trHead.appendChild(thAlbum);
    let thAction = document.createElement("th");
    thAction.innerText = "Action";
    trHead.appendChild(thAction);
    let tbody = document.createElement("tbody");
    table.appendChild(tbody);
    if (data.length < 1) {
      displayNoDataMsg("There is no data")
      return;
    }
    for (let i = 0; i < data.length; i++) {
      let tr = document.createElement("tr");
      tbody.appendChild(tr);
      let idTd = document.createElement("td");
      idTd.innerText = data[i].id
      tr.appendChild(idTd);
      let nameTd = document.createElement("td");
      nameTd.innerText = data[i].name
      tr.appendChild(nameTd);
      let durationTd = document.createElement("td");
      durationTd.innerText = data[i].duration
      tr.appendChild(durationTd);
      let albumTd = document.createElement("td");
      albumTd.innerText = data[i].album.name
      tr.appendChild(albumTd);
      let actionTd = document.createElement("td");
      actionTd.setAttribute("style", "width: 7em; text-align: center;");

      let editIcon = document.createElement("i");
      editIcon.setAttribute("class", "bi bi-pen-fill");
      editIcon.setAttribute("type", "button");
      editIcon.setAttribute("data-bs-toggle", "modal");
      editIcon.setAttribute("data-bs-target", "#update-track");
      editIcon.addEventListener("click", () => retrieveOneTrack(data[i].id));
      let deleteIcon = document.createElement("i");
      deleteIcon.setAttribute("class", "bi bi-trash-fill");
      deleteIcon.setAttribute("type", "button");
      deleteIcon.setAttribute("data-bs-toggle", "modal");
      deleteIcon.setAttribute("data-bs-target", "#delete-track");
      deleteIcon.addEventListener("click", () => deleteTrack(data[i].id))
      actionTd.appendChild(editIcon);
      actionTd.appendChild(deleteIcon);
      tr.appendChild(actionTd);
    }
  }

  const loadAlbumsDropdown = (data, action) => {
    let select;
    if (action === "add") {
      select = document.querySelector(".album-list-add");

    } else if (action === "update") {
      select = document.querySelector(".album-list-update");
    }

    for (let i = 0; i < data.length; i++) {
      let option = document.createElement("option");
      option.setAttribute("value", data[i].id);
      option.innerHTML = data[i].name;
      select.appendChild(option);
    }
  }

  const getAllTracks = () => {
    fetch(`http://localhost:8082/tracks/read`)
    .then(response => response.json())
    .then(data => buildTable(data))
    .catch(error => console.error(error));
  }
  getAllTracks();

  const getAllAlbums = (action) => {
    fetch(`http://localhost:8082/albums/read`)
    .then(response => response.json())
    .then(data => loadAlbumsDropdown(data, action))
    .catch(error => console.error(error));
  }
  getAllAlbums("update");
  getAllAlbums("add");

  const displayTrackUpdate = (data) => {
    let trackName = document.querySelector("#name");
    let duration = document.querySelector("#duration");
    let lyric = document.querySelector("#lyric");
    let album = document.querySelector("#album");
    trackName.value = data.name;
    duration.value = data.duration;
    lyric.value = data.lyrics;
    album.value = data.album.id;
    trackId = data.id;
  }

  const retrieveOneTrack = (trackId) => {
    fetch(`http://localhost:8082/tracks/read/${trackId}`)
    .then(response => response.json())
    .then(data => displayTrackUpdate(data))
    .catch(error => console.error(error));
  }
  
  const addTrack = async (track, albumId) => {
    await fetch(`http://localhost:8082/tracks/create/album/${albumId}`, {
      method: "POST",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(track)
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
    
    location.reload();
  }

  const updateTrack = async (track, albumId) => {
    await fetch(`http://localhost:8082/tracks/update/${trackId}/album/${albumId}`, {
      method: "PUT",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(track)
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
    
    location.reload();
  }

  const deleteTrack = (trackId) => {
    fetch(`http://localhost:8082/tracks/delete/${trackId}`, {
      method: "DELETE",
      headers: {
        "Content-type": "application/json"
      }
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
    
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

  const retrieveUpdateFormDetails = () => {
    let trackName = document.querySelector("#name").value;
    let duration = document.querySelector("#duration").value;
    let lyric = document.querySelector("#lyric").value;
    let album = document.querySelector("#album").value;

    if (trackName !== "" && duration !== "" && lyric !== "" && album !== "") {
      let track = {
        name: trackName,
        duration: duration,
        lyrics: lyric
      };
      updateTrack(track, album);
    } else {
      displayErrorMessage("All fields must be valid!");
    }
  }

  const retrieveAddFormDetails = () => {
    let trackName = document.querySelector("#new-name").value;
    let duration = document.querySelector("#new-duration").value;
    let lyric = document.querySelector("#new-lyric").value;
    let album = document.querySelector("#new-album").value;

    if (trackName !== "" && duration !== "" && lyric !== "" && album !== "") {
      let track = {
        name: trackName,
        duration: duration,
        lyrics: lyric
      };
      addTrack(track, album);
    } else {
      displayErrorMessage("All fields must be valid!");
    }
  }

  addBtn.addEventListener("click", () => retrieveAddFormDetails());
  updateBtn.addEventListener("click", () => retrieveUpdateFormDetails());
})();
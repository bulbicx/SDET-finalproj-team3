(() => {
  let addBtn = document.querySelector(".add");
  let updateBtn = document.querySelector(".update");
  let albumId;

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
    thId.innerText = "Album ID";
    trHead.appendChild(thId);
    let thName = document.createElement("th");
    thName.innerText = "Album name";
    trHead.appendChild(thName);
    let thCover = document.createElement("th");
    thCover.innerText = "Cover";
    trHead.appendChild(thCover);
    let thGenre = document.createElement("th");
    thGenre.innerText = "Genre";
    trHead.appendChild(thGenre);
    let thArtist = document.createElement("th");
    thArtist.innerText = "Artist";
    trHead.appendChild(thArtist);
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
      let coverTd = document.createElement("td");
      let img = document.createElement("img");
      img.setAttribute("class", "img-cover");
      img.setAttribute("alt", data[i].name);
      img.setAttribute("src", data[i].cover);
      coverTd.appendChild(img);
      tr.appendChild(coverTd);
      let genreTd = document.createElement("td");
      genreTd.innerText = data[i].genre.name
      tr.appendChild(genreTd);
      let artistTd = document.createElement("td");
      artistTd.innerText = data[i].artist.name
      tr.appendChild(artistTd);
      let actionTd = document.createElement("td");
      actionTd.setAttribute("style", "width: 7em; text-align: center;");

      let editIcon = document.createElement("i");
      editIcon.setAttribute("class", "bi bi-pen-fill");
      editIcon.setAttribute("type", "button");
      editIcon.setAttribute("data-bs-toggle", "modal");
      editIcon.setAttribute("data-bs-target", "#update-album");
      editIcon.addEventListener("click", () => retrieveOneAlbum(data[i].id));
      let deleteIcon = document.createElement("i");
      deleteIcon.setAttribute("class", "bi bi-trash-fill");
      deleteIcon.setAttribute("type", "button");
      deleteIcon.setAttribute("data-bs-toggle", "modal");
      deleteIcon.setAttribute("data-bs-target", "#delete-album");
      deleteIcon.addEventListener("click", () => deleteAlbum(data[i].id))
      actionTd.appendChild(editIcon);
      actionTd.appendChild(deleteIcon);
      tr.appendChild(actionTd);
    }
  }

  const loadGenresDropdown = (data, action) => {
    let select;
    if (action === "add") {
      select = document.querySelector(".genre-list-add");

    } else if (action === "update") {
      select = document.querySelector(".genre-list-update");
    }

    for (let i = 0; i < data.length; i++) {
      let option = document.createElement("option");
      option.setAttribute("value", data[i].id);
      option.innerHTML = data[i].name;
      select.appendChild(option);
    }
  }

  const loadArtistsDropdown = (data, action) => {
    let select;
    if (action === "add") {
      select = document.querySelector(".artist-list-add");

    } else if (action === "update") {
      select = document.querySelector(".artist-list-update");
    }

    for (let i = 0; i < data.length; i++) {
      let option = document.createElement("option");
      option.setAttribute("value", data[i].id);
      option.innerHTML = data[i].name;
      select.appendChild(option);
    }
  }

  const getAllAlbums = () => {
    fetch(`http://localhost:8082/albums/read`)
    .then(response => response.json())
    .then(data => buildTable(data))
    .catch(error => console.error(error));
  }
  getAllAlbums();

  const getAllGenres = (action) => {
    fetch(`http://localhost:8082/genres/read`)
    .then(response => response.json())
    .then(data => loadGenresDropdown(data, action))
    .catch(error => console.error(error));
  }

  const getAllArtists = (action) => {
    fetch(`http://localhost:8082/artists/read`)
    .then(response => response.json())
    .then(data => loadArtistsDropdown(data, action))
    .catch(error => console.error(error));
  }

  const loadDropdowns = (action) => {
    getAllGenres(action);
    getAllArtists(action);
  }
  loadDropdowns("update");
  loadDropdowns("add");

  const displayAlbumUpdate = (data) => {
    let albumName = document.querySelector("#name");
    let cover = document.querySelector("#cover");
    let artist = document.querySelector("#artist");
    let genre = document.querySelector("#genre");
    albumName.value = data.name;
    cover.value = data.cover;
    artist.value = data.artist.id;
    genre.value = data.genre.id;
    albumId = data.id;
  }

  const retrieveOneAlbum = (albumId) => {
    fetch(`http://localhost:8082/albums/read/${albumId}`)
    .then(response => response.json())
    .then(data => displayAlbumUpdate(data))
    .catch(error => console.error(error));
  }
  
  const addAlbum = async (album, artistId, genreId) => {
    await fetch(`http://localhost:8082/albums/create/artist/${ artistId }/genre/${ genreId }`, {
      method: "POST",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(album)
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .then(alert("New Album added!"))
    .catch(error => console.error(error));
    
    location.reload();
  }

  const updateAlbum = async (album, artistId, genreId) => {
    await fetch(`http://localhost:8082/albums/update/${albumId}/${artistId}/${genreId}`, {
      method: "PUT",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(album)
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
    
    location.reload();
  }

  const deleteAlbum = (albumId) => {
    fetch(`http://localhost:8082/albums/delete/${albumId}`, {
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
    let albumName = document.querySelector("#name").value;
    let cover = document.querySelector("#cover").value;
    let artist = document.querySelector("#artist").value;
    let genre = document.querySelector("#genre").value;

    if (albumName !== "" && cover !== "" && artist !== "" && genre !== "") {
      let album = {
        name: albumName,
        cover: cover
      };
      updateAlbum(album, artist, genre);
    } else {
      displayErrorMessage("All fields must be valid!");
    }
  }

  const retrieveAddFormDetails = () => {
    let albumName = document.querySelector("#new-name").value;
    let cover = document.querySelector("#new-cover").value;
    let artist = document.querySelector("#new-artist").value;
    let genre = document.querySelector("#new-genre").value;
    
    if (albumName !== "" && cover !== "" && artist !== "" && genre !== "") {
      let album = {
        name: albumName,
        cover: cover
      };
      addAlbum(album, artist, genre);
    } else {
      displayErrorMessage("All fields must be valid!");
    }
  }

  addBtn.addEventListener("click", () => retrieveAddFormDetails());
  updateBtn.addEventListener("click", () => retrieveUpdateFormDetails());
})();
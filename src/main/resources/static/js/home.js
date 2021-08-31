(() => {
  let groupSection = document.querySelector(".group-section");
  let addPlaylistBtn = document.querySelector(".add");
  let updatePlaylistBtn = document.querySelector(".update");
  let deletePlaylistBtn = document.querySelector(".delete");
  let playlistId;

  //Build request to just limit query result on 5

  // Playlist
  const getAllPlaylists = () => {
    fetch(`http://localhost:8082/playlists/read`)
    .then(response => response.json())
    .then(data => displaySection(data, "Playlist"))
    .catch(error => console.error(error));
  }

  //This method is used to retrieve a single playlist
  //and output it on form data to edit it
  const fetchPlaylistSingle = (id, action) => {
    fetch(`http://localhost:8082/playlists/read/${id}`)
      .then((response) => {
          if (response.status !== 200) {
              console.error(`status: ${reponse.status}`);
              return;
          }
          return response.json() 
      })
      .then(data => {
        if (action === "put") {
          insertDataOnForm(data)
        } else if (action === "delete") {
          playlistId = id;
        }
      })
      .catch((err) => console.error(`${ err }`));
  }

  //this method is used to retrieve playlists and put them
  //into a dropdown when editing a playlist
  const getListPlaylist = (action) => {
    fetch(`http://localhost:8082/playlists/read`)
    .then(response => response.json())
    .then(data =>  loadPlaylistOnDropdown(data, action))
    .catch(error => console.error(error));
  }

  const goToPlaylistSinglePage = (data, playlistId) => {
    window.location = `${data}?id=${playlistId}`;
  }

  const getPlaylistSinglePage = (playlistId) => {
    fetch(`http://localhost:8082/playlistsingle`)
            .then(response => response.text())
            .then(data => goToPlaylistSinglePage(data, playlistId));
  }

  // Albums
  const getAllAlbums = () => {
    fetch(`http://localhost:8082/albums/read`)
    .then(response => response.json())
    .then(data => displaySection(data, "Albums"))
    .catch(error => console.error(error));
  }

  const goToAlbumSinglePage = (data, albumId) => {
    window.location = `${data}?id=${albumId}`;
  }

  const getAlbumSinglePage = (albumId) => {
    fetch(`http://localhost:8082/albumsingle`)
            .then(response => response.text())
            .then(data => goToAlbumSinglePage(data, albumId));
  }

  // Tracks
  const getAllTracks = () => {
    fetch(`http://localhost:8082/tracks/read`)
    .then(response => response.json())
    .then(data => displaySection(data, "Tracks"))
    .catch(error => console.error(error));
  }

  const goToTrackSinglePage = (data, trackId) => {
    window.location = `${data}?id=${trackId}`;
  }

  const getTrackSinglePage = (trackId) => {
    fetch(`http://localhost:8082/track`)
      .then(response => response.text())
      .then(data => goToTrackSinglePage(data, trackId));
  }

  // Artists
  const getAllArtists = () => {
    fetch(`http://localhost:8082/artists/read`)
    .then(response => response.json())
    .then(data => displaySection(data, "Artists"))
    .catch(error => console.error(error));
  }

  const goToArtistSinglePage = (data, artistId) => {
    window.location = `${data}?id=${artistId}`;
  }

  const getArtistSinglePage = (artistId) => {
    fetch(`http://localhost:8082/artistsingle`)
      .then(response => response.text())
      .then(data => goToArtistSinglePage(data, artistId));
  }

  // Genres
  const getAllGenres = () => {
    fetch(`http://localhost:8082/genres/read`)
    .then(response => response.json())
    .then(data => displaySection(data, "Genres"))
    .catch(error => console.error(error));
  }

  const goToGenreSinglePage = (data, genreId) => {
    window.location = `${data}?id=${genreId}`;
  }

  const getGenreSinglePage = (genreId) => {
    fetch(`http://localhost:8082/genresingle`)
      .then(response => response.text())
      .then(data => goToGenreSinglePage(data, genreId));
  }

  const getSectionTitle = (sectionType) => {
    return sectionType + " for you";
  }

  const getActionType = (sectionName, data) => {
    if (sectionName === "Playlist") {
      getPlaylistSinglePage(data.id);
      return;
    } else if (sectionName === "Albums") {
      getAlbumSinglePage(data.id);
      return;
    } else if (sectionName === "Tracks") {
      getTrackSinglePage(data.id);
      return;
    } else if (sectionName === "Artists") {
      getArtistSinglePage(data.id);
      return;
    } else if (sectionName === "Genres") {
      getGenreSinglePage(data.id);
      return;
    }
  }

  const goToPlaylistsPage = () => {
    fetch(`http://localhost:8082/playlist`)
            .then(response => response.text())
            .then(window.location = `playlists.html`);
  }

  const retrieveAddFormDetails = () => {
    let playlistName = document.querySelector("#new-name").value;
    let description = document.querySelector("#new-description").value;
    let userId = document.querySelector("#new-user").value;
    let artworkImg = document.querySelector("#new-artwork").value;
    let playlist = {
      artwork: artworkImg,
      description: description,
      name: playlistName
    }
    
    postPlaylist(playlist, userId);
  }

  const retrieveEditFormDetails = () => {
    let playlistName = document.querySelector("#name").value;
    let description = document.querySelector("#description").value;
    let artworkImg = document.querySelector("#artwork").value;
    let playlistId = document.querySelector("#playlist-list-update").value;

    let playlist = {
      artwork: artworkImg,
      description: description,
      name: playlistName
    }
    
    updatePlaylist(playlist, playlistId);
  }

  
  const getPlaylistIdDropdown = (action) => {
    let id = document.querySelector("#playlist-list-update").value;
    fetchPlaylistSingle(id, action);
  }

  const getPlaylistIdDropdownDelete = (action) => {
    let playlistId = document.querySelector("#playlist-list-delete").value;
    fetchPlaylistSingle(playlistId, action);
  }
  
  const insertDataOnForm = (data) => {
    let name = document.querySelector("#name");
    let description = document.querySelector("#description");
    let artwork = document.querySelector("#artwork");

    name.value = data.name;
    description.value = data.description;
    artwork.value = data.artwork;
  }

  const loadPlaylistOnDropdown = (data, action) => {
    let modalBody;
    let select = document.createElement("select");
    select.setAttribute("class", "form-select");
    if (action === "put") {
      modalBody = document.querySelector(".dropdown-list-update");
      select.setAttribute("id", "playlist-list-update");
      select.addEventListener("change", () => getPlaylistIdDropdown(action));

    } else if (action === "delete") {
      modalBody = document.querySelector(".dropdown-list-delete");
      select.setAttribute("id", "playlist-list-delete");
      select.addEventListener("change", () => getPlaylistIdDropdownDelete(action));
    }
    
    modalBody.appendChild(select);
    let option = document.createElement("option");
    option.setAttribute("value", "");
    option.innerHTML = "--Select an option--";
    select.appendChild(option);

    for (let i = 0; i < data.length; i++) {
      let option = document.createElement("option");
      option.setAttribute("value", data[i].id);
      option.innerHTML = data[i].name;
      select.appendChild(option);
    }
  }

  const deletePlaylist = async (playlistId) => {
    
    await fetch(`http://localhost:8082/playlists/delete/${ playlistId }`, {
      method: "DELETE",
      headers: {
        "Content-type": "application/json"
      }
    })
      .then(response => response.json())
      .then(data => console.log(data))
      .catch(error => console.error(error));
    // window.location.reload(true);
    location.reload();
  }

  const postPlaylist = async (playlist, userId) => {
    await fetch(`http://localhost:8082/playlists/create/user/${ userId }`, {
      method: "POST",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(playlist)
    })
      .then(response => response.json())
      .then(data => console.log(data))
      .catch(error => console.error(error));
    // window.location.reload(true);
    location.reload();
  }

  const updatePlaylist = async (playlist, playlistId) => {
    await fetch(`http://localhost:8082/playlists/update/${ playlistId }`, {
      method: "PUT",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(playlist)
    })
      .then(response => response.json())
      .then(data => console.log(data))
      .catch(error => console.error(error));
      location.reload();
  }

  const displaySection = (data, sectionType) => {
    let homeSection = document.createElement("div");
    homeSection.setAttribute("class", "row home-section");
    groupSection.appendChild(homeSection);

    let sectionHeader = document.createElement("div");
    sectionHeader.setAttribute("class", "section-header");
    homeSection.appendChild(sectionHeader);

    let titleHomeSection = document.createElement("h4");
    titleHomeSection.setAttribute("class", "title-home-section");
    titleHomeSection.innerText = getSectionTitle(sectionType);
    sectionHeader.appendChild(titleHomeSection);

    if (sectionType.toLowerCase() === "playlist") {
      let iconSection = document.createElement("span");
      iconSection.setAttribute("class", "icon-section")
      sectionHeader.appendChild(iconSection);
      let plusIcon = document.createElement("i");
      plusIcon.setAttribute("class", "bi bi-plus-circle-fill");
      plusIcon.setAttribute("type", "button");
      plusIcon.setAttribute("data-bs-toggle", "modal");
      plusIcon.setAttribute("data-bs-target", "#add-playlist");
      let editIcon = document.createElement("i");
      editIcon.setAttribute("class", "bi bi-pen-fill");
      editIcon.setAttribute("type", "button");
      editIcon.setAttribute("data-bs-toggle", "modal");
      editIcon.setAttribute("data-bs-target", "#edit-playlist");
      editIcon.addEventListener("click", () => getListPlaylist("put"));
      let deleteIcon = document.createElement("i");
      deleteIcon.setAttribute("class", "bi bi-trash-fill");
      deleteIcon.setAttribute("type", "button");
      deleteIcon.setAttribute("data-bs-toggle", "modal");
      deleteIcon.setAttribute("data-bs-target", "#delete-playlist");
      deleteIcon.addEventListener("click", () => getListPlaylist("delete"));
      let seeMoreIcon = document.createElement("i");
      seeMoreIcon.setAttribute("class", "bi bi-eye-fill");
      seeMoreIcon.addEventListener("click", () => goToPlaylistsPage());
      iconSection.appendChild(plusIcon);
      iconSection.appendChild(editIcon);
      iconSection.appendChild(deleteIcon);
      iconSection.appendChild(seeMoreIcon);
    }

    if (data.length > 0) {
      let cardGroup = document.createElement("div");
      cardGroup.setAttribute("class", "row");
      cardGroup.setAttribute("id", "card-group");
      homeSection.appendChild(cardGroup);
      for (let i = 0; i < data.length; i++) {

        let cardBox = document.createElement("div");
        cardBox.setAttribute("class", "card-box");
        cardGroup.appendChild(cardBox);

        let card = document.createElement("div");
        card.setAttribute("class", "card");
        cardBox.appendChild(card);

        let img = document.createElement("img");
        img.setAttribute("class", "card-img-top");
        img.setAttribute("src", "https://cdn.pixabay.com/photo/2015/08/10/21/26/vinyl-883199_960_720.png");
        img.setAttribute("alt", "cover " + data[i].id);
        card.appendChild(img);

        let titleCardSection = document.createElement("div") 
        titleCardSection.setAttribute("class", "title-section");
        cardBox.appendChild(titleCardSection);

        let titleCard = document.createElement("p");
        titleCard.setAttribute("class", "title-card");
        titleCard.innerText = data[i].name;
        titleCardSection.appendChild(titleCard);

        card.addEventListener("click", () => getActionType(sectionType, data[i]));
        }
    } else {
      let pText = document.createElement("p");
      pText.setAttribute("class", "no-data-found");
      let noTextFound = `:( There are no ${ sectionType.toLowerCase() } yet, but you can start adding new ones now!`;
      pText.innerHTML = noTextFound;
      homeSection.appendChild(pText);
    } 
  }

  addPlaylistBtn.addEventListener("click", () => retrieveAddFormDetails());
  updatePlaylistBtn.addEventListener("click", () => retrieveEditFormDetails());
  deletePlaylistBtn.addEventListener("click", () => deletePlaylist(playlistId));

  getAllPlaylists();
  getAllAlbums();
  getAllArtists();
  getAllTracks();
  getAllGenres()
})();
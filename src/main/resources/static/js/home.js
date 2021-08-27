(() => {
  let groupSection = document.querySelector(".group-section");
  //Build request to just limit query result on 5

  // Playlist
  const getAllPlaylists = () => {
    fetch(`http://localhost:8082/playlists/read`)
    .then(response => response.json())
    .then(data => displaySection(data, "Playlist"))
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
    console.log(data)
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

  const displaySection = (data, sectionType) => {
    if (data.length > 0) {
      for (let i = 0; i < data.length; i++) {
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

        // <i class="bi bi-plus-circle-fill"></i>
        // <i class="bi bi-pen-fill"></i>
        // <i class="bi bi-trash-fill"></i>
        // <i class="bi bi-eye-fill"></i>
        // type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop"
        let iconSection = document.createElement("span");
        iconSection.setAttribute("class", "icon-section")
        sectionHeader.appendChild(iconSection);
        let plusIcon = document.createElement("i");
        plusIcon.setAttribute("class", "bi bi-plus-circle-fill");
        plusIcon.setAttribute("type", "button");
        plusIcon.setAttribute("data-bs-toggle", "modal");
        plusIcon.setAttribute("data-bs-target", "#staticBackdrop");
        let editIcon = document.createElement("i");
        editIcon.setAttribute("class", "bi bi-pen-fill");
        let deleteIcon = document.createElement("i");
        deleteIcon.setAttribute("class", "bi bi-trash-fill");
        let seeMoreIcon = document.createElement("i");
        seeMoreIcon.setAttribute("class", "bi bi-eye-fill");
        iconSection.appendChild(plusIcon);
        iconSection.appendChild(editIcon);
        iconSection.appendChild(deleteIcon);
        iconSection.appendChild(seeMoreIcon);

        let cardGroup = document.createElement("div");
        cardGroup.setAttribute("class", "row");
        cardGroup.setAttribute("id", "card-group");
        homeSection.appendChild(cardGroup);

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
      let homeSection = document.createElement("div");
      homeSection.setAttribute("class", "row home-section");
      groupSection.appendChild(homeSection);
      let pText = document.createElement("p");
      pText.setAttribute("class", "no-data-found");
      let noTextFound = `:( There are no ${ sectionType.toLowerCase() } yet, but you can start adding new ones now!`;
      pText.innerHTML = noTextFound;
      homeSection.appendChild(pText);
    } 
  }

  getAllPlaylists();
  getAllAlbums();
  getAllArtists();
  getAllTracks();
  getAllGenres()
})();
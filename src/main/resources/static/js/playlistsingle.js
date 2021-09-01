(() => {
  const urlParams = new URLSearchParams(window.location.search);
  const myParam = urlParams.get('id');
  let playlistContainer = document.querySelector(".playlist-container");
  let addBtn = document.querySelector(".add");

  const removeTrackFromPlaylist = async (trackId) => {
    await fetch(`http://localhost:8082/playlists/${myParam}/removeTrack/${trackId}`, {
      method: "PUT",
      headers: {
        "Content-type": "application/json"
      }
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
    
    alert("Track deleted!");
    location.reload();
  }

  const buildHeaderPage = (name, description) => {
    let header = document.createElement("header");
    header.setAttribute("id", "header-page");
    let h2 = document.createElement("h2");
    h2.setAttribute("id", "title-page");
    h2.innerText = name;
    header.appendChild(h2);
    let p = document.createElement("p");
    p.setAttribute("id", "playlist-description");
    p.innerText = description;
    header.appendChild(p);
    playlistContainer.appendChild(header);
  }

  const buildTracksList = (tracks) => {
    let trackList = document.createElement("section");
    trackList.setAttribute("class", "track-list");
    playlistContainer.appendChild(trackList);
    
    let header = document.createElement("header");
    header.setAttribute("class", "header-tracks");
    trackList.appendChild(header);
    let tracksTitle = document.createElement("h3");
    tracksTitle.setAttribute("class", "tracks-title");
    tracksTitle.innerText = "List of tracks";
    header.appendChild(tracksTitle);
    let plusIcon = document.createElement("i");
    plusIcon.setAttribute("class", "bi bi-plus-circle-fill bi-track");
    plusIcon.setAttribute("type", "button");
    plusIcon.setAttribute("data-bs-toggle", "modal");
    plusIcon.setAttribute("data-bs-target", "#add-track");
    plusIcon.innerText = " Add track";
    header.appendChild(plusIcon);

    let section = document.createElement("section");
    section.setAttribute("class", "row");
    trackList.appendChild(section);

    for (let i = 0; i < tracks.length; i++) {
      let card = document.createElement("div");
      card.setAttribute("class", "card p-0");
      card.setAttribute("style", "width: 18rem");
      section.appendChild(card);
  
      let span = document.createElement("span");
      span.setAttribute("class", "img-container");
      span.addEventListener("click", () => getTrackSinglePage(tracks[i].id));
      let img = document.createElement("img");
      img.setAttribute("src", "https://www.superiorwallpapers.com/download/relaxing-place-for-a-special-summer-holiday-tropical-island-4028x2835.jpg");
      img.setAttribute("class", "card-img-top card-background");
      img.setAttribute("alt", tracks[i].name);
      span.appendChild(img);
      
      let p = document.createElement("p");
      p.setAttribute("class", "card-text text");
      p.innerText = tracks[i].name;
      span.appendChild(p);
      card.appendChild(span);

      let deleteIcon = document.createElement("i");
      deleteIcon.setAttribute("class", "bi bi-trash-fill");
      deleteIcon.addEventListener("click", () => removeTrackFromPlaylist(tracks[i].id));
      card.appendChild(deleteIcon);
    }
  }

  const buildPage = (data) => {
    buildHeaderPage(data.name, data.description);
    buildTracksList(data.tracks);
  }

  const fetchPlaylistSingle = () => {
    fetch(`http://localhost:8082/playlists/read/${myParam}`)
      .then((response) => {
          if (response.status !== 200) {
              console.error(`status: ${reponse.status}`);
              return;
          }
          return response.json() 
      })
      .then(data => buildPage(data))
      .catch((err) => console.error(`${ err }`));
  }
  fetchPlaylistSingle();

  const getAllTracks = async () => {
    await fetch(`http://localhost:8082/tracks/read`)
    .then(response => response.json())
      .then(data => {
        let select = document.querySelector(".track-list-dropdown");
        for (let i = 0; i < data.length; i++) {
          let option = document.createElement("option");
          option.setAttribute("value", data[i].id);
          option.innerHTML = data[i].name;
          select.appendChild(option);
        }
    })
    .catch(error => console.error(error));
  }
  getAllTracks();

  const goToTrackSinglePage = (data, trackId) => {
    window.location = `${data}?id=${trackId}`;
  }

  const getTrackSinglePage = (trackId) => {
    fetch(`http://localhost:8082/track`)
      .then(response => response.text())
      .then(data => goToTrackSinglePage(data, trackId));
  }

  const addTrackToPlaylist = async (playlistId, trackId) => {
    await fetch(`http://localhost:8082/playlists/${playlistId}/addTrack/${trackId}`, {
      method: "PUT",
      headers: {
        "Content-type": "application/json"
      }
    })
    .then(response => response.json())
    .then(data => alert("Track added!"))
    .catch(error => console.error(error));
    
    location.reload();
  }

  const displayErrorMessage = (msg) => {
    let alert = document.querySelector("#alert");
    alert.setAttribute("class", "mt-4 alert alert-danger");
    alert.innerText = msg;
  }

  const retrieveAddFormDetails = () => {
    let track = document.querySelector("#track-list").value;

    if (track !== "") {
      addTrackToPlaylist(myParam, track);
    } else {
      displayErrorMessage("A track must be selected!");
    }
  }

  addBtn.addEventListener("click", () => retrieveAddFormDetails());

})();
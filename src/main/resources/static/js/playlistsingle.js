(() => {
  const urlParams = new URLSearchParams(window.location.search);
  const myParam = urlParams.get('id');
  let playlistContainer = document.querySelector(".playlist-container");

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

  const goToTrackSinglePage = (data, trackId) => {
    window.location = `${data}?id=${trackId}`;
  }

  const getTrackSinglePage = (trackId) => {
    fetch(`http://localhost:8082/track`)
            .then(response => response.text())
            .then(data => goToTrackSinglePage(data, trackId));
  }

  const buildHeaderPage = (name, description) => {
    let header = `
      <header id="header-page">
        <h2 id="title-playlist">${name}</h2>
        <p id="playlist-description">${description}</p>
      </header>
    `;
    playlistContainer.innerHTML = header;
  }

  const buildTracksList = (tracks) => {
    let trackList = document.createElement("section");
    trackList.setAttribute("class", "track-list");
    playlistContainer.appendChild(trackList);
    
    let header = document.createElement("header");
    trackList.appendChild(header);
    let tracksTitle = document.createElement("h3");
    tracksTitle.setAttribute("class", "tracks-title");
    tracksTitle.innerText = "List of tracks";
    header.appendChild(tracksTitle);

    let section = document.createElement("section");
    section.setAttribute("class", "row");
    trackList.appendChild(section);

    for (let i = 0; i < tracks.length; i++) {
      let card = document.createElement("div");
      card.setAttribute("class", "card p-0");
      card.setAttribute("style", "width: 18rem");
      section.appendChild(card);
  
      let img = document.createElement("img");
      img.setAttribute("src", "https://www.superiorwallpapers.com/download/relaxing-place-for-a-special-summer-holiday-tropical-island-4028x2835.jpg");
      img.setAttribute("class", "card-img-top card-background");
      img.setAttribute("alt", tracks[i].name);
      card.appendChild(img);
  
      let p = document.createElement("p");
      p.setAttribute("class", "card-text text");
      p.innerText = tracks[i].name;
      card.appendChild(p);
      card.addEventListener("click", () => getTrackSinglePage(tracks[i].id));
    }
  }

  const buildPage = (data) => {
    buildHeaderPage(data.name, data.description);
    buildTracksList(data.tracks);
  }

})();
(() => {
  let main = document.querySelector("main");
  
  const getAllPlaylists = () => {
    fetch(`http://localhost:8082/playlists/read`)
    .then(response => response.json())
    .then(data => displayPlaylist(data))
    .catch(error => console.error(error));
  }
  
  const goToPlaylistSinglePage = (data, playlistId) => {
    window.location = `${data}?id=${playlistId}`;
  }

  const getAlbumSinglePage = (playlistId) => {
    console.log(playlistId);
    fetch(`http://localhost:8082/playlistsingle`)
            .then(response => response.text())
            .then(data => goToPlaylistSinglePage(data, playlistId));
  }
  
  const displayPlaylist = (data) => {
    if (data.length > 0) {
      for (let i = 0; i < data.length; i++) {
        console.log(data[i]);
        let card = document.createElement("div");
        card.setAttribute("class", "card p-0");
        card.setAttribute("style", "width: 18rem");
        main.appendChild(card);

        let img = document.createElement("img");
        img.setAttribute("src", "https://www.superiorwallpapers.com/download/relaxing-place-for-a-special-summer-holiday-tropical-island-4028x2835.jpg");
        img.setAttribute("class", "card-img-top card-background");
        img.setAttribute("alt", data[i].name);
        card.appendChild(img);

        let p = document.createElement("p");
        p.setAttribute("class", "card-text text");
        p.innerText = data[i].name;
        card.appendChild(p);
        card.addEventListener("click", () => getAlbumSinglePage(data[i].id));
        }
    } else {
      let textToDisplay = `<p id="text-no-playlist">:( There are no playlist. But you can start adding new ones now!</p>`;
      main.innerHTML = textToDisplay;
    } 
  }

  getAllPlaylists();
})();
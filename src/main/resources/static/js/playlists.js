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
        let card = `
          <div class="card" style="width: 18rem;">
            <img src="../img/playlist_card_img.PNG" class="card-img-top card-background" alt="">
            <p class="card-text text">${data[i].name}</p>
          </div>
          `;
          
          main.innerHTML += card;
        let cardEl = document.querySelector(".card");
        cardEl.addEventListener("click", () => getAlbumSinglePage(data[i].id));
        }
    } else {
      let textToDisplay = `<p id="text-no-playlist">:( There are no playlist. But you can start adding new ones now!</p>`;
      main.innerHTML = textToDisplay;
    } 
  }

  getAllPlaylists();
})();
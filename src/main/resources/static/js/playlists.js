(() => {
  let main = document.querySelector("main");


  const getAllPlaylists = () => {
    fetch(`http://localhost:8082/playlists/read`)
      .then(response => response.json())
      .then(data => displayPlaylist(data))
      .catch(error => console.error(error));
  }

  const displayPlaylist = (data) => {
    for (let i = 0; i < data.length; i++) {
      console.log(data[i]);
      let card = `
        <div class="card" style="width: 18rem;">
          <img src="../img/playlist_card_img.PNG" class="card-img-top card-background" alt="">
          <p class="card-text text">Playlist name</p>
        </div>
      `;
  
      main.innerHTML = card;
    }
  }

  getAllPlaylists();
})();
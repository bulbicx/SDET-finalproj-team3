(() => {
  let main = document.querySelector("main");


  const getAllPlaylists = () => {
    fetch(`http://localhost:8082/playlists/read`)
      .then(response => response.json())
      .then(data => displayPlaylist(data))
      .catch(error => console.error(error));
  }

  const displayPlaylist = (data) => {
    if (data.length > 0) {
      for (let i = 0; i < data.length; i++) {
        let card = `
          <div class="card" style="width: 18rem;">
            <img src="../img/playlist_card_img.PNG" class="card-img-top card-background" alt="">
            <p class="card-text text">${data[i].name}</p>
          </div>
        `;
    
        main.innerHTML += card;
      }
    } else {
      let textToDisplay = `<p id="text-no-playlist">:( There are no playlist. But you can start adding new ones now!</p>`;
      main.innerHTML = textToDisplay;
    } 
  }

  getAllPlaylists();
})();
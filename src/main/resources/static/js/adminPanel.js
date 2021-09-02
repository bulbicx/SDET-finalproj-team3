(() => {
  let myStorage = window.localStorage;
  let adminPanel = document.querySelector(".admin-panel");
  let panel = `
    <div class="section-link">
      <a href="index.html" class="page-link artist-panel">Home</a>
    </div>
    <div class="section-link">
      <a href="artistCrud.html" class="page-link artist-panel">Artists</a>
    </div>
    <div class="section-link">
      <a href="albumCrud.html" class="page-link album-panel">Albums</a>
    </div>
    <div class="section-link">
      <a href="genreCrud.html" class="page-link genre-panel">Genres</a>
    </div>
    <div class="section-link">
      <a href="trackCrud.html" class="page-link track-panel">Tracks</a>
    </div>
    <div class="section-link">
      <a id="logout" class="page-link">Log out</a>
    </div>
  `;

  adminPanel.innerHTML = panel;

  let artistPanel = document.querySelector(".artist-panel");
  let albumPanel = document.querySelector(".album-panel");
  let genrePanel = document.querySelector(".genre-panel");
  let trackPanel = document.querySelector(".track-panel");

  let logoutButton = document.querySelector("#logout");

 

  if (window.location.href.includes("artist")) {
    artistPanel.setAttribute("class", "page-link artist-panel panel-link-active");
  } else if (window.location.href.includes("album")) {
    albumPanel.setAttribute("class", "page-link album-panel panel-link-active");
  } else if (window.location.href.includes("genre")) {
    genrePanel.setAttribute("class", "page-link genre-panel panel-link-active");
  } else if (window.location.href.includes("track")) {
    trackPanel.setAttribute("class", "page-link track-panel panel-link-active");
  }

  let deleteToken = async (token) => {
    await fetch(`http://localhost:8082/sessions/delete/${token}`, {
      method: 'delete'
    })
      .then(myStorage.removeItem("session-token"))
      .catch((error) => {
        console.log(`Request failed ${error}`);
      });
  }

  const goToAdminLogin = () => {
    fetch(`http://localhost:8082/adminlogin`)
        .then(response => response.text())
        .then(pagelink => window.location = `${pagelink}`);
}

  logoutButton.onclick = () => {
    deleteToken(myStorage.getItem("session-token"));
    myStorage.removeItem("id");
    goToAdminLogin();
  }
})();
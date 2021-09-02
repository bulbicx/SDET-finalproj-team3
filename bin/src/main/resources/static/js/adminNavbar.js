(() => {

  (() => {
    let containerNav = document.querySelector("#nav-container");
    let navbar = `
      <nav class="navbar fixed-top navbar-expand-lg navbar-light">
        <a class="navbar-brand mx-3" href="./">
          <img class="logo p-0" src="../img/Choonz.png" width="auto" height="50">
        </a>
        <button class="navbar-toggler collapsed border-0" type="button" data-toggle="collapse" data-target="#collapsingNavbar">
          <span class="navbar-toggler-icon"></span>
          <div class="close-icon py-1">✖</div>
        </button>
        <div class="collapse navbar-collapse" id="collapsingNavbar">
          <ul class="nav navbar-nav text-center">
            <li class="nav-item">
              <a class="nav-link nav-home" href="index.html">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link nav-artist" href="artistCrud.html">Artists</a>
            </li>
            <li class="nav-item">
                <a class="nav-link nav-album" href="albumCrud.html">Albums</a>
            </li>
            <li class="nav-item">
              <a class="nav-link nav-genre" href="genreCrud.html">Genres</a>
            </li>
            <li class="nav-item">
              <a class="nav-link nav-track" href="trackCrud.html">Tracks</a>
            </li>
            <li class="nav-item">
              <a class="nav-link nav-dashboard" href="admindashboard.html">Dashboard</a>
            </li>
          </ul>
        </div>
      </nav>
      `;
    
    containerNav.innerHTML = navbar;
      
    let navArtist = document.querySelector(".nav-artist");
    let navAlbum = document.querySelector(".nav-album");
    let navGenre = document.querySelector(".nav-genre");
    let navTrack = document.querySelector(".nav-track");
    let navDashboard = document.querySelector(".nav-dashboard");
  
    if (window.location.href.includes("artist")) {
      navArtist.setAttribute("class", "nav-link nav-artist nav-link-active");
    } else if (window.location.href.includes("album")) {
      navAlbum.setAttribute("class", "nav-link nav-album nav-link-active");
    } else if (window.location.href.includes("playlists")) {
      navPlaylist.setAttribute("class", "nav-link nav-playlist nav-link-active");
    } else if (window.location.href.includes("genre")) {
      navGenre.setAttribute("class", "nav-link nav-genre nav-link-active");
    } else if (window.location.href.includes("track")) {
      navTrack.setAttribute("class", "nav-link nav-profile nav-link-active");
    } else {
      navDashboard.setAttribute("class", "nav-link nav-home nav-link-active");
    }
  })();
})();
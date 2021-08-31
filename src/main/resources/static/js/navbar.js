(() => {
  let myStorage = window.localStorage;
  let containerNav = document.querySelector("#nav-container");
  let deleteToken = async (token) => {
    await fetch(`http://localhost:8082/sessions/delete/${token}`, {
      method: 'delete'
    })
      .then(myStorage.removeItem("session-token"))
      .catch((error) => {
        console.log(`Request failed ${error}`);
      });
  }
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
            <a class="nav-link nav-artist" href="artists.html">Artists</a>
          </li>
          <li class="nav-item">
              <a class="nav-link nav-album" href="albums.html">Albums</a>
          </li>
          <li class="nav-item">
              <a class="nav-link nav-playlist" href="playlists.html">Playlists</a>
          </li>
          <li class="nav-item">
            <a class="nav-link nav-genre" href="genres.html">Genres</a>
          </li>
          <li class="nav-item">
            <a class="nav-link nav-profile" href="#">Profile</a>
          </li>
          <li id="nav-login" class="nav-item">
            <a class="nav-link nav-login" href="login.html">Login</a>
          </li>
          <li id="nav-logout" class="nav-item display-none">
            <a class="nav-link nav-logout" href="#">Logout</a>
          </li>
        </ul>
      </div>
    </nav>
    `;

  containerNav.innerHTML = navbar;

  let navArtist = document.querySelector(".nav-artist");
  let navAlbum = document.querySelector(".nav-album");
  let navPlaylist = document.querySelector(".nav-playlist");
  let navGenre = document.querySelector(".nav-genre");
  let navLogin = document.querySelector(".nav-login");
  let navHome = document.querySelector(".nav-home");
  let navLogin = document.querySelector(".nav-login");
  let navLogout = document.querySelector(".nav-logout");
  let navLoginListItem = document.querySelector("#nav-login");
  let navLogoutListItem = document.querySelector("#nav-logout");

  const toggleLoginLogout = () => {
    navLoginListItem.classList.toggle("display-none");
    navLogoutListItem.classList.toggle("display-none");
  }
  if (myStorage.getItem("session-token")) {
    toggleLoginLogout();
  }
  navLogout.onclick = () => {
    deleteToken(myStorage.getItem("session-token"));
    toggleLoginLogout();
  }

  if (window.location.href.includes("artists")) {
    navArtist.setAttribute("class", "nav-link nav-artist nav-link-active");
  } else if (window.location.href.includes("albums")) {
    navAlbum.setAttribute("class", "nav-link nav-album nav-link-active");
  } else if (window.location.href.includes("playlists")) {
    navPlaylist.setAttribute("class", "nav-link nav-playlist nav-link-active");
  } else if (window.location.href.includes("genres")) {
    navGenre.setAttribute("class", "nav-link nav-genre nav-link-active");
  } else if (window.location.href.includes("profile")) {
    navProfile.setAttribute("class", "nav-link nav-profile nav-link-active");
  } else if (window.location.href.includes("login")) {
    navLogin.setAttribute("class", "nav-link nav-login nav-link-active");
  } else {
    navHome.setAttribute("class", "nav-link nav-home nav-link-active");
  }
})();
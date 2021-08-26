(() => {
  let containerNav = document.querySelector("#nav-container");
  let navbar =
    `
    <nav class="navbar fixed-top navbar-expand-lg bg-light navbar-light">
      <a class="navbar-brand" href="./">
      <img class="logo" src="../img/Choonz.jpg" width="auto" ml-auto height="50"></a>
      <button class="navbar-toggler collapsed border-0" type="button" data-toggle="collapse" data-target="#collapsingNavbar">
        <span class="navbar-toggler-icon"></span>
        <div class="close-icon py-1">✖</div>
      </button>
      <div class="collapse navbar-collapse" id="collapsingNavbar">
        <ul class="nav navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="/index.html">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/artists.html">Artists</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="/albums.html">Albums</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="/playlists.html">Playlists</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/genres.html">Genres</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/playlists.html">Profile</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/playlists.html">Login</a>
          </li>
        </ul>
      </div>
    </nav>
    `;
  
  containerNav.innerHTML = navbar;

})();
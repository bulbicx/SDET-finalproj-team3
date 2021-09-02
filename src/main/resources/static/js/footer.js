(() => {
  let footerContainer = document.querySelector(".footer-container");
  const footer = `
    <footer class="row">
      <div class="footer logo-side col-md-4">
          <img src="../img/Choonz.png" alt="logo" class="logo" width="150px">
          <p id="catch-phrase" class="text-muted">We have all the music you are looking for.</p>
          <p id="sub-catch-phrase">Choose us.</p>
      </div>
      <div class="footer pages-side col-md-2">
          <h3 class="title-footer-section">Explore</h3>
          <a href="index.html" class="footer-link">Home</a>
          <a href="artists.html" class="footer-link">Artists</a>
          <a href="albums.html" class="footer-link">Albums</a>
          <a href="playlists.html" class="footer-link">Playlists</a>
          <a href="genres.html" class="footer-link">Genres</a>
      </div>
      <div class="footer contact-side col-md-3">
          <div class="visit-us">
              <h3 class="title-footer-section">Visit</h3>
              <p class="contact">230, Hyvoy street</p>
              <p class="contact">SEK 9HG</p>
              <p class="contact">London, UK</p>
          </div>
          <div class="contact-us">
              <h3 class="title-footer-section">Contact us</h3>
              <p class="contact">choonz@we.at.choonz.com</p>
              <p class="contact">+44 (0) 7 999 777 555</p>
          </div>
      </div>
      <div class="footer follow-us col-md-3">
          <h3 class="title-footer-section">Follow</h3>
          <ul class="social">
              <li><a class="social-link" href="https://en-gb.facebook.com/"><i class="bi bi-facebook"></i></a></li>
              <li><a class="social-link" href="https://www.instagram.com/"><i class="bi bi-instagram"></i></a></li>
              <li><a class="social-link" href="https://twitter.com/?lang=en-gb"><i class="bi bi-twitter"></i></a></li>
          </ul>
          <span>
            <a href="adminlogin.html" class="admin-link">Admin login</a>
          </span>
      </div>
      <div class="copyright-section">
          <p id="copyright">&copy; 2021 Choonz. All Rights Reserved.</p>
          <p class="text-muted creator">Made by Marco Castellana & Niall Duggan & David Indiongco & Leaf Cooper</p>
      </div>
    </footer>
  `;

  footerContainer.innerHTML = footer;
})();
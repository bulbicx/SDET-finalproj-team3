(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const myParam = urlParams.get('id');

    function getPlaylistPage(playlistId) {
        fetch(`http://localhost:8082/playlistsingle`)
                .then(response => response.text())
                .then(pagelink => goToDynamicPage(pagelink, playlistId));
    }
    function getAlbumPage(albumId){
        fetch(`http://localhost:8082/albumsingle`)
                .then(response => response.text())
                .then(pagelink => goToDynamicPage(pagelink, albumId));
    }
    function getArtistPage(artistId){
        fetch(`http://localhost:8082/artistsingle`)
                .then(response => response.text())
                .then(pagelink => goToDynamicPage(pagelink, artistId));
    }

    function goToDynamicPage(pagelink, id) {
        window.location = `${pagelink}?id=${id}`;
    }

    fetch(`http://localhost:8082/tracks/read/${myParam}`)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json()
                .then(data => populatePage(data))
        }).catch((err) => console.error(`${err}`));

    function populatePage(track) {
        createTrackPageHeader(track);
        if (track.playlist.length > 0) {
            let playlistsDiv = document.querySelector("#track-playlists");
            playlistsDiv.toggleAttribute("class", "display-none");
            let i = 1;
            for (playlist in track.playlist) {
                createPlayListCard(track.playlist[playlist], playlistsDiv);
            }
        }
    }

    function createPlayListCard(playlist, playListsDiv) {
        let card = document.createElement("div");
        card.setAttribute("class", "card mb-3");
        card.setAttribute("style", "width:12rem");
        card.onclick = () => {
            getPlaylistPage(playlist.id);
        }
        playListsDiv.appendChild(card);

        let cardImage = document.createElement("img");
        cardImage.setAttribute("class", "card-img-top");
        cardImage.setAttribute("src", "https://www.superiorwallpapers.com/download/relaxing-place-for-a-special-summer-holiday-tropical-island-4028x2835.jpg");
        cardImage.setAttribute("alt", "Card image cap");
        card.appendChild(cardImage);

        let cardBody = document.createElement("div");
        cardBody.setAttribute("class", "card-body");
        card.appendChild(cardBody);

        let cardBodyText = document.createElement("div");
        cardBodyText.setAttribute("class", "text-playlist-card");
        cardBodyText.textContent = playlist.name;
        cardBody.appendChild(cardBodyText);
    }

    function createTrackPageHeader(track) {
        let albumArtistCol = document.querySelector("#album-artist-col");
        let trackCol = document.querySelector("#track-col");

        let albumImage = document.createElement("img");
        albumImage.setAttribute("class", "img-header");
        albumImage.setAttribute("src", "https://www.superiorwallpapers.com/download/relaxing-place-for-a-special-summer-holiday-tropical-island-4028x2835.jpg");
        albumImage.setAttribute("alt", "image");
        albumImage.onclick = () => {
            getAlbumPage(track.album.id);
        }
        albumArtistCol.appendChild(albumImage);

        let albumName = document.createElement("h1");
        albumName.textContent = track.album.name;
        albumArtistCol.appendChild(albumName);

        let trackAndArtistRow = document.createElement("div");
        trackAndArtistRow.setAttribute("class", "row");
        trackCol.appendChild(trackAndArtistRow);

        let artistImage = document.createElement("img");
        artistImage.setAttribute("class", "img-artist-mini");
        artistImage.setAttribute("src", "https://www.superiorwallpapers.com/download/relaxing-place-for-a-special-summer-holiday-tropical-island-4028x2835.jpg");
        artistImage.setAttribute("alt", "artist-image");
        artistImage.onclick = () => {
            getArtistPage(track.album.artist.id);
        }
        trackAndArtistRow.appendChild(artistImage);

        let trackAndArtistName = document.createElement("h1");
        trackAndArtistName.textContent = track.album.artist.name + " - " + track.name;
        trackAndArtistRow.appendChild(trackAndArtistName);

        let trackLyrics = document.createElement("p");
        trackLyrics.textContent = track.lyrics;
        trackCol.appendChild(trackLyrics);
    }
})();
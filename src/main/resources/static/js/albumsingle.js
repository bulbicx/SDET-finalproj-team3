(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const myParam = urlParams.get('id');

    function getTrackPage(trackId) {
        fetch(`http://localhost:8082/track`)
            .then(response => response.text())
            .then(pagelink => goToDynamicPage(pagelink, trackId));
    }

    function getArtistPage(artistId){
        fetch(`http://localhost:8082/artistsingle`)
            .then(response => response.text())
            .then(pagelink => goToDynamicPage(pagelink, artistId));
    }

    function goToDynamicPage(pagelink, id) {
        window.location = `${pagelink}?id=${id}`;
    }

    fetch(`http://localhost:8082/albums/read/${myParam}`)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json() // 3
                .then(data => populatePage(data))
        }).catch((err) => console.error(`${err}`));

    function populatePage(album){
        createAlbumHeader(album);
        if(album.tracks.length > 0){
            let tracksDiv = document.querySelector("#track-div");
            tracksDiv.toggleAttribute("class", "display-none");
            let tracksTableBody = document.querySelector("#track-table-body");
            let i = 1; 
            for(track in album.tracks){
                createTrackRow(album.tracks[track], tracksTableBody, i++);
            }
        } 
    }

    function createTrackRow(track, tracksTableBody, rowNumber){
        console.log(track);
        let trackTableRow = document.createElement("tr");
        trackTableRow.setAttribute("class", "row-track");
        trackTableRow.onclick = () => {
            getTrackPage(track.id);
        }
        tracksTableBody.appendChild(trackTableRow);
        
        let trackTableHead = document.createElement("th");
        trackTableHead.setAttribute("scope", "row");
        trackTableHead.textContent = rowNumber;
        trackTableRow.appendChild(trackTableHead);

        let trackNameTableData = document.createElement("td");
        trackNameTableData.textContent = track.name;
        trackTableRow.appendChild(trackNameTableData);

        let trackDurationTableData = document.createElement("td");
        trackDurationTableData.textContent = track.duration;
        trackTableRow.appendChild(trackDurationTableData);
    }

    function createAlbumHeader(album) {
        let albumCol = document.querySelector("#album-col");
        let artistCol = document.querySelector("#artist-col");
        
        let albumImage = document.createElement("img");
        albumImage.setAttribute("class", "img-header");
        
        albumImage.setAttribute("src", "data:image/" + album.cover.type + ";base64," + album.cover.picByte);
        albumImage.setAttribute("alt", "image");
        albumImage.setAttribute("width", 192);
        albumImage.setAttribute("height", 192);
        albumCol.appendChild(albumImage);
        let albumName = document.createElement("h1");
        albumName.textContent = album.name;
        albumName.setAttribute("class", "title-section-sgl-pg");
        albumCol.appendChild(albumName);
        
        let artistImage = document.createElement("img");
        artistImage.setAttribute("class", "img-header");
        artistImage.setAttribute("src", "https://media.gq-magazine.co.uk/photos/5e830b92013fff000829dd01/master/w_1920,h_1280,c_limit/20200331-new-music-09.jpg");
        artistImage.setAttribute("alt", "image");
        artistImage.setAttribute("width", 192);
        artistImage.setAttribute("height", 192);
        artistImage.setAttribute("class", "card");
        artistImage.onclick=() => {
            getArtistPage(album.artist.id);
        }
        artistCol.appendChild(artistImage);
        let artistName = document.createElement("h1");
        artistName.textContent = album.artist.name;
        artistName.setAttribute("class", "artist-name");
        artistCol.appendChild(artistName);
    }
})();
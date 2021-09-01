(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const myParam = urlParams.get('id');

    function getTrackPage(trackId) {
        fetch(`http://localhost:8082/track`)
                .then(response => response.text())
                .then(pagelink => gotToTrackPage(pagelink, trackId));
    }

    function gotToTrackPage(pagelink, trackId) {
        window.location = `${pagelink}?id=${trackId}`;
    }

    console.log(myParam);
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
            console.log(album.tracks.length);
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
                console.log(tracksTableBody);
                let trackTableRow = document.createElement("tr");
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
            
            let albumName = document.createElement("h1");
            albumName.textContent = album.name;
            albumCol.appendChild(albumName);
            
            let albumImage = document.createElement("img");
            albumImage.setAttribute("class", "img-header");
            
            albumImage.setAttribute("src", "https://www.superiorwallpapers.com/download/relaxing-place-for-a-special-summer-holiday-tropical-island-4028x2835.jpg");
            albumImage.setAttribute("alt", "image");
            albumImage.setAttribute("width", 192);
            albumImage.setAttribute("height", 192);
            albumCol.appendChild(albumImage);

            let artistName = document.createElement("h1");
            artistName.textContent = album.artist.name;
            artistCol.appendChild(artistName);
            
            let artistImage = document.createElement("img");
            artistImage.setAttribute("class", "img-header");
            artistImage.setAttribute("src", "https://www.superiorwallpapers.com/download/relaxing-place-for-a-special-summer-holiday-tropical-island-4028x2835.jpg");
            artistImage.setAttribute("alt", "image");
            artistImage.setAttribute("width", 192);
            artistImage.setAttribute("height", 192);
            artistCol.appendChild(artistImage);
        }
})();
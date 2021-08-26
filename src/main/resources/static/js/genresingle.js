(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const myParam = urlParams.get('id');

    
    function getAlbumPage(albumId){
        fetch(`http://localhost:8082/albumsingle`)
                .then(response => response.text())
                .then(pagelink => goToDynamicPage(pagelink, albumId));
    }
    function getArtistPage(artistId) {
        fetch(`http://localhost:8082/artistsingle`)
                .then(response => response.text())
                .then(pagelink => goToDynamicPage(pagelink, artistId));
    }

    function goToDynamicPage(pagelink, id) {
        window.location = `${pagelink}?id=${id}`;
    }

    console.log(myParam);
    fetch(`http://localhost:8082/genres/read/${myParam}`)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json() // 3
                .then(data => populatePage(data))
        }).catch((err) => console.error(`${err}`));

        function populatePage(genre){
            createGenreHeader(genre);
            console.log(genre.albums.length);
            if(genre.albums.length > 0){
                let albumsDiv = document.querySelector("#album-div");
                albumsDiv.toggleAttribute("class", "display-none");
                let albumsTableBody = document.querySelector("#album-table-body");
                let i = 1; 
                for(album in genre.albums){
                    createGenreBodyAlbum(genre.albums[album], albumsTableBody, i++);
                }
            } 
        }

        function createGenreBodyAlbum(album, albumsTableBody, rowNumber){
                console.log(album);
                console.log(albumsTableBody);
                let albumTableRow = document.createElement("tr");
                albumsTableBody.appendChild(albumTableRow);
                
                let albumTableHead = document.createElement("th");
                albumTableHead.setAttribute("scope", "row");
                albumTableHead.textContent = rowNumber;
                albumTableRow.appendChild(albumTableHead);

                let albumCoverTableData = document.createElement("td");
                let albumCover = document.createElement("img");
                albumCover.setAttribute("src", "https://www.superiorwallpapers.com/download/a-guitar-in-flames-rock-music-guitar-1920x1080.jpg");
                albumCover.setAttribute("width", 100);
                albumCover.setAttribute("height", 100);
                albumCoverTableData.append(albumCover);
                albumTableRow.appendChild(albumCoverTableData);
                albumCover.onclick=() => {
                    getAlbumPage(album.id);
                }

                let albumNameTableData = document.createElement("td");
                albumNameTableData.textContent = album.name;
                albumTableRow.appendChild(albumNameTableData);
                albumNameTableData.onclick=() => {
                    getAlbumPage(album.id);
                }

                let albumArtistTableData = document.createElement("td");
                albumArtistTableData.textContent = album.artist.name;
                albumTableRow.appendChild(albumArtistTableData);
                albumArtistTableData.onclick=() => {
                    getArtistPage(album.artist.id);
                }
        }

        function createGenreHeader(genre) {
            let genreCol = document.querySelector("#genre-col");
            
            let genreName = document.createElement("h1");
            genreName.textContent = genre.name;
            genreCol.appendChild(genreName);
            
            let genreImage = document.createElement("img");
            genreImage.setAttribute("class", "img-header");
            genreImage.setAttribute("src", "https://www.superiorwallpapers.com/download/a-guitar-in-flames-rock-music-guitar-1920x1080.jpg");
            genreImage.setAttribute("alt", "image");
            genreImage.setAttribute("width", 100);
            genreImage.setAttribute("height", 100);
            genreCol.appendChild(genreImage);

            let genreDescription = document.createElement("h2");
            genreDescription.textContent = genre.description;
            genreDescription.setAttribute("style", "display: inline");
            genreDescription.setAttribute("align", "right");
            genreCol.appendChild(genreDescription);
        }
})();
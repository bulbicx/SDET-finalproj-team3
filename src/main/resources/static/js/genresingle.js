(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const myParam = urlParams.get('id');

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
                albumCover.setAttribute("width", 10);
                albumCover.setAttribute("height", 10);
                albumCoverTableData.append(albumCover);
                albumTableRow.appendChild(albumCoverTableData);

                let albumNameTableData = document.createElement("td");
                albumNameTableData.textContent = album.name;
                albumTableRow.appendChild(albumNameTableData);

                let albumArtistTableData = document.createElement("td");
                albumArtistTableData.textContent = album.artist.name;
                albumTableRow.appendChild(albumArtistTableData);
        }

        function createGenreHeader(genre) {
            let genreCol = document.querySelector("#genre-col");
            let albumCol = document.querySelector("#album-col");
            
            let genreName = document.createElement("h1");
            genreName.textContent = genre.name;
            genreCol.appendChild(genreName);
            
            let genreImage = document.createElement("img");
            genreImage.setAttribute("class", "img-header");
            genreImage.setAttribute("src", "https://www.superiorwallpapers.com/download/a-guitar-in-flames-rock-music-guitar-1920x1080.jpg");
            genreImage.setAttribute("alt", "image");
            genreCol.appendChild(genreImage);

            // let albumName = document.createElement("h1");
            // albumName.textContent = genre.album.name;
            // albumCol.appendChild(albumName);
            
            // let albumImage = document.createElement("img");
            // albumImage.setAttribute("class", "img-header");
            // albumImage.setAttribute("src", "https://www.superiorwallpapers.com/download/relaxing-place-for-a-special-summer-holiday-tropical-island-4028x2835.jpg");
            // albumImage.setAttribute("alt", "image");
            // albumCol.appendChild(albumImage);


        }
})();
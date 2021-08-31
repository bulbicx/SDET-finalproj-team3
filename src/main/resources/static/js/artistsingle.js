(() => {
    const urlParams = new URLSearchParams(window.location.search);
    const myParam = urlParams.get('id');

    function getGenrePage(genreId) {
        fetch(`http://localhost:8082/genresingle`)
                .then(response => response.text())
                .then(pagelink => goToDynamicPage(pagelink, genreId));
    }
    function getAlbumPage(albumId){
        fetch(`http://localhost:8082/albumsingle`)
                .then(response => response.text())
                .then(pagelink => goToDynamicPage(pagelink, albumId));
    }

    function goToDynamicPage(pagelink, id) {
        window.location = `${pagelink}?id=${id}`;
    }

    console.log(myParam);
    fetch(`http://localhost:8082/artists/read/${myParam}`)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json() // 3
                .then(data => populatePage(data))
        }).catch((err) => console.error(`${err}`));

        function populatePage(artist){
            createArtistHeader(artist);
            console.log(artist.albums.length);
            if(artist.albums.length > 0){
                let albumsDiv = document.querySelector("#album-div");
                albumsDiv.toggleAttribute("class", "display-none");
                let albumsTableBody = document.querySelector("#album-table-body");
                let i = 1; 
                for(album in artist.albums){
                    createArtistBodyAlbum(artist.albums[album], albumsTableBody, i++);
                }
            } 
        }

        function createArtistBodyAlbum(album, albumsTableBody, rowNumber){
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
                //setAttribute("src", album.cover);
                albumCover.setAttribute("src", "https://www.superiorwallpapers.com/download/relaxing-place-for-a-special-summer-holiday-tropical-island-4028x2835.jpg");
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

                let albumGenreTableData = document.createElement("td");
                albumGenreTableData.textContent = album.genre.name;
                albumTableRow.appendChild(albumGenreTableData);
                albumGenreTableData.onclick=() => {
                    getGenrePage(album.genre.id);
                }
        }

        function createArtistHeader(artist) {
            let artistCol = document.querySelector("#artist-col");

            let artistName = document.createElement("h1");
            artistName.textContent = artist.name;
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
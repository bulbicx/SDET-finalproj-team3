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
        })
        .catch((err) => console.error(`${ err }`));

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
        let albumTableRow = document.createElement("tr");
        // albumTableRow.setAttribute("class", "row-album");
        albumsTableBody.appendChild(albumTableRow);
        
        let albumTableHead = document.createElement("th");
        albumTableHead.setAttribute("scope", "row");
        albumTableHead.textContent = rowNumber;
        albumTableRow.appendChild(albumTableHead);

        let albumCoverTableData = document.createElement("td");
        let albumCover = document.createElement("img");
        albumCover.setAttribute("src", "data:image/" + album.cover.type + ";base64," + album.cover.picByte);
        albumCover.setAttribute("width", 100);
        albumCover.setAttribute("height", 100);
        albumCover.setAttribute("class", "card");
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
        let imgSection = document.querySelector(".img-section");
        let textSection = document.querySelector(".text-section");
        let genreImage = document.createElement("img");
        genreImage.setAttribute("class", "img-header");
        genreImage.setAttribute("src", "https://www.superiorwallpapers.com/download/a-guitar-in-flames-rock-music-guitar-1920x1080.jpg");
        genreImage.setAttribute("alt", "image");
        genreImage.setAttribute("width", 100);
        genreImage.setAttribute("height", 100);
        imgSection.appendChild(genreImage);

        let genreName = document.createElement("h1");
        genreName.textContent = genre.name;
        genreName.setAttribute("class", "title-section-sgl-genre");
        textSection.appendChild(genreName);

        let genreDescription = document.createElement("h2");
        genreDescription.textContent = genre.description;
        genreDescription.setAttribute("style", "display: inline");
        genreDescription.setAttribute("align", "right");
        genreDescription.setAttribute("class", "description-genre text-muted");
        textSection.appendChild(genreDescription);
    }
})();
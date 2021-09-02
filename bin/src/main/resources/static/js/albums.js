
(() => {
    function getAlbumSinglePage(albumId) {
        fetch(`http://localhost:8082/albumsingle`)
                .then(response => response.text())
                .then(data => goToAlbumSinglePage(data, albumId));
    }

    function goToAlbumSinglePage(data, albumId) {
        window.location = `${data}?id=${albumId}`;
    }

    fetch(`http://localhost:8082/albums/read`)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json() // 3
                .then(data => createAlbums(data))
        }).catch((err) => console.error(`${err}`));

    function createAlbums(albums) {
        for (album in albums) {
            createAlbumCard(albums[album]);
        }
    }
    function createAlbumCard(album) {
        let cardGroup = document.querySelector("#card-group");

        let card = document.createElement("div");
        card.setAttribute("class", "card mb-3");
        card.setAttribute("style", "width:12rem;");
        card.onclick = () => {
            getAlbumSinglePage(album.id);
        }
        cardGroup.appendChild(card);
        let cardImage = document.createElement("img");
        cardImage.setAttribute("class", "card-img-top");
        cardImage.setAttribute("src", "data:image/" + album.cover.type + ";base64," + album.cover.picByte);
        cardImage.setAttribute("alt", album.cover.name);
        card.appendChild(cardImage);

        let cardBody = document.createElement("div");
        cardBody.setAttribute("class", "card-body");
        card.appendChild(cardBody);

        let cardBodyText = document.createElement("div");
        cardBodyText.setAttribute("class", "text-playlist-card");
        cardBodyText.textContent = album.name;
        cardBody.appendChild(cardBodyText);
    }
}
)();



(() => {
    function getAlbumSinglePage(albumId) {
        console.log(albumId);
        fetch(`http://localhost:8082/albumsingle`)
                .then(response => response.text())
                .then(data => goToAlbumSinglePage(data, albumId));
    }

    function goToAlbumSinglePage(data, albumId) {
        console.log(albumId);
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
        console.log("create albums")
        for (album in albums) {
            console.log(albums[0]);
            createAlbumCard(albums[album]);
        }
    }
    function createAlbumCard(album) {
        console.log("in here alvbum card")
        let cardGroup = document.querySelector("#card-group");

        let card = document.createElement("div");
        card.setAttribute("class", "card mb-3");
        card.setAttribute("style", "width:12rem");
        card.onclick = () => {
            getAlbumSinglePage(album.id);
        }
        cardGroup.appendChild(card);

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
        cardBodyText.textContent = album.name;
        cardBody.appendChild(cardBodyText);
    }
}
)();


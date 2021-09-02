(() => {
    function getArtistSinglePage(artistId) {
        fetch(`http://localhost:8082/artistsingle`)
                .then(response => response.text())
                .then(data => goToArtistSinglePage(data, artistId));
    }

    function goToArtistSinglePage(data, artistId) {
        window.location = `${data}?id=${artistId}`;
    }

    fetch(`http://localhost:8082/artists/read`)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json() // 3
                .then(data => createArtists(data))
        }).catch((err) => console.error(`${err}`));

    function createArtists(artists) {
        for (artist in artists) {
            createArtistCard(artists[artist]);
        }
    }
    function createArtistCard(artist) {
        let cardGroup = document.querySelector("#card-group");

        let card = document.createElement("div");
        card.setAttribute("class", "card mb-3");
        card.setAttribute("style", "width:12rem");
        card.onclick = () => {
            getArtistSinglePage(artist.id);
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
        cardBodyText.textContent = artist.name;
        cardBody.appendChild(cardBodyText);
    }
}
)();
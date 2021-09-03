(() => {
    function getGenreSinglePage(genreId) {
        fetch(`http://localhost:8082/genresingle`)
                .then(response => response.text())
                .then(data => goToGenreSinglePage(data, genreId));
    }

    function goToGenreSinglePage(data, genreId) {
        window.location = `${data}?id=${genreId}`;
    }

    fetch(`http://localhost:8082/genres/read`)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`status: ${reponse.status}`);
                return;
            }
            response.json() // 3
                .then(data => createGenres(data))
        }).catch((err) => console.error(`${err}`));

    function createGenres(genres) {
        for (genre in genres) {
            createGenreCard(genres[genre]);
        }
    }

    function createGenreCard(genre) {
        let cardGroup = document.querySelector("#card-group");

        let card = document.createElement("div");
        card.setAttribute("class", "card mb-3");
        card.setAttribute("style", "width:12rem");
        card.onclick = () => {
            getGenreSinglePage(genre.id);
        }
        cardGroup.appendChild(card);

        let cardImage = document.createElement("img");
        cardImage.setAttribute("class", "card-img-top");
        cardImage.setAttribute("alt", genre.image.name);
        cardImage.setAttribute("src", "data:image/" + genre.image.type + ";base64," + genre.image.picByte);
        card.appendChild(cardImage);

        let cardBody = document.createElement("div");
        cardBody.setAttribute("class", "card-body");
        card.appendChild(cardBody);

        let cardBodyText = document.createElement("div");
        cardBodyText.setAttribute("class", "text-playlist-card");
        cardBodyText.textContent = genre.name;
        cardBody.appendChild(cardBodyText);
    }
}
)();
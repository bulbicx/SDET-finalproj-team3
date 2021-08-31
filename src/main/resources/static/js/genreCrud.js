(() => {
  let addBtn = document.querySelector(".add");
  let updateBtn = document.querySelector(".update");
  let genreId;

  const buildTable = (data) => {
    let container = document.querySelector(".box-pages");
    let table = document.createElement("table");
    table.setAttribute("id", "myTable");
    table.setAttribute("class", "table table-striped")
    container.appendChild(table);
    let thead = document.createElement("thead");
    table.appendChild(thead);
    let trHead = document.createElement("tr");
    thead.appendChild(trHead);
    let thId = document.createElement("th");
    thId.innerText = "Genre ID";
    trHead.appendChild(thId);
    let thName = document.createElement("th");
    thName.innerText = "Genre name";
    trHead.appendChild(thName);
    let thDesc = document.createElement("th");
    thDesc.innerText = "Description";
    trHead.appendChild(thDesc);
    let thAction = document.createElement("th");
    thAction.innerText = "Action";
    trHead.appendChild(thAction);
    let tbody = document.createElement("tbody");
    table.appendChild(tbody);
    if (data.length < 1) {
      displayNoDataMsg("There is no data")
      return;
    }
    for (let i = 0; i < data.length; i++) {
      let tr = document.createElement("tr");
      tbody.appendChild(tr);
      let idTd = document.createElement("td");
      idTd.innerText = data[i].id
      tr.appendChild(idTd);
      let nameTd = document.createElement("td");
      nameTd.innerText = data[i].name
      tr.appendChild(nameTd);
      let descTd = document.createElement("td");
      descTd.innerText = data[i].description
      tr.appendChild(descTd);
      let actionTd = document.createElement("td");
      actionTd.setAttribute("style", "width: 10em; text-align: center;");

      let editIcon = document.createElement("i");
      editIcon.setAttribute("class", "bi bi-pen-fill");
      editIcon.setAttribute("type", "button");
      editIcon.setAttribute("data-bs-toggle", "modal");
      editIcon.setAttribute("data-bs-target", "#update-genre");
      editIcon.addEventListener("click", () => retrieveOneGenre(data[i].id));
      let deleteIcon = document.createElement("i");
      deleteIcon.setAttribute("class", "bi bi-trash-fill");
      deleteIcon.setAttribute("type", "button");
      deleteIcon.setAttribute("data-bs-toggle", "modal");
      deleteIcon.setAttribute("data-bs-target", "#delete-genre");
      deleteIcon.addEventListener("click", () => deleteGenre(data[i].id))
      actionTd.appendChild(editIcon);
      actionTd.appendChild(deleteIcon);
      tr.appendChild(actionTd);
    }
  }

  const getAllGenres = () => {
    fetch(`http://localhost:8082/genres/read`)
    .then(response => response.json())
    .then(data => buildTable(data))
    .catch(error => console.error(error));
  }
  getAllGenres();

  
  const displayGenreUpdate = (data) => {
    let genreName = document.querySelector("#name");
    let genreDesc = document.querySelector("#description");
    genreName.value = data.name;
    genreDesc.value = data.description;
    genreId = data.id;
  }

  const retrieveOneGenre = (genreId) => {
    fetch(`http://localhost:8082/genres/read/${genreId}`)
    .then(response => response.json())
    .then(data => displayGenreUpdate(data))
    .catch(error => console.error(error));
  }
  
  const addGenre = async (genre) => {
    await fetch(`http://localhost:8082/genres/create`, {
      method: "POST",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(genre)
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
    
    location.reload();
  }

  const updateGenre = async (genre) => {
    await fetch(`http://localhost:8082/genres/update/${genreId}`, {
      method: "PUT",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(genre)
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
    
    location.reload();
  }

  const deleteGenre = (genreId) => {
    fetch(`http://localhost:8082/genres/delete/${genreId}`, {
      method: "DELETE",
      headers: {
        "Content-type": "application/json"
      }
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error(error));
    
    location.reload();
  }

  const displayErrorMessage = (msg) => {
    let alert = document.querySelector("#alert");
    alert.setAttribute("class", "alert alert-danger");
    alert.innerText = msg;
  }
  
  const displayNoDataMsg = (msg) => {
    let alert = document.querySelector("#alert");
    alert.setAttribute("class", "alert alert-warning");
    alert.innerText = msg;
  }

  const retrieveUpdateFormDetails = () => {
    let genreUpdateName = document.querySelector("#name").value;
    let genreUpdateDesc = document.querySelector("#description").value;

    if (genreUpdateName !== "" && genreUpdateDesc !== "") {
      let genre = {
        name: genreUpdateName,
        description: genreUpdateDesc
      };
      updateGenre(genre);
    } else {
      displayErrorMessage("All fields must be valid!");
    }
  }

  const retrieveAddFormDetails = () => {
    let genreName = document.querySelector("#new-name").value;
    let genreDesc = document.querySelector("#new-description").value;
    
    if (genreName !== "" && genreDesc !== "") {
      let genre = {
        name: genreName,
        description: genreDesc
      };
      addGenre(genre);
    } else {
      displayErrorMessage("All fields must be valid!");
    }

  }

  addBtn.addEventListener("click", () => retrieveAddFormDetails());
  updateBtn.addEventListener("click", () => retrieveUpdateFormDetails());
})();
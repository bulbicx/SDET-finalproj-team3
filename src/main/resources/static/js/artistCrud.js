(() => {
  let addBtn = document.querySelector(".add");
  let updateBtn = document.querySelector(".update");
  let artistId;

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
    thId.innerText = "Artist ID";
    trHead.appendChild(thId);
    let thName = document.createElement("th");
    thName.innerText = "Artist name";
    trHead.appendChild(thName);
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
      let actionTd = document.createElement("td");
      actionTd.setAttribute("style", "width: 10em; text-align: center;");

      let editIcon = document.createElement("i");
      editIcon.setAttribute("class", "bi bi-pen-fill");
      editIcon.setAttribute("type", "button");
      editIcon.setAttribute("data-bs-toggle", "modal");
      editIcon.setAttribute("data-bs-target", "#update-artist");
      editIcon.addEventListener("click", () => retrieveOneArtist(data[i].id));
      let deleteIcon = document.createElement("i");
      deleteIcon.setAttribute("class", "bi bi-trash-fill");
      deleteIcon.setAttribute("type", "button");
      deleteIcon.setAttribute("data-bs-toggle", "modal");
      deleteIcon.setAttribute("data-bs-target", "#delete-artist");
      deleteIcon.addEventListener("click", () => deleteArtist(data[i].id))
      actionTd.appendChild(editIcon);
      actionTd.appendChild(deleteIcon);
      tr.appendChild(actionTd);
    }
  }

  const getAllArtists = () => {
    fetch(`http://localhost:8082/artists/read`)
    .then(response => response.json())
    .then(data => buildTable(data))
    .catch(error => console.error(error));
  }
  getAllArtists();

  
  const displayArtistUpdate = (data) => {
    let artistName = document.querySelector("#name");
    artistName.value = data.name;
    artistId = data.id;
  }

  const retrieveOneArtist = (artistId) => {
    fetch(`http://localhost:8082/artists/read/${artistId}`)
    .then(response => response.json())
    .then(data => displayArtistUpdate(data))
    .catch(error => console.error(error));
  }
  
  const addArtist = async (artist) => {
    await fetch(`http://localhost:8082/artists/create`, {
      method: "POST",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(artist)
    })
    .then(response => response.json())
    .then(data => alert("New Artist added!"))
    .catch(error => console.error(error));
    
    location.reload();
    
  }

  const updateArtist = async (artist) => {
    await fetch(`http://localhost:8082/artists/update/${artistId}`, {
      method: "PUT",
      headers: {
        "Content-type": "application/json"
      },
      body: JSON.stringify(artist)
    })
    .then(response => response.json())
    .then(data => alert("Artist updated!"))
    .catch(error => console.error(error));
    
    location.reload();
  }

  const deleteArtist = async(artistId) => {
    await fetch(`http://localhost:8082/artists/delete/${artistId}`, {
      method: "DELETE",
      headers: {
        "Content-type": "application/json"
      }
    })
    .then(response => response.json())
    .then(data => alert("Artist deleted!"))
    .catch(error => console.error(error));
    
    alert("Artist deleted!");
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
    let artistUpdateName = document.querySelector("#name").value;

    if (artistUpdateName !== "") {
      let artist = { name: artistUpdateName };
      updateArtist(artist);
    } else {
      displayErrorMessage("An artist name must be valid!");
    }
  }

  const retrieveAddFormDetails = () => {
    let artistName = document.querySelector("#new-name").value;
    
    if (artistName !== "") {
      let artist = { name: artistName };
      addArtist(artist);
    } else {
      displayErrorMessage("An artist name must be inserted!");
    }

  }

  addBtn.addEventListener("click", () => retrieveAddFormDetails());
  updateBtn.addEventListener("click", () => retrieveUpdateFormDetails());
})();
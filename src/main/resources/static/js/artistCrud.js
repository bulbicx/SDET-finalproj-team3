(() => {
  
  const buildTable = (data) => {
    if (data.length < 1) {
      let tr = document.createElement("tr");
      tbody.appendChild(tr);
      let idTd = document.createElement("td");
      idTd.innerText = "There is no artist yet"
      tr.appendChild(idTd);
      return;
    }
    let container = document.querySelector(".box-pages");
    let table = document.createElement("table");
    table.setAttribute("id", "myTable");
    table.setAttribute("class", "table table-striped")
    container.appendChild(table);
    // let table = document.querySelector(".table");
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
    for (let i = 0; i < data.length; i++) {

      let tbody = document.createElement("tbody");
      table.appendChild(tbody);

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

      let plusIcon = document.createElement("i");
      plusIcon.setAttribute("class", "bi bi-plus-circle-fill");
      plusIcon.setAttribute("type", "button");
      plusIcon.setAttribute("data-bs-toggle", "modal");
      plusIcon.setAttribute("data-bs-target", "#add-artist");
      let editIcon = document.createElement("i");
      editIcon.setAttribute("class", "bi bi-pen-fill");
      editIcon.setAttribute("type", "button");
      editIcon.setAttribute("data-bs-toggle", "modal");
      editIcon.setAttribute("data-bs-target", "#edit-artist");
      // editIcon.addEventListener("click", () => getListPlaylist("put"));
      let deleteIcon = document.createElement("i");
      deleteIcon.setAttribute("class", "bi bi-trash-fill");
      deleteIcon.setAttribute("type", "button");
      deleteIcon.setAttribute("data-bs-toggle", "modal");
      deleteIcon.setAttribute("data-bs-target", "#delete-artist");
      // deleteIcon.addEventListener("click", () => getListPlaylist("delete"))
      actionTd.appendChild(plusIcon);
      actionTd.appendChild(editIcon);
      actionTd.appendChild(deleteIcon);
      tr.appendChild(actionTd);
    }
    // $(document).ready(function () {
    //   $('#myTable').DataTable({
    //     'searching': false
    //   });
    // });
  }

  const getAllArtists = () => {
    fetch(`http://localhost:8082/artists/read`)
    .then(response => response.json())
    .then(data => buildTable(data))
    .catch(error => console.error(error));
  }

  getAllArtists();
})();
let films;

let pageNo = 1;

function getAllFilms(){
    console.log("loadingFiles");
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "http://localhost:8080/api/film", false);
    xmlHttp.send(null);
    let jsonString = xmlHttp.responseText;
    films = JSON.parse(jsonString);
    let results = [];
    //return films;
    
    printFilms(films);

    return films;
}

function searchFieldChanged(){
    let field = document.getElementById("searchField").value.toLowerCase();
    let newSelect = document.getElementById("comparitor");
    let actorSelect = document.getElementById("actorComparitor");
    let set1 = new Set(["price","length"]);
    if(set1.has(field)){
        newSelect.removeAttribute("hidden");
        actorSelect.setAttribute("hidden", true);
    }
    else if(field == "actors"){
        newSelect.setAttribute("hidden", true);
        actorSelect.removeAttribute("hidden");
    }
    else{
        newSelect.setAttribute("hidden", true);
        actorSelect.setAttribute("hidden", true);
    }
}

function printFilms(filmstoPrint){
    if(document.getElementById("newTable") != null){
        document.getElementById("newTable").parentNode.removeChild(document.getElementById("newTable"));
    }
    let newTable = document.createElement("table");
    newTable.className = "w3-table-all w3-hoverable";
    newTable.id = "newTable";
    document.getElementById("filmListDiv").appendChild(newTable);
    let headers = ["Title","Description","Category","Price","Length","Rating","Actors"];
    for(let i = 0; i < headers.length; i++){
        let newHeader = document.createElement("TH");
        newHeader.innerHTML = headers[i];
        newTable.appendChild(newHeader);
    }


    let filmsPerPage = document.getElementById("pageSize").value;

    for(let i = ((pageNo-1)*filmsPerPage); i < filmsPerPage*pageNo; i++){
        let newRow = document.createElement("TR");
        newRow.className = "w3-light-grey";
        for(key in filmstoPrint[i]){
            if(key == "fid"){
                continue;
            }
            let newData = document.createElement("TD")
            newData.innerHTML = filmstoPrint[i][key];
            console.log(filmstoPrint[i] + key);
            newRow.appendChild(newData);
        }
        newTable.appendChild(newRow);
        //results.push(films[i]);
    }
}

function btnSearchClick(){
    let searchValue = document.getElementById("searchCriteria").value.toLowerCase();
    if(searchValue == ""){
        getAllFilms();
    }
    else{
        let fieldToSearch = document.getElementById("searchField").value.toLowerCase();
        let set1 = new Set(["price","length"]);
        if(set1.has(fieldToSearch)){
            complexSearch();
        }
        else if(fieldToSearch == "actors"){
            actorSearch();
        }
        else{
            let results = [];
            for(i in films){
                if(films[i][fieldToSearch].toLowerCase().includes(searchValue)){
                    results.push(films[i]);
                }
            }
            printFilms(results);
        }
    }
}

function complexSearch(){
    let results = [];
    let searchValue = document.getElementById("searchCriteria").value.toLowerCase();
    let fieldToSearch = document.getElementById("searchField").value.toLowerCase();
    let comparitor = document.getElementById("comparitor").value;
    for(i in films){
        switch(comparitor){
            case "=":
                if(films[i][fieldToSearch].toLowerCase() == searchValue){
                    results.push(films[i]);
                }
                break;
            case "<":
                if(films[i][fieldToSearch].toLowerCase() < searchValue){
                    results.push(films[i]);
                }
                break;
            case ">":
                if(films[i][fieldToSearch].toLowerCase() > searchValue){
                    results.push(films[i]);
                }
                break;
        }
        printFilms(results);
    }
}

function actorSearch(){
    let searchType = document.getElementById("actorComparitor").value;

    let actors = [];
    actors = document.getElementById("searchCriteria").value.toLowerCase().split(",");
    let results = [];
    for(i in films){
        let toAdd = false;
        let failed = false;
        for(a in actors){
            if(films[i]["actors"].toLowerCase().includes(actors[a])){
                toAdd = true;
            }
            else{
                if(searchType == "all"){
                    toAdd = false;
                    failed = true;
                }
            }
        }
        if(toAdd && failed == false){
            results.push(films[i]);
        }
    }
    printFilms(results);
}

function nextPage(){
    pageNo = pageNo + 1;
    btnSearchClick();
}

function prevPage(){
    if(pageNo > 1){
        pageNo = pageNo - 1;
    }
    btnSearchClick();
}
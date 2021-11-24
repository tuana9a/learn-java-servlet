"use strict"

let contentTag = document.getElementById("content");
let urlTag = document.getElementById("url");

async function getFolder(url = "") {
    let promise = new Promise((resolve, reject) => {

        let xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                try {
                    let list = JSON.parse(this.responseText);
                    updateContent(list);
                    updateUrl(url);
                } catch (e) {

                }
                resolve("success");
            }
        };
        //GET
        xhttp.open("GET", url);
        xhttp.send();

        setTimeout(() => {
            reject("failed");
        }, 5000);
    });
    return promise;
}

function getFile(url = "") {
    window.location.href = url;
}

function updateContent(files = []) {
    contentTag.innerHTML = "";
    for (const file of files) {
        let newDiv = document.createElement("div");
        let name = file["name"];
        let url = file["url"];

        if (file["type"] == "folder") {
            newDiv.className = "folder same";
            newDiv.innerHTML = `
                <div class="img">
                    <img class="img" src="img/folder.PNG"></img>
                </div>
                <div class="name">
                    <code class="name">${name}</code>
                </div>
            `;
            newDiv.ondblclick = function () {
                getFolder(url).then().catch();
            }

        } else {
            newDiv.className = "file same";
            newDiv.innerHTML = `
                <div class="img">
                    <img class="img" src="img/file.PNG"></img>
                </div>
                <div class="name">
                    <code class="name">${name}</code>
                </div>
            `;
            newDiv.ondblclick = function () {
                getFile(url);
            }

        }

        contentTag.appendChild(newDiv);
    }
}

function updateUrl(newUrl = "") {
    urlTag.innerHTML = "";
    if (newUrl.indexOf("/") == 0) {
        newUrl = newUrl.slice(1);
    }
    if (newUrl.lastIndexOf("/") == newUrl.length - 1) {
        newUrl = newUrl.slice(0, -1);
    }
    let urls = newUrl.split("/");
    for (let i = 0, length = urls.length; i < length; i++) {
        let name = urls[i];
        let url = "/" + urls.reduce((total, value, index) => {
            if (index > i) return total + "";
            return total + value + "/";
        }, "");

        let newCode = document.createElement("code");
        newCode.className = "url";
        newCode.innerText = name;
        newCode.onclick = function () {
            getFolder(url).then().catch();
        }

        urlTag.appendChild(newCode);
    }
}

getFolder("/resource/explorer/").then().catch();

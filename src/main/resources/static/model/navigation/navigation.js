// 页面跳转
function jump(target) {
    var source = "http://localhost:9090/model";
    location.href = source + target + ".html";
}

// title自动提示
function listTitles() {
    var searchTitle = document.getElementById("search");
    var keyword = searchTitle.value;
    if (keyword != null) {
        var url = "http://localhost:9090/blog/listTitles?keyword=" + keyword;
        ajaxGet(url, showTitleList, null, null);
    }
}

// 展示可选标题
function showTitleList(responseStr) {
    var response = JSON.parse(responseStr);
    if (response.code === 1) {
        var titles = response.data;
        // 创建datalist列表
        var datalistE = document.createElement("datalist");
        datalistE.id = "titleList";
        var optionStr = "";
        for (var i = 0; i < titles.length; i++) {
            var title = titles[i];
            optionStr += "<option value=\"" + title + "\">" + title + "</option>";
        }
        datalistE.innerHTML = optionStr;

        var searchE = document.getElementById("search");
        searchE.parentElement.appendChild(datalistE);
    }
}

// 根据title搜索
function search() {
    document.addEventListener("keydown", searchByTitle);
}

// keyCode：enter：13
function searchByTitle(event) {
    var keyCode = event.keyCode;
    switch (keyCode) {
        case 13:
            var searchE = document.getElementById("search");
            var title = searchE.value;
            location.href = "http://localhost:9090/model/search/search.html?title=" + title;
            break;
        default:
            break;
    }
}
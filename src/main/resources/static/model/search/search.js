listSearchResult();

function listSearchResult() {
    var url = location.search;
    var index = url.indexOf("?");
    if (index !== -1) {
        var param = url.substring(index + 1, url.length);
        var mapping = param.split("=");
        if (mapping[0] === "title") {
            searchByTitle(mapping[1]);
        }
    }
}

function searchByTitle(title) {
    // url解码
    var titleDecode = decodeURIComponent(title);
    var url = "http://localhost:9090/blog/search";
    var json = {
        "title": titleDecode
    };
    ajaxPost(url, JSON.stringify(json), showBlogs, null, function () {
        console.log("response unComplete: searchByTitle");
    });
}
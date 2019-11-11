function listTags() {
    var url = "http://localhost:9090/blog/listTags";
    ajaxGet(url, printTags, null, function () {
        console.log("get request unfinished: listTags");
    });
}

function printTags(responseStr) {
    var response = JSON.parse(responseStr);
    if (response.code === 1) {
        var innerTag = document.getElementById("innerTag");
        var tags = response.data;
        for (var i = 0; i < tags.length; i++) {
            var tag = tags[i];
            var wordCloudSpanE = document.createElement("span");
            wordCloudSpanE.textContent = tag;
            wordCloudSpanE.className = "wordCloud";
            innerTag.appendChild(wordCloudSpanE);
        }
    }
}
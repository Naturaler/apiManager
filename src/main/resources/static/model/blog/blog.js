loadBlog();
// showTime();

function loadBlog() {
    var url = location.search;
    var index = url.indexOf("?");
    if (index !== -1) {
        var param = url.substring(index + 1, url.length);
        var mapping = param.split("=");
        if (mapping[0] === "blogId") {
            getByBlogId(mapping[1]);
        }
    }
}

function getByBlogId(id) {
    var url = "http://localhost:9090/blog/getByBlogId?id=" + id;
    ajaxPost(url, null, parseResponse, null, null)
}

function parseResponse(responseStr) {
    var response = JSON.parse(responseStr);
    if (response.code === 1) {
        var blogDto = response.data;
        parseBlog(blogDto);
    } else {
        console.log("数据异常 " + responseStr)
    }
}

function parseBlog(blogDto) {
    var title = blogDto.title;
    var description = blogDto.description;
    var insertTime = new Date(blogDto.insertTime);

    // 标题
    var titleE = document.getElementById("blogTitle");
    titleE.textContent = title;

    // 时间
    var insertTimeE = document.getElementById("insertTime");
    insertTimeE.textContent = insertTime.getFullYear() + "." + (insertTime.getMonth() + 1) + "." + insertTime.getDate();

    // 描述
    var descriptionE = document.getElementById("blogDescription");
    descriptionE.textContent = description;

    // 博文
    var blog = blogDto.blog;
    var lines = blog.split("\n");
    var code = "";
    var language = "";
    var codeFlag = false;
    for (var i = 0, len = lines.length; i < len; i++) {
        var content = lines[i];
        console.log("codeFlag:" + codeFlag);
        // h1 h2 h3...
        var tagH = /(#+)\s+(.+)/;
        var isH = tagH.test(content);
        if (isH) {
            console.log(lines[i]);
            printH(RegExp.$1.length, RegExp.$2);
            continue;
        }
        // code start
        var codeStartReg = /```\s+(\w*)/;
        if (codeStartReg.test(content)) {
            codeFlag = true;
            language = RegExp.$1;
            console.log("language:" + language);
            continue;
        }
        // code end
        var codeEndReg = /```/;
        if (codeEndReg.test(content)) {
            // 输出code
            printCode(language, code);
            // 还原code变量
            code = "";
            codeFlag = false;
            continue;
        }
        if (codeFlag) {
            code = code + content + "\n";
        } else {
            printOriginal(content);
        }
    }
    // 加载高亮脚本
    loadHighlightScript();
}

// h1 h2 h3 ...
function printH(Hlevel, content) {
    var HE = document.createElement("h1");
    switch (Hlevel) {
        case 1:
            break;
        case 2:
            HE = document.createElement("h2");
            break;
        case 3:
            HE = document.createElement("h3");
            break;
        case 4:
            HE = document.createElement("h4");
            break;
        case 5:
            HE = document.createElement("h5");
            break;
        default:
            HE = document.createElement("h1");
    }
    HE.textContent = content;

    var kernelE = document.getElementById("kernel");
    kernelE.appendChild(HE);
}

// code
function printCode(language, code) {
    var codeE = document.createElement("code");
    codeE.textContent = code;
    if (language.length !== 0) {
        codeE.className = language;
    }

    var preE = document.createElement("pre");
    preE.appendChild(codeE);

    var kernelE = document.getElementById("kernel");
    kernelE.appendChild(preE);
}

// 普通内容
function printOriginal(content) {
    var pE = document.createElement("p");
    pE.textContent = content;
    var kernelE = document.getElementById("kernel");
    kernelE.appendChild(pE);
}

// 高亮代码
function loadHighlightScript() {
    console.log("加载高亮脚本");
    var codes = document.getElementsByTagName("pre");
    for (var i = 0, len = codes.length; i < len; i++) {
        var block = codes[i];
        hljs.highlightBlock(block);
    }
}

// 编辑博文
function editBlog() {
    var url = location.search;
    var index = url.indexOf("?");
    var blogId = -1;
    if (index !== -1) {
        var param = url.substring(index + 1, url.length);
        var mapping = param.split("=");
        if (mapping[0] === "blogId") {
            blogId = mapping[1];
        }
    }
    location.href = "http://localhost:9090/model/edit/edit.html?blogId=" + blogId;
}
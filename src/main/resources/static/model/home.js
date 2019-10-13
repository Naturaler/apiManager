function loadBlog() {
    var url = "http://localhost:9090/blog/api";
    ajaxPost(url, null, parseBlog, null, null)
}

function getById() {
    var id = document.getElementById("blogId").value;
    var url = "http://localhost:9090/blog/get?id=" + id;
    ajaxPost(url, null, parseBlog, null, null)
}

// 加载列表
function listBlog() {
    var url = "http://localhost:9090/blog/list";
    ajaxPost(url, null, showBlogs, null, null);
    // showTime();
}

function showBlogs(responseStr) {
    var response = JSON.parse(responseStr);
    if (response.code === 1) {
        var blogs = response.data;
        for (var i = 0; i < blogs.length; i++) {
            var blog = blogs[i];
            printBlog(blog.id, blog.title, blog.description, blog.insertTime, blog.tags);
        }
    }
}

function printBlog(blogId, title, description, insertTime, tags) {
    // 标题
    var titleH2E = document.createElement("h2");
    titleH2E.innerHTML = "<span onclick='readBlog(" + blogId + ")'>" + title + "</span>";
    // 摘要
    var descPE = document.createElement("p");
    descPE.textContent = description;
    // 时间和标签
    var infoPE = document.createElement("p");
    var date = new Date(insertTime);
    var insertTimeSpanE = document.createElement("span");
    insertTimeSpanE.textContent = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    insertTimeSpanE.className = "blogDate";
    infoPE.appendChild(insertTimeSpanE);
    var tagArray = tags.split(",");
    for (var i = 0; i < tagArray.length; i++) {
        var tagSpanE = document.createElement("span");
        tagSpanE.textContent = tagArray[i];
        tagSpanE.className = "blogTag";
        infoPE.appendChild(tagSpanE);
    }
    // 换行符
    var hrE = document.createElement("hr");
    // 组装
    var blogItemE = document.createElement("div");
    blogItemE.className = "blogItem";
    blogItemE.appendChild(titleH2E);
    blogItemE.appendChild(descPE);
    blogItemE.appendChild(infoPE);
    blogItemE.appendChild(hrE);

    var kernel = document.getElementById("kernel");
    kernel.appendChild(blogItemE);
}
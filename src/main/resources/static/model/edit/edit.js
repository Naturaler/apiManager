// 保存blog
function saveBlog() {
    var blogTitle = document.getElementById("blogTitle").value;
    var blogTag = document.getElementById("blogTag").value;
    var blogContent = document.getElementById("blogContent").value;
    var blogDescription = document.getElementById("blogDescription").value;
    var blogCategory = document.getElementById("blogCategory").value;
    var url = "http://localhost:9090/blog/save";
    var vo = {
        "title": blogTitle,
        "tag": blogTag,
        "blog": blogContent,
        "description": blogDescription,
        "category": blogCategory
    };
    var blogId = getBlogIdFromUrl();
    if (blogId != null) {
        vo.id = blogId;
    }
    ajaxPost(url, JSON.stringify(vo), backToHome, null, function () {
        console.log("response unComplete: saveBlog");
    });
}

function backToHome(responseStr) {
    // 返回首页
    jump("/home");
}

function getBlogIdFromUrl() {
    var currentUrl = location.search;
    var index = currentUrl.indexOf("?");
    if (index !== -1) {
        var param = currentUrl.substring(index + 1, currentUrl.length);
        var mapping = param.split("=");
        if (mapping[0] === "blogId") {
            return mapping[1];
        }
    }
    return null;
}

// 加载待编辑blog
loadEditingBlog();

function loadEditingBlog() {
    var blogId = getBlogIdFromUrl();
    if (blogId != null) {
        var url = "http://localhost:9090/blog/getByBlogId?id=" + blogId;
        ajaxPost(url, null, printEditingBlog, null, function () {
            console.log("response unComplete: loadEditingBlog");
        })
    }
}

function printEditingBlog(responseStr) {
    var response = JSON.parse(responseStr);
    if (response.code === 1) {
        var blogDto = response.data;
        // 标题
        var blogTitle = document.getElementById("blogTitle");
        blogTitle.value = blogDto.title;
        // 描述
        var blogDescription = document.getElementById("blogDescription");
        blogDescription.value = blogDto.description;
        // 标签
        var blogTag = document.getElementById("blogTag");
        blogTag.value = blogDto.tags;
        // 博文
        var blogContent = document.getElementById("blogContent");
        blogContent.value = blogDto.blog;
        // 类别
        var blogCategory = document.getElementById("blogCategory");
        blogCategory.selected = blogDto.category;
    } else {
        console.log("数据异常 " + responseStr)
    }
}

function softDeleteBlog() {
    var blogId = getBlogIdFromUrl();
    var url = "http://localhost:9090/blog/deleteById?id=" + blogId;
    ajaxGet(url, null, null, function () {
        console.log("get request unfinished: softDeleteBlog");
    });
}

// 加载category
listCategories();

function listCategories() {
    var url = "http://localhost:9090/config/listCategories";
    ajaxGet(url, printCategory, null, function () {
        console.log("get request unfinished: listCategories");
    });
}

function printCategory(responseStr) {
    var response = JSON.parse(responseStr);
    if (response.code === 1) {
        var blogCategory = document.getElementById("blogCategory");
        var data = response.data;
        for (var i = 0; i < data.length; i++) {
            var category = data[i];
            var optionE = document.createElement("option");
            optionE.value = category;
            optionE.textContent = category;
            blogCategory.appendChild(optionE);
        }
    }
}
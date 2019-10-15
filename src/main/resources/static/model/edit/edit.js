// 保存blog
function saveBlog() {
    var blogTitle = document.getElementById("blogTitle").value;
    var blogTag = document.getElementById("blogTag").value;
    var blogContent = document.getElementById("blogContent").value;
    var blogDescription = document.getElementById("blogDescription").value;
    var url = "http://localhost:9090/blog/save";
    var vo = {
        "title": blogTitle,
        "tag": blogTag,
        "blog": blogContent,
        "description": blogDescription
    };
    var blogId = getBlogIdFromUrl();
    if (blogId != null) {
        vo.id = blogId;
    }
    ajaxPost(url, JSON.stringify(vo), null, null, null);
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
        ajaxPost(url, null, printEditingBlog, null, null)
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
    } else {
        console.log("数据异常 " + responseStr)
    }
}

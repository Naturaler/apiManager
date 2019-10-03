function loadBlog() {
    var url = "http://localhost:9090/blog/api";
    ajaxPost(url, null, parseBlog, null, null)
}

function getById() {
    var id = document.getElementById("blogId").value;
    var url = "http://localhost:9090/blog/get?id=" + id;
    ajaxPost(url, null, parseBlog, null, null)
}

function getByBlogId(id) {
    location.href = "http://localhost:9090/model/blog/blog.html?blogId=" + id;
}
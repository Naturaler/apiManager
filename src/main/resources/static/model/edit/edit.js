// 保存blog
function saveBlog() {
    var blogTitle = document.getElementById("blogTitle").value;
    var blogTag = document.getElementById("blogTag").value;
    var blogContent = document.getElementById("blogContent").value;
    var url = "http://localhost:9090/blog/save";
    var vo = {
        "title": blogTitle,
        "tag": blogTag,
        "blog": blogContent
    };
    ajaxPost(url, JSON.stringify(vo), parseBlog, null, null)
}
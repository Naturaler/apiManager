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
    ajaxPost(url, JSON.stringify(vo), null, null, null);
    // 返回首页
    jump("/home");
}
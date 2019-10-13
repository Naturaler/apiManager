function loadArchives() {
    var url = "http://localhost:9090/blog/listArchives";
    ajaxGet(url, printArchive, null, null);
}

function printArchive(responseStr) {
    var response = JSON.parse(responseStr);
    if (response.code === 1) {
        var data = response.data;
        var table = document.createElement("table");
        for (var i = 0; i < data.length; i++) {
            var archive = data[i];
            // 年份
            var year = archive.year;
            // var yearTdE = document.createElement("td");
            // yearTdE.colSpan = 2;
            // yearTdE.className = "year";
            // yearTdE.textContent = year;
            // yearTrE.appendChild(yearTdE);
            var yearTrE = document.createElement("tr");
            yearTrE.innerHTML = "<td colspan=\"2\" class=\"year\">" + year + "</td>";
            table.appendChild(yearTrE);

            var blogs = archive.archiveBlogs;
            var right = true;
            for (var j = 0; j < blogs.length; j++) {
                var blog = blogs[j];
                var blogId = blog.id;
                var title = blog.title;

                if (right) {
                    var rightTrE = document.createElement("tr");
                    rightTrE.innerHTML = "<td class=\"borderRight\" onclick='readBlog(" + blogId + ")'>" + title + "</td><td></td>";
                    table.appendChild(rightTrE);
                    right = false;
                } else {
                    var leftTrE = document.createElement("tr");
                    leftTrE.innerHTML = "<td></td><td class=\"borderLeft\" onclick='readBlog(" + blogId + ")'>" + title + "</td>";
                    table.appendChild(leftTrE);
                    right = true;
                }
            }
        }
        var kernel = document.getElementById("kernel");
        kernel.appendChild(table);
    }
}
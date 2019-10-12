function loadArchives(responseStr) {
    var url = "http://localhost:9090/";
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
            var yearTdE = document.createElement("td");
            yearTdE.colSpan = 2;
            yearTdE.className = "year";
            yearTdE.textContent = year;
            var yearTrE = document.createElement("tr");
            yearTrE.appendChild(yearTdE);
            table.appendChild(yearTrE);

            var blogs = archive.blogs;
            var right = true;
            for (var j = 0; j < blogs.length; j++) {
                var blog = blogs[j];
                var blogId = blog.id;
                var title = blog.title;

                if (right) {
                    var rightTrE = document.createElement("tr");
                    rightTrE.innerHTML = "<td class=\"borderRight\">" + title + "</td><td></td>";
                    table.appendChild(rightTrE);
                    right = false;
                } else {
                    var leftTrE = document.createElement("tr");
                    leftTrE.innerHTML = "<td></td><td class=\"borderLeft\">"+title+"</td>";
                    table.appendChild(leftTrE);
                    right = true;
                }
            }
        }
        var kernel = document.getElementById("kernel");
        kernel.appendChild(table);
    }
}
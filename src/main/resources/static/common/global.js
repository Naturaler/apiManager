// 显示当前时间
// function showTime() {
//     var date = new Date(); //日期对象
//     var now = "";
//     now = date.getFullYear() + "-"; //读英文就行了
//     now = now + (date.getMonth() + 1) + "-"; //取月的时候取的是当前月-1如果想取当前月+1就可以了
//     now = now + date.getDate() + "-";
//     now = now + date.getHours() + ":";
//     now = now + date.getMinutes() + ":";
//     now = now + date.getSeconds() + "";
//     document.getElementById("nowDiv").innerHTML = now; //div的html是now这个字符串
//     setTimeout("showTime()", 1000); //设置过1000毫秒就是1秒，调用show方法
// }

// 刷新页面
function refreshPage() {
    location.reload();
}

// ajax post请求：
function ajaxPost(url, data, fnSucceed, fnFail, fnLoading) {
    var ajax = ajaxObject();
    ajax.open("post", url, true);
    ajax.setRequestHeader("Content-Type", "application/json");
    ajax.onreadystatechange = function () {
        if (ajax.readyState === 4) {
            if (ajax.status === 200) {
                fnSucceed(ajax.responseText);
            } else {
                fnFail("HTTP请求错误！错误码：" + ajax.status);
            }
        } else {
            fnLoading();
        }
    };
    ajax.send(data);
}

// ajax get请求：
function ajaxGet(url, fnSucceed, fnFail, fnLoading) {
    var ajax = ajaxObject();
    ajax.open("get", url, true);
    ajax.onreadystatechange = function () {
        if (ajax.readyState === 4) {
            if (ajax.status === 200) {
                fnSucceed(ajax.responseText);
            } else {
                fnFail("HTTP请求错误！错误码：" + ajax.status);
            }
        } else {
            fnLoading();
        }
    };
    ajax.send();
}

// ajax 对象
function ajaxObject() {
    var xmlHttp;
    try {
        // Firefox, Opera 8.0+, Safari
        xmlHttp = new XMLHttpRequest();
    } catch (e) {
        // Internet Explorer
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                alert("您的浏览器不支持AJAX！");
                return false;
            }
        }
    }
    return xmlHttp;
}

// 查看博文
function readBlog(id) {
    location.href = "http://localhost:9090/model/blog/blog.html?blogId=" + id;
}
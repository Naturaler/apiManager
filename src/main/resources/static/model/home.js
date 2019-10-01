function showTime() {
    var date = new Date(); //日期对象
    var now = "";
    now = date.getFullYear() + "-"; //读英文就行了
    now = now + (date.getMonth() + 1) + "-"; //取月的时候取的是当前月-1如果想取当前月+1就可以了
    now = now + date.getDate() + "-";
    now = now + date.getHours() + ":";
    now = now + date.getMinutes() + ":";
    now = now + date.getSeconds() + "";
    document.getElementById("nowDiv").innerHTML = now; //div的html是now这个字符串
    setTimeout("showTime()", 1000); //设置过1000毫秒就是1秒，调用show方法
}

function loadBlog() {
    var url = "http://localhost:9090/blog/api"
    ajaxPost(url, null, parseBlog, null, null)
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

function parseBlog(blog) {
    // var blog = "# spring boot整合mybatis\n" +
    //     "spring boot项目整合mybatis\n" +
    //     "### jdbc配置\n" +
    //     "``` \n" +
    //     "spring.datasource.url=jdbc:mysql://localhost:3306/data_source?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true\n" +
    //     "spring.datasource.username=root\n" +
    //     "spring.datasource.password=root\n" +
    //     "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver\n" +
    //     "```\n" +
    //     "### mybatis相关配置\n" +
    //     "``` xml\n" +
    //     "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
    //     "<!DOCTYPE generatorConfiguration\n" +
    //     "  PUBLIC \"-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN\"\n" +
    //     "  \"http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd\">\n" +
    //     "\n" +
    //     "<generatorConfiguration>\n" +
    //     "    <classPathEntry location=\"C:\\Users\\r.x\\.m2\\repository\\mysql\\mysql-connector-java\\8.0.17\\mysql-connector-java-8.0.17.jar\" />\n" +
    //     "\n" +
    //     "    <context id=\"DB2Tables\" targetRuntime=\"MyBatis3\">\n" +
    //     "        <jdbcConnection driverClass=\"com.mysql.cj.jdbc.Driver\"\n" +
    //     "                        connectionURL=\"jdbc:mysql://localhost:3306/data_source?serverTimezone=UTC\"\n" +
    //     "                        userId=\"root\"\n" +
    //     "                        password=\"root\">\n" +
    //     "```\n" +
    //     "### mapper代码示例\n" +
    //     "``` java\n" +
    //     "\n" +
    //     "package com.yrx.datasourcemanager.manager.dao.extend;\n" +
    //     "\n" +
    //     "import com.yrx.datasourcemanager.manager.pojo.ParamConfig;\n" +
    //     "import org.apache.ibatis.annotations.Result;\n" +
    //     "import org.apache.ibatis.annotations.Results;\n" +
    //     "import org.apache.ibatis.annotations.Select;\n" +
    //     "import org.apache.ibatis.type.JdbcType;\n" +
    //     "\n" +
    //     "import java.util.List;\n" +
    //     "\n" +
    //     "/**\n" +
    //     " * Created by r.x on 2019/9/2.\n" +
    //     " */\n" +
    //     "public interface ParamConfigExtendMapper {\n" +
    //     "}\n" +
    //     "```";
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
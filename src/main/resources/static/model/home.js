function show() {
    var date = new Date(); //日期对象
    var now = "";
    now = date.getFullYear() + "-"; //读英文就行了
    now = now + (date.getMonth() + 1) + "-"; //取月的时候取的是当前月-1如果想取当前月+1就可以了
    now = now + date.getDate() + "-";
    now = now + date.getHours() + ":";
    now = now + date.getMinutes() + ":";
    now = now + date.getSeconds() + "";
    document.getElementById("nowDiv").innerHTML = now; //div的html是now这个字符串
    setTimeout("show()", 1000); //设置过1000毫秒就是1秒，调用show方法
}
function showDate(id) {
    var today = new Date();
    var days = ["星期日",
        "星期一",
        "星期二",
        "星期三",
        "星期四",
        "星期五",
        "星期六" ];
    
    var hours = today.getHours();
    var minutes = today.getMinutes();
    var seconds = today.getSeconds();

    if (hours <= 9) {
    	hours = "0" + hours;
    }
    if (minutes <= 9) {
        minutes = "0" + minutes;
    }
    if (seconds <= 9) {
        seconds = "0" + seconds;
    }

	var txtDate = today.getFullYear() + "年" + (today.getMonth() + 1) + "月" + today.getDate() + "日 " + days[today.getDay()];
    //var txtTime = hours + ":" + minutes + ":" + seconds;
   // document.getElementById(id).innerHTML = txtTime;

    document.getElementById(id).innerHTML = "&nbsp;&nbsp;" + txtDate + "  ";// + txtTime
            
    setTimeout("showDate('" + id + "')", 1000)
}
$(function(){
    showDate("date");
})
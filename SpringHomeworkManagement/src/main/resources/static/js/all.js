$("document").ready(function(){
    function SimpleDateFormat(pattern) {
        var fmt = new Object();
        fmt.pattern = pattern;

        fmt.parse = function (source) {
            try {
                return new Date(source);
            } catch (e) {
                console.log("字符串 " + source + " 转时间格式失败！");
                return null;
            }
        };

        fmt.format = function (date) {
            if (typeof (date) == "undefined" || date == null || date == "") {
                return "";
            }

            try {
                date = new Date(date);
            } catch (e) {
                console.log("时间 " + date + " 格式化失败！");
                return "";
            }

            var strTime = this.pattern;//时间表达式的正则

            var o = {
                "M+": date.getMonth() + 1, //月份
                "d+": date.getDate(), //日
                "H+": date.getHours(), //小时
                "m+": date.getMinutes(), //分
                "s+": date.getSeconds(), //秒
                "q+": Math.floor((date.getMonth() + 3) / 3), //季度
                "S": date.getMilliseconds() //毫秒
            };

            if (/(y+)/.test(strTime)) {
                strTime = strTime
                    .replace(RegExp.$1, (date.getFullYear() + "")
                        .substr(4 - RegExp.$1.length));
            }
            for (var k in o) {
                if (new RegExp("(" + k + ")").test(strTime)) {
                    strTime = strTime.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            }

            return strTime;
        };
        return fmt;
    }
    var sdf = new SimpleDateFormat("yyyy-MM-dd");

    $.post("ucontroller/login3",
        function(data){
            console.log(data);
            $("#usernamespan").text(data.loginname);
            if(data.status+"" == "教师"){
                $("#select3").show();
                $("#select4").show();
            }
        }, "json");

    $("#searchbtn").click(function(){
        var searchDateVal = $("#dateserarch").val();
        var searchTypeVal = $("#typeserarch").val();
        console.log(searchDateVal);
        console.log(searchTypeVal);
        $.getJSON("hcontroller/getbydateandtype",{opentime:searchDateVal, type:searchTypeVal},function(json){
            console.log(json);
            if(json.length == 0){
                $("#allbody").empty();
                alert("未查询到结果");
            }
            else{
                $("#allbody").empty();

                for(var i = 0; i < json.length; i++){
                    $("#allbody").append(
                        "<tr id='tridval1"+json[i].hid+"'>"+
                        "<td>" + json[i].hid + "</td>"+
                        "<td>" + json[i].title + "</td>"+
                        "<td>" + json[i].content + "</td>"+
                        "<td>" + sdf.format(json[i].openTime) + "</td>"+
                        "<td>" + json[i].type + "</td>"+
                        "</tr>"
                    );
                }
            }





            $("button[name='modbtn']").click(function(){
                var modbtnid = this.id;
                var id = modbtnid.substr(6);
                $("#trid2"+id).show();
                $("#tridval1"+id).hide();
            });

        });

    });
});
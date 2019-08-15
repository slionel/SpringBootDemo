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

    $.post("pcontroller/login3",
        function(data){
            console.log(data);
            $("#usernamespan").text(data.loginname);
        }, "json");

    $("#deletesearchbtn").click(function(){
        var searchVal = $("#deleteserarch").val();
        console.log(searchVal);
        $.getJSON("pcontroller/getbyname",{pname:searchVal},function(json){
            console.log(json);
            if(json.length == 0){
                alert("未查询到结果");
            }
            else{
                for(var i = 0; i < json.length; i++){
                    $("#deletebody").append(
                        "<tr id='tridval1"+json[i].pid+"'>"+
                        "<td>" + json[i].pid + "</td>"+
                        "<td>" + json[i].pname + "</td>"+
                        "<td>" + json[i].category + "</td>"+
                        "<td>" + json[i].stock + "</td>"+
                        "<td>" + sdf.format(json[i].produceDate) + "</td>"+
                        "<td>" + json[i].shelfLife + "</td>"+
                        "<td>" + json[i].price + "</td>"+
                        "<td>"+
                        "<button name='delbtn' type='button' class='btn btn-danger' id='delbtn"+json[i].pid+"'>删除</button>"+
                        "</td>"+
                        "</tr>"
                    );
                }
            }



            $("button[name='delbtn']").click(function(){
                var delbtnid = this.id;
                var id = delbtnid.substr(6);
                $.getJSON("pcontroller/delete",{id:id},function(json){
                    if(json.result+"" == "true"){
                        alert("删除成功");
                        window.location.href="table.html";
                    }
                    else{
                        alert("删除失败");
                    }
                });
            });
        });
    });
});
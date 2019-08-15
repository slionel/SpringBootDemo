$(document).ready(function(){

    $.post("pcontroller/login3",
        function(data){
            $("#usernamespan").text(data.loginname);
        }, "json");
});

    $.getJSON("pcontroller/all",function(json){function SimpleDateFormat(pattern) {
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
        for(var i = 0; i < json.length;i++){
            $("#tbody").append(
                "<tr id='trid1"+json[i].pid+"'>"+
                    "<td>" + json[i].pid + "</td>"+
                    "<td>" + json[i].pname + "</td>"+
                    "<td>" + json[i].category + "</td>"+
                    "<td>" + json[i].stock + "</td>"+
                    "<td>" + sdf.format(json[i].produceDate) + "</td>"+
                    "<td>" + json[i].shelfLife + "</td>"+
                    "<td>" + json[i].price + "</td>"+
                    "<td>"+
                    "<button name='btn1' type='button' class='btn btn-info' id='btn1"+json[i].pid+"'>修改 </button>&nbsp;&nbsp;"+
                    /*"<button name='btn2' type='button' class='btn btn-danger' id='btn2"+json[i].pid+"'>删除</button>"+*/
                    "</td>"+
                "</tr>"
        );
            $("#tbody").append(
                "<tr style='display: none' id='trid2"+json[i].pid+"'><form>" +
                "<td>" + json[i].pid + "</td>"+
                /*"<td><input type='text' id='pid' value='"+json[i].pid+"'/></td>"+*/
                "<td><input type='text' id='pname' value='"+json[i].pname+"'/></td>"+
                "<td><input type='text' id='category' value='"+json[i].category+"'/></td>"+
                "<td><input type='text' id='stock' value='"+json[i].stock+"'/></td>"+
                "<td><input type='date' id='producedate' value='"+sdf.format(json[i].produceDate)+"'/></td>"+
                "<td><input type='text' id='shelflife' value='"+json[i].shelfLife+"'/></td>"+
                "<td><input type='text' id='price' value='"+json[i].price+"'/></td>"+
                "<td><button type='button' class='btn btn-primary' id='btn3"+json[i].pid+"' name='btn3'>保存</button></td>"+
                "</form><td></td></tr>"
            );
        }
        $("button[name='btn1']").click(function(){
            var btn1id = this.id;
            var num = btn1id.substr(4);
            $("#trid1"+num).hide();
            $("#trid2"+num).show();

        });


        $("button[name='btn3']").click(function(){
            var btn3id = this.id;
            var num = btn3id.substr(4);
            var pname = $("#pname").val();
            var category = $("#category").val();
            var stock = $("#stock").val();
            var produceDate = $("#producedate").val();
            var shelfLife = $("#shelflife").val();
            var price = $("#price").val();
            $.getJSON("pcontroller/update",{id:num,pname:pname,category:category,stock:stock,produceDate:produceDate,shelfLife:shelfLife,price:price},function(json){
                if(json.result+"" == "true"){
                    window.location.href="table.html";
                }
                else{
                    alert("修改失败");
                }
            });
            $("#trid1"+num).show();
            $("#trid2"+num).hide();
        });


        /*$("button[name='btn2']").click(function(){
            var btn2id = this.id;
            var id = btn2id.substr(4);
            $.getJSON("delete",{id:id},function(json){
                if(json.result+"" == "true"){
                    window.location.href="table.html";
                }
                else{
                    alert("删除失败");
                }
            });
        });*/


});

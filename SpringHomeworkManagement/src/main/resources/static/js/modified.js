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

    $("#deletesearchbtn").click(function(){
        var searchDateVal = $("#dateserarch").val();
        var searchTypeVal = $("#typeserarch").val();
        console.log(searchDateVal);
        console.log(searchTypeVal);
        $.getJSON("hcontroller/getbydateandtype",{opentime:searchDateVal, type:searchTypeVal},function(json){
            console.log(json);
            if(json.length == 0){
                alert("未查询到结果");
            }
            else{
                $("#modifiedbody").empty();

                for(var i = 0; i < json.length; i++){
                    $("#modifiedbody").append(
                        "<tr id='tridval1"+json[i].hid+"'>"+
                        "<td>" + json[i].hid + "</td>"+
                        "<td>" + json[i].title + "</td>"+
                        "<td>" + json[i].content + "</td>"+
                        "<td>" + sdf.format(json[i].openTime) + "</td>"+
                        "<td>" + json[i].type + "</td>"+
                        "<td>"+
                        "<button name='modbtn' type='button' class='btn btn-info' id='modbtn"+json[i].hid+"'>修改</button>&nbsp;"+
                        "<button name='delbtn' type='button' class='btn btn-danger' id='delbtn"+json[i].hid+"'>删除</button>"+
                        "</td>"+
                        "</tr>"
                    );


                    $("#modifiedbody").append(
                        "<tr style='display: none' id='trid2"+json[i].hid+"'><form>" +
                        "<td>" + json[i].hid + "</td>"+
                        /*"<td><input type='text' id='pid' value='"+json[i].pid+"'/></td>"+*/
                        "<td><input type='text' id='title' value='"+json[i].title+"'/></td>"+
                        "<td><input type='text' id='content' value='"+json[i].content+"'/></td>"+
                        "<td><input type='date' id='openTime' value='"+sdf.format(json[i].openTime)+"'/></td>"+
                        "<td><input type='text' id='type' value='"+json[i].type+"'/></td>"+
                        "<td><button type='button' class='btn btn-primary' id='btn3"+json[i].hid+"' name='btn3'>保存</button></td>"+
                        "</form><td></td></tr>"
                    );





                }
            }



            $("button[name='delbtn']").click(function(){
                var delbtnid = this.id;
                var id = delbtnid.substr(6);
                $.getJSON("hcontroller/delete",{id:id},function(json){
                    if(json.result+"" == "true"){
                        alert("删除成功");
                        window.location.href="table.html";
                    }
                    else{
                        alert("删除失败");
                    }
                });
            });


            $("button[name='modbtn']").click(function(){
                var modbtnid = this.id;
                var id = modbtnid.substr(6);
                $("#trid2"+id).show();
                $("#tridval1"+id).hide();
            });


            $("button[name='btn3']").click(function(){
                var btn3id = this.id;
                console.log(btn3id);
                var id = btn3id.substr(4);
                console.log(id);
                var title = $("#title").val();
                console.log(title);
                var content = $("#content").val();
                console.log(content);
                var openTime = $("#openTime").val();
                console.log(openTime);
                var type = $("#type").val();
                console.log(type);
                $("#trid2"+id).show();
                $("#tridval1"+id).hide();
                $.getJSON("hcontroller/update",{id:id,title:title, content:content, openTime:openTime, type:type},function(json){
                    if(json.result+"" == "true"){
                        alert("修改成功");
                        window.location.href = "modified.html";
                    }
                    else{
                        alert("修改失败");
                        window.location.href = "modified.html";
                    }
                    $("#trid2"+id).hide();
                    $("#tridval1"+id).show();
                });
            });




        });

    });
});
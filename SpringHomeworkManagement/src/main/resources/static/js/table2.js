$(document).ready(function(){
    var uid;
    $.post("ucontroller/login3",
        function(data){
            console.log(data);
            uid = data.uid;
            $("#usernamespan").text(data.loginname);
            if(data.status+"" == "教师"){
                $("#select3").show();
                $("#select4").show();
            }


            $.getJSON("ucontroller/getbyid",{uid:uid},function(json){
                var fileName = json.imgId;
                console.log('/Path/'+fileName);
                $("#headuserimg").attr("src","http://localhost:8082/homework/image/"+fileName);
            });


        }, "json");


    $.getJSON("hcontroller/all",{type:"结对"},function(json){
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
        for(var i = 0; i < json.length;i++){
            $("#tbody").append(
                "<tr id='trid1"+json[i].hid+"'>"+
                "<td>" + json[i].hid + "</td>"+
                "<td>" + json[i].title + "</td>"+
                "<td>" + json[i].content + "</td>"+
                "<td>" + sdf.format(json[i].openTime) + "</td>"+
                "<td>" + json[i].type + "</td>"+
                "<td><button type='button' name='modalbtn' id='modal"+json[i].hid+"' class='btn btn-primary' data-toggle='modal' data-target='#myModal'>详情</button></td>"+
                "<td><button type='button' name='uploadmodalbtn' id='uploadmodal"+json[i].hid+"' class='btn btn-primary' data-toggle='modal' data-target='#uploadModal'>提交作业</button></td>"+
                "</tr>"
            );
        }


        $("button[name=modalbtn]").click(function(){
            var id = this.id;
            var hid = id.substr(5);
            console.log(id);
            console.log(hid);
            $.getJSON("hcontroller/submitdetail",{id:hid},function(json){
                $("#modalbody").empty();
                for(var i = 0; i < json.length; i++){
                    if("学生" == json[i].status){
                        $("#modalbody").append(
                            "<tr>" +
                            "<td>"+json[i].userName+"</td>" +
                            "<td>"+json[i].isSubmit+"</td>" +
                            "</tr>"
                        );
                    }

                }
            });
        });





        $("button[name='uploadmodalbtn']").click(function(){
            var id = this.id;
            var hid = id.substr(11);

            $("#uploadbtn").click(function(){
                var formData = new FormData(document.getElementById("fileUploadForm"));
                /*var formData = new FormData($("#fileUploadForm")[0]);*/
                $.ajax({
                    url:'hcontroller/upload',
                    type:'POST',
                    data:formData,
                    contentType:false,
                    async:false,
                    cache:false,
                    processData:false,
                    success:function (data) {
                        if(data.result+"" == "true"){
                            alert("文件提交成功");
                            $.getJSON("hcontroller/addmiddletable",{uid:uid,hid:hid},function(json){
                                console.log(json);
                                if(json.result+"" == "true"){
                                    $("#uploadModal").modal("hide");
                                }
                            });

                        }
                        else if(data.result+"" == "false"){
                            alert("文件提交失败");
                        }
                    },
                    error:function(data){
                        console.log(data)
                        if(data.result+"" == "false"){
                            alert("文件提交失败");
                        }
                    }
                });
            });
        });





    });
});



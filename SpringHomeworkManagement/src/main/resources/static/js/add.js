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

                $.getJSON("ucontroller/getbyid",{uid:uid},function(json){
                    var fileName = json.imgId;
                    console.log('/Path/'+fileName);
                    $("#userimg").attr("src","http://localhost:8082/homework/image/"+fileName);
                    $("#headuserimg").attr("src","http://localhost:8082/homework/image/"+fileName);
                });
            }
        }, "json");





    $("#addbtn").click(function(){
            $.getJSON("hcontroller/add",{title:$("#title").val(), content:$("#content").val(),opentime:$("#opentime").val(),type:$("#type").val()},function(json){
                console.log(json)
                if(json.result+"" == "true"){
                    alert("作业布置成功")
                    window.location.href="table.html";
                }
                else{
                    alert("作业布置失败");
                }
            });
        });
    });

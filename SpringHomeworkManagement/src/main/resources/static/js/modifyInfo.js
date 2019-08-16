$(document).ready(function(){var uid;
    var uid;
    var sex;
    var status;
    $.post("ucontroller/login3",
        function(data){
            console.log(data);
            uid = data.uid;
            sex = data.sex;
            status = data.status;
            $("#usernamespan").text(data.loginname);
            if(data.status+"" == "教师"){
                $("#select3").show();
                $("#select4").show();
            }


            $.getJSON("ucontroller/getbyid",{uid:uid},function(json){
                console.log(json);
                $("#username").val(json.name);
                $("#pwd").val(json.pwd);
                $("#repwd").val(json.pwd);
                $("#age").val(json.age);

                $("#updbtn").click(function(){
                    var username = $("#username").val();
                    var pwd = $("#pwd").val();
                    var repwd = $("#repwd").val();
                    var age = $("#age").val();
                    if(username.length > 0){
                        if(age.length>0){
                            if(age > 0){
                                if(pwd.length > 0){
                                    if(pwd+"" == repwd+""){
                                        $.getJSON("ucontroller/updatedata",{uid:uid,username:username,pwd:pwd,age:age,sex:sex,status:status},function(json){
                                            if(json.result+"" == "true"){
                                                alert("修改成功，请重新登录");
                                                window.location.href="login.html";
                                            }
                                            else{
                                                alert("修改失败");
                                            }
                                        });
                                    }
                                    else{
                                        alert("两次密码输入不一致");
                                    }
                                }
                                else{
                                    alert("密码不能为空");
                                }
                            }
                            else{
                                alert("年龄不能小于0");
                            }
                        }
                        else{
                            alert("年龄不能为空");
                        }
                    }
                    else{
                        alert("用户名不能为空");
                    }
                });
            });


            $("#uploadbtn").click(function(json){
                var formData = new FormData(document.getElementById("fileUploadForm"));
                $.ajax({
                    url:'ucontroller/imgupload',
                    type:'POST',
                    data:formData,
                    contentType:false,
                    async:false,
                    cache:false,
                    processData:false,
                    success:function (data) {
                        var username = $("#username").val();
                        var pwd = $("#pwd").val();
                        var repwd = $("#repwd").val();
                        var age = $("#age").val();
                        if(data.result+"" == "true"){
                            var fileName = data.fileName;
                            alert("文件提交成功");
                            $.getJSON("ucontroller/updatedata",{uid:uid,fileName:fileName,username:username,pwd:pwd,age:age,sex:sex,status:status},function(json){
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

            $.getJSON("ucontroller/getbyid",{uid:uid},function(json){
                var fileName = json.imgId;
                console.log('/Path/'+fileName);
                $("#userimg").attr("src","http://localhost:8082/homework/image/"+fileName);
                $("#headuserimg").attr("src","http://localhost:8082/homework/image/"+fileName);
            });

        }, "json");




});
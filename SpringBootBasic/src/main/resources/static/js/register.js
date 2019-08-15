$(document).ready(function(){

    $("#registerbtn").click(function(){
        var utype = $("#utype").val();
        var user_name = $("#user_name").val();
        var pwd = $("#pwd").val();
        var repwd = $("#repwd").val();
        console.log(utype);
        console.log(user_name);
        console.log(pwd);
        console.log(repwd);

        $.getJSON("pcontroller/register",{utype:utype,user_name:user_name,pwd:pwd,repwd:repwd},function(json){
            console.log(json);
            if(json.result+"" == "true"){
                window.location.href = "login.html";
            }
            else{
                window.location.href = "register.html";
            }
        });
    });







    $("#user_name").blur(function(){

        var username = this.value;


        $.getJSON("CheckNameServlet", { "username": username}, function(json){
            if(username.length != 0){
                $("#nameIsNull").hide();
                if(json.rs+"" == "true"){
                    $("#nameIsExist").show();
                    document.getElementById("registerbtn").disabled=true;
                }
                else if(json.rs+"" == "false") {
                    $("#nameIsExist").hide();
                    if(username.length <= 10){
                        document.getElementById("registerbtn").disabled=false;
                        $("#nameLength").hide();
                    }
                    else{
                        document.getElementById("registerbtn").disabled=true;
                        $("#nameLength").show();
                    }

                }
            }
            else if(username.length == 0 || $("#pwd").val().length == 0 || $("#repwd").val().length == 0){
                $("#nameIsNull").show();
                document.getElementById("registerbtn").disabled=true;
            }
        });
    });




    $("#pwd").blur(function(){
        var password = this.value;
        if($("#user_name").val().length == 0 || this.value.length == 0 || $("#repwd").val().length == 0){
            $("#pwdIsNull").show();
            document.getElementById("registerbtn").disabled=true;
        }
        else if(password.length != 0){
            if(password.length <= 20){
                $("#pwdIsNull").hide();
                $("#pwdLength").hide();
                document.getElementById("registerbtn").disabled=false;
            }

            else{
                $("#pwdLength").show();
                document.getElementById("registerbtn").disabled=true;
            }
        }
    });


    $("#repwd").click(function(){
        var password = $("#pwd").val();
        var repassword = this.value;
        if(password != repassword){
            $("#repwdWarn").show();
            document.getElementById("registerbtn").disabled=true;
        }

        else if(password == repassword){
            $("#repwdWarn").hide();
            document.getElementById("registerbtn").disabled=false;
        }
    });
});
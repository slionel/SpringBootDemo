$(document).ready(function(){
    $("#loginbtn").click(function(){
        var username=$("#username").val();
        var password=$("#password").val();
        console.log(username);
        console.log(password);

        $.getJSON("ucontroller/login",{username:username,password:password},function(json){
            if(json.result+"" == "true"){
                window.location.href = "table.html";
            }

            else {
                alert("此用户不存在");
                window.location.href = "login.html";
            }
        });

        $.post("ucontroller/login2", { "loginName": username},
            function(data){

            }, "json");
    });
});
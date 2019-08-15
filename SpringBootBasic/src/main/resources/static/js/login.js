$(document).ready(function(){
    $("#loginbtn").click(function(){
        var username=$("#username").val();
        var password=$("#password").val();
        $.post("pcontroller/login2", { "loginName": username },
            function(data){

            }, "json");
        $.getJSON("pcontroller/login",{username:username,password:password},function(json){
            if(json.result+"" == "true"){
                window.location.href = "table.html";
            }

            else {
                window.location.href = "login.html";
            }
        });
    });
});
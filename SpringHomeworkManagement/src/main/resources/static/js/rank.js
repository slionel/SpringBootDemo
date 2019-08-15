$(document).ready(function(){
    $.post("ucontroller/login3",
        function(data){
            console.log(data);
            $("#usernamespan").text(data.loginname);
            if(data.status+"" == "教师"){
                $("#select3").show();
                $("#select4").show();
            }
        }, "json");

    $("button[name='ranksearchbtn']").click(function(){
        var typeserarch = $("#typeserarch").val();
        console.log(typeserarch);
        $.getJSON("hcontroller/rank",{typeserarch:typeserarch},function(json){
            console.log(json);
            $("#rankbody").empty();
            for(var i = 0; i < json.length; i++){
                $("#rankbody").append(
                    "<tr>" +
                    "<td>"+json[i].rank+"</td>" +
                    "<td>"+json[i].name+"</td>" +
                    "<td>"+json[i].count+"</td>" +
                    "</tr>"
                );
            }
        });
    });
});
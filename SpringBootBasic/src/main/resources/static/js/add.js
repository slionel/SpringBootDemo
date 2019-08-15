$(document).ready(function(){

    $.post("pcontroller/login3",
        function(data){
            console.log(data);
            $("#usernamespan").text(data.loginname);
        }, "json");

    $("#addbtn").click(function(){
            $.getJSON("pcontroller/add",{pname:$("#pname").val(), category:$("#category").val(),stock:$("#stock").val(),producedate:$("#producedate").val(),shelfLife:$("#shelflife").val(),price:$("#price").val()},function(json){
                if(json.result+"" == "true"){
                    alert("商品添加成功")
                    window.location.href="table.html";
                }
                else{
                    alert("添加失败");
                }
            });
        });
    });

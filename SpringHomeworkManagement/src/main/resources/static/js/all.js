$("document").ready(function(){
    var currentPages = 0;
    var uid;
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


    $("#searchbtn").click(function(){
        var searchDateVal = $("#dateserarch").val();
        var searchTypeVal = $("#typeserarch").val();
        console.log(searchDateVal);
        console.log(searchTypeVal);
        getData();




        function getData() {
            $.getJSON("hcontroller/getbydateandtype",{page:currentPages, opentime:searchDateVal, type:searchTypeVal}, function (json) {
                $("#allbody").empty();
                for(var i = 0; i < json.content.length; i++){
                    $("#allbody").append(
                        "<tr>" +
                        "<td>"+json.content[i].hid+"</td>" +
                        "<td>"+json.content[i].title+"</td>" +
                        "<td>"+json.content[i].content+"</td>" +
                        "<td>"+json.content[i].openTime+"</td>" +
                        "<td>"+json.content[i].type+"</td>" +
                        "</tr>"
                    );
                }
                var totalPages = json.totalPages;
                $(".pagination").empty();
                $(".pagination").append('<li class=""><a class="page-link" href="#" id="firstpage">首页</a></li>');
                $(".pagination").append('<li class=""><a class="page-link" href="#" id="previouspage">上一页</a></li>');
                for(var j = 0; j < totalPages; j++){
                    $(".pagination").append(
                        '<li class="page-item" id="pageno'+j+'"><a class="page-link" href="#">'+(j+1)+'</a></li>'
                    );
                }
                $(".pagination").append('<li class=""><a class="page-link" href="#" id="nextpage">下一页</a></li>');
                $(".pagination").append('<li class=""><a class="page-link" href="#" id="lastpage">尾页</a></li>');


                //显示当前页数
                $(".page-item").removeClass("active");
                $("#pageno"+currentPages).addClass("active");

                //下一页
                $("#nextpage").click(function () {
                    if(currentPages < totalPages-1){
                        var curr = new Number(currentPages)+1;
                        currentPages = curr;
                        getData();
                    }
                    else{
                        alert("这是最后一页");
                    }
                });


                //上一页
                $("#previouspage").click(function () {
                    if(currentPages > 0){
                        var curr = new Number(currentPages);
                        currentPages = curr - 1;
                        getData();
                    }
                    else{
                        alert("这是第一页");
                    }
                });


                //点击页数
                $(".page-item").click(function () {
                    var id = this.id;
                    currentPages = id.substr(6);
                    getData();
                });

                //尾页
                $("#lastpage").click(function () {
                    currentPages = totalPages - 1;
                    getData();
                });

                //首页
                $("#firstpage").click(function () {
                    currentPages = 0;
                    getData();
                });
            });
        }
    });
});
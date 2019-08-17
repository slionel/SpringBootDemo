$(document).ready(function(){
    var currentPage = 0;
    getData();

    function getData(){
        $.getJSON("pcontroller/page",{page:currentPage},function (json) {
            var content = json.content;
            $("#databody").empty();
            for(var i = 0; i < content.length; i++){
                $("#databody").append(
                    "<tr>" +
                    "<td>"+json.content[i].id+"</td>" +
                    "<td>"+json.content[i].productName+"</td>" +
                    "<td>"+json.content[i].productPrice+"</td>" +
                    "<td>"+json.content[i].productType+"</td>" +
                    "</tr>"
                );
            }

            var totalPages = json.totalPages;
            $(".pagination").empty();
            $(".pagination").append('<li class="page-item active"><a class="page-link" href="#" id="previouspage">Previous</a></li>');

            for(var j = 0; j < totalPages; j++){
                $(".pagination").append(
                    '<li class="page-item" id="pageno'+j+'"><a class="page-link" href="#">'+(j+1)+'</a></li>'
                );
            }

            $(".pagination").append('<li class="page-item"><a class="page-link" href="#" id="nextpage">Next</a></li>');

            $(".page-item").removeClass("active");
            $("#pageno"+currentPage).addClass("active");


            //下一页
            $("#nextpage").click(function () {
                if(currentPage < totalPages){
                    currentPage = currentPage + 1;
                    getData();
                }
                else{
                    alert("这是最后一页");
                }
            });


            //上一页
            $("#previouspage").click(function () {
                if(currentPage > 0){
                    currentPage = currentPage - 1;
                    getData();
                }
                else{
                    alert("这是第一页");
                }
            });


            $(".page-item").click(function () {
                var idval = this.id;
                currentPage = idval.substr(6);
                getData();
            });

        });
    }
});






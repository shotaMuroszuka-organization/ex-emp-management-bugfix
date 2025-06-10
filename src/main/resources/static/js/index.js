"use strict";

$(function(){
    function searchEmployeeList(){
        const nameValue = $("#name").val();
        $.ajax({
        url: "http://localhost:8080/employee/search",
        type: "POST",
        dataType: "html",
        data:{
            name: nameValue
        },
        async:true,
        }).done(function(data){
            console.log(data);
        }).fail(function(XMLHttpRequest, textStatus, errorThrown){
//            alert('エラーが発生しました！');
            console.log("XMLHttpRequest：" + XMLHttpRequest.status);
            console.log("textStatus：" + textStatus);
            console.log("errorThrown：" + errorThrown.message);
        });
    }

    $("#name").on("change", function(){
        searchEmployeeList();
    });
});
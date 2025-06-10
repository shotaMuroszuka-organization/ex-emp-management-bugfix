"use strict";

$(function(){
    function searchEmployeeList(){
        const nameValue = $("#name").val();
        console.log(nameValue);
        $.ajax({
        url: "http://localhost:8080/employee/autoComp",
        type: "GET",
        dataType: "json",
        data:{
            name: nameValue
        },
        async:true,
        }).done(function(data){
            console.log(data[0].name);
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
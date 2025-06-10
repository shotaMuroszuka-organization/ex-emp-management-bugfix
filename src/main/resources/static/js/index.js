"use strict";

$(function(){
    function searchEmployeeList(){
        const nameValue = $("#name").val();
        $.ajax({
        url: "http://localhost:8080/employee/autoComp",
        type: "GET",
        dataType: "json",
        data:{
            name: nameValue
        },
        async:true,
        }).done(function(data){
            $("#autocomplete-list").remove();

            // 候補リストを作成
            const list = $("<ul>").attr("id", "autocomplete-list").addClass("autocomplete-items list-group");

            data.forEach(function(item){
                const li = $("<li>")
                    .addClass("list-group-item list-group-item-action")
                    .text(item)
                    .on("click", function() {
                        $("#name").val(item);
                        $("#autocomplete-list").remove(); // 候補非表示
                    });
                list.append(li);
            });

            // name入力欄の直後に挿入
            $("#name").after(list);

        }).fail(function(XMLHttpRequest, textStatus, errorThrown){
//            alert('エラーが発生しました！');
            console.log("XMLHttpRequest：" + XMLHttpRequest.status);
            console.log("textStatus：" + textStatus);
            console.log("errorThrown：" + errorThrown.message);
        });
    }

    $("#name").on("input", function(){
        if ($(this).val().length > 0) {
            searchEmployeeList();
        } else {
            $("#autocomplete-list").remove();
        }
    });

     $(document).on("click", function(e){
        if (!$(e.target).closest("#name, #autocomplete-list").length) {
            $("#autocomplete-list").remove();
        }
    });
});
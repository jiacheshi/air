function a() {
    $("#t").val("123");
    $.ajax({
        type: "post",
        url: "/data/show",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (data) {
            alert(data);
        }
    })
}

function show() {
    $.ajax({
        type: "post",
        url: "/data/test3",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (data) {
            alert(data.id);
        }
    })
}
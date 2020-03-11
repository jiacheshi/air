$(function() {
    $.ajax({
        type : "post",
        url : "/deviceList2",
        contentType : "application/json;charset=utf-8",
        dataType : "json",
        success : function(data) {
            for (var i = 0; i < data.length; i++) {
                var html = $("<div class='devic' id='"
                    + i
                    + "'style='width:100%;'><div style='width:100%;margin:8px auto;' class='one"
                    + i
                    + "' id='"
                    + data[i].mac
                    + "'  onclick='ff(this)''> <img src='imgs/open1.jpg' id='img" + data[i].mac + "' class='imgies'/>&nbsp;&nbsp;"
                    + data[i].nickname
                    + "</div>");
                var RegionTool = $("<div class='none' id='" + data[i].mac + "mac" + i + "' style='display:none;width: 100%;'><ul class='two_ul'><li  class='li_1'><div class='data'>室内PM2.5</div><div id='pm25" + data[i].mac + "' class='num'>--</div><p>μg/m3</p ></li><li  class='li_1'> <div class='data'>室内温度</div><div id='indoorTemperature" + data[i].mac + "' class='num'>-- </div><p>°C</p ></li><li  class='li_1'><div class='data'>室内湿度</div><div id='roomHumidity" + data[i].mac + "' class='num'>-- </div><p>%</p ></li><li  class='li_1'><div class='data'>室外PM2.5</div><div id='outPM25" + data[i].mac + "' class='num'>--</div><p>μg/m3</p ></li><li  class='li_1'><div class='data'>设备状态</div><div id='error_code" + data[i].mac + "' class='num'>--</div><h6></h6></li><li class='li_1'><div class='data'>室内TVOC</div><div id='tvoc" + data[i].mac + "' class='num'>--  </div><p class='data3'>ppm</p ></li><li  class='li_1'><div class='data'>室内HCHO</div><div id='hcho" + data[i].mac + "' class='num'>-- </div><p >ppm</p ></li><li  class='li_1'><div class='data'>室内CO2</div><div id='co2" + data[i].mac + "' class='num'>-- </div><p>ppm</p ></li></ul></div></div>");

                //将设备名称追加到main1
                $("#main1").append(html);
                //将设备详情追加到main2
                $("#main2").append(RegionTool);

            }
}


})
})


function ff(obj) {
    var elementId = $(obj).attr("id");
    mac=elementId;
    ii = elementId;
    $.ajax({
        type : "get",
        data : "mac=" + elementId,
        contentType : "application/json;charset=utf-8",
        dataType : "json",
        async : true,
        url : "/devices/switcher",
        success : function(data) {
        }
    });
    //通过class获得元素组对象
    var box = document.getElementsByClassName("none");
    document.getElementsByClassName("dele");
    for (var i = 0; i < box.length; i++) {
        //通过对象获得id
        var did=$(box[i]).attr("id");
        if (did.match(elementId)) {
            //通过对象获得id

            document.getElementById(did).style.display = (document
                .getElementById(did).style.display == 'none') ? ''
                : 'none'
        }
        if (!did.match(elementId)) {
            document.getElementById(did).style.display = 'none';
        }
    }


}
function off() {

    document.getElementById('addmac_1').style.display = 'none';

}

function off1() {

    document.getElementById('addmac_1').style.display = 'none';

}
function addmac() {
    document.getElementById('addmac_1').style.display = 'block';

}
function my() {
    document.getElementById('two').style.display = 'none';

}

function myfun() {
    document.getElementById('two').style.display = 'block';
    document.getElementById('three').style.display = 'none';
    document.getElementById('light').style.display = 'none';


}
function myfun22() {

    document.getElementById('three').style.display = 'block';
    document.getElementById('two').style.display = 'none';
    document.getElementById('light').style.display = 'none';
    $("#slider").val(sc);
    $("#scale1").html(sc+"%");

}
function myfun2() {
    document.getElementById('three').style.display = 'none';

}

function openWin() {
    document.getElementById('light').style.display = 'block';
    document.getElementById('two').style.display = 'none';
    document.getElementById('three').style.display = 'none';

}
function closeWin() {
    document.getElementById('light').style.display = 'none';

}


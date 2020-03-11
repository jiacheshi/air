//全局变量控制设备
var t;
var ii;
var mac;
var sc;
//动态添加设备
$(function() {

    $.ajax({
        type : "post",
        url : "/deviceList",
        contentType : "application/json;charset=utf-8",
        dataType : "json",
        success : function(data) {
            //获取分辨率
            document.getElementById('documen').style.width = screen.width
                + 'px';
            document.getElementById('documen').style.height = screen.height
                + 'px';
            for (var i = 0; i < data.length; i++) {
                var html = $("<div class='devic' id='"
                    + i
                    + "'style='width:100%;'><img class='dele' style=' float: right;margin-right: 60px;  display: none; '  src='imgs/delaaa.jpg' id='"+data[i].mac+"_a' onclick='aa0(this)'/><div style='width:800px' class='one"
                    + i
                    + "' id='"
                    + data[i].mac
                    + "'  onclick='ff(this)''> <img src='imgs/open1.jpg' id='img"+data[i].mac+"' class='imgies'/>&nbsp;&nbsp;"
                    + data[i].nickname
                    + "</div> <div class='none' id='"+data[i].mac+"mac"+i+"' style='display:none;width: 100%;'><ul class='two_ul'><li  class='li_1'><div class='data'>室内PM2.5</div><div id='pm25"+data[i].mac+"' class='num'>--</div><p>μg/m3</p ></li><li  class='li_1'> <div class='data'>室内温度</div><div id='indoorTemperature"+data[i].mac+"' class='num'>-- </div><p>°C</p ></li><li  class='li_1'><div class='data'>室内湿度</div><div id='roomHumidity"+data[i].mac+"' class='num'>-- </div><p>%</p ></li><li  class='li_1'><div class='data'>室外PM2.5</div><div id='outPM25"+data[i].mac+"' class='num'>--</div><p>μg/m3</p ></li><li  class='li_1'><div class='data'>设备状态</div><div id='error_code"+data[i].mac+"' class='num'>--</div><h6></h6></li><li class='li_1'><div class='data'>室内TVOC</div><div id='tvoc"+data[i].mac+"' class='num'>--  </div><p class='data3'>ppm</p ></li><li  class='li_1'><div class='data'>室内HCHO</div><div id='hcho"+data[i].mac+"' class='num'>-- </div><p >ppm</p ></li><li  class='li_1'><div class='data'>室内CO2</div><div id='co2"+data[i].mac+"' class='num'>-- </div><p>ppm</p ></li></ul></div></div>");

                $("#main1").append(html);
            }

        }
    })
    $.ajax({
        type : "get",
        contentType : "application/json;charset=utf-8",
        dataType : "json",
        async : true,
        url : "/AirCleaner_03/getName",
        success : function(data) {
            $("#aName").html(data);
        }
    });

    setInterval("sta()", 20000);

})

function ff(obj) {


    clearTimeout(t);
    var elementId = $(obj).attr("id");
    mac=elementId;
    ii = elementId;
    $.ajax({
        type : "get",
        data : "mac=" + elementId,
        contentType : "application/json;charset=utf-8",
        dataType : "json",
        async : true,
        url : "/AirCleaner_03/devices/switcher",
        success : function(data) {
        }
    });

    show();
    sta();
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

function show() {

    $.ajax({
        type : "get",
        url : "/AirCleaner_03/show",
        data : "mac=" + mac,
        contentType : "application/json;charset=utf-8",
        dataType : "json",
        success : function(data) {
            $("#pm25" + ii).html(data.PM25);
            $("#outPM25" + ii).html(data.OutPM25);

            $("#tvoc" + ii).html(data.TVOC);
            $("#hcho" + ii).html(data.HCHO);
            $("#co2" + ii).html(data.co2);
            $("#indoorTemperature" + ii).html(
                data.IndoorTemperature);
            $("#roomHumidity" + ii).html(data.RoomHumidity);
            $("#scale").html(data.Scale+"%");
            sc=data.Scale;
            //设备状态
            switch (data.Error_Code) {
                case 0:
                    $("#error_code"+ ii).html("无故障");
                    break;
                case 1:
                    $("#error_code"+ ii).html("室内PM25故障");
                    break;
                case 2:
                    $("#error_code"+ ii).html("室内温湿度故障");
                    break;
                case 3:
                    $("#error_code"+ ii).html("室内TVOC故障");
                    break;
                case 4:
                    $("#error_code"+ ii).html("设备通信故障");
                    break;
                case 5:
                    $("#error_code"+ ii).html("室外传感器故障");
                    break;
                case 6:
                    $("#error_code"+ ii).html("室外通信故障");
                    break;
            }


            //空气等级
            if (data.PM25Level == 1) {
                $("#pm25"+ii).html(data.PM25+"(优)");
            }
            if (data.PM25Level == 2) {
                $("#pm25"+ii).html(data.PM25+"(良)");
            }
            if (data.PM25Level == 3) {
                $("#pm25"+ii).html(data.PM25+"(轻微污染)");
            }
            if (data.PM25Level == 4) {
                $("#pm25"+ii).html(data.PM25+"(中度污染)");
            }
            if (data.PM25Level == 5) {
                $("#pm25"+ii).html(data.PM25+"(重度污染)");
            }
            if (data.PM25Level == 6) {
                $("#pm25"+ii).html(data.PM25+"(严重污染)");
            }

            //工作模式
            if (data.WorkMode == 0) {
                $("#workMode").html("待机");
            }
            if (data.WorkMode == 1) {
                $("#workMode").html("手动");
            }
            if (data.WorkMode == 2) {
                $("#workMode").html("自动");
            }
            //风速
            if (data.WindSpeed == 1) {
                $("#windSpeed").html("关闭");
            }
            if (data.WindSpeed == 2) {
                $("#windSpeed").html("低速");
            }
            if (data.WindSpeed == 3) {
                $("#windSpeed").html("中速");
            }
            if (data.WindSpeed == 4) {
                $("#windSpeed").html("高速");
            }
        }
    });
    t = setTimeout("show()", 2000);
}
function low() {

    $.ajax({
        type : "get",
        url : "/AirCleaner_03/low",
        data : "mac=" + mac,
        contentType : "application/json;charset=utf-8",
        dataType : "json",
        success : function(data) {
        }

    })
    document.getElementById('light').style.display = 'none';

}
function mid() {
    $.ajax({

        type : "get",
        url : "/AirCleaner_03/mid",
        data : "mac=" + mac,
        contentType : "application/json;charset=utf-8",
        dataType : "json",
        success : function(data) {
        }

    })
    document.getElementById('light').style.display = 'none';

}
function hig() {
    $.ajax({

        type : "get",
        url : "/AirCleaner_03/hig",
        data : "mac=" + mac,
        contentType : "application/json;charset=utf-8",
        dataType : "json",
        success : function(data) {
        }

    })
    document.getElementById('light').style.display = 'none';

}

//调节工作模式
function auto() {
    $.ajax({

        type : "get",
        url : "/AirCleaner_03/auto",
        data : "mac=" + mac,
        contentType : "application/json;charset=utf-8",
        dataType : "json",
        success : function(data) {
        }

    })
    document.getElementById('two').style.display = 'none';

}
function shutdown() {
    $.ajax({

        type : "get",
        url : "/AirCleaner_03/shutdown",
        data : "mac=" + mac,
        contentType : "application/json;charset=utf-8",
        dataType : "json",
        success : function(data) {
        }

    })
    document.getElementById('two').style.display = 'none';

}
//调节新风比例
function moveChange() {
    $.ajax({
        type : "post",
        url : "/AirCleaner_03/scale",
        contentType : "application/json;charset=utf-8",
        dataType : "json",
        data : JSON.stringify({
            sz : $("#slider").val(),
            mac:mac
        }),
        success : function(data) {

        }
    })
    alert("修改为了：" + $("#slider").val())
    document.getElementById('three').style.display = 'none';

}
//绑定设备
function binding1() {
    var adminpwd = $("#adminpwd").val();
    var mac = $("#mac").val();
    var name = $("#nickname").val();
    if(adminpwd!=""&&mac!=""&&name!="") {
        $.ajax({
            url: "/AirCleaner_03/devices/binding",
            type: "get",
            dataType: "json",
            data: "mac=" + mac + "&adminpwd=" + adminpwd + "&nickname=" + name,
            success: function (obj) {
                if (obj.code == 0) {
                    alert("绑定成功！");
                    window.location.href = '/AirCleaner_03/userloginpage';
                } else if (obj.code == 1) {
                    alert("MAC和密码不匹配！");
                    // document.getElementById('boldStuff').innerHTML = 'MAC和密码不匹配';
                } else if (obj.code == 2) {
                    alert("设备绑定上限");
                    // document.getElementById('boldStuff').innerHTML = '设备绑定上限';
                } else if (obj.code == 3) {
                    alert("用户绑定上限！");

                } else if (obj.code == 4) {
                    alert("绑定重复设备！");
                } else if (obj.code == 5) {
                    alert("绑定失败请重试！");
                }
            }

        })
        document.getElementById('addmac_1').style.display = 'none';
    }else {
        alert("输入内容为空，请重新输入");
    }



}



function checkTime(i){
    if (i<10){
        i="0" + i;
    }
    return i;
}
//判断设备在线状态
function sta() {
    $.ajax({
        type: "post",
        url: "/AirCleaner_03/deviceStatus",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                if (data[i].code == 1) {
                    document.getElementById('img'+data[i].mac).src="imgs/open1.jpg";
                } else {

                    document.getElementById('img'+data[i].mac).src="imgs/close1.jpg";
                }
            }
        }


    })
}

//删除设备方法
function aa0(obj){
    var elementId = $(obj).attr("id");
    elementId=elementId.replace("_a","");
    if(confirm("是否移除该设备？")){
        $.ajax({
            url : "/AirCleaner_03/devices/delDevie",
            type : "get",
            dataType : "json",
            data : "mac=" + elementId,
            success : function(data) {
                if(data.code==1){
                    alert("删除成功");
                    //调用页面刷新方法
                    window.location.href = '/AirCleaner_03/userloginpage';
                }else {
                    alert("删除失败");
                }
            }
        })
        return true;
    }

    return false;

}
//删除设备
function delece(){

    var de =    document.getElementsByClassName("dele");
    for (var i = 0; i < de.length; i++) {
        //通过对象获得id
        var did=$(de[i]).attr("id");

        document.getElementById(did).style.display = (document
            .getElementById(did).style.display == 'none') ? ''
            : 'none'
    }



}



var FNScanner, eHeader, headerH;
apiready = function() {
    //应用全局FNScanner模块
    FNScanner = api.require('FNScanner');
    //定义根据id获取dom
    eHeader = $api.byId('header');
    //设置头部沉浸式
    $api.fixStatusBar(eHeader);
    //获取头部高度
    headerH = $api.offset(eHeader).h;

    //监听应用回到前台
    api.addEventListener({
        name: 'resume'
    }, function(ret, err) {
        FNScanner.onResume();
    });

    //监听应用回到后台
    api.addEventListener({
        name: 'pause'
    }, function(ret, err) {
        FNScanner.onPause();
    });

    fnOpenFNScanner();
}

//全局变量控制设备
var t;
var ii;
var mac;
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
                    + "'style='width:100%;'><div style='width:800px' class='one"
                    + i
                    + "' id='"
                    + data[i].mac
                    + "'  onclick='ff(this)''> <img src='imgs/open1.jpg' id='img"+data[i].mac+"' class='imgies'/>&nbsp;&nbsp;"
                    + data[i].nickname
                    + " </div><div class='none' id='"+data[i].mac+"mac"+i+"' style='display:none;width: 100%;'><ul class='two_ul'><li  class='li_1'><div class='data'>室内PM2.5</div><div id='pm25"+data[i].mac+"' class='num'>--</div><p>μg/m3</p ></li><li  class='li_1'> <div class='data'>室内温度</div><div id='indoorTemperature"+data[i].mac+"' class='num'>-- </div><p>°C</p ></li><li  class='li_1'><div class='data'>室内湿度</div><div id='roomHumidity"+data[i].mac+"' class='num'>-- </div><p>%</p ></li><li  class='li_1'><div class='data'>室外PM2.5</div><div id='outPM25"+data[i].mac+"' class='num'>--</div><p>μg/m3</p ></li><li  class='li_1'><div class='data'>设备状态</div><div id='error_code"+data[i].mac+"' class='num'>--</div><h6></h6></li><li class='li_1'><div class='data'>室内TVOC</div><div id='tvoc"+data[i].mac+"' class='num'>--  </div><p class='data3'>ppm</p ></li><li  class='li_1'><div class='data'>室内HCHO</div><div id='hcho"+data[i].mac+"' class='num'>-- </div><p >ppm</p ></li><li  class='li_1'><div class='data'>室内CO2</div><div id='co2"+data[i].mac+"' class='num'>-- </div><p>ppm</p ></li></ul></div></div>");

                $("#main1").append(html);
            }

        }
    })

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
    //通过class获得元素组对象
    var box = document.getElementsByClassName("none");


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
        url : "/show",
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
            $("#scale").html(data.Scale);
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
            alert(data);
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
            alert(data);
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
            alert(data);
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

//setInterval("low()","1000");

//一秒更新一次数据

//绑定设备
function binding1() {
    var adminpwd = $("#adminpwd").val();
    var mac = $("#mac").val();
    $
        .ajax({
            url : "/devices/binding",
            type : "get",
            dataType : "json",
            data : "mac=" + mac + "&adminpwd=" + adminpwd,
            success : function(obj) {
                if (obj.code == 0) {
                    alert("绑定成功！");
                    var html = $("<div class='devic' id='"
                        + i
                        + "'style='width=60%'><div class='one"
                        + i
                        + "' id='"
                        + obj.mac
                        + "'  onclick='ff"
                        + i
                        + "()''>"
                        + obj.nickname
                        + " </div><div id='child"+i+"' style='display:none'><ul class='two_ul'><li  class='li_1'><div class='data'>室内PM2.5</div><div id='pm25"+i+"' class='num'>--</div><p>μg/m3</p ></li><li  class='li_1'> <div class='data'>室内温度</div><div id='indoorTemperature"+i+"' class='num'>-- </div><p>°C</p ></li><li  class='li_1'><div class='data'>室内湿度</div><div id='roomHumidity"+i+"' class='num'>-- </div><p>%</p ></li><li  class='li_1'><div class='data'>室外PM2.5</div><div id='outPM25"+i+"' class='num'>--</div><p>μg/m3</p ></li><li  class='li_1'><div class='data'>设备状态</div><div id='error_code"+i+"' class='num'>--</div><h6></h6></li><li class='li_1'><div class='data'>室内TVOC</div><div id='tvoc"+i+"' class='num'>--  </div><p class='data3'>ppm</p ></li><li  class='li_1'><div class='data'>室内HCHO</div><div id='hcho"+i+"' class='num'>-- </div><p >ppm</p ></li><li  class='li_1'><div class='data'>室内CO2</div><div id='co2"+i+"' class='num'>-- </div><p>ppm</p ></li></ul></div></div>");
                    $("#main1").append(html);

                } else if (obj.code == 1) {
                    alert("MAC和密码不匹配！");
                    // document.getElementById('boldStuff').innerHTML = 'MAC和密码不匹配';
                } else if (obj.code == 2) {
                    alert("设备绑定上限");
                    // document.getElementById('boldStuff').innerHTML = '设备绑定上限';
                } else if (obj.code== 3) {
                    alert("用户绑定上限！");

                } else if (obj.code == 4) {
                    alert("绑定重复设备！");
                }
                else if (obj.code == 5) {
                    alert("绑定失败请重试！");
                }
            }

        })

    document.getElementById('addmac_1').style.display = 'none';

}

// //定位气象
// $(function(){
//     var geoc = new BMap.Geocoder();
//     var native = new BMap.LocalCity();
//     native.get(function(r) {
//         geoc.getLocation(r.center,
//             function (rs) {
//                 var addComp = rs.addressComponents;
//                 $("#gpscity").html(addComp.city);
//                 $("#gpsdistrict").html(addComp.province);
//                 // if (navigator.geolocation){
//                 //     navigator.geolocation.getCurrentPosition(showPosition,showError);
//                 //
//                 // }else{
//                 //     alert("浏览器不支持地理定位。");
//                 // }
//                 //
//                 //
//                 // function showError(error){
//                 //     switch(error.code) {
//                 //         case error.PERMISSION_DENIED:
//                 //             alert("定位失败,用户拒绝请求地理定位");
//                 //             break;
//                 //         case error.POSITION_UNAVAILABLE:
//                 //             alert("定位失败,位置信息是不可用");
//                 //             break;
//                 //         case error.TIMEOUT:
//                 //             alert("定位失败,请求获取用户位置超时");
//                 //             break;
//                 //         case error.UNKNOWN_ERROR:
//                 //             alert("定位失败,定位系统失效");
//                 //             break;
//                 //     }
//                 // }
//                 // function showPosition(position) {
//                 //
//                 //     var lat = position.coords.latitude; //纬度
//                 //     var lag = position.coords.longitude; //经度
//                 //     var latlon = position.coords.longitude + ',' + position.coords.latitude;
//                 $.ajax({
//                     type: "GET",
//                     data: "latlon=" + addComp.city,
//                     async: true,
//                     url: "/FromAjaxservlet",
//                     dataType: "json",
//                     success: function (data) {
//                         //alert(data.HeWeather6[0].basic.location);
//                         $("#tmp").append(data.HeWeather6[0].now.tmp + "°C");
//                         $("#wind_sc").append(data.HeWeather6[0].now.wind_sc + "级");
//                         $("#cond_txt").append(data.HeWeather6[0].now.cond_txt);
//                         $("#location").append(data.HeWeather6[0].basic.location);
//                         setTimeout("showPosition()", 10000);
//                     },
//                     error: function () {
//                         alert('fail');
//                     }
//                 });
//
//             })
//     })
//
//     })
//
//
//
//  function startTime() {
//         var today=new Date();//定义日期对象
//         var yyyy = today.getFullYear();//通过日期对象的getFullYear()方法返回年
//         var MM = today.getMonth()+1;//通过日期对象的getMonth()方法返回年
//         var dd = today.getDate();//通过日期对象的getDate()方法返回年
//         var hh=today.getHours();//通过日期对象的getHours方法返回小时
//         var mm=today.getMinutes();//通过日期对象的getMinutes方法返回分钟
//         var ss=today.getSeconds();//通过日期对象的getSeconds方法返回秒
//         // 如果分钟或小时的值小于10，则在其值前加0，比如如果时间是下午3点20分9秒的话，则显示15：20：09
//         MM=checkTime(MM);
//         dd=checkTime(dd);
//         mm=checkTime(mm);
//         ss=checkTime(ss);
//         var day; //用于保存星期（getDay()方法得到星期编号）
//         if(today.getDay()==0)   day   =   "星期日 "
//         if(today.getDay()==1)   day   =   "星期一 "
//         if(today.getDay()==2)   day   =   "星期二 "
//         if(today.getDay()==3)   day   =   "星期三 "
//         if(today.getDay()==4)   day   =   "星期四 "
//         if(today.getDay()==5)   day   =   "星期五 "
//         if(today.getDay()==6)   day   =   "星期六 "
//         document.getElementById('nowDateTimeSpan').innerHTML=yyyy+"-"+MM +"-"+ dd +" " + day;
//         setTimeout('startTime()',1000);//每一秒中重新加载startTime()方法
//     }

    function checkTime(i)
    {
        if (i<10){
            i="0" + i;
        }
        return i;
    }

    // function sta() {
    //     $.ajax({
    //         type: "post",
    //         async: true,
    //         url: "/deviceStatus",
    //         dataType: "json",
    //         success: function (data) {
    //             for (var i = 0; i < data.length; i++) {
    //                 alert(data[i].mac+data[i].code);
    //
    //             }
    //             }
    //         })
    // }

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

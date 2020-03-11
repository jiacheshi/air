// apiready = function() {
//     // api.addEventListener({ //使用下方函数此处好像失效了
//     //     name: 'keyback'
//     // }, function(ret, err) {
//     //     alert('woshi index fanhui ')
//     //     alert('准备 return 了')
//     //     return;
//     //     alert('return 了')
//     // });
//
//     // 返回键双击退出
//
//         var exitFlag = false;
//
//         api.addEventListener({
//             name: 'keyback'
//         }, function (ret,err) {
//             if (!exitFlag) {
//                 api.toast({
//                     msg: '再按一次退出应用',
//                     duration: 2000,
//                     location: 'bottom'
//                 });
//                 exitFlag = true;
//             } else {
//                 api.closeWidget({
//                     silent: true
//                 });
//             }
//             setTimeout(function () {
//                 exitFlag = false;
//             }, 2000);
//         });
//
//
//
// }
// pushHistory();
//
// window.addEventListener("popstate", function(e) {
// // pushHistory();
//     alert("我监听到了浏览器的返回按钮事件啦");//根据自己的需求实现自己的功能
// }, false);
// function pushHistory() {
//     var state = {
//         title: "title",
//         url: "#"
//     };
//     window.history.pushState(state, "title", "#");
// }

history.pushState(null,null,document.URL);

window.addEventListener('popstate',function(){

    //监听浏览器的返回事件

    history.pushState(null,null,document.URL);

});

//动态添加设备
$(function() {
    var ci = 0;
    var time1, time2;
    api.addEventListener({
        name : 'keyback'
    }, function(ret, err) {
        if (ci == 0) {
            time1 = new Date().getTime();
            ci = 1;
            api.toast({msg:'再按一次返回键退出'});

        } else if (ci == 1) {
            time2 = new Date().getTime();
            if (time2 - time1 < 3000) {
                api.closeWidget({
                    id : A6028645498097,
                    retData : {
                        name : 'closeWidget'
                    },
                    silent : true
                });
            } else {
                ci = 0;
                api.toast({msg:'再按一次返回键退出'});
            }
        }
    });
})

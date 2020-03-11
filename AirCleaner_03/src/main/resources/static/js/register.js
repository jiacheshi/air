var a=false;
//验证用户名
function hint() {
    var value = $("#username").val();//获取id为xxx的元素
    var v=$("#upass").val();
     $.ajax({
            url:"/AirCleaner_03/hint",//要请求服务器URL
            data:{uname:value},//这是一个对象，它表示请求参数 服务器端可以使用request。getParamet() 来获取
            async:true,//是否为异步请求
            type:"POST",//请求方式
            dataType:"json",//服务器返回的数据是什么类型   json:接收一个对象
            success:function(result){//这个函数会在服务器执行成功时被调用，参数result就是服务器返回的值
                if(result==0){
                    $("#hint").html("可以使用");
                    a=true;
                }if(result==1){
                    $("#hint").html("重复用户名");

                }if(result==2){
                    $("#hint").html("输入为空");
                }
            }
        });

}

function up() {
    var v=$("#upass").val();
    if(v=="") {
        $("#hintV").html("输入为空");
        a=false;
    }else {
        $("#hintV").html("");
        a=true;
    }
}



// //邮箱验证
// function Emali() {
//
// var email=$("#email").val();
// var regemail = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
// if(email==""){
//     $("#hintE").html("输入为空");
//     return false;
// }else if(!(regemail.test(email))){
//     $("#hintE").html("格式输入不正确");
//     return false;
// }else {
//
//         $("#hintE").html("可以使用");
//     return true;
//     }
//
// }
// //手机号码验证
// function checkMobile(){
//
//     var phone=$("#phonenumber").val();
//     var length = phone.length;
//     if(length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|)+\d{8})$/.test(phone) )
//     {
//
//         $("#hintP").html("可以使用");
//
//
//     }else if(phone=="") {
//
//         $("#hintP").html("输入为空");
//
//     }
//     else {
//         $("#hintP").html("手机格式不正确");
//
//
//         }
//     }



//$("#form-reg").onsubmit(a);
function  check() {
   return a;

}

//判断账号密码不能为空
function fun() {
    var a=$("#uname").val();
    var b=$("#upass").val();
    if(a!=""&&b!=""){
        return true;
    }else {

        alert("账号或密码不能为空");
        return false;
    }
}


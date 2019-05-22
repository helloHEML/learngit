var urls = "/api/user/sendMsg/";
var channel = getvl("channelName");
var mes = new Vue({
    el: '.mes',
    data: {
        name:getvl("channelName"),
        mes: '发送验证码',
        phone: '',
        code: '',
        password:''
    },
    methods: {
        send: function () {
            var that = this;
            if(channel==null||channel== undefined || channel==''){
                alert("请从正规渠道进入");
                return ;
            }

            if(that.phone==''||that.phone==null||that.phone==undefined){
                alert("请输入手机号");
                return ;
            }
            if (this.mes == '发送验证码') {
                if(this.phone!=''){
                    yzm(this.phone);
                }
                this.mes = '60s'
                var time = 60;
                var timer = setInterval(function () {
                    time--
                    if (time == 0) {
                        that.mes = '发送验证码'
                        clearInterval(timer)
                        timer = null
                    } else {
                        that.mes = time + 's'
                    }
                }, 1000)
            }
        },
        register: function () {
            var that = this;
            if(channel==null||channel== undefined || channel==''){
                alert("请从正规渠道进入");
                return ;
            }

            if(that.phone==''||that.phone==null||that.phone==undefined){
                alert("请输入手机号");
                return ;
            }
            if(!(/^1[34578]\d{9}$/.test(that.phone))){
                alert("手机号码有误，请重填");
                return false;
            }

      /*      if(that.password==''||that.password==null||that.password==undefined){
                alert("请输入密码");
                return ;
            }*/
            submit(this.phone,this.code,this.password);

        },
        conut:function () {
            if(this.name!=null&&this.name!==''&&this.name!=undefined){
                $.ajax({
                    url: '/api/channel/jiayi?encryption='+this.name,
                    type: "get",
                    data:{},
                    dataType: 'json',
                    contentType: "application/json;charset=utf-8",
                    success:function(data){
                        if(data.code==0){


                        }else{
                            alert(data.msg);
                        }
                    }
                });
            }

        }
    },
    mounted:function () {
        this.conut();
    }
});

function yzm(phone) {
    // var secret = phone.substring()
    var salt = "tt^hz";
    // alert($.md5(secret))

    if(channel==null||channel== undefined || channel==''){
        alert("请从正规渠道进入");
        return ;
    }
    var secret = $.md5(phone.substring(phone.length-4)+salt);
    // alert($.md5(phone.substring(phone.length-4))+","+phone)
    $.ajax({
        url: urls+secret+"/"+phone,
        type: "get",
        data:{},
        dataType: 'json',
        contentType: "application/json;charset=utf-8",
        success:function(data){
            if(data.success==true){


            }else{
                alert(data.msg);
            }
        }
    });
}

/**
 * 注册
 */
function submit(phone,code,password){
    var checkbox = $("#zc").is(':checked');
    if(!checkbox){
        alert("请同意协议")
    }
    if(channel!=null && channel != undefined && channel!=''){
        $.ajax({
            url: "/api/user/register",
            type: "get",
            data:{
                "phone":phone,
                "code":code,
                "password":password,
                "channelName":channel,
            },
            dataType: 'json',
            contentType: "application/json;charset=utf-8",
            success:function(data){
                if(data.success==true){
                    alert("注册成功");
                    location.href="/download.html?channelName="+channel;
                }else{
                    if(data.msg=='该号码已被注册'){
                        location.href="/download.html?channelName="+channel;
                    }else{
                        alert(data.msg);
                    }
                }
            }
        });
    }else{
        alert("请从正规渠道注册");
    }
}
//下载
// function redirect(channel) {
//     $.ajax({
//         url: "/api/download/redirect",
//         type: "get",
//         data:{
//             "channel":channel
//         },
//         dataType: 'json',
//         contentType: "application/json;charset=utf-8",
//         success:function(data){
//             if(data.code==0){
//                 location.href="https://fir.im/xh57";
//             }else{
//                 alert(data.msg)
//             }
//         }
//     });
// }

$("#tus").height(parseInt($('body').height())/2+"px");
$("#tux").height(parseInt($('body').height())/2+"px");
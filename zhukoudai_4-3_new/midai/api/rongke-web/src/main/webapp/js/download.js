var channel = getvl("channelName");
var browser = {
    versions: function () {
        var u = navigator.userAgent, app = navigator.appVersion;
        return {         //移动终端浏览器版本信息
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
            iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
        };
    }(),
    language: (navigator.browserLanguage || navigator.language).toLowerCase()
};
if (browser.versions.mobile){//判断是否是移动设备打开。browser代码在下面
    var ua = navigator.userAgent.toLowerCase();//获取判断用的对象
    if (ua.match(/MicroMessenger/i) == "micromessenger") {//微信中打开
        $('#showtext').html("请在浏览器中打开");
        $('#showtext').show();
        $('#app-show').show();
        $('#down_load').attr("disabled",true);
    }
}
$('#inhouseClick').click(function () {
    $("#inhouseTip").modal('show');
});
function loading(a) {
    if($('#down_load').attr("disabled")==undefined){
        $(a).hide();
        if(browser.versions.ios&&!browser.versions.webApp){
            $('#yuanquan').show();
            setTimeout(function () {
                $('#yuanquan').hide();
                $('#showtext').show();
                downloadsss(function (data) {
                    location.href=data.data;
                    setTimeout(function () {
                        $('#showtext').hide();
                        $('#trust').show();
                    },9000)
                    console.log(data)
                })
            },1000);
            $('#yuanquan').show();
        }else{
            $('#yuanquan').show();
            downloadsss(function (data) {
                $('#yuanquan').hide();
                $('#down_load').html("已下载");
                $('#down_load').attr("disabled",true);
                $('#down_load').show();
                console.log(data);
                location.href=data.data;
            })
        }
    }
}


function trustClick() {
    location.href="/css/embedded.mobileprovision";
}

function downloadsss(back) {
    $.ajax({
        url: "/api/download/redirect",
        type: "get",
        data:{
            "channel":channel
        },
        dataType: 'json',
        contentType: "application/json;charset=utf-8",
        success:function(data){
            if(data.code==0){
                back(data);
                // location.href="https://fir.im/xh57";
            }else{
                alert(data.msg)
            }
        }
    });
}
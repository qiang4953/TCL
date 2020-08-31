// 生成两个数之间的随机数
    function rnd(start,end){
        return Math.floor(Math.random()*(end-start+1))+start;
    }

    var str ="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    // 生成6位数字验证码
    function code(){
        var arr = [];
        for(var i = 0;i<6;i++){
            arr[i] = str.charAt(rnd(0,str.length-1));
        }
        // 将数组转化为字符串
        var res = arr.join("")
        $('.yz_code').html(res);
    }
  // 首次进入时展示的验证码
    code();

    $('.btn').click(function(event) {
        code();
    });
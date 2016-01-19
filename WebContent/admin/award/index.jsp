<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String serverPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>抽奖</title>
    <script type="text/javascript" src="<%=basePath%>UI/js/jquery-1.7.2-min.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <style>
        html, body {
            margin: 0;
            padding: 0;
            overflow: hidden;
        }

        body {
            background: url(images/body_bg.jpg) 0px 0px repeat-x #000;
        }

        .main_bg {
            background: url(images/main_1bg.jpg) top center no-repeat;
            height: 1000px;
        }

        .main {
            width: 1000px;
            height: 1000px;
            position: relative;
            margin: 0 auto;
        }

        .num_mask {

            height: 184px;
            width: 740px;
            position: absolute;
            /*left: 50%;*/
            top: 0;
            /*margin-left: -370px;*/
            z-index: 9;
        }

        .num_box {
            height: 450px;
            width: 1000px;
            position: absolute;
            left: 50%;
            top: 350px;
            margin-left: -400px;
            z-index: 8;
            overflow: hidden;
            text-align: center;
        }

        .num {
            background: url(images/num.png) top center repeat-y;
            width: 155px;
            height: 220px;
            float: left;
            margin-right: 6px;
        }

        .btn {
            background: url(images/btn_start.png) 0px 0px no-repeat;
            width: 264px;
            height: 89px;
            position: absolute;
            left: 50%;
            bottom: 50px;
            margin-left: -132px;
            cursor: pointer;
            clear: both;
        }
        #mb{
            position: absolute;
            left: 6px;
            top: 7px;
        }
    </style>
</head>
<body>



<div class="main_bg">
    <div class="main">
        <div id="res" style="text-align:center;color:#fff;padding-top:15px;"></div>
        <div class="num_mask"></div>
        <div class="num_box">
            <div id="mb"><img src="images/2.png"> </div>
            <div id="t1">
                <div class="num"></div>
                <div class="num"></div>
                <div class="num"></div>
                <div class="num"></div>
                <div class="num"></div>
                <div class="btn"></div>
            </div>
            <div id="t2" style="display: none">
                <div class="num"></div>
                <div class="num"></div>
                <div class="num"></div>
                <div class="num"></div>
                <div class="num"></div>
                <div class="btn"></div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
<script>
    function numRand() {
        var x = 99999; //上限
        var y = 11111; //下限
        var rand = parseInt(Math.random() * (x - y + 1) + y);
        return rand;
    }
    var k=0;
    var isBegin = false;
    $(function () {
        var u = 265;
        $('.btn').click(function () {
            $('#t1').show();
            $('#t2').hide();
            if (isBegin) return false;
            isBegin = true;
            $("#t1 .num").css('backgroundPositionY', 0);
            var result = numRand();
            $('#res').text('摇奖结果 = ' + result);
            var num_arr = (result + '').split('');

            var tttt=setInterval(a,1);

            function a(){
                $("#t1 .num:eq(0)").css('backgroundPositionY',k+'px');

                setTimeout(a,200);
                setTimeout(b,400);
                setTimeout(c,600);
                setTimeout(d,800);
                function a(){
                    $("#t1 .num:eq(1)").css('backgroundPositionY',k+200+'px');
                }

                function b(){
                    $("#t1 .num:eq(2)").css('backgroundPositionY',k+400+'px');
                }

                function c(){
                    $("#t1 .num:eq(3)").css('backgroundPositionY',k+600+'px');
                }


                function d(){
                    $("#t1 .num:eq(4)").css('backgroundPositionY',k+800+'px');
                }

                k+=10;
            }



            function stop(){
                $('#t1').hide();
                $('#t2').show();
                clearInterval(tttt);
            }

            var run={
                a:function(){
                    $(".num").each(function (index) {




//                        var _num = $(this);
//                        setTimeout(function () {
//                            _num.animate({
//                                backgroundPositionY: 265*10
//                            }, {
//                                duration: 10000,
//                                easing: "easeInOutCirc",
//                                complete: function () {
//                                    if (index == 3) isBegin = false;
//                                }
//                            });
//                        }, 0);
                    });
                },


                b:function(){
                    setTimeout(stop,3000);
                    $("#t2 .num").each(function (index) {
                        var _num = $(this);
                        setTimeout(function () {
                            _num.animate({
                                backgroundPositionY: 265*60-(265*num_arr[index])
                            }, {
                                duration: 10000,
                                easing: "easeInOutCirc",
                                complete: function () {
                                    if (index == 3) isBegin = false;
                                }
                            });
                        }, index*100);
                    });
                }
            }
            run.a();

            //$.post('Server.aspx', {id: idValue}, function (data) {
                setTimeout(run.b,2000);
            //});

        });
    });
</script>
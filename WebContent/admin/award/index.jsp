<%@include file="/basepage/response.jsp" %>
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
    <script type="text/javascript" src="<%=basePath%>admin/award/js/jquery-1.7.2-min.js"></script>
    <script type="text/javascript" src="<%=basePath%>admin/award/js/easing.js"></script>
    <style>
        html, body {
            margin: 0;
            padding: 0;
        }

        body {
            background-image: url(images/main_1bg.jpg);
            /*background-repeat: no-repeat;*/
            background-size: 100% 100%;
            width: 100%;
            height: 100%;
        }

        .main_bg {


        }

        .main {
            width: 100%;
            height: 100%;
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
		.num_box1 {
            height: 450px;
            width: 780px;
            position: absolute;
            left: 5%;
            top: 340px;
            margin-top: 0;
            z-index: 5;
        }
        
        /* #t1{
        	position:relative;
            left: 5%;
            top: 350px;
            z-index: 10;
        } */
        
        .num_box {
            height: 450px;
            width: 780px;
            position: absolute;
            left: 5%;
            top: 350px;
            /*margin-left: -400px;*/
            z-index: 18;
            overflow: hidden;
            text-align: center;
        }

        .num {
            background: url(images/num.png) top center repeat-y;
            width: 155px;
            height: 265px;
            float: left;
            margin-right: 0;
        }
        .num1 {
            background: url(images/single1.jpg) -20px -23px no-repeat;
            background-size:130% 112%; 
            /* background-size: 155px 265px; */
            width: 155px;
            height: 275px; 
            /* background-size: 100% 100%;
           	width: 68%;
            height: 78%; 
           	width: 100% ;
            height: 100% ; */ 
            float: left;
            margin-right: 0;
        }

        .btn {
            background: url(images/btn_start.png) 0px 0px no-repeat;
            background-size: 100% 100%;
            width: 283px;
            height: 142px;
            position: absolute;
            left: 50%;
            bottom: 0;
            margin-left: -141px;
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
            <div>
           	 	<div class="num1"></div>
           	 	<div class="num1"></div>
           	 	<div class="num1"></div>
           	 	<div class="num1"></div>
           	 	<div class="num1"></div>
            </div>
        </div>
        <div class="num_box">
            <!--<div id="mb"><img src="images/2.png"> </div>-->
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

<style>
    #kb{
        color: #ffffff;
        font-weight: bold;
        position: absolute;
        right: 2%;
        top: 25%;
        width: 32%;
    }
    #kb table{
        width: 100%;
    }
    #kb table tr td{
        /*border: 1px solid #cccccc;*/
        width:25%;
        text-align: center;
        height: 45px;
        font-size: 24px;

    }

    #kb table tr th{
        text-align: center;
        height: 45px;
        font-size: 35px;
        font-family:"微软雅黑";
    }
</style>


<!--结果报告-->
<div id="kb">
    <table>
			<thead>
				<tr>
					<th>奖项</th>
					<th>姓名</th>
					<th>工号</th>
					<th>部门</th>
				</tr>
			</thead>
			<tbody id="tb">
			</tbody>
    </table>
</div>
</body>
</html>
<script>
    function init(){
        var w = $(document).width();
        var h = $(document).height();
        $('body').width(w).height(h);
    }
    init();

    function numRand() {
        var x = 99999; //上限
        var y = 11111; //下限
        var rand = parseInt(Math.random() * (x - y + 1) + y);
        return rand;
    }
    var k=0;
    var isBegin = false;
    var txt , s , num_arr ; var second = 5 , first = 3 , special = 1;
    $(function () {
        var u = 265;
        $('.btn').click(function () {
            $('#t1').show();
            $('#t2').hide();
            if (isBegin) return false;
            isBegin = true;
            $("#t1 .num").css('backgroundPositionY', 0);
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
                    });
                },

                b:function(){
           			$.post('<%=basePath%>admin/award/ajaxpage/award-doAward.od?act=doAward', {}, function (data) {
						s = data.replace(/\r\n/g,'').split(',');
						var award ;
						if(second > 0){
							award = '二等奖';
							second = second - 1;
						}else if(first > 0){
							award = '一等奖';
							first = first - 1;
						}else {
							award = "特等奖";
						}
						txt = "<tr><td>" + award + "</td><td>" + s[0] + "</td><td>" + s[1] + "</td><td>" + s[2] + "</td></tr>";
           				num_arr = (s[1] + '').split('');
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
                                   		if (index == 4) {
	                                    	$("#tb").append(txt);
                                    		isBegin = false;
                                    	}
                                	}
                           		});
                        	}, index*100);
                    	});
           			});
                }
            }
            run.a();
            setTimeout(run.b,2000);
        });
    });
</script>
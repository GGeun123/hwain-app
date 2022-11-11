<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html lang="ko">
<!--[if IE 6]><html lang="ko" class="no-js old ie6"><![endif]-->
<!--[if IE 7]><html lang="ko" class="no-js old ie7"><![endif]-->
<!--[if IE 8]><html lang="ko" class="no-js old ie8"><![endif]-->
<!--[if IE 9]><html lang="ko" class="no-js modern ie9"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<!-- <html lang="ko" class="no-js modern"> -->
<!--<![endif]-->
<!-- IE6~8에서 HTML5 태그를 지원하기위한 HTML5 shim -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->
<head>
<title>스마트팩토리 로그인</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
<link href="css/css.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">

<!-- jQuery -->
<script src="js/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
</head>
<style>


</style>
<script type="text/javascript">

$(document).ready(function() {

	$("#sid").val("");
	$("#s_nm").val("");
	$("#group_nm").val("");
	
});
function fn_sid_check_ajax(){
	
		var sid=$("#sid").val();
		if(sid==""){

			$("#s_nm").val("");
			$("#group_nm").val("");
			alert("해당데이터가 없습니다.");
			$("#sid").focus();
			return false;
		}
	
		$.ajax({
			url: "rest/Login/sid_check_ajax/sid="+sid
			, success: function(data) {
// 				alert(data);
// 				alert(data.status);
// 				alert("결과:"+data.status);
				
// 				alert("결과:"+data.datas.s_nm);
// 				alert("결과:"+data.datas.group_nm);
				if ( data.status) {
					$("#s_nm").val(data.datas.user_nm);
					$("#group_nm").val(data.datas.buseo_nm);
					$("#password").focus();
// 					alert("성공");
				} else {
					$("#sid").val("");
					$("#s_nm").val("");
					$("#group_nm").val("");
					alert("해당데이터가 없습니다.");
				}
			}
			, error: function(e, x) {
				alert(x);
			}
		});
}


function fn_loginCheck_ajax(){
	
		var sid=$("#sid").val();

		var s_nm=$("#s_nm").val();

		if(sid==""){

			alert("사번을 확인하세요.");
			$("#sid").focus();
			return false;
		}
		if(s_nm==""){

			alert("사번을 확인하세요.");
			$("#sid").focus();
			return false;
		}
		
		
		var password=$("#password").val();

		if(password==""){

			alert("비밀번호를 입력하세요.");
			$("#password").focus();
			return false;
		}
		$.ajax({
			url: "rest/Login/loginCheck/sid="+sid+"/password="+password
			, success: function(data) {
// 				alert(data);
// 				alert(data.status);
// 				alert("결과:"+data.status);
				
// 				alert("결과:"+data.datas.s_nm);
// 				alert("결과:"+data.datas.group_nm);
				if ( data.status) {
// 					$("#s_nm").val(data.datas.s_nm);
// 					$("#group_nm").val(data.datas.group_nm);
// 					alert("성공");
					if(data.result==1){
// 						alert("성공");
						location.href="Login/main.do";
					}else if(data.result==2){
						alert("비밀번호를 입력하세요.");
						$("#password").focus();
					}else if(data.result==3){
						alert("사번을 확인하세요.");
						$("#sid").focus();
					}


				} else {
// 					$("#s_nm").val("");
// 					$("#group_nm").val("");
					alert("로그인중 에러가 발생하였습니다.\n관리자에게 연락바랍니다.");
				}
			}
			, error: function(e, x) {
				alert(x);
			}
		});
}
</script>
<body>
   <!-- Page Content -->
    <div class="container_intro">

        <div class="login_bg">
            <div class="center">
               <!-- 내용시작-->
               <div class="login_logo"><img src="images/login_logo.png"></div>

               <div class="login-panel">
                    <div class="panel-body">
                        <form role="form">
                            <fieldset>
                                <div class="form-group">
                                            <select class="form-control" placeholder="회사선택" id="company" name="company">
                                                <option value="">회사선택</option>
												<option value="1" selected="selected">메인테크</option>
                                            </select>

                                </div>
                                <div class="form-group">
                                    <input onkeydown="javascript: if (event.keyCode == 13) { fn_sid_check_ajax(); return false; }" class="form-control inLine" placeholder="사번" name="sid" id="sid" type="text" value="" style="width: 80%; margin-right: 1.2%;"><a href="#;" onclick="fn_sid_check_ajax()"  class="btn btn_colorBlue btn_colorBlue inLine" style="width: 18.2%;">조회</a>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="이름" name="s_nm" id="s_nm" type="text" autofocus readonly="readonly" value="">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="조직이름" name="group_nm" id="group_nm" type="text" value="" readonly="readonly">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="비밀번호" name="password" id="password" type="password" value="" onkeydown="javascript: if (event.keyCode == 13) { fn_loginCheck_ajax(); return false; }">
                                </div>
                                <!-- <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div> -->
                                <!-- Change this to a button or input when using this as a form -->
                                <a href="#;" onclick="fn_loginCheck_ajax()" class="btn btn-lg btn_colorBlue btn-block">로그인</a>
                            </fieldset>
                        </form>
                    </div>
                </div>

                  </div> <!--  // 내용 -->

            </div>
        </div><!-- /.row -->

    </div><!-- /.container -->
</body>
</html>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html lang="ko">
<!--[if IE 6]><html lang="ko" class="no-js old ie6"><![endif]-->
<!--[if IE 7]><html lang="ko" class="no-js old ie7"><![endif]-->
<!--[if IE 8]><html lang="ko" class="no-js old ie8"><![endif]-->
<!--[if IE 9]><html lang="ko" class="no-js modern ie9"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html lang="ko" class="no-js modern"><!--<![endif]-->
<!-- IE6~8에서 HTML5 태그를 지원하기위한 HTML5 shim -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->
<head>
<title>스마트팩토리</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta name="description" content="">
<link href="css/css.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

 </head>
<script type="text/javascript">

$(document).ready(function() {
	setTimeout(function(){
		var sid="${sessionScope.sid}";
		if(sid==""){
			location.href="login.jsp";
		}else{
			location.href="Login/main.do";
		}
		
	},1000);	
});
</script>
<body>
   <!-- Page Content -->
    <div class="container_intro">
               <!-- 내용시작-->
                    <div class="intro_bg">
                        <img src="images/intro.png">
                    </div><!-- //리스트1-->

                 <!--  // 내용 -->


    </div><!-- /.container -->

<!-- jQuery -->
</body>
</html>

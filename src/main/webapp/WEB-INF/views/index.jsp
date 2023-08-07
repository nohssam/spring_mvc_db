<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>spring mvc project</title>
<link rel="icon" type="image/x-icon" href="resources/images/favicon.png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var loginChk = "${loginChk}";
		if(loginChk == "fail"){
			alert("비밀번호틀림");
			return ;
		}else if(loginChk == "ok"){
			$("#login").css("display","none");    // 감추기
			$("#lgin_ok").css("display","block"); // 나타내기	
		}
	});
</script>
<script type="text/javascript">
	function go_members() {
		location.href = "/members_list.do";
	}
	function go_guestbook() {
		location.href = "/guestbook_list.do";
	}
	function go_guestbook2() {
		location.href = "/guestbook2_list.do";
	}
	function reg_add_go() {
		location.href = "/member_reg.do";
	}
	function member_logout() {
		location.href = "/member_logout.do";
	}
	function go_bbs() {
		location.href = "/bbs_list.do";
	}
</script>
</head>
<body>
	<button onclick="go_members()">Members</button>
	<button onclick="go_guestbook()">GuestBook</button>
	<button onclick="go_guestbook2()">GuestBook2</button>
	<button onclick="go_bbs()">BBS</button>
	<hr>
	<div id="login" style="margin: 30px;">
		<form action="/member_login.do" method="post">
			<p>	ID : <input type="text" name="m_id" required>	</p>
			<p>	PW : <input type="password" name="m_pw" required></p>
			<input type="submit" value="로그인">
		</form>
	</div>
	<div id="lgin_ok" style="display: none;">
		<h2> ${mvo.m_id }님 로그인 성공</h2>
		<button onclick="member_logout()">로그아웃</button>
	</div>
	<div id="btns" style="margin: 30px;">
	<button onclick="reg_add_go()">회원가입</button>
	<button onclick="id_find_go()">아이디찾기</button>
	<button onclick="pw_find_go()">비번찾기</button>
	</div>
</body>
</html>
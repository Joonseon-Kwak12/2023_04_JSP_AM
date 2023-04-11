<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
	<div>
		<a href="../home/main">메인페이지로 이동</a>
	</div>

	<h1>회원 가입</h1>
	<script type="text/javascript">
		var JoinForm__submitDone = false;
	
		function JoinForm__submit(form) {
			if(JoinForm__submitDone) {
				alert('처리 중입니다.');
				return;
			}
			
			var loginId = form.loginId.value.trim();
			var loginPw = form.loginPw.value.trim();
			var loginPwConfirm = form.loginPwConfirm.value.trim();
			var name = form.name.value.trim();
			
			if(loginId.length == 0) {
				alert('아이디를 입력해주세요');
				form.loginId.focus();
				return;
			}
			if(loginPw.length == 0) {
				alert('비밀번호를 입력해주세요');
				form.loginPw.focus();
				return;
			}
			if(loginPwConfirm.length == 0) {
				alert('비밀번호 확인을 입력해주세요');
				form.loginPwConfirm.focus();
				return;
			}
			if(loginPw != loginPwConfirm) {
				alert('비밀번호가 일치하지 않습니다.');
				form.loginPw.focus();
				return;
			}
			if(name.length == 0) {
				alert('이름을 입력해주세요');
				form.name.focus();
				return;
			}
			
			JoinForm__submitDone = true;
			form.submit();
		}
		
		function CheckDupId() {
			var loginId = document.getElementById("loginId").value;
			if(loginId) {
				url = "/checkDupId?loginId="+loginId;
				window.open(url, "_blank", "width=400px,height=200px");	
			} else {
				alert("아이디를 입력하세요.");
			}
		}
	</script>

	<!-- 	<a href="https://www.naver.com" -->
<!-- 		onclick="if(confirm('이동하시겠습니까?') == false) return false;"> naver </a> -->

	<form method="post" action="doJoin"
	onsubmit="JoinForm__submit(this); return false;">
		<div>
			로그인 아이디: <input autocomplete="off" type="text" placeholder="아이디를 입력해주세요." name="loginId"  id="loginId"/>
			<button type="button" onclick="CheckDupId()">아이디 중복 검사</button>
		</div>
		<div>
			로그인 비밀번호: <input autocomplete="off" type="text" placeholder="비밀번호를 입력해주세요." name="loginPw"/>
		</div>
		<div>
			로그인 비밀번호 확인: <input autocomplete="off" type="text" placeholder="비밀번호 확인을 입력해주세요." name="loginPwConfirm"/>
		</div>
		<div>
			이름: <input autocomplete="off" type="text" placeholder="이름을 입력해주세요." name="name"/>
		</div>

		<button type="submit">가입</button>
	</form>

</body>
</html>
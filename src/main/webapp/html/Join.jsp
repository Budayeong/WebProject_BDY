<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@ include file="../inc/member_head.jsp" %>
  <title>회원가입</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script type="text/javascript">
//   폼 내용 검증
  function validateForm(form) { 

	    if (form.join_id.value == "") {
	        alert("아이디를 입력하세요.");
	        form.join_id.focus();
	        return false;
	    }
	    if (form.join_id.value.length < 6 || form.join_id.value.length > 12) {
	        alert("아이디는 영문소문자 또는 숫자 6~12자로 입력해 주세요");
	        form.join_id.focus();
	        return false;
	    }
	    if (form.checkId.value !== "check") {
	        alert("아이디 중복체크를 진행하세요.");
	        form.checkId.focus();
	        return false;
	    }
	    if (form.join_pass.value == "") {
	        alert("비밀번호를 입력하세요.");
	        form.join_pass.focus();
	        return false;
	    } 
	    if (form.join_pass.value.length < 6 || form.join_pass.value.length > 18) {
	        alert("비밀번호는 영문소문자 또는 숫자 6~18자로 입력해 주세요");
	        form.join_pass.focus();
	        return false;
	    }
	    if (form.pass_check.value == "") {
	        alert("비밀번호 체크를 진행하세요.");
	        form.pass_check.focus();
	        return false;
	    } 
	    if (form.join_pass.value != form.pass_check.value) {
	        alert("비밀번호가 일치하지 않습니다.");
	        form.join_tel.focus();
	        return false;
	    } 
	    if (form.join_name.value == "") {
	        alert("이름을 입력하세요.");
	        form.join_name.focus();
	        return false;
	    } 
	    if (form.join_email.value == "") {
	        alert("이메일을 입력하세요.");
	        form.join_email.focus();
	        return false;
	    } 
	    if (form.join_tel.value == "") {
	        alert("전화번호를 입력하세요.");
	        form.join_tel.focus();
	        return false;
	    } 
	    return true;
	}
  
// 아이디 중복 확인
$(function() {
    $.ajaxSetup({
        url: "../member/idCheck.do",
        dataType: "text",
    });

    $("#idCheck").click(function() {
        var join_id = $('input[name="join_id"]').val();
        
//         아이디가 입력되지않았으면 입력 안내
        if (join_id === "") {
            alert("아이디를 입력하세요.");
            return;
        }

        $.ajax({
            data: { user_id: join_id },
            success: function(responseData) {
                if (responseData === "0") {
//                     alert("아이디 사용 가능");
                    $("#checkResult").css("color","green").text("사용가능한 아이디");
                    $('input[name="checkId"]').val("check");
                } else {
                    $("#checkResult").css("color","red").text("사용할 수 없는 아이디");
                    $('input[name="checkId"]').val("unCheck");
                }
            },
            error: errFunc
        });
    });
});

// 아이디 중복 실패 
function errFunc(errData) {
    alert("실패: " + errData.status + "-" + errData.statusText);
}

</script> 
</head>
<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth px-0">
        <div class="row w-100 mx-0">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left py-5 px-4 px-sm-5">
              <div class="brand-logo">
                <img src="${pageContext.request.contextPath}/assets/images/logo.svg" alt="logo">
              </div>
               ${ JoinErrMsg }
              <h3>회원가입</h3>
              <form name="joinForm" class="pt-3" method="post" onsubmit="return validateForm(this);" action="${pageContext.request.contextPath}/member/Join.do">
                <div class="form-group">
                  <input type="text" class="form-control form-control-lg" id="exampleInputUsername1" name="join_id" placeholder="아이디 (영문소문자/숫자, 6~12자)"></br>
                  <button type="button" name="idCheck" id="idCheck">중복 확인</button>
                  <span id="checkResult"></span><br> 
<!--                   아이디 중복 확인 여부를 위한 hidden input태그 -->
                  <input type="hidden" name="checkId" value="unCheck" />
                </div>
                <div class="form-group">
                  <input type="password" class="form-control form-control-lg" id="exampleInputPassword1" name="join_pass" placeholder="비밀번호 (영문소문자/숫자, 6~18자)">
                </div>
                <div class="form-group">
                  <input type="password" class="form-control form-control-lg" id="exampleInputPassword1" name="pass_check" placeholder="비밀번호 확인">
                </div>
                <div class="form-group">
                  <input type="text" class="form-control form-control-lg" id="exampleInputUsername1" name="join_name" placeholder="이름">
                </div>
                <div class="form-group">
                  <input type="email" class="form-control form-control-lg" id="exampleInputEmail1" name="join_email" placeholder="이메일">
                </div>
                <div class="form-group">
                  <input type="text" class="form-control form-control-lg" id="exampleInputEmail1" name="join_tel" placeholder="전화번호 (숫자만 입력, ex:01011112222)">
                </div>
                <div class="mt-3">
                  <button type="submit" class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn" >회원가입</button>
                </div>
                <div class="text-center mt-4 font-weight-light">
                  이미 계정이 있으신가요? <a href="${pageContext.request.contextPath}/member/login.do" class="text-primary">Login</a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <%@ include file="../inc/member_js.jsp" %>>
</body>

</html>

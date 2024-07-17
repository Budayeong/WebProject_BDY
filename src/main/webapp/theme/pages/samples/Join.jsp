<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Skydash Admin</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/vendors/feather/feather.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- Plugin css for this page -->
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/theme/images/favicon.png" />
  
  <!-- 회원가입 폼값 전송 시 폼값이 입력되었는지 검증하는 JS 함수 선언  -->
  <script type="text/javascript">
  function validateForm(form) {  // 폼 내용 검증

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
	    if (form.pass_check.value == "") {
	        alert("비밀번호 체크를 진행하세요.");
	        form.join_pass.focus();
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

  
  function idCheck(form){
	  
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
                <img src="../../images/logo.svg" alt="logo">
              </div>
               ${ JoinErrMsg }
              <h3>회원가입</h3>
              <form class="pt-3" method="post" onsubmit="return validateForm(this);" action="${pageContext.request.contextPath}/member/Join.do">
                <div class="form-group">
                  <input type="text" class="form-control form-control-lg" id="exampleInputUsername1" name="join_id" placeholder="아이디 (영문소문자/숫자, 6~12자)"></br>
                  <button type="button" name="idCheck">중복 확인</button>
                  <input type="hi-den" name="checkId" value="unCheck" />
                </div>
                <div class="form-group">
                  <input type="password" class="form-control form-control-lg" id="exampleInputPassword1" name="join_pass" placeholder="비밀번호">
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
                  이미 계정이 있으신가요? <a href="Login.do" class="text-primary">Login</a>
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
  <!-- container-scroller -->
  <!-- plugins:js -->
  <script src="../../vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="../../js/off-canvas.js"></script>
  <script src="../../js/hoverable-collapse.js"></script>
  <script src="../../js/template.js"></script>
  <script src="../../js/settings.js"></script>
  <script src="../../js/todolist.js"></script>
  <!-- endinject -->
</body>

</html>

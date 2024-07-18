<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="../inc/member_head.jsp" %>
  <title>로그인</title>
  
  <script type="text/javascript">
  function validateForm(form) {  // 폼 내용 검증
      if (form.user_id.value == "") {
          alert("아이디를 입력하세요.");
          form.user_id.focus();
          return false;
      }
      if (form.user_pw.value == "") {
          alert("비밀번호를 입력하세요.");
          form.user_pw.focus();
          return false;
      } 
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
              <h3>로그인</h3>
              ${ LoginErrMsg }
              <form name="loginForm" class="pt-3" method="post" action="${pageContext.request.contextPath}/member/Login.do" onsubmit="return validateForm(this);">
                <div class="form-group">
                  <input type="text" class="form-control form-control-lg" id="exampleInputEmail1" name="user_id" placeholder="아이디" value="${ cookie.savdId.value }">
                </div>
                <div class="form-group">
                  <input type="password" class="form-control form-control-lg" id="exampleInputPassword1" name="user_pw" placeholder="비밀번호">
                </div>
                <div class="mt-3">
                  <button type="submit" class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn" >로그인</button>
                </div>
                <div class="my-2 d-flex justify-content-between align-items-center">
                  <div class="form-check">
                    <label class="form-check-label text-muted">
                      <input type="checkbox" class="form-check-input" name="save" value="save">
                      아이디 저장
                    </label>
                  </div>
                  <a href="#" class="auth-link text-black">비밀번호 찾기</a>
                </div>
<!--                 <div class="mb-2"> -->
<!--                   <button type="button" class="btn btn-block btn-facebook auth-form-btn"> -->
<!--                     <i class="ti-facebook mr-2"></i>페이스북계정 로그인 -->
<!--                   </button> -->
<!--                 </div> -->
                <div class="text-center mt-4 font-weight-light">
                  <a href="${pageContext.request.contextPath}/member/Join.do" class="text-primary">회원가입</a>
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
  <%@ include file="../inc/member_js.jsp" %>
</body>

</html>

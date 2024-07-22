<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<script>
function validateForm(form) { 

    if (form.title.value == "") {
        alert("제목을 입력하세요.");
        form.title.focus();
        return false;
    }
    if (form.content.value == "") {
        alert("내용을 입력하세요.");
        form.title.focus();
        return false;
    }
    return true;
}

</script>
<head>
  <%@ include file="../inc/board_head.jsp" %>
  <title>자료실 글수정</title>
</head>
<body>
  <div class="container-scroller">
    <!-- 네비 바 start -->
    <%@ include file="../inc/navbar.jsp" %> 
	<!-- 유저메뉴 start -->
	<c:if test="${ not empty UserId }">
	          <li class="nav-item nav-profile dropdown">
	            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
	              <img src="${pageContext.request.contextPath}/assets/images/faces/face28.jpg" alt="profile"/> &nbsp&nbsp
	            </a>
	             <strong>${ UserName }</strong>님
	            <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
	              <a class="dropdown-item" href="${pageContext.request.contextPath}/member/modify.do">
	                <i class="ti-settings text-primary"></i>
	                Settings
	              </a>
	              <a class="dropdown-item" href="${pageContext.request.contextPath}/member/logout.do">
	                <i class="ti-power-off text-primary"></i>
	                Logout
	              </a>
	            </div>
	          </li>
	</c:if>
	<c:if test="${ empty UserId }">
	        <ul class="navbar-nav mr-lg-2">
	          <li class="nav-item nav-search d-none d-lg-block">
	            <div class="input-group">
	              <div class="input-group-prepend hover-cursor" id="navbar-search-icon">
	                <span class="input-group-text">
	                <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/member/login.do' ">로그인</button>&nbsp&nbsp&nbsp
	                <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/member/join.do' ">회원가입</button>
	                </span>
	              </div>
	            </div>
	          </li>
	        </ul>
	</c:if>
	<!-- 유저메뉴 end -->
        </ul>
      </div>
    </nav>
    <!-- 네비 바 end -->
    <!-- 페이지 내용 start  -->
    <div class="container-fluid page-body-wrapper">
      <!-- 왼쪽 사이드 바 -->
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/main/index.do">
              <i class="icon-grid menu-icon"></i>
              <span class="menu-title">메인</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
              <i class="icon-layout menu-icon"></i>
              <span class="menu-title">게시판</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="ui-basic">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item"> <a class="nav-link" href="../board/board.do">자유게시판</a></li>
                <li class="nav-item"> <a class="nav-link" href="../board/qboard.do">Q&A게시판</a></li>
                <li class="nav-item"> <a class="nav-link" href="../board/fboard.do">자료실</a></li>
              </ul>
            </div>
          </li>
          <!-- 유저페이지 start -->
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#auth" aria-expanded="false" aria-controls="auth">
              <i class="icon-head menu-icon"></i>
              <span class="menu-title">마이페이지</span>
              <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="auth">
              <ul class="nav flex-column sub-menu">
			
              <c:if test="${ not empty UserId }">
                <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/member/logout.do"> 로그아웃 </a></li>
                <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/member/modify.do"> 회원정보 </a></li>
			  </c:if>
			  <c:if test="${ empty UserId }">
				<li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/member/login.do"> 로그인 </a></li>
                <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/member/join.do"> 회원가입 </a></li>
			  </c:if>
              </ul>
            </div>
          </li>
          <!-- 유저페이지 end -->
        </ul>
      </nav>
      <!-- 왼쪽 사이드 바 end -->
      <!-- 게시판 start -->
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-12 grid-margin">
              <div class="row">
                <div class="col-12 col-xl-8 mb-4 mb-xl-0">
                  <h3 class="font-weight-bold">자료실</h3>
                  <h6 class="font-weight-normal mb-0">파일을 첨부해 자유롭게 글을 작성해보세요 <span class="text-primary">3 unread alerts!</span></h6>
                </div>
              </div>
            </div>
          </div>
          <!-- 테이블  -->
        <div class="col-md-12 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <div class="table-responsive">
              
              
<form name="editForm" method="post" enctype="multipart/form-data" action="../board/fboardEdit.do" onsubmit="return validateForm(this);">
<!-- 글내용 수정을 위해 input hidden으로 게시글 번호 전달 -->
<input type="hidden"  name="num" value="${ dto.num }"/>
<!-- 
기존 등록된 파일명을 설정. 만약 수정페이지에서 첨부파일을 변경하지 않는다면
여기에 등록된 파일명을 이용해 update 할 예정.
 -->
<input type="hidden"  name="prevOfile" value="${ dto.ofile }"/>
<input type="hidden"  name="prevSfile" value="${ dto.sfile }"/>
<table class="table" width="90%">
<tr>
  <td>제목</td>
  <td>
  	<input type="text" name="title" class="form-control form-control-lg" placeholder="제목을 입력하세요" value="${ dto.title }"/> 
  </td>
</tr>
<tr>
  <td>파일첨부</td>
  <td>
   <input type="file" name="ofile"/>
  </td>
</tr>
<!-- 게시물 내용 -->
<tr>
  <td>내용</td>
  <td>
  	<textarea name="content" class="form-control form-control-lg" placeholder="내용을 입력하세요" style="height:400px;">${ dto.content }</textarea>
  </td>
</tr>
<tr>
   <td colspan="2" align="center">
     <button type="submit" class="btn btn-primary btn-sm">
         작성완료
     </button>
     <button type="reset" class="btn btn-primary btn-sm" >
         다시하기
     </button>
     <button type="button" class="btn btn-primary btn-sm" onclick="location.href='../board/fboard.do';">
           목록 바로가기
     </button>
   </td>
</tr>
</table>
</form>
			 
			 
            </div>
          </div>
         </div>
       </div>
        <!-- 테이블 end -->
        <!-- 푸터 start  -->
        <%@ include file="../inc/footer.jsp" %> 
        <!-- 푸터 end -->
        </div>
      </div>   
      <!-- 게시판 end -->
  	</div>
  	<!-- 페이지 내용 end  -->

  <!-- plugins:js -->
  <%@ include file="../inc/js.jsp" %>
</body>
</html>


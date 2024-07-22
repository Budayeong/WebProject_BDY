<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@ include file="../inc/board_head.jsp" %>
  <title>자유게시판 글작성</title>
   <script>
   function confirmDelete(url) {
	   if (confirm("정말로 삭제하시겠습니까?")){
		   location.href = url;
	   } 
	}

	    
  </script>
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
                <li class="nav-item"> <a class="nav-link" href="../board/QBoard.do">Q&A게시판</a></li>
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
                  <h3 class="font-weight-bold">자유게시판</h3>
                  <h6 class="font-weight-normal mb-0">특정한 주제없이 자유롭게 글을 작성해보세요 <span class="text-primary">3 unread alerts!</span></h6>
                </div>
              </div>
            </div>
          </div>
          <!-- 테이블  -->
        <div class="col-md-12 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <div class="table-responsive">
              <table class="table" width="90%">
			   <colgroup>
			     <col width="15%"/> <col width="35%"/>
			     <col width="15%"/> <col width="*"/>
			   </colgroup> 
			   <tr>
			     <td>번호</td> <td>${ dto.num } </td>
			     <td>작성자</td> <td>${ dto.name }</td>
			   </tr>
			   <tr>
			     <td>작성일</td> <td>${ dto.postdate }</td>
			     <td>조회수</td> <td>${ dto.visitcount }</td>
			   </tr>
			   <tr>
			     <td >좋아요 수</td>
		         <td colspan="3">            
		         	<c:if test="${ not empty UserId }">
		        		<button type="button" class="btn btn-primary btn-sm" id="likeCount">좋아요</button>
		        	</c:if>
		        	${ dto.likecount }
		         </td>
			   </tr>
			   <tr>
			     <td>제목</td>
			     <td colspan="3">${ dto.title }</td>
			   </tr>
			   <tr>
			     <td colspan="4" height="100">
		         ${ dto.content }
		    	 </td>
			   </tr>
			   <c:choose>
				 <c:when test="${ (not empty UserId) && (UserId eq dto.id) }">
				   <tr>
			         <td colspan="4" align="center">
<%-- 		                <button type="button" class="btn btn-primary btn-sm"  onclick="location.href='../board/pass.do?mode=edit&idx=${ param.idx }';"> --%>
		                <button type="button" class="btn btn-primary btn-sm"  onclick="location.href='../board/boardEdit.do?num=${ dto.num }&id=${ dto.id }';">
		                    수정하기
		                </button>
<%-- 		                <button type="button" class="btn btn-primary btn-sm"  onclick="location.href='../board/boardDelete.do?num=${ dto.num }';"> --%>
		                <button type="button" class="btn btn-primary btn-sm"  onclick="confirmDelete('../board/boardDelete.do?num=${dto.num}&id=${ dto.id }')">
		                    삭제하기
		                </button>
		                <button type="button" class="btn btn-primary btn-sm"  onclick="location.href='../board/board.do';">
		                    목록 바로가기
		                </button>
			         </td>
				    </tr>
				 </c:when>
			     <c:otherwise>
			        <tr>
		              <td colspan="4" align="center">
		                 <button type="button" class="btn btn-primary btn-sm" onclick="location.href='../board/board.do';">
		                     목록 바로가기
		                 </button>
		              </td>
			        </tr>
			     </c:otherwise>
			   </c:choose>
			 </table>
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


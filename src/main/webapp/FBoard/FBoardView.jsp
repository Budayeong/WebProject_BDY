<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@ include file="../inc/board_head.jsp" %>
  <title>자료실 상세보기</title>
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
    <%@ include file="../inc/navbar.jsp" %> 
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
        </ul>
      </div>
    </nav>
    <div class="container-fluid page-body-wrapper">
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
        </ul>
      </nav>
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
            <div class="col-md-12 grid-margin">
              <div class="row">
                <div class="col-12 col-xl-8 mb-4 mb-xl-0">
                  <h3 class="font-weight-bold">자료실</h3>
                  <h6 class="font-weight-normal mb-0">파일을 첨부해 자유롭게 글을 작성해보세요 
                </div>
              </div>
            </div>
          </div>
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
			     <td>첨부파일</td>
		         <td>            
		        	${ dto.ofile }
		        	<a href="../board/download.do?ofile=${ dto.ofile }&sfile=${ dto.sfile }&num=${ dto.num }">[다운로드]</a>
		         </td>
			     <td>다운로드 수</td> <td>${ dto.downcount }</td>
			   </tr>
			   <tr>
			     <td>제목</td>
			     <td colspan="3">${ dto.title }</td>
			   </tr>
			   <tr>
			     <td colspan="4" height="100">
		         	${ dto.content }
		         	<br/>
		         	<br/>
<!-- 이미지일때 -->
<c:if test="${not empty dto.ofile and fileType eq 'img'}">
	<img src="../uploads/${ dto.sfile }" style="width: 450px; height: 450px; border-radius: 0;" />
</c:if>

<!-- 비디오일때 -->
<c:if test="${not empty dto.ofile and fileType eq 'video'}">
    <div>
        <video controls>
            <source src="../uploads/${ dto.sfile }" style="width: 450px; height: 450px; border-radius: 0;">
        </video>
    </div>
</c:if>

<!-- 음원일때 -->
<c:if test="${not empty dto.ofile and fileType eq 'sound'}">
    <div>
        <audio controls>
            <source src="../uploads/${ dto.sfile }" style="width: 450px; height: 450px; border-radius: 0;">
        </audio>
    </div>
</c:if>

		    	 </td>
			   </tr>
			   <c:choose>
				 <c:when test="${ (not empty UserId) && (UserId eq dto.id) }">
				   <tr>
			         <td colspan="4" align="center">
		                <button type="button" class="btn btn-primary btn-sm"  onclick="location.href='../board/fboardEdit.do?num=${ dto.num }&id=${ dto.id }';">
		                    수정하기
		                </button>
		                <button type="button" class="btn btn-primary btn-sm"  onclick="confirmDelete('../board/fboardDelete.do?num=${dto.num}&id=${ dto.id }')">
		                    삭제하기
		                </button>
		                <button type="button" class="btn btn-primary btn-sm"  onclick="location.href='../board/fboard.do';">
		                    목록 바로가기
		                </button>
			         </td>
				    </tr>
				 </c:when>
			     <c:otherwise>
			        <tr>
		              <td colspan="4" align="center">
		                 <button type="button" class="btn btn-primary btn-sm" onclick="location.href='../board/fboard.do';">
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
        <%@ include file="../inc/footer.jsp" %> 
        </div>
      </div>   
  	</div>

  <!-- plugins:js -->
  <%@ include file="../inc/js.jsp" %>
</body>
</html>


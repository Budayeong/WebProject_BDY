<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@ include file="../inc/board_head.jsp" %>
  <title>자료실</title>
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
					<!-- 글쓰기 버튼 -->
					<div class="form-group">
			            <div class="justify-content-end d-flex">
			                <button type="button" class="btn btn-outline-primary btn-icon-text" onclick="location.href='../board/fboardWrite.do'">
	                          <i class="ti-file btn-icon-prepend"></i>
	                          글쓰기
	                        </button>
			            </div>
				    </div>
					<!-- 검색 폼 start -->
					<!-- form으로 변경 - ui 수정 -->
					<form method="get">
					    <div class="form-group">
					        <div class="input-group">
					            <div class="input-group-prepend">
					                <select class="btn btn-sm btn-outline-primary dropdown-toggle" name="searchField">
					                    <option value="title">제목</option>
					                    <option value="content">내용</option>
					                    <option value="name">작성자</option>
					                </select>
					            </div>
					            <input type="text" class="form-control" name="searchWord" aria-label="Text input with dropdown button">
					            <div class="input-group-append">
					                <input type="submit" value="검색하기" class="btn btn-sm btn-primary" />
					            </div>
					        </div>
					    </div>
					</form>
					<!-- 검색 폼 end -->
					<!-- 게시판 리스트 start -->
	                <table class="table">
	                  <thead>
	                    <tr>
	                      <th>No</th>
	                      <th>제목</th>
	                      <th>작성자</th>
	                      <th>작성일</th>
	                      <th>조회수</th>
	                      <th>첨부파일</th>
	                    </tr>
	                  </thead>
	                  <tbody>
						 <!-- 검색어 조건에 따라 게시물이 있는지 확인하기 위해 choose태그 사용 -->
						<c:choose>
							<c:when test="${ empty boardLists }">
								<!-- List에 저장된 레코드가 없는 경우  -->
						        <tr>
						            <td colspan="5" align="center">
						                등록된 게시물이 없습니다^^*
						            </td>
						        </tr>
							</c:when>
							<c:otherwise>
								<!-- 저장된 게시물이 있다면 개수만큼 반복해서 출력
								items 속성에는 반복가능한 객체를 기술, 순서대로 추출된 데이터는
								var 속성에 지정한 변수로 저장됨 -->      
								<c:set var="countNum" value="0" />     
								<c:forEach items="${ boardLists }" var="row" varStatus="loop">
							        <tr>
							        	<!-- 가상번호 -->
							            <td>
								            	${ boardParam.totalCount - (((boardParam.pageNum - 1) * boardParam.pageSize) + countNum) }
							            </td>
							            <td>
							            	<a href="../board/fboardView.do?num=${ row.num }">${ row.title }</a>
							            </td> 
							            <td>${ row.name }</td>
							            <td>${ row.postdate }</td>
							            <td>${ row.visitcount }</td>
							            <td>
							            <!-- 다운로드 링크는 첨부파일이 있을때만 표시 -->
							            <c:if test="${ not empty row.ofile }">
							            	<a href="../board/download.do?ofile=${ row.ofile }&sfile=${ row.sfile }&num=${ row.num }">[Down]</a>	
							            </c:if>
							            </td>
							        </tr>
							     <c:set var="countNum" value="${countNum + 1}" />
								</c:forEach>		
							</c:otherwise>
						</c:choose>
	                  </tbody>
	                </table>
	                <!-- 게시판 리스트 end -->
					<!-- 페이징 처리  -->
					<table width="100%">
					  <tr align="center">
					  	<td>
					  		<br/>
					  		${ boardParam.boardPaging }
					  	</td>
					  </tr>
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


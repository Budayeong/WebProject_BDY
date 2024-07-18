<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@ include file="../inc/board_head.jsp" %>
  <title>자유게시판</title>
</head>
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
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
	              <a class="dropdown-item" href="${pageContext.request.contextPath}/member/Logout.do">
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
	                <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/member/Login.do' ">로그인</button>&nbsp&nbsp&nbsp
	                <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/member/Join.do' ">회원가입</button>
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
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_settings-panel.html -->
      <div class="assets-setting-wrapper">
        <div id="settings-trigger"><i class="ti-settings"></i></div>
        <div id="assets-settings" class="settings-panel">
          <i class="settings-close ti-close"></i>
          <p class="settings-heading">SIDEBAR SKINS</p>
          <div class="sidebar-bg-options selected" id="sidebar-light-assets"><div class="img-ss rounded-circle bg-light border mr-3"></div>Light</div>
          <div class="sidebar-bg-options" id="sidebar-dark-assets"><div class="img-ss rounded-circle bg-dark border mr-3"></div>Dark</div>
          <p class="settings-heading mt-2">HEADER SKINS</p>
          <div class="color-tiles mx-0 px-4">
            <div class="tiles success"></div>
            <div class="tiles warning"></div>
            <div class="tiles danger"></div>
            <div class="tiles info"></div>
            <div class="tiles dark"></div>
            <div class="tiles default"></div>
          </div>
        </div>
      </div>
      <!-- partial -->
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
                <li class="nav-item"> <a class="nav-link" href="../board/Board.do">자유게시판</a></li>
                <li class="nav-item"> <a class="nav-link" href="../board/QBoard.do">Q&A게시판</a></li>
                <li class="nav-item"> <a class="nav-link" href="pages/ui-features/typography.html">자료실</a></li>
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
                <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/member/Logout.do"> 로그아웃 </a></li>
                <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/member/modify.do"> 회원정보 </a></li>
			  </c:if>
			  <c:if test="${ empty UserId }">
				<li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/member/Login.do"> 로그인 </a></li>
                <li class="nav-item"> <a class="nav-link" href="${pageContext.request.contextPath}/member/Join.do"> 회원가입 </a></li>
			  </c:if>
              </ul>
            </div>
          </li>
          <!-- 유저페이지 end -->
        </ul>
      </nav>
      <!-- 왼쪽 사이드 바 end -->
      <!-- partial -->
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
                  
                  <!-- 기존 ui -->
<!--                  <div class="form-group"> -->
<!--                     <div class="input-group"> -->
<!--                       <div class="input-group-prepend"> -->
<!--                         <button class="btn btn-sm btn-outline-primary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</button> -->
<!--                         <div class="dropdown-menu"> -->
<!--                           <a class="dropdown-item" href="#">Action</a> -->
<!--                           <a class="dropdown-item" href="#">Another action</a> -->
<!--                           <a class="dropdown-item" href="#">Something else here</a> -->
<!--                           <a class="dropdown-item" href="#">Separated link</a> -->
<!--                         </div> -->
<!--                       </div> -->
<!--                       <input type="text" class="form-control" aria-label="Text input with dropdown button"> -->
<!--                       <div class="input-group-append"> -->
<!--                         <input type="submit" value="검색하기"  class="btn btn-sm btn-primary" /> -->
<!--                       </div> -->
<!--                     </div> -->
<!--                   </div> -->

				<!-- 검색 폼 -->
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


				    
                  
                    <table class="table">
                      <thead>
                        <tr>
                          <th>No</th>
                          <th>제목</th>
                          <th>작성자</th>
                          <th>작성일</th>
                          <th>조회수</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>5</td>
                          <td>제목5</td>
                          <td>작성자5</td>
                          <td><label class="badge badge-danger">Pending</label></td>
                          <th>조회수</th>
                        </tr>
                        <tr>
                          <td>4</td>
                          <td>제목4</td>
                          <td>작성자4</td>
                          <td><label class="badge badge-warning">In progress</label></td>
                          <th>조회수</th>
                        </tr>
                        <tr>
                          <td>3</td>
                          <td>제목3</td>
                          <td>작성자3</td>
                          <td><label class="badge badge-info">Fixed</label></td>
                          <th>조회수</th>
                        </tr>
                        <tr>
                          <td>2</td>
                          <td>제목2</td>
                          <td>작성자2</td>
                          <td><label class="badge badge-success">Completed</label></td>
                          <th>조회수</th>
                        </tr>
                        <tr>
                          <td>1</td>
                          <td>제목1</td>
                          <td>작성자1</td>
                          <td><label class="badge badge-warning">In progress</label></td>
                          <th>조회수</th>
                        </tr>
                      </tbody>
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
      <!-- 게시판 end -->
    </div>   
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->

  <!-- plugins:js -->
  <%@ include file="../inc/js.jsp" %>
</body>

</html>


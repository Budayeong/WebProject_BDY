package controller;

import java.io.IOException;

import common.CookieManager;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MemberDAO;
import model.MemberDTO;

@WebServlet("/member/Login.do")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/theme/pages/samples/Login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		커넥션풀을 이용해 DB연결
		MemberDAO dao = new MemberDAO();
		
//		form에서 사용자 입력값 받아옴
		MemberDTO dto = dao.getMemberDTO(req.getParameter("user_id"), req.getParameter("user_pw"));
		
//		내장객체 사용을 위해 getServletContext() 메서드를 통해 객체를 얻어온 후 사용
		ServletContext application = getServletContext();
		
		// 만약 DTO 객체에 아이디가 저장되어 있다면 로그인에 성공한 것
		System.out.println(dto.getId());
		if(dto.getId() != null){
//			세션 영역에 회원의 아이디와 이름을 저장 
			req.getSession().setAttribute("UserId", dto.getId());
			req.getSession().setAttribute("UserName", dto.getName());
			
            // 아이디 저장버튼이 눌렸다면 로그아웃을 해도 다음 로그인 시 아이디가 표시됨
            if(req.getParameter("save") != null) {
            	CookieManager.makeCookie(resp, "savdId", dto.getId(), 86400);
            } else {
            	CookieManager.deleteCookie(resp, "savdId");
            }

			resp.sendRedirect(req.getContextPath() + "/theme/Index.jsp");
		}
		else {
			/* 
			로그인에 실패한 경우에는 request영역에 에러메세지를 저장한 후
			로그인 페이지로 포워드함. request영역은 포워드 한 페이지까지
			데이터를 공유함.
			*/
			req.setAttribute("LoginErrMsg", "로그인 오류입니다");
			req.getRequestDispatcher("/theme/pages/samples/Login.jsp").forward(req, resp);
		}
	}

	
	
	
}

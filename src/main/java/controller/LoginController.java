package controller;

import java.io.IOException;

import common.CookieManager;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.MemberDAO;
import model.MemberDTO;

@WebServlet("/member/login.do")
public class LoginController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/html/Login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		커넥션풀을 이용해 DB연결
		MemberDAO dao = new MemberDAO();
		
//		form에서 사용자 입력값 받아옴
		MemberDTO dto = dao.getMemberDTO(req.getParameter("user_id"), req.getParameter("user_pw"));
		dao.close();
		
		
		if(dto.getId() == null || dto.getId().equals("")) {
//			로그인에 실패한 경우
			common.JSFunction.alertLocation(resp, "아이디 또는 비밀번호가 일치하지않습니다.", req.getContextPath()+"/html/Login.jsp");
		}
		else {
//			로그인에 성공한 경우: 세션영역에 회원정보 저장
//			세션 영역에 회원의 아이디와 이름을 저장 
			req.getSession().setAttribute("UserId", dto.getId());
			req.getSession().setAttribute("UserName", dto.getName());
			req.getSession().setAttribute("UserPass", dto.getPass());
			
            // 아이디 저장버튼이 눌렸다면 로그아웃을 해도 다음 로그인 시 아이디가 표시됨
            if(req.getParameter("save") != null) {
            	CookieManager.makeCookie(resp, "savdId", dto.getId(), 86400);
            } else {
            	CookieManager.deleteCookie(resp, "savdId");
            }

			resp.sendRedirect(req.getContextPath() + "/html/Index.jsp");
		}
		
		
	}

	
	
	
}

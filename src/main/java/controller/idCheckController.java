package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MemberDAO;

@WebServlet("/member/idCheck.do")
public class idCheckController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		커넥션풀을 이용해 DB연결
		MemberDAO dao = new MemberDAO();
		
//		form에서 사용자 입력값 받아옴
		int checkOk = dao.checkId(req.getParameter("user_id"));
		dao.close();
		
//		checkOk값을 문자열로 변경해 응답
		resp.getWriter().write(String.valueOf(checkOk));
		
	}

	
}

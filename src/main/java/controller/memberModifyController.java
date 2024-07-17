package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MemberDAO;
import model.MemberDTO;

@WebServlet("/member/modify.do")
public class memberModifyController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		커넥션풀을 이용해 DB연결
		MemberDAO dao = new MemberDAO();
		String userId = (String) req.getSession().getAttribute("UserId");
		String userPass = (String) req.getSession().getAttribute("UserPass");
		
		MemberDTO dto = dao.getMemberDTO(userId, userPass);
		
		req.getSession().setAttribute("UserName", dto.getName());
		req.getSession().setAttribute("UserEmail", dto.getEmail());
		req.getSession().setAttribute("UserTel", dto.getTel());
		
		
		req.getRequestDispatcher("/theme/pages/samples/memberModify.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("멤버 정보 수정");
		
	}
	
	
}

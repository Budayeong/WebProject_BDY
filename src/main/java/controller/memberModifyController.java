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
		
		
		req.getRequestDispatcher("/html/memberModify.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("멤버 정보 수정 시작");
		
//		커넥션풀을 이용해 DB연결
		MemberDAO dao = new MemberDAO();
		
		String userId = (String) req.getSession().getAttribute("UserId");
		String userPass = (String) req.getSession().getAttribute("UserPass");
		
		MemberDTO dto = new MemberDTO();
		dto.setId(req.getParameter("member_id"));
		dto.setPass(req.getParameter("member_pass"));
		dto.setName(req.getParameter("member_name"));
		dto.setEmail(req.getParameter("member_eamil"));
		dto.setTel(req.getParameter("member_tel"));
		
		int result = dao.updateMember(dto, userId);
		
		if(result == 1) {
			System.out.println("업데이트 성공");
			
//			회원정보 수정 후 현재 페이지에도 변화된 내용으로 세션에 이름정보 저장
			req.getSession().setAttribute("UserId", dto.getId());
			req.getSession().setAttribute("UserName", dto.getName());
			req.getSession().setAttribute("UserPass", dto.getPass());
			
			resp.sendRedirect("../html/Index.jsp");
		}
		else {
			System.out.println("업데이트 실패");
			req.getRequestDispatcher("../html/memberModify.jsp");
			resp.sendRedirect("../html/memberModify.jsp");
		}
		
		
		
		
		
		
	}
	
	
}

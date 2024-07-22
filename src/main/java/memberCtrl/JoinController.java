package memberCtrl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MemberDAO;
import model.MemberDTO;

@WebServlet("/member/join.do")
public class JoinController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/Member/Join.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		커넥션풀을 이용해 DB연결
		MemberDAO dao = new MemberDAO();
		
//		form에서 사용자 입력값 받아옴
		String id = req.getParameter("join_id");
		String pass = req.getParameter("join_pass");
		String name = req.getParameter("join_name");
		String email = req.getParameter("join_email");
		String tel = req.getParameter("join_tel");
		
		System.out.println("id: " + id + ", pass: " + pass +", name: " + name + ", email: " + email + ", tel: " + tel);
		
//		폼값 dto에 저장
		MemberDTO dto = new MemberDTO();
		dto.setId(req.getParameter("join_id"));
		dto.setPass(req.getParameter("join_pass"));
		dto.setName(req.getParameter("join_name"));
		dto.setEmail(req.getParameter("join_email"));
		dto.setTel(req.getParameter("join_tel"));
		System.out.println(dto.getId() + " / " + dto.getPass());
		
//		dao를 통해 db에 insert
		int result = dao.insertMember(dto);
		dao.close();
		
		System.out.println(result);
//		insert에 성공하면 로그인으로 이동, 실패하면 회원가입페이지로 이동
		if(result==1)
			resp.sendRedirect(req.getContextPath() + "/Member/JoinEnd.jsp");
		else
			common.JSFunction.alertLocation(resp, "회원가입에 실패했습니다", req.getContextPath()+"/Member/Join.jsp");
		
		
		
	}

	
	
	
}

package boardCtrl;

import java.io.IOException;

import common.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BoardDAO;
import model.BoardDTO;

@WebServlet("/board/boardEdit.do")
public class BoardEditController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		선택된 게시글의 번호 받음
		String num = req.getParameter("num");
		String id = req.getParameter("id");
		System.out.println(num +"/" + id);
		
		if(req.getSession().getAttribute("UserId") == null){
			JSFunction.alertLocation(resp, "로그인 후 이용가능합니다.", "../member/login.do");
			return;
		}
		if(!(req.getSession().getAttribute("UserId").equals(id))) {
			JSFunction.alertBack(resp, "잘못된 접근입니다.");
			return;
		}
		
		
		BoardDAO dao = new BoardDAO();
		
		BoardDTO dto = dao.selectView(num);
		dao.close();
		
//		선택된 게시글의 내용을 반환
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/Board/BoardEdit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String num = req.getParameter("num");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
//		DTO에 저장
		BoardDTO dto = new BoardDTO();
		dto.setNum(num);
		dto.setTitle(title);
		dto.setContent(content);
		
//		DB에 수정 내용 적용
		BoardDAO dao = new BoardDAO();
		int result = dao.updatePost(dto);
		dao.close();
		
		if(result==1) {
			resp.sendRedirect("../board/boardView.do?num=" + num);
		}
		else 
			System.out.println("update 중 예외 발생");
	}
	
}

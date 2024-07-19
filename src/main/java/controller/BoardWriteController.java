package controller;

import java.io.IOException;

import common.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BoardDAO;
import model.BoardDTO;

@WebServlet("/board/boardWrite.do")
public class BoardWriteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("UserId") == null){
			JSFunction.alertLocation(resp, "로그인 후 이용가능합니다.", "../member/login.do");
			/* 
			JSP가 Tomcat에서 Java로 변환되면 스크립트릿에 작성된 코드는 
			_jspService()메서드 내부에 기술됨. 따라서 return은 해당 메서드의
			실행을 종료한다는 의미로 사용됨.
			return이후의 문장은 실행되지 않는다.
			 */
			return;
		}
		
		req.getRequestDispatcher("/html/BoardWrite.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		현재 로그인되어 있는 유저의 아이디와 이름을 세션에서 가져옴
		String UserId = (String) req.getSession().getAttribute("UserId");
		String UserName = (String) req.getSession().getAttribute("UserName");
		
//		폼값을 DTO에 저장
		BoardDTO dto = new BoardDTO();
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		dto.setId(UserId);
		dto.setName(UserName);
		
		
		BoardDAO dao = new BoardDAO();
		int result = dao.insertWrite(dto);
		dao.close();
		
//		insert에 성공하면 목록으로 이동, 실패하면 쓰기페이지로 이동
		if(result==1)
			resp.sendRedirect("../board/board.do");
		else
			JSFunction.alertLocation(resp, "글쓰기에 실패했습니다", "../board/boardWrite.do");
		
	}
	
}

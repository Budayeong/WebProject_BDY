package controller;

import java.io.IOException;

import common.CookieManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BoardDAO;
import model.BoardDTO;

@WebServlet("/board/boardView.do")
public class BoardViewController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		커넥션풀을 이용해 DB연결
		BoardDAO dao = new BoardDAO();
		
//		파라미터로 전달된 일련번호 받기
		String num = req.getParameter("num");
		
//		게시물 인출
		BoardDTO dto = dao.selectView(num);
		
//		페이지로 진입하면 "visit현재게시글번호" 라는 쿠키가 있는지 확인
		String visitCookieOk = CookieManager.readCookie(req, "visit"+num);
		if(visitCookieOk.equals("")){
			// 게시물 조회수 증가
			dao.updateVisitCount(num);
			// 	쿠키 생성
			CookieManager.makeCookie(resp, "visit"+num, "visit"+num , 86400);
		}
		dao.close();

		/*
		내용의 경우 Enter를 통해 줄바꿈을 하게되므로 웹브라우저 출력시
		<br> 태그로 변경해야함.
		*/
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		
//		게시물이 저장된 DTO 객체를 request영역에 저장, JSP로 포워드
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/html/BoardView.jsp").forward(req, resp);
	}
	
	
}



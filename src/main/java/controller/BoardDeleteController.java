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

@WebServlet("/board/boardDelete.do")
public class BoardDeleteController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String num = req.getParameter("num");
		
//		게시글 본인인증 (url을 통해 들어올 경우)
		if (req.getSession().getAttribute("UserId") == null || !(req.getSession().getAttribute("UserId").equals(num))) {
			JSFunction.alertBack(resp, "잘못된 접근입니다.");
			return;
		}
		
		System.out.println("삭제할게염");
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.selectView(num);
		
//		게시물 삭제처리
		int result = dao.deletePost(num);
		dao.close();
		
//		게시물이 삭제되었다면
		if(result == 1) {
			JSFunction.alertLocation(resp, "삭제되었습니다", "../board/board.do");
		}
		
		
	}
	
}

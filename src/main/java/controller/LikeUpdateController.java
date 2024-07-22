package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BoardDAO;

@WebServlet("/board/likeUpdate.do")
public class LikeUpdateController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		커넥션풀을 이용해 DB연결
		BoardDAO dao = new BoardDAO();
		
//		form에서 사용자 입력값 받아옴
//		int likeUpdate = dao.updateLikeCount(req.getParameter("user_id"));
		dao.close();
		
//		checkOk값을 문자열로 변경해 응답
//		resp.getWriter().write(String.valueOf(likeUpdate));
		
	}

	
}

package qboardCtrl;

import java.io.IOException;

import common.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CommentDAO;
import model.CommentDTO;

@WebServlet("/board/commentAdd.do")
public class CommentAddController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CommentDAO dao = new CommentDAO();
		
//		현재 로그인 한 유저의 아이디, 이름 가져옴
		String UserId = (String) req.getSession().getAttribute("UserId");
//		댓글을 단 게시글의 번호 가져옴
		String bnum = req.getParameter("bnum");
		
		CommentDTO dto = new CommentDTO();
		dto.setContent(req.getParameter("content"));
		dto.setId(UserId);
		dto.setBnum(bnum);
		
		int result = dao.insertComment(dto);
		
		dao.close();
		
		if(result==1)
			resp.sendRedirect("../board/qboardView.do?num=" + bnum);
		else
			JSFunction.alertLocation(resp, "댓글 작성에 실패했습니다", "../board/qboardView.do?num=" + bnum);
	}
	
	

}

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

@WebServlet("/board/commentDelete.do")
public class CommentDeleteController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("댓글삭제시작");
//		현재 게시글 번호
		String bnum = req.getParameter("bnum");
//		현재 댓글 번호
		String num = req.getParameter("num");
		
		System.out.println("bnum의 값: " + bnum);
		CommentDAO dao = new CommentDAO();
		int result = dao.deleteComment(num);
		dao.close();
		
		if(result==1)
			resp.sendRedirect("../board/qboardView.do?num=" + bnum);
		else
			JSFunction.alertLocation(resp, "댓글 삭제에 실패했습니다", "../board/qboardView.do?num=" + bnum);
		
		
	}
	
	

}

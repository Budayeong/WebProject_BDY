package fboardCtrl;

import java.io.IOException;

import common.FileUtil;
import common.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FBoardDAO;
import model.FBoardDTO;

@WebServlet("/board/fboardDelete.do")
public class FBoardDeleteController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String num = req.getParameter("num");
		String id = req.getParameter("id");
		
//		게시글 본인인증 (url을 통해 들어올 경우)
		if (req.getSession().getAttribute("UserId") == null || !(req.getSession().getAttribute("UserId").equals(id))) {
			JSFunction.alertBack(resp, "잘못된 접근입니다.");
			return;
		}
		
		
//	기존 게시물의 내용을 가져옴 
		FBoardDAO dao = new FBoardDAO();
		FBoardDTO dto = dao.selectView(num);
		
//		게시물 삭제처리
		int result = dao.deletePost(num);
		dao.close();
		
//		게시물이 삭제되었다면
		if(result == 1) {
//			DB에 저장된 파일명을 인출한 후
			String saveFileName = dto.getSfile();
//			서버에서 파일을 삭제함
			FileUtil.deleteFile(req, "/uploads", saveFileName);
			JSFunction.alertLocation(resp, "삭제되었습니다", "../board/fboard.do");
		}
		
		
	}
	
}

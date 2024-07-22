package qboardCtrl;

import java.io.IOException;
import java.util.Arrays;

import common.CookieManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.QBoardDAO;
import model.QBoardDTO;

//어노테이션으로 매핑
@WebServlet("/board/qboardView.do")
public class QBoardViewController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		QBoardDAO dao = new QBoardDAO();
//		파라미터로 전달된 일련번호 받기
		String num = req.getParameter("num");
//		현재 로그인한 유저의 아이디
		String UserId = (String) req.getSession().getAttribute("UserId");
//		게시물 인출
		QBoardDTO dto = dao.selectView(num);
		
//		페이지로 진입하면 "visit현재게시글번호" 라는 쿠키가 있는지 확인
		String visitCookieOk = CookieManager.readCookie(req, "qboard_visit"+num+UserId);
		if(visitCookieOk.equals("")){
			// 게시물 조회수 증가
			dao.updateVisitCount(num);
			// 	쿠키 생성
			CookieManager.makeCookie(resp, "qboard_visit"+num+UserId, "qboard_visit"+num+UserId , 86400);
		}
		dao.close();
		
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		
		String ext = null;
		String fileName = dto.getSfile();
		String fileType = null;
		
		if(fileName != null) {
			ext = fileName.substring(fileName.lastIndexOf(".")+1);
//			이미지인 경우
			if(ext.equals("jpg") || ext.equals("png") || ext.equals("gif")) 
				fileType ="img";
//			동영상인 경우
			if(ext.equals("mp4") || ext.equals("avi"))
				fileType ="video";
//			음원인 경우
			if(ext.equals("mp3"))
				fileType ="sound";
		}
		
		
		req.setAttribute("fileType", fileType);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/QBoard/QBoardView.jsp").forward(req, resp);
	}
}

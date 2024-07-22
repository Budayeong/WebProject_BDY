package qboardCtrl;

import java.io.IOException;

import common.FileUtil;
import common.JSFunction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.QBoardDAO;
import model.QBoardDTO;

@WebServlet("/board/qboardEdit.do")
@MultipartConfig(
		maxFileSize = 1024 * 1024 *100,
		maxRequestSize = 1024 * 1024 * 1000
		)
public class QBoardEditController extends HttpServlet {
//	수정페이지로 진입하면 기존 게시물의 내용을 가져와 작성폼에 설정.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String num = req.getParameter("num");
		String id = req.getParameter("id");
		
//		게시글 본인인증 (url을 통해 들어올 경우)
		if (req.getSession().getAttribute("UserId") == null || !(req.getSession().getAttribute("UserId").equals(id))) {
			JSFunction.alertBack(resp, "잘못된 접근입니다.");
			return;
		}
		
		/*
		파라미터로 전달된 일련번호를 통해 기존의 게시물 인출.
		상세보기에서 사용한 메서드 그대로 사용
		*/
		QBoardDAO dao = new QBoardDAO();
		QBoardDTO dto = dao.selectView(num);
//		DTO를 request영역에 저장한 후 수정페이지로 포워드
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/QBoard/QBoardEdit.jsp").forward(req, resp);
	}

//	수정할 내용을 입력한 후 전송된 폼값을 update 쿼리문으로 실행
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		업로드 디렉터리의 물리적 경로 얻기
		String saveDirectory = req.getServletContext().getRealPath("/uploads");
		
//		파일 업로드
		String originalFileName = "";
		try {
			originalFileName = FileUtil.uploadFile(req, saveDirectory);
		}
		catch (Exception e) {
			JSFunction.alertLocation(resp, "파일 업로드 오류입니다.", "../board/qboardWrite.do");
			return;
		}
		

		String num = req.getParameter("num");
		String prevOfile = req.getParameter("prevOfile");
		String prevSfile = req.getParameter("prevSfile");
		
		String title = req.getParameter("title");
		System.out.println(title);
		String content = req.getParameter("content");
		
		
//		DTO에 저장
		QBoardDTO dto = new QBoardDTO();
		dto.setNum(num);
		dto.setTitle(title);
		dto.setContent(content);
		
		/*
		수정페이지에서는 첨부파일을 등록하지 않을수도 있으므로 파일이 있을때와
		없을때를 구분해서 DTO를 설정해야함
		*/
		if(originalFileName != "") {
//			첨부파일이 있는 경우에는 저장된 파일명을 새롭게 변경
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			
//			새롭게 등록한 파일의 정보를 DTO에 추가
			dto.setOfile(originalFileName);
			dto.setSfile(savedFileName);
			
//			기존 파일 삭제
			FileUtil.deleteFile(req, "/uploads", prevSfile);
		}
		else {
//			첨부파일이 없으면 기존 파일명 유지
			dto.setOfile(prevOfile);
			dto.setSfile(prevSfile);
		}
		
//		DB에 수정 내용 적용
		QBoardDAO dao = new QBoardDAO();
		int result = dao.updatePost(dto);
		dao.close();
		
		if(result==1) {
			resp.sendRedirect("../board/qboardView.do?num=" + num);
		}
		else
//			실패하면 경고창을 띄우고 이동
			JSFunction.alertLocation(resp, "비밀번호 검증을 다시 진행해주세요.", "../board/qboardView.do?num=" +num);
	}
}

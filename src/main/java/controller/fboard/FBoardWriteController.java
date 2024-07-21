package controller.fboard;

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

@WebServlet("/board/fboardWrite.do")
public class FBoardWriteController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("UserId") == null){
			JSFunction.alertLocation(resp, "로그인 후 이용가능합니다.", "../member/login.do");
			return;
		}
		req.getRequestDispatcher("/html/FBoardWrite.jsp").forward(req, resp);
	}
	
	/*
	글쓰기는 post방식의 전송이므로 doPost()에서 요청을 처리.
	*/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		업로드 디렉터리의 물리적 경로 확인
		String saveDirectory = req.getServletContext().getRealPath("/uploads");
		
//		파일 업로드
		String originalFileName = "";
		try {
//			업로드가 정상적으로 완료되면 원본파일명을 반환
			originalFileName = FileUtil.uploadFile(req, saveDirectory);
		}
		catch (Exception e) {
//			파일업로드 시 오류 발생하면 경고창 띄운 후 작성페이지로 이동
			JSFunction.alertLocation(resp, "파일 업로드 오류입니다.", "../board/fboardWrite.do");
			return;
		}
		
//		파일 외 폼값을 DTO에 저장
		FBoardDTO dto = new FBoardDTO();
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		
//		오리지널 파일명이 빈값이 아니면 = 첨부파일 업로드가 정상적으로 완료됐으면
		if(originalFileName != "") {
//			파일명을 "날짜_시간.확장자" 형식으로 변경
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
//			파일 확장자
			String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
			
//			원본파일명과 저장된파일명을 DTO에 저장
			dto.setOfile(originalFileName);
			dto.setSfile(savedFileName);
		}
		
//		DAO를 통해 DB에 값 입력
		FBoardDAO dao = new FBoardDAO();
		int result = dao.insertWrite(dto);
		dao.close();
		
//		insert에 성공하면 목록으로 이동, 실패하면 쓰기페이지로 이동
		if(result==1)
			resp.sendRedirect("../board/fboard.do");
		else
			JSFunction.alertLocation(resp, "글쓰기에 실패했습니다", "../board/fboardWrite.do");
		
	}
	
	
	
}

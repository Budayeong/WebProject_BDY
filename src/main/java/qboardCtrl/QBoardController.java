package qboardCtrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.BoardPage;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.QBoardDAO;
import model.QBoardDTO;

@WebServlet("/board/qboard.do")
public class QBoardController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		커넥션풀을 이용해 DB연결
		QBoardDAO dao = new QBoardDAO();
		
//		DB 및 사용자에게 전달할 파라미터들을 저장할 Map
		Map<String, Object> boardParam = new HashMap<String, Object>();
		
		/* 게시물 처리 */
//		검색폼에서 검색필드와 단어를 가져옴
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		if(searchWord != null){
//			클라이언트가 입력한 검색어가 있는 경우, Map에 컬럼명과 값 추가
			boardParam.put("searchField", searchField);
			boardParam.put("searchWord", searchWord);
		}
		
//		Map을 인수로 게시물의 개수를 카운트
		int totalCount = dao.selectCount(boardParam);
		
		/* 페이지 처리 */
		ServletContext application = getServletContext();
//		web.xml에서 파라미터 가져옴
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
//		전체 페이지 수
		int totalPage = (int)Math.ceil((double)totalCount / pageSize);
			
//		현재 페이지 (처음 진입하면 pageNum 없음 -> 1페이지)
		int pageNum = 1; // 기본값
		String pageTemp = req.getParameter("pageNum");
		if(pageTemp != null && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp); //요청받은 페이지로 수정
		
//		출력할 게시물 구간
		int start = (pageNum - 1) * pageSize + 1;
		int end = pageNum * pageSize; 
		boardParam.put("start", start);
		boardParam.put("end", end);
		
//		DB로부터 게시물 목록을 받아옴
		List<QBoardDTO> boardLists = dao.selectListPage(boardParam);
		dao.close();
		
		/* 반환 */
		String boardPaging = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../board/qboard.do");
		boardParam.put("boardPaging", boardPaging);
		boardParam.put("totalCount", totalCount);
		boardParam.put("pageSize", pageSize);
		boardParam.put("pageNum", pageNum);
		
		
		req.setAttribute("boardLists",boardLists);
		req.setAttribute("boardParam", boardParam);
		
		
//		list 페이지로 이동
		req.getRequestDispatcher("/QBoard/QBoard.jsp").forward(req, resp);
		
	}
	
	
	
	
}

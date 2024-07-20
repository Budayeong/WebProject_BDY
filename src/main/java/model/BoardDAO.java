package model;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class BoardDAO extends DBConnPool {
	
	public BoardDAO() {}
	
//	게시물의 개수 반환
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		
//		게시물 수를 얻어오기 위한 쿼리문 작성
		String query = "SELECT COUNT(*) FROM pboard";
//		검색어가 있는 경우 where절을 추가하여 조건에 맞는 게시물만 select
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")+ " " 
					+ " LIKE '%" + map.get("searchWord") + "%'"; 
		}
		
		try {
//			정적쿼리문 실행을 위한 Statement 인스턴스 생성
			stmt = con.createStatement();
//			쿼리문 실행 후 결과는 ResultSet으로 반환
			rs = stmt.executeQuery(query);
			
//			커서를 첫번째 행으로 이동하여 레코드를 읽는다
			rs.next();
//			첫번째 컬럼(count함수)의 값을 가져와서 변수에 저장
			totalCount = rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시물 수를 구하는 중 예외발생");
			e.printStackTrace();
		}
		System.out.println("selectCount 완료");
		return totalCount;
	}
	
//	게시물 목록 반환
	public List<BoardDTO> selectListPage(Map<String, Object> map) {
		List<BoardDTO> boardList = new Vector<BoardDTO>();
		
//		인파라미터가 있는 서브쿼리문 작성
		String query ="SELECT * FROM ( "
				+ "    SELECT Tb.*, ROWNUM rNum FROM ( "
				+ "        SELECT * FROM pboard ";
		
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")
					+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		query += "		ORDER BY num DESC "
				+ "     ) Tb "
				+ "   ) "
				+ "    WHERE rNum BETWEEN ? AND ?";
		
		try {
//			prepared 인스턴스 생성 및 인파라미터 설정
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
			
			while(rs.next()) {
//				하나의 레코드를 저장할 수 있는 dto 객체 생성
				BoardDTO dto = new BoardDTO();
				
//				setter를 이용해 각 컬럼의 값을 멤버변수에 저장
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setVisitcount(rs.getString("visitcount"));
				
//				레코드 하나를 dto에 저장 후 List에 추가
				boardList.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
//		인출한 레코드를 저장한 List를 JSP로 반환
		return boardList;
	}
	
//	상세보기: 게시물 한개 반환
//	상세보기를 위해 일련번호에 해당하는 레코드 1개를 인출해서 반환
	public BoardDTO selectView(String num) {
		
		BoardDTO dto = new BoardDTO();
		
//		인파라미터가 있는 select 쿼리문
		String query = "SELECT * FROM pboard WHERE num=?";
		try {
//			인파라미터 설정 및 쿼리실행
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			
//			결과를 DTO에 저장
			if(rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));;
				dto.setName(rs.getString(5));
				dto.setPostdate(rs.getDate(6));
				dto.setVisitcount(rs.getString(7));
			}
		}
		catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외발생");
			e.printStackTrace();
		}
		
		return dto;
	}
	
//	글쓰기
	public int insertWrite(BoardDTO dto) {
		int result = 0 ;
		try{
			/*
			쿼리문에서 사용한 시퀀스는 모델1 게시판에서 생성한 내용 그대로 사용.
			나머지 값들은 컨트롤러에서 받은 후 모델(DAO)로 전달함.
			*/
			String query = "INSERT INTO pboard ( "
						+ " num, title, content, id, name) "
						+ " VALUES ( "
						+ " seq_pboard_num.NEXTVAL, ?, ?, ?, ? )";
			
//			인파라미터가 없는 정적쿼리문을 실행하므로 Statement인스턴스 생성
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			psmt.setString(4, dto.getName());
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
//	수정하기
	public int updatePost(BoardDTO dto) {
		int result = 0;
		try {
//			수정을 위한 update 쿼리문 작성 (일련번호와 패스워드까지 조건문에 추가)
			String query = "UPDATE pboard "
						+ "SET title=?, content=?"
						+ "WHERE num=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3,  dto.getNum());
			
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
}

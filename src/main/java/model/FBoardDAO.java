package model;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class FBoardDAO extends DBConnPool {
	
	public FBoardDAO() {} 
	
//	게시물 개수 카운트
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		
		String query = "SELECT COUNT(*) FROM fboard";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")+ " " 
					+ " LIKE '%" + map.get("searchWord") + "%'"; 
		}
		
		try {
//			인파라미터가 없는 정적쿼리문을 실행하므로 Statement인스턴스 생성
			stmt = con.createStatement();
//			쿼림누 실행 후 결과 반환
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시물 카운트 중 예외발생");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	

//	게시물 목록 반환
	public List<FBoardDTO> selectListPage(Map<String, Object> map) {
		
		List<FBoardDTO> board = new Vector<FBoardDTO>();
		
		/*
		페이징 처리를 위한 서브쿼리문 작성. 게시물의 순차적인 일련번호를 부여하는
		rownum을 통해 게시물을 구간에 맞게 인출.
		*/
		String query ="SELECT * FROM ( "
				+ "    SELECT Tb.*, ROWNUM rNum FROM ( "
				+ "        SELECT * FROM fboard ";
		
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")
					+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		query += "		ORDER BY num DESC "
				+ "     ) Tb "
				+ "   ) "
				+ "    WHERE rNum BETWEEN ? AND ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();

			while(rs.next()) {
				FBoardDTO dto = new FBoardDTO();
				
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setName(rs.getString(5));
				dto.setPostdate(rs.getDate(6));
				dto.setVisitcount(rs.getInt(7));
				dto.setOfile(rs.getString(8));
				dto.setSfile(rs.getString(9));
				dto.setDowncount(rs.getInt(10));
				
				board.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board;
	}
	
//	상세보기: 게시물 한개 반환
//	상세보기를 위해 일련번호에 해당하는 레코드 1개를 인출해서 반환
	public FBoardDTO selectView(String num) {
		
		FBoardDTO dto = new FBoardDTO();
		
//		인파라미터가 있는 select 쿼리문
		String query = "SELECT * FROM fboard WHERE num=?";
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
				dto.setVisitcount(rs.getInt(7));
				dto.setOfile(rs.getString(8));
				dto.setSfile(rs.getString(9));
				dto.setDowncount(rs.getInt(10));
			}
		}
		catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외발생");
			e.printStackTrace();
		}
		
		return dto;
	}
	
//	글쓰기 페이지에서 전송한 폼값을 테이블에 insert
	public int insertWrite(FBoardDTO dto) {
		int result = 0 ;
		try{

			String query = "INSERT INTO fboard ( "
						+ " num, title, content, id, name, ofile, sfile) "
						+ " VALUES ( "
						+ " seq_board_num.NEXTVAL, ?, ?, ?, ?, ?, ? )";

			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			psmt.setString(4, dto.getName());
			psmt.setString(5, dto.getOfile()); //원본파일명
			psmt.setString(6, dto.getSfile()); //서버에 저장된 파일명
			result = psmt.executeUpdate();
			
		}
		catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	
	
//	게시물 조회수 증가하기
	public void updateVisitCount(String num) {
		String query =  "UPDATE fboard SET"
						+ " visitcount = visitcount + 1"
						+ " WHERE num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
		}
		catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외발생");
			e.printStackTrace();
		}
		
	}
	
}

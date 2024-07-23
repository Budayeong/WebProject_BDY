package model;

import java.sql.SQLException;
import java.util.ArrayList;

import common.DBConnPool;

public class CommentDAO extends DBConnPool {
	public CommentDAO() {} 
	
//	댓글 목록 (게시글의 번호를 받음)
	public ArrayList<CommentDTO> listComment(String bnum){
		// 부모글 번호를 조건으로 받기
//		String sql ="SELECT b.*, p.name "
//				+"FROM bcomment b "
//				+"LEFT JOIN pmember p ON b.id = p.id "
//				+"WHERE b.bnum = ?";
		String sql = "select b.*, (select name from pmember where id = b.id) "
				+ "from bcomment b where bnum=? order by num asc";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, bnum);
			
			rs = psmt.executeQuery();
			
			ArrayList<CommentDTO> commentList = new ArrayList<CommentDTO>();
			
			while (rs.next()) {
				
				CommentDTO dto = new CommentDTO();
				
				dto.setNum(rs.getString("num"));
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setBnum(rs.getString("bnum"));
				dto.setName(rs.getString(6));
				
				commentList.add(dto);
				
			}
			
			return commentList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

//	댓글입력
	public int insertComment(CommentDTO dto) {
		int result = 0 ;
		
		try {
			String sql = "INSERT INTO bcomment (num, id, content, bnum) " 
					+" VALUES (seq_bcomment_num.NEXTVAL, ?, ?, ?)";
			
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getBnum());
			
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
//	댓글삭제
	public int deleteComment(String num) {
		int result = 0;
		
		String sql = "DELETE FROM bcomment WHERE num =?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, num);
			
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
//	댓글 선택
	public CommentDTO selectComment(String num) {
		
		CommentDTO dto = new CommentDTO();
		String sql = "select b.*, (select name from pmember where id = b.id) "
				+ "from bcomment b where num=? order by num asc";
		
		try {
//			인파라미터 설정 및 쿼리실행
			psmt = con.prepareStatement(sql);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			
//			결과를 DTO에 저장
			if(rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setId(rs.getString(2));;
				dto.setContent(rs.getString(3));
				dto.setRegdate(rs.getString(4));
				dto.setBnum(rs.getString(5));
				dto.setName(rs.getString(6));
			}
		}
		catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외발생");
			e.printStackTrace();
		}
		return dto;
		
	}
	
	public int updateComment(CommentDTO dto) {
		System.out.println(dto.getContent());
		System.out.println(dto.getNum());
		int result = 0;
		
		String sql = "UPDATE bcomment SET content=? WHERE num=?";
		
		try {
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, dto.getContent());
			psmt.setString(2, dto.getNum());
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}

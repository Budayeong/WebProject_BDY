package model;

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
				
				System.out.println(dto.getNum() + "/" + dto.getContent());
				
			}
			
			return commentList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

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
	
	
	
}

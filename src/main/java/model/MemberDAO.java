package model;

import common.DBConnPool;

public class MemberDAO extends DBConnPool {
	
	public MemberDAO() {};


	public MemberDTO getMemberDTO(String uid, String upass)	{
		System.out.println(uid +" / "+ upass);
//		회원인증을 위한 쿼리문 실행 후 회원정보 저장을 위한 인스턴스 생성
		MemberDTO dto = new MemberDTO();
//		로그인 폼에서 입력한 아이디, 패스워드를 통해 인파라미터를 설정할 수 있도록 쿼리문 작성
		String query = "select * from pmember where id=? and pass=?";
		
		try {
//			쿼리문 실행을 위한 prepared 인스턴스 생성
			psmt = con.prepareStatement(query);
//			쿼리문의 인파라미터 설정 (아이디, 비밀번호)
			psmt.setString(1, uid);
			psmt.setString(2, upass);
//			쿼리문 실행 후 결과는 ResultSet으로 반환
			rs = psmt.executeQuery();
			
			System.out.println("rs.next()실행");
//			반환된 ResultSet 객체에 정보가 저장되어있는지 확인
			if(rs.next()) {
//				회원정보가 있다면 DTO객체에 저장한다
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setTel(rs.getString(5));
				System.out.println("dto에 id 설정확인" + dto.getId());
				System.out.println("rs.next() 실행완료");
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
//		회원정보를 저장한 DTO객체를 반환
		return dto;
	}

//	회원가입: 멤버추가 메서드
	public int insertMember(MemberDTO dto) {
		int result = 0 ;
		try{
			String query = "INSERT INTO pmember ( "
						+ " id, pass, name, email, tel ) "
						+ " VALUES ( "
						+ " ?, ?, ?, ?, ? )";
			
			psmt = con.prepareStatement(query);
			System.out.println(dto.getId());
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			psmt.setString(5, dto.getTel()); 
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("회원가입 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
//	회원가입: 아이디중복확인
	public int checkId(String id) {
		
		int check = 0; 
		try {
			String query = "SELECT count(*) "
					+ "FROM pmember "
					+ "WHERE id = ?";
			
//			인파라미터가 있는 동적쿼리문 실행
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
                check = rs.getInt(1);
            }
			System.out.println(check);
		}
		catch (Exception e) {
			System.out.println("아이디 체크 중 예외 발생");
			e.printStackTrace();
		}
		
		return check;
	}
	
//	회원정보 수정
	public int updateMember(MemberDTO dto, String id) {
		int result = 0;
		try{
			
			String query = "UPDATE pmember SET id = ?, pass = ?, name = ?, email = ?, tel =? WHERE id = ? ";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			psmt.setString(5, dto.getTel());
			psmt.setString(6, id);
			result = psmt.executeUpdate();
			
		}
		catch (Exception e) {
			System.out.println("회원정보 수정 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	
	
		
}

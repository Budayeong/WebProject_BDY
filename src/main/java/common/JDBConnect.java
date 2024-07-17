package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

public class JDBConnect {
	
//	DB 연결, 정적쿼리실행, 동적쿼리실행, select결과 반환
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	
//	인자생성자2: Application내장객체를 매개변수로 선언
	public JDBConnect(ServletContext application) {
		/*
		내장객체를 전달받았으므로 Java클래스 내에서 web.xml에 접근할 수 있음
		그러면 JSP에서 DB연결을 위해 반복적으로 사용했던 코드를 메서드로 정의하여
		중복된 코드를 줄일 수 있음 
		*/
		try {
			String driver = application.getInitParameter("OracleDriver");
			Class.forName(driver);
			String url = application.getInitParameter("OracleURL");
			String id = application.getInitParameter("OracleId");
			String pwd = application.getInitParameter("OraclePwd");
			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB 연결 성공(인수 생성자 2)");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void close() {
		try {
			if(con!=null) con.close();
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(psmt!=null) psmt.close();
			System.out.println("JDBC 자원 반납");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}

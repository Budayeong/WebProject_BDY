package common;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;

//자주 사용하는 JS 함수를 클래스로 정의
public class JSFunction {
	
//	[모델2 사용]
	public static void alertLocation(HttpServletResponse resp, String msg, String url) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = "" 
					+ "<script>"
					+ "		alert('" + msg + "');"
					+ "		location.href='"+url+"';"
					+"</script>";
//			해당 문자열을 웹브라우저에 출력
			writer.print(script);
		}
		catch (Exception e) {}
	}

	public static void alertBack(HttpServletResponse resp, String msg) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = ""
					+ "<script>"
					+ "		alert('" + msg + "');"
					+ "		history.back();"
					+ "</script>";
			writer.print(script);
		}
		catch (Exception e) {}
	}
	
	
}

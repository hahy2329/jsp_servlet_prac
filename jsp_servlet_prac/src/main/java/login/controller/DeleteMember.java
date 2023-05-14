package login.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.dao.MemberDAO;


@WebServlet("/deleteMember")
public class DeleteMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String file_repo_path="C:\\Users\\hahy2\\git\\jsp_servlet_prac\\jsp_servlet_prac\\WebContent\\step2_loginExxx\\memberImg\\";
		
		String imgNm = MemberDAO.getInstance().getMemberDetail((String)session.getAttribute("memberId")).getImgNm();
		new File(file_repo_path + imgNm).delete();
		
		MemberDAO.getInstance().deleteMember((String)session.getAttribute("memberId"));
		
		
		session.invalidate();
		
		String jsScript ="<script>";
		jsScript +="alert('정상적으로 탈퇴 되었습니다.');";
		jsScript +="location.href='mainMember';";
		jsScript +="</script>";
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsScript);
		
	}

	

}

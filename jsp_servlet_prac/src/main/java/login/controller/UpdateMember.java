package login.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import login.dao.MemberDAO;
import login.dto.MemberDTO;

@WebServlet("/updateMember")
public class UpdateMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.setAttribute("memberDTO", MemberDAO.getInstance().getMemberDetail((String)session.getAttribute("memberId")));
		
		RequestDispatcher dis = request.getRequestDispatcher("step2_loginEx/mUpdate.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String file_repo_path = "C:\\Users\\hahy2\\git\\jsp_servlet_prac\\jsp_servlet_prac\\WebContent\\step2_loginExxx\\memberImg\\";
		MultipartRequest multi = new MultipartRequest(request, file_repo_path, 1024 * 1024 *30, "utf-8" ,new DefaultFileRenamePolicy());
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId(multi.getParameter("memberId"));
		memberDTO.setMemberNm(multi.getParameter("memberNm"));
		memberDTO.setSex(multi.getParameter("sex"));
		memberDTO.setBirthDt(multi.getParameter("birthDt"));
		memberDTO.setHp(multi.getParameter("hp"));
		if(multi.getParameter("smsstsYn") == null) memberDTO.setSmsstsYn("N");
		else									memberDTO.setSmsstsYn(multi.getParameter("smsstsYn"));
		memberDTO.setEmail(multi.getParameter("email"));
		if(multi.getParameter("emailstsYn")==null)	memberDTO.setEmailstsYn("N");
		else								memberDTO.setEmailstsYn(multi.getParameter("emailstsYn"));
		memberDTO.setZipcode(multi.getParameter("zipcode"));
		memberDTO.setRoadAddress(multi.getParameter("roadAddress"));
		memberDTO.setJibunAddress(multi.getParameter("jibunAddress"));
		memberDTO.setNamujiAddress(multi.getParameter("namujiAddress"));
		
		Enumeration files = multi.getFileNames();
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD");
		String imgNm = "";
		String originalFileName = "";
		if(files.hasMoreElements()) {
			String element = (String)files.nextElement();
			if(multi.getOriginalFileName(element) != null) {
				originalFileName = multi.getOriginalFileName(element);
				imgNm = sdf.format(new Date()) + "_" + UUID.randomUUID() +"_" + originalFileName;
				
				
				String deleteImgNm = MemberDAO.getInstance().getMemberDetail(multi.getParameter("memberId")).getImgNm();
				new File(file_repo_path + deleteImgNm);
				
				File file = new File(file_repo_path + originalFileName);	// 새로 업로드한 파일을 읽어옴
				File renameFile = new File(file_repo_path + imgNm);			// 변환된 파일명으로 새로운 파일을 생성
				file.renameTo(renameFile);		
				
				
				
				
			}
		}
		
		memberDTO.setImgNm(imgNm);
		MemberDAO.getInstance().updateMember(memberDTO);
		
		String jsScript = "<script>";
		   jsScript += "alert('수정 되었습니다.');";
		   jsScript += "location.href='detailMember';";
		   jsScript += "</script>";

		   response.setContentType("text/html; charset=utf-8");
		   PrintWriter out = response.getWriter();	
		   out.print(jsScript);
		
	}

}

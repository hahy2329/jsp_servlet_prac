package boardAdvance.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardAdvance.dao.BoardAdvanceDAO;


@WebServlet("/replyUpdate")
public class ReplyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
 		request.setAttribute("replyDTO", BoardAdvanceDAO.getInstance().getReplyDetail(Long.parseLong(request.getParameter("replyId"))));
 		
 		RequestDispatcher dis = request.getRequestDispatcher("step3_boardAdvanceExxx/reply/replyUpdate.jsp");
 		dis.forward(request, response);
 		
 		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}

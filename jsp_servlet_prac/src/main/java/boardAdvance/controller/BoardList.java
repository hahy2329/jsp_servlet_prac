package boardAdvance.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardAdvance.dao.BoardAdvanceDAO;
import boardAdvance.dto.MainBoardDTO;


@WebServlet("/boardList")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchKeyword = request.getParameter("searchKeyword");
		if(searchKeyword == null) {
			searchKeyword = "total";
		} //검색 페이지에서 아무것도 선택이 안되었을 때 total로 기본 옵션
		
		String searchWord = request.getParameter("searchWord");
		if(searchWord == null) {
			searchWord = "";
		}//검색창에 아무것도 입력을 안했다면 빈 공백으로 기본 옵션 
		
		int onePageViewCnt = 10; 
		
		if(request.getParameter("onePageViewCnt") != null) {
			onePageViewCnt = Integer.parseInt(request.getParameter("onePageViewCnt"));
			
		}// 화면에 보여질 게시글의 개수를 지정 
		
		//현재 보여지고 있는 페이지의 number값을 읽어들임
		String temp = request.getParameter("currentPageNumber");
		if(temp ==null) {
			temp="1";
		}
		
		
		int currentPageNumber = Integer.parseInt(temp);
		//현재 보여지고 있는 페이지의 number값을 int형으로 change!
		
		int allBoardCnt = BoardAdvanceDAO.getInstance().getAllBoardCnt(searchKeyword, searchWord);
		//검색을 통한 전체 게시글의 개수 
		
		
		int allPageCnt = allBoardCnt / onePageViewCnt + 1;
		//전체 필요한 페이지 수 
		
		if(allBoardCnt % onePageViewCnt == 0) allPageCnt--;
		//만약에 게시글의 총 개수가 하나의 화면에 보여지는 게시글 개수로 나누어 나머지가 0이라면 전체 페이지수를 -1
		
		int startPage = (currentPageNumber - 1) / 10 * 10 + 1;
		if(startPage == 0) {
			startPage = 1;
		}//화면에 처음 시작하는 페이지 번호  
		
		int endPage = startPage + 9;
		//화면에 끝나는 페이지 번호 
		
		
		
		//현재 보여질 페이지 시작 번호를 설정
		int startBoardIdx = (currentPageNumber - 1) * onePageViewCnt;
		
		ArrayList<MainBoardDTO> boardList = BoardAdvanceDAO.getInstance().getBoardList(searchKeyword, searchWord,startBoardIdx, onePageViewCnt);
		request.setAttribute("boardList", boardList);
		request.setAttribute("onePageViewCnt" , onePageViewCnt);
		request.setAttribute("allBoardCnt"    , allBoardCnt);
		request.setAttribute("startBoardIdx"    , startBoardIdx);
		request.setAttribute("currentPageNumber"   , currentPageNumber);
		request.setAttribute("startPage"        , startPage);
		request.setAttribute("endPage"          , endPage);
		request.setAttribute("allPageCnt"    , allPageCnt);
		request.setAttribute("searchKeyword"    , searchKeyword);
		request.setAttribute("searchWord"       , searchWord);
		
		RequestDispatcher dis = request.getRequestDispatcher("step3_boardAdvanceExxx/board/boardList.jsp");
		dis.forward(request, response);
				
				
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

package kr.co.rfy.notice.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.notice.model.service.NoticeService;
import kr.co.rfy.notice.model.service.NoticeServiceImpl;

/**
 * Servlet implementation class NoticeSearchServlet
 */
@WebServlet("/notice/noticeSearch.do")
public class NoticeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//페이지 처리값 갖고오기
		int currentPage;
						
		if(request.getParameter("currentPage") == null) {//첫 페이지
			currentPage = 1;
		}else {//그 다음 페이지 이동하기
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//키워드 갖고오기
		request.setCharacterEncoding("utf-8");
		String keyword = request.getParameter("keyword");
		String type = request.getParameter("type");
		
		NoticeService nservice = new NoticeServiceImpl();
		HashMap<String, Object> map = nservice.noticeSearch(currentPage,keyword,type);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/customerServiceCenter/notice.jsp");
		request.setAttribute("pageDataMap", map);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("keyword", keyword);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

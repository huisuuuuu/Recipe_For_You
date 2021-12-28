package kr.co.rfy.qnaAdmin.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.qnaAdmin.model.service.QnaAdminService;
import kr.co.rfy.qnaAdmin.model.service.QnaAdminServiceImpl;

/**
 * Servlet implementation class QnaAdminSearchListServlet
 */
@WebServlet("/qna/qnaAdminSearch.do")
public class QnaAdminSearchListServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaAdminSearchListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		
		QnaAdminService adminService = new QnaAdminServiceImpl();
		HashMap<String, Object> map = adminService.qnaAdminSearch(currentPage,keyword,type);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/customerServiceCenter/qnaAdminList.jsp");
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

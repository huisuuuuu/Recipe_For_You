package kr.co.rfy.qna.controller;

import java.io.IOException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.qna.model.service.QnaService;
import kr.co.rfy.qna.model.service.QnaServiceImpl;

/**
 * Servlet implementation class QnaServlet
 */
@WebServlet("/customerServiceCenter/qna.do")
public class QnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//페이지 처리
		int currentPage;
				
		if(request.getParameter("currentPage") == null) {//첫 페이지
			currentPage = 1;
		}else {//그 다음 페이지 넘기면
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//비지니스 로직
		QnaService service = new QnaServiceImpl();
		HashMap<String, Object> pageDataMap = service.selectAllQnaList(currentPage);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/customerServiceCenter/qna.jsp");
		request.setAttribute("pageDataMap", pageDataMap);
		request.setAttribute("currentPage", currentPage);
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

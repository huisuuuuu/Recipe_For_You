package kr.co.fty.NoticeQNA.controllrt;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fty.NoticeQNA.model.service.QnaAdminService;
import kr.co.fty.NoticeQNA.model.service.QnaAdminServiceImpl;

/**
 * Servlet implementation class QnaAdminListServlet
 */
@WebServlet("/customerServiceCenter/qnaAdmin.do")
public class QnaAdminListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaAdminListServlet() {
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
		
		QnaAdminService adminService = new QnaAdminServiceImpl();
		HashMap<String, Object> pageDataMap = adminService.selectAllQnaList(currentPage);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/customerServiceCenter/qnaAdminList.jsp");
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

package kr.co.rfy.noticeAdmin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.common.MemberAuthorityCheck;
import kr.co.rfy.notice.model.vo.Notice;
import kr.co.rfy.noticeAdmin.model.service.NoticeAdminService;
import kr.co.rfy.noticeAdmin.model.service.NoticeAdminServiceImpl;

/**
 * Servlet implementation class NoticeAdminViewServlet
 */
@WebServlet("/notice/noticeAdminView.do")
public class NoticeAdminViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeAdminViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String roll = MemberAuthorityCheck.authorityCheck(request, response);
		
		if(roll == null) {
			response.sendRedirect("/views/common/error.jsp");
			return;
		}
		
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		NoticeAdminService adminService = new NoticeAdminServiceImpl();
		Notice n = adminService.noticeAdminView(board_no);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/customerServiceCenter/noticeAdminView.jsp");
		request.setAttribute("n", n);
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

package kr.co.rfy.noticeAdmin.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.common.MemberAuthorityCheck;
import kr.co.rfy.noticeAdmin.model.service.NoticeAdminService;
import kr.co.rfy.noticeAdmin.model.service.NoticeAdminServiceImpl;

/**
 * Servlet implementation class NoticeAdminListServlet
 */
@WebServlet("/customerServiceCenter/noticeAdmin.do")
public class NoticeAdminListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeAdminListServlet() {
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
		
		//페이지 처리
		int currentPage;
		
		if(request.getParameter("currentPage") == null) {//첫 페이지
			currentPage = 1;
		}else {//그 다음 페이지 넘기면
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//페이징 처리한것 비지니스 로직
		NoticeAdminService adminService = new NoticeAdminServiceImpl();
		adminService.selectAllNoticeList(currentPage);
		HashMap<String, Object> pageDataMap = adminService.selectAllNoticeList(currentPage);
		
		
		
		RequestDispatcher view = request.getRequestDispatcher("/views/customerServiceCenter/noticeAdminList.jsp");
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

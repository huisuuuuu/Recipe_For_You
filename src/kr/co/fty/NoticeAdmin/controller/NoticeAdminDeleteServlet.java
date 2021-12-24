package kr.co.fty.NoticeAdmin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fty.NoticeAdmin.model.service.NoticeAdminService;
import kr.co.fty.NoticeAdmin.model.service.NoticeAdminServiceImpl;
import kr.co.rfy.member.vo.Member;

/**
 * Servlet implementation class NoticeAdminDeleteServlet
 */
@WebServlet("/customerServiceCenter/noticeDelete.do")
public class NoticeAdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeAdminDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		String user_id = ((Member)request.getSession().getAttribute("member")).getUserId();
		
		//비지니스 로직
		NoticeAdminService adminService = new NoticeAdminServiceImpl();
		int result = adminService.deleteView(board_no,user_id);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/customerServiceCenter/noticeDelete.jsp");
		
		if(result>0) {
			request.setAttribute("result", true);
		}else {
			request.setAttribute("result", false);
		}
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

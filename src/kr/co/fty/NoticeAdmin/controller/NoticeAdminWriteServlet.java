package kr.co.fty.NoticeAdmin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fty.NoticeAdmin.model.service.NoticeAdminService;
import kr.co.fty.NoticeAdmin.model.service.NoticeAdminServiceImpl;
import kr.co.rfy.member.model.vo.Member;
import kr.co.rfy.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeAdminWriteServlet
 */
@WebServlet("/notice/noticeWrite.do")
public class NoticeAdminWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeAdminWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//작성자 아이디 검증 후 갖고오기
		if(request.getSession().getAttribute("member")==null) {
			response.sendRedirect("/views/customerServiceCenter/error.jsp");
			return;
		}
		String user_id = ((Member)request.getSession().getAttribute("member")).getUser_id();
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setUser_id(user_id);
		
		NoticeAdminService adminService = new NoticeAdminServiceImpl();
		int result = adminService.insertNotice(notice);
		
		if(result > 0) {
			int board_no = adminService.searchBoardNo(notice);
			response.sendRedirect("http://localhost/customerServiceCenter/noticeAdmin.do?board_no="+board_no+"&currentPage=1");
		}else {
			response.sendRedirect("/views/common/error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

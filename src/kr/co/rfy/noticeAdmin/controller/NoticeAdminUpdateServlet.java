package kr.co.rfy.noticeAdmin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.member.model.vo.Member;
import kr.co.rfy.notice.model.vo.Notice;
import kr.co.rfy.noticeAdmin.model.service.NoticeAdminService;
import kr.co.rfy.noticeAdmin.model.service.NoticeAdminServiceImpl;

/**
 * Servlet implementation class NoticeAdminUpdateServlet
 */
@WebServlet("/notice/noticeUpdate.do")
public class NoticeAdminUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeAdminUpdateServlet() {
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
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		//작성자 아이디 검증 후 갖고오기
		if(request.getSession().getAttribute("member")==null) {
			response.sendRedirect("/views/common/error.jsp");
			return;
		}
		String user_id = ((Member)request.getSession().getAttribute("member")).getUserId();
		
		Notice notice = new Notice();
		notice.getTitle();
		notice.getContent();
		notice.getUser_id();
		notice.getBoard_no();

		
		NoticeAdminService service = new NoticeAdminServiceImpl();
		int result = service.updateView(notice,title,content,board_no,user_id);
		
		RequestDispatcher view = request.getRequestDispatcher("/notice/noticeAdminView.do?board_no="+board_no+"&currentPage="+currentPage);
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

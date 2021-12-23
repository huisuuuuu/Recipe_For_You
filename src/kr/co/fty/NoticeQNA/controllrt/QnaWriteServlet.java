package kr.co.fty.NoticeQNA.controllrt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fty.NoticeQNA.model.service.QnaAdminService;
import kr.co.fty.NoticeQNA.model.service.QnaAdminServiceImpl;
import kr.co.rfy.member.model.vo.Member;
import kr.co.rfy.qna.model.vo.Qna;
import oracle.net.aso.q;

/**
 * Servlet implementation class QnaWriteServlet
 */
@WebServlet("/qna/qnaWrite.do")
public class QnaWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String q_code =request.getParameter("q_code");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//작성자 아이디 검증 후 갖고오기
		if(request.getSession().getAttribute("member")==null) {
			response.sendRedirect("/views/common/error.jsp");
			return;
		}
		String user_id = ((Member)request.getSession().getAttribute("member")).getUser_id();
		
		Qna qna = new Qna();
		qna.setQ_code(q_code);
		qna.setTitle(title);
		qna.setContent(content);
		qna.setUser_id(user_id);
		
		System.out.println(q_code);
		System.out.println(title);
		System.out.println(content);
		System.out.println(user_id);
		
		QnaAdminService adminService = new QnaAdminServiceImpl();
		int result = adminService.insertQna(qna);
		
		if(result > 0) {
			int board_no = adminService.searchBoardNo(qna);
			response.sendRedirect("http://localhost/customerServiceCenter/qnaAdmin.do?board_no="+board_no+"&currentPage=1");
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

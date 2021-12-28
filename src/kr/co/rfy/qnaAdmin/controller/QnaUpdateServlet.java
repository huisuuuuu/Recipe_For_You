package kr.co.rfy.qnaAdmin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.member.model.vo.Member;
import kr.co.rfy.qna.model.vo.Qna;
import kr.co.rfy.qnaAdmin.model.service.QnaAdminService;
import kr.co.rfy.qnaAdmin.model.service.QnaAdminServiceImpl;

/**
 * Servlet implementation class QnaUpdateServlet
 */
@WebServlet("/qna/qnaUpdate.do")
public class QnaUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String q_code = request.getParameter("q_code");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int board_no =Integer.parseInt(request.getParameter("board_no"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		//작성자 아이디 검증 후 갖고오기
		if(request.getSession().getAttribute("member")==null) {
			response.sendRedirect("/views/common/error.jsp");
			return;
		}
		String user_id = ((Member)request.getSession().getAttribute("member")).getUserId();
		
		Qna qna = new Qna();
		qna.getQ_code();
		qna.getTitle();
		qna.getContent();
		qna.getUser_id();
		qna.getBoard_no();

//		System.out.println(q_code);
//		System.out.println(title);
//		System.out.println(content);
//		System.out.println(user_id);
//		System.out.println(board_no);
//		System.out.println(currentPage);
		
		QnaAdminService service = new QnaAdminServiceImpl();
		int result = service.updateView(qna,board_no,user_id,title,content,q_code);
		
		RequestDispatcher view = request.getRequestDispatcher("/qna/qnaAmdinView.do?board_no="+board_no+"&currentPage="+currentPage);
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

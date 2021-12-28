package kr.co.rfy.qnaAdmin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.common.MemberAuthorityCheck;
import kr.co.rfy.qna.model.vo.Qna;
import kr.co.rfy.qnaAdmin.model.service.QnaAdminService;
import kr.co.rfy.qnaAdmin.model.service.QnaAdminServiceImpl;

/**
 * Servlet implementation class QnaAdminViewServlet
 */
@WebServlet("/qna/qnaAmdinView.do")
public class QnaAdminViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaAdminViewServlet() {
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
		
		
		QnaAdminService adminService = new QnaAdminServiceImpl();
		Qna qna = adminService.qnaAdminView(board_no);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/customerServiceCenter/qnaAdminView.jsp");
		request.setAttribute("qna", qna);
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

package kr.co.rfy.qnaAdmin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.member.model.vo.Member;
import kr.co.rfy.qnaAdmin.model.service.QnaAdminService;
import kr.co.rfy.qnaAdmin.model.service.QnaAdminServiceImpl;

/**
 * Servlet implementation class QnaDeleteServlet
 */
@WebServlet("/qna/qnaDelete.do")
public class QnaDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaDeleteServlet() {
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
		
		QnaAdminService service = new QnaAdminServiceImpl();
		int result = service.deleteView(board_no,user_id);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/customerServiceCenter/qnaDelete.jsp");
		
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

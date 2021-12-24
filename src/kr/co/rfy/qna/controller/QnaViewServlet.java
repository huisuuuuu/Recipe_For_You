package kr.co.rfy.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.qna.model.service.QnaService;
import kr.co.rfy.qna.model.service.QnaServiceImpl;
import kr.co.rfy.qna.model.vo.Qna;

/**
 * Servlet implementation class QnaViewServlet
 */
@WebServlet("/qna/qnaView.do")
public class QnaViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		QnaService service = new QnaServiceImpl();
		Qna qna = service.qnaView(board_no);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/customerServiceCenter/qnaView.jsp");
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

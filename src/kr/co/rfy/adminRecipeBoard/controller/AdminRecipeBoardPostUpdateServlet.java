package kr.co.rfy.adminRecipeBoard.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardService;
import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardServiceImpl;

/**
 * Servlet implementation class AdminRecipeBoardPostUpdateServlet
 */
@WebServlet("/recipeBoard/recipePostUpdate.do")
public class AdminRecipeBoardPostUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminRecipeBoardPostUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		System.out.println("요청 게시글 번호: "+boardNo);

		AdminRecipeBoardService rbService = new AdminRecipeBoardServiceImpl();
		HashMap<String, Object> pageDataMap = rbService.selectOnePost(boardNo);

		// 가져온 데이터를 view 페이지로 전달
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/adminRecipeBoardSelectPostUpdate.jsp");
		request.setAttribute("pageDataMap", pageDataMap);
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

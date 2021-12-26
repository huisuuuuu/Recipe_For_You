package kr.co.rfy.adminRecipeBoard.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardService;
import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardServiceImpl;
import kr.co.rfy.common.MemberAuthorityCheck;

/**
 * Servlet implementation class AdminRecipeBoardListDeleteServlet
 */
@WebServlet("/adRecipeBoard/deletAdminBoardList.do")
public class AdminRecipeBoardListDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminRecipeBoardListDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String roll = MemberAuthorityCheck.authorityCheck(request, response);

		// 권한 검증 작업(권한을 확인하여 관리자 또는 운영자가 아니라면 error 페이지로 안내)
		if (roll == null) {
			response.sendRedirect("/views/common/error.jsp");
			return;
		}

		// 삭제하려는 게시물 번호 가져오기
		String[] recipeBoardNoValues = request.getParameterValues("postNo");
		
		AdminRecipeBoardService rbService = new AdminRecipeBoardServiceImpl();
		int result = rbService.deleteAdminPost(recipeBoardNoValues);

		if (result == recipeBoardNoValues.length) {
			response.sendRedirect("/recipeBoard/recipeBoardAllSelect.do");

		} else {
			response.sendRedirect("/views/common/error.jsp");
		}

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

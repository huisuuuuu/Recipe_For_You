package kr.co.rfy.adminRecipeBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardService;
import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardServiceImpl;
import kr.co.rfy.common.MemberAuthorityCheck;

/**
 * Servlet implementation class AdminRecipeBoardMemberBlackServlet
 */
@WebServlet("/adRecipeBoard/memberBlack.do")
public class AdminRecipeBoardMemberBlackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminRecipeBoardMemberBlackServlet() {
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

		// 삭제하려는 게시물 작성자 아이디 가져오기
		String[] recipePostWriterIdValues = request.getParameterValues("postWriterId");

		AdminRecipeBoardService rbService = new AdminRecipeBoardServiceImpl();
		int result = rbService.recipeBoardMemberBlack(recipePostWriterIdValues);

		if (result == recipePostWriterIdValues.length) {
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

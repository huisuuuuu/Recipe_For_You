package kr.co.rfy.recipeBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardService;
import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardServiceImpl;
import kr.co.rfy.adminRecipeBoard.model.vo.AdminRecipeBoard;
import kr.co.rfy.common.MemberAuthorityCheck;
import kr.co.rfy.member.model.vo.Member;
import kr.co.rfy.recipeBoard.model.service.RecipeService;
import kr.co.rfy.recipeBoard.model.service.RecipeServiceImpl;

/**
 * Servlet implementation class UserRecipePostUpdateCompleteService
 */
@WebServlet("/recipeBoard/userRecipePostUpdateComplete.do")
public class UserRecipePostUpdateCompleteService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRecipePostUpdateCompleteService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		request.setCharacterEncoding("UTF-8");
		
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String title = request.getParameter("title");
		String subTitle = request.getParameter("subTitle");
		String recipeCode = request.getParameter("recipe_Code");
		String levelCode = request.getParameter("level_Code");
		String timeCode = request.getParameter("time_Code");
		String writer = ((Member) request.getSession().getAttribute("member")).getUserId();
		
		AdminRecipeBoard arb = new AdminRecipeBoard(boardNo, recipeCode, levelCode, timeCode, title, subTitle, writer);
		
		String[] ingredientNameValues = request.getParameterValues("ingredientName");
		String[] ingredientNumValues = request.getParameterValues("ingredientNum");
		String[] recipeContentValues = request.getParameterValues("recipeContent");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		RecipeService rService = new RecipeServiceImpl();
		
		boolean result = rService.updateUserRecipePost(arb,ingredientNameValues,ingredientNumValues,recipeContentValues);
		
		if (result) {
			response.sendRedirect("/recipe/recipeSelectContent.do?boardNo="+boardNo);
		} else {
			response.sendRedirect("/views/common/error.jsp");
		};
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package kr.co.rfy.recipeBoard.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.recipeBoard.model.service.RecipeService;
import kr.co.rfy.recipeBoard.model.service.RecipeServiceImpl;

/**
 * Servlet implementation class RecipeBoardSelectContentServlet
 */
@WebServlet("/recipe/recipeSelectContent.do")
public class RecipeBoardSelectContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeBoardSelectContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		int currentPage=Integer.parseInt(request.getParameter("currentPage"));
		
		
		
		RecipeService rService = new RecipeServiceImpl();
		HashMap<String,Object> recipeDetailInfo=rService.selectOnePost(boardNo);

		RequestDispatcher view =request.getRequestDispatcher("/views/recipe/recipeDetail.jsp");
		request.setAttribute("recipeDetailInfo", recipeDetailInfo);
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

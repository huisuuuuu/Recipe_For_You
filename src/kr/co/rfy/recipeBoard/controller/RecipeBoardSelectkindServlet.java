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
 * Servlet implementation class RecipeBoardSelectkindServlet
 */
@WebServlet("/recipe/recipeBoardSelectList.do")
public class RecipeBoardSelectkindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeBoardSelectkindServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String recipeKind=request.getParameter("recipe");

		
		//String recipeCode;
		
		
		switch(recipeKind)
		{
		case "hansik": recipeKind="Recipe_01"; break;
		case "yangsik": recipeKind="Recipe_02"; break;
		case "ilsik": recipeKind="Recipe_03"; break;
		case "jungsik": recipeKind="Recipe_04"; break;
		case "bunsik": recipeKind="Recipe_05"; break;
		case "vege": recipeKind="Recipe_06"; break;
		case "dite": recipeKind="Recipe_07"; break;
		case "banchan": recipeKind="Recipe_08"; break;
		case "annju": recipeKind="Recipe_09"; break;
		}
		
		
		
		int currentPage;
		
		if(request.getParameter("currentPage")==null)
		{	
			currentPage = 1;
		}else
		{
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
			
	
		RecipeService rService = new RecipeServiceImpl();
		
		HashMap<String, Object> kindPageDataMap =rService.selectRecipeKindAllList(currentPage,recipeKind);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/recipe/recipeKind.jsp");
		request.setAttribute("kindPageDataMap", kindPageDataMap);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("recipe", recipeKind);
		
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

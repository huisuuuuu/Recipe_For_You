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
		
		request.setCharacterEncoding("UTF-8");
		
		String recipeKind=request.getParameter("recipe");

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

		
		//검색조건 값 받아오기 (최신,난이도,조리시간,추천순)
		
		String type=(String)request.getParameter("type");
		
		if(type==null)
		{
			type ="latest_desc";
		}else 
		{
			type=(String)request.getParameter("type");
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
		
		HashMap<String, Object> kindPageDataMap =rService.selectRecipeKindAllList(currentPage,recipeKind,type);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/recipe/recipeKind.jsp");
		response.setCharacterEncoding("UTF-8");
		request.setAttribute("kindPageDataMap", kindPageDataMap);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("type", type);
		
		switch(recipeKind)	
		{
		case "Recipe_01": recipeKind="hansik"; break;
		case "Recipe_02": recipeKind="yangsik"; break;
		case "Recipe_03": recipeKind="ilsik"; break;
		case "Recipe_04": recipeKind="jungsik"; break;
		case "Recipe_05": recipeKind="bunsik"; break;
		case "Recipe_06": recipeKind="vege"; break;
		case "Recipe_07": recipeKind="dite"; break;
		case "Recipe_08": recipeKind="banchan"; break;
		case "Recipe_09": recipeKind="annju"; break;
		}
		
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

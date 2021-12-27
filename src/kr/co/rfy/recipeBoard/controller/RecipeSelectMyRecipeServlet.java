package kr.co.rfy.recipeBoard.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.buf.UEncoder;

import kr.co.rfy.member.model.vo.Member;
import kr.co.rfy.recipeBoard.model.service.RecipeService;
import kr.co.rfy.recipeBoard.model.service.RecipeServiceImpl;

/**
 * Servlet implementation class RecipeSelectMyRecipe
 */
@WebServlet("/recipe/recipeBoard/myRecipe.do")
public class RecipeSelectMyRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeSelectMyRecipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String userId=((Member)session.getAttribute("member")).getUserId();
		
		
		
		int currentPage;
		
		if(request.getParameter("currentPage")==null)
		{
			currentPage = 1;
		}else
		{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//요청한 page 값을 갖고 페이지 목록화 처리 -> currentPage에 대한 글 목록 가져온다
		
		RecipeService rService = new RecipeServiceImpl();
		
		HashMap<String, Object>	myRecipeDataMap =rService.selectMyRecipeList(currentPage,userId);
		
		
		RequestDispatcher view = request.getRequestDispatcher("/views/recipe/myRecipe.jsp");
		request.setAttribute("myRecipeDataMap", myRecipeDataMap);
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

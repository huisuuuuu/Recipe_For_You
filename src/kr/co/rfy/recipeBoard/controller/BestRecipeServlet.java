package kr.co.rfy.recipeBoard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.rfy.mybox.model.service.MyboxService;
import kr.co.rfy.mybox.model.service.MyboxServiceImpl;
import kr.co.rfy.mybox.model.vo.RecipeWithFile;
import kr.co.rfy.recipeBoard.model.service.RecipeService;
import kr.co.rfy.recipeBoard.model.service.RecipeServiceImpl;
import kr.co.rfy.recipeBoard.model.vo.OurRecipe;

/**
 * Servlet implementation class BestRecipeServlet
 */
@WebServlet("/recipe/bestRecipePost.do")
public class BestRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BestRecipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//best recipe 게시물 가져오기
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
		ArrayList<OurRecipe> bestRecipe	=rService.selectBestRecipe(currentPage);
		
		
		//메인 페이지 상단 데이터 가져오기
		
		MyboxService myboxService = new MyboxServiceImpl();
		
		// USER_ID
		HttpSession session = request.getSession();
		//String userId = (String) session.getAttribute("user_id");
		String userId = "test1000";
		System.out.println("======== userId: " + userId);
		
		
		List<RecipeWithFile> recipeWithFileList = null;
		try {
			recipeWithFileList = myboxService.topMatchedRecipes(userId);
			for (RecipeWithFile recipe : recipeWithFileList) {
				System.out.println(recipe);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(recipeWithFileList == null) {
			recipeWithFileList = new LinkedList<RecipeWithFile>();
		}
		
		
		RequestDispatcher view = request.getRequestDispatcher("/views/recipe/mainPage.jsp");
		
		request.setAttribute("bestRecipe", bestRecipe);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("recipeWithFileList", recipeWithFileList);
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

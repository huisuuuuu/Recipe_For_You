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
 * Servlet implementation class RecipeBoardAllSelectServlet
 */
@WebServlet("/recipe/recipeBoard/selectAll.do")
public class RecipeBoardAllSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeBoardAllSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
		
		//검색조건 값 받아오기 (최신,난이도,조리시간,추천순)
		
		request.setCharacterEncoding("UTF-8");
		
		String type="";
		
		if(request.getParameter("type")==null)
		{
			type ="latest_desc";
		}else
		{
			type = (String)request.getParameter("type");
		}
		
		
		
		HashMap<String, Object> pageDataMap =rService.selectAllPostList(currentPage,type);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/recipe/recipeAll.jsp");
		request.setAttribute("pageDataMap", pageDataMap);
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

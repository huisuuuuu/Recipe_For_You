package kr.co.rfy.adminRecipeBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardService;
import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardServiceImpl;

/**
 * Servlet implementation class AdminRecipeBoardPostLikeServlet
 */
@WebServlet("/recipeBoard/recipeBoardPostLike.do")
public class AdminRecipeBoardPostLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRecipeBoardPostLikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int likeNum = Integer.parseInt(request.getParameter("likeNum"));
		AdminRecipeBoardService rbService = new AdminRecipeBoardServiceImpl();
		int result = rbService.updateRecipePostLike(boardNo, likeNum);
		
		if(result>0)
		{
			response.sendRedirect("/recipeBoard/recipeBoardSelectContent.do?boardNo="+boardNo);
		}else
		{
			response.sendRedirect("/views/commons/error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

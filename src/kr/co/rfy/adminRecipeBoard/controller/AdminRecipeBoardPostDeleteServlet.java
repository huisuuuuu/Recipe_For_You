package kr.co.rfy.adminRecipeBoard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardService;
import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardServiceImpl;
import kr.co.rfy.member.model.vo.Member;

/**
 * Servlet implementation class AdminRecipeBoardPostDeleteServlet
 */
@WebServlet("/recipeBoard/recipeBoardPostDelete.do")
public class AdminRecipeBoardPostDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRecipeBoardPostDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//삭제할 게시물의 번호
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		//요청자가 누구인지 userId 값을 session에서 추출
		String writer = ((Member)request.getSession().getAttribute("member")).getUser_Id();
		
		//비즈니스 로직 처리
		AdminRecipeBoardService rbService = new AdminRecipeBoardServiceImpl();
		int result = rbService.deletePost(boardNo,writer);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/deleteRecipePost.jsp");
		
		if(result>0)
		{
			request.setAttribute("result",true);
		}else
		{
			request.setAttribute("result",false);
		}
		
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

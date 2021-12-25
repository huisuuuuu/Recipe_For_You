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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String roll = MemberAuthorityCheck.authorityCheck(request, response);
		
		//삭제하려는 게시물 번호 가져오기
		String [] recipeBoardNoValues = request.getParameterValues("postNo");
		
		AdminRecipeBoardService rbService = new AdminRecipeBoardServiceImpl();
		int result = rbService.recipeBoardMemberBlack(recipeBoardNoValues);
		
		if(result==recipeBoardNoValues.length)
		{
			response.sendRedirect("/recipeBoard/recipeBoardAllSelect.do");
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

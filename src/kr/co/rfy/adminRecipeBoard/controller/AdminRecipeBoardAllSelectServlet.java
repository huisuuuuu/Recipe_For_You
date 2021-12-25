package kr.co.rfy.adminRecipeBoard.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardService;
import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardServiceImpl;
import kr.co.rfy.common.MemberAuthorityCheck;

/**
 * Servlet implementation class RecipeBoardAllSelectServlet
 */
@WebServlet("/recipeBoard/recipeBoardAllSelect.do")
public class AdminRecipeBoardAllSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRecipeBoardAllSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//관리자 페이지처럼 특정한 권한이 필요한 경우에는 권한을 확인해서 필터링 할 수 있어야 한다.
		//즉, 관리자가 아니라면 이 Servlet은 동작되어서는 안된다.
		String roll = MemberAuthorityCheck.authorityCheck(request, response);
		
		//권한 검증 작업(권한을 확인하여 관리자 또는 운영자가 아니라면 error 페이지로 안내)
		if(roll==null)
		{
			response.sendRedirect("/views/commons/error.jsp");
			return ;
		}
		
		//해당 Servlet 요청 시, 페이지 값을 받아와서 동작하는 형태로 구현
		
		int currentPage;
		
		if(request.getParameter("currentPage") == null)
		{	
			//adminNavigation에서 '레시피 조회 및 관리 게시판'으로 이동하는 경우 가장 첫 페이지인 1page로 셋팅
			currentPage = 1;
			
		}else
		{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//사용자가 요청한 Page 값(currentPage)을 가지고 페이지에 따른 데이터 목록화를 비즈니스 로직으로 처리
		AdminRecipeBoardService rbService = new AdminRecipeBoardServiceImpl();
		HashMap<String,Object> pageDataMap = rbService.selectAllPostList(currentPage);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/adminRecipeBoard.jsp");
		
		request.setAttribute("pageDataMap", pageDataMap);
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

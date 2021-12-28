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

/**
 * Servlet implementation class AdminRecipeBoardCategorySelectServlet
 */
@WebServlet("/recipeBoard/recipeBoardCategorySelect.do")
public class AdminRecipeBoardCategorySelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRecipeBoardCategorySelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 해당 Servlet 요청 시, 페이지 값을 받아와서 동작하는 형태로 구현

				int currentPage;

				if (request.getParameter("currentPage") == null) {
					// adminNavigation에서 '레시피 조회 및 관리 게시판'으로 이동하는 경우 가장 첫 페이지인 1page로 셋팅
					currentPage = 1;

				} else {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				}
				
				//키워드 가져오기
				//인코딩
				request.setCharacterEncoding("UTF-8");
				
				String recipeCode = request.getParameter("recipeCode");
				System.out.println(recipeCode);
				
				//해당 값을 가지고, 비즈니스 로직 처리(키워드에 해당하는 게시물을 검색 후 페이징 처리하여 리턴)
				AdminRecipeBoardService rbService = new AdminRecipeBoardServiceImpl();
				HashMap<String,Object> map = rbService.selectPostCategory(currentPage, recipeCode);
				
				RequestDispatcher view = request.getRequestDispatcher("/views/admin/adminRecipeBoard.jsp");
				
				request.setAttribute("pageDataMap", map);
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

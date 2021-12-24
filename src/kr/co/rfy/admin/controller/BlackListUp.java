package kr.co.rfy.admin.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.admin.model.service.AdminMemberService;
import kr.co.rfy.admin.model.service.AdminMemberServiceImpl;
import kr.co.rfy.common.MemberAuthorityCheck;

/**
 * Servlet implementation class BlackListUp
 */
@WebServlet("/admin/blackListUp.do")
public class BlackListUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlackListUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roll = MemberAuthorityCheck.authorityCheck(request, response);

		int currentPage;

		if (request.getParameter("currentPage") == null) {
			
			currentPage = 1;
		} else {

			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		
		if (roll == null) {
			response.sendRedirect("/views/commons/error.jsp");
			return;
		}
		// 모든 회원의 정보를 가져오는 비즈니스 로직 처리
		AdminMemberService adService = new AdminMemberServiceImpl();
		HashMap<String, Object> pageDataMap = adService.BlackListUp(currentPage);

		// 가져온 회원정보를 가지고, jsp(view) 페이지로이동
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/blackListUp.jsp");

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

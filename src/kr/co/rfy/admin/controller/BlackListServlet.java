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
 * Servlet implementation class BlackListServlet
 */
@WebServlet("/admin/blackList.do")
public class BlackListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlackListServlet() {
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
			// 즉, index.jsp에서 해당 게시판으로 이동하는 경우에는 가장 첫페이지만 1page로 셋팅
			currentPage = 1;
		} else {

			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		// 검증 절차후
		if (roll == null) {
			response.sendRedirect("/views/commons/error.jsp");
			return;
		}
		// 모든 회원의 정보를 가져오는 비즈니스 로직 처리
		AdminMemberService adService = new AdminMemberServiceImpl();
		HashMap<String, Object> pageDataMap = adService.BlackList(currentPage);

		// 가져온 회원정보를 가지고, jsp(view) 페이지로이동
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/blackList.jsp");

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

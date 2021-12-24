package kr.co.rfy.admin.controller;

import java.io.IOException;

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
 * Servlet implementation class memberEndYnChangeBlackServlet
 */
@WebServlet("/admin/membereEndYNChangeBlack.do")
public class memberEndYnChangeBlackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberEndYnChangeBlackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roll = MemberAuthorityCheck.authorityRootCheck(request, response);
		
		if(roll==null)
		{
			response.sendRedirect("/views/commons/error.jsp");
			return;
		}
		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		char blackYN = request.getParameter("blackYN").charAt(0);
		
		if(blackYN=='Y') blackYN='N';
		else			blackYN='Y';
		AdminMemberService adService = new AdminMemberServiceImpl();
		int result = adService.updateMemberBlackYN(userNo,blackYN);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/updateMemberBlackYNBlack.jsp");
		if(result>0) request.setAttribute("result", true);
		else		request.setAttribute("result", false);
		
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

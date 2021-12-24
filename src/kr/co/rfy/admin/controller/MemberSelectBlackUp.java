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
import kr.co.rfy.member.model.vo.Member;

/**
 * Servlet implementation class MemberSelectBlackUp
 */
@WebServlet("/admin/memberSelectBlackUp.do")
public class MemberSelectBlackUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSelectBlackUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		AdminMemberService adService = new AdminMemberServiceImpl();
		Member m = adService.selectOneUp(userNo);
		
		
		
		//가져온 데이터를 view 페이지로 전달
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/memberSelectBlackUp.jsp");
		
		request.setAttribute("m", m);
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

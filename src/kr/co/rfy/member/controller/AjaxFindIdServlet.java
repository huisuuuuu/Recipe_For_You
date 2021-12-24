package kr.co.rfy.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.member.service.MemberService;
import kr.co.rfy.member.service.MemberServiceImpl;

/**
 * Servlet implementation class AjaxFindIdServlet
 */
@WebServlet("/member/AjaxFindId.do")
public class AjaxFindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxFindIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ajax로부터 이름과 이메일을 입력받아 그에 해당하는 아이디를 ajax로 돌려주는 서블릿
		
		request.setCharacterEncoding("UTF-8");
		// 입력받은 값 받기1
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		
		// 비지니스로직 처리
		MemberService mService = new MemberServiceImpl();
		String userId = mService.memberFindId(userName, userEmail);
		
		// 이름과 이메일로 조회된 Id가 없으면
		if(userId.isEmpty()) userId = ""; 

		// ajax로 결과 전송
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
			
		out.println(userId);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

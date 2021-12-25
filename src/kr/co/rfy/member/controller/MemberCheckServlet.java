package kr.co.rfy.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberCheckServlet
 */
@WebServlet("/member/memberCheck.do")
public class MemberCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//간단하게 회원 로그인 한 사용자인지 검증하고, 문제 없으면 비밀번호를 입력하는 페이지로 이동
				HttpSession session = request.getSession();
				
				RequestDispatcher view;
				
				if(session.getAttribute("member")!=null) //회원정보를 꺼내보았을때 있다면! -> 로그인한 사용자라면
				{
					view = request.getRequestDispatcher("/views/member/myPagePasswordCheck.jsp");
					
				}else // 회원정보를 꺼내보았을때 없다면! -> 비로그인한 사용자라면
				{
					view = request.getRequestDispatcher("/views/commons/error.jsp");
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

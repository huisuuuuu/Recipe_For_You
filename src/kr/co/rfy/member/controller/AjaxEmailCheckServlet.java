package kr.co.rfy.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.member.model.service.MemberService;
import kr.co.rfy.member.model.service.MemberServiceImpl;

/**
 * Servlet implementation class AjaxEmailCheckServlet
 */
@WebServlet("/member/AjaxEmailCheck.do")
public class AjaxEmailCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxEmailCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ajax로부터 사용자가 입력한 이메일 주소가 중복된 이메일인지 확인하고 그 결과를 ajax로 돌려주는 서블릿
		
		// 입력받은 이메일주소
		String userEmail = request.getParameter("userEmail");
		
		// 이메일 중복 검사
		MemberService mService = new MemberServiceImpl();
		String email = mService.emailCheck(userEmail);
		System.out.println(email);
		String result = "";
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		// email이 비어있다면 사용가능한 이메일
		if(email.equals("")) {
			MemberMailCheck mCheck = new MemberMailCheck();
			result = mCheck.emailSend(userEmail);
		}else {
			result = "fail";
		}
		out.println(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

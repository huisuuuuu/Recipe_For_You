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
 * Servlet implementation class AjaxFindPwdServlet
 */
@WebServlet("/member/AjaxFindPwd.do")
public class AjaxFindPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxFindPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ajax로부터 사용자가 입력한 이메일 주소가 중복된 이메일인지 확인하고 그 결과를 ajax로 돌려주는 서블릿
		
		request.setCharacterEncoding("UTF-8");
		// 입력한 아이디와 이메일 받기
		String userId = request.getParameter("userId");
		String userEmail = request.getParameter("userEmail");
		
		// 임시비밀번호 생성
		MemberPwdEmailSend mSend = new MemberPwdEmailSend();
		String tmpPwd = mSend.passwordMaker();
		
		// 비지니스 로직처리 - userId와 userEmail로 조회된 회원이 있을 경우 비밀번호를 발급받은 임시비밀번호로 변경 
		MemberService mService = new MemberServiceImpl();
		int result = mService.memberFindPwd(userId, userEmail, tmpPwd);
		System.out.println(result);
		System.out.println(userId);
		System.out.println(userEmail);
		// 임시비밀번호로 변경 성공시 입력받은 이메일로 임시비밀번호 발송 
		String ajaxResult = "";
		if(result>0) {
			ajaxResult = mSend.pwdSend(userEmail, tmpPwd);
		}
		
		// 결과값 ajax로 전달
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println(ajaxResult);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

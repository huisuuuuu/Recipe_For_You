package kr.co.rfy.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.member.model.service.MemberService;
import kr.co.rfy.member.model.service.MemberServiceImpl;
import kr.co.rfy.member.model.vo.Member;

/**
 * Servlet implementation class MemberJoinServlet
 */
@WebServlet("/member/memberJoin.do")
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입시 입력받은 정보를 데이터 유효성검증을 하고 DB에 정보를 저장하는 서블릿
		
		// 로그인한 회원인지 확인
		if(request.getSession().getAttribute("member")!=null) {
			response.sendRedirect("/views/common/error.jsp");
		}else {		
			// 입력받은 회원정보 가져오기
			request.setCharacterEncoding("UTF-8");
			String userId = request.getParameter("userId");
			String userPwd = request.getParameter("userPwd");
			String userName = request.getParameter("userName");
			String userEmail = request.getParameter("userEmail");
			String userPhone = request.getParameter("userPhone");
			char userConsent = request.getParameter("userConsent").charAt(0);
			
			// 입력받은 정보 Member 객체화 
			Member m = new Member(userId, userPwd, userName, userEmail, userPhone, userConsent);
			
			// 유효성 검증
			MemberJoinDataCheck mDataCheck = new MemberJoinDataCheck();
			
			RequestDispatcher view = request.getRequestDispatcher("/views/member/memberJoinResult.jsp");
			
			// 유효성 검증에 따른 비지니스로직처리
			if(mDataCheck.regExpJoin(m)) {
				MemberService mService = new MemberServiceImpl();
				int result = mService.memberJoin(m);
				
				if(result>0) request.setAttribute("result", true);
				else request.setAttribute("result", false);
				
				view.forward(request, response);
				
			}else {
				request.setAttribute("result", false);
				view.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package kr.co.rfy.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.rfy.member.model.service.MemberService;
import kr.co.rfy.member.model.service.MemberServiceImpl;
import kr.co.rfy.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/memberLogin.do")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력받은 아이디와 비밀번호에 해당하는 회원이 있으면 회원정보를 가져오고 세션을 생성하는 서블릿
		
		request.setCharacterEncoding("UTF-8");
		
		// 로그인한 회원인지 확인
		if(request.getSession().getAttribute("member")!=null) response.sendRedirect("/views/commons/error.jsp");
		
		// 로그인 데이터 가져오기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 유효성 검증
		MemberJoinDataCheck mDataCheck = new MemberJoinDataCheck();

		if(mDataCheck.regExId(userId) && mDataCheck.regExPwd(userPwd)) {
			// 비지니스 로직 처리
			MemberService mService = new MemberServiceImpl();
			Member m = mService.memberLogin(userId, userPwd);

			if(m!=null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("member", m);
				response.sendRedirect("/");
			}
		}else {
			// 유효성 검증에 맞지 않거나 비지니스로직 처리에서 입력한 정보에 해당하는 회원이 없을 경우
			RequestDispatcher view = request.getRequestDispatcher("/views/member/memberMsg.jsp");
			String msg = "아이디와 비밀번호를 확인해주세요";
			request.setAttribute("addr", "/views/member/memberLogin.jsp");
			request.setAttribute("msg", msg);
			view.forward(request, response);
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

package kr.co.rfy.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

<<<<<<< HEAD
import kr.co.rfy.member.model.service.MemberService;
import kr.co.rfy.member.model.service.MemberServiceImpl;
import kr.co.rfy.member.model.vo.Member;
=======
import kr.co.rfy.member.service.MemberService;
import kr.co.rfy.member.service.MemberServiceImpl;
import kr.co.rfy.member.vo.Member;
>>>>>>> d5939b1f99d94c7a744a9b52d10efd48952b58be

/**
 * Servlet implementation class MemberLoginServlet
 */
<<<<<<< HEAD
@WebServlet("/member/login.do")
=======
@WebServlet("/member/memberLogin.do")
>>>>>>> d5939b1f99d94c7a744a9b52d10efd48952b58be
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
<<<<<<< HEAD
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		
		//비즈니스 로직 처리
		MemberService mService = new MemberServiceImpl();
		Member m = mService.selectOneMember(userId, userPwd);
		
		// 리턴값은 Member 객체 이거나 혹은 null
		
		if(m != null) // 로그인이 성공 했다면
		{
			
			HttpSession session = request.getSession();
			session.setAttribute("member", m);
			
			
			//로그인 성공시 페이지 이동
			response.sendRedirect("/");
			//로그인을 성공하게 되면, sendRedirect를 통하여 메인페이지로 이동 시켜주어라
			//이때, sendRedirect는 사용자의 URL을 변경시켜주는 response 객체의 메소드
			
			
		}else //로그인이 실패 했다면
		{
			
			// RequestDispatcher를 이용하면 URL을 변경하지 않고, 이동 할 수 있다.
			// 이때, pageContext.forward처럼 request와 response 객체를 가지고 이동할 수 있다.
			
			RequestDispatcher view = request.getRequestDispatcher("/views/member/memberLoginFail.jsp");
		
			view.forward(request, response);
		}
	
	
=======
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
>>>>>>> d5939b1f99d94c7a744a9b52d10efd48952b58be
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package kr.co.rfy.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.member.vo.Member;

/**
 * Servlet implementation class MemberIdPwCheckServlet
 */
@WebServlet("/member/memberIdPwCheck.do")
public class MemberIdPwCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberIdPwCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비밀번호 변경/개인정보 수정 전 아이디와 비밀번호를 확인하고 비밀번호 변경 페이지 또는 개인정보 수정페에지로 이동시키는 서블릿
		
		
		request.setCharacterEncoding("UTF-8");
		
		//로그인한 회원인지 확인
		if(request.getSession().getAttribute("member")==null) response.sendRedirect("/views/commons/error.jsp");
		
		// 데이터 가져오기
		String inputUserId = request.getParameter("userId");
		String inputUserPwd = request.getParameter("userPwd");
		String type = request.getParameter("type");
		String sendAddr =  "";
		
		// 세션에서 member 객체 가져오기
		Member m = (Member)request.getSession().getAttribute("member");
		
		//updateCheck.jsp에서 왔다면 memberUpdate.jsp로 PwdChangeCheck.jsp에서 왔다면 memberPwdChenge.jsp로 이동할 주소 설정
		if(type.equals("update")) {
			sendAddr="/views/member/memberUpdate.jsp";
		}else if(type.equals("change")) {
			sendAddr="/views/member/memberPwdChange.jsp";
		}else {
			response.sendRedirect("/views/commons/error.jsp");
		}
		
		String userId = m.getUserId();
		String userPwd = m.getUserPwd();
		
		// parameter로 받아온 아이디와 비밀번호를 로그인 정보와 비교하여 맞으면 이동하고 다르면 fail 페이지로 이동
		if(inputUserId.equals(userId) && inputUserPwd.equals(userPwd) ) {
			response.sendRedirect(sendAddr);
		}else {
			// 페이지 이동 및 메세지 세팅			
			String addr="";
			if(type.equals("update")) addr = "/views/member/memberUpdateCheck.jsp";
			else addr = "/views/member/memberUpdateCheck.jsp";
			String msg = "아이디와 비밀번호를 확인해주세요";
			
			RequestDispatcher view = request.getRequestDispatcher("/views/member/memberMsg.jsp");
			request.setAttribute("addr", addr);
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

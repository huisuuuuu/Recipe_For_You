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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/member/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정하는 정보를 가져오고 유효성 검사후 비지니스로직 처리로 DB에 정보를 업데이트하는 서블릿
		
		request.setCharacterEncoding("UTF-8");
		
		// 로그인한 회원인지 확인
		if(request.getSession().getAttribute("member")==null) response.sendRedirect("/views/commons/error.jsp");
		
		// 업데이트할 데이터 가져오기
		String userEmail = request.getParameter("userEmail");
		String userPhone = request.getParameter("userPhone");
		
		// 성공여부 알림을 위해  member/memberMsg.jsp로 페이지 이동 및 메세지 변수 생성
		RequestDispatcher view = request.getRequestDispatcher("/views/member/memberMsg.jsp");
		String addr = "/";
		String msg = "";
		// 유효성 검증
		MemberJoinDataCheck mDataCheck = new MemberJoinDataCheck();		
		if(mDataCheck.regExEmail(userEmail) && mDataCheck.regExPhone(userPhone)) {
			
			// 세션에서 id 가쟈오기
			String userId = ((Member)request.getSession().getAttribute("member")).getUserId();
			
			// 비지니스 로직처리
			MemberService mService = new MemberServiceImpl();
			int result = mService.memberUpdate(userEmail, userPhone, userId);
			
			// 처리결과에 따라 메세지 설정
			if(result>0) {
				msg = "개인정보가 수정되었습니다.";
				
			}else {
				msg = "개인정보수정 실패하였습니다. "+"<br>"+"- 지속적인 문제발생시 관리자에게 문의해주세요 -";
			}
			
		}else {
			addr = "/views/member/memberUpdate.jsp";
			msg = "입력하신 정보를 확인해주세요";
		}
		
		request.setAttribute("addr", addr);
		request.setAttribute("msg", msg);
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

package kr.co.rfy.commons;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.rfy.member.vo.Member;




public class MemberAuthorityCheck {
	public static String authorityCheck(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		HttpSession session = req.getSession(false);
		Object obj = session.getAttribute("member");//로그인 정보 갖고오기
		
		//불법접근 막아버리기
		if(obj == null) {
			return null;
		}
		//권한 가져와야해서 member객체 갖고옴
		Member m = (Member)obj;
		
		if(!m.getRoll().substring(0, 2).equals("AD")) {//관리자가 아닐때
			return null;
		}else {//관리자 맞을때
			return m.getRoll();
		}
	}
	
	public static String authorityRootCheck(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
		HttpSession session = req.getSession(false);
		Object obj = session.getAttribute("member");//로그인 정보 갖고오기
		
		//불법접근 막아버리기
		if(obj == null) {
			return null;
		}
		//권한 가져와야해서 member객체 갖고옴
		Member m = (Member)obj;
		
		if(!m.getRoll().substring(0, 2).equals("AD")) {//관리자가 아닐때
			return null;
		}else if(! m.getRoll().substring(3, 4).equals("0")){//최고관리자 아니면
			return null;
		}else {//관리자 맞을때
			return m.getRoll();
		}
	}
}

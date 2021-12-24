package kr.co.rfy.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.rfy.member.model.vo.Member;

public class MemberAuthorityCheck {
	public static String authorityCheck(HttpServletRequest req, HttpServletResponse res)
	         throws ServletException, IOException
	   {
	      HttpSession session = req.getSession(false);
	      
	      Object obj = session.getAttribute("member");

	      //로그인을 하지 않은 사용자라면
	      if(obj==null){   return null; }
	      
	      Member m = (Member)obj;
	      
	      if(!m.getRoll().substring(0,2).equals("AD")) // 관리자가 아니라면
	      {
	         return null;
	      }
	      else // 관리자라면
	      {
	         return m.getRoll();
	      }
	      
	      
	      
	   }
	   
	   public static String authorityRootCheck(HttpServletRequest req, HttpServletResponse res)
	         throws ServletException, IOException
	   {
	      HttpSession session = req.getSession(false);
	      
	      Object obj = session.getAttribute("member");
	      
	      //로그인을 하지 않은 사용자라면
	      if(obj==null){   return null; }
	      
	      Member m = (Member)obj;
	      
	      if(!m.getRoll().substring(0,2).equals("AD")) // 관리자가 아니라면
	      {
	         return null;
	      }
	    
	      else // 관리자라면
	      {
	         return m.getRoll();
	      }
	      
	      
	      
	   }

	}
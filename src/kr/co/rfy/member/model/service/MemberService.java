package kr.co.rfy.member.model.service;

import java.util.HashMap;

import kr.co.rfy.member.model.vo.Member;

public interface MemberService {

	public Member selectOneMember(String userId, String userPwd);

	
}

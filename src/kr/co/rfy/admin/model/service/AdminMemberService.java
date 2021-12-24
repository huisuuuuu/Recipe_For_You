package kr.co.rfy.admin.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.rfy.member.model.vo.Member;

public interface AdminMemberService {

	HashMap<String, Object> selectAllMemberList(int currentPage);

	
	 HashMap<String, Object> SearchMember(int currentPage, String keyword, String type);


	HashMap<String, Object> BlackList(int currentPage);


	HashMap<String, Object> SearchMemberBlack(int currentPage, String keyword, String type);


	Member selectOne(int userNo);



	int updateMemberBlackYN(int userNo, char blackYN);


	HashMap<String, Object> selectAllMemberListUp(int currentPage);


	HashMap<String, Object> SearchMemberUp(int currentPage, String keyword, String type);


	Member selectOneUp(int userNo);


	int updateMemberBlackYNUp(int userNo, char blackYN);


	HashMap<String, Object> BlackListUp(int currentPage);


	HashMap<String, Object> SearchMemberBlackUp(int currentPage, String keyword, String type);




}

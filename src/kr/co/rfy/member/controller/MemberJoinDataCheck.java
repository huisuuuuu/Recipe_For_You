package kr.co.rfy.member.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.co.rfy.member.vo.Member;

public class MemberJoinDataCheck {
	
	/**
	 * @param Member 
	 * @return boolean
	 * Description : 회원가입시 입력받은 데이터를 정규식 조회하여 전부 맞을 경우 true 리턴 
	 */
	public boolean regExpJoin(Member m) {
		boolean result = false;
		if(regExId(m.getUserId()) && regExPwd(m.getUserPwd()) && regExName(m.getUserName()) && regExEmail(m.getUserEmail()) && regExPhone(m.getUserPhone())) result =true; 
		return result;
	}
	
	/**
	 * @param String 
	 * @return boolean
	 * Description : 회원 아이디 정규식 조회 (아이디는 영문자+숫자 조합으로 6~12글자)
	 */
	public boolean regExId(String userId) {
		boolean result = userId.matches("\\w{6,12}"); //영문자+숫자 6~12글자 확인 
		return result;
	}
	
	/**
	 * @param String 
	 * @return boolean
	 * Description : 회원 비밀번호 정규식 조회 (비밀번호는 영문자+숫자+특수문자 조합으로 8글자 이상)
	 */
	public boolean regExPwd(String userPwd) {
		boolean result = false;
		
		// 영문자 확인
		Pattern patternA = Pattern.compile("[a-z]");
		Matcher matcherA = patternA.matcher(userPwd);
		// 숫자 확인
		Pattern patternN = Pattern.compile("[0-9]");
		Matcher matcherN = patternA.matcher(userPwd);
		// 특수문자 확인
		Pattern patternS = Pattern.compile("[~!@#$%^&*()-_=+<>/?]");
		Matcher matcherS = patternA.matcher(userPwd);
		// 공백문자 확인
		Pattern patternB = Pattern.compile("\\s");
		Matcher matcherB = patternA.matcher(userPwd);

		// 비밀번호 글자수 검사
		if(userPwd.length()>7) result = false;
		
		// 영문자 확인
		if(matcherA.find()) result = true;
		// 숫자 확인
		if(matcherN.find()) result = true;
		// 특수문자 확인
		if(matcherS.find()) result = true;
		// 공백문자 확인
		if(!matcherB.find()) result = true;
		
		return result;
	}
	
	/**
	 * @param String 
	 * @return boolean
	 * Description : 회원 이름 정규식 조회 (이름은 한글만 입력가능하고 2~10글자) 
	 */
	public boolean regExName(String userName) {
		boolean result = userName.matches("^[가-힣]{2,10}"); // 한글 2~10글자
		return result;
	}
	
	/**
	 * @param String 
	 * @return boolean
	 * Description : 회원 이메일 정규식 조회 (이메일 형식 영문자+숫자 @ 영문자 로 조회)
	 */
	public boolean regExEmail(String userEmail) {
		boolean result = userEmail.matches("\\w+@[a-z]+.[a-z]+"); // 이메일 (영문자+숫자) @ 영문자  
		return result;
	}
	
	/**
	 * @param String 
	 * @return boolean
	 * Description : 회원 전화번호 정규식 조회 (전화번호는 숫자만 입력가능하고 10~11글자)
	 */
	public boolean regExPhone(String userPhone) {
		boolean result = userPhone.matches("^[0-9]{10,11}"); // 숫자 10~11글자
		return result;
	}
}

package kr.co.rfy.member.controller;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MemberPwdEmailSend {

	public String pwdSend(String userEmail, String tmpPwd) {
		
		// mail server 설정
		String host = "smtp.gmail.com";
		String admin = "recipe.for.you.email@gmail.com"; // 관리자 메일
		String password = "qqqq1111!";// 관리자 메일 패스워드
		
		// 메일 받을 주소
		String to_email = userEmail;
		System.out.println("userEmail : " + userEmail);

		// SMTP 서버 정보를 설정한다.
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", host);
		//google - TLS : 587, SSL: 465
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.debug", "true");
        
		
		Authenticator auth = new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(admin, password);
			}
		};
		
		// 메일을 전송하기 위한 세션생성
		Session mailSession = Session.getDefaultInstance(prop, auth);

		// 메일관련 내용 생성을 위해 MimeMessage 생성
		MimeMessage msg = new MimeMessage(mailSession);
		
		// email 전송
		try {
			// 보내는 사람 / 받은 사람 설정
			msg.setFrom(new InternetAddress(admin)); // 보내는 사람
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email)); // 받는 사람

			// 메일 제목
			msg.setSubject("냉장고를 부탁해 임시 비밀번호입니다.", "UTF-8");
			
			// 메일 내용 작성
			String mail = "냉장고를 부탁해 이메일 <strong>임시비밀번호</strong>입니다. <br>"
					+ "로그인 후 비밀번호를 변경해주세요. <br>"
					+ "임시비밀번호 : <strong>"+ tmpPwd + "</strong> <br><br>"
					+ "마이냉장고에 나만의 식재료를 등록하시오 레시피를 추천받아보세요!! " ;
			
			// 메일 내용 
			//msg.setText();
			msg.setContent(mail, "text/html; charset=utf-8");

			// 메일 전송	
			Transport.send(msg);
			// 확인
			System.out.println("이메일 전송 : " + tmpPwd);
			
		} catch (AddressException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} catch (MessagingException e) { 
				// TODO Auto-generated catch block 
				e.printStackTrace(); 
		}
		return tmpPwd;
	}
	
		// 인증 번호 생성기
		public String passwordMaker() {
			String tmpPwd = null;
			
			// 인증번호를 담을 버퍼 생성
			StringBuffer temp = new StringBuffer();
			// 랜덤번호 생성을 위한 랜덤객체 생성
			
			Random r = new Random();
			// 6자리 인증번호를 만들기 위해 for문 사용
			for (int i = 0; i < 10; i++) {
				// rIndex 0~2까지 숫자 생성
				int rIndex = r.nextInt(5);
				switch (rIndex) {
				case 0:
					// a-z 
					temp.append((char) ((int) (r.nextInt(26)) + 97));
					break;
				case 1:
					// a-z
					temp.append((char) ((int) (r.nextInt(26)) + 97));
					break;
				case 2:
					// 0-9
					temp.append((r.nextInt(10)));
					break;
				case 3:
					// 0-9
					temp.append((r.nextInt(10)));
					break;
				case 4:
					// 특수문자
					temp.append((char) ((int) (r.nextInt(4)) + 33));
					break;
				}
			}
			tmpPwd = temp.toString();
			System.out.println(tmpPwd);
			
			return tmpPwd;	
	}
}


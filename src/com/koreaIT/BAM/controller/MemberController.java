package com.koreaIT.BAM.controller;

import java.util.Scanner;

import com.koreaIT.BAM.container.Container;
import com.koreaIT.BAM.dto.Member;
import com.koreaIT.BAM.service.MemberService;

public class MemberController extends Controller{
	
	private MemberService memberService;
	
	public MemberController(Scanner sc) {
		this.sc = sc;
		loginedMember = null;
		memberService = Container.memberService;
	}
	
	@Override
	public void doAction(String cmd, String methodName) {
		switch(methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
		}
	}
	
	public void doJoin() {
		String loginId = null;
		String loginPw = null;
		String name = null;
		
		while(true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();
			
			if (loginId.isEmpty()) {
				System.out.println("아이디는 필수 입력 정보입니다.");
				continue;
			}
			
			if (memberService.loginIdDupChk(loginId)) {
				System.out.printf("[%s] 은(는)이미 사용중인 아이디 입니다.\n", loginId);
				continue;
			}
			
			System.out.printf("[%s] 은(는) 사용가능한 아이디 입니다.\n", loginId);
			
			break;
		}
		
		while(true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine().trim();
			
			if (loginPw.isEmpty()) {
				System.out.println("비밀번호를 입력해주세요.");
				continue;
			}
			
			System.out.printf("비밀번호 확인: ");
			String loginPwChk = sc.nextLine().trim();
			
			if(!loginPw.equals(loginPwChk)) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			
			break;
		}
		
		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();
			
			if (name.isEmpty()) {
				System.out.println("이름은 필수 입력 정보입니다.");
				continue;
			}
			
			break;
		}
		
		memberService.joinMember(loginId, loginPw, name);
		System.out.printf("%s 회원님의 가입이 완료되었습니다.\n", name);
	}
	
	public void doLogin() {
		while(true) {
			System.out.printf("아이디 : ");
			String loginId = sc.nextLine().trim();
			if (loginId.isEmpty()) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}
			
			System.out.printf("비밀번호 : ");
			String loginPw = sc.nextLine().trim();
			if (loginPw.isEmpty()) {
				System.out.println("비밀번호를 입력해주세요.");
				continue;
			}
			
			Member foundMember = memberService.getMemberByLoginId(loginId);
			
			if (foundMember == null) {
				System.out.printf("[%s]은(는) 존재하지 않는 아이디 입니다.\n", loginId);
				continue;
			}
			
			if (!foundMember.getLoginPw().equals(loginPw)) {
				System.out.println("비밀번호를 확인해주세요.");
				continue;
			}
			
			loginedMember = foundMember;
			System.out.printf("%s 회원님 로그인 성공!.\n", loginedMember.getName());
			break;
		}
		
	}
	
	public void doLogout() {
		System.out.printf("%s 회원님 로그아웃 성공!.\n", loginedMember.getName());
		loginedMember = null;
	}
	
	@Override
	public void makeTestData() {
		for (int i = 1; i <= 3; i++) {
			memberService.joinMember("user" + i, "user" + i, "유저" + i);
		}
		
		System.out.println("테스트 회원 데이터를 3개 생성했습니다.");
	}

}

package com.koreaIT.BAM;

import java.util.Scanner;

import com.koreaIT.BAM.controller.ArticleController;
import com.koreaIT.BAM.controller.Controller;
import com.koreaIT.BAM.controller.MemberController;
import com.koreaIT.BAM.dto.Member;

public class App {
	
	App() {
	}
	
	public void run() {
			
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		ArticleController articleController = new ArticleController(sc);
		MemberController memberController = new MemberController(sc);
		
		articleController.makeTestData();
		memberController.makeTestData();
		
		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			if (cmd.equals("exit")) {
				break;
			}
			
			String[] cmdBits = cmd.split(" ");
			
			if (cmdBits.length < 2) {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}
			
			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];
			
			Controller controller = null;
			
			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}
			
			controller.doAction(cmd, methodName);
			articleController.loginedMember = memberController.loginedMember;
			
			try {
				System.out.println(controller.loginedMember.getLoginId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				System.out.println(memberController.loginedMember.getLoginId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		sc.close();
		
		System.out.println("== 프로그램 끝 ==");
	}
	
}

package com.koreaIT.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.dto.Member;
import com.koreaIT.BAM.util.Util;

public class App {
	
	List<Article> articleList;
	List<Member> memberList;
	int lastArticleId;
	int lastMemberId;
	
	App() {
		articleList = new ArrayList<>();
		memberList = new ArrayList<>();
		lastArticleId = 0;
		lastMemberId = 0;
	}
	
	public void run() {
			
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		makeTestArticleData();
		makeTestMemberData();
		
		while (true) {
			
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			if (cmd.equals("member join")) {
				
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
					
					if (loginIdDupChk(loginId)) {
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
				
				memberList.add(new Member(++lastMemberId, Util.getDateStr(), loginId, loginPw, name));
				System.out.printf("%s 회원님의 가입이 완료되었습니다.\n", name);
				
			} else if (cmd.equals("article write")) {
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				articleList.add(new Article(0, ++lastArticleId, Util.getDateStr(), title, body));
				
				System.out.printf("%d번 글이 생성되었습니다.\n", lastArticleId);
				
			} else if (cmd.startsWith("article list")) {
				
				if (articleList.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다.");
					continue;
				}
				 
				List<Article> printArticle = articleList;
				
				String searchKeyword = cmd.substring("article list".length()).trim();
				
				if (searchKeyword.length() > 0) {
					printArticle = new ArrayList<>();
					
					for(Article article : articleList) {
						if(article.getTitle().contains(searchKeyword)) {
							printArticle.add(article);
						}
					}
				}
				
				if (printArticle.size() == 0) {
					System.out.println("검색결과가 없습니다.");
					continue;
				}
					
				System.out.println("조회수	|	번호	|	제목	|	날짜");
				
				for (int i = printArticle.size() - 1; i >= 0; i--) {
					Article article = printArticle.get(i);
					System.out.printf("%d	|	%d	|	%s	|	%s\n" , article.getViewCnt(), article.getId(), article.getTitle(), article.getRegDate());
				}
				
			} else if (cmd.startsWith("article detail ")) {
				
				int id = getCmdNum(cmd);
				
				if (id == 0) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
				}
				
				Article foundArticle = getArticleById(id);
				
				if (foundArticle == null) {
					System.out.println(id + "번 게시물이 존재하지 않습니다.");
					continue;
				}
				
				foundArticle.increaseViewCnt();
				System.out.println("조회수 : " + foundArticle.getViewCnt());
				System.out.println("번호 : " + foundArticle.getId());
				System.out.println("날짜 : " + foundArticle.getRegDate());
				System.out.println("제목 : " + foundArticle.getTitle());
				System.out.println("내용 : " + foundArticle.getBody());
				
			} else if (cmd.startsWith("article modify ")) {
				
				int id = getCmdNum(cmd);
				
				Article foundArticle = null;
				
				for (Article article : articleList) {
					if (article.getId() == id) {
						foundArticle = article;
						break;
					}
				}
				
				if (foundArticle == null) {
					System.out.println(id + "번 게시물이 존재하지 않습니다.");
					continue;
				}
				
				System.out.printf("수정 할 제목 : ");
				String title = sc.nextLine();
				
				System.out.printf("수정 할 내용 : ");
				String body = sc.nextLine();
				
				foundArticle.setTitle(title);
				foundArticle.setBody(body);
				
				System.out.println(foundArticle.getId() + "번 게시물이 수정되었습니다.");
				
			} else if (cmd.startsWith("article delete ")) {
				
				int id = getCmdNum(cmd);
				
				Article foundArticle = null;
				
				for (Article article : articleList) {
					if (article.getId() == id) {
						foundArticle = article;
						break;
					}
				}
				
				if (foundArticle == null) {
					System.out.println(id + "번 게시물이 존재하지 않습니다.");
					continue;
				}
				
				articleList.remove(foundArticle);
				System.out.println(foundArticle.getId() + "번 게시물이 삭제되었습니다.");
				
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}
			
			if (cmd.equals("exit")) {
				break;
			}
				
		} 
		
		sc.close();
		
		System.out.println("== 프로그램 끝 ==");

	}
	
	private boolean loginIdDupChk(String loginId) {
		for (Member member : memberList) {
			if (member.getLoginId().equals(loginId)) {
				return true;
			}
		}
		return false;
	}

	private Article getArticleById(int id) {
		
		for (Article article : articleList) {
			if (article.getId() == id) {
				return article;
			}
		}
		
		return null;
	}

	private int getCmdNum(String cmd) {
		
		try {
			int id = Integer.parseInt(cmd.split(" ")[2]);
			return id;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private void makeTestArticleData() {
		
		for (int i = 1; i <= 5; i++) {
			articleList.add(new Article(i*10, ++lastArticleId, Util.getDateStr(), "제목" + i, "내용" + i));
		}
		
		System.out.println("테스트 게시글 데이터를 5개 생성했습니다.");
		
	}
	
	private void makeTestMemberData() {
		for (int i = 1; i <= 3; i++) {
			memberList.add(new Member(++lastMemberId, Util.getDateStr(), "user" + i, "user" + i, "유저" + i));
		}
		
		System.out.println("테스트 회원 데이터를 3개 생성했습니다.");
		
	}
}

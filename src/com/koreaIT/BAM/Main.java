package com.koreaIT.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.util.Util;

public class Main {
	
	static List<Article> articleList;
	static int lastArticleId;
	
	static {
		articleList = new ArrayList<>();
		lastArticleId = 0;
	}
	
	public static void main(String[] args) {
		
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		makeTestData();
		
		while (true) {
			
			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			
			if (cmd.equals("article write")) {
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				articleList.add(new Article(0, ++lastArticleId, Util.getDateStr(), title, body));
				
				System.out.printf("%d번 글이 생성되었습니다.\n", lastArticleId);
				
			} else if (cmd.equals("article list")) {
				
				if (articleList.size() == 0) {
					System.out.println("존재하는 게시글이 없습니다.");
					continue;
				}
				
				System.out.println("조회수	|	번호	|	제목	|	날짜");
				
				for (int i = articleList.size() - 1; i >= 0; i--) {
					Article article = articleList.get(i);
					System.out.printf("%d	|	%d	|	%s	|	%s\n" , article.getViewCnt(), article.getId(), article.getTitle(), article.getRegDate());
				}
				
			} else if (cmd.startsWith("article detail")) {
				
//				int id = Integer.parseInt(cmd.substring(15));
				
				int id = 0;
				
				try {
					id = Integer.parseInt(cmd.split(" ")[2]);
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
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
				
				foundArticle.increaseViewCnt();
				System.out.println("조회수 : " + foundArticle.getViewCnt());
				System.out.println("번호 : " + foundArticle.getId());
				System.out.println("날짜 : " + foundArticle.getRegDate());
				System.out.println("제목 : " + foundArticle.getTitle());
				System.out.println("내용 : " + foundArticle.getBody());
				
			} else if (cmd.startsWith("article modify")) {
				
				int id = 0;
				
				try {
					id = Integer.parseInt(cmd.split(" ")[2]);
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
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
				
			} else if (cmd.startsWith("article delete")) {
				
				int id = 0;
				
				try {
					id = Integer.parseInt(cmd.split(" ")[2]);
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
				} catch (Exception e) {
					e.printStackTrace();
				}
				
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
			}
			
			if (cmd.equals("exit")) {
				break;
			}
		} 
		
		sc.close();
		
		System.out.println("== 프로그램 끝 ==");
	}

	private static void makeTestData() {
		
		for (int i = 1; i <= 5; i++) {
			articleList.add(new Article(i*10, ++lastArticleId, Util.getDateStr(), "제목" + i, "내용" + i));
		}
		
		System.out.println("테스트 데이터를 5개 생성했습니다.");
		
	}
}


























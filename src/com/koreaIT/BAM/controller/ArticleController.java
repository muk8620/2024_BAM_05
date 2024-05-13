package com.koreaIT.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.util.Util;

public class ArticleController extends Controller{
	
	private List<Article> articleList;
	private int lastArticleId;
	
	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articleList = new ArrayList<>();
		this.lastArticleId = 0;
	}
	
	@Override
	public void doAction(String cmd, String methodName) {
		this.cmd = cmd;
		
		switch(methodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
		}
	}
	
	public void doWrite() {
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		
		articleList.add(new Article(0, ++lastArticleId, Util.getDateStr(), title, body));
		
		System.out.printf("%d번 글이 생성되었습니다.\n", lastArticleId);
		
	}

	public void showList() {
		if (articleList.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다.");
			return;
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
			return;
		}
			
		System.out.println("조회수	|	번호	|	제목	|	날짜");
		
		for (int i = printArticle.size() - 1; i >= 0; i--) {
			Article article = printArticle.get(i);
			System.out.printf("%d	|	%d	|	%s	|	%s\n" , article.getViewCnt(), article.getId(), article.getTitle(), article.getRegDate());
		}
	}
	
	public void showDetail() {
		int id = getCmdNum(cmd);
		
		if (id == 0) {
			System.out.println("명령어가 올바르지 않습니다.");
			return;
		}
		
		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			System.out.println(id + "번 게시물이 존재하지 않습니다.");
			return;
		}
		
		foundArticle.increaseViewCnt();
		System.out.println("조회수 : " + foundArticle.getViewCnt());
		System.out.println("번호 : " + foundArticle.getId());
		System.out.println("날짜 : " + foundArticle.getRegDate());
		System.out.println("제목 : " + foundArticle.getTitle());
		System.out.println("내용 : " + foundArticle.getBody());
	}
	
	public void doModify() {
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
			return;
		}
		
		System.out.printf("수정 할 제목 : ");
		String title = sc.nextLine();
		
		System.out.printf("수정 할 내용 : ");
		String body = sc.nextLine();
		
		foundArticle.setTitle(title);
		foundArticle.setBody(body);
		
		System.out.println(foundArticle.getId() + "번 게시물이 수정되었습니다.");
	}
	
	public void doDelete() {
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
			return;
		}
		
		articleList.remove(foundArticle);
		System.out.println(foundArticle.getId() + "번 게시물이 삭제되었습니다.");
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
		String[] cmdBits = cmd.split(" ");
		
		if (cmdBits.length > 3) {
			return 0;
		}
		
		try {
			int id = Integer.parseInt(cmdBits[2]);
			return id;
		} catch (NumberFormatException e) {
			return 0;
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	@Override
	public void makeTestData() {
		for (int i = 1; i <= 5; i++) {
			articleList.add(new Article(i*10, ++lastArticleId, Util.getDateStr(), "제목" + i, "내용" + i));
		}
		
		System.out.println("테스트 게시글 데이터를 5개 생성했습니다.");
	}
	
}

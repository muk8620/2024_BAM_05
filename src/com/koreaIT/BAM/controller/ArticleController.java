package com.koreaIT.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.container.Container;
import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.service.ArticleService;
import com.koreaIT.BAM.service.MemberService;

public class ArticleController extends Controller{
	
	private ArticleService articleService;
	private MemberService memberService;
	
	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articleService = Container.articleService;
		this.memberService = Container.memberService;
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
		
		int articleNumber = articleService.writeArticle(title, body, loginedMember.getId(), 0);
		
		System.out.printf("%d번 글이 생성되었습니다.\n", articleNumber);
		
	}

	public void showList() {
		String searchKeyword = cmd.substring("article list".length()).trim();
		
		List<Article> printArticle = articleService.getPrintArticles(searchKeyword);
		
		if (printArticle.size() == 0) {
			System.out.println("게시물이 없습니다.");
			return;
		}
		
		System.out.println("번호	|		날짜		|	제목	|	작성자	|	조회수");
		
		for (int i = printArticle.size() - 1; i >= 0; i--) {
			Article article = printArticle.get(i);
			String writerLoginId = memberService.getLoginIdByMemberId(article.getMemberId());
			System.out.printf("%d	|	%s	|	%s	|	%s	|	%d\n" ,  article.getId(), article.getRegDate(), article.getTitle(), writerLoginId, article.getViewCnt());
		}
	}
	
	public void showDetail() {
		int id = getCmdNum(cmd);
		
		if (id == 0) {
			System.out.println("명령어가 올바르지 않습니다.");
			return;
		}
		
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			System.out.println(id + "번 게시물이 존재하지 않습니다.");
			return;
		}
		String writerLoginId = memberService.getLoginIdByMemberId(foundArticle.getMemberId());
		
		articleService.increaseViewCnt(foundArticle.getId());
		System.out.println("번호 : " + foundArticle.getId());
		System.out.println("날짜 : " + foundArticle.getRegDate());
		System.out.println("제목 : " + foundArticle.getTitle());
		System.out.println("내용 : " + foundArticle.getBody());
		System.out.println("작성자 : " + writerLoginId);
		System.out.println("조회수 : " + foundArticle.getViewCnt());
	}
	
	public void doModify() {
		int id = getCmdNum(cmd);
		
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			System.out.println(id + "번 게시물이 존재하지 않습니다.");
			return;
		}
		
		if (foundArticle.getMemberId() != loginedMember.getId()) {
			System.out.println("해당 게시물에 대한 권한이 없습니다.");
			return;
		}
		
		System.out.printf("수정 할 제목 : ");
		String title = sc.nextLine();
		
		System.out.printf("수정 할 내용 : ");
		String body = sc.nextLine();
		
		articleService.modifyArticle(foundArticle, title, body);
		
		System.out.println(foundArticle.getId() + "번 게시물이 수정되었습니다.");
	}
	
	public void doDelete() {
		int id = getCmdNum(cmd);
		
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			System.out.println(id + "번 게시물이 존재하지 않습니다.");
			return;
		}
		
		if (foundArticle.getMemberId() != loginedMember.getId()) {
			System.out.println("해당 게시물에 대한 권한이 없습니다.");
			return;
		}
		
		articleService.deleteArticle(foundArticle);
		
		System.out.println(foundArticle.getId() + "번 게시물이 삭제되었습니다.");
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
			articleService.writeArticle("제목" + i, "내용" + i, (int) (Math.random() * 3) + 1, i * 10);
		}
		
		System.out.println("테스트 게시글 데이터를 5개 생성했습니다.");
	}
	
}

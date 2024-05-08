import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		int lastArticleId = 0;
		List<Article> articleList = new ArrayList<>();
		
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
				
				++lastArticleId;
				
				articleList.add(new Article(0, lastArticleId, Util.getDateStr(), title, body));
				
				System.out.printf("%d번 글이 생성되었습니다.\n", lastArticleId);
				
			} else if (cmd.equals("article list")) {
				
				if (articleList.size() == 0) {
					System.out.println("존재하는 게시글이 없습니다.");
					continue;
				}
				
				System.out.println("조회수	|	번호	|	제목	|	날짜");
				
				for (int i = articleList.size() - 1; i >= 0; i--) {
					Article article = articleList.get(i);
					System.out.printf("%d	|	%d	|	%s	|	%s\n" , article.viewCnt, article.id, article.title, article.regDate);
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
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
				if (foundArticle == null) {
					System.out.println(id + "번 게시물이 존재하지 않습니다.");
					continue;
				}
				
				foundArticle.increaseViewCnt();
				System.out.println("조회수 : " + foundArticle.viewCnt);
				System.out.println("번호 : " + foundArticle.id);
				System.out.println("날짜 : " + foundArticle.regDate);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);
				
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
					if (article.id == id) {
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
				
				foundArticle.title = title;
				foundArticle.body = body;
				
				System.out.println(foundArticle.id + "번 게시물이 수정되었습니다.");
				
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
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}
				
				if (foundArticle == null) {
					System.out.println(id + "번 게시물이 존재하지 않습니다.");
					continue;
				}
				
				articleList.remove(foundArticle);
				System.out.println(foundArticle.id + "번 게시물이 삭제되었습니다.");
				
				
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
}

class Article {
	
	int viewCnt;
	int id;
	String regDate;
	String title;
	String body;
	
	Article(int viewCnt, int id, String regDate, String title, String body) {
		this.viewCnt = viewCnt;
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
	}
	
	void increaseViewCnt() {
		this.viewCnt++;
	}
	
}
























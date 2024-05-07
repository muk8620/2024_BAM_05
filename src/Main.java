import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
				
//				Article article = new Article(id, title, body);
//				articleList.add(article);
				
				String formatedNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				
				articleList.add(new Article(lastArticleId, formatedNow, title, body));
				
				System.out.printf("%d번 글이 생성되었습니다.\n", lastArticleId);
				
			} else if (cmd.equals("article list")) {
				
				if (articleList.size() == 0) {
					System.out.println("존재하는 게시글이 없습니다.");
					continue;
				}
				
				System.out.println("번호	|	제목");
				
				for (int i = articleList.size() - 1; i >= 0; i--) {
					Article article = articleList.get(i);
					System.out.printf("%d	|	%s\n" , article.id, article.title);
				}
				
			} else if (cmd.startsWith("article detail")) {
				
//				int id = Integer.parseInt(cmd.substring(15));
				int id = Integer.parseInt(cmd.split(" ")[2]);
				
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
				
				System.out.println("번호 : " + foundArticle.id);
				System.out.println("날짜 : " + foundArticle.date);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);
				
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
	int id;
	String date;
	String title;
	String body;
	
	Article(int id, String date, String title, String body) {
		this.id = id;
		this.date = date;
		this.title = title;
		this.body = body;
	}
	
}
























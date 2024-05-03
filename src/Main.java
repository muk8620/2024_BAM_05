import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		int id = 0;
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
				
				++id;
				
//				Article article = new Article(id, title, body);
//				articleList.add(article);
				
				articleList.add(new Article(id, title, body));
				
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
				continue;
			}
			
			if (cmd.equals("article list")) {
				
				if (articleList.size() == 0) {
					System.out.println("존재하는 게시글이 없습니다.");
					continue;
				}
				
				System.out.println("번호	|	제목");
				
				for (int i = articleList.size() - 1; i >= 0; i--) {
					Article article = articleList.get(i);
					System.out.printf("%d	|	%s\n" , article.id, article.title);
				}
				continue;
			}
			
			if (cmd.equals("exit")) {
				break;
			}
			
			System.out.println("존재하지 않는 명령어 입니다.");
			
		} 
		
		sc.close();
		
		System.out.println("== 프로그램 끝 ==");
	}
}

class Article {
	int id;
	String title;
	String body;
	
	Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
	
}
























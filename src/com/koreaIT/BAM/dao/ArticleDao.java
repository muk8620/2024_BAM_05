package com.koreaIT.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.BAM.container.Container;
import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.dto.Member;
import com.koreaIT.BAM.util.Util;

public class ArticleDao {
	
	private List<Article> articles;
	private List<Member> members;
	private int lastId;
	
	public ArticleDao() {
		articles = Container.articles;
		members = Container.members;
		lastId = 0;
	}

	public int writeArticle(String title, String body, int id, int viewCnt) {
		articles.add(new Article(++lastId, Util.getDateStr(), title, body, id, viewCnt));
		return lastId;
	}
	
	public String getLoginIdByMemberId(int id) {
		for (Member member : members) {
			if (member.getId() == id) {
				return member.getLoginId();
			}
		}
		
		return null;
	}
	
	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		
		return null;
	}

	public void increaseViewCnt(int id) {
		Article article = getArticleById(id);
		article.setViewCnt(++id);
	}

	public void doModify(int id, String title, String body) {
		Article article = getArticleById(id);
		article.setTitle(title);
		article.setBody(body);
	}
	
	public void doDelete(Article foundArticle) {
		articles.remove(foundArticle);
	}

	public List<Article> showArticleList(String searchKeyword) {
		if (articles.size() == 0) {
			return null;
		}
		
		List<Article> printArticle = new ArrayList<>();
		
		if (searchKeyword.length() > 0) {
			for(Article article : articles) {
				if(article.getTitle().contains(searchKeyword)) {
					printArticle.add(article);
				}
			}
			return printArticle;
		}
		return articles;
	}


}

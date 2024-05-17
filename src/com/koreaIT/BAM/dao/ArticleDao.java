package com.koreaIT.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.util.Util;

public class ArticleDao {
	
	private List<Article> articles;
	private int lastId;
	
	public ArticleDao() {
		articles = new ArrayList<>();
		lastId = 0;
	}

	public int writeArticle(String title, String body, int id, int viewCnt) {
		articles.add(new Article(++lastId, Util.getDateStr(), title, body, id, viewCnt));
		return lastId;
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

	public void modifyArticle(Article article, String title, String body) {
		article.setTitle(title);
		article.setBody(body);
	}
	
	public void deleteArticle(Article foundArticle) {
		articles.remove(foundArticle);
	}

	public List<Article> getPrintArticles(String searchKeyword) {
		if (searchKeyword.length() > 0) {
			List<Article> printArticle = new ArrayList<>();
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

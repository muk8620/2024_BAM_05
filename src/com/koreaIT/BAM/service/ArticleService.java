package com.koreaIT.BAM.service;

import java.util.List;

import com.koreaIT.BAM.container.Container;
import com.koreaIT.BAM.dao.ArticleDao;
import com.koreaIT.BAM.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = Container.articleDao;
	}

	public int writeArticle(String title, String body, int id, int viewCnt) {
		return articleDao.writeArticle(title, body, id, viewCnt);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void increaseViewCnt(int id) {
		articleDao.increaseViewCnt(id);
	}

	public List<Article> getPrintArticles(String searchKeyword) {
		return articleDao.getPrintArticles(searchKeyword);
	}

	public void deleteArticle(Article foundArticle) {
		articleDao.deleteArticle(foundArticle);
	}

	public void modifyArticle(Article article, String title, String body) {
		articleDao.modifyArticle(article, title, body);
	}
	
}

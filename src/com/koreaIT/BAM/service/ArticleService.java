package com.koreaIT.BAM.service;

import java.util.List;

import com.koreaIT.BAM.dao.ArticleDao;
import com.koreaIT.BAM.dto.Article;

public class ArticleService {
	
	private ArticleDao articleDao;
	
	public ArticleService() {
		articleDao = new ArticleDao();
	}

	public int writeArticle(String title, String body, int id, int viewCnt) {
		return articleDao.writeArticle(title, body, id, viewCnt);
	}

	public String getLoginIdByMemberId(int memberId) {
		return articleDao.getLoginIdByMemberId(memberId);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void increaseViewCnt(int id) {
		articleDao.increaseViewCnt(id);
	}

	public List<Article> showArticleList(String searchKeyword) {
		return articleDao.showArticleList(searchKeyword);
	}

	public void doDelete(Article foundArticle) {
		articleDao.doDelete(foundArticle);
	}

	public void doModify(int id, String title, String body) {
		articleDao.doModify(id, title, body);
	}
	
}

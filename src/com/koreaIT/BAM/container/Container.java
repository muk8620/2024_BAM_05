package com.koreaIT.BAM.container;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.dto.Member;

public class Container {

	public static List<Member> memberList;
	public static List<Article> articleList;
	
	static {
		memberList = new ArrayList<>();
		articleList = new ArrayList<>();
	}
}

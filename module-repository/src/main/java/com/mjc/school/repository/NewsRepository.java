package com.mjc.school.repository;

import java.util.Set;

public interface NewsRepository extends BaseRepository<NewsModel, Long> {
		Set<NewsModel> readByTagName(String tagName);
		Set<NewsModel> readByTagId(Long tagId);
		Set<NewsModel> readByAuthorName(String authorName);
		Set<NewsModel> readByTitle(String title);
		Set<NewsModel> readByContent(String content);
}

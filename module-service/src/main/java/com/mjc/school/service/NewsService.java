package com.mjc.school.service;

import com.mjc.school.service.dto.NewsModelDto;

import java.util.List;

public interface NewsService extends BaseService<NewsModelDto, NewsModelDto, Long> {
		List<NewsModelDto> readAll();

		NewsModelDto readById(Long id);

		NewsModelDto create(NewsModelDto createRequest);

		NewsModelDto update(NewsModelDto updateRequest);

		boolean deleteById(Long id);
}

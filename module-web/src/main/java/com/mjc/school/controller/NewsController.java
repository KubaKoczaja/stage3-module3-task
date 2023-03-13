package com.mjc.school.controller;

import com.mjc.school.service.dto.NewsModelDto;

import java.util.List;

public interface NewsController extends BaseController<NewsModelDto, NewsModelDto, Long> {
		List<NewsModelDto> readAll();

		NewsModelDto readById(Long id);

		NewsModelDto create(NewsModelDto createRequest);

		NewsModelDto update(NewsModelDto updateRequest);

		boolean deleteById(Long id);
}

package com.mjc.school.service;

import com.mjc.school.service.dto.NewsModelDto;
import com.mjc.school.service.dto.NewsRequestDto;

import java.util.Set;

public interface NewsService extends BaseService<NewsRequestDto, NewsModelDto, Long> {
		Set<NewsModelDto> readNewsByVariousParameters(NewsRequestDto newsRequestDto);
}

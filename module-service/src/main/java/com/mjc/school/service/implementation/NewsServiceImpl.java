package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.NewsModel;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsModelDto;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.validator.ValidateNewsContent;
import com.mjc.school.service.validator.ValidateNewsId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
		private final BaseRepository<NewsModel, Long> newsModelRepository;
		private final NewsMapper newsMapper;

		@Override
		public List<NewsModelDto> readAll() {
				return newsModelRepository.readAll().stream().map(newsMapper::newsToNewsDTO).toList();
		}

		@Override
		@ValidateNewsId
		public NewsModelDto readById(Long id) {
				return newsMapper
								.newsToNewsDTO(newsModelRepository
												.readById(id)
												.orElseThrow(() -> new NoSuchEntityException("No news with such id!")));
		}

		@Override
		@ValidateNewsContent
		public NewsModelDto create(NewsModelDto createRequest) {
				NewsModel savedNews = newsMapper.newsDTOToNews(createRequest);
				return newsMapper.newsToNewsDTO(newsModelRepository.create(savedNews));
		}

		@Override
		@ValidateNewsContent
		public NewsModelDto update(NewsModelDto updateRequest) {
				NewsModel updatedNews = newsMapper.newsDTOToNews(updateRequest);
				return newsMapper.newsToNewsDTO(newsModelRepository.update(updatedNews));
		}

		@Override
		@ValidateNewsId
		public boolean deleteById(Long id) {
				return newsModelRepository.deleteById(id);
		}
}
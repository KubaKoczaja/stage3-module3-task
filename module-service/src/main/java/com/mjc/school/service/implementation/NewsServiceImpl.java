package com.mjc.school.service.implementation;

import com.mjc.school.repository.*;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.TagService;
import com.mjc.school.service.dto.NewsModelDto;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.mapper.NewsMapper;
import com.mjc.school.service.mapper.TagMapper;
import com.mjc.school.service.validator.ValidateNewsContent;
import com.mjc.school.service.validator.ValidateNewsId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
		private final NewsRepository newsModelRepository;
		private final AuthorService authorService;
		private final AuthorMapper authorMapper;
		private final TagService tagService;
		private final TagMapper tagMapper;
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
		public NewsModelDto create(NewsRequestDto createRequest) {
				createRequest.setCreateDate(LocalDateTime.now());
				createRequest.setLastUpdateDate(LocalDateTime.now());
				NewsModel savedNews = newsMapper.newsRequestToNews(createRequest);
    		savedNews.setAuthorModel(authorMapper.authorDtoToAuthor(authorService.readById(createRequest.getAuthorId())));
				if (!createRequest.getTagIds().isBlank()) {
						Set<TagModel> collect =
										Arrays.stream(createRequest.getTagIds().split(","))
														.map(t -> tagMapper.tagDTOToTag(tagService.readById(Long.valueOf(t))))
														.collect(Collectors.toSet());
						savedNews.setTagModelSet(collect);
				}
				return newsMapper.newsToNewsDTO(newsModelRepository.create(savedNews));
		}

		@Override
		@ValidateNewsContent
		public NewsModelDto update(NewsRequestDto updateRequest) {
				NewsModel newsFromDatabase = newsModelRepository.readById(updateRequest.getId())
								.orElseThrow(() -> new NoSuchEntityException("No such news!"));
    		newsFromDatabase.setTitle(updateRequest.getTitle());
				newsFromDatabase.setContent(updateRequest.getContent());
				newsFromDatabase.setLastUpdateDate(LocalDateTime.now());
				if (!updateRequest.getTagIds().isBlank()) {
						Set<TagModel> collect =
										Arrays.stream(updateRequest.getTagIds().split(","))
														.map(t -> tagMapper.tagDTOToTag(tagService.readById(Long.valueOf(t))))
														.collect(Collectors.toSet());
						newsFromDatabase.setTagModelSet(collect);
				}
				newsFromDatabase.setAuthorModel(authorMapper.authorDtoToAuthor(authorService.readById(updateRequest.getAuthorId())));
				return newsMapper.newsToNewsDTO(newsModelRepository.update(newsFromDatabase));
		}

		@Override
		@ValidateNewsId
		public boolean deleteById(Long id) {
				return newsModelRepository.deleteById(id);
		}

		@Override
		public Set<NewsModelDto> readNewsByVariousParameters(NewsRequestDto newsRequestDto) {
				Set<NewsModel> searchResult = new HashSet<>();
				if (!newsRequestDto.getTagNames().isBlank()) {
						Arrays.stream(newsRequestDto.getTagNames().split(","))
										.map(newsModelRepository::readByTagName)
										.forEach(searchResult::addAll);
				}
				if (!newsRequestDto.getTagIds().isBlank()) {
						Arrays.stream(newsRequestDto.getTagIds().split(","))
										.map(s -> newsModelRepository.readByTagId(Long.valueOf(s)))
										.forEach(searchResult::addAll);
				}
    if (!newsRequestDto.getAuthorName().isBlank()) {
				searchResult.addAll(newsModelRepository.readByAuthorName(newsRequestDto.getAuthorName()));
		}
		if (!newsRequestDto.getTitle().isBlank()) {
				searchResult.addAll(newsModelRepository.readByTitle(newsRequestDto.getTitle()));
		}
		if (!newsRequestDto.getContent().isBlank()) {
				searchResult.addAll(newsModelRepository.readByContent(newsRequestDto.getContent()));
		}
				return searchResult.stream().map(newsMapper::newsToNewsDTO).collect(Collectors.toSet());
		}
}
package com.mjc.school.service.implementation;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.implementation.NewsRepositoryImpl;
import com.mjc.school.service.dto.AuthorModelDto;
import com.mjc.school.service.dto.NewsModelDto;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.mapper.AuthorMapperImpl;
import com.mjc.school.service.mapper.NewsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NewsServiceImplTest {
		@InjectMocks
		private NewsServiceImpl newsModelService;
		@Mock
		private NewsRepositoryImpl newsModelRepository;
		@Mock
		private AuthorServiceImpl authorService;
		@Mock
		private AuthorMapper authorMapper;
		@Mock
		private NewsMapper newsMapper;

		@Test
		void shouldReadAllNews() {
				List<NewsModel> newsModelList = List.of(new NewsModel(), new NewsModel());
				when(newsMapper.newsToNewsDTO(any(NewsModel.class))).thenReturn(new NewsModelDto());
				when(newsModelRepository.readAll()).thenReturn(newsModelList);
				int lengthExpected = 2;
				assertEquals(lengthExpected, newsModelService.readAll().size());
		}

		@Test
		void shouldReturnEntityWithGivenId() {
				NewsModelDto expected = new NewsModelDto(1L, "test", "test", LocalDateTime.now(), LocalDateTime.now(), new AuthorModelDto());
				when(newsModelRepository.readById(anyLong())).thenReturn(Optional.of(new NewsModel()));
				when(newsMapper.newsToNewsDTO(any(NewsModel.class))).thenReturn(expected);
				assertEquals(expected, newsModelService.readById(1L));
		}

		@Test
		void shouldThrowExceptionWhenThereIsNoNewsWithSpecificId() {
				when(newsModelRepository.readById(anyLong())).thenThrow(new NoSuchEntityException(""));
				assertThrows(NoSuchEntityException.class, () -> newsModelService.readById(3L));
		}

		@Test
		void shouldReturnAddedObjectIfValuesAreCorrect() {
				NewsRequestDto newsModelToCreate = new NewsRequestDto(1L, "testTitle", "testContent", LocalDateTime.now(), LocalDateTime.now(), 1L, "", "test", "test");
				NewsModel newsToSave = new NewsModel(1L, "testTitle", "testContent", LocalDateTime.now(), LocalDateTime.now(), new AuthorModel(), new HashSet<>());
				lenient().when(newsMapper.newsRequestToNews(any(NewsRequestDto.class))).thenReturn(newsToSave);
				when(authorService.readById(anyLong())).thenReturn(new AuthorModelDto());
				when(authorMapper.authorDtoToAuthor(any(AuthorModelDto.class))).thenReturn(new AuthorModel());
				lenient().when(newsModelRepository.create(any(NewsModel.class))).thenReturn(new NewsModel());
				NewsModelDto savedNewsDto = new NewsModelDto(1L, "testTitle", "testContent", LocalDateTime.now(), LocalDateTime.now(), new AuthorModelDto());
				lenient().when(newsMapper.newsToNewsDTO(any(NewsModel.class))).thenReturn(savedNewsDto);
				assertEquals(savedNewsDto, newsModelService.create(newsModelToCreate));
		}

		@Test
		void shouldUpdateNewsWithGivenIdWhenValuesOfTitleAndContentAreCorrect() {
				NewsRequestDto newsModelToUpdate = new NewsRequestDto(1L, "new_title", "new_content", LocalDateTime.now(), LocalDateTime.now(), 1L, "", "test", "test");
				NewsModelDto updatedNewsDto = new NewsModelDto(1L, "testTitle", "testContent", LocalDateTime.now(), LocalDateTime.now(), new AuthorModelDto());
				NewsModel newsToSave = new NewsModel(1L, "testTitle", "testContent", LocalDateTime.now(), LocalDateTime.now(), new AuthorModel(), new HashSet<>());
				AuthorModel authorModel = new AuthorModel(1L, "TestAuthor", LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>());
				when(authorService.readById(anyLong())).thenReturn(new AuthorModelDto());
				when(authorMapper.authorDtoToAuthor(any(AuthorModelDto.class))).thenReturn(new AuthorModel());
				lenient().when(newsModelRepository.readById(anyLong())).thenReturn(Optional.of(newsToSave));
				lenient().when(newsModelRepository.update(any(NewsModel.class))).thenReturn(new NewsModel());
				lenient().when(newsMapper.newsToNewsDTO(any(NewsModel.class))).thenReturn(updatedNewsDto);
				assertEquals(updatedNewsDto, newsModelService.update(newsModelToUpdate));
		}

		@Test
		void shouldReturnTrueWhenEntityIsDeleted() {
				when(newsModelRepository.deleteById(anyLong())).thenReturn(true);
				assertTrue(newsModelService.deleteById(1L));
		}
}
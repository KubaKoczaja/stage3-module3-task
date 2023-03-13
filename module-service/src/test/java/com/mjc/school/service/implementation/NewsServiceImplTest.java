package com.mjc.school.service.implementation;

import com.mjc.school.repository.NewsModel;
import com.mjc.school.repository.implementation.NewsRepositoryImpl;
import com.mjc.school.service.dto.NewsModelDto;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.mapper.NewsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
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
				NewsModelDto expected = new NewsModelDto(1L, "test", "test", LocalDateTime.now(), LocalDateTime.now(), 1L);
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
				NewsModelDto newsModelToCreate = new NewsModelDto(1L, "testTitle", "testContent", LocalDateTime.now(), LocalDateTime.now(), 1L);
				lenient().when(newsMapper.newsDTOToNews(any(NewsModelDto.class))).thenReturn(new NewsModel());
				lenient().when(newsModelRepository.create(any(NewsModel.class))).thenReturn(new NewsModel());
				NewsModelDto savedNewsDto = new NewsModelDto(1L, "testTitle", "testContent", LocalDateTime.now(), LocalDateTime.now(), 1L);
				lenient().when(newsMapper.newsToNewsDTO(any(NewsModel.class))).thenReturn(savedNewsDto);
				assertEquals(savedNewsDto, newsModelService.create(newsModelToCreate));
		}

		@Test
		void shouldUpdateNewsWithGivenIdWhenValuesOfTitleAndContentAreCorrect() {
				NewsModelDto newsModelToUpdate = new NewsModelDto(1L, "new_title", "new_content", LocalDateTime.now(), LocalDateTime.now(), 1L);
				NewsModelDto updatedNewsDto = new NewsModelDto(1L, "testTitle", "testContent", LocalDateTime.now(), LocalDateTime.now(), 1L);
				lenient().when(newsMapper.newsDTOToNews(any(NewsModelDto.class))).thenReturn(new NewsModel());
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
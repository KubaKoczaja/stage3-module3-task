package com.mjc.school.service.implementation;

import com.mjc.school.repository.AuthorModel;
import com.mjc.school.repository.implementation.AuthorRepositoryImpl;
import com.mjc.school.service.dto.AuthorModelDto;
import com.mjc.school.service.mapper.AuthorMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
		@InjectMocks
		private AuthorServiceImpl authorService;
		@Mock
		private AuthorRepositoryImpl authorRepository;
		@Mock
		private AuthorMapper authorMapper;

		@Test
		void shouldReadAllAuthors() {
				List<AuthorModel> authorModelList = List.of(new AuthorModel(), new AuthorModel());
				when(authorMapper.authorToAuthorDto(any(AuthorModel.class))).thenReturn(new AuthorModelDto());
				when(authorRepository.readAll()).thenReturn(authorModelList);
				int lengthExpected = 2;
				assertEquals(lengthExpected, authorService.readAll().size());
		}

		@Test
		void shouldReturnEntityWithGivenId() {
				AuthorModelDto expected = new AuthorModelDto(1L, "test", LocalDateTime.now(), LocalDateTime.now());
				when(authorRepository.readById(anyLong())).thenReturn(Optional.of(new AuthorModel()));
				when(authorMapper.authorToAuthorDto(any(AuthorModel.class))).thenReturn(expected);
				assertEquals(expected, authorService.readById(1L));
		}

		@Test
		void shouldReturnAddedObjectIfValuesAreCorrect() {
				AuthorModelDto authorModelToCreate = new AuthorModelDto(1L, "test", LocalDateTime.now(), LocalDateTime.now());
				when(authorMapper.authorDtoToAuthor(any(AuthorModelDto.class))).thenReturn(new AuthorModel());
				when(authorRepository.create(any(AuthorModel.class))).thenReturn(new AuthorModel());
				AuthorModelDto savedAuthorDto = new AuthorModelDto(1L, "test", LocalDateTime.now(), LocalDateTime.now());
				when(authorMapper.authorToAuthorDto(any(AuthorModel.class))).thenReturn(savedAuthorDto);
				assertEquals(savedAuthorDto, authorService.create(authorModelToCreate));
		}

		@Test
		void shouldUpdateNewsWithGivenIdWhenValuesOfTitleAndContentAreCorrect() {
				AuthorModelDto authorModelToUpdate = new AuthorModelDto(1L, "test", LocalDateTime.now(), LocalDateTime.now());
				AuthorModelDto updatedAuthorDto = new AuthorModelDto(1L, "test", LocalDateTime.now(), LocalDateTime.now());
				when(authorMapper.authorDtoToAuthor(any(AuthorModelDto.class))).thenReturn(new AuthorModel());
				when(authorRepository.update(any(AuthorModel.class))).thenReturn(new AuthorModel());
				lenient().when(authorMapper.authorToAuthorDto(any(AuthorModel.class))).thenReturn(updatedAuthorDto);
				assertEquals(updatedAuthorDto, authorService.update(authorModelToUpdate));
		}

		@Test
		void shouldReturnTrueWhenEntityIsDeleted() {
				when(authorRepository.deleteById(anyLong())).thenReturn(true);
				assertTrue(authorService.deleteById(1L));
		}
}
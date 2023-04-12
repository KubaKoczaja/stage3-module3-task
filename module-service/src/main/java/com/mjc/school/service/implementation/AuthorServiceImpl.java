package com.mjc.school.service.implementation;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorModelDto;
import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.validator.ValidateAuthorId;
import com.mjc.school.service.validator.ValidateAuthorsDetails;
import com.mjc.school.service.validator.ValidateNewsId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
		private final AuthorRepository authorModelRepository;
		private final AuthorMapper authorMapper;
		@Override
		public List<AuthorModelDto> readAll() {
				return authorModelRepository.readAll().stream().map(authorMapper::authorToAuthorDto).toList();
		}

		@Override
		@ValidateAuthorId
		public AuthorModelDto readById(Long id) {
				return authorMapper.authorToAuthorDto(authorModelRepository
												.readById(id)
												.orElseThrow(() -> new NoSuchEntityException("No author with such id!")));
		}

		@Override
		@ValidateAuthorsDetails
		public AuthorModelDto create(AuthorRequestDto createRequest) {
				createRequest.setCreateDate(LocalDateTime.now());
				createRequest.setLastUpdateDate(LocalDateTime.now());
				AuthorModel savedAuthor = authorMapper.authorRequestToAuthor(createRequest);
				return authorMapper.authorToAuthorDto(authorModelRepository.create(savedAuthor));
		}

		@Override
		@ValidateAuthorsDetails
		public AuthorModelDto update(AuthorRequestDto updateRequest) {
				AuthorModel authorFromDatabase = authorModelRepository.readById(updateRequest.getId()).orElseThrow();
   		  authorFromDatabase.setName(updateRequest.getName());
				authorFromDatabase.setLastUpdateDate(LocalDateTime.now());
				return authorMapper.authorToAuthorDto(authorModelRepository.update(authorFromDatabase));
		}

		@Override
		@ValidateAuthorId
		@OnDelete
		public boolean deleteById(Long id) {
				return authorModelRepository.deleteById(id);
		}

		@Override
		@ValidateNewsId
		public AuthorModelDto readByNewsId(Long newsId) {
				return authorMapper.authorToAuthorDto(authorModelRepository.readByNewsId(newsId));
		}
}

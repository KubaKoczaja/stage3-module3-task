package com.mjc.school.service.implementation;

import com.mjc.school.repository.AuthorModel;
import com.mjc.school.repository.BaseRepository;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorModelDto;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.validator.ValidateAuthorId;
import com.mjc.school.service.validator.ValidateAuthorsDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
		private final BaseRepository<AuthorModel, Long> authorModelRepository;
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
		public AuthorModelDto create(AuthorModelDto createRequest) {
				AuthorModel savedAuthor = authorMapper.authorDtoToAuthor(createRequest);
				return authorMapper.authorToAuthorDto(authorModelRepository.create(savedAuthor));
		}

		@Override
		@ValidateAuthorsDetails
		public AuthorModelDto update(AuthorModelDto updateRequest) {
				AuthorModel updatedAuthor = authorMapper.authorDtoToAuthor(updateRequest);
				return authorMapper.authorToAuthorDto(authorModelRepository.update(updatedAuthor));
		}

		@Override
		@ValidateAuthorId
		@OnDelete
		public boolean deleteById(Long id) {
				return authorModelRepository.deleteById(id);
		}
}

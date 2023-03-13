package com.mjc.school.service;

import com.mjc.school.service.dto.AuthorModelDto;

import java.util.List;

public interface AuthorService extends BaseService<AuthorModelDto, AuthorModelDto, Long> {
		List<AuthorModelDto> readAll();

		AuthorModelDto readById(Long id);

		AuthorModelDto create(AuthorModelDto createRequest);

		AuthorModelDto update(AuthorModelDto updateRequest);

		boolean deleteById(Long id);
}

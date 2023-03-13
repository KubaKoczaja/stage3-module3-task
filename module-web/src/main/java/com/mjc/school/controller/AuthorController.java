package com.mjc.school.controller;

import com.mjc.school.service.dto.AuthorModelDto;

import java.util.List;

public interface AuthorController extends BaseController<AuthorModelDto, AuthorModelDto, Long> {
		List<AuthorModelDto> readAll();

		AuthorModelDto readById(Long id);

		AuthorModelDto create(AuthorModelDto createRequest);

		AuthorModelDto update(AuthorModelDto updateRequest);

		boolean deleteById(Long id);
}

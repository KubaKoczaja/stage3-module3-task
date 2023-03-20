package com.mjc.school.controller.implementation;

import com.mjc.school.controller.*;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorControllerImpl implements BaseController<AuthorModelDto, AuthorModelDto, Long> {
		private final AuthorService authorService;
		@Override
		@CommandHandler(id = 2)
		public List<AuthorModelDto> readAll() {
				return authorService.readAll();
		}

		@Override
		@CommandHandler(id = 4)
		public AuthorModelDto readById(@CommandParam Long id) {
				return authorService.readById(id);
		}

		@Override
		@CommandHandler(id = 6)
		public AuthorModelDto create(@CommandBody AuthorModelDto createRequest) {
				return authorService.create(createRequest);
		}

		@Override
		@CommandHandler(id = 8)
		public AuthorModelDto update(@CommandBody AuthorModelDto updateRequest) {
				AuthorModelDto authorReadById = authorService.readById(updateRequest.getId());
				updateRequest.setCreateDate(authorReadById.getCreateDate());
				return authorService.update(updateRequest);
		}

		@Override
		@CommandHandler(id = 10)
		public boolean deleteById(@CommandParam Long id) {
				return authorService.deleteById(id);
		}
}
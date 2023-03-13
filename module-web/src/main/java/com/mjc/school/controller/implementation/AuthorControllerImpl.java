package com.mjc.school.controller.implementation;

import com.mjc.school.controller.AuthorController;
import com.mjc.school.controller.command.annotation.CommandHandler;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.dto.AuthorModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorControllerImpl implements AuthorController {
		private final AuthorService authorService;
		@Override
		@CommandHandler
		public List<AuthorModelDto> readAll() {
				return authorService.readAll();
		}

		@Override
		@CommandHandler
		public AuthorModelDto readById(Long id) {
				return authorService.readById(id);
		}

		@Override
		@CommandHandler
		public AuthorModelDto create(AuthorModelDto createRequest) {
				return authorService.create(createRequest);
		}

		@Override
		@CommandHandler
		public AuthorModelDto update(AuthorModelDto updateRequest) {
				AuthorModelDto authorReadById = authorService.readById(updateRequest.getId());
				updateRequest.setCreateDate(authorReadById.getCreateDate());
				return authorService.update(updateRequest);
		}

		@Override
		@CommandHandler
		public boolean deleteById(Long id) {
				return authorService.deleteById(id);
		}
}
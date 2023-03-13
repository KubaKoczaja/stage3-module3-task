package com.mjc.school.controller.command;

import com.mjc.school.controller.AuthorController;
import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.command.annotation.CommandBody;
import com.mjc.school.service.dto.AuthorModelDto;
import com.mjc.school.service.dto.NewsModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandProvider {
		private final NewsController newsControllerImpl;
		private final AuthorController authorControllerImpl;

		@CommandBody(id = 1)
		public Command readAllNews() {
				return () -> newsControllerImpl.readAll().forEach(System.out::println);
		}
		@CommandBody(id = 2)
		public Command readAllAuthors() {
				return () -> authorControllerImpl.readAll().forEach(System.out::println);
		}
		@CommandBody(id = 3)
		public Command readNewsById(Long id) {
				return () -> System.out.println(newsControllerImpl.readById(id));
		}
		@CommandBody(id = 4)
		public Command readAuthorById(Long id) {
				return () -> System.out.println(authorControllerImpl.readById(id));
		}
		@CommandBody(id = 5)
		public Command createNews(NewsModelDto newsModel) {
				return () -> newsControllerImpl.create(newsModel);
		}
		@CommandBody(id = 6)
		public Command createAuthor(AuthorModelDto authorModel) {
				return () -> authorControllerImpl.create(authorModel);
		}
		@CommandBody(id = 7)
		public Command updateNews(NewsModelDto newsModel) {
				return () -> newsControllerImpl.update(newsModel);
		}
		@CommandBody(id = 8)
		public Command updateAuthor(AuthorModelDto authorModel) {
				return () -> authorControllerImpl.update(authorModel);
		}
		@CommandBody(id = 9)
		public Command deleteNewsById(Long id) {
				return () -> newsControllerImpl.deleteById(id);
		}
		@CommandBody(id = 10)
		public Command deleteAuthorById(Long id) {
				return () -> authorControllerImpl.deleteById(id);
		}
		@CommandBody(id = 11)
		public Command exit() {
				return () -> System.out.println("Exit");
		}
		@CommandBody(id = 12)
		public Command invalidOption() {
				return () -> System.out.println("Invalid option number!");
		}
}

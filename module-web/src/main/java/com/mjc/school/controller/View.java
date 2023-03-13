package com.mjc.school.controller;

import com.mjc.school.controller.command.Command;
import com.mjc.school.controller.command.CommandExecutor;
import com.mjc.school.controller.command.CommandFactory;
import com.mjc.school.service.AuthorService;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.AuthorModelDto;
import com.mjc.school.service.dto.NewsModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class View {
		private final Scanner scanner = new Scanner(System.in);
		private final NewsService newsService;
		private final AuthorService authorService;
		private final CommandExecutor commandExecutor;
		private final CommandFactory commandFactory;
		private int mainMenu() {
				System.out.println("""
						Enter the number of operation:
						1 - Get all news.
						2 - Get all authors.
						3 - Get news by id.
						4 - Get author by id.
						5 - Create news.
						6 - Create author.
						7 - Update news.
						8 - Update author.
						9 - Remove news by id.
						10 - Remove author by id.
						0 - Exit.""");
				scanner.reset();
				return scanner.nextInt();
		}
		public void start() {
				int menuOption;
				do {
						menuOption = mainMenu();
						Command command = switch (menuOption) {
								case 1 -> allNewsView();
								case 2 -> allAuthorsView();
								case 3 -> newsByIdView();
								case 4 -> authorByIdView();
								case 5 -> createNewsView();
								case 6 -> createAuthorView();
								case 7 -> updateNewsView();
								case 8 -> updateAuthorView();
								case 9 -> deleteNewsByIdView();
								case 10 -> deleteAuthorByIdView();
								case 0 -> exitView();
								default -> invalidOption();
						};
						try {
								commandExecutor.executeCommand(command);
						} catch (RuntimeException e) {
								menuOption = -1;
						}
				} while(menuOption != 0);
		}

		private Command allNewsView() {
				System.out.println("List of all news");
				return commandFactory.getCommand(1);
		}

		private Command allAuthorsView() {
				System.out.println("List of all authors");
				return commandFactory.getCommand(2);
		}

		private Command newsByIdView() {
				System.out.println("Please enter news id:");
				Long id = scanner.nextLong();
				return commandFactory.getCommand(3,id);
		}

		private Command authorByIdView() {
				System.out.println("Please enter author id:");
				Long id = scanner.nextLong();
				return commandFactory.getCommand(4,id);
		}

		private Command createNewsView() {
				scanner.nextLine();
				System.out.println("Please enter title:");
				String title = scanner.nextLine();
				System.out.println("Please enter content:");
				String content = scanner.nextLine();
				System.out.println("Please enter Author Id:");
				Long authorId = scanner.nextLong();
				NewsModelDto newsModel = new NewsModelDto();
				newsModel.setId((long) newsService.readAll().size());
				newsModel.setTitle(title);
				newsModel.setContent(content);
				newsModel.setCreateDate(LocalDateTime.now());
				newsModel.setLastUpdateDate(LocalDateTime.now());
				newsModel.setAuthorId(authorId);
				return commandFactory.getCommand(5,newsModel);
		}

		private Command createAuthorView() {
				scanner.nextLine();
				System.out.println("Please enter author's details:");
				String name = scanner.nextLine();
				AuthorModelDto authorModel = new AuthorModelDto();
				authorModel.setId((long) authorService.readAll().size());
				authorModel.setName(name);
				authorModel.setCreateDate(LocalDateTime.now());
				authorModel.setLastUpdateDate(LocalDateTime.now());
				return commandFactory.getCommand(6,authorModel);
		}

		private Command deleteNewsByIdView() {
				scanner.nextLine();
				System.out.println("Please enter news to remove:");
				Long id = scanner.nextLong();
				return commandFactory.getCommand(9, id);
		}

		private Command deleteAuthorByIdView() {
				scanner.nextLine();
				System.out.println("Please enter author to remove:");
				Long id = scanner.nextLong();
				return commandFactory.getCommand(10, id);
		}

		private Command updateAuthorView() {
				scanner.nextLine();
				System.out.println("Please enter author to update:");
				Long id = scanner.nextLong();
				System.out.println("Please enter new name:");
				scanner.nextLine();
				String name = scanner.nextLine();
				AuthorModelDto authorModelToUpdate = new AuthorModelDto();
				authorModelToUpdate.setId(id);
				authorModelToUpdate.setName(name);
				authorModelToUpdate.setLastUpdateDate(LocalDateTime.now());
				return commandFactory.getCommand(8,authorModelToUpdate);
		}

		private Command updateNewsView() {
				scanner.nextLine();
				System.out.println("Please enter news to update:");
				Long id = scanner.nextLong();
				System.out.println("Please enter new title:");
				scanner.nextLine();
				String title = scanner.nextLine();
				System.out.println("Please enter new content:");
				String content = scanner.nextLine();
				NewsModelDto newsModelToUpdate = new NewsModelDto();
				newsModelToUpdate.setId(id);
				newsModelToUpdate.setTitle(title);
				newsModelToUpdate.setContent(content);
				newsModelToUpdate.setLastUpdateDate(LocalDateTime.now());
				return commandFactory.getCommand(7,newsModelToUpdate);
		}

		private Command exitView() {
				return commandFactory.getCommand(11);
		}

		private Command invalidOption() {
				return commandFactory.getCommand(12);
		}
}
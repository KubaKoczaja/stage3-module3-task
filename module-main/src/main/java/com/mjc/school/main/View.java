package com.mjc.school.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjc.school.main.command.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class View {
		private final Scanner scanner = new Scanner(System.in);
		private final ObjectMapper objectMapper = new ObjectMapper();
		public int mainMenu() {
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
		public Command allNewsView() {
				System.out.println("List of all news");
				return new Command(1, null, null);
		}
		public Command allAuthorsView() {
				System.out.println("List of all authors");
				return new Command(2, null, null);
		}
		public Command newsByIdView() {
				System.out.println("Please enter news id:");
				Long id = scanner.nextLong();
				return new Command(3, id, null);
		}
		public Command authorByIdView() {
				System.out.println("Please enter author id:");
				Long id = scanner.nextLong();
				return new Command(4, id, null);
		}
		public Command createNewsView() {
				Command command = null;
				scanner.nextLine();
				System.out.println("Please enter title:");
				String title = scanner.nextLine();
				System.out.println("Please enter content:");
				String content = scanner.nextLine();
				System.out.println("Please enter Author Id:");
				Long authorId = scanner.nextLong();
				Map<String, String> body = Map.of("title",title,"content", content, "authorId",String.valueOf(authorId));
				try {
						command = new Command(5, null, objectMapper.writeValueAsString(body));
				} catch (JsonProcessingException e) {
						System.out.println(e.getMessage());
				}
				return command;
		}
		public Command createAuthorView() {
				Command command = null;
				scanner.nextLine();
				System.out.println("Please enter author's details:");
				String name = scanner.nextLine();
				Map<String, String> body = Map.of("name",name);
				try {
						command = new Command(6, null, objectMapper.writeValueAsString(body));
				} catch (JsonProcessingException e) {
						System.out.println(e.getMessage());
				}
				return command;
		}
		public Command updateNewsView() {
				Command command = null;
				scanner.nextLine();
				System.out.println("Please enter news to update:");
				Long id = scanner.nextLong();
				System.out.println("Please enter new title:");
				scanner.nextLine();
				String title = scanner.nextLine();
				System.out.println("Please enter new content:");
				String content = scanner.nextLine();
				Map<String, String> body = Map.of("id", String.valueOf(id),"title",title,"content", content);
				try {
						command = new Command(7, null, objectMapper.writeValueAsString(body));
				} catch (JsonProcessingException e) {
						System.out.println(e.getMessage());
				}
				return command;
		}
		public Command updateAuthorView() {
				Command command = null;
				scanner.nextLine();
				System.out.println("Please enter author to update:");
				Long id = scanner.nextLong();
				System.out.println("Please enter new name:");
				scanner.nextLine();
				String name = scanner.nextLine();
				Map<String, String> body = Map.of("id", String.valueOf(id),"name",name);
				try {
						command = new Command(8, null, objectMapper.writeValueAsString(body));
				} catch (JsonProcessingException e) {
						System.out.println(e.getMessage());
				}
				return command;
		}
		public Command deleteNewsByIdView() {
				scanner.nextLine();
				System.out.println("Please enter news to remove:");
				Long id = scanner.nextLong();
				return new Command(9, id, null);
		}
		public Command deleteAuthorByIdView() {
				scanner.nextLine();
				System.out.println("Please enter author to remove:");
				Long id = scanner.nextLong();
				return new Command(10, id, null);
		}
		public Command exitView() {
				return new Command(0, null, null);
		}
		public Command invalidOption() {
				return new Command(-1, null, null);
		}
}
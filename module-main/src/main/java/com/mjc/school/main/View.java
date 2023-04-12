package com.mjc.school.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjc.school.main.command.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
public class View {
		public static final String OBJ_TITLE = "title";
		public static final String OBJ_CONTENT = "content";
		public static final String OBJ_TAG_IDS = "tagIds";
		private final Scanner scanner = new Scanner(System.in);
		private final ObjectMapper objectMapper = new ObjectMapper();
		public int mainMenu() {
				scanner.reset();
				log.info("""
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
						11 - Get all tags.
						12 - Get tag by id.
						13 - Create tag.
						14 - Update tag.
						15 - Remove tag by id.
						16 - Get author by news id.
						17 - Get tags by news id.
						18 - Get news by various parameters.
						0 - Exit.""");
				scanner.reset();
				return scanner.nextInt();
		}
		public Command allNewsView() {
				log.info("List of all news");
				return new Command(1, null, null);
		}
		public Command allAuthorsView() {
				log.info("List of all authors");
				return new Command(2, null, null);
		}
		public Command newsByIdView() {
				log.info("Please enter news id:");
				Long id = scanner.nextLong();
				return new Command(3, id, null);
		}
		public Command authorByIdView() {
				log.info("Please enter author id:");
				Long id = scanner.nextLong();
				return new Command(4, id, null);
		}
		public Command createNewsView() {
				Command command = null;
				scanner.nextLine();
				log.info("Please enter title:");
				String title = scanner.nextLine();
				log.info("Please enter content:");
				String content = scanner.nextLine();
				log.info("Please enter Author Id:");
				Long authorId = scanner.nextLong();
				scanner.nextLine();
				log.info("Please enter tag Ids separated by comas:");
				String tagIds = scanner.nextLine();
				Map<String, String> body = Map.of(OBJ_TITLE,title, OBJ_CONTENT, content, "authorId",String.valueOf(authorId), OBJ_TAG_IDS, tagIds);
				try {
						command = new Command(5, null, objectMapper.writeValueAsString(body));
				} catch (JsonProcessingException e) {
						log.info(e.getMessage());
				}
				return command;
		}
		public Command createAuthorView() {
				Command command = null;
				scanner.nextLine();
				log.info("Please enter author's details:");
				String name = scanner.nextLine();
				Map<String, String> body = Map.of("name",name);
				try {
						command = new Command(6, null, objectMapper.writeValueAsString(body));
				} catch (JsonProcessingException e) {
						log.info(e.getMessage());
				}
				return command;
		}
		public Command updateNewsView() {
				Command command = null;
				scanner.nextLine();
				log.info("Please enter news to update:");
				Long id = scanner.nextLong();
				log.info("Please enter new title:");
				scanner.nextLine();
				String title = scanner.nextLine();
				log.info("Please enter new content:");
				String content = scanner.nextLine();
				log.info("Please enter Author Id:");
				Long authorId = scanner.nextLong();
				scanner.nextLine();
				log.info("Please enter tag Ids separated by comas:");
				String tagIds = scanner.nextLine();
				Map<String, String> body = Map.of("id", String.valueOf(id), OBJ_TITLE,title, OBJ_CONTENT, content,"authorId",String.valueOf(authorId), OBJ_TAG_IDS, tagIds);
				try {
						command = new Command(7, null, objectMapper.writeValueAsString(body));
				} catch (JsonProcessingException e) {
						log.info(e.getMessage());
				}
				return command;
		}
		public Command updateAuthorView() {
				Command command = null;
				scanner.nextLine();
				log.info("Please enter author to update:");
				Long id = scanner.nextLong();
				log.info("Please enter new name:");
				scanner.nextLine();
				String name = scanner.nextLine();
				Map<String, String> body = Map.of("id", String.valueOf(id),"name",name);
				try {
						command = new Command(8, null, objectMapper.writeValueAsString(body));
				} catch (JsonProcessingException e) {
						log.info(e.getMessage());
				}
				return command;
		}
		public Command deleteNewsByIdView() {
				scanner.nextLine();
				log.info("Please enter news to remove:");
				Long id = scanner.nextLong();
				return new Command(9, id, null);
		}
		public Command deleteAuthorByIdView() {
				scanner.nextLine();
				log.info("Please enter author to remove:");
				Long id = scanner.nextLong();
				return new Command(10, id, null);
		}
		public Command allTagsView() {
				log.info("List of all tags");
				return new Command(11, null, null);
		}
		public Command tagByIdView() {
				log.info("Please enter tag id:");
				Long id = scanner.nextLong();
				return new Command(12, id, null);
		}
		public Command createTagView() {
				Command command = null;
				scanner.nextLine();
				log.info("Please enter tag's name:");
				String name = scanner.nextLine();
				Map<String, String> body = Map.of("name",name);
				try {
						command = new Command(13, null, objectMapper.writeValueAsString(body));
				} catch (JsonProcessingException e) {
						log.info(e.getMessage());
				}
				return command;
		}
		public Command updateTagView() {
				Command command = null;
				scanner.nextLine();
				log.info("Please enter tag to update:");
				Long id = scanner.nextLong();
				log.info("Please enter new name:");
				scanner.nextLine();
				String name = scanner.nextLine();
				Map<String, String> body = Map.of("id", String.valueOf(id),"name",name);
				try {
						command = new Command(14, null, objectMapper.writeValueAsString(body));
				} catch (JsonProcessingException e) {
						log.info(e.getMessage());
				}
				return command;
		}
		public Command deleteTagView() {
				scanner.nextLine();
				log.info("Please enter id of tag to be removed:");
				Long id = scanner.nextLong();
				return new Command(15, id, null);
		}
		public Command readAuthorByNewsIdView() {
				scanner.nextLine();
				log.info("Please enter id of news created by author");
				Long id = scanner.nextLong();
				return new Command(16, id, null);
		}
		public Command readTagsByNewsIdView() {
				scanner.nextLine();
				log.info("Please enter id of news to check it's tags: ");
				Long id = scanner.nextLong();
				return new Command(17, id, null);
		}
		public Command readNewsByVariousParameters() {
				scanner.nextLine();
				log.info("Enter tag names (separate by comas):");
				String tagNames = scanner.nextLine();
				log.info("Enter tag ids (separate by comas):");
				String tagIds = scanner.nextLine();
				log.info("Enter author name:");
				String authorName = scanner.nextLine();
				log.info("Enter title:");
				String title = scanner.nextLine();
				log.info("Enter content:");
				String content = scanner.nextLine();
				Command command = null;
				Map<String, String> body = Map.of(OBJ_TITLE, title, OBJ_CONTENT,content, OBJ_TAG_IDS, tagIds, "tagNames", tagNames, "authorName", authorName);
				try {
						command = new Command(18, null, objectMapper.writeValueAsString(body));
				} catch (JsonProcessingException e) {
						log.info(e.getMessage());
				}
				return command;
		}
		public Command exitView() {
				return new Command(0, null, null);
		}
		public Command invalidOption() {
				return new Command(-1, null, null);
		}


}
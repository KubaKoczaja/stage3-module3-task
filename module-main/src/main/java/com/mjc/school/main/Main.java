package com.mjc.school.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mjc.school.main.command.Command;
import com.mjc.school.main.command.CommandExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Slf4j
public class Main {
		public static void main(String[] args) {
				ApplicationContext context = new AnnotationConfigApplicationContext("com.mjc.school");
				View view = context.getBean("view", View.class);
				int menuOption;
				CommandExecutor commandExecutor = context.getBean("commandExecutor", CommandExecutor.class);
				do {
						menuOption = view.mainMenu();
						Command command = switch (menuOption) {
								case 1 -> view.allNewsView();
								case 2 -> view.allAuthorsView();
								case 3 -> view.newsByIdView();
								case 4 -> view.authorByIdView();
								case 5 -> view.createNewsView();
								case 6 -> view.createAuthorView();
								case 7 -> view.updateNewsView();
								case 8 -> view.updateAuthorView();
								case 9 -> view.deleteNewsByIdView();
								case 10 -> view.deleteAuthorByIdView();
								case 11 -> view.allTagsView();
								case 12 -> view.tagByIdView();
								case 13 -> view.createTagView();
								case 14 -> view.updateTagView();
								case 15 -> view.deleteTagView();
								case 16 -> view.readAuthorByNewsIdView();
								case 17 -> view.readTagsByNewsIdView();
								case 18 -> view.readNewsByVariousParameters();
								case 0 -> view.exitView();
								default -> view.invalidOption();
						};
						try {
								Object result = commandExecutor.execute(command);
								if (result instanceof List<?>) {
										((List<?>) result).forEach(r ->log.info(r.toString()));
								} else {
										log.info(result.toString());
								}
						} catch (RuntimeException e) {
								log.info(e.getMessage());
								menuOption = -1;
						}
						 catch (JsonProcessingException | InvocationTargetException | IllegalAccessException e) {
								 log.info(e.getCause().getMessage());
								 menuOption = -1;
						 }
				} while(menuOption != 0);
		}
}
package com.mjc.school.controller.command;

import com.mjc.school.controller.command.annotation.CommandBody;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Component
public class CommandFactory {
		private final List<Method> commands;
		private final CommandProvider commandProvider;
		public CommandFactory(CommandProvider commandProvider) {
				this.commandProvider = commandProvider;
				commands = Arrays.stream(CommandProvider.class.getDeclaredMethods())
								.filter(m -> m.isAnnotationPresent(CommandBody.class))
								.sorted(Comparator.comparingInt(o -> o.getDeclaredAnnotation(CommandBody.class).id()))
								.toList();
		}
		public Command getCommand(int id, Object...args) {
				try {
						return (Command) commands.get(id - 1).invoke(commandProvider, args);
				} catch (IllegalAccessException | InvocationTargetException e) {
						System.out.println(e.getMessage());
				}
				return () -> System.out.println("Invalid option number!");
		}
}

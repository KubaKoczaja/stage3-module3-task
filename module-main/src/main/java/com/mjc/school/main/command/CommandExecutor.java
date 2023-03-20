package com.mjc.school.main.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjc.school.controller.CommandBody;
import com.mjc.school.controller.CommandHandler;
import com.mjc.school.controller.CommandParam;
import com.mjc.school.main.exception.InvalidCommandException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class CommandExecutor {
		private final Map<Method, Object> controllersMethods;
		private final ObjectMapper objectMapper = new ObjectMapper();
		public CommandExecutor(ListableBeanFactory listableBeanFactory) {
				controllersMethods = new HashMap<>();
				Collection<Object> values = listableBeanFactory.getBeansWithAnnotation(Controller.class).values();
				for(Object obj: values) {
						List<Method> methods =
										Arrays.stream(obj.getClass().getDeclaredMethods())
														.filter(m -> m.isAnnotationPresent(CommandHandler.class) && Arrays.stream(m.getParameters()).noneMatch(p -> p.getType().equals(Object.class)))
														.toList();
						for(Method method: methods) {
								controllersMethods.put(method, obj);
						}
				}
		}
		public Object execute(Command command) throws JsonProcessingException, InvocationTargetException,
						IllegalAccessException {
				if (command.getId() == 0) {
						System.exit(0);
				}
				if (command.getId() == -1) {
						throw new InvalidCommandException("No such option. Please try again!");
				}
				Map.Entry<Method, Object> methodWithController =
								controllersMethods.entrySet()
												.stream()
												.filter(e -> e.getKey().getDeclaredAnnotation(CommandHandler.class).id() == command.getId())
												.findFirst()
												.orElseThrow(() -> new RuntimeException("No such method!"));
						return methodWithController.getKey().invoke(methodWithController.getValue(), getMethodArgs(command, methodWithController.getKey()));
		}
		private Object[] getMethodArgs(Command command, Method method) throws JsonProcessingException {
				List<Object> args = new ArrayList<>();
				Annotation[][] parameterAnnotations = method.getParameterAnnotations();
    if (Arrays.stream(parameterAnnotations).flatMap(Arrays::stream).filter(CommandParam.class::isInstance).count() == 1) {
						args.add(command.getParam());
    } else if (Arrays.stream(parameterAnnotations).flatMap(Arrays::stream).filter(CommandBody.class::isInstance).count() == 1) {
						args.add(objectMapper.readValue(command.getBody(), method.getParameters()[0].getType()));
    } else {
      return new Object[0];
		}
				return args.toArray();
		}
}

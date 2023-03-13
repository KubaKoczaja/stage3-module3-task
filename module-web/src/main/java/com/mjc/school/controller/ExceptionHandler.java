package com.mjc.school.controller;

import com.mjc.school.service.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class ExceptionHandler {
		private final View view;
		@AfterThrowing(pointcut = "@annotation(com.mjc.school.controller.command.annotation.CommandHandler)", throwing = "e")
		public void handleException(CustomException e) {
				System.out.println(e.getMessage());
		}
}


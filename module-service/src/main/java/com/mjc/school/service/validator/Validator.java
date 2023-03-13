package com.mjc.school.service.validator;

import com.mjc.school.repository.AuthorModel;
import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.NewsModel;
import com.mjc.school.service.dto.AuthorModelDto;
import com.mjc.school.service.dto.NewsModelDto;
import com.mjc.school.service.exception.InvalidContentException;
import com.mjc.school.service.exception.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
@RequiredArgsConstructor
public class Validator {
		private final DataSource dataSource;
		private static final String NO_AUTHOR_ID = "No such author id!";
		@Before("@annotation(ValidateNewsId)")
		public void validateIfNewsIdExists(JoinPoint joinPoint) throws NoSuchEntityException{
				Long id = (Long) joinPoint.getArgs()[0];
				List<NewsModel> newsModelList = dataSource.parseNewsFromFile();
				if (newsModelList.stream().map(NewsModel::getId).noneMatch(i -> i.equals(id))) {
						throw new NoSuchEntityException("No such id!");
				}
		}
		@Before("@annotation(ValidateAuthorId)")
		public void validateIfAuthorsIdExists(JoinPoint joinPoint) throws NoSuchEntityException {
				Long id = (Long) joinPoint.getArgs()[0];
				List<AuthorModel> authorModelList = dataSource.parseAuthorFromFile();
				if (authorModelList.stream().map(AuthorModel::getId).noneMatch(i -> i.equals(id))) {
						throw new NoSuchEntityException(NO_AUTHOR_ID);
				}
		}
		@Before("@annotation(ValidateNewsContent)")
		public void validateNewsContent(JoinPoint joinPoint) {
				NewsModelDto newsModel = (NewsModelDto) joinPoint.getArgs()[0];
				List<AuthorModel> authorModelList = dataSource.parseAuthorFromFile();
				if (newsModel.getTitle().length() < 5 || newsModel.getTitle().length() > 30) {
						throw new InvalidContentException("Title must be between 5 and 30 characters long!");
				}
				if (newsModel.getContent().length() < 5 || newsModel.getContent().length() > 255) {
						throw new InvalidContentException("Title must be between 5 and 255 characters long!");
				}
				if (authorModelList.stream().map(AuthorModel::getId).noneMatch(i -> i.equals(newsModel.getAuthorId()))) {
						throw new NoSuchEntityException(NO_AUTHOR_ID);
				}
		}
		@Before("@annotation(ValidateAuthorsDetails)")
		public void validateAuthorsName(JoinPoint joinPoint) {
				AuthorModelDto authorModel = (AuthorModelDto) joinPoint.getArgs()[0];
				if (authorModel.getName().isBlank()) {
						throw new InvalidContentException("Author's name can't be empty!");
				}
				if (authorModel.getName().length() > 155) {
						throw new InvalidContentException("Author's name can't be longer than 155 characters!");
				}
		}
}

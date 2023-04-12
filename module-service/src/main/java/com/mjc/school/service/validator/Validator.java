package com.mjc.school.service.validator;

import com.mjc.school.repository.*;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.dto.*;
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
		private final NewsRepository newsModelRepository;
		private final AuthorRepository authorModelRepository;
		private final TagRepository tagRepository;
		private static final String NO_AUTHOR_ID = "No such author id!";
		@Before("@annotation(ValidateNewsId)")
		public void validateIfNewsIdExists(JoinPoint joinPoint) throws NoSuchEntityException{
				Long id = (Long) joinPoint.getArgs()[0];
				List<NewsModel> newsModelList = newsModelRepository.readAll();
				if (newsModelList.stream().map(NewsModel::getId).noneMatch(i -> i.equals(id))) {
						throw new NoSuchEntityException("No such id!");
				}
		}
		@Before("@annotation(ValidateAuthorId)")
		public void validateIfAuthorsIdExists(JoinPoint joinPoint) throws NoSuchEntityException {
				Long id = (Long) joinPoint.getArgs()[0];
				List<AuthorModel> authorModelList = authorModelRepository.readAll();
				if (authorModelList.stream().map(AuthorModel::getId).noneMatch(i -> i.equals(id))) {
						throw new NoSuchEntityException(NO_AUTHOR_ID);
				}
		}
		@Before("@annotation(ValidateTagId)")
		public void validateIfTagIdExists(JoinPoint joinPoint) throws NoSuchEntityException {
				Long id = (Long) joinPoint.getArgs()[0];
				List<TagModel> tagModelList = tagRepository.readAll();
				if (tagModelList.stream().map(TagModel::getId).noneMatch(i -> i.equals(id))) {
						throw new NoSuchEntityException("No such tag id!");
				}
		}
		@Before("@annotation(ValidateNewsContent)")
		public void validateNewsContent(JoinPoint joinPoint) {
				NewsRequestDto newsModel = (NewsRequestDto) joinPoint.getArgs()[0];
				List<AuthorModel> authorModelList = authorModelRepository.readAll();
				if (newsModel.getTitle().length() < 5 || newsModel.getTitle().length() > 30) {
						throw new InvalidContentException("Title must be between 5 and 30 characters long!");
				}
				if (newsModel.getContent().length() < 5 || newsModel.getContent().length() > 255) {
						throw new InvalidContentException("Content must be between 5 and 255 characters long!");
				}
				if (authorModelList.stream().map(AuthorModel::getId).noneMatch(i -> i.equals(newsModel.getAuthorId()))) {
						throw new NoSuchEntityException(NO_AUTHOR_ID);
				}
		}
		@Before("@annotation(ValidateAuthorsDetails)")
		public void validateAuthorsName(JoinPoint joinPoint) {
				AuthorRequestDto authorModel = (AuthorRequestDto) joinPoint.getArgs()[0];
				if (authorModel.getName().length() < 3 || authorModel.getName().length() > 155) {
						throw new InvalidContentException("Author's name must be between 5 and 255 characters long!");
				}
		}
		@Before("@annotation(ValidateTagsDetails)")
		public void validateTagContent(JoinPoint joinPoint) {
				TagRequestDto tagModel = (TagRequestDto) joinPoint.getArgs()[0];
				if (tagModel.getName().length() < 3 || tagModel.getName().length() > 155) {
						throw new InvalidContentException("Tag's name must be between 5 and 255 characters long!");
				}
		}
}

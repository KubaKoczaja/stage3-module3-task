package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Aspect
public class DeleteAuthorAspect {
		private final BaseRepository<NewsModel, Long> newsModelRepository;
		@Before("@annotation(OnDelete)")
		public void deletingNewsCreatedByDeletedAuthor(JoinPoint joinPoint) {
				Long deletedAuthorId = (Long) joinPoint.getArgs()[0];
				newsModelRepository.readAll()
								.stream()
								.filter(n -> n.getAuthorModel().getId().equals(deletedAuthorId))
								.forEach(n -> newsModelRepository.deleteById(n.getId()));
		}
}

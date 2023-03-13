package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.NewsModel;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Aspect
public class DeleteAuthorAspect {
		private final BaseRepository<NewsModel, Long> newsModelRepository;
		private final DataSource dataSource;
		@After("@annotation(OnDelete)")
		public void deletingNewsCreatedByDeletedAuthor(JoinPoint joinPoint) {
				Long deletedAuthorId = (Long) joinPoint.getArgs()[0];
				List<NewsModel> newsWithOutConcreteAuthor = newsModelRepository.readAll()
								.stream()
								.filter(n -> !n.getAuthorId().equals(deletedAuthorId))
								.toList();
				dataSource.saveAllNewsToFile(newsWithOutConcreteAuthor);
		}
}

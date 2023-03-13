package com.mjc.school.controller.implementation;

import com.mjc.school.controller.NewsController;
import com.mjc.school.controller.command.annotation.CommandHandler;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsControllerImpl implements NewsController {
		private final NewsService newsService;
		@Override
		@CommandHandler
		public List<NewsModelDto> readAll() {
				return newsService.readAll();
		}

		@Override
		@CommandHandler
		public NewsModelDto readById(Long id) {
				return newsService.readById(id);
		}

		@Override
		@CommandHandler
		public NewsModelDto create(NewsModelDto createRequest) {
				return newsService.create(createRequest);
		}

		@Override
		@CommandHandler
		public NewsModelDto update(NewsModelDto updateRequest) {
				NewsModelDto newsReadById = newsService.readById(updateRequest.getId());
				updateRequest.setAuthorId(newsReadById.getAuthorId());
				return newsService.update(updateRequest);
		}

		@Override
		@CommandHandler
		public boolean deleteById(Long id) {
				return newsService.deleteById(id);
		}
}
package com.mjc.school.controller.implementation;

import com.mjc.school.controller.*;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsModelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsControllerImpl implements BaseController<NewsModelDto, NewsModelDto, Long> {
		private final NewsService newsService;
		@Override
		@CommandHandler(id = 1)
		public List<NewsModelDto> readAll() {
				return newsService.readAll();
		}

		@Override
		@CommandHandler(id = 3)
		public NewsModelDto readById(@CommandParam Long id) {
				return newsService.readById(id);
		}

		@Override
		@CommandHandler(id = 5)
		public NewsModelDto create(@CommandBody NewsModelDto createRequest) {
				return newsService.create(createRequest);
		}

		@Override
		@CommandHandler(id = 7)
		public NewsModelDto update(@CommandBody NewsModelDto updateRequest) {
				NewsModelDto newsReadById = newsService.readById(updateRequest.getId());
				updateRequest.setAuthorId(newsReadById.getAuthorId());
				return newsService.update(updateRequest);
		}

		@Override
		@CommandHandler(id = 9)
		public boolean deleteById(@CommandParam Long id) {
				return newsService.deleteById(id);
		}
}
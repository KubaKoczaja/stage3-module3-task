package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.CommandBody;
import com.mjc.school.controller.CommandHandler;
import com.mjc.school.controller.CommandParam;
import com.mjc.school.service.NewsService;
import com.mjc.school.service.dto.NewsModelDto;
import com.mjc.school.service.dto.NewsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class NewsControllerImpl implements BaseController<NewsRequestDto, NewsModelDto, Long> {
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
		public NewsModelDto create(@CommandBody NewsRequestDto createRequest) {
				return newsService.create(createRequest);
		}

		@Override
		@CommandHandler(id = 7)
		public NewsModelDto update(@CommandBody NewsRequestDto updateRequest) {
				return newsService.update(updateRequest);
		}

		@Override
		@CommandHandler(id = 9)
		public boolean deleteById(@CommandParam Long id) {
				return newsService.deleteById(id);
		}

		@CommandHandler(id = 18)
		public Set<NewsModelDto> readNewsByVariousParameters(@CommandBody NewsRequestDto newsRequestDto) {
				return newsService.readNewsByVariousParameters(newsRequestDto);
		}
}
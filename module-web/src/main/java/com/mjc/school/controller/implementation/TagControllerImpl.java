package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.CommandBody;
import com.mjc.school.controller.CommandHandler;
import com.mjc.school.controller.CommandParam;
import com.mjc.school.service.TagService;
import com.mjc.school.service.dto.TagModelDto;
import com.mjc.school.service.dto.TagRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TagControllerImpl implements BaseController<TagRequestDto, TagModelDto, Long> {
		private final TagService tagService;
		@Override
		@CommandHandler(id = 11)
		public List<TagModelDto> readAll() {
				return tagService.readAll();
		}

		@Override
		@CommandHandler(id = 12)
		public TagModelDto readById(@CommandParam Long id) {
				return tagService.readById(id);
		}

		@Override
		@CommandHandler(id = 13)
		public TagModelDto create(@CommandBody TagRequestDto createRequest) {
				return tagService.create(createRequest);
		}

		@Override
		@CommandHandler(id = 14)
		public TagModelDto update(@CommandBody TagRequestDto updateRequest) {
				return tagService.update(updateRequest);
		}

		@Override
		@CommandHandler(id = 15)
		public boolean deleteById(@CommandParam Long id) {
				return tagService.deleteById(id);
		}

		@CommandHandler(id = 17)
		public List<TagModelDto> readByNewsId(@CommandParam Long newsId) {
				return tagService.readByNewsId(newsId);
		}
}

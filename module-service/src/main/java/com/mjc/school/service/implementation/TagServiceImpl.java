package com.mjc.school.service.implementation;

import com.mjc.school.repository.TagModel;
import com.mjc.school.repository.TagRepository;
import com.mjc.school.service.TagService;
import com.mjc.school.service.dto.TagModelDto;
import com.mjc.school.service.dto.TagRequestDto;
import com.mjc.school.service.exception.NoSuchEntityException;
import com.mjc.school.service.mapper.TagMapper;
import com.mjc.school.service.validator.ValidateNewsId;
import com.mjc.school.service.validator.ValidateTagId;
import com.mjc.school.service.validator.ValidateTagsDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
		private final TagRepository tagModelRepository;
		private final TagMapper tagMapper;

		@Override
		public List<TagModelDto> readAll() {
				return tagModelRepository.readAll().stream().map(tagMapper::tagToTagDTO).toList();
		}

		@Override
		@ValidateTagId
		public TagModelDto readById(Long id) {
				return tagMapper
								.tagToTagDTO(tagModelRepository
												.readById(id)
												.orElseThrow(() -> new NoSuchEntityException("No news with such id!")));
		}

		@Override
		@ValidateTagsDetails
		public TagModelDto create(TagRequestDto createRequest) {
				TagModel savedTag = tagMapper.tagRequestToTag(createRequest);
				return tagMapper.tagToTagDTO(tagModelRepository.create(savedTag));
		}

		@Override
		@ValidateTagsDetails
		public TagModelDto update(TagRequestDto updateRequest) {
				TagModel tagFromDatabase = tagModelRepository.readById(updateRequest.getId())
								.orElseThrow(() -> new NoSuchEntityException("No such news!"));
    		tagFromDatabase.setName(updateRequest.getName());
				return tagMapper.tagToTagDTO(tagModelRepository.update(tagFromDatabase));
		}

		@Override
		@ValidateTagId
		public boolean deleteById(Long id) {
				return tagModelRepository.deleteById(id);
		}

		@Override
		@ValidateNewsId
		public List<TagModelDto> readByNewsId(Long newsId) {
				return tagModelRepository.readByNewsId(newsId).stream().map(tagMapper::tagToTagDTO).toList();
		}
}

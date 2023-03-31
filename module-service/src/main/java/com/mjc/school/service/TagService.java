package com.mjc.school.service;

import com.mjc.school.service.dto.TagModelDto;
import com.mjc.school.service.dto.TagRequestDto;

import java.util.List;

public interface TagService extends BaseService<TagRequestDto, TagModelDto, Long>{
		List<TagModelDto> readByNewsId(Long newsId);
}

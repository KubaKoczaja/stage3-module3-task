package com.mjc.school.service.mapper;

import com.mjc.school.repository.TagModel;
import com.mjc.school.service.dto.TagModelDto;
import com.mjc.school.service.dto.TagRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = NewsMapper.class)
public interface TagMapper {
		TagModelDto tagToTagDTO(TagModel tagModel);
		TagModel tagDTOToTag(TagModelDto tagModelDto);
		TagModel tagRequestToTag(TagRequestDto tagRequestDto);
}

package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsModelDto;
import com.mjc.school.service.dto.NewsRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TagMapper.class, AuthorMapper.class})
public interface NewsMapper {
		@Mapping(target = "authorModelDto", source = "authorModel")
		NewsModelDto newsToNewsDTO(NewsModel newsModel);
		@Mapping(target = "authorModel", source = "authorModelDto")
		NewsModel newsDTOToNews(NewsModelDto newsModelDTO);
		NewsModel newsRequestToNews(NewsRequestDto newsRequestDto);
}

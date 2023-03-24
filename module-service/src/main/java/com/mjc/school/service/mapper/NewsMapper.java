package com.mjc.school.service.mapper;

import com.mjc.school.repository.NewsModel;
import com.mjc.school.service.dto.NewsModelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = AuthorMapper.class)
public interface NewsMapper {
		@Mapping(target = "authorId", source = "authorModel.id")
		NewsModelDto newsToNewsDTO(NewsModel newsModel);
		NewsModel newsDTOToNews(NewsModelDto newsModelDTO);
}

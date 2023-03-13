package com.mjc.school.service.mapper;

import com.mjc.school.repository.NewsModel;
import com.mjc.school.service.dto.NewsModelDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface NewsMapper {
		NewsModelDto newsToNewsDTO(NewsModel newsModel);
		NewsModel newsDTOToNews(NewsModelDto newsModelDTO);
}

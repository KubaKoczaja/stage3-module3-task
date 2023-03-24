package com.mjc.school.service.mapper;

import com.mjc.school.repository.AuthorModel;
import com.mjc.school.service.dto.AuthorModelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface AuthorMapper {
		AuthorModelDto authorToAuthorDto(AuthorModel authorModel);
		@Mapping(target = "newsModelList", ignore = true)
		AuthorModel authorDtoToAuthor(AuthorModelDto authorModelDto);
}

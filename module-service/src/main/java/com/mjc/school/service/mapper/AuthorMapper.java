package com.mjc.school.service.mapper;

import com.mjc.school.repository.AuthorModel;
import com.mjc.school.service.dto.AuthorModelDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AuthorMapper {
		AuthorModelDto authorToAuthorDto(AuthorModel authorModel);
		AuthorModel authorDtoToAuthor(AuthorModelDto authorModelDto);
}

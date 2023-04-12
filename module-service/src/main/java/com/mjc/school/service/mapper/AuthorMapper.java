package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.dto.AuthorModelDto;
import com.mjc.school.service.dto.AuthorRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {NewsMapper.class})
@Component
public interface AuthorMapper {
		AuthorModelDto authorToAuthorDto(AuthorModel authorModel);

		AuthorModel authorDtoToAuthor(AuthorModelDto authorModelDto);
		AuthorModel authorRequestToAuthor(AuthorRequestDto authorRequestDto);
}

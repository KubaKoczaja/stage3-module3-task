package com.mjc.school.service;

import com.mjc.school.service.dto.AuthorModelDto;
import com.mjc.school.service.dto.AuthorRequestDto;

public interface AuthorService extends BaseService<AuthorRequestDto, AuthorModelDto, Long>{
		AuthorModelDto readByNewsId(Long newsId);
}

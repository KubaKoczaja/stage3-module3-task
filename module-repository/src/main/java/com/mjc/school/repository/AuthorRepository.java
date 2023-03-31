package com.mjc.school.repository;

public interface AuthorRepository extends BaseRepository<AuthorModel, Long>{
		AuthorModel readByNewsId(Long newsId);
}

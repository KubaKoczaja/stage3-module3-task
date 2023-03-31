package com.mjc.school.repository;

import java.util.List;

public interface TagRepository extends BaseRepository<TagModel, Long> {
		List<TagModel> readByNewsId(Long newsId);
}

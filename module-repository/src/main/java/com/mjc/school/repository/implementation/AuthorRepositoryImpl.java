package com.mjc.school.repository.implementation;

import com.mjc.school.repository.AuthorModel;
import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements BaseRepository<AuthorModel, Long> {
		private final DataSource dataSource;
		@Override
		public List<AuthorModel> readAll() {
				return dataSource.parseAuthorFromFile();
		}

		@Override
		public Optional<AuthorModel> readById(Long id) {
				List<AuthorModel> authorModelList = dataSource.parseAuthorFromFile();
				return Optional.of(authorModelList.get(Math.toIntExact(id)));
		}

		@Override
		public AuthorModel create(AuthorModel entity) {
				dataSource.appendAuthorToFile(entity);
				return entity;
		}

		@Override
		public AuthorModel update(AuthorModel entity) {
				List<AuthorModel> authorsModelList = dataSource.parseAuthorFromFile();
				AuthorModel authorModelToUpdate = authorsModelList.get(Math.toIntExact(entity.getId()));
				authorModelToUpdate.setName(entity.getName());
				authorModelToUpdate.setCreateDate(entity.getCreateDate());
				authorModelToUpdate.setLastUpdateDate(LocalDateTime.now());
				dataSource.saveAllAuthorsToFile(authorsModelList);
				return entity;
		}

		@Override
		public boolean deleteById(Long id) {
				List<AuthorModel> authorModelList = new ArrayList<>(dataSource.parseAuthorFromFile());
				authorModelList.remove(Math.toIntExact(id));
				dataSource.saveAllAuthorsToFile(authorModelList);
				return Boolean.TRUE;
		}

		@Override
		public boolean existById(Long id) {
				return dataSource.parseAuthorFromFile()
								.stream()
								.map(AuthorModel::getId)
								.toList()
								.contains(id);
		}
}

package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.DataSource;
import com.mjc.school.repository.NewsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NewsRepositoryImpl implements BaseRepository<NewsModel, Long> {
		private final DataSource dataSource;
		@Override
		public List<NewsModel> readAll() {
				return dataSource.parseNewsFromFile();
		}

		@Override
		public Optional<NewsModel> readById(Long id) {
				List<NewsModel> newsModelList = dataSource.parseNewsFromFile();
				return Optional.of(newsModelList.get(Math.toIntExact(id)));
		}

		@Override
		public NewsModel create(NewsModel entity) {
				dataSource.appendNewsToFile(entity);
				return entity;
		}

		@Override
		public NewsModel update(NewsModel entity) {
				List<NewsModel> newsModelList = dataSource.parseNewsFromFile();
				NewsModel newsModelToUpdate = newsModelList.get(Math.toIntExact(entity.getId()));
				newsModelToUpdate.setTitle(entity.getTitle());
				newsModelToUpdate.setContent(entity.getContent());
				newsModelToUpdate.setLastUpdateDate(entity.getLastUpdateDate());
				dataSource.saveAllNewsToFile(newsModelList);
				return entity;
		}

		@Override
		public boolean deleteById(Long id) {
				List<NewsModel> newsModelList = new ArrayList<>(dataSource.parseNewsFromFile());
				newsModelList.remove(Math.toIntExact(id));
				dataSource.saveAllNewsToFile(newsModelList);
				return Boolean.TRUE;
		}

		@Override
		public boolean existById(Long id) {
				return dataSource.parseNewsFromFile()
								.stream()
								.map(NewsModel::getId)
								.toList()
								.contains(id);
		}
}
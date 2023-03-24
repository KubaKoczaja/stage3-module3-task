package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.NewsModel;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NewsRepositoryImpl implements BaseRepository<NewsModel, Long> {
		private final Session session;
		@Override
		public List<NewsModel> readAll() {
				return session.createQuery("select n from NewsModel n").getResultList();
		}

		@Override
		public Optional<NewsModel> readById(Long id) {
				return session.createQuery("select n from NewsModel n where n.id = ?1")
								.setParameter(1, id)
								.uniqueResultOptional();
		}

		@Override
		public NewsModel create(NewsModel entity) {
				Transaction transaction = session.getTransaction();
				transaction.begin();
				Long id = (Long) session.save(entity);
				entity.setId(id);
				transaction.commit();
				return entity;
		}

		@Override
		public NewsModel update(NewsModel entity) {
				session.update(entity);
				return entity;
		}

		@Override
		public boolean deleteById(Long id) {
				Transaction transaction = session.getTransaction();
				transaction.begin();
				try {
				session.createQuery("delete from NewsModel where id = ?1")
								.setParameter( 1, id )
								.executeUpdate();
				} catch (RuntimeException e) {
						transaction.rollback();
						return Boolean.FALSE;
				}
				transaction.commit();
				return Boolean.TRUE;
		}
		@Override
		public boolean existById(Long id) {
				return  readById(id).isPresent();
		}
}
package com.mjc.school.repository.implementation;

import com.mjc.school.repository.TagModel;
import com.mjc.school.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {
		private final Session session;
		@Override
		public List<TagModel> readAll() {
				return session.createQuery("select t from TagModel t").getResultList();
		}

		@Override
		public Optional<TagModel> readById(Long id) {
				return session.createQuery("select t from TagModel t where t.id = ?1")
								.setParameter(1, id)
								.uniqueResultOptional();
		}

		@Override
		public TagModel create(TagModel entity) {
				Transaction transaction = session.getTransaction();
				transaction.begin();
				try {
						session.save(entity);
				} catch (Exception e) {
						transaction.rollback();
				}
				transaction.commit();
				return entity;
		}

		@Override
		public TagModel update(TagModel entity) {
				Transaction transaction = session.getTransaction();
				transaction.begin();
				try {
						session.update(entity);
				} catch (Exception e) {
						transaction.rollback();
				}
				transaction.commit();
				return entity;
		}

		@Override
		public boolean deleteById(Long id) {
				Transaction transaction = session.getTransaction();
				transaction.begin();
				try {
						session.createQuery("delete from TagModel where id = ?1")
										.setParameter( 1, id)
										.executeUpdate();
				} catch (Exception e) {
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

		@Override
		public List<TagModel> readByNewsId(Long newsId) {
    return session
        .createQuery("select t from TagModel t join FETCH t.newsModelSet n where n.id = ?1")
        .setParameter(1, newsId)
        .getResultList();
		}
}

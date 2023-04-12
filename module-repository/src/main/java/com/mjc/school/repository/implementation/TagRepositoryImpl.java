package com.mjc.school.repository.implementation;

import com.mjc.school.repository.TagRepository;
import com.mjc.school.repository.model.TagModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {
		@PersistenceUnit
		private final EntityManagerFactory entityManagerFactory;
		@Override
		public List<TagModel> readAll() {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				List<TagModel> tagModelList = entityManager.createQuery("select t from TagModel t", TagModel.class).getResultList();
						entityManager.close();
				return tagModelList;
		}

		@Override
		public Optional<TagModel> readById(Long id) {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				Optional<TagModel> tag =
								entityManager.createQuery("select t from TagModel t where t.id = ?1", TagModel.class)
												.setParameter(1, id)
												.getResultStream()
												.findFirst();
				entityManager.close();
				return tag;
		}

		@Override
		public TagModel create(TagModel entity) {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				try {
						entityManager.persist(entity);
						transaction.commit();
				} catch (RuntimeException e) {
						transaction.rollback();
				}
				entityManager.close();
				return entity;
		}

		@Override
		public TagModel update(TagModel entity) {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				try {
						entityManager.merge(entity);
						transaction.commit();
				} catch (RuntimeException e) {
						transaction.rollback();
				}
				entityManager.close();
				return entity;
		}

		@Override
		public boolean deleteById(Long id) {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				try{
						entityManager.createQuery("delete from TagModel where id = ?1")
										.setParameter( 1, id )
										.executeUpdate();
						transaction.commit();
				} catch (RuntimeException e) {
						transaction.rollback();
						entityManager.close();
						return Boolean.FALSE;
				}
				entityManager.close();
				return Boolean.TRUE;
		}

		@Override
		public boolean existById(Long id) {
				return  readById(id).isPresent();
		}

		@Override
		public List<TagModel> readByNewsId(Long newsId) {
				EntityManager entityManager = entityManagerFactory.createEntityManager();
				List<TagModel> readByNewsIdList = entityManager
								.createQuery("select t from TagModel t join FETCH t.newsModelSet n where n.id =?1", TagModel.class)
								.setParameter(1, newsId)
								.getResultList();
				entityManager.close();
				return readByNewsIdList;
		}
}

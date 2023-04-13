package com.mjc.school.repository.implementation;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository

@AllArgsConstructor
public class NewsRepositoryImpl implements NewsRepository {
		@PersistenceContext
		private EntityManager entityManager;
		@Override
		public List<NewsModel> readAll() {
//				EntityManager entityManager = entityManagerFactory.createEntityManager();
				List<NewsModel>	newsModelList = entityManager.createQuery("select n from NewsModel n", NewsModel.class).getResultList();
				entityManager.close();
				return newsModelList;
		}

		@Override
		public Optional<NewsModel> readById(Long id) {
//				EntityManager entityManager = entityManagerFactory.createEntityManager();
				Optional<NewsModel> newsReadById =
								entityManager.createQuery("select n from NewsModel n where n.id = ?1", NewsModel.class)
												.setParameter(1, id)
												.getResultStream()
												.findFirst();
				entityManager.close();
				return newsReadById;
		}

		@Override
		public NewsModel create(NewsModel entity) {
//				EntityManager entityManager = entityManagerFactory.createEntityManager();
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				try {
						entityManager.persist(entity);
						transaction.commit();
				} catch (RuntimeException e) {
						e.printStackTrace();
						transaction.rollback();
				}
				entityManager.close();
				return entity;
		}

		@Override
		public NewsModel update(NewsModel entity) {
//				EntityManager entityManager = entityManagerFactory.createEntityManager();
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
//				EntityManager entityManager = entityManagerFactory.createEntityManager();
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				try{
						entityManager.createQuery("delete from NewsModel where id = ?1")
										.setParameter( 1, id )
										.executeUpdate();
						transaction.commit();
				} catch (RuntimeException e) {
						transaction.rollback();
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
		public Set<NewsModel> readByTagName(String tagName) {
//				EntityManager entityManager = entityManagerFactory.createEntityManager();
				Set<NewsModel> newsModelSet = entityManager.createQuery("Select n from NewsModel n join n.tagModelSet t where t.name like ?1", NewsModel.class)
								.setParameter(1, "%" + tagName + "%")
								.getResultStream()
								.collect(Collectors.toSet());
				entityManager.close();
				return newsModelSet;
		}

		@Override
		public Set<NewsModel> readByTagId(Long tagId) {
//				EntityManager entityManager = entityManagerFactory.createEntityManager();
    Set<NewsModel> newsModelSet =
        entityManager
            .createQuery(
                "Select n from NewsModel n join n.tagModelSet t where t.id = ?1", NewsModel.class)
            .setParameter(1, tagId)
            .getResultStream()
            .collect(Collectors.toSet());
				entityManager.close();
				return newsModelSet;
		}

		@Override
		public  Set<NewsModel> readByAuthorName(String authorName) {
//				EntityManager entityManager = entityManagerFactory.createEntityManager();
    Set<NewsModel> newsModelSet =
        entityManager
            .createQuery(
                "Select n from NewsModel n join n.authorModel a where a.name = ?1", NewsModel.class)
            .setParameter(1, "%" + authorName + "%")
            .getResultStream()
            .collect(Collectors.toSet());
				entityManager.close();
				return newsModelSet;
		}

		@Override
		public  Set<NewsModel> readByTitle(String title) {
//				EntityManager entityManager = entityManagerFactory.createEntityManager();
    Set<NewsModel> newsModelSet =
        entityManager
            .createQuery("Select n from NewsModel n where n.title like ?1", NewsModel.class)
            .setParameter(1, "%" + title + "%")
            .getResultStream()
            .collect(Collectors.toSet());
				entityManager.close();
				return newsModelSet;
		}

		@Override
		public Set<NewsModel> readByContent(String content) {
//				EntityManager entityManager = entityManagerFactory.createEntityManager();
    Set<NewsModel> newsModelSet =
        entityManager
            .createQuery("Select n from NewsModel n where n.content like ?1", NewsModel.class)
            .setParameter(1, "%" + content + "%")
            .getResultStream()
            .collect(Collectors.toSet());
				entityManager.close();
				return newsModelSet;
		}
}
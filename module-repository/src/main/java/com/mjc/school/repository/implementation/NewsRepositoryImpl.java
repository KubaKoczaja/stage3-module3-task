package com.mjc.school.repository.implementation;

import com.mjc.school.repository.NewsModel;
import com.mjc.school.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class NewsRepositoryImpl implements NewsRepository {
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
				try {
      			session.save(entity);
						transaction.commit();
				} catch (Exception e) {
						transaction.rollback();
				}
				return entity;
		}

		@Override
		public NewsModel update(NewsModel entity) {
				Transaction transaction = session.getTransaction();
				transaction.begin();
				try {
						session.update(entity);
						transaction.commit();
				} catch (Exception e) {
						transaction.rollback();
				}
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

		@Override
		public Set<NewsModel> readByTagName(String tagName) {
				return (Set<NewsModel>) session.createQuery("Select n from NewsModel n join n.tagModelSet t where t.name like ?1")
								.setParameter(1, "%" + tagName + "%")
								.getResultStream()
								.collect(Collectors.toSet());
		}

		@Override
		public Set<NewsModel> readByTagId(Long tagId) {
				return (Set<NewsModel>) session.createQuery("Select n from NewsModel n join n.tagModelSet t where t.id = ?1")
								.setParameter(1, tagId)
								.getResultStream()
								.collect(Collectors.toSet());
		}

		@Override
		public  Set<NewsModel> readByAuthorName(String authorName) {
				return (Set<NewsModel>) session.createQuery("Select n from NewsModel n join n.authorModel a where a.name = ?1")
								.setParameter(1, "%" + authorName + "%")
								.getResultStream()
								.collect(Collectors.toSet());
		}

		@Override
		public  Set<NewsModel> readByTitle(String title) {
				return (Set<NewsModel>) session.createQuery("Select n from NewsModel n where n.title like ?1")
								.setParameter(1, "%" + title + "%")
								.getResultStream()
								.collect(Collectors.toSet());
		}

		@Override
		public Set<NewsModel> readByContent(String content) {
				return (Set<NewsModel>) session.createQuery("Select n from NewsModel n where n.content like ?1")
								.setParameter(1, "%" + content + "%")
								.getResultStream()
								.collect(Collectors.toSet());
		}
}
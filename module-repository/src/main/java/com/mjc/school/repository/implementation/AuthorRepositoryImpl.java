package com.mjc.school.repository.implementation;

import com.mjc.school.repository.AuthorModel;
import com.mjc.school.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {
		private final Session session;
		@Override
		public List<AuthorModel> readAll() {
				Transaction transaction = session.getTransaction();
				transaction.begin();
				List<AuthorModel> authorsList = new ArrayList<>();
				try {
						authorsList = session.createQuery("select a from AuthorModel a").getResultList();
				} catch (Exception e) {
						transaction.rollback();
				}
				transaction.commit();
				return authorsList;
		}
		@Override
		public Optional<AuthorModel> readById(Long id) {
				return session.createQuery("select a from AuthorModel a where a.id = ?1")
								.setParameter(1, id)
								.uniqueResultOptional();
		}
		@Override
		public AuthorModel create(AuthorModel entity) {
				Transaction transaction = session.getTransaction();
				transaction.begin();
				try {
						session.save(entity);
				} catch (RuntimeException e) {
						transaction.rollback();
				}
				transaction.commit();
				return entity;
		}
		@Override
		public AuthorModel update(AuthorModel entity) {
				session.update(entity);
				return entity;
		}
		@Override
		public boolean deleteById(Long id) {
				Transaction transaction = session.getTransaction();
				transaction.begin();
				try{
				session.createQuery("delete from AuthorModel where id = ?1")
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
		public AuthorModel readByNewsId(Long newsId) {
    return (AuthorModel) session.createQuery("Select a FROM AuthorModel a JOIN a.newsModelList n WHERE n.id = ?1")
            .setParameter(1, newsId)
            .uniqueResult();
		}
}
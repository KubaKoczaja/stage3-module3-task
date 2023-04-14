package com.mjc.school.repository.implementation;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.model.AuthorModel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository

@AllArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {
		@PersistenceContext
    private EntityManager entityManager;
		@Override
		public List<AuthorModel> readAll() {
//				EntityManager entityManager = entityManagerFactory.createEntityManager();
				TypedQuery<AuthorModel> query = entityManager.createQuery("select a from AuthorModel a", AuthorModel.class);
				List<AuthorModel> authorsList = query.getResultList();
				entityManager.close();
				return authorsList;
		}
@Override
public Optional<AuthorModel> readById(Long id) {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Optional<AuthorModel> authorById =
						entityManager.createQuery("select a from AuthorModel a where a.id = ?1", AuthorModel.class)
										.setParameter(1, id)
										.getResultStream()
										.findFirst();
		entityManager.close();
		return authorById;
}
@Override
public AuthorModel create(AuthorModel entity) {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
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
public AuthorModel update(AuthorModel entity) {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
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
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		try{
				entityManager.createQuery("delete from AuthorModel where id = ?1")
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
		public AuthorModel readByNewsId(Long newsId) {
//				EntityManager entityManager = entityManagerFactory.createEntityManager();
				AuthorModel authorModel = entityManager
								.createQuery("Select a FROM AuthorModel a JOIN a.newsModelList n WHERE n.id = ?1", AuthorModel.class)
								.setParameter(1, newsId)
								.getSingleResult();
				entityManager.close();
				return authorModel;
		}
}
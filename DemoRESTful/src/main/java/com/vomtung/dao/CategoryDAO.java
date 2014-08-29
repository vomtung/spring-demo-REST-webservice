package com.vomtung.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vomtung.entity.Category;

@Repository("CategoryDAO")
@Transactional
public class CategoryDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void create(Category category) {
		entityManager.persist(category);
	}

	public void delete(Category category) {
		entityManager.remove(entityManager.merge(category));
	}

	public void edit(Category category) {
		entityManager.merge(category);
	}

	public Category findById(Long id) {
		return entityManager.find(Category.class, id);
	}
	
	public List<Category> findAll() {
		Query query = entityManager.createQuery("select category from Category category",Category.class);
		return query.getResultList();
	}
}

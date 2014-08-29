package com.vomtung.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vomtung.dao.CategoryDAO;
import com.vomtung.entity.Category;

public interface CategoryService {

	public CategoryDAO getCategoryDAO();

	public void setCategoryDAO(CategoryDAO categoryDAO);

	public void create(Category category);

	public void delete(Category category);

	public void edit(Category category);

	public Category findById(long id);

	public List<Category> findAll();

}
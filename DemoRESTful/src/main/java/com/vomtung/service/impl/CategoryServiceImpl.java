package com.vomtung.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vomtung.dao.CategoryDAO;
import com.vomtung.entity.Category;
import com.vomtung.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDAO categoryDAO;
	
	/* (non-Javadoc)
	 * @see com.vomtung.service.impl.CategoryService#getCategoryDAO()
	 */
	@Override
	public CategoryDAO getCategoryDAO(){
		return this.categoryDAO;
	};

	/* (non-Javadoc)
	 * @see com.vomtung.service.impl.CategoryService#setCategoryDAO(com.vomtung.dao.CategoryDAO)
	 */
	@Override
	public void setCategoryDAO(CategoryDAO categoryDAO){
		this.categoryDAO=categoryDAO;
	};

	/* (non-Javadoc)
	 * @see com.vomtung.service.impl.CategoryService#create(com.vomtung.entities.Category)
	 */
	@Override
	@Transactional
	public void create(Category category){
		this.categoryDAO.create(category);
	};

	/* (non-Javadoc)
	 * @see com.vomtung.service.impl.CategoryService#delete(com.vomtung.entities.Category)
	 */
	@Override
	public void delete(Category category){
		this.categoryDAO.delete(category);
	};

	/* (non-Javadoc)
	 * @see com.vomtung.service.impl.CategoryService#edit(com.vomtung.entities.Category)
	 */
	@Override
	public void edit(Category category){
		this.categoryDAO.edit(category);
	};

	/* (non-Javadoc)
	 * @see com.vomtung.service.impl.CategoryService#findById(long)
	 */
	@Override
	public Category findById(long id){
		return this.categoryDAO.findById(id);
	};
	
	/* (non-Javadoc)
	 * @see com.vomtung.service.impl.CategoryService#findAll()
	 */
	@Override
	public List<Category> findAll(){
		return this.categoryDAO.findAll();
	};
	
}

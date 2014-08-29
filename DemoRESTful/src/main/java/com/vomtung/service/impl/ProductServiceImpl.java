package com.vomtung.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vomtung.dao.CategoryDAO;
import com.vomtung.dao.ProductDAO;
import com.vomtung.dao.UserDAO;
import com.vomtung.dto.ProductDTO;
import com.vomtung.entity.Category;
import com.vomtung.entity.Product;
import com.vomtung.entity.User;
import com.vomtung.service.ProductService;

@Component
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private UserDAO userDAO;
	
	/* (non-Javadoc)
	 * @see com.vomtung.service.ProductServicei#getProductDAO()
	 */
	public ProductDAO getProductDAO() {
		return productDAO;
	}

	/* (non-Javadoc)
	 * @see com.vomtung.service.ProductServicei#setProductDAO(com.vomtung.dao.ProductDAO)
	 */
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	/* (non-Javadoc)
	 * @see com.vomtung.service.ProductServicei#create(com.vomtung.entities.Product)
	 */
	@Transactional
	public void create(Product product) {
		//Set Owner
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username=auth.getName();
		User user=userDAO.findByUserName(username);
		product.setOwner(user);
		//set category
		Category category=categoryDAO.findById(product.getCategory().getId());
		category.getProducts().add(product);
		categoryDAO.edit(category);
		product.setCategory(category);
		this.productDAO.create(product);
	}

	/* (non-Javadoc)
	 * @see com.vomtung.service.ProductServicei#delete(com.vomtung.entities.Product)
	 */
	@Transactional
	public void delete(Long productId) {
		//Get User
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username=auth.getName();
		User user=userDAO.findByUserName(username);
		//Get Product
		Product product=productDAO.findById(productId);
		//Check if user is owner of the product
		if(user.getId()==product.getOwner().getId()){
		this.productDAO.delete(product);
		}
	}

	/* (non-Javadoc)
	 * @see com.vomtung.service.ProductServicei#edit(com.vomtung.entities.Product)
	 */
	@Transactional
	public void edit(Product product) {
		Category category=categoryDAO.findById(product.getCategory().getId());
		User owner=userDAO.findById(product.getOwner().getId());
		
		product.setOwner(owner);
		product.setCategory(category);
		this.productDAO.edit(product);
	}

	/* (non-Javadoc)
	 * @see com.vomtung.service.ProductServicei#findByUsername(java.lang.String)
	 */
	@Transactional
	public List<Product> findByOwner(Long ownerId) {
		if(ownerId==null){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username=auth.getName();
		User user=userDAO.findByUserName(username);
		ownerId=user.getId();
		}
		return this.productDAO.findByOwner(ownerId);
	}
	
	/* (non-Javadoc)
	 * @see com.vomtung.service.ProductServicei#findById(long)
	 */
	public Product findById(long id) {
		return this.productDAO.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.vomtung.service.ProductServicei#findAll()
	 */
	public List<ProductDTO> findAll() {
		return this.productDAO.findAll();
	}

	public List<Product> findByCategory(long categoryId){
		return productDAO.findByCategory(categoryId);
	}
	
	public List<Product> findFeaturedProduct(){
		return this.productDAO.findByCategoryName("Featured Products");
	}
	
	public List<Product> findNewProduct(){
		return this.productDAO.findByCategoryName("New Newducts");
	}
	
	public List<Product> findPromotionProduct(){
		return this.productDAO.findByCategoryName("Promotions Products");
	}
}

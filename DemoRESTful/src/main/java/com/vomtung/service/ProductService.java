package com.vomtung.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vomtung.dao.ProductDAO;
import com.vomtung.dto.ProductDTO;
import com.vomtung.entity.Product;

public interface ProductService {

	public ProductDAO getProductDAO();

	public void setProductDAO(ProductDAO productDAO);

	@Transactional
	public void create(Product product);

	public void delete(Long productId);

	public void edit(Product account);

	public List<Product> findByOwner(Long ownerId);
	
	public abstract Product findById(long id);

	public abstract List<ProductDTO> findAll();
	
	public List<Product> findByCategory(long categoryId);
	
	public List<Product> findFeaturedProduct();
	
	public List<Product> findNewProduct();
	
	public List<Product> findPromotionProduct();

}
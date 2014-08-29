package com.vomtung.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vomtung.entity.Category;
import com.vomtung.entity.Product;
import com.vomtung.service.CategoryService;
import com.vomtung.service.ProductService;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired(required = true)
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
	String showCategory(ModelMap mm,@PathVariable("categoryId") Long categoryId){
		
		if(mm.get("categories")==null){
			List<Category>categories=this.categoryService.findAll();
	    	mm.addAttribute("categories", categories);
			}
			
			List<Product>promotionProducts=productService.findPromotionProduct();
			mm.addAttribute("promotionProducts", promotionProducts);
			
		return "index";
	}
}

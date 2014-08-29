package com.vomtung.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.vomtung.entity.Category;
import com.vomtung.entity.Product;
import com.vomtung.service.CategoryService;
import com.vomtung.service.ProductService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired(required = true)
	private ProductService productService;
	
	@Autowired(required = true)
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		return "redirect:/user/login.html";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			ModelMap mm) {
		if (error != null) {
			mm.addAttribute("msg", "Invalid username and password! Do you have account?");
		}
		if (logout != null) {
			mm.addAttribute("msg", "You've been logged out successfully.");
		}
		return "user/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap modelMap) {
		return "user/login";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied(ModelMap mm) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			mm.addAttribute("username", userDetail.getUsername());
		}
		return "user/403";
	}
	
	@RequestMapping(value = "/myaccount", method = RequestMethod.GET)
	public String myaccount(ModelMap mm) {
		
		if(mm.get("categories")==null){
			List<Category>categories=this.categoryService.findAll();
	    	mm.addAttribute("categories", categories);
			}
			
			List<Product>promotionProducts=productService.findPromotionProduct();
			mm.addAttribute("promotionProducts", promotionProducts);
			
		return "user/myaccount";
	}

	
	
}
package com.vomtung.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vomtung.entity.Category;
import com.vomtung.service.CategoryService;

@Controller
@RequestMapping(value = "/template")
public class TemplateController {

	@Autowired
	CategoryService categoryService;
	
    public TemplateController() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
    	modelMap.addAttribute("page", "home");
    	List<Category>categories=this.categoryService.findAll();
    	modelMap.addAttribute("categories", categories);
    	return "redirect:/index.html";
    }

    @RequestMapping(value = "/aboutus", method = RequestMethod.GET)
    public String aboutus(ModelMap modelMap) {
    	modelMap.addAttribute("page", "aboutus");
        return "aboutus";
    }

    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String category(ModelMap modelMap) {
    	modelMap.addAttribute("page", "category");
        return "category";
    }

    @RequestMapping(value = "/specials", method = RequestMethod.GET)
    public String specials(ModelMap modelMap) {
    	modelMap.addAttribute("page", "specials");
        return "specials";
    }

    @RequestMapping(value = "/myaccount", method = RequestMethod.GET)
    public String myaccount(ModelMap modelMap) {
    	modelMap.addAttribute("page", "myaccount");
    	
        return "myaccount";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap modelMap) {
    	modelMap.addAttribute("page", "register");
        return "register";
    }
    
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String details(ModelMap modelMap) {
    	modelMap.addAttribute("page", "details");
        return "details";
    }
    
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(ModelMap modelMap) {
    	modelMap.addAttribute("page", "contact");
        return "contact";
    }
    
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(ModelMap modelMap) {
    	modelMap.addAttribute("page", "cart");
        return "cart";
    }
    
}
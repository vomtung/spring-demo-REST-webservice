package com.vomtung.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vomtung.entity.Category;
import com.vomtung.entity.Product;
import com.vomtung.service.CategoryService;
import com.vomtung.service.ProductService;
import com.vomtung.util.Utils;


@Controller
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired(required = true)
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap mm) {
		
		if(mm.get("categories")==null){
		List<Category> categories = this.categoryService.findAll();
		mm.addAttribute("categories", categories);
		}
		List<Product> promotionProducts = productService.findPromotionProduct();
		mm.addAttribute("promotionProducts", promotionProducts);
		
		mm.addAttribute("product", new Product());
		return "product/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@Transactional
	public String add(@Valid @ModelAttribute(value = "product") Product product,BindingResult result,@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		
		if(result.hasErrors()) {
			return "product/add";
		}
		
		File serverFile;
		//image
		if (!file.isEmpty()||(!file.getContentType().startsWith("image"))) {
            try {
                byte[] bytes = file.getBytes();
                
                // Creating the directory to store file
                File dir = new File(Utils.getFolderStoreImage(request.getSession().getServletContext().getRealPath("/")));
                if (!dir.exists())
                    dir.mkdirs();
                // Create the file on server
                serverFile = new File(Utils.getFolderStoreImage(request.getServletContext().getRealPath("/"))+System.currentTimeMillis()+"-"+UUID.randomUUID().toString()+".jpg");
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
            	result.rejectValue("image", "error when upload image");
                return "product/add";
            }
        } else {
        	result.rejectValue("image", "error when upload image");
            return "product/add";
        }
		
		product.setImageName(serverFile.getName());
		productService.create(product);
		
		return "redirect:/index.html";
	}
	
	@RequestMapping(value = "/delete/{productId}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "productId") long id) {
		productService.delete(id);
		return "redirect:/product/myproduct.html";
	}
	
	@RequestMapping(value = "/edit/{productId}", method = RequestMethod.GET)
	public String edit(@PathVariable(value = "productId") long productId,ModelMap mm) {
		mm.addAttribute("product", productService.findById(productId));
		if(mm.get("categories")==null){
			List<Category> categories = this.categoryService.findAll();
			mm.addAttribute("categories", categories);
			}
		return "editproduct";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute(value = "product") Product product) {
		productService.edit(product);
		return "redirect:/index.html";
	}
	
	@RequestMapping(value = "/userproduct/{ownerId}", method = RequestMethod.GET)
	public String userproduct(@RequestParam("ownerId" ) Long ownerId,ModelMap mm) {
		
		List<Product>products=productService.findByOwner(ownerId);
		mm.addAttribute("products", products);
		return "userproduct";
	}
	
	@RequestMapping(value = "/myproduct", method = RequestMethod.GET)
	public String myProduct(ModelMap mm) {
		
		List<Product>products=productService.findByOwner(null);
		mm.addAttribute("products", products);
		return "myproduct";
	}
}

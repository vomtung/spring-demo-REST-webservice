package com.vomtung.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vomtung.dto.ProductDTO;
import com.vomtung.service.ProductService;

@Controller
@RequestMapping(value = "/index")
public class IndexController {

	@Autowired
	ProductService productService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		
	return "index";
	}
	
	@RequestMapping(value = "/sum/{a}/{b}", method = RequestMethod.POST)
	@ResponseBody
	public String sum(@PathVariable(value = "a") int a,
			@PathVariable(value = "b") int b) {
		return String.valueOf(a + b);
	}

	@RequestMapping(value = "/listjson", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> listjson() {
		List<ProductDTO> products=productService.findAll();
		return new ResponseEntity<String>(ProductDTO.toJsonArray(products),HttpStatus.OK); 
	}
}

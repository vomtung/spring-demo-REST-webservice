package com.vomtung.dto;

import java.util.Collection;

import com.vomtung.entity.Category;
import com.vomtung.entity.Product;
import com.vomtung.entity.User;

import flexjson.JSONSerializer;

public class ProductDTO {

	private static JSONSerializer serialiser = null;
	
	private Product product = null;
	
	private long productCount=0;
	
	public ProductDTO(Product product, long productCount) {
		super();
		this.product = product;
		this.productCount = productCount;
	}

	public String getCategory() {
		return product.getCategory().getCategoryName();
	}

	public String getImageName() {
		return product.getImageName();
	}

	public long getId() {
		return product.getId();
	}

	public String getName() {
		return product.getName();
	}

	public String getDescription() {
		return product.getDescription();
	}
	
	public String getManufacture() {
		return product.getManufacture();
	}
	
	public long getPrice() {
		return product.getPrice();
	}

	public String getOwner() {
		return product.getOwner().getUsername();
	}

	public long getProductCount(){
		return this.productCount;
	}
	
	private static synchronized JSONSerializer getSerializer() {
        if (serialiser == null) {
            serialiser = new JSONSerializer();
            serialiser.exclude("*.class");
        }
        return serialiser;
    }
	
	public String toJson() {
        return getSerializer().serialize(this);
    }

    public static String toJsonArray(Collection<ProductDTO> collection) {
        return getSerializer().serialize(collection);
    }
    
}
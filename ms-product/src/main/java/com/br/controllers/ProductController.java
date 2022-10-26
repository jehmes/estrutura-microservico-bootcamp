package com.br.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.models.Product;
import com.br.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/product-service")
@RestController
@Tag(name = "Product Service API")
public class ProductController {
	
	@Autowired
	ProductService ProductService;

	@Operation(description = "Get all products")
	@GetMapping
	private List<Product>getProducts() {
		return ProductService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
		 Optional<Product> Product = Optional.ofNullable(ProductService.getProductById(id));
	        if(Product.isPresent())
	            return new ResponseEntity<Product>(Product.get(), HttpStatus.OK);
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	
	@DeleteMapping("/delete/{id}")
	private void deleteProduct(@PathVariable("id") int id) {
		
		ProductService.delete(id);
	}
	
	@PostMapping
	   public ResponseEntity<Product> Post( @RequestBody  Product Product, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	    ProductService.saveOrUpdate(Product);
	    return new ResponseEntity<Product>(Product, HttpStatus.OK);
	   }

	@PutMapping("/update/{id}")
	private  ResponseEntity<Product> updateProduct(@PathVariable(value = "id") int id, @RequestBody Product newProduct) {
		Optional<Product> oldProduct = Optional.ofNullable(ProductService.getProductById(id));
        if(oldProduct.isPresent()){

        	ProductService.saveOrUpdate(newProduct);
            return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

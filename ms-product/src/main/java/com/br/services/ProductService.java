package com.br.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.br.models.Product;
import com.br.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository ProductRepository;
	
	@Autowired
	private Environment environment;
	
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		products = (List<Product>) ProductRepository.findAll();
		products.stream().forEach(p -> p.setEnvironment(environment.getProperty("local.server.port")));
		return products;
	}
	
	public Product getProductById(int id) {
		return ProductRepository.findById(id).get();
	}
	
	public Product saveOrUpdate(Product product) {
		float preco = product.getPreco();	
		product.setPrecoTotal(preco);
		
		return ProductRepository.save(product);
	}
	
	public void delete(int id) {
		ProductRepository.deleteById(id);
	}
}

package com.br.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.br.models.Product;
import com.br.proxy.CambioProxy;
import com.br.repositories.ProductRepository;
import com.br.response.Cambio;

@Service
public class ProductService {

	@Autowired
	ProductRepository ProductRepository;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CambioProxy proxy;
	
	public List<Product> getAllProducts(String from, String to) {
		List<Product> products = new ArrayList<Product>();
		
		products = (List<Product>) ProductRepository.findAll();
		for (Product p: products) {
			Cambio cambio = proxy.getCambio(p.getPreco(), from, to);
			
			var port = environment.getProperty("local.server.port");
			p.setEnvironment("Book port: " + port + " Cambio port: " + cambio.getEnviroment());
			p.setPreco(cambio.getConvertedValue());
		}
		
		return products;
	}
	
	public Product getProductByIdCurrency(int id, String from, String to) {
		Product p = ProductRepository.findById(id).get();
		
		var port = environment.getProperty("local.server.port");
		
		Cambio cambio = proxy.getCambio(p.getPreco(), from, to);
		p.setEnvironment("Book port: " + port + " Cambio port: " + cambio.getEnviroment());
		p.setPreco(cambio.getConvertedValue());
		
		return p;
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

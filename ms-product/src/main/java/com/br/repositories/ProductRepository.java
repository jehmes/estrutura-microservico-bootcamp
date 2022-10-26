package com.br.repositories;

import org.springframework.data.repository.CrudRepository;

import com.br.models.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}

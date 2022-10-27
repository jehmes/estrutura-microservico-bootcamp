package com.br.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.models.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long>{

	
	Cambio findByFromAndTo(String from, String to);
}

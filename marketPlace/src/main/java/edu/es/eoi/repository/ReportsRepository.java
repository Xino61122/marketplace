package edu.es.eoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.es.eoi.entity.RelacionArticuloPedido;

@Repository
public interface ReportsRepository  extends JpaRepository<RelacionArticuloPedido, Integer>{

	
	
}

package com.algaworks.osticket.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.osticket.domain.model.Cliente;

// componente do spring
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}

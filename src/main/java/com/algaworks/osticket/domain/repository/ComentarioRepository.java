package com.algaworks.osticket.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.osticket.domain.model.Comentario;

//componente do spring
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
}

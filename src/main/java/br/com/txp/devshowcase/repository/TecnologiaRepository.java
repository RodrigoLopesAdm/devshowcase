package br.com.txp.devshowcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.txp.devshowcase.model.Tecnologia;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {

}
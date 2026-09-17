package br.com.txp.devshowcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.txp.devshowcase.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
package br.com.mundovirtual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mundovirtual.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}

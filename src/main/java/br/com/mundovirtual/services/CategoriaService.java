package br.com.mundovirtual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mundovirtual.domain.Categoria;
import br.com.mundovirtual.repositories.CategoriaRepository;
import br.com.mundovirtual.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado!");
		}
		
		return obj.orElse(null);
	}

}

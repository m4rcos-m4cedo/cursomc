package br.com.mundovirtual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mundovirtual.domain.Cliente;
import br.com.mundovirtual.repositories.ClienteRepository;
import br.com.mundovirtual.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado!");
		}
		
		return obj.orElse(null);
	}

}

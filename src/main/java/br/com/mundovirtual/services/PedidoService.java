package br.com.mundovirtual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mundovirtual.domain.Pedido;
import br.com.mundovirtual.repositories.PedidoRepository;
import br.com.mundovirtual.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado!");
		}
		
		return obj.orElse(null);
	}

}

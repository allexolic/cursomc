package com.alexandrecurvelo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandrecurvelo.cursomc.domain.Cliente;
import com.alexandrecurvelo.cursomc.repositories.ClienteRepository;
import com.alexandrecurvelo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> cli = clienteRepository.findById(id);
		
		return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}
}

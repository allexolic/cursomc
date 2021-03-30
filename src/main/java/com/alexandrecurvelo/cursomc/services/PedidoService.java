package com.alexandrecurvelo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandrecurvelo.cursomc.domain.Pedido;
import com.alexandrecurvelo.cursomc.repositories.PedidoRepository;
import com.alexandrecurvelo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> ped = pedidoRepository.findById(id);
		return ped.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}
}

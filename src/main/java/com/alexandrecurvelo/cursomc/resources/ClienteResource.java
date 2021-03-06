package com.alexandrecurvelo.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexandrecurvelo.cursomc.domain.Cliente;
import com.alexandrecurvelo.cursomc.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Integer id){
		Cliente cli = clienteService.buscar(id);
		
		return ResponseEntity.ok().body(cli);
	}
}

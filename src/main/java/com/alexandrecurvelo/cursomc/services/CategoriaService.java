package com.alexandrecurvelo.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandrecurvelo.cursomc.domain.Categoria;
import com.alexandrecurvelo.cursomc.repositories.CategoriaRepository;
import com.alexandrecurvelo.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscarPorId(Integer id){
		Optional<Categoria> cat = categoriaRepository.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
}

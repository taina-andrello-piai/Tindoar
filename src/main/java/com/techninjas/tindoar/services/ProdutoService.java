package com.techninjas.tindoar.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techninjas.tindoar.DTOs.ProdutoDTO;
import com.techninjas.tindoar.models.Produto;
import com.techninjas.tindoar.models.Usuario;
import com.techninjas.tindoar.repositories.ProdutoRepository;
import com.techninjas.tindoar.repositories.UsuarioRepository;
import com.techninjas.tindoar.services.exceptions.DataIntegrityViolationException;
import com.techninjas.tindoar.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private UsuarioRepository userRepository;

	public Produto findById(Integer idProduto) {
		Optional<Produto> obj = repository.findById(idProduto);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! Id: " + idProduto));
	}

	public List<Produto> findAll() {
		return repository.findAll();
	}

	public @Valid Produto create(String username_doador, @Valid Produto obj) {
		obj.setIdProduto(null);
		Usuario user = userRepository.findByUsername(username_doador).orElseThrow(() -> new 
				UsernameNotFoundException("Usuário não encontrado com username: " + username_doador));
		obj.setUser(user);
		return repository.save(obj);
	}

	public Produto update(Integer idProduto, ProdutoDTO objDTO) {
		Produto obj = findById(idProduto);
		return repository.save(obj);
	}

	public void delete(Integer idProduto) {
		findById(idProduto);
		try {
			repository.deleteById(idProduto);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Produto não pode ser deletado! Possui interesses associados!");
		}
		
	}
}

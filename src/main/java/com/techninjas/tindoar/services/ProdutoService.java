package com.techninjas.tindoar.services;

import java.time.LocalDateTime;
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

	public Produto create(String username_doador, Produto obj) {
		obj.setIdProduto(null);
		Usuario user = userRepository.findByUsername(username_doador).orElseThrow(() -> new 
				UsernameNotFoundException("Usuário não encontrado com username: " + username_doador));
		obj.setUser(user);
		LocalDateTime localDateTime = LocalDateTime.now();
		obj.setDataCadastro(localDateTime);
		return repository.save(obj);
	}

	public Produto update(Integer idProduto, Produto obj) {
		Produto newObj = findById(idProduto);
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Produto newObj, Produto obj) {
		newObj.setUser(obj.getUser());
		newObj.setNome(obj.getNome());
		newObj.setEstado(obj.getEstado());
		newObj.setStatus(obj.getStatus());
		newObj.setCategoria(obj.getCategoria());
		newObj.setCidade(obj.getCidade());
		newObj.setFotos(obj.getFotos());
		newObj.setDescricao(obj.getDescricao());
		LocalDateTime localDateTime = LocalDateTime.now();
		newObj.setDataCadastro(localDateTime);
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

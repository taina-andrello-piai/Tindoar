package com.techninjas.tindoar.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techninjas.tindoar.controllers.DoacaoController;
import com.techninjas.tindoar.models.Doacao;
import com.techninjas.tindoar.models.Usuario;
import com.techninjas.tindoar.models.Produto;
import com.techninjas.tindoar.repositories.DoacaoRepository;
import com.techninjas.tindoar.repositories.UsuarioRepository;
import com.techninjas.tindoar.services.exceptions.ObjectNotFoundException;

@Service
public class DoacaoService {
	
	@Autowired
	private DoacaoRepository repository;
	
	@Autowired
    private UsuarioRepository userRepository;
	
	@Autowired
	private ProdutoService produtoService;

	public List<Doacao> findAll() {
		return repository.findAll();
	}

	public Doacao findById(Integer idDoacao) {
		Optional<Doacao> obj = repository.findById(idDoacao);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Ineteresse em doação não encontrado! Id: " 
		+ idDoacao));
	}

	public Doacao create(Integer idProduto, String username_recebedor, Doacao obj) {
		obj.setIdDoacao(null);
		Usuario user = userRepository.findByUsername(username_recebedor).orElseThrow(() -> new 
				UsernameNotFoundException("User not found with username" + username_recebedor));
		obj.setRecebedor(user);
		Produto produto = produtoService.findById(idProduto);
		obj.setProduto(produto);
		return repository.save(obj);
	}

	public void delete(Integer idDoacao) {
		Doacao obj = findById(idDoacao);
		repository.delete(obj);
	}
}

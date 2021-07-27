package com.techninjas.tindoar.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techninjas.tindoar.services.DoacaoService;
import com.techninjas.tindoar.DTOs.DoacaoDTO;
import com.techninjas.tindoar.models.Doacao;
import com.techninjas.tindoar.models.Usuario;
import com.techninjas.tindoar.repositories.UsuarioRepository;

import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins="*",maxAge = 3600)
@RestController
@RequestMapping(value="/interesses")
public class DoacaoController {
	
	@Autowired
	private DoacaoService service;
	
	@Autowired 
	private UsuarioRepository userRepository;
	
	@GetMapping //tem que adicionar os PreAuthorize
	public ResponseEntity<List<DoacaoDTO>> findAll() {
		List<Doacao> list = service.findAll();
		List<DoacaoDTO> listDTO = list.stream().map(obj -> new DoacaoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value="/{idDoacao}") //tem que adicionar os PreAuthorize
	public ResponseEntity<DoacaoDTO> findById( @PathVariable Integer idDoacao) {
		Doacao obj = service.findById(idDoacao);
		DoacaoDTO objDTO = new DoacaoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@PostMapping//tem que adicionar os PreAuthorize
	public ResponseEntity<Doacao> create(@RequestParam(value="produto", defaultValue="0") Integer idProduto, @RequestParam(value = "recebedor", defaultValue = "0") String username_recebedor, 
			@Valid @RequestBody Doacao obj) {
		Usuario user = userRepository.findByUsername(username_recebedor).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o username: " + username_recebedor));
		Doacao newObj = service.create(idProduto,username_recebedor, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idDoacao}").buildAndExpand(newObj.getIdDoacao()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/{idDoacao")
	public ResponseEntity<Void> delete(@PathVariable Integer idDoacao) {
		service.delete(idDoacao);
		return ResponseEntity.noContent().build();
	}
}

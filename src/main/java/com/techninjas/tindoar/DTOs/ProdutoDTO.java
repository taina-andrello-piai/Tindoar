package com.techninjas.tindoar.DTOs;

import java.io.Serializable;
import java.time.LocalDate;

import com.techninjas.tindoar.models.Produto;;

public class ProdutoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer idProduto;
	
	private Integer idUsuarioDoador;
	
	private String nome;
	
	private String estado;
	
	private String status;
	
	private String categoria;
	
	private String cidade;
	
	private String fotos;
	
	private String descricao;
	
	private LocalDate dataCadastro;

	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(Produto obj) {
		super();
		this.idProduto = obj.getIdProduto();
		this.idUsuarioDoador = obj.getUser().getId();
		this.nome = obj.getNome();
		this.estado = obj.getEstado();
		this.status = obj.getStatus();
		this.categoria = obj.getCategoria();
		this.cidade = obj.getCidade();
		this.fotos = obj.getFotos();
		this.descricao = obj.getDescricao();
		this.dataCadastro = obj.getDataCadastro();
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getIdUsuarioDoador() {
		return idUsuarioDoador;
	}

	public void setIdUsuarioDoador(Integer idUsuarioDoador) {
		this.idUsuarioDoador = idUsuarioDoador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(String fotos) {
		this.fotos = fotos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}

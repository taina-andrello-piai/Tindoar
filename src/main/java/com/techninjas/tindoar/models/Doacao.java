package com.techninjas.tindoar.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Doacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDoacao; 
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Usuario doador;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Usuario recebedor;
	
	private LocalDate dataPedido;
	
	private LocalDate match;
	
	private LocalDate dataDoacao;
	
	private int status;
	
	@JsonIgnore
	@JoinColumn
	@ManyToOne
	private Produto produto;

	public Integer getIdDoacao() {
		return idDoacao;
	}

	public void setIdDoacao(Integer idDoacao) {
		this.idDoacao = idDoacao;
	}

	public Usuario getDoador() {
		return doador;
	}

	public void setDoador(Usuario doador) {
		this.doador = doador;
	}

	public Usuario getRecebedor() {
		return recebedor;
	}

	public void setRecebedor(Usuario recebedor) {
		this.recebedor = recebedor;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getMatch() {
		return match;
	}

	public void setMatch(LocalDate match) {
		this.match = match;
	}

	public LocalDate getDataDoacao() {
		return dataDoacao;
	}

	public void setDataDoacao(LocalDate dataDoacao) {
		this.dataDoacao = dataDoacao;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Doacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doacao(Integer idDoacao, Usuario doador, Usuario recebedor, LocalDate dataPedido, LocalDate match,
			LocalDate dataDoacao, int status, Produto produto) {
		super();
		this.idDoacao = idDoacao;
		this.doador = doador;
		this.recebedor = recebedor;
		this.dataPedido = dataPedido;
		this.match = match;
		this.dataDoacao = dataDoacao;
		this.status = status;
		this.produto = produto;
	}
	
	

}

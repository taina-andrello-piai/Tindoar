package com.techninjas.tindoar.DTOs;

import java.time.LocalDate;

import com.techninjas.tindoar.models.Doacao;
import com.techninjas.tindoar.models.Produto;
import com.techninjas.tindoar.models.Usuario;

public class DoacaoDTO {

	private Integer idDoacao;
	
	private Integer idDoador;
	
	private Integer idRecebedor;
	
	private LocalDate dataPedido;
	
	private LocalDate match;
	
	private LocalDate dataDoacao;
	
	private int status;
	
	private Integer idProduto;

	public Integer getIdDoacao() {
		return idDoacao;
	}

	public void setIdDoacao(Integer idDoacao) {
		this.idDoacao = idDoacao;
	}

	public Integer getIdDoador() {
		return idDoador;
	}

	public void setIdDoador(Integer idDoador) {
		this.idDoador = idDoador;
	}

	public Integer getIdRecebedor() {
		return idRecebedor;
	}

	public void setIdRecebedor(Integer idRecebedor) {
		this.idRecebedor = idRecebedor;
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

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public DoacaoDTO(Doacao obj) {
		super();
		this.idDoacao = obj.getIdDoacao();
		this.idDoador = obj.getDoador().getId();
		this.idRecebedor = obj.getRecebedor().getId();
		this.dataPedido = obj.getDataPedido();
		this.match = obj.getMatch();
		this.dataDoacao = obj.getDataDoacao();
		this.status = obj.getStatus();
		this.idProduto = obj.getProduto().getIdProduto();
	}

	public DoacaoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}

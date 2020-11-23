package br.com.bluesoft.desafio.dto;

import java.math.BigDecimal;
import java.util.Optional;

import br.com.bluesoft.desafio.model.Fornecedor;
import lombok.Data;

@Data
public class FornecedorPorPrecoDTO {
	private Optional<Fornecedor> fornecedor;
	private BigDecimal menorPreco;

	public FornecedorPorPrecoDTO() {
	}
	public FornecedorPorPrecoDTO(Optional<Fornecedor> ofNullable, BigDecimal menorPreco2) {
		setFornecedor(ofNullable);
		setMenorPreco(menorPreco2);
	}
	public Optional<Fornecedor> getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Optional<Fornecedor> fornecedor) {
		this.fornecedor = fornecedor;
	}
	public BigDecimal getMenorPreco() {
		return menorPreco;
	}
	public void setMenorPreco(BigDecimal menorPreco) {
		this.menorPreco = menorPreco;
	}
}

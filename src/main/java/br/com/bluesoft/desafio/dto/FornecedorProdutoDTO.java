package br.com.bluesoft.desafio.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class FornecedorProdutoDTO {
	private String cnpj;
	private String nome;
	private List<PrecoPorProdutoDTO> precos = new ArrayList<>();
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<PrecoPorProdutoDTO> getPrecos() {
		return precos;
	}
	public void setPrecos(List<PrecoPorProdutoDTO> precos) {
		this.precos = precos;
	}
}

package br.com.bluesoft.desafio.dto;

import java.io.Serializable;

import lombok.Data;

@Data()
public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nome;
	public ProdutoDTO(String nome2) {
		setNome(nome2);
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}

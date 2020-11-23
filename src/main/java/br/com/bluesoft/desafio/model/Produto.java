package br.com.bluesoft.desafio.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    private String gtin;
    private String nome;
    
    public Produto() {
	
	}
    
    public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Produto(String gtin) {
    	this.gtin = gtin;
    }

}

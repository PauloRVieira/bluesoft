package br.com.bluesoft.desafio.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.bluesoft.desafio.dto.FornecedorProdutoDTO;

@Service
public class ProdutoRestTemplate {

	private static final String URL_API = "https://egf1amcv33.execute-api.us-east-1.amazonaws.com/dev/produto/%s";
	
	@Autowired
	private RestTemplate restTemplate;

	public List<FornecedorProdutoDTO> consultarFornecedores(String gtin) {
		final String urlApi = String.format(URL_API, gtin);
		return restTemplate.exchange(urlApi, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<FornecedorProdutoDTO>>() {}).getBody();
	}
}
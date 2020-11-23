package br.com.bluesoft.desafio;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import br.com.bluesoft.desafio.dto.PedidoItemDTO;
import br.com.bluesoft.desafio.dto.ProdutoDTO;
import br.com.bluesoft.desafio.model.PedidoItem;

@Configuration
@SpringBootApplication
public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		
		Converter<PedidoItem, PedidoItemDTO> conversorPedidoIten = new Converter<PedidoItem, PedidoItemDTO>() {

			@Override
			public PedidoItemDTO convert(MappingContext<PedidoItem, PedidoItemDTO> context) {
				PedidoItem source = context.getSource();

				return new PedidoItemDTO(new ProdutoDTO(source.getId().getProduto().getNome()), source.getQuantidade(), source.getPreco(),
						source.getTotal());
			}

		};
		
		ModelMapper mapper = new ModelMapper();
		mapper.addConverter(conversorPedidoIten);
		
		return mapper;
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}

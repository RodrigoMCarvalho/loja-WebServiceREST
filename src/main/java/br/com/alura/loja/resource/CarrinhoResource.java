package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos")
public class CarrinhoResource {
	
	@GET 
	@Produces(MediaType.APPLICATION_XML) // mostra que esta solicita��o GET ir� produzir 
	public String buscar() {			 // uma resposta no formato XML ao JAX-RS
		Carrinho carrinho = new CarrinhoDAO().busca(1l);
		
		return carrinho.toXML();
	}
}

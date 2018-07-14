package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;

@Path("carrinhos")
public class CarrinhoResource {
	
	@Path("{id}")  //carrinhos/id
	@GET 
	@Produces(MediaType.APPLICATION_XML) // mostra que esta solicita��o GET ir� produzir uma resposta no formato XML ao JAX-RS
	public String buscarPorXML(@PathParam("id") long id) {			
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		
		return carrinho.toXML();
	}
	
	@POST  //no caso, o path ser� "carrinhos"
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo) {
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
		URI uri = URI.create("/carrinhos/" + carrinho.getId());
		
		return Response.created(uri).build(); //o c�digo 201 significa que um recurso foi criado, created.
	}
	
	@Path("{id}/produtos/{produtoId}")
	@DELETE
	public Response removeItemDoProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);
		return Response.ok().build();
	}
	
//	@Path("{id}") @GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public String buscarPorJson(@PathParam("id") long id) {
//		Carrinho carrinho = new CarrinhoDAO().busca(id);
//		
//		return carrinho.toJson();
//	}
}

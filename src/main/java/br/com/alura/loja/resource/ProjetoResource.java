package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

@Path("projetos")
public class ProjetoResource { 
	
//	@Path("{id}") //projetos/id
//	@GET
//	@Produces(MediaType.APPLICATION_XML) // mostra que esta solicitação GET irá produzir uma resposta no formato XML ao JAX-RS
//	public String buscarPorXML(@PathParam("id") long id) {             
//		Projeto projeto = new ProjetoDAO().busca(id);
//		System.out.println(projeto.getNome());
//		return projeto.toXML();
//	}
	
	@Path("{id}") @GET
	@Produces(MediaType.APPLICATION_JSON)
	public String buscarPorJson(@PathParam("id") long id) {
		Projeto projeto = new ProjetoDAO().busca(id);
		
		return projeto.toJson();
	}
}

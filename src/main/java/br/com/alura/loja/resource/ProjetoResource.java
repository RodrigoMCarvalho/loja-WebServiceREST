package br.com.alura.loja.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

@Path("projetos")
public class ProjetoResource { 

	@GET
	@Produces(MediaType.APPLICATION_XML) // mostra que esta solicita��o GET ir� produzir
	public String buscar() {             // uma resposta no formato XML ao JAX-RS
		Projeto projeto = new ProjetoDAO().busca(1l);
		System.out.println(projeto);
		return projeto.toXML();
	}
}
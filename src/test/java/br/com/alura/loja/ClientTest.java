package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Ignore;
import org.junit.Test;

import junit.framework.Assert;

public class ClientTest {
	
	@Test
	@Ignore
	public void testaConexaoComOServidor() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://www.mocky.io");
		String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class); //revolver uma String
		
		System.out.println(conteudo);
		
		Assert.assertTrue(conteudo.contains("Rua Vergueiro"));
	}
	
	@Test
    public void testaQueAConexaoComOServidorFuncionaNoPathDeProjetos() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/");
        String conteudo = target.path("projetos").request().get(String.class);
        Assert.assertTrue(conteudo.contains("<nome>Minha loja"));


    }
}

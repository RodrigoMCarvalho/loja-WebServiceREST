package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import junit.framework.Assert;

public class ClientTest {
	
	private HttpServer server;

	@Before
	public void antesDoTesteStartServer() {
		server = Server.inicializarServer();
	}
	
	@After
	public void depoisDoTesteDerrubaServer() {
		server.stop();
	}
	
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
	@Ignore
    public void testaQueAConexaoComOServidorFuncionaNoPathDeProjetos() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/");
        String conteudo = target.path("projetos").request().get(String.class);
        Assert.assertTrue(conteudo.contains("<nome>Minha loja"));
    }
	
	@Test
    public void testaQueBuscaUmCarrinhoTrazOCarrinhoEsperado() {
		
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/");
        String conteudo = target.path("carrinhos").request().get(String.class);
        Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
        Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
        
        server.stop();
    }
}

package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import junit.framework.Assert;

public class ClientTest {

	private HttpServer server;
	private Client client;
	private WebTarget target;

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

		this.client = ClientBuilder.newClient();
		this.target = client.target("http://www.mocky.io");
		String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class); // devolver uma String
																									
		System.out.println(conteudo);

		Assert.assertTrue(conteudo.contains("Rua Vergueiro"));
	}

	@Test
	@Ignore
	public void testaQueAConexaoComOServidorFuncionaNoPathDeProjetos() {
		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080/");
		String conteudo = target.path("projetos").request().get(String.class);
		Assert.assertTrue(conteudo.contains("<nome>Minha loja"));
	}

	@Test
	@Ignore
	public void testaQueBuscaUmCarrinhoTrazOCarrinhoEsperado() {

		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080/");
		String conteudo = target.path("carrinhos/1").request().get(String.class);
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());

		server.stop();
	}

	@Test
	public void testaMetodoPOST() {
		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080");

		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
		carrinho.setRua("Rua Vergueiro");
		carrinho.setCidade("Sao Paulo");
		String xml = carrinho.toXML();

		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);

		Response response = target.path("/carrinhos").request().post(entity);
		Assert.assertEquals(201, response.getStatus()); //o código 201 significa que um recurso foi criado, created.
		
		String location = response.getHeaderString("Location");
		String conteudo = client.target(location).request().get(String.class);
		Assert.assertTrue(conteudo.contains("Tablet"));
	}


}

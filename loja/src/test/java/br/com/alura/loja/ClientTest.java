package br.com.alura.loja;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;
import junit.framework.Assert;

public class ClientTest {
	
	private HttpServer server;
	private Client client;
	private WebTarget target;
	
	@Before
	public void startaServidor() throws IOException {
		this.server= Servidor.startServidor();		
		
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());
		
		this.client = ClientBuilder.newClient(config);
		this.target= client.target("http://localhost:8080");
		
	}
	
	@After
	public void endServidor() {
		this.server.stop();
		System.out.println("Servidor morreu ;/");
	
	}
	
	@Test
	public void test() {
		Client client = ClientBuilder.newClient();
		WebTarget t= client.target("http://localhost:8080");
		String cont= t.path("/projetos/1").request().get(String.class);
		System.out.println(cont);
		Projeto p= (Projeto) new XStream().fromXML(cont);
		Assert.assertEquals("Minha loja", p.getNome());
					
	}
	
	@Test 
	public void testaPost() {		
		Client client= ClientBuilder.newClient();
		WebTarget t= client.target("http://localhost:8080");
		Carrinho c= new Carrinho();
		c.adiciona(new Produto(317L, "Ipad Pro", 999.10, 1 ));
		c.setCidade("Belo Horizonte");
		c.setRua("Quinta da Boa Vista");
		c.setId(56565L);		
		Entity<Carrinho> entity= Entity.entity(c, MediaType.APPLICATION_XML);
		Response r= t.path("/carrinhos").request().post(entity);
		Assert.assertEquals(201, r.getStatus());
		
		String location = r.getHeaderString("Location");
		Carrinho carrinhoCarregado= client.target(location).request().get(Carrinho.class);
		Assert.assertEquals("Ipad", carrinhoCarregado);
	}	
}

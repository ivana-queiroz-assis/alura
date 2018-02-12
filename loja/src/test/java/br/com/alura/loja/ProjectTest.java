package br.com.alura.loja;

import java.io.IOException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.Projeto;

public class ProjectTest {
	
	private HttpServer server;
	
	@Before
	public void startaServidor() throws IOException {
		this.server= Servidor.startServidor();	
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
	
	

	
	
}



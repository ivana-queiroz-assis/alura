package br.com.alura.loja;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class ClientTest {
	
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
	public void testaConexao() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/carrinhos/1").request().get(String.class);
		System.out.println(conteudo);
		Assert.assertTrue(conteudo.contains("Rua Vergueiro 3185, 8 andar"));
	}
	
	
}

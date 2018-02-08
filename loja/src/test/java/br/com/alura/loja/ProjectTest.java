package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Test;

public class ProjectTest {
	
	@Test
	public void test() {
		Client client = ClientBuilder.newClient();
		WebTarget t= client.target("http://localhost:8080");
		String cont= t.path("/projetos").request().get(String.class);
		System.out.println(cont);
		Assert.assertTrue(cont.contains("Alura"));
	}
	
}



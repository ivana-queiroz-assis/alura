package br.com.alura.loja;

import org.junit.Test;

import junit.framework.Assert;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class ClientTest {
	
	@Test
	public void testaConexao() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://www.mocky.io");
		String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
		System.out.println(conteudo);
		Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));
	}
}

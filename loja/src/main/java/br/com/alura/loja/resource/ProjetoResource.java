package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Projeto;

@Path("projetos")
public class ProjetoResource {
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Projeto busca(@PathParam("id") long id) {		
		Projeto p= new ProjetoDAO().busca(id);
		System.out.println(p.toJson());
		return p;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String newProj) {
		Projeto p= (Projeto) new XStream().fromXML(newProj);
		new ProjetoDAO().adiciona(p);	
		URI uri= URI.create("/projetos/"+p.getId());
		return Response.created(uri).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response removeProjeto(@PathParam("id") long id) {
		new ProjetoDAO().remove(id);
		return Response.ok().build();		
	}
	
	
	
}






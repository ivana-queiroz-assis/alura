package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;


@Path("carrinhos")
public class CarrinhoResource {
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Carrinho busca(@PathParam("id") long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho;
	}	
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(Carrinho carrinho) {       
        new CarrinhoDAO().adiciona(carrinho);
        URI uri = URI.create("/carrinhos/" + carrinho.getId());
        return Response.created(uri).build();
    }
	
	@Path("{id}/produtos/{produtoId}")
	@DELETE	
	public Response removeProduto(@PathParam("id") long id, @PathParam("produtoId") long idProduto) {
		Carrinho c= new CarrinhoDAO().busca(id);
		c.remove(idProduto);
		return Response.ok().build();
	}
	
	@Path("{id}/produtos/{produtoId}/qntidade")
	@PUT
	public Response alteraProduto(String conteudo, @PathParam("id") long id, @PathParam("produtoId") long idProduto) {
		Carrinho c= new CarrinhoDAO().busca(id);
		Produto p= (Produto) new XStream().fromXML(conteudo);
		c.trocaQuantidade(p);
		return Response.ok().build();
	}
}

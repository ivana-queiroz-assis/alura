package br.com.casacodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casacodigo.loja.dao.ProdutoDAO;
import br.com.casacodigo.loja.models.Produto;

@Controller
public class ProdutosController {

	private ProdutoDAO produtoDAO;
	@RequestMapping("/produtos/form")
	public String form() {
		return "produtos/form";
	}
	@RequestMapping("/produtos")
	public String cadastra(Produto produto) {
		System.out.println(produto.toString());
	
		
		return "produtos/ok";
	}
	
}

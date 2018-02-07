package br.com.alura.loja.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.alura.loja.modelo.Projeto;

public class ProjetoDAO {

	private static Map<Long, Projeto> banco = new HashMap<Long,Projeto>();
	private static AtomicLong contador = new AtomicLong(1);
	
	static {
		banco.put(1l, new Projeto("Minha loja",1l, 2014));
		banco.put(2l, new Projeto("Alura", 2l, 2012));
		banco.put(3l, new Projeto("Curso aula 02", 3l, 2018));
	}
	
	public void adiciona(Projeto p) {
		long id= contador.incrementAndGet();
		p.setId(id);
		banco.put(id, p);
	}
	
	public Projeto busca(Long id) {
		return banco.get(id);
	}
	
	public Projeto remove(long id) {
		return banco.remove(id);		
	}
}

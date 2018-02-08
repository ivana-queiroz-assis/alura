package br.com.alura.loja.modelo;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class Projeto {

	private String nome;
	private long id;
	private int anoInicio;
	
	public Projeto(String nome, long id, int anoInicio) {
		this.nome=nome;
		this.id=id;
		this.anoInicio=anoInicio;		
	}
	
	public int getAnoInicio() {
		return anoInicio;
	}
	public void setAnoInicio(int anoInicio) {
		this.anoInicio = anoInicio;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toXML() {		
		return new XStream().toXML(this);
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
	
}

package br.uffrj.comp3.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Consumidor {
	private String nome;
	private int matricula;
	private String anoDeIngresso;
	private Sexo sexo;
	private Titulo titulo;
	private CPF cpf;
	private List<Ticket> tickets;
	
	public Consumidor(String nome, int matricula, String anoDeIngresso, Sexo sexo, Titulo titulo, CPF cpf) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.anoDeIngresso = anoDeIngresso;
		this.sexo = sexo;
		this.titulo = titulo;
		this.cpf = cpf;
	}

	public Consumidor() {
		// TODO Auto-generated constructor stub
	}
		
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getAnoDeIngresso() {
		return anoDeIngresso;
	}

	public void setAnoDeIngresso(String anoDeIngresso) {
		this.anoDeIngresso = anoDeIngresso;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

	public CPF getCpf() {
		return cpf;
	}

	public void setCpf(CPF cpf) {
		this.cpf = cpf;
	}

	public List<Ticket> getTickets() {
		return new ArrayList(tickets);
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}


}

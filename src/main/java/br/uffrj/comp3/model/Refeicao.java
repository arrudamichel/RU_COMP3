package br.uffrj.comp3.model;

import java.util.List;

public class Refeicao {
	private int identificador;
	private String descricao;
	private String opcaoVeg;
	private Turno turno;
	private List<Ticket> tickets;
	
	public Refeicao() {
		// TODO Auto-generated constructor stub
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getOpcaoVeg() {
		return opcaoVeg;
	}

	public void setOpcaoVeg(String opcaoVeg) {
		this.opcaoVeg = opcaoVeg;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	
	

}

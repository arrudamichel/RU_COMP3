package br.uffrj.comp3.rusys.model;

import java.util.List;

public class Refeicao
{
	private int identificador;
	private String descricao;
	private String opcaoVeg;
	private TurnoEnum turno;
	private List<Ticket> tickets;

	public Refeicao()
	{
		// TODO Auto-generated constructor stub
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public String getOpcaoVeg()
	{
		return opcaoVeg;
	}

	public void setOpcaoVeg(String opcaoVeg)
	{
		this.opcaoVeg = opcaoVeg;
	}

	public TurnoEnum getTurno()
	{
		return turno;
	}

	public void setTurno(TurnoEnum turno)
	{
		this.turno = turno;
	}


	public int getIdentificador()
	{
		return identificador;
	}

	public void setIdentificador(int identificador)
	{
		this.identificador = identificador;
	}
}

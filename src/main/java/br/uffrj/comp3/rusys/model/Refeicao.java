package br.uffrj.comp3.rusys.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Refeicao
{
	protected int id;
	protected String descricao;
	protected String opcaoVeg;
	protected TurnoEnum turno;
	protected List<Ticket> tickets = new ArrayList<Ticket>();

	public Refeicao(int id, String descricao) throws Exception
	{
		super();
		this.id = id;
		
		if (descricao == null)
		{
			throw new Exception("model.refeicao.descricao.deve.ser.informado.para.criacao");
		}
		
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao) throws Exception
	{
		if (descricao == null)
		{
			throw new Exception("model.refeicao.descricao.deve.ser.informada");
		}
		
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

	public int getIdentificador()
	{
		return id;
	}

	public int getId()
	{
		return id;
	}

	public List<Ticket> getTickets()
	{
		return tickets;
	}

	public void setTickets(List<Ticket> tickets)
	{
		this.tickets = tickets;
	}
}

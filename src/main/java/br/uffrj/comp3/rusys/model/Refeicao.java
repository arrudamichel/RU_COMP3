package br.uffrj.comp3.rusys.model;

import java.util.ArrayList;
import java.util.List;

public class Refeicao
{
	private int id;
	private String descricao;
	private String opcaoVeg;
	private TurnoEnum turno;
	private List<Ticket> tickets = new ArrayList<Ticket>();

	public Refeicao(int id, String descricao, String opcaoVeg, TurnoEnum turno) throws Exception {
		this.id = id;
	
		if (descricao == null){
			throw new Exception("model.refeicao.descricao.deve.ser.informado.para.criacao");
		} 
		
		this.descricao = descricao;
		this.opcaoVeg = opcaoVeg;
		this.turno = turno;
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
	
	public void setTurno(TurnoEnum tu)
	{
		this.turno = tu;
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

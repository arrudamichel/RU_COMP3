package br.uffrj.comp3.rusys.model.mock;

import java.util.ArrayList;
import java.util.List;

import br.uffrj.comp3.rusys.model.Ticket;
import br.uffrj.comp3.rusys.model.TipoRefeicaoEnum;
import br.uffrj.comp3.rusys.model.TurnoEnum;
import br.uffrj.comp3.rusys.model.interfaces.Refeicao;

public class MockRefeicao implements Refeicao
{
	private Integer id;
	private String descricao;
	private String opcaoVeg;
	private List<Ticket> tickets = new ArrayList<Ticket>();
	private TipoRefeicaoEnum tipo;
	private TurnoEnum turno;

	public MockRefeicao(Integer id, String descricao, String opcaoVeg, List<Ticket> tickets, TipoRefeicaoEnum tipo, TurnoEnum turno) 
	{
		super();
		this.id = id;
		this.descricao = descricao;
		this.opcaoVeg = opcaoVeg;
		this.tickets = tickets;
		this.tipo = tipo;
		this.turno = turno;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public void setTipo(TipoRefeicaoEnum tipo) 
	{
		this.tipo = tipo;
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

	public List<Ticket> getTickets() 
	{
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) 
	{
		this.tickets = tickets;
	}

	public Integer getId() 
	{
		return id;
	}

	public TipoRefeicaoEnum getTipo() 
	{
		return tipo;
	}

	@Override
	public TurnoEnum getTurno() 
	{
		return turno;
	}
}

package br.uffrj.comp3.rusys.model;

import java.util.ArrayList;
import java.util.List;

import br.uffrj.comp3.rusys.util.Constantes;

public class Refeicao
{
	private Integer id;
	private String descricao;
	private String opcaoVeg;
	private List<Ticket> tickets = new ArrayList<Ticket>();
	private TipoRefeicaoEnum tipo;

	public Refeicao(Integer id, String descricao, TipoRefeicaoEnum tipo) throws Exception 
	{
		this.id = id;
	
		if (descricao == null){
			throw new Exception("model.refeicao.descricao.deve.ser.informado.para.criacao");
		} 
		
		this.descricao = descricao;
		
		if (tipo == null)
		{
			throw new Exception("model.refeicao.tipo.deve.ser.informado.para.criacao");
		}
		
		this.tipo = tipo;
	}
	
	public TurnoEnum getTurno()
	{
		if (this.tipo.equals(TipoRefeicaoEnum.DEJEJUM))
		{
			return Constantes.TURNO_DEJEJUM;
		} 
		else if (this.tipo.equals(TipoRefeicaoEnum.ALMOCO))
		{
			return Constantes.TURNO_ALMOCO;
		}
		else if (this.tipo.equals(TipoRefeicaoEnum.JANTAR))
		{
			return Constantes.TURNO_JANTAR;
		}
		else
		{
			return null;
		}	
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

	public Integer getIdentificador()
	{
		return id;
	}

	public Integer getId()
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

	public TipoRefeicaoEnum getTipo() {
		return tipo;
	}
}

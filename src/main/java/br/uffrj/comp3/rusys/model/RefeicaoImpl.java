package br.uffrj.comp3.rusys.model;

import java.util.ArrayList;
import java.util.List;

import br.uffrj.comp3.rusys.model.interfaces.Refeicao;
import br.uffrj.comp3.rusys.service.exceptions.DescricaoNull;
import br.uffrj.comp3.rusys.service.exceptions.TipoNullOrVazio;
import br.uffrj.comp3.rusys.util.Constantes;

public class RefeicaoImpl implements Refeicao
{
	private Integer id;
	private String descricao;
	private String opcaoVeg;
	private List<Ticket> tickets = new ArrayList<Ticket>();
	private TipoRefeicaoEnum tipo;

	public RefeicaoImpl(Integer id, String descricao, TipoRefeicaoEnum tipo) throws Exception 
	{
		this.id = id;
	
		setDescricao(descricao);
		
		if (tipo == null || tipo.equals(TipoRefeicaoEnum.VAZIO))
		{
			throw new TipoNullOrVazio();
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
		if (descricao == null || descricao.trim().length() == 0)
		{
			throw new DescricaoNull();
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

	public Integer getId()
	{
		return id;
	}

	public List<Ticket> getTickets()
	{
		return tickets;
	}

	public TipoRefeicaoEnum getTipo() 
	{
		return tipo;
	}
}

package br.uffrj.comp3.rusys.model;

import br.uffrj.comp3.rusys.model.interfaces.Refeicao;
import br.uffrj.comp3.rusys.service.exceptions.ConsumidorNull;
import br.uffrj.comp3.rusys.service.exceptions.RefeicaoNull;
import br.uffrj.comp3.rusys.service.exceptions.ValorTuplaConsumidorTurnoVazioException;
import br.uffrj.comp3.rusys.util.Constantes;

public class Ticket
{
	private Integer id;
	private boolean pago = false;
	private Float valor;
	private Consumidor consumidor;
	private Refeicao refeicao;
	
	public Ticket(Integer id, boolean pago, Consumidor consumidor, Refeicao refeicao) throws Exception
	{
		super();
		this.id = id;
		this.pago = pago;		
		
		if (consumidor == null)
		{
			throw new ConsumidorNull();
		}
		
		this.consumidor = consumidor;
		
		if (refeicao == null)
		{
			throw new RefeicaoNull();
		}
		
		this.refeicao = refeicao;
		
		if (consumidor instanceof Aluno)
		{		
			this.valor = Constantes.mapaTurnoConsumidor_PRECO.get(refeicao.getTurno().toString() + Aluno.class);
		}
		else if (consumidor instanceof Funcionario)
		{
			this.valor = Constantes.mapaTurnoConsumidor_PRECO.get(refeicao.getTurno().toString() + Funcionario.class);
		}
		
		if (this.valor == null)
		{
			throw new ValorTuplaConsumidorTurnoVazioException();
		}
	}

	public boolean isPago()
	{
		return pago;
	}

	public void setPago(boolean pago)
	{
		this.pago = pago;
	}

	public float getValor()
	{
		return this.valor;
	}
	
	public Consumidor getConsumidor()
	{
		return consumidor;
	}

	public Refeicao getRefeicao()
	{
		return refeicao;
	}

	public Integer getId()
	{
		return id;
	}
	
	public boolean getPago()
	{
		return this.pago;
	}
}

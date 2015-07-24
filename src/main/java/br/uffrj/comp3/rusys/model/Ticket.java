package br.uffrj.comp3.rusys.model;

import br.uffrj.comp3.rusys.util.Constantes;

public class Ticket
{
	private int id;
	private boolean pago = false;
	private float valor;
	private Consumidor consumidor;
	private Refeicao refeicao;
	
	public Ticket(int id, boolean pago, Consumidor consumidor, Refeicao refeicao) throws Exception
	{
		super();
		this.id = id;
		this.pago = pago;		
		
		if (consumidor instanceof Aluno)
		{		
			this.valor = Constantes.mapaTurnoConsumidor_PRECO.get(refeicao.getTurno().toString() + Aluno.class);
		}
		else if (consumidor instanceof Funcionario)
		{
			this.valor = Constantes.mapaTurnoConsumidor_PRECO.get(refeicao.getTurno().toString() + Funcionario.class);
		}
		
		if (this.valor == 0)
		{
			throw new Exception("model.ticket.valor.para.essa.tupla.cosumidor.refeicao.nao.existe");
		}
		
		if (consumidor == null)
		{
			throw new Exception("model.ticket.consumidor.deve.ser.informado.para.criacao");
		}
		
		this.consumidor = consumidor;
		
		if (refeicao == null)
		{
			throw new Exception("model.ticket.refeicao.deve.ser.informado.para.criacao");
		}
		
		this.refeicao = refeicao;
	}

	public boolean isPago()
	{
		return pago;
	}

	public void setPago(boolean pago)
	{
		this.pago = pago;
	}

	public void setValor(String turno)
	{
		if (this.consumidor instanceof Aluno)
		{		
			this.valor = Constantes.mapaTurnoConsumidor_PRECO.get(turno + Aluno.class);
		}
		else if (this.consumidor instanceof Funcionario)
		{
			this.valor = Constantes.mapaTurnoConsumidor_PRECO.get(turno + Funcionario.class);
		}
	}

	public float getValor()
	{
		return this.valor;
	}
	
	public Consumidor getConsumidor()
	{
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) throws Exception
	{
		if (consumidor == null)
		{
			throw new Exception("model.ticket.consumidor.deve.ser.informado");
		}

		this.consumidor = consumidor;
	}

	public Refeicao getRefeicao()
	{
		return refeicao;
	}

	public void setRefeicao(Refeicao refeicao) throws Exception
	{
		if (refeicao == null)
		{
			throw new Exception("model.ticket.refeicao.deve.ser.informada");
		}
		
		this.refeicao = refeicao;
	}

	public int getId()
	{
		return id;
	}
	
	public boolean getPago()
	{
		return this.pago;
	}
}

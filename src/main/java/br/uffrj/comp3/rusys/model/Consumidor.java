package br.uffrj.comp3.rusys.model;

import java.util.ArrayList;
import java.util.List;

import br.uffrj.comp3.rusys.util.Util;

public abstract class Consumidor
{
	private int id;
	private String nome;
	private int matricula;
	private String anoDeIngresso;
	private SexoEnum sexo;
	private TituloEnum titulo;
	private String cpf;
	private List<Ticket> tickets;

	public Consumidor(int id, String nome, int matricula, String anoDeIngresso) throws Exception
	{
		super();
		this.id = id;
		
		if (nome == null)
		{
			throw new Exception("model.consumidor.nome.deve.ser.informado.para.criacao");
		}
		
		this.nome = nome;
		this.matricula = matricula;
		
		if (anoDeIngresso == null)
		{
			throw new Exception("model.consumidor.anoDeIngresso.deve.ser.informado.para.criacao");
		}
		
		this.anoDeIngresso = anoDeIngresso;
		this.tickets = new ArrayList<>();
	}

	public List<Ticket> getTickets()
	{
		return tickets;
	}

	public void setTickets(List<Ticket> tickets)
	{
		this.tickets = tickets;
	}

	public int getId()
	{
		return id;
	}
	
	public void  setId(int id)
	{
		this.id = id;
	}
	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public int getMatricula()
	{
		return matricula;
	}

	public void setMatricula(int matricula)
	{
		this.matricula = matricula;
	}

	public String getAnoDeIngresso()
	{
		return anoDeIngresso;
	}

	public void setAnoDeIngresso(String anoDeIngresso)
	{
		this.anoDeIngresso = anoDeIngresso;
	}

	public SexoEnum getSexo()
	{
		return sexo;
	}

	public void setSexo(SexoEnum sexo)
	{
		this.sexo = sexo;
	}

	public TituloEnum getTitulo()
	{
		return titulo;
	}

	public void setTitulo(TituloEnum titulo)
	{
		this.titulo = titulo;
	}

	public String getCpf()
	{
		return cpf;
	}

	public void setCpf(String cpf) throws Exception
	{
		//if (!Util.valida(cpf))
		//{
		//	throw new Exception("cpf.informado.invalido");
		//}
		
//		TODO testar se é unico fazendo uma consulda com um handler
		
		this.cpf = cpf;
	}
}

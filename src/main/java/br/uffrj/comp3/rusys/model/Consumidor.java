package br.uffrj.comp3.rusys.model;

import java.util.ArrayList;
import java.util.List;

import br.uffrj.comp3.rusys.service.exceptions.AnoDeIngressoNull;
import br.uffrj.comp3.rusys.service.exceptions.InvalidCpfException;
import br.uffrj.comp3.rusys.service.exceptions.NomeNull;
import br.uffrj.comp3.rusys.util.Util;

public abstract class Consumidor
{
	private Integer id;
	private String nome;
	private Integer matricula;
	private Integer anoDeIngresso;
	private SexoEnum sexo;
	private TituloEnum titulo;
	private String cpf;
	private List<Ticket> tickets;

	public Consumidor(Integer id, String nome, Integer matricula, Integer anoDeIngresso2) throws Exception
	{
		super();
		this.id = id;
		
		setNome(nome);
		
		this.matricula = matricula;
		
		setAnoDeIngresso(anoDeIngresso2);
		
		this.tickets = new ArrayList<Ticket>();
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
	
	public void  setId(Integer id)
	{
		this.id = id;
	}
	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome) throws NomeNull
	{
		if (nome == null || nome.trim().length() == 0) 
		{
			throw new NomeNull();
		}
		
		this.nome = nome;
	}

	public Integer getMatricula()
	{
		return matricula;
	}

	public void setMatricula(Integer matricula)
	{
		this.matricula = matricula;
	}

	public Integer getAnoDeIngresso()
	{
		return anoDeIngresso;
	}

	public void setAnoDeIngresso(Integer anoDeIngresso) throws AnoDeIngressoNull
	{
		if (anoDeIngresso == null) 
		{
			throw new AnoDeIngressoNull();
		}
		
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
		if (!Util.valida(cpf))
		{
			throw new InvalidCpfException();
		}
			
		this.cpf = cpf;
	}
}

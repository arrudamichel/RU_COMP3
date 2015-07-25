package br.uffrj.comp3.rusys.model;

import java.util.ArrayList;
import java.util.List;

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
		
		if (nome == null)
		{
			throw new Exception("model.consumidor.nome.deve.ser.informado.para.criacao");
		}
		
		this.nome = nome;
		this.matricula = matricula;
		
		if (anoDeIngresso2 == null)
		{
			throw new Exception("model.consumidor.anoDeIngresso.deve.ser.informado.para.criacao");
		}
		
		this.anoDeIngresso = anoDeIngresso2;
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

	public void setNome(String nome)
	{
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

	public void setAnoDeIngresso(Integer anoDeIngresso)
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
		if (!Util.valida(cpf))// TODO: COMENTANDO PRA FICAR MAIS FACIL TESTAR
		{
			throw new Exception("cpf.informado.invalido");
		}
			
		this.cpf = cpf;
	}
}

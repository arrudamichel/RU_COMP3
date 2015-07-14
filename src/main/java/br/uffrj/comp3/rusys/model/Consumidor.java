package br.uffrj.comp3.rusys.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Consumidor
{
	private String nome;
	private int matricula;
	private String anoDeIngresso;
	private SexoEnum sexo;
	private TituloEnum titulo;
	private String cpf;
	private List<Ticket> tickets;

	public Consumidor(String nome, int matricula, String anoDeIngresso, SexoEnum sexo, TituloEnum titulo, String cpf)
	{
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.anoDeIngresso = anoDeIngresso;
		this.sexo = sexo;
		this.titulo = titulo;
		this.cpf = cpf;
	}

	public Consumidor()
	{
		// TODO Auto-generated constructor stub
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

	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}



}

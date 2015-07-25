package br.uffrj.comp3.rusys.model;

import br.uffrj.comp3.rusys.service.exceptions.DepartamentoNull;
import br.uffrj.comp3.rusys.service.exceptions.SiglaNull;

public class Curso
{
	private Integer id;
	private String nome;
	private String sigla;
	private Departamento departamento;

	public Curso(Integer identificador, String nome, String sigla, Departamento departamento) throws Exception
	{
		super();
		this.id = identificador;
		this.nome = nome;
		
		setSigla(sigla);
		setDepartamento(departamento);
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getSigla()
	{
		return sigla;
	}

	public void setSigla(String sigla) throws Exception
	{
		if (sigla == null || sigla.trim().length() == 0)
		{
			throw new SiglaNull();
		}
		
		this.sigla = sigla;
	}

	public Departamento getDepartamento()
	{
		return departamento;
	}

	public void setDepartamento(Departamento departamento) throws Exception
	{
		if (departamento == null)
		{
			throw new DepartamentoNull();
		}
		
		this.departamento = departamento;
	}

	public Integer getIdentificador()
	{
		return id;
	}

	public Integer getId()
	{
		return id;
	}
}

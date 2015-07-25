package br.uffrj.comp3.rusys.model;

import br.uffrj.comp3.rusys.service.exceptions.SiglaNull;

public class Departamento
{
	private Integer id;
	private String nome;
	private String sigla;

	public Departamento(Integer id, String nome, String sigla) throws Exception
	{
		super();
		this.id = id;
		this.nome = nome;
		
		setSigla(sigla);
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

	public Integer getIdentificador()
	{
		return id;
	}

	public void setIdentificador(Integer identificador)
	{
		this.id = identificador;
	}

	@Override
	public int hashCode()
	{
		final Integer prime = 31;
		Integer result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (nome == null)
		{
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sigla == null)
		{
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

	public Integer getId()
	{
		return id;
	}
}

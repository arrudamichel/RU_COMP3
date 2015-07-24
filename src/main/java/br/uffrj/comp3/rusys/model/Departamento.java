package br.uffrj.comp3.rusys.model;

import java.util.ArrayList;

import br.uffrj.comp3.rusys.model.vo.DepartamentoVO;
import br.uffrj.comp3.rusys.service.DepartamentoHandler;

public class Departamento
{
	private int id;
	private String nome;
	private String sigla;

	public Departamento(int id, String nome, String sigla) throws Exception
	{
		super();
		this.id = id;
		this.nome = nome;
		
		if (sigla == null)
		{
			throw new Exception("model.departamento.sigla.deve.ser.informado.para.criacao");
		}
		
		this.sigla = sigla;
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
		if (sigla == null)
		{
			throw new Exception("model.departamento.sigla.deve.ser.informada");
		}
		
		this.sigla = sigla;
	}

	public int getIdentificador()
	{
		return id;
	}

	public void setIdentificador(int identificador)
	{
		this.id = identificador;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
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

	public int getId()
	{
		return id;
	}
	
	public static boolean isSILGAunica(String sigla) throws Exception
	{
		DepartamentoVO departamentoVO = new DepartamentoVO();
		departamentoVO.setSigla(sigla);
		
		ArrayList<Departamento> departamentos = (ArrayList<Departamento>) DepartamentoHandler.recuperarDepartamentos(departamentoVO);
		
		return departamentos!=null && !departamentos.isEmpty();
	}
}

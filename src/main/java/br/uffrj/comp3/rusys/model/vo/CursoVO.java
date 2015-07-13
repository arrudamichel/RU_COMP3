package br.uffrj.comp3.rusys.model.vo;

import br.uffrj.comp3.rusys.model.Departamento;

public class CursoVO
{
	private int identificador;
	private String nome;
	private String sigla;
	private int departamento;
	private String nomeDepartamento;
	
	public int getIdentificador()
	{
		return identificador;
	}
	public void setIdentificador(int identificador)
	{
		this.identificador = identificador;
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
	public void setSigla(String sigla)
	{
		this.sigla = sigla;
	}
	public int getDepartamento()
	{
		return departamento;
	}
	public void setDepartamento(int departamento)
	{
		this.departamento = departamento;
	}
	public String getNomeDepartamento()
	{
		return nomeDepartamento;
	}
	public void setNomeDepartamento(String nomeDepartamento)
	{
		this.nomeDepartamento = nomeDepartamento;
	}
}

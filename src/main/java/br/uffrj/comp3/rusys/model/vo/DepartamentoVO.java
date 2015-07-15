package br.uffrj.comp3.rusys.model.vo;

public class DepartamentoVO
{
	private int id;
	private int identificador;
	private String nome;
	private String sigla;

	
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
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
}

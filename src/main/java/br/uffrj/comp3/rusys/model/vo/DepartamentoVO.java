package br.uffrj.comp3.rusys.model.vo;

public class DepartamentoVO
{
	private Integer id;
	private Integer identificador;
	private String nome;
	private String sigla;

	
	public Integer getIdentificador()
	{
		return identificador;
	}
	public void setIdentificador(Integer identificador)
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
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
}

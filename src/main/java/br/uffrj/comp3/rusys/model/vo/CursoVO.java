package br.uffrj.comp3.rusys.model.vo;

public class CursoVO
{
	private Integer id;
	private String nome;
	private String sigla;
	private Integer departamento;
	private String nomeDepartamento;
	
	public Integer getIdentificador()
	{
		return id;
	}
	public void setIdentificador(Integer identificador)
	{
		this.id = identificador;
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
	public Integer getDepartamento()
	{
		return departamento;
	}
	public void setDepartamento(Integer departamento)
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
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
}

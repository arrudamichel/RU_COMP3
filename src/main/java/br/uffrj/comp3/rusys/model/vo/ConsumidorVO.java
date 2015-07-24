package br.uffrj.comp3.rusys.model.vo;

public class ConsumidorVO
{
	private Integer id;
	private String nome;
	private Integer matricula;
	private Integer anoDeIngresso;
	private String sexo;
	private String titulo;
	private String cpf;
	private Integer curso;
	private Integer departamento;
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
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
	public String getSexo()
	{
		return sexo;
	}
	public void setSexo(String sexo)
	{
		this.sexo = sexo;
	}
	public String getTitulo()
	{
		return titulo;
	}
	public void setTitulo(String titulo)
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
	public Integer getCurso()
	{
		return curso;
	}
	public void setCurso(Integer curso)
	{
		this.curso = curso;
	}
	public Integer getDepartamento()
	{
		return departamento;
	}
	public void setDepartamento(Integer departamento)
	{
		this.departamento = departamento;
	}
}

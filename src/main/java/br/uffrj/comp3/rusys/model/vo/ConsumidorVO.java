package br.uffrj.comp3.rusys.model.vo;

public class ConsumidorVO
{
	private int id;
	private String nome;
	private int matricula;
	private String anoDeIngresso;
	private String sexo;
	private String titulo;
	private String cpf;
	private int curso;
	private int departamento;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
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
	public int getCurso()
	{
		return curso;
	}
	public void setCurso(int curso)
	{
		this.curso = curso;
	}
	public int getDepartamento()
	{
		return departamento;
	}
	public void setDepartamento(int departamento)
	{
		this.departamento = departamento;
	}
}

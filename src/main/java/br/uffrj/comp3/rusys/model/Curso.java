package br.uffrj.comp3.rusys.model;

public class Curso
{
	private int id;
	private String nome;
	private String sigla;
	private Departamento departamento;

	public Curso(int identificador, String nome, String sigla, Departamento departamento) throws Exception
	{
		super();
		this.id = identificador;
		this.nome = nome;
		
		if (sigla == null)
		{
			throw new Exception("model.curso.sigla.deve.ser.informado.para.criacao");
		}
		
		this.sigla = sigla; 
		
		if (departamento == null)
		{
			throw new Exception("model.curso.departamento.deve.ser.informado.para.criacao");
		}
		
		this.departamento = departamento; 
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
			throw new Exception("model.curso.sigla.deve.ser.informada");
		}
		
		 //TODO testar se é unica no banco com uma consulta ao hadler
		
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
			throw new Exception("model.curso.departamento.deve.ser.informado.para.criacao");
		}
		
		this.departamento = departamento;
	}

	public int getIdentificador()
	{
		return id;
	}

	public int getId()
	{
		return id;
	}
}

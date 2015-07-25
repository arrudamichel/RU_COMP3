package br.uffrj.comp3.rusys.model;

public class Aluno extends Consumidor
{
	private Curso curso;
	
	public Aluno(Integer id, String nome, Integer matricula, Integer anoDeIngresso, Curso curso) throws Exception
	{
		super(id, nome, matricula, anoDeIngresso);
		
		if (curso == null)
		{
			throw new Exception("model.aluno.curso.deve.ser.informado.para.criacao");
		}
		
		this.curso = curso;
	}

	public Curso getCurso()
	{
		return this.curso;
	}

	public void setCurso(Curso curso) throws Exception
	{
		if (curso == null)
		{
			throw new Exception("model.aluno.curso.deve.ser.informado.para.criacao");
		}
		this.curso = curso;
	}
}

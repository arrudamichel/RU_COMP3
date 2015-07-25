package br.uffrj.comp3.rusys.model;

import br.uffrj.comp3.rusys.service.exceptions.CursoNull;

public class Aluno extends Consumidor
{
	private Curso curso;
	
	public Aluno(Integer id, String nome, Integer matricula, Integer anoDeIngresso, Curso curso) throws Exception
	{
		super(id, nome, matricula, anoDeIngresso);
		
		setCurso(curso);
	}

	public Curso getCurso()
	{
		return this.curso;
	}

	public void setCurso(Curso curso) throws Exception
	{
		if (curso == null)
		{
			throw new CursoNull();
		}
		this.curso = curso;
	}
}

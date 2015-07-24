package br.uffrj.comp3.rusys.model;

import java.util.ArrayList;

import br.uffrj.comp3.rusys.model.vo.CursoVO;
import br.uffrj.comp3.rusys.service.CursoHandler;

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
	
	public static boolean isSILGAunica(String sigla) throws Exception
	{
		CursoVO cursoVO = new CursoVO();
		cursoVO.setSigla(sigla);
		
		ArrayList<Curso> cursos = (ArrayList<Curso>) CursoHandler.recuperarCursos(cursoVO);
		
		return cursos!=null && !cursos.isEmpty();
	}
}

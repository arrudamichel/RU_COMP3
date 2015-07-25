package br.uffrj.comp3.rusys.model;

import br.uffrj.comp3.rusys.service.exceptions.DepartamentoNull;

public class Funcionario extends Consumidor
{
	private Departamento departamento;
	
	public Funcionario(Integer id, String nome, Integer matricula, Integer anoDeIngresso, Departamento departamento) throws Exception
	{
		super(id, nome, matricula, anoDeIngresso);
		
		setDepartamento(departamento);
	}

	public Departamento getDepartamento()
	{
		return departamento;
	}

	public void setDepartamento(Departamento departamento) throws Exception
	{
		if (departamento == null)
		{
			throw new DepartamentoNull();
		}
		
		this.departamento = departamento;
	}
}

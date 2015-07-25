package br.uffrj.comp3.rusys.model;

public class Funcionario extends Consumidor
{
	private Departamento departamento;
	
	public Funcionario(Integer id, String nome, Integer matricula, Integer anoDeIngresso, Departamento departamento) throws Exception
	{
		super(id, nome, matricula, anoDeIngresso);
		
		if (departamento == null)
		{
			throw new Exception("model.funcionario.departamento.deve.ser.informado.para.criacao");
		}
		
		this.departamento = departamento;
	}

	public Departamento getDepartamento()
	{
		return departamento;
	}

	public void setDepartamento(Departamento departamento) throws Exception
	{
		if (departamento == null)
		{
			throw new Exception("model.funcionario.departamento.deve.ser.informado");
		}
		
		this.departamento = departamento;
	}
}

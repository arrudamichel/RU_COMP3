package br.uffrj.comp3.rusys.model;

public class Funcionario extends Consumidor
{
	private Departamento departamento;

	public Funcionario(String nome, int matricula, String anoDeIngresso, SexoEnum sexo, TituloEnum titulo, String cpf, Departamento departamento)
	{
		super(nome, matricula, anoDeIngresso, sexo, titulo, cpf);
		this.departamento = departamento;
	}

	public Funcionario()
	{
		// TODO Auto-generated constructor stub
	}

	public Departamento getDepartamento()
	{
		return departamento;
	}

	public void setDepartamento(Departamento departamento)
	{
		this.departamento = departamento;
	}
}

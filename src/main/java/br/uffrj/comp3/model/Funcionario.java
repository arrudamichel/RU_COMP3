package br.uffrj.comp3.model;

public class Funcionario extends Consumidor {
	private Funcionario funcionario;

	public Funcionario(String nome, int matricula, String anoDeIngresso, Sexo sexo, Titulo titulo, CPF cpf,
			Funcionario funcionario) {
		super(nome, matricula, anoDeIngresso, sexo, titulo, cpf);
		this.funcionario = funcionario;
	}

	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

}

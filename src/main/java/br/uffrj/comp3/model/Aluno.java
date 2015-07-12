package br.uffrj.comp3.model;

public class Aluno extends Consumidor {
	private Curso curso;
	
	public Aluno(String nome, int matricula, String anoDeIngresso, Sexo sexo, Titulo titulo, CPF cpf, Curso curso) {
		super(nome, matricula, anoDeIngresso, sexo, titulo, cpf);				
		
		this.curso = curso;
	}

	public Aluno() {
		// TODO Auto-generated constructor stub
	}
	
	public Curso getCurso(){
		return this.curso;
	}
	
	public void setCurso(Curso curso){
		this.curso = curso;
	}
}

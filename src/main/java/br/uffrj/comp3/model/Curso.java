package br.uffrj.comp3.model;

public class Curso {
	private int identificador;
	private String nome;
	private String sigla;
	private Departamento departamento;

	public Curso() {
		// TODO Auto-generated constructor stub
	}

	public Curso(String nome, String sigla, Departamento departamento) {
		super();
		this.nome = nome;
		this.sigla = sigla;
		this.departamento = departamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

}

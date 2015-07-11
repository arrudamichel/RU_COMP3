package br.uffrj.comp3.model;

public abstract class Consumidor {
	private String nome;
	private int matricula;
	private String anoDeIngresso;
	private Sexo sexo;
	private Titulo titulo;
	private CPF cpf;
	
	public Consumidor(String nome, int matricula, String anoDeIngresso, Sexo sexo, Titulo titulo, CPF cpf) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.anoDeIngresso = anoDeIngresso;
		this.sexo = sexo;
		this.titulo = titulo;
		this.cpf = cpf;
	}

	public Consumidor() {
		// TODO Auto-generated constructor stub
	}
		
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getAnoDeIngresso() {
		return anoDeIngresso;
	}

	public void setAnoDeIngresso(String anoDeIngresso) {
		this.anoDeIngresso = anoDeIngresso;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

	public CPF getCpf() {
		return cpf;
	}

	public void setCpf(CPF cpf) {
		this.cpf = cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anoDeIngresso == null) ? 0 : anoDeIngresso.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + matricula;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consumidor other = (Consumidor) obj;
		if (anoDeIngresso == null) {
			if (other.anoDeIngresso != null)
				return false;
		} else if (!anoDeIngresso.equals(other.anoDeIngresso))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (matricula != other.matricula)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sexo != other.sexo)
			return false;
		if (titulo != other.titulo)
			return false;
		return true;
	}


}

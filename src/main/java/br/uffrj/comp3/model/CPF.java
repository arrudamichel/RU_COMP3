package br.uffrj.comp3.model;

public class CPF {
	private String cpf;

	public CPF(String cpf) {
		super();
		this.cpf = cpf;
	}
	
	private boolean valida(String cpf) {
		return true;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}

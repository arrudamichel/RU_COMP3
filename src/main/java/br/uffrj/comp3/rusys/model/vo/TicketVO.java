package br.uffrj.comp3.rusys.model.vo;

public class TicketVO {
	private boolean pago;
	private float valor;
	private String nome;
	private int matricula;
	
	public TicketVO(boolean pago, float valor, String nome, int matricula) {
		super();
		this.pago = pago;
		this.valor = valor;
		this.nome = nome;
		this.matricula = matricula;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
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
	
		
}


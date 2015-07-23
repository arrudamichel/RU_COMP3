package br.uffrj.comp3.rusys.model.vo;

public class TicketVO {
	private int id;
	private boolean pago;
	private float valor;
	private String nome;
	private int matricula;
	private int refeicao;
	


	public boolean isPago() {
		return pago;
	}

	public static void setPago(boolean pago) {
		pago = pago;
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

	public int getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(int refeicao) {
		this.refeicao = refeicao;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
		
}


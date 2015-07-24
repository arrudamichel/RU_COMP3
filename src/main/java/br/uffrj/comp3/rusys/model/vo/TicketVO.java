package br.uffrj.comp3.rusys.model.vo;

public class TicketVO {
	private Integer id;
	private boolean pago;
	private float valor;
	private String nome;
	private Integer matricula;
	private Integer refeicao;
	private Integer consumidorId;
	


	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago2) {
		this.pago = pago2;
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

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Integer getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Integer refeicao) {
		this.refeicao = refeicao;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public Integer getConsumidorId()
	{
		return this.consumidorId;
	}

	public void setConsumidorId(Integer consumidorId)
	{
		this.consumidorId = consumidorId;
	}	
}


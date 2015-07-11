package br.uffrj.comp3.model;

public class Ticket {
	private boolean pago;
	private float valor;
	private Consumidor consumidor;

	public Ticket() {
		// TODO Auto-generated constructor stub
	}

	public Ticket(boolean pago, float valor, Consumidor consumidor) {
		super();
		this.pago = pago;
		this.valor = valor;
		this.consumidor = consumidor;
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

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}
	
	
	

}

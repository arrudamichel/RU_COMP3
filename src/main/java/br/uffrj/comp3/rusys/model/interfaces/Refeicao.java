package br.uffrj.comp3.rusys.model.interfaces;

import java.util.List;

import br.uffrj.comp3.rusys.model.Ticket;
import br.uffrj.comp3.rusys.model.TipoRefeicaoEnum;
import br.uffrj.comp3.rusys.model.TurnoEnum;

public interface Refeicao 
{
	public TurnoEnum getTurno();

	public String getDescricao();

	public void setDescricao(String descricao) throws Exception;

	public String getOpcaoVeg();

	public void setOpcaoVeg(String opcaoVeg);

	public Integer getId();

	public List<Ticket> getTickets();

	public void setTickets(List<Ticket> tickets);

	public TipoRefeicaoEnum getTipo();
}

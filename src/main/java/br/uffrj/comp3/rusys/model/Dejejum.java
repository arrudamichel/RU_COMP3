package br.uffrj.comp3.rusys.model;

import br.uffrj.comp3.rusys.util.Constantes;

public class Dejejum extends Refeicao
{

	public Dejejum(int id, String descricao) throws Exception
	{
		super(id, descricao);
		super.turno = Constantes.TURNO_DEJEJUM;
	}

}

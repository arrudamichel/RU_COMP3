package br.uffrj.comp3.rusys.model;

public class TipoRefeicaoEnum
{	
	public static final TipoRefeicaoEnum DEJEJUM = new TipoRefeicaoEnum("Dejejum"); 
	public static final TipoRefeicaoEnum ALMOCO = new TipoRefeicaoEnum("Almoco"); 
	public static final TipoRefeicaoEnum JANTAR = new TipoRefeicaoEnum("Jantar");
	
	private String valor;
	
	private TipoRefeicaoEnum(String string)
	{
		valor = string;
	}
	
	public static TipoRefeicaoEnum fromString(String string)
	{
		if (string.toLowerCase().equals("dejejum"))
		{
			return DEJEJUM;
		} 
		else if (string.toLowerCase().equals("almoco"))
		{
			return ALMOCO;
		}	
		else if (string.toLowerCase().equals("jantar"))
		{
			return JANTAR;
		}	
		return null;
	}
	
	public static String toString(TipoRefeicaoEnum e)
	{
		return new String(e.valor);
	}
	
	public String toString()
	{
		return new String(this.valor);
	}
	
	public static TipoRefeicaoEnum[] values()
	{
		TipoRefeicaoEnum[] array = new TipoRefeicaoEnum[3];
		array[0] = ALMOCO;
		array[1] = DEJEJUM;
		array[2] = JANTAR;
		
		return array;
	}

}

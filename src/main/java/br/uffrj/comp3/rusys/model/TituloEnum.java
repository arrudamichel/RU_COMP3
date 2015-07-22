package br.uffrj.comp3.rusys.model;

public class TituloEnum
{
	private static final TituloEnum ESPECIALIZACAO = new TituloEnum("Especializacao"); 
	private static final TituloEnum MESTRADO = new TituloEnum("Mestrado"); 
	private static final TituloEnum DOUTORADO = new TituloEnum("Doutorado"); 
	
	private String valor;
	
	private TituloEnum(String string)
	{
		valor = string;
	}
	
	public static TituloEnum fromString(String string)
	{
		if (string.equals("Especializacao") || string.equals("ESPECIALIZACAO"))
		{
			return ESPECIALIZACAO;
		} 
		else if (string.equals("Mestrado") || string.equals("MESTRADO"))
		{
			return MESTRADO;
		}	
		else if (string.equals("Doutorado") || string.equals("DOUTORADO"))
		{
			return DOUTORADO;
		}	
		return null;
	}
	
	public static String toString(TituloEnum e)
	{
		return new String(e.valor);
	}
	
	public String toString()
	{
		return new String(this.valor);
	}
	
	public static TituloEnum[] values()
	{
		TituloEnum[] array = new TituloEnum[3];
		array[0] = ESPECIALIZACAO;
		array[1] = MESTRADO;
		array[2] = DOUTORADO;
		return array;
	}
}

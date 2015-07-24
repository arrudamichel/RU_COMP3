package br.uffrj.comp3.rusys.model;

public class TituloEnum
{
	public static final TituloEnum ESPECIALIZACAO = new TituloEnum("Especializacao"); 
	public static final TituloEnum MESTRADO = new TituloEnum("Mestrado"); 
	public static final TituloEnum DOUTORADO = new TituloEnum("Doutorado"); 
	public static final TituloEnum VAZIO = new TituloEnum("vazio"); 
	
	private String valor;
	
	private TituloEnum(String string)
	{
		valor = string;
	}
	
	public static TituloEnum fromString(String string)
	{
		if (string==null)
			return VAZIO;
		
		if (string.toLowerCase().equals("especializacao"))
		{
			return ESPECIALIZACAO;
		} 
		else if (string.toLowerCase().equals("mestrado"))
		{
			return MESTRADO;
		}	
		else if (string.toLowerCase().equals("doutorado"))
		{
			return DOUTORADO;
		}	
		return VAZIO;
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
		TituloEnum[] array = new TituloEnum[4];
		array[0] = ESPECIALIZACAO;
		array[1] = MESTRADO;
		array[2] = DOUTORADO;
		array[3] = VAZIO;
		return array;
	}
}

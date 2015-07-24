package br.uffrj.comp3.rusys.model;

public class TurnoEnum
{
	public static final TurnoEnum MANHA = new TurnoEnum("Manha"); 
	public static final TurnoEnum TARDE = new TurnoEnum("Tarde"); 
	public static final TurnoEnum NOITE = new TurnoEnum("Noite"); 
	public static final TurnoEnum VAZIO = new TurnoEnum("vazio"); 
	
	private String valor;
	
	private TurnoEnum(String string)
	{
		valor = string;
	}
	
	public static TurnoEnum fromString(String string)
	{
		if (string==null)
			return VAZIO;
		
		if (string.toLowerCase().equals("manha"))
		{
			return MANHA;
		} 
		else if (string.toLowerCase().equals("tarde"))
		{
			return TARDE;
		}	
		else if (string.toLowerCase().equals("noite"))
		{
			return NOITE;
		}	
		return VAZIO;
	}
	
	public static String toString(TurnoEnum e)
	{
		return new String(e.valor);
	}
	
	public String toString()
	{
		return new String(this.valor);
	}
	
	public static TurnoEnum[] values()
	{
		TurnoEnum[] array = new TurnoEnum[4];
		array[0] = MANHA;
		array[1] = TARDE;
		array[2] = NOITE;
		array[3] = VAZIO;
		return array;
	}
}

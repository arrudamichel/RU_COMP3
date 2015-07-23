package br.uffrj.comp3.rusys.model;

public class TurnoEnum
{
	public static final TurnoEnum MANHA = new TurnoEnum("Manha"); 
	public static final TurnoEnum TARDE = new TurnoEnum("Tarde"); 
	public static final TurnoEnum NOITE = new TurnoEnum("Noite"); 
	
	private String valor;
	
	private TurnoEnum(String string)
	{
		valor = string;
	}
	
	public static TurnoEnum fromString(String string)
	{
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
		return null;
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
		TurnoEnum[] array = new TurnoEnum[3];
		array[0] = MANHA;
		array[1] = TARDE;
		array[2] = NOITE;
		return array;
	}
}

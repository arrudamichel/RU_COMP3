package br.uffrj.comp3.rusys.model;

public class SexoEnum
{
	private static final SexoEnum M = new SexoEnum("Masculino"); 
	private static final SexoEnum F = new SexoEnum("Feminino"); 
	
	private String valor;
	
	private SexoEnum(String string)
	{
		valor = string;
	}
	
	public static SexoEnum fromString(String string)
	{
		if (string.equals("Masculino") || string.equals("M") )
		{
			return M;
		} 
		else if (string.equals("Feminino") || string.equals("F"))
		{
			return F;
		}	
		return null;
	}
	
	public static String toString(SexoEnum e)
	{
		return new String(e.valor);
	}
	
	public String toString()
	{
		return new String(this.valor);
	}
	
	public static SexoEnum[] values()
	{
		SexoEnum[] array = new SexoEnum[2];
		array[0] = M;
		array[1] = F;
		return array;
	}
}

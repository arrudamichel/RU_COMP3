package br.uffrj.comp3.rusys.model;

public class SexoEnum
{
	public static final SexoEnum M = new SexoEnum("Masculino"); 
	public static final SexoEnum F = new SexoEnum("Feminino"); 
	public static final SexoEnum VAZIO = new SexoEnum("vazio"); 
	
	private String valor;
	
	private SexoEnum(String string)
	{
		valor = string;
	}
	
	public static SexoEnum fromString(String string)
	{
		if (string==null)
			return VAZIO;
		
		if (string.toLowerCase().equals("masculino") || string.toLowerCase().equals("m") )
		{
			return M;
		} 
		else if (string.toLowerCase().equals("feminino") || string.toLowerCase().equals("f"))
		{
			return F;
		}	
		return VAZIO;
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
		SexoEnum[] array = new SexoEnum[3];
		array[0] = M;
		array[1] = F;
		array[3] = VAZIO;
		return array;
	}
}

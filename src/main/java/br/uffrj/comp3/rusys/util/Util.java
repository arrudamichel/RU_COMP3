package br.uffrj.comp3.rusys.util;

public class Util
{
	public static boolean valida(String cpfstr)
	{
		if (cpfstr.length() != 11)
			return false;

		int[] a = new int[11];
		char[] cpf = cpfstr.toCharArray();
		
		for (int i = 0; i < cpf.length; i++)
		{
			if (Character.isDigit(cpf[i]))
				a[i] = cpf[i] - '0';
		}

		int b1 = 0, b2 = 0;

		for (int i = 1; i < 10; i++)
		{
			b1 += i * a[i - 1];
			b2 += (10 - i) * a[i - 1];
		}

		b1 = (b1 % 11 == 10) ? 0 : b1 % 11;
		b2 = (b2 % 11 == 10) ? 0 : b2 % 11;

		return (b1 == a[9] && b2 == a[10]);
	}
}

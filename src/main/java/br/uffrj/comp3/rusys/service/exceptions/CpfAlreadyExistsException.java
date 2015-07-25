package br.uffrj.comp3.rusys.service.exceptions;

public class CpfAlreadyExistsException extends Exception 
{
	private String cpf;

	public CpfAlreadyExistsException(String cpf)
	{
		this.cpf = cpf;
	}

}

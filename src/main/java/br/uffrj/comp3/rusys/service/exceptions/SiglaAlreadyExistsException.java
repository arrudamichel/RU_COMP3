package br.uffrj.comp3.rusys.service.exceptions;

public class SiglaAlreadyExistsException extends Exception 
{
	
	private String sigla;

	public SiglaAlreadyExistsException(String sigla)
	{
		this.sigla = sigla;
	}

}

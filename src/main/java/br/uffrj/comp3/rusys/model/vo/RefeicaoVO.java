package br.uffrj.comp3.rusys.model.vo;
import br.uffrj.comp3.rusys.model.TurnoEnum;

public class RefeicaoVO
{
	private int id;
	private int identificador;
	private String descricao;
	private String opcaoVeg;
	private TurnoEnum turno;	

	public RefeicaoVO()
	{
		// TODO Auto-generated constructor stub
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public String getOpcaoVeg()
	{
		return opcaoVeg;
	}

	public void setOpcaoVeg(String opcaoVeg)
	{
		this.opcaoVeg = opcaoVeg;
	}

	public TurnoEnum getTurno()
	{
		return turno;
	}

	public void setTurno(TurnoEnum turno)
	{
		this.turno = turno;
	}

	public int getIdentificador()
	{
		return identificador;
	}

	public void setIdentificador(int identificador)
	{
		this.identificador = identificador;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}

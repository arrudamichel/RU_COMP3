package br.uffrj.comp3.rusys.model.vo;
import br.uffrj.comp3.rusys.model.TipoRefeicaoEnum;
import br.uffrj.comp3.rusys.model.TurnoEnum;

public class RefeicaoVO
{
	private Integer id;
	private Integer identificador;
	private String descricao;
	private String opcaoVeg;
	private TurnoEnum turno;	
	private TipoRefeicaoEnum tipo;

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

	public Integer getIdentificador()
	{
		return identificador;
	}

	public void setIdentificador(Integer identificador)
	{
		this.identificador = identificador;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public TipoRefeicaoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoRefeicaoEnum tipo) {
		this.tipo = tipo;
	}
}

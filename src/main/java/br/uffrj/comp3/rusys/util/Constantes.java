/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uffrj.comp3.rusys.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import br.uffrj.comp3.rusys.model.Aluno;
import br.uffrj.comp3.rusys.model.Funcionario;
import br.uffrj.comp3.rusys.model.TurnoEnum;

/**
 *
 * @author liliane
 */
public class Constantes
{
//	TURNO_CONSUMIDOR -> VALOR
	public static final Map<String, Float> mapaTurnoConsumidor_PRECO;
    static {
        Map<String, Float> mapa = new HashMap<String, Float>();
        mapa.put(TurnoEnum.MANHA.toString() + Aluno.class, (float) 0.5);
        mapa.put(TurnoEnum.MANHA.toString() + Funcionario.class, (float) 3.0);
        mapa.put(TurnoEnum.TARDE.toString() + Aluno.class, (float) 1.0);
        mapa.put(TurnoEnum.TARDE.toString() + Funcionario.class, (float) 6.0);
        mapa.put(TurnoEnum.NOITE.toString() + Aluno.class, (float) 1.0);
        mapa.put(TurnoEnum.NOITE.toString() + Funcionario.class, (float) 6.0);
        
        mapaTurnoConsumidor_PRECO = Collections.unmodifiableMap(mapa);
    }
	
//	REFEICAO -> TURNO
	final public static TurnoEnum TURNO_ALMOCO = TurnoEnum.TARDE;
	final public static TurnoEnum TURNO_DEJEJUM = TurnoEnum.MANHA;
	final public static TurnoEnum TURNO_JANTAR = TurnoEnum.NOITE;
	
	final public static String ALMOCO = "Almo&ccedilo";
	final public static String DEJEJUM = "Dejejum";
	final public static String JANTAR = "Jantar";
	
//	Configuracao
	final public static String DBPATH = "jdbc:h2:~/git/RU_COMP3/RU";
	final public static String USER = "sa";
	final public static String PASS = "sa";

	
//	view
	final public static String BEMVINDO = "Bem vindo ao sistema do restaurante Universit&aacuterio !";
	
	final public static String ALUNOS = "Alunos";
	final public static String ANOINGRESSO = "Ano de Ingresso ";
	final public static String ACAO_EDITAR = "Editar";
	final public static String ACAO_DELETAR = "Excluir";
	final public static String ACAO_LISTAR = "Listar";
	final public static String ACAO_SALVAR = "Salvar";
	final public static String ACAO_CANCELAR = "Cancelar";
	final public static String ACAO_PROXIMO = "Proximo";

	final public static String CADREFEICAO = "Cadastro de Refei&ccedil&otildees";
	final public static String CADALUNOS = "Cadastro de Alunos ";
	final public static String CPF = "CPF ";
	final public static String CURSO = "Curso";
	final public static String CANCELAR = "Cancelar ";
	final public static String NOVO = "Novo ";
	final public static String CADCURSO = "Cadastro de " + CURSO;

	final public static String DESCRICAO = "Descri&ccedil&atildeo";
	final public static String DOUTORADO = "Doutorado";
	final public static String DELETE = "Deletar ";
	final public static String DEPTO = "Departamento";
	final public static String CADDEPTO = "Cadastro de " + DEPTO;

	final public static String ESPECIALIZACAO = "Especializa&ccedil;&atilde;o";
	final public static String EDITAR = "Editar ";

	final public static String FUNCIONARIO = "Funcion&aacuterios";
	final public static String CADFUNC = "Cadastro de " + FUNCIONARIO;
	final public static String FEMININO = "Feminino ";

	final public static String LISTREFEICAO = "Lista de Refei&ccedil&otildees dispon&iacuteveis";
	final public static String LISTALUNOS = "Lista de Alunos cadastrados no sistema";
	final public static String LISTFUNCIONARIO = "Lista de " + FUNCIONARIO + " cadastrados no sistema";
	final public static String LISTDEPTO = "Lista de " + DEPTO + "s cadastrados no sistema";
	final public static String LISTCURSO = "Lista de " + CURSO + "s cadastrados no sistema";

	final public static String MATRICULA = "Matr&iacute;cula";
	final public static String MESTRADO = "Mestrado";
	final public static String MASCULINO = "Masculino ";

	final public static String NOME = "Nome ";

	final public static String OPVEG = "Op&ccedil&atildeo Vegetariana ";
	final public static String PAGO = "Pago";

	final public static String RU = "Restaurante Universit&aacuterio";
	final public static String REFEICAO = "Refei&ccedil&atildeo";

	final public static String SEXO = "Sexo ";
	final public static String SALVAR = "Salvar ";
	final public static String EXCLUIR = "Excluir";
	final public static String SIGLA = "Sigla ";
	final public static String SITPAGO = "Situa&ccedil&atildeo do Pagamento ";

	final public static String SIM = "Sim";
	final public static String NAO = "Não";
	
	final public static String TITULO = "T&iacute;tulo Acad&ecircmico";
	final public static String TURNO = "Turno";
	final public static String LISTTURNO = "Lista de " + TURNO + "s de " + REFEICAO;
	final public static String TICKET = "Ticket";
	final public static String LISTTICKET = "Lista de " + TICKET + "s de " + REFEICAO;
	final public static String CADTICKET = "Compra de " + TICKET + "s";

//	Mensagens
	final public static String VALOR = "Pre&ccedilo";

	final public static String ERRO = "Erro ao executar opera&ccedil&atildeo";
	final public static String ERRO_NUM = "Erro, verifique os formatos num&eacutericos";
	final public static String ERRO_SQL = "Erro, verifique a consulta";

	final public static String SUCESSO = "Sucesso ao executar opera&ccedil&atildeo";

	// TEMPLATE
	final public static String BASE_CSS = "<link rel='stylesheet' href='webapp/WEB-INF/resources/Stylesheets/base.css' type='text/css' media='screen' /> ";
	final public static String ESTILO_CSS = "<link rel='stylesheet' id='current-theme' href='webapp/WEB-INF/resources/Stylesheets/themes/default/style.css' type='text/css' media='screen' />";
	final public static String JQUERY_LINK = "<script type='text/javascript' charset='utf-8' src='webapp/WEB-INF/resources/Javascripts/jquery-2.1.3.min.js'></script>";
}

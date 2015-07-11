package br.uffrj.comp3.model;
//creio que esse menu não deveria ficar neste pacote, mas meu eclipse estava dando erro, 
//acho legal criar uma classe de constantes tambem, por que se mudarmos os diretorios dos 
//arquivos de template, vamos ter de mudar em todos os arquivos jsp de novo
public class Menu {
	public String menu()	
	{
		StringBuilder menu = new StringBuilder();
		
		menu.append("<div id='header'>");
		menu.append("<h1><a href='index.jsp'>Restaurante Universitário</a></h1>");
	    menu.append("<div id='main-navigation'>");
	    menu.append("<ul class='wat-cf'>");
	    menu.append("<li class='active'><a href='index.jsp'>Refeição</a></li>");
	    menu.append("<li><a href='listarAlunos.jsp'>Alunos</a></li>");
	    menu.append("<li><a href='listarFuncionarios.jsp'>Funcionários</a></li>");
	    menu.append("</ul>");
	    menu.append("</div>");
	    menu.append("</div>");
		
		return menu.toString();
	} 	
}

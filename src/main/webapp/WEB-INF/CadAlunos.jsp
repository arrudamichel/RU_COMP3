<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="br.uffrj.comp3.rusys.model.Aluno"%>
<%@page import="br.uffrj.comp3.rusys.model.Curso"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java"
	pageEncoding="UTF-8"%>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%@include file="messagePage.jsp" %>
<%
	//se tivesse verificacao de login, aqui que ele seria programado
    
	/* String mensagem = request.getAttribute("mensagem") == null ? "" : (String)request.getAttribute("mensagem");

	String acao = (String)request.getAttribute("acao"); */
	
	ArrayList<Aluno> alunos = (ArrayList<Aluno>)request.getAttribute("alunos");
	ArrayList<Curso> cursos = (ArrayList<Curso>)request.getAttribute("cursos");


	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title><%=Constantes.RU%></title>
 <%@include file="menu.jspf" %>
  <script type='text/javascript' charset='utf-8' src='resources/Javascripts/jsAlunos.js'></script>
  <script type="text/javascript" charset="utf-8">
    // <![CDATA[
    $(document).ready(function() {
    });
    // ]]>
  </script>
</head>
<div id="container">
	<%@include file="menu.jspf" %>
    <div id="wrapper" class="wat-cf">
      <div id="main">
        <div class="block" id="block-forms">
          <div class="secondary-navigation">
            <ul class="wat-cf">
                <li class="active"><a href="#block-text"><%=Constantes.ALUNOS%></a></li>
            </ul>
          </div>
          <div class="content">
            <h2 class="title"><%=Constantes.CADALUNOS%> </h2>
            
            <table id="table-resultado" class="table">
                  <tr>
                    <th class="first"></th>
                    <th><%=Constantes.NOME%></th>
                    <th><%=Constantes.MATRICULA%></th>
                    <th><%=Constantes.ANOINGRESSO%></th>
                    <th><%=Constantes.CURSO%></th>
                    <th><%=Constantes.CPF%></th>
                    <th class="last">&nbsp;</th>
                  </tr>
                  <% if (alunos != null) {           
	                for(int i=0; i < alunos.size(); i++) {
	                	String urlEditar="GerirAluno?acao="+ Constantes.ACAO_EDITAR+ "&matricula="+alunos.get(i).getMatricula();
             		   	String urlDelete ="GerirAluno?acao="+ Constantes.ACAO_DELETAR+ "&matricula="+alunos.get(i).getMatricula();
             		   	
	                  	if(i%2 == 0){ %>
	                  	<tr class="odd">                 	    
                    		<input type="hidden" name="id" value=<%=alunos.get(i).getMatricula()%> />                  
	                        <td><%=alunos.get(i).getNome()%></td>
	                        <td><%=alunos.get(i).getMatricula()%></td>
	                        <td><%=alunos.get(i).getAnoDeIngresso()%></td>                                              
	                        <td><%=alunos.get(i).getCurso().getNome()%></td>
	                        <td><%=alunos.get(i).getCpf()%></td>
	                        <td class="last"><a href="<%=urlEditar %>"><%=Constantes.EDITAR%></a> </td>	
	                       	<td class="last"><a href="<%=urlDelete %>"><%=Constantes.DELETE%></a> </td>	                        
	                                                
	                    </tr>
	                <% } else { %>
	                    <tr class="even">
                    		<input type="hidden" name="id" value=<%=alunos.get(i).getMatricula()%>/>                 
	                        <td><%=alunos.get(i).getNome()%></td>
	                        <td><%=alunos.get(i).getMatricula()%></td>
	                        <td><%=alunos.get(i).getAnoDeIngresso()%></td>                                              
	                        <td><%=alunos.get(i).getCurso().getNome()%></td>
	                        <td><%=alunos.get(i).getCpf()%></td>
	                        <td class="last"><a href="<%=urlEditar %>"><%=Constantes.EDITAR%></a> </td>	
	                       	<td class="last"><a href="<%=urlDelete %>"><%=Constantes.DELETE%></a> </td>	                   
	                    </tr>                    
	            	<%}  }
	           		}%>
                </table>
            
            <div class="inner">
              <form id="FrmAluno" name="FrmAluno" action="GerirAluno" method="POST" class="form">
        	  <input type = "hidden" id="id" name = "id" <% /* Caso de edicão if (pergunta != null && pergunta.getId() != null ) { out.print(" value = '" + pergunta.getId() + "'"); } */ %>>
                <div class="group">
                  <label class="label"><%=Constantes.NOME%></label>
                  <input type="text" id="nome" name="nome" <% /// if (pergunta != null && pergunta.getPergunta() != null ) { out.print(" value = '" + pergunta.getPergunta() + "'"); } %> class="text_field" onblur="testaCampo(this, 'Nome')"/>
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.MATRICULA%></label>
                   <input type="text" id="matricula" name="matricula" <% /// if (pergunta != null && pergunta.getPergunta() != null ) { out.print(" value = '" + pergunta.getPergunta() + "'"); } %> class="text_field" />
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.ANOINGRESSO%></label>
                  	<input type="text"  id="anoIngresso" name="anoIngresso" <% //if (pergunta != null && pergunta.getQtdRespostas() != null ) { out.print(" value = '" + pergunta.getQtdRespostas() + "'"); } %> class="text_field" />
                </div> 
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.SEXO%></label>
                    <input type="radio" id="sexo" name="sexo" value="M"> &nbsp;<%=Constantes.MASCULINO%> &nbsp;&nbsp;
                    <input type="radio" id="sexo" name="sexo" value="F"> &nbsp;<%=Constantes.FEMININO%>
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.TITULO%></label>
                    <select id ="titulo" name="titulo">
                        <option value="">Selecione</option>
                        <option value="ESPECIALIZACAO"><%=Constantes.ESPECIALIZACAO%></option>
                        <option value="MESTRADO"><%=Constantes.MESTRADO%></option>
                        <option value="DOUTORADO"><%=Constantes.DOUTORADO%></option>
                    </select>           
                </div>
                
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.CPF%></label>
                    <input type="text"  id="cpf" name="cpf" <% //if (pergunta != null && pergunta.getQtdRespostas() != null ) { out.print(" value = '" + pergunta.getQtdRespostas() + "'"); } %> class="text_field" onblur="validaCPF(this)"/>     
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.CURSO%></label>
                    <select id ="curso" name="curso">
                    <% if (cursos != null){ 
	                    for(Curso curso : cursos){ %>
	                        <option value="<%=curso.getIdentificador()%>"><%=curso.getNome()%></option>
	                    <% } }%>                        
                    </select> 
                    <%/// Listar aqui, se quiser mando essa funcao combo out.print(new TipoDocumentoBll().comboHtml("turno", pergunta == null || pergunta.getTipoDocumento() == null || pergunta.getTipoDocumento().getId() == null  ? null : pergunta.getTipoDocumento().getId().toString(), "Selecione"));%>
                </div>
                <div class="group navform wat-cf">
                  <button class="button" type="submit" name="acao" value="<%=Constantes.SALVAR%>">
                    <img src="Images/icons/tick.png" alt="Save" /> <%=Constantes.SALVAR%>
                  </button>
                  <span class="text_button_padding">Ou</span>
                  <a class="text_button_padding link_button" href="index.jsp"><%=Constantes.CANCELAR%></a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
</div>
</body>
</html>
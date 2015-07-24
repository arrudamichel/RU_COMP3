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
 <%@include file="header.jspf" %>
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
                    <th><%=Constantes.NOME%></th>
                    <th><%=Constantes.MATRICULA%></th>
                    <th><%=Constantes.ANOINGRESSO%></th>
                    <th><%=Constantes.SEXO%></th>
                    <th><%=Constantes.CURSO%></th>
                    <th><%=Constantes.CPF%></th>
                    <th class="last">&nbsp;</th>
                  </tr>
                  <% if (alunos != null) {           
	                for(int i=0; i < alunos.size(); i++) {
	                	String urlEditar="GerirAluno?acao="+ Constantes.ACAO_EDITAR+ "&id="+alunos.get(i).getId();
             		   	String urlDelete ="GerirAluno?acao="+ Constantes.ACAO_DELETAR+ "&id="+alunos.get(i).getId();
             		   	
	                  	if(i%2 == 0){ %>
	                  	<tr class="odd">                 	    
                    		<input type="hidden" name="id" value=<%=alunos.get(i).getMatricula()%> />                  
	                        <td><%=alunos.get(i).getNome()%></td>
	                        <td><%=alunos.get(i).getMatricula()%></td>
	                        <td><%=alunos.get(i).getAnoDeIngresso()%></td> 
	                        <td><%=alunos.get(i).getSexo()%></td>                                               
	                        <td><%=alunos.get(i).getCurso().getSigla()%></td>
	                        <td><%=alunos.get(i).getCpf()%></td>
	                        <td class="last"><a href="<%=urlEditar %>"><%=Constantes.EDITAR%></a>&nbsp;&nbsp;
	                         <a href="<%=urlDelete %>"><%=Constantes.DELETE%></a> </td>	                        
	                                                
	                    </tr>
	                <% } else { %>
	                    <tr class="even">
                    		<input type="hidden" name="id" value=<%=alunos.get(i).getMatricula()%>/>                 
	                        <td><%=alunos.get(i).getNome()%></td>
	                        <td><%=alunos.get(i).getMatricula()%></td>
	                        <td><%=alunos.get(i).getAnoDeIngresso()%></td>  
	                        <td><%=alunos.get(i).getSexo()%></td>                                                
	                        <td><%=alunos.get(i).getCurso().getSigla()%></td>
	                        <td><%=alunos.get(i).getCpf()%></td>
	                        <td class="last"><a href="<%=urlEditar %>"><%=Constantes.EDITAR%></a> &nbsp;&nbsp;
	                        <a href="<%=urlDelete %>"><%=Constantes.DELETE%></a> </td>	                   
	                    </tr>                    
	            	<%}  }
	           		}%>
                </table>
          </div>
          <form action="GerirAluno">
          <input type="submit" name="acao" value="<%=Constantes.NOVO%>"> 
          
          </form>
        </div>
      </div>
    </div>
</div>
</body>
</html>
<%@page import="br.uffrj.comp3.rusys.model.Departamento"%>
<%@page import="br.uffrj.comp3.rusys.model.Funcionario"%>
<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java"
	pageEncoding="UTF-8"%>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%	
	ArrayList<Funcionario> funcionarios = (ArrayList<Funcionario>)request.getAttribute("funcionarios");
	ArrayList<Departamento> departamentos = (ArrayList<Departamento>)request.getAttribute("departamentos");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title><%=Constantes.RU%></title>
 <%@include file="header.jspf" %>
  <script type='text/javascript' charset='utf-8' src='resources/Javascripts/jsfuncionarios.js'></script>
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
                <li class="active"><a href="#block-text"><%=Constantes.FUNCIONARIO%></a></li>
            </ul>
          </div>
          <div class="content">
            <h2 class="title"><%=Constantes.CADFUNC%> </h2>
            <%@include file="messagePage.jsp" %>
            <table id="table-resultado" class="table">
                  <tr>
                    <th><%=Constantes.NOME%></th>
                    <th><%=Constantes.MATRICULA%></th>
                    <th><%=Constantes.ANOINGRESSO%></th>
                    <th><%=Constantes.SEXO%></th>
                    <th><%=Constantes.DEPTO%></th>
                    <th><%=Constantes.CPF%></th>
                    <th class="last">&nbsp;</th>
                  </tr>
                  <% if (funcionarios != null) {           
	                for(int i=0; i < funcionarios.size(); i++) {
	                	String urlEditar="GerirFuncionario?acao="+ Constantes.ACAO_EDITAR+ "&id="+funcionarios.get(i).getId();
             		   	String urlDelete ="GerirFuncionario?acao="+ Constantes.ACAO_DELETAR+ "&id="+funcionarios.get(i).getId();
             		   	
	                  	if(i%2 == 0){ %>
	                  	<tr class="odd">                 	    
                    		<input type="hidden" name="id" value=<%=funcionarios.get(i).getMatricula()%> />                  
	                        <td><%=funcionarios.get(i).getNome()%></td>
	                        <td><%=funcionarios.get(i).getMatricula()%></td>
	                        <td><%=funcionarios.get(i).getAnoDeIngresso()%></td> 
	                        <td><%=funcionarios.get(i).getSexo()%></td>                                               
	                        <td><%=funcionarios.get(i).getDepartamento().getSigla()%></td>
	                        <td><%=funcionarios.get(i).getCpf()%></td>
	                        <td class="last"><a href="<%=urlEditar %>"><%=Constantes.EDITAR%></a> </td>	
	                       	<td class="last"><a href="<%=urlDelete %>"><%=Constantes.DELETE%></a> </td>	                        
	                                                
	                    </tr>
	                <% } else { %>
	                    <tr class="even">
                    		<input type="hidden" name="id" value=<%=funcionarios.get(i).getMatricula()%>/>                 
	                        <td><%=funcionarios.get(i).getNome()%></td>
	                        <td><%=funcionarios.get(i).getMatricula()%></td>
	                        <td><%=funcionarios.get(i).getAnoDeIngresso()%></td>  
	                        <td><%=funcionarios.get(i).getSexo()%></td>                                                
	                        <td><%=funcionarios.get(i).getDepartamento().getSigla()%></td>
	                        <td><%=funcionarios.get(i).getCpf()%></td>
	                        <td class="last"><a href="<%=urlEditar %>"><%=Constantes.EDITAR%></a> </td>	
	                       	<td class="last"><a href="<%=urlDelete %>"><%=Constantes.DELETE%></a> </td>	                   
	                    </tr>                    
	            	<%}  }
	           		}%>
                </table>
          </div>
          <form action="GerirFuncionario">
          <input type="submit" name="acao" value="<%=Constantes.NOVO%>"> 
          
          </form>
        </div>
      </div>
    </div>
</div>
</body>
</html>
<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="br.uffrj.comp3.rusys.model.Departamento"%>
<%@page import="br.uffrj.comp3.rusys.model.Curso"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java"
	pageEncoding="UTF-8"%>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
	ArrayList<Curso> cursos = (ArrayList<Curso>)request.getAttribute("cursos");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="header.jspf" %>
<script type='text/javascript' charset='utf-8'  src='resources/Javascripts/jsAlunos.js'></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
	});
</script>
<div id="container">
	<%@include file="menu.jspf" %>
	<div id="wrapper" class="wat-cf">
		<div id="main">
			<div class="block" id="block-forms">
				<div class="secondary-navigation">
					<ul class="wat-cf">
						<li class="active"><a href="#block-text"><%=Constantes.CURSO%></a></li>
					</ul>
				</div>
				<div class="content">
					<h2 class="title"><%=Constantes.CADCURSO%></h2>
					<%@include file="messagePage.jsp" %>
					<table id="table-resultado" class="table">
	                  <tr>
	                    <th><%=Constantes.NOME%></th>
	                    <th><%=Constantes.SIGLA%></th>
	                    <th class="last">&nbsp;</th>
	                  </tr>
	                  <%	                  	
	                  	if(cursos != null){
			                for(int i=0; i < cursos.size(); i++) {
			                	String urlEditar="GerirCurso?acao="+ Constantes.ACAO_EDITAR +"&cursoId="+cursos.get(i).getId();
		
			                  	if(i%2 == 0){ %>
			                  	<tr class="odd">                  
			                        <td><%=cursos.get(i).getNome()%></td>                                              
			                        <td><%=cursos.get(i).getSigla()%></td>
			                        <td class="last"><a href="<%=urlEditar%>"><%=Constantes.EDITAR%></a> </td>	                        
			                    </tr>
			                <% } else { %>
			                    <tr class="even">                      
									<td><%=cursos.get(i).getNome()%></td>
			                        <td><%=cursos.get(i).getSigla()%></td>
			                        <td class="last"> <a href="<%=urlEditar%>"><%=Constantes.EDITAR%></a> </td>	                                   
			                    </tr>                    
			            	<%}  
			           		}
			           	}%>  
	                </table>
	                <form action="GerirCurso">
          				<input type="submit" name="acao" value="<%=Constantes.NOVO%>"> 
          
		          </form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
<%@page import="br.uffrj.comp3.rusys.model.RefeicaoImpl"%>
<%@page import="br.uffrj.comp3.rusys.model.interfaces.*"%>
<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="br.uffrj.comp3.rusys.model.TurnoEnum"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html; charset=ISO-8859-1" language="java"
	pageEncoding="UTF-8"%>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
	ArrayList<Refeicao> refeicoes = (ArrayList<Refeicao>)request.getAttribute("refeicoes");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constantes.RU%></title>
<%=Constantes.BASE_CSS%>
<%=Constantes.ESTILO_CSS%>
<%=Constantes.JQUERY_LINK%>
<script type='text/javascript' charset='utf-8' src='resources/Javascripts/jsRefeicao.js'></script>
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
						<li class="active"><a href="#block-text"><%=Constantes.REFEICAO%></a></li>
					</ul>
				</div>
				<div class="content">
					<h2 class="title"><%=Constantes.CADREFEICAO%></h2>
					<%@include file="messagePage.jsp" %>
					<table class="table">
                       <tr>
                       	   <th></th>
                       	   <th><%=Constantes.TIPO%></th>
                           <th><%=Constantes.TURNO%></th>
                           <th><%=Constantes.DESCRICAO%></th>
                           <th><%=Constantes.OPVEG%></th>
                           <th class="last">&nbsp;</th>
                           <th class="last">&nbsp;</th>
                       </tr>     
                       <%
                       	if(refeicoes != null){
	                       	for(int i=0; i < refeicoes.size(); i++){
	                       		String urlEditar="GerirRefeicao?acao="+ Constantes.ACAO_EDITAR+ "&id="+refeicoes.get(i).getId();
	                       		if(i%2 == 0){
	                       %>
	                       <tr class="odd">                                        
	                           <td><input type="hidden" name="id" value=<%=refeicoes.get(i).getId()%> /></td>
	                           <td><%=refeicoes.get(i).getTipo()%></td>
	                           <td><%=refeicoes.get(i).getTurno()%></td>
	                           <td><%=refeicoes.get(i).getDescricao()%></td>
	                           <td><%=refeicoes.get(i).getOpcaoVeg()%></td>                                            
	                           <td class="last"><a href="<%=urlEditar %>"><%=Constantes.EDITAR%></a> </td>	
	                       </tr>
	                       <%      } else { %>
	                       <tr class="even">
	                           <td><input type="hidden" name="id" value=<%=refeicoes.get(i).getId()%> /></td>
	                           <td><%=refeicoes.get(i).getTipo()%></td>
	                           <td><%=refeicoes.get(i).getTurno()%></td>
	                           <td><%=refeicoes.get(i).getDescricao()%></td>
	                           <td><%=refeicoes.get(i).getOpcaoVeg()%></td>                                            
	                           <td class="last"><a href="<%=urlEditar %>"><%=Constantes.EDITAR%></a> </td>	
	                           
	                       </tr>
	                       
	                       <%		}  
	                       	}
	                       } else {%>
	                       
	                       <tr class="even">
	                           <td colspan=6>Nenhuma refeição registrada.</td>	                           
	                       </tr>
	                       <%}%>
                   </table>
                   <form action="GerirRefeicao" method="GET">
          				<input type="submit" name="acao" value="<%=Constantes.NOVO%>"> 
          
          			</form>	
                   
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
<%@page import="br.uffrj.comp3.rusys.model.TurnoEnum"%>
<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.uffrj.comp3.rusys.model.Ticket"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
	
	ArrayList<Ticket> tickets = (ArrayList<Ticket>)request.getAttribute("tickets");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
                <li class="active"><a href="#block-text"><%=Constantes.TICKET%></a></li>
            </ul>
          </div>
          <div class="content">
				<h2 class="title"><%=Constantes.CADTICKET%></h2>
				<%@include file="messagePage.jsp" %>
				<table class="table">
                      <tr>                      	  
                          <th><%=Constantes.MATRICULA%></th>
                          <th><%=Constantes.VALOR%></th>
                          <th><%=Constantes.PAGO%></th>
                          <th><%=Constantes.TURNO%></th>
                          <th><%=Constantes.DESCRICAO%></th>                          
                          <th class="last">&nbsp;</th>
                          <th class="last">&nbsp;</th>
                      </tr>
                      
                      <%
                      	if(tickets != null){
                       	for(int i=0; i < tickets.size(); i++){
                       		String urlEditar="GerirTicket?acao="+ Constantes.ACAO_EDITAR+ "&id="+tickets.get(i).getId();
                 		   	String urlDelete ="GerirTicket?acao="+ Constantes.ACAO_DELETAR+ "&id="+tickets.get(i).getId();
                       		if(i%2 == 0){
                       %>
                       <tr class="odd">                                        
                           <td><%=tickets.get(i).getConsumidor().getMatricula()%></td>
                           <td><%=tickets.get(i).getValor()%></td>
                           <td>
                           <% if(tickets.get(i).isPago() == true) { %>
                           <%="Sim"%>
                           <% } else { %>
                           <%="Não"%>
                           <% } %>
                           </td>                           
                           <td><%=tickets.get(i).getRefeicao().getTurno()%></td>
                           <td><%=tickets.get(i).getRefeicao().getDescricao()%></td>
                           <td class="last"><a href="<%=urlEditar %>"><%=Constantes.EDITAR%></a> </td>	
                       </tr>
                       <%      } else { %>
                       <tr class="even">
                           <td><%=tickets.get(i).getConsumidor().getMatricula()%></td>
                           <td><%=tickets.get(i).getValor()%></td>
                           <td>
                           <% if(tickets.get(i).isPago() == true) { %>
                           <%="Sim"%>
                           <% } else { %>
                           <%="Não"%>
                           <% } %>
                           </td>                           
                           <td><%=tickets.get(i).getRefeicao().getTurno()%></td>
                           <td><%=tickets.get(i).getRefeicao().getDescricao()%></td>
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
                  <form action="GerirTicket">
          				<input type="submit" name="acao" value="<%=Constantes.NOVO%>"> 
          
		          </form>
			</div>
        </div>
      </div>
    </div>
</div>
</body>
</html>
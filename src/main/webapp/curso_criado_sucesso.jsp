
<%@page import="br.uffrj.comp3.model.<%=Constantes.CURSO%>"%>

<p><%=Constantes.CURSO%> criado com sucesso!</p>
<%
	<%= Constantes.CURSO %> curso = (<%= Constantes.CURSO %>) request.getAttribute("curso");
	out.print("O curso " + curso.get<%=Constantes.NOME%>() + " foi criado com sucesso!");

%>

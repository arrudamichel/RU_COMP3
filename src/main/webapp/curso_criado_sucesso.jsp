
<%@page import="br.uffrj.comp3.model.<%=Constantes.Curso%>"%>

<p><%=Constantes.Curso%> criado com sucesso!</p>
<%
	<%=Constantes.Curso%> curso = (<%=Constantes.Curso%>) request.getAttribute("curso");
	out.print("O curso " + curso.get<%=Constantes.NOME%>() + " foi criado com sucesso!");
%>

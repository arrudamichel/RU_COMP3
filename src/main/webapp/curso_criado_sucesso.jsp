
<%@page import="br.uffrj.comp3.model.Curso"%>

<p>Curso criado com sucesso!</p>
<%
	Curso curso = (Curso) request.getAttribute("curso");
	out.print("O curso " + curso.getNome() + " foi criado com sucesso!");
%>

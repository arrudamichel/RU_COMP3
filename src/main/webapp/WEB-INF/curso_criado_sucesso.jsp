
<%@page import="br.uffrj.comp3.rusys.model.Curso"%>
<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>

<p><%=Constantes.CURSO%> criado com sucesso!</p>
<%
	Curso curso = (Curso) request.getAttribute("curso");
	out.print("O curso " + curso.getNome() + " foi criado com sucesso!");

%>

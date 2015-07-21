<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="br.uffrj.comp3.rusys.model.Departamento"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8"%>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%@include file="messagePage.jsp" %>
<%
	Departamento departamento = (Departamento) request.getAttribute("departamento");
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
						<li class="active"><a href="#block-text"><%=Constantes.DEPTO%></a></li>
					</ul>
				</div>
				<div class="content">
					<h2 class="title"><%=Constantes.EDITAR%>
					</h2>
					<div class="inner">
						<form id="FrmDepartamento" name="FrmDepartamento" action="GerirDepartamento" method="POST" class="form">
							<input type="hidden" id="departamentoId" name="departamentoId" value="<%=(departamento!=null) ? departamento.getId() : "" %>" />
							<div class="group">
								<label class="label"><%=Constantes.NOME%></label> 
								<input type="text" id="nome" name="nome" class="text_field" value="<%=(departamento!=null) ? departamento.getNome() : "" %>" />
							</div>
							<div class="group">
								<label class="label" for="post_title"><%=Constantes.SIGLA%></label>
								<input type="text" id="sigla" name="sigla" class="text_field" value="<%=(departamento!=null) ? departamento.getSigla() : "" %>" />
							</div>
							<input type="submit" name="acao" value="<%=Constantes.ACAO_SALVAR%>">
							<input type="submit" name="acao" value="<%=Constantes.ACAO_CANCELAR%>">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>